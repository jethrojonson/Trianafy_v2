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
                .name("Estopa")
                .build();

        Artist a2 = Artist.builder()
                .name("Violadores del Verso")
                .build();

        Artist a3 = Artist.builder()
                .name("Dogma Crew")
                .build();

        artistRepository.saveAll(List.of(a1, a2, a3));

        Album al1 = Album.builder()
                .title("Voces de Ultrarumba")
                .year(2005)
                .build();

        al1.addArtist(a1);

        Album al2 = Album.builder()
                .title("Vivir para Contarlo")
                .year(2006)
                .build();

        al2.addArtist(a2);

        Album al3 = Album.builder()
                .title("Block Massacre")
                .year(2003)
                .build();

        al3.addArtist(a3);

        albumRepository.saveAll(List.of(al1, al2, al3));

        Song s1 = Song.builder()
                .title("Malabares")
                .release(LocalDate.of(2005, 11, 16))
                .build();

        s1.addArtist(a1);
        s1.addAlbum(al1);

        Song s2 = Song.builder()
                .title("Vacaciones")
                .release(LocalDate.of(2005, 11, 16))
                .build();

        s2.addArtist(a1);
        s2.addAlbum(al1);

        Song s3 = Song.builder()
                .title("Gulere Gulere")
                .release(LocalDate.of(2005, 11, 16))
                .build();

        s3.addArtist(a1);
        s3.addAlbum(al1);

        Song s4 = Song.builder()
                .title("Asómate")
                .release(LocalDate.of(2006, 11, 2))
                .build();

        s4.addArtist(a2);
        s4.addAlbum(al2);

        Song s5 = Song.builder()
                .title("Filosofía y letras")
                .release(LocalDate.of(2006, 11, 2))
                .build();

        s5.addArtist(a2);
        s5.addAlbum(al2);

        Song s6 = Song.builder()
                .title("Ocho lineas")
                .release(LocalDate.of(2006, 11, 2))
                .build();

        s6.addArtist(a2);
        s6.addAlbum(al2);

        Song s7 = Song.builder()
                .title("Chúpala")
                .release(LocalDate.of(2003, 11, 20))
                .build();

        s7.addArtist(a2);
        s7.addArtist(a3);
        s7.addAlbum(al3);

        Song s8 = Song.builder()
                .title("Doble vida")
                .release(LocalDate.of(2003, 11, 20))
                .build();

        s8.addArtist(a3);
        s8.addAlbum(al3);

        Song s9 = Song.builder()
                .title("Terapia extrema")
                .release(LocalDate.of(2003, 11, 20))
                .build();

        s9.addArtist(a3);
        s9.addAlbum(al3);

        songRepository.saveAll(List.of(s1, s2, s3, s4, s5, s6, s7, s8, s9));

        Playlist p1 = Playlist.builder()
                .name("Rumbeo Pal Cuerpo")
                .description("Rumbita española y folclore en general")
                .build();

        p1.addSong(s1);
        p1.addSong(s2);
        p1.addSong(s3);

        Playlist p2 = Playlist.builder()
                .name("Rap'a Grap'a")
                .description("Rap y hip-hop variado")
                .build();

        p2.addSong(s4);
        p2.addSong(s5);
        p2.addSong(s6);
        p2.addSong(s7);
        p2.addSong(s8);
        p2.addSong(s9);

        playlistRepository.saveAll(List.of(p1, p2));

    }
}
