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
    private Album album;

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "made_by",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private List<Artist> authors = new ArrayList<>();

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
