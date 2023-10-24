package utn.frc.backend.parcialbda.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import static lombok.AccessLevel.*;

@Entity
@Table(name = "artists")
@FieldDefaults(level = PRIVATE)
@Getter
@NoArgsConstructor
public class Artist {
    public static final String TABLE_NAME = "artists";
    @Id
    @Column(name = "ArtistId")
    Integer id;
    @Column(name = "Name")
    String name;

    public Artist(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public void update(String name) {
        this.name = name;
    }
}
