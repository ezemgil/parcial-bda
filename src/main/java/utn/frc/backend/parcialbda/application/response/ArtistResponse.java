package utn.frc.backend.parcialbda.application.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import utn.frc.backend.parcialbda.model.Artist;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArtistResponse {
    Integer id;
    String name;

    public static ArtistResponse from(Artist artist) {
        return ArtistResponse.builder()
                .id(artist.getId())
                .name(artist.getName())
                .build();
    }
}
