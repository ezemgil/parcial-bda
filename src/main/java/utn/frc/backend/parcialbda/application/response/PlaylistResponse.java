package utn.frc.backend.parcialbda.application.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import utn.frc.backend.parcialbda.model.Playlist;
import utn.frc.backend.parcialbda.model.Track;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlaylistResponse {
    Integer id;
    String name;
    Double duration;
    List<String> tracks;

    //  lista de tracks incluidos ordenados de mayor a menor según UnitPrice
    public static PlaylistResponse from(Playlist playlist) {
        return PlaylistResponse.builder()
                .id(playlist.getId())
                .name(playlist.getName())
                .tracks(playlist.getTracks().stream().map(Track::getName).toList())
                .duration(playlist.getTracks().stream().mapToDouble(Track::getMilliseconds).sum() / 60000)
                .build();
    }

}
