package com.salesianos.triana.dam.trianafy.song.service;

import com.salesianos.triana.dam.trianafy.song.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository repository;

}
