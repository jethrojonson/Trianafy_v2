package com.salesianos.triana.dam.trianafy;

import com.salesianos.triana.dam.trianafy.album.model.Album;
import com.salesianos.triana.dam.trianafy.album.repository.AlbumRepository;
import com.salesianos.triana.dam.trianafy.artist.model.Artist;
import com.salesianos.triana.dam.trianafy.artist.repository.ArtistRepository;
import com.salesianos.triana.dam.trianafy.playlist.model.Playlist;
import com.salesianos.triana.dam.trianafy.playlist.repository.PlaylistRepository;
import com.salesianos.triana.dam.trianafy.song.model.Song;
import com.salesianos.triana.dam.trianafy.song.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitData {

    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;
    private final PlaylistRepository playlistRepository;
    private final AlbumRepository albumRepository;

    @PostConstruct
    public void run() {

        Artist a1 = Artist.builder()
                .name("El niño de la hipoteca")
                .build();

        Artist a2 = Artist.builder()
                .name("El Kanka")
                .build();

        artistRepository.saveAll(List.of(a1, a2));

        Album al1 = Album.builder()
                .title("Cancioncicas hipotecadas")
                .year(2014)
                .build();

        al1.addArtist(a1);

        albumRepository.saveAll(List.of(al1));

        Song s1 = Song.builder()
                .title("Canela en Rama")
                .release(LocalDate.of(2014, 2, 14))
                .build();

        Song s2 = Song.builder()
                .title("La más bella")
                .release(LocalDate.of(2012, 4, 27))
                .build();

        s1.addAlbum(al1);
        s1.addArtist(a1);
        s1.addArtist(a2);
        s2.addArtist(a1);
        s2.addAlbum(al1);

        songRepository.saveAll(List.of(s1,s2));

        Playlist p1 = Playlist.builder()
                .name("Música Garrapatera")
                .description("Canciones de cantautores españoles")
                .build();

        p1.addSong(s1);

        playlistRepository.saveAll(List.of(p1));

    }
}
