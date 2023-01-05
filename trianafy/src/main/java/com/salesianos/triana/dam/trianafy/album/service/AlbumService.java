package com.salesianos.triana.dam.trianafy.album.service;

import com.salesianos.triana.dam.trianafy.album.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository repository;
}
