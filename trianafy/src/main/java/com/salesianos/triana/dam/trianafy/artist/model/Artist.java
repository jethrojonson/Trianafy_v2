package com.salesianos.triana.dam.trianafy.artist.model;

import com.salesianos.triana.dam.trianafy.album.model.Album;
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
public class Artist {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(
            mappedBy = "authors",
            fetch = FetchType.LAZY
    )
    private List<Song> songs = new ArrayList<>();


    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            mappedBy = "artist"
    )
    private List<Album> albums = new ArrayList<>();

    @PreRemove
    public void setArtistToNull(){
        songs.forEach(song -> song.getAuthors().remove(this));
        albums.forEach(album -> album.setArtist(null));
    }


}
