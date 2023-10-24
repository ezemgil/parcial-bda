package utn.frc.backend.parcialbda.services.interfaces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import utn.frc.backend.parcialbda.model.Artist;
import utn.frc.backend.parcialbda.repositories.ArtistRepository;
import utn.frc.backend.parcialbda.services.ArtistServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ArtistServiceTest {
    @InjectMocks
    private ArtistServiceImpl artistService;
    @Mock
    private ArtistRepository artistRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAll() {
        List<Artist> artists = Arrays.asList(new Artist(1, "Artist 1"), new Artist(2, "Artist 2"));
        when(artistRepository.findAll()).thenReturn(artists);

        List<Artist> result = artistService.findAll();

        assertEquals(2, result.size());
    }


    @Test
    void testUpdate() {
        Artist existingArtist = new Artist(1, "Existing Artist");
        when(artistRepository.findById(1)).thenReturn(java.util.Optional.of(existingArtist));

        artistService.update(1, "Updated Artist");

        assertEquals("Updated Artist", existingArtist.getName());
    }

    @Test
    void testDelete() {
        artistService.delete(1);
        Mockito.verify(artistRepository).deleteById(1);
    }

    @Test
    void testFindById() {
        Artist existingArtist = new Artist(1, "Existing Artist");
        when(artistRepository.findById(1)).thenReturn(java.util.Optional.of(existingArtist));

        Artist result = artistService.findById(1).orElse(null);

        assertEquals("Existing Artist", result.getName());
    }
}