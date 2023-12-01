package utn.frc.backend.parcialbda.application.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.backend.parcialbda.application.ResponseHandler;
import utn.frc.backend.parcialbda.application.request.CreatePlaylistRequest;
import utn.frc.backend.parcialbda.application.request.UpdatePlaylistRequest;
import utn.frc.backend.parcialbda.application.response.PlaylistResponse;
import utn.frc.backend.parcialbda.services.interfaces.PlaylistService;
import utn.frc.backend.parcialbda.services.interfaces.TrackService;

@RequestMapping("/api/playlists")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PlaylistController {
    PlaylistService playlistService;
    TrackService trackService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            val playlists = playlistService.findAll().stream()
                    .map(PlaylistResponse::from)
                    .toList();
            return ResponseHandler.success(playlists);
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    /*
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreatePlaylistRequest aRequest) {
        try {
            val playlist = playlistService.create(
                    aRequest.getTitle(),
                    trackService.getTracksByIds(aRequest.getTrackIds())
            );
            return ResponseHandler.success(PlaylistResponse.from(playlist));
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }
     */

    /*
            Crear un nuevo playlist en base a: nombre de la playlist, id de artista, id de género y una cantidad
            de tiempo en minutos que agregue tracks del artista y genero a la nueva lista en orden aleatorio
            hasta alcanzar el tiempo solicitado o se acaben los tracks disponibles
     */
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreatePlaylistRequest aRequest) {
        try {
            val playlist = playlistService.create(
                    aRequest.getTitle(),
                    aRequest.getArtistId(),
                    aRequest.getGenreId(),
                    aRequest.getMinutes()
            );
            return ResponseHandler.success(PlaylistResponse.from(playlist));
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable Integer id) {
        try {
            return playlistService.findById(id)
                    .map(PlaylistResponse::from)
                    .map(ResponseHandler::success)
                    .orElseGet(ResponseHandler::notFound);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.notFound();
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            playlistService.delete(id);
            return ResponseHandler.noContent();
        } catch (IllegalArgumentException e) {
            return ResponseHandler.noContent();
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody UpdatePlaylistRequest aRequest) {
        try {
            playlistService.update(
                    id,
                    aRequest.getTitle(),
                    trackService.getTracksByIds(aRequest.getTrackIds())
                    );
            return ResponseHandler.noContent();
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PostMapping("/{playlistId}/addTrack/{trackId}")
    public ResponseEntity<Object> addTrackToPlaylist(@PathVariable Integer playlistId, @PathVariable Integer trackId) {
        try {
            playlistService.addTrackToPlaylist(playlistId, trackId);
            return ResponseHandler.noContent();
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @DeleteMapping("/{playlistId}/removeTrack/{trackId}")
    public ResponseEntity<Object> removeTrackFromPlaylist(@PathVariable Integer playlistId, @PathVariable Integer trackId) {
        try {
            playlistService.removeTrackFromPlaylist(playlistId, trackId);
            return ResponseHandler.noContent();
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }
}
