package com.salesianos.triana.dam.trianafy.album.service;

import com.salesianos.triana.dam.trianafy.album.dto.AlbumDTO;
import com.salesianos.triana.dam.trianafy.album.model.Album;
import com.salesianos.triana.dam.trianafy.album.repository.AlbumRepository;
import com.salesianos.triana.dam.trianafy.artist.service.ArtistService;
import com.salesianos.triana.dam.trianafy.playlist.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository repository;

    private final ArtistService arService;

    public AlbumDTO add(AlbumDTO a){
        if(a.getArtist()!=null)
            a.setArtist(arService.getById(a.getArtist().getId()));
        return AlbumDTO.of(repository.save(AlbumDTO.of(a)));
    }

    public List<AlbumDTO> getAll(){
        return repository.findAll().stream().map(AlbumDTO::of).toList();
    }

    public AlbumDTO getById(Long id) {
        return AlbumDTO.of(repository.findById(id).get());
    }

    public AlbumDTO edit(AlbumDTO a, Long id){
        Album result = repository.findById(id).get();
        a.setId(id);
        return AlbumDTO.of(repository.save(AlbumDTO.of(a)));
    }

    public void remove(Long id){
        Optional<Album> result = repository.findById(id);
        repository.delete(result.get());
    }

}
