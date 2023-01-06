package com.salesianos.triana.dam.trianafy.artist.service;

import com.salesianos.triana.dam.trianafy.artist.dto.ArtistDTO;
import com.salesianos.triana.dam.trianafy.artist.error.ArtistExceptions;
import com.salesianos.triana.dam.trianafy.artist.model.Artist;
import com.salesianos.triana.dam.trianafy.artist.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository repository;

    public ArtistDTO add(ArtistDTO a){
        return ArtistDTO.of(repository.save(ArtistDTO.of(a)));
    }

    public List<ArtistDTO> getAll(){
       List<Artist> result= repository.findAll();
       if(result.isEmpty())
           throw new ArtistExceptions.EmptyListException();
       return result.stream().map(ArtistDTO::of).toList();
    }

    public ArtistDTO getById(Long id){
        return ArtistDTO.of(repository.findById(id).orElseThrow(()-> new ArtistExceptions.ArtistNotFoundException(id)));
    }

    public ArtistDTO edit(ArtistDTO a, Long id){
        Artist result = repository.findById(id).orElseThrow(()->new ArtistExceptions.ArtistNotFoundException(id));
        a.setId(id);
        return ArtistDTO.of(repository.save(ArtistDTO.of(a)));
    }

    public void remove(Long id){
        repository.delete(repository.findById(id).orElseThrow(()->new ArtistExceptions.ArtistNotFoundException(id)));
    }

}
