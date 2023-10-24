package utn.frc.backend.parcialbda.application.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import utn.frc.backend.parcialbda.model.Playlist;
import utn.frc.backend.parcialbda.model.Track;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrackResponse {
    Integer id;
    String name;
    GenreResponse genre;
    MediaTypeResponse mediaType;
    AlbumResponse album;
    String composer;
    Integer milliseconds;
    Integer bytes;
    Integer unitPrice;
    List<String> playlists;

    public static TrackResponse from(Track track) {
        return TrackResponse.builder()
                .id(track.getId())
                .name(track.getName())
                .genre(GenreResponse.from(track.getGenre()))
                .mediaType(MediaTypeResponse.from(track.getMediaType()))
                .album(AlbumResponse.from(track.getAlbum()))
                .composer(track.getComposer())
                .milliseconds(track.getMilliseconds())
                .bytes(track.getBytes())
                .unitPrice(track.getUnitPrice())
                .playlists(track.getPlaylists().stream().map(Playlist::getName).toList())
                .build();
    }
}
