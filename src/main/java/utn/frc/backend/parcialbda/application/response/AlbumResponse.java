package utn.frc.backend.parcialbda.application.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import utn.frc.backend.parcialbda.model.Album;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AlbumResponse {
    Integer id;
    String title;
    ArtistResponse artist;

    public static AlbumResponse from(Album album) {
        return AlbumResponse.builder()
                .id(album.getId())
                .title(album.getTitle())
                .artist(ArtistResponse.from(album.getArtist()))
                .build();
    }
}
