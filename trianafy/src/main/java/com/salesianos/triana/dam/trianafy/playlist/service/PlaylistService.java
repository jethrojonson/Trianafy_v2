package com.salesianos.triana.dam.trianafy.playlist.service;

import com.salesianos.triana.dam.trianafy.playlist.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository repository;

}