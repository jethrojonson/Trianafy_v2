package com.salesianos.triana.dam.trianafy.song.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.salesianos.triana.dam.trianafy.album.dto.AlbumDTO;
import com.salesianos.triana.dam.trianafy.album.view.AlbumViews;
import com.salesianos.triana.dam.trianafy.artist.dto.ArtistDTO;
import com.salesianos.triana.dam.trianafy.artist.model.Artist;
import com.salesianos.triana.dam.trianafy.artist.view.ArtistViews;
import com.salesianos.triana.dam.trianafy.playlist.view.PlaylistViews;
import com.salesianos.triana.dam.trianafy.song.model.Song;
import com.salesianos.triana.dam.trianafy.song.view.SongViews;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SongDTO {

    @JsonView({
            SongViews.SimpleSong.class, ArtistViews.FullArtist.class,
            AlbumViews.FullAlbum.class, PlaylistViews.FullPlaylist.class
    })
    private Long id;

    @JsonView({
            SongViews.NewSong.class, SongViews.SimpleSong.class,
            ArtistViews.FullArtist.class, AlbumViews.FullAlbum.class,
            PlaylistViews.FullPlaylist.class
    })
    private String title;

    @JsonView({
            SongViews.NewSong.class, SongViews.SimpleSong.class,
            ArtistViews.FullArtist.class, PlaylistViews.FullPlaylist.class
    })
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate release;

    @JsonView(PlaylistViews.FullPlaylist.class)
    private String albumTitle;

    @JsonView({SongViews.NewSong.class, SongViews.SimpleSong.class})
    private AlbumDTO album;

    @JsonView(PlaylistViews.FullPlaylist.class)
    private String artists;

    @JsonView({SongViews.NewSong.class, SongViews.SimpleSong.class})
    @Builder.Default
    private List<ArtistDTO> authors = new ArrayList<>();

    public static SongDTO of(Song s) {
        return SongDTO.builder()
                .id(s.getId())
                .title(s.getTitle())
                .release(s.getRelease())
                .album(s.getAlbum()!=null?AlbumDTO.of(s.getAlbum()):null)
                .albumTitle(s.getAlbum()!=null?s.getAlbum().getTitle():null)
                .authors(s.getAuthors().stream()
                        .map(a -> ArtistDTO.builder()
                                .id(a.getId())
                                .name(a.getName())
                                .build()
                        )
                        .toList())
                .build();
    }

    public static Song of(SongDTO s) {
        return Song.builder()
                .id(s.getId())
                .title(s.getTitle())
                .release(s.getRelease())
                .album(s.getAlbum()!=null?AlbumDTO.of(s.getAlbum()):null)
                .authors(s.getAuthors().stream()
                        .map(a -> Artist.builder()
                                .id(a.getId())
                                .name(a.getName())
                                .build())
                        .toList()
                )
                .build();
    }
}
