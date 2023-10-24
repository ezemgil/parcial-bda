package utn.frc.backend.parcialbda.services.interfaces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import utn.frc.backend.parcialbda.model.Album;
import utn.frc.backend.parcialbda.model.Artist;
import utn.frc.backend.parcialbda.repositories.AlbumRepository;
import utn.frc.backend.parcialbda.services.AlbumServiceImpl;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class AlbumServiceTest {
    @InjectMocks
    private AlbumServiceImpl albumService;
    @Mock
    private AlbumRepository albumRepository;
    @Mock
    private ArtistService artistService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);

        when(artistService.findById(anyInt())).thenReturn(Optional.of(new Artist(1, "Artist")));
    }

    @Test
    void testFindAll() {
        List<Album> albums = Arrays.asList(new Album(1, "Album 1", new Artist(1, "Artist")), new Album(2, "Album 2", new Artist(1, "Artist")));
        when(albumRepository.findAll()).thenReturn(albums);

        List<Album> result = albumService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void testUpdate() {
        Album existingAlbum = new Album(1, "Existing Album", new Artist(1, "Artist"));
        when(albumRepository.findById(1)).thenReturn(Optional.of(existingAlbum));

        albumService.update(1, "Updated Album", 1);

        assertEquals("Updated Album", existingAlbum.getTitle());
    }

    @Test
    void testDelete() {
        albumService.delete(1);
        Mockito.verify(albumRepository).deleteById(1);
    }

    @Test
    void testFindById() {
        Album existingAlbum = new Album(1, "Existing Album", new Artist(1, "Artist"));
        when(albumRepository.findById(1)).thenReturn(Optional.of(existingAlbum));

        Optional<Album> result = albumService.findById(1);

        assertEquals("Existing Album", result.orElse(null).getTitle());
    }
}