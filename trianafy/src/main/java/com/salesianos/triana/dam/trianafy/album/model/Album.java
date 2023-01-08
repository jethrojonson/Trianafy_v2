package com.salesianos.triana.dam.trianafy.album.model;

import com.salesianos.triana.dam.trianafy.artist.model.Artist;
import com.salesianos.triana.dam.trianafy.song.model.Song;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Album {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Column(name = "release_year")
    private int year;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "artist_id",
            foreignKey = @ForeignKey(name = "FK_ALBUM_ARTIST")
    )
    private Artist artist;

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            mappedBy = "album",
            fetch = FetchType.LAZY
    )
    private List<Song> tracklist = new ArrayList<>();

    @PreRemove
    public void setAlbumToNull(){
        tracklist.forEach(song->song.setAlbum(null));
        artist.getAlbums().remove(this);
    }

    //******************//
    //* HELPERS ARTIST *//
    //******************//

    public void addArtist(Artist a){
        artist=a;
        a.getAlbums().add(this);
    }

    public void removeArtist(Artist a){
        artist=null;
        a.getAlbums().remove(this);
    }
}
