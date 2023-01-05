package com.salesianos.triana.dam.trianafy.artist.repository;

import com.salesianos.triana.dam.trianafy.artist.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
