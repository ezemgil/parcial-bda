package utn.frc.backend.parcialbda.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import static lombok.AccessLevel.*;

@Entity
@Table(name = "albums")
@Getter
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Album {
    public static final String TABLE_NAME = "albums";
    @Id
    @Column(name = "AlbumId")
    Integer id;
    @Column(name = "Title")
    String title;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ArtistId")
    Artist artist;

    public Album(Integer id, String title, Artist artist) {
        this.id = id;
        this.title = title;
        this.artist = artist;
    }

    public void update(String title, Artist artist) {
        this.title = title;
        this.artist = artist;
    }
}
