package utn.frc.backend.parcialbda.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.*;

@Entity
@Table(name = "tracks")
@Getter
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Track {
    public static final String TABLE_NAME = "tracks";
    @Id
    @Column(name = "TrackId")
    Integer id;
    @Column(name = "Name")
    String name;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GenreId")
    Genre genre;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MediaTypeId")
    MediaType mediaType;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AlbumId")
    Album album;
    @Column(name = "Composer")
    String composer;
    @Column(name = "Milliseconds")
    Integer milliseconds;
    @Column(name = "Bytes")
    Integer bytes;
    @Column(name = "UnitPrice")
    Integer unitPrice;
    @ManyToMany(mappedBy = "tracks")
    List<Playlist> playlists;

    public Track(Integer id, String name, Genre genre, MediaType mediaType, Album album, String composer, Integer milliseconds, Integer bytes, Integer unitPrice, List<Playlist> playlists) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.mediaType = mediaType;
        this.album = album;
        this.composer = composer;
        this.milliseconds = milliseconds;
        this.bytes = bytes;
        this.unitPrice = unitPrice;
        this.playlists = playlists;
    }


    public void update(String name, Genre genre, MediaType mediaType, Album album, String composer, Integer milliseconds, Integer bytes, Integer unitPrice, List<Playlist> playlists) {
        this.name = name;
        this.genre = genre;
        this.mediaType = mediaType;
        this.album = album;
        this.composer = composer;
        this.milliseconds = milliseconds;
        this.bytes = bytes;
        this.unitPrice = unitPrice;
        this.playlists = playlists;
    }
}
