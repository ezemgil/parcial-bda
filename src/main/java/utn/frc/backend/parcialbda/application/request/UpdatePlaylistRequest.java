package utn.frc.backend.parcialbda.application.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdatePlaylistRequest {
    @NotNull(message = "Title is required")
    String title;
    @Nullable
    List<Integer> trackIds;
}
