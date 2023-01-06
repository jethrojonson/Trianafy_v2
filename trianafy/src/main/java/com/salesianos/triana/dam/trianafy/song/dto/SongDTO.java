package com.salesianos.triana.dam.trianafy.song.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.salesianos.triana.dam.trianafy.album.dto.AlbumDTO;
import com.salesianos.triana.dam.trianafy.artist.dto.ArtistDTO;
import com.salesianos.triana.dam.trianafy.artist.view.ArtistViews;
import com.salesianos.triana.dam.trianafy.song.model.Song;
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

    @JsonView({ArtistViews.FullArtist.class})
    private Long id;

    @JsonView({ArtistViews.FullArtist.class})
    private String title;

    @JsonView({ArtistViews.FullArtist.class})
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate release;

    private AlbumDTO album;

    @Builder.Default
    private List<ArtistDTO> authors = new ArrayList<>();

    public static SongDTO of (Song s){
        return SongDTO.builder()
                .id(s.getId())
                .title(s.getTitle())
                .release(s.getRelease())
                .build();
    }

    public static Song of (SongDTO s){
        return Song.builder()
                .build();
    }
}
