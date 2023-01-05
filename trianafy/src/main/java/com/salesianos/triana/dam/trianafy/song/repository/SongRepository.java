package com.salesianos.triana.dam.trianafy.song.repository;

import com.salesianos.triana.dam.trianafy.song.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
