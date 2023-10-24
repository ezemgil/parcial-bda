package utn.frc.backend.parcialbda.application.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import utn.frc.backend.parcialbda.model.MediaType;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MediaTypeResponse {
    Integer id;
    String name;

    public static MediaTypeResponse from(MediaType mediaType) {
        return MediaTypeResponse.builder()
                .id(mediaType.getId())
                .name(mediaType.getName())
                .build();
    }
}
