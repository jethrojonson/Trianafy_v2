package com.salesianos.triana.dam.trianafy.playlist.service;

import com.salesianos.triana.dam.trianafy.playlist.repository.PlaylistRepository;
import com.salesianos.triana.dam.trianafy.song.model.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository repository;

    public void songFullRemove(Song song){
        repository.findAll().forEach(p-> p.getSongs().remove(song));
    }

}
