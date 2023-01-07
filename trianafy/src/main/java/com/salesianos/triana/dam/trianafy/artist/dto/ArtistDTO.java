package com.salesianos.triana.dam.trianafy.artist.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.salesianos.triana.dam.trianafy.album.dto.AlbumDTO;
import com.salesianos.triana.dam.trianafy.album.model.Album;
import com.salesianos.triana.dam.trianafy.album.view.AlbumViews;
import com.salesianos.triana.dam.trianafy.artist.model.Artist;
import com.salesianos.triana.dam.trianafy.artist.view.ArtistViews;
import com.salesianos.triana.dam.trianafy.song.dto.SongDTO;
import com.salesianos.triana.dam.trianafy.song.model.Song;
import com.salesianos.triana.dam.trianafy.song.view.SongViews;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ArtistDTO {


    @JsonView({ArtistViews.SimpleArtist.class, SongViews.NewSong.class, SongViews.FullSong.class, AlbumViews.NewAlbum.class, AlbumViews.FullAlbum.class})
    private Long id;

    @JsonView({ArtistViews.NewArtist.class, SongViews.SimpleSong.class, AlbumViews.SimpleAlbum.class})
    private String name;

    @JsonView(ArtistViews.FullArtist.class)
    private int totalSongs;

    @JsonView(ArtistViews.FullArtist.class)
    private int totalAlbums;

    @JsonView(ArtistViews.FullArtist.class)
    @Builder.Default
    private List<SongDTO> songs = new ArrayList<>();

    @JsonView(ArtistViews.FullArtist.class)
    @Builder.Default
    private List<AlbumDTO> albums = new ArrayList<>();

    public static Artist of(ArtistDTO a){
        return Artist.builder()
                .id(a.getId())
                .name(a.getName())
                .songs(a.getSongs().stream().map(SongDTO::of).toList())
                .albums(a.getAlbums().stream().map(AlbumDTO::of).toList())
                .build();
    }

    public static ArtistDTO of(Artist a){
        return ArtistDTO.builder()
                .id(a.getId())
                .name(a.getName())
                .totalSongs(a.getSongs().size())
                .totalAlbums(a.getAlbums().size())
                .songs(a.getSongs().stream().map(SongDTO::of).toList())
                .albums(a.getAlbums().stream().map(AlbumDTO::of).toList())
                .build();
    }
}
