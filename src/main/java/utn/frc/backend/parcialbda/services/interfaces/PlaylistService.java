package utn.frc.backend.parcialbda.services.interfaces;

import utn.frc.backend.parcialbda.model.Playlist;
import utn.frc.backend.parcialbda.model.Track;

import java.util.List;
import java.util.Optional;

public interface PlaylistService {
    List<Playlist> findAll();
    Playlist create(String name, List<Track> tracks);
    void update(Integer id, String name, List<Track> tracks);
    void delete(Integer id);
    Optional<Playlist> findById(Integer id);
    List<Playlist> getPlaylistsByIds(List<Integer> playlistIds);
    void addTrackToPlaylist(Integer playlistId, Integer trackId);
    void removeTrackFromPlaylist(Integer playlistId, Integer trackId);
}
