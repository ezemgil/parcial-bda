package utn.frc.backend.parcialbda.services.interfaces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import utn.frc.backend.parcialbda.model.Playlist;
import utn.frc.backend.parcialbda.repositories.PlaylistRepository;
import utn.frc.backend.parcialbda.services.PlaylistServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PlaylistServiceTest {
    @InjectMocks
    private PlaylistServiceImpl playlistService;
    @Mock
    private PlaylistRepository playlistRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAll() {
        List<Playlist> playlists = Arrays.asList(new Playlist(1, "Playlist 1", null), new Playlist(2, "Playlist 2", null));
        when(playlistRepository.findAll()).thenReturn(playlists);

        List<Playlist> result = playlistService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void testUpdate() {
        Playlist existingPlaylist = new Playlist(1, "Existing Playlist", null);
        when(playlistRepository.findById(1)).thenReturn(java.util.Optional.of(existingPlaylist));

        playlistService.update(1, "Updated Playlist", null);

        assertEquals("Updated Playlist", existingPlaylist.getName());
    }

    @Test
    void testDelete() {
        playlistService.delete(1);
        Mockito.verify(playlistRepository).deleteById(1);
    }

    @Test
    void testFindById() {
        Playlist existingPlaylist = new Playlist(1, "Existing Playlist", null);
        when(playlistRepository.findById(1)).thenReturn(java.util.Optional.of(existingPlaylist));

        Playlist result = playlistService.findById(1).orElse(null);

        assertEquals("Existing Playlist", result.getName());
    }
}