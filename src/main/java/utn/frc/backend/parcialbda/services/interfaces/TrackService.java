package utn.frc.backend.parcialbda.services.interfaces;

import utn.frc.backend.parcialbda.model.Playlist;
import utn.frc.backend.parcialbda.model.Track;

import java.util.List;
import java.util.Optional;

public interface TrackService {
    List<Track> findAll();
    Track create(
            String name, Integer genreId, Integer mediaTypeId, Integer albumId,
            String composer, Integer milliseconds, Integer bytes, Integer unitPrice, List<Playlist> playlists
    );
    void update(
            Integer id, String name, Integer genreId, Integer mediaTypeId, Integer albumId,
            String composer, Integer milliseconds, Integer bytes, Integer unitPrice, List<Playlist> playlists
    );
    void delete(Integer id);
    Optional<Track> findById(Integer id);
    List<Track> getTracksByIds(List<Integer> trackIds);
    List<Track> findAllTracksByArtistGenre(Integer artistId, Integer genreId);
    public List<Track> addTracksToList(Integer artistId, Integer genreId, Double minutes);
}
