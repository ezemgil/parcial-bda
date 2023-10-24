package utn.frc.backend.parcialbda.services.interfaces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import utn.frc.backend.parcialbda.model.*;
import utn.frc.backend.parcialbda.repositories.IdentifierRepository;
import utn.frc.backend.parcialbda.repositories.TrackRepository;
import utn.frc.backend.parcialbda.services.TrackServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TrackServiceTest {
    @InjectMocks
    private TrackServiceImpl trackService;
    @Mock
    private TrackRepository trackRepository;
    @Mock
    private IdentifierRepository identifierRepository;
    @Mock
    private GenreService genreService;
    @Mock
    private MediaTypeService mediaTypeService;
    @Mock
    private AlbumService albumService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        when(identifierRepository.nextValue(Track.TABLE_NAME)).thenReturn(1);
        when(genreService.findById(1)).thenReturn(Optional.of(new Genre(1, "Genre")));
        when(mediaTypeService.findById(1)).thenReturn(Optional.of(new MediaType(1, "Media Type")));
        when(albumService.findById(1)).thenReturn(Optional.of(new Album(1, "Album", null)));

        Track existingTrack = new Track(
                1,
                "Pista existente",
                null,
                null,
                null,
                null,
                0,
                0,
                0,
                Collections.emptyList());
        when(trackRepository.findById(1)).thenReturn(Optional.of(existingTrack));
    }

    @Test
    void testFindAll() {
        List<Track> tracks = Arrays.asList(
                new Track(
                        1,
                        "Pista 1",
                        null,
                        null,
                        null,
                        null,
                        0,
                        0,
                        0,
                        Collections.emptyList()
                ),
                new Track(
                        2,
                        "Pista 2",
                        null,
                        null,
                        null,
                        null,
                        0,
                        0,
                        0,
                        Collections.emptyList())
        );
        when(trackRepository.findAll()).thenReturn(tracks);
        List<Track> result = trackService.findAll();
        assertEquals(2, result.size());
    }

    @Test
    void testUpdate() {
        Track existingTrack = new Track(
                1,
                "Pista existente",
                null,
                null,
                null,
                null,
                0,
                0,
                0,
                Collections.emptyList());
        trackService.update(1,
                "Pista actualizada",
                1,
                1,
                1,
                "Compositor actualizado",
                200,
                2000,
                20,
                Collections.emptyList()
        );
        assertEquals("Pista existente", existingTrack.getName());
    }

    @Test
    void testDelete() {
        trackService.delete(1);
        verify(trackRepository).deleteById(1);
    }

    @Test
    void testFindById() {
        Optional<Track> result = trackService.findById(1);
        assertTrue(result.isPresent());
        assertEquals("Pista existente", result.get().getName());
    }
}