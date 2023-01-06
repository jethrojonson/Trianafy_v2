package com.salesianos.triana.dam.trianafy.album.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianos.triana.dam.trianafy.album.model.Album;
import com.salesianos.triana.dam.trianafy.album.view.AlbumViews;
import com.salesianos.triana.dam.trianafy.artist.dto.ArtistDTO;
import com.salesianos.triana.dam.trianafy.artist.model.Artist;
import com.salesianos.triana.dam.trianafy.artist.view.ArtistViews;
import com.salesianos.triana.dam.trianafy.song.dto.SongDTO;
import com.salesianos.triana.dam.trianafy.song.model.Song;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AlbumDTO {

    @JsonView({ArtistViews.FullArtist.class})
    private Long id;

    @JsonView({AlbumViews.NewAlbum.class, ArtistViews.FullArtist.class})
    private String title;

    @JsonView({AlbumViews.NewAlbum.class, ArtistViews.FullArtist.class})
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
                .build();
    }

}
