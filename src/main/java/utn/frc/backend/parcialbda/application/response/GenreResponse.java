package utn.frc.backend.parcialbda.application.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import utn.frc.backend.parcialbda.model.Genre;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GenreResponse {
    Integer id;
    String name;

    public static GenreResponse from(Genre genre){
        return GenreResponse.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }
}
