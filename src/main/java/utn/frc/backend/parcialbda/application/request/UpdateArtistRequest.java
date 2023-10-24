package utn.frc.backend.parcialbda.application.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateArtistRequest {
    @NotNull(message = "Name is required")
    String name;
}
