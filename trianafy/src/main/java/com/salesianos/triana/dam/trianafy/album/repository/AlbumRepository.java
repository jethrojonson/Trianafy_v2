package com.salesianos.triana.dam.trianafy.album.repository;

import com.salesianos.triana.dam.trianafy.album.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
