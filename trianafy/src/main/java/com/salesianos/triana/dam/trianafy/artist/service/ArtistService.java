package com.salesianos.triana.dam.trianafy.artist.service;

import com.salesianos.triana.dam.trianafy.artist.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository repository;

}
