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
    // @NotEmpty(message = "At least one trackId is required") -> En caso de que deba tener al menos una pista
    @Nullable
    List<Integer> trackIds;
}
