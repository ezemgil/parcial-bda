package utn.frc.backend.parcialbda.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateTrackRequest {
    String name;
    @NotBlank(message = "Genre id is mandatory")
    Integer genreId;
    @NotBlank(message = "Media type id is mandatory")
    Integer mediaTypeId;
    @NotBlank(message = "Album id is mandatory")
    Integer albumId;
    @NotNull(message = "Composer is mandatory")
    String composer;
    @NotNull(message = "Milliseconds are mandatory")
    Integer milliseconds;
    @NotNull(message = "Bytes are mandatory")
    Integer bytes;
    @NotNull(message = "Unit price is mandatory")
    Integer unitPrice;
    List<Integer> playlistIds;
}
