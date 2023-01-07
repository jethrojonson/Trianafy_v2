package com.salesianos.triana.dam.trianafy.album.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianos.triana.dam.trianafy.album.model.Album;
import com.salesianos.triana.dam.trianafy.album.view.AlbumViews;
import com.salesianos.triana.dam.trianafy.artist.dto.ArtistDTO;
import com.salesianos.triana.dam.trianafy.artist.model.Artist;
import com.salesianos.triana.dam.trianafy.artist.view.ArtistViews;
import com.salesianos.triana.dam.trianafy.song.dto.SongDTO;
import com.salesianos.triana.dam.trianafy.song.model.Song;
import com.salesianos.triana.dam.trianafy.song.view.SongViews;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AlbumDTO {

    @JsonView({SongViews.NewSong.class, SongViews.FullSong.class, ArtistViews.FullArtist.class})
    private Long id;

    @JsonView({SongViews.SimpleSong.class, ArtistViews.FullArtist.class})
    private String title;

    @JsonView({SongViews.FullSong.class, ArtistViews.FullArtist.class})
    private int year;

    private ArtistDTO artist;
    private List<SongDTO> tracklist;

    public static AlbumDTO of (Album a){
        return AlbumDTO.builder()
                .id(a.getId())
                .title(a.getTitle())
                .year(a.getYear())
                .build();
    }

    public static Album of (AlbumDTO a){
        return Album.builder()
                .id(a.getId())
                .title(a.title)
                .year(a.getYear())
                .build();
    }

}
