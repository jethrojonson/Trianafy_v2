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

    @JsonView({AlbumViews.SimpleAlbum.class, SongViews.NewSong.class, SongViews.FullSong.class, ArtistViews.FullArtist.class})
    private Long id;

    @JsonView({AlbumViews.NewAlbum.class, AlbumViews.SimpleAlbum.class, SongViews.SimpleSong.class, ArtistViews.FullArtist.class})
    private String title;

    @JsonView({AlbumViews.NewAlbum.class, AlbumViews.SimpleAlbum.class, SongViews.FullSong.class, ArtistViews.FullArtist.class})
    private int year;

    @JsonView(AlbumViews.SimpleAlbum2.class)
    private int songs;

    @JsonView({AlbumViews.NewAlbum.class, AlbumViews.SimpleAlbum.class})
    private ArtistDTO artist;

    @JsonView(AlbumViews.FullAlbum.class)
    private List<SongDTO> tracklist;

    public static AlbumDTO of(Album a) {
        return AlbumDTO.builder()
                .id(a.getId())
                .title(a.getTitle())
                .year(a.getYear())
                .artist(a.getArtist() != null ?
                        ArtistDTO.builder()
                                .id(a.getArtist().getId())
                                .name(a.getArtist().getName())
                                .build() :
                        null
                )
                .songs(a.getTracklist().size())
                .tracklist(a.getTracklist().stream()
                        .map(s-> SongDTO.builder()
                                .id(s.getId())
                                .title(s.getTitle())
                                .build()
                        )
                        .toList()
                )
                .build();
    }

    public static Album of(AlbumDTO a) {
        return Album.builder()
                .id(a.getId())
                .title(a.title)
                .year(a.getYear())
                .artist(a.getArtist() != null ?
                        Artist.builder()
                                .id(a.getArtist().getId())
                                .name(a.getArtist().getName())
                                .build() :
                        null
                )
                .build();
    }

}
