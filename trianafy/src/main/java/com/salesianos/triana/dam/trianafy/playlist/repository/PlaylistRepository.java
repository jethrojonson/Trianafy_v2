package com.salesianos.triana.dam.trianafy.playlist.repository;

import com.salesianos.triana.dam.trianafy.playlist.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository <Playlist, Long> {
}
