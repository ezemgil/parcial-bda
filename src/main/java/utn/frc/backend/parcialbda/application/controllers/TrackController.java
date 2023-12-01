package utn.frc.backend.parcialbda.application.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.backend.parcialbda.application.ResponseHandler;
import utn.frc.backend.parcialbda.application.request.CreateTrackRequest;
import utn.frc.backend.parcialbda.application.request.UpdateTrackRequest;
import utn.frc.backend.parcialbda.application.response.TrackResponse;
import utn.frc.backend.parcialbda.application.response.TrackResponseParcial;
import utn.frc.backend.parcialbda.services.interfaces.PlaylistService;
import utn.frc.backend.parcialbda.services.interfaces.TrackService;

@RequestMapping("/api/tracks")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TrackController {
    TrackService trackService;
    PlaylistService playlistService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            val tracks = trackService.findAll().stream()
                    .map(TrackResponse::from)
                    .toList();
            return ResponseHandler.success(tracks);
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreateTrackRequest aRequest) {
        try {
            val track = trackService.create(
                    aRequest.getName(),
                    aRequest.getGenreId(),
                    aRequest.getMediaTypeId(),
                    aRequest.getAlbumId(),
                    aRequest.getComposer(),
                    aRequest.getMilliseconds(),
                    aRequest.getBytes(),
                    aRequest.getUnitPrice(),
                    playlistService.getPlaylistsByIds(aRequest.getPlaylistIds())
            );
            return ResponseHandler.success(TrackResponse.from(track));
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable Integer id) {
        try {
            return trackService.findById(id)
                    .map(TrackResponse::from)
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
            trackService.delete(id);
            return ResponseHandler.noContent();
        } catch (IllegalArgumentException e) {
            return ResponseHandler.noContent();
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody UpdateTrackRequest aRequest) {
        try {
            trackService.update(
                    id,
                    aRequest.getName(),
                    aRequest.getGenreId(),
                    aRequest.getMediaTypeId(),
                    aRequest.getAlbumId(),
                    aRequest.getComposer(),
                    aRequest.getMilliseconds(),
                    aRequest.getBytes(),
                    aRequest.getUnitPrice(),
                    playlistService.getPlaylistsByIds(aRequest.getPlaylistIds())
            );
            return ResponseHandler.noContent();
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @GetMapping("/{artistId}/{genreId}")
    public ResponseEntity<Object> findAllTracksByArtistGenre(@PathVariable Integer artistId, @PathVariable Integer genreId) {
        try {
            val tracks = trackService.findAllTracksByArtistGenre(artistId, genreId)
                    .stream()
                    .map(TrackResponseParcial::from)
                    .toList();
            return ResponseHandler.success(tracks);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.notFound();
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }
}
