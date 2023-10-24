package utn.frc.backend.parcialbda.application.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.backend.parcialbda.application.ResponseHandler;
import utn.frc.backend.parcialbda.application.request.CreateAlbumRequest;
import utn.frc.backend.parcialbda.application.request.UpdateAlbumRequest;
import utn.frc.backend.parcialbda.application.response.AlbumResponse;
import utn.frc.backend.parcialbda.services.interfaces.AlbumService;
import utn.frc.backend.parcialbda.services.interfaces.ArtistService;

@RequestMapping("/api/albums")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AlbumController {
    AlbumService albumService;
    ArtistService artistService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            val albums = albumService.findAll().stream()
                    .map(AlbumResponse::from)
                    .toList();
            return ResponseHandler.success(albums);
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreateAlbumRequest aRequest) {
        try {
            val album = albumService.create(aRequest.getTitle(), aRequest.getArtistId());
            return ResponseHandler.success(AlbumResponse.from(album));
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable Integer id) {
        try {
            return albumService.findById(id)
                    .map(AlbumResponse::from)
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
            albumService.delete(id);
            return ResponseHandler.noContent();
        } catch (IllegalArgumentException e) {
            return ResponseHandler.noContent();
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody UpdateAlbumRequest aRequest) {
        try {
            albumService.update(id, aRequest.getTitle(), aRequest.getArtistId());
            return ResponseHandler.noContent();
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }


}
