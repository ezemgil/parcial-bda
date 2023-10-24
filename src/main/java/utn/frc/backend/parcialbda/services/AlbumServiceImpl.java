package utn.frc.backend.parcialbda.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.stereotype.Service;
import utn.frc.backend.parcialbda.model.Album;
import utn.frc.backend.parcialbda.repositories.AlbumRepository;
import utn.frc.backend.parcialbda.repositories.IdentifierRepository;
import utn.frc.backend.parcialbda.services.interfaces.AlbumService;
import utn.frc.backend.parcialbda.services.interfaces.ArtistService;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {
    AlbumRepository albumRepository;
    IdentifierRepository identifierRepository;
    ArtistService artistService;

    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    @Override
    public Album create(final String title, final Integer artistId) {
        val albumId = identifierRepository.nextValue(Album.TABLE_NAME);
        val artist = artistService.findById(artistId).orElseThrow(() -> new IllegalArgumentException("Artist not found"));
        val newAlbum = new Album(albumId, title, artist);
        return albumRepository.save(newAlbum);
    }

    @Override
    public void update(final Integer id, final String title, final Integer artistId) {
        val existingAlbum = albumRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Album not found"));
        val artist = artistService.findById(artistId).orElseThrow(() -> new IllegalArgumentException("Artist not found"));
        existingAlbum.update(title, artist);
        albumRepository.save(existingAlbum);
    }

    @Override
    public void delete(final Integer id) {
        albumRepository.deleteById(id);
    }

    @Override
    public Optional<Album> findById(final Integer id) {
        return albumRepository.findById(id);
    }
}
