package com.salesianos.triana.dam.trianafy.playlist.model;

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
public class Playlist {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "listed",
            joinColumns = @JoinColumn(
                    name = "playlist_id",
                    foreignKey = @ForeignKey(name = "FK_LISTED_PLAYLIST")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "song_id",
                    foreignKey = @ForeignKey(name = "FK_LISTED_SONG")
            )
    )
    private List<Song> songs = new ArrayList<>();

    //*****************//
    //* HELPERS SONGS *//
    //*****************//

    public void addSong(Song s){
        songs.add(s);
    }

    public void deleteSong(Song s){
        songs.remove(s);
    }
}
