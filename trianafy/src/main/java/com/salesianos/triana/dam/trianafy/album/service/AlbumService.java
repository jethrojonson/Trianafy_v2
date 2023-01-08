package com.salesianos.triana.dam.trianafy.album.service;

import com.salesianos.triana.dam.trianafy.album.dto.AlbumDTO;
import com.salesianos.triana.dam.trianafy.album.exceptions.AlbumExceptions;
import com.salesianos.triana.dam.trianafy.album.model.Album;
import com.salesianos.triana.dam.trianafy.album.repository.AlbumRepository;
import com.salesianos.triana.dam.trianafy.artist.dto.ArtistDTO;
import com.salesianos.triana.dam.trianafy.artist.model.Artist;
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

    public AlbumDTO add(AlbumDTO a) {
        if(a.getTitle()==null)
            throw new AlbumExceptions.AlbumTitleNullException();
        if (a.getArtist() != null)
            a.setArtist(arService.getById(a.getArtist().getId()));
        return AlbumDTO.of(repository.save(AlbumDTO.of(a)));
    }

    public List<AlbumDTO> getAll() {
        List<Album> result = repository.findAll();
        if (result.isEmpty())
            throw new AlbumExceptions.EmptyAlbumListException();
        return repository.findAll().stream().map(AlbumDTO::of).toList();
    }

    public AlbumDTO getById(Long id) {
        return AlbumDTO.of(repository.findById(id).orElseThrow(()->new AlbumExceptions.AlbumNotFoundException(id)));
    }

    public AlbumDTO edit(AlbumDTO a, Long id) {
        Album result = repository.findById(id).orElseThrow(()->new AlbumExceptions.AlbumNotFoundException(id));
        ArtistDTO art = arService.getById(a.getArtist().getId());
        if(a.getTitle()==null)
            throw new AlbumExceptions.AlbumTitleNullException();
        result.setTitle(a.getTitle());
        result.setYear(a.getYear());
        result.setArtist(ArtistDTO.of(art));
        return AlbumDTO.of(repository.save(result));
    }

    public void remove(Long id) {
        Album result = repository.findById(id).orElseThrow(()->new AlbumExceptions.AlbumNotFoundException(id));
        repository.delete(result);
    }

}
