package com.salesianos.triana.dam.trianafy.playlist.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianos.triana.dam.trianafy.playlist.model.Playlist;
import com.salesianos.triana.dam.trianafy.playlist.view.PlaylistViews;
import com.salesianos.triana.dam.trianafy.song.dto.SongDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PlaylistDTO {

    @JsonView({PlaylistViews.SimplePlaylist.class, PlaylistViews.SimplePlaylist2.class})
    private Long id;

    @JsonView({PlaylistViews.NewPlaylist.class, PlaylistViews.SimplePlaylist2.class})
    private String name;

    @JsonView(PlaylistViews.NewPlaylist.class)
    private String description;

    @JsonView(PlaylistViews.SimplePlaylist2.class)
    private int numberOfSongs;

    @JsonView(PlaylistViews.FullPlaylist.class)
    @Builder.Default
    private List<SongDTO> songs = new ArrayList<>();

    public static PlaylistDTO of(Playlist p){
//        Song song = new Song();
//        String artists = "";
//
//
//        song.getAuthors().forEach(a-> artists.join(", ",a.getName()));




        return PlaylistDTO.builder()
                .id(p.getId())
                .name(p.getName())
                .description(p.getDescription())
                .numberOfSongs(p.getSongs().size())
                .songs(p.getSongs().stream()
                        .map(s->SongDTO.builder()
                                .id(s.getId())
                                .title(s.getTitle())
                                .artists(
                                        String.join(", ",s.getAuthors().stream().map(a->a.getName()).toList())
                                )
                                .release(s.getRelease())
                                .albumTitle(s.getAlbum()!=null?s.getAlbum().getTitle():null)
                                .build()
                        )
                        .toList())
                .build();
    }

    public static Playlist of(PlaylistDTO p){
        return Playlist.builder()
                .id(p.getId())
                .name(p.getName())
                .description(p.getDescription())
                .build();
    }


}
