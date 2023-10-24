package utn.frc.backend.parcialbda.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateAlbumRequest {
    @NotBlank(message = "Title is mandatory")
    String title;
    @NotNull(message = "Artist id is mandatory")
    Integer artistId;
}
