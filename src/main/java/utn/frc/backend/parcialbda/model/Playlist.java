package utn.frc.backend.parcialbda.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import static lombok.AccessLevel.*;

import java.util.List;

@Entity
@Table(name = "playlists")
@Getter
@FieldDefaults(level = PRIVATE)
@NoArgsConstructor
public class Playlist {
    public static final String TABLE_NAME = "playlists";
    @Id
    @Column(name = "PlaylistId")
    Integer id;
    @Column(name = "Name")
    String name;
    @ManyToMany
    @JoinTable(
            name = "playlist_track",
            joinColumns = @JoinColumn(name = "PlaylistId"),
            inverseJoinColumns = @JoinColumn(name = "TrackId")
    )
    List<Track> tracks;

    public Playlist(Integer id, String name, List<Track> tracks) {
        this.id = id;
        this.name = name;
        this.tracks = tracks;
    }

    public void update(String name, List<Track> tracks){
        this.name = name;
        this.tracks = tracks;
    }
}
