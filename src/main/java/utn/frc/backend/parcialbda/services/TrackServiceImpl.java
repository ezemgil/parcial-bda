package utn.frc.backend.parcialbda.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.stereotype.Service;
import utn.frc.backend.parcialbda.model.Playlist;
import utn.frc.backend.parcialbda.model.Track;
import utn.frc.backend.parcialbda.repositories.IdentifierRepository;
import utn.frc.backend.parcialbda.repositories.TrackRepository;
import utn.frc.backend.parcialbda.services.interfaces.AlbumService;
import utn.frc.backend.parcialbda.services.interfaces.GenreService;
import utn.frc.backend.parcialbda.services.interfaces.MediaTypeService;
import utn.frc.backend.parcialbda.services.interfaces.TrackService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TrackServiceImpl implements TrackService {
    TrackRepository trackRepository;
    IdentifierRepository identifierRepository;
    GenreService genreService;
    MediaTypeService mediaTypeService;
    AlbumService albumService;

    @Override
    public List<Track> findAll() {
        return trackRepository.findAll();
    }

    @Override
    public Track create(String name, Integer genreId, Integer mediaTypeId, Integer albumId, String composer, Integer milliseconds, Integer bytes, Integer unitPrice, List<Playlist> playlists) {
        val trackId = identifierRepository.nextValue(Track.TABLE_NAME);
        val genre = genreService.findById(genreId).orElseThrow(() -> new IllegalArgumentException("Genre not found"));
        val mediaType = mediaTypeService.findById(mediaTypeId).orElseThrow(() -> new IllegalArgumentException("Media type not found"));
        val album = albumService.findById(albumId).orElseThrow(() -> new IllegalArgumentException("Album not found"));
        val newTrack = new Track(trackId, name, genre, mediaType, album, composer, milliseconds, bytes, unitPrice, playlists);
        return trackRepository.save(newTrack);
    }

    @Override
    public void update(Integer id, String name, Integer genreId, Integer mediaTypeId, Integer albumId, String composer, Integer milliseconds, Integer bytes, Integer unitPrice, List<Playlist> playlists) {
        val existingTrack = trackRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Track not found"));
        val genre = genreService.findById(genreId).orElseThrow(() -> new IllegalArgumentException("Genre not found"));
        val mediaType = mediaTypeService.findById(mediaTypeId).orElseThrow(() -> new IllegalArgumentException("Media type not found"));
        val album = albumService.findById(albumId).orElseThrow(() -> new IllegalArgumentException("Album not found"));
        existingTrack.update(name, genre, mediaType, album, composer, milliseconds, bytes, unitPrice, playlists);
        trackRepository.save(existingTrack);
    }

    @Override
    public void delete(Integer id) {
        trackRepository.deleteById(id);
    }

    @Override
    public Optional<Track> findById(Integer id) {
        return trackRepository.findById(id);
    }

    @Override
    public List<Track> getTracksByIds(List<Integer> trackIds) {
        return trackIds.stream()
                .map(trackRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
