package utn.frc.backend.parcialbda.application.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreatePlaylistRequest {
    @NotNull(message = "Title is required")
    String title;
    @NotNull(message = "Artist name is required")
    Integer artistId;
    @NotNull(message = "Genre Id is required")
    Integer genreId;
    @NotNull(message = "Minutes is required")
    @NotBlank(message = "Minutes is required")
    Double minutes;
}
