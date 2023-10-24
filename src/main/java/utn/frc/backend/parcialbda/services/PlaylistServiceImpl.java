package utn.frc.backend.parcialbda.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.stereotype.Service;
import utn.frc.backend.parcialbda.model.Playlist;
import utn.frc.backend.parcialbda.model.Track;
import utn.frc.backend.parcialbda.repositories.IdentifierRepository;
import utn.frc.backend.parcialbda.repositories.PlaylistRepository;
import utn.frc.backend.parcialbda.repositories.TrackRepository;
import utn.frc.backend.parcialbda.services.interfaces.PlaylistService;
import utn.frc.backend.parcialbda.services.interfaces.TrackService;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {
    PlaylistRepository playlistRepository;
    IdentifierRepository identifierRepository;
    TrackRepository trackRepository;

    @Override
    public List<Playlist> findAll() {
        return playlistRepository.findAll();
    }

    @Override
    public Playlist create(String name, List<Track> tracks) {
        val playlistId = identifierRepository.nextValue(Playlist.TABLE_NAME);
        val newPlaylist = new Playlist(playlistId, name, tracks);
        return playlistRepository.save(newPlaylist);
    }



    @Override
    public void update(Integer id, String name, List<Track> tracks) {
        val existingPlaylist = playlistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Playlist not found"));
        existingPlaylist.update(name, tracks);
        playlistRepository.save(existingPlaylist);
    }

    @Override
    public void delete(Integer id) {
        playlistRepository.deleteById(id);
    }

    @Override
    public Optional<Playlist> findById(Integer id) {
        return playlistRepository.findById(id);
    }

    @Override
    public List<Playlist> getPlaylistsByIds(List<Integer> playlistIds) {
        return playlistIds.stream()
                .map(this::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    @Override
    public void addTrackToPlaylist(Integer playlistId, Integer trackId) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new IllegalArgumentException("Playlist not found"));
        Track track = trackRepository.findById(trackId)
                .orElseThrow(() -> new IllegalArgumentException("Track not found"));

        if (!playlist.getTracks().contains(track)) {
            playlist.getTracks().add(track);
            playlistRepository.save(playlist);
        }
    }

    @Override
    public void removeTrackFromPlaylist(Integer playlistId, Integer trackId) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new IllegalArgumentException("Playlist not found"));
        Track track = trackRepository.findById(trackId)
                .orElseThrow(() -> new IllegalArgumentException("Track not found"));

        if (playlist.getTracks().contains(track)) {
            playlist.getTracks().remove(track);
            playlistRepository.save(playlist);
        }
    }
}
