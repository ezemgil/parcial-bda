package utn.frc.backend.parcialbda.services.interfaces;

import utn.frc.backend.parcialbda.model.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    List<Album> findAll();
    Album create(String title, Integer artistId);
    void update(Integer id, String title, Integer artistId);
    void delete(Integer id);
    Optional<Album> findById(Integer id);
}
