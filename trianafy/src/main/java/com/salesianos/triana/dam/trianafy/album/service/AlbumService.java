package com.salesianos.triana.dam.trianafy.album.service;

import com.salesianos.triana.dam.trianafy.album.dto.AlbumDTO;
import com.salesianos.triana.dam.trianafy.album.repository.AlbumRepository;
import com.salesianos.triana.dam.trianafy.playlist.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository repository;
    private final PlaylistRepository playlistRepository;

    public AlbumDTO getById(Long id){
        return AlbumDTO.of(repository.findById(id).get());
    }
}
