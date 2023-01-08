package com.salesianos.triana.dam.trianafy.song.model;


import com.salesianos.triana.dam.trianafy.album.model.Album;
import com.salesianos.triana.dam.trianafy.artist.model.Artist;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
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
public class Song {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private LocalDate release;

    @ManyToOne
    @JoinColumn(
            name = "album_id",
            foreignKey = @ForeignKey(name = "FK_SONG_ALBUM")
    )
    private Album album;

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "made_by",
            joinColumns = @JoinColumn(
                    name = "song_id",
                    foreignKey = @ForeignKey(name = "FK_MADE_SONG")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "artist_id",
                    foreignKey = @ForeignKey(name = "FK_MADE_ARTIST")
            )
    )
    private List<Artist> authors = new ArrayList<>();

    @PreRemove
    public void setSongToNull(){
        authors.forEach(artist -> artist.getSongs().remove(this));
        album.getTracklist().remove(this);
    }

    //******************//
    //* HELPERS ARTIST *//
    //******************//

    public void addArtist(Artist a){
        authors.add(a);
        a.getSongs().add(this);
    }

    public void removeArtist(Artist a){
        authors.remove(a);
        a.getSongs().remove(this);
    }

    //*****************//
    //* HELPERS ALBUM *//
    //*****************//

    public void addAlbum(Album a){
        album=a;
        a.getTracklist().add(this);
    }

    public void removeAlbum(Album a){
        album=null;
        a.getTracklist().remove(this);
    }
}
