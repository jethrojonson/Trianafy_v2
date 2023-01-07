package com.salesianos.triana.dam.trianafy.song.service;

import com.salesianos.triana.dam.trianafy.album.repository.AlbumRepository;
import com.salesianos.triana.dam.trianafy.album.service.AlbumService;
import com.salesianos.triana.dam.trianafy.artist.dto.ArtistDTO;
import com.salesianos.triana.dam.trianafy.artist.service.ArtistService;
import com.salesianos.triana.dam.trianafy.playlist.service.PlaylistService;
import com.salesianos.triana.dam.trianafy.song.dto.SongDTO;
import com.salesianos.triana.dam.trianafy.song.model.Song;
import com.salesianos.triana.dam.trianafy.song.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository repository;
    private final AlbumService alService;
    private final ArtistService arService;

    private final PlaylistService plService;

    public SongDTO add(SongDTO s){
        if(!s.getAuthors().isEmpty())
            s.getAuthors().forEach(a -> {
                ArtistDTO ar = arService.getById(a.getId());
                a.setName(ar.getName());
            });
        if(s.getAlbum()!=null)
            s.setAlbum(alService.getById(s.getAlbum().getId()));
        return SongDTO.of(repository.save(SongDTO.of(s)));
    }

    public List<SongDTO> getAll(){
        return repository.findAll().stream().map(SongDTO::of).toList();
    }

    public SongDTO getById(Long id){
        return SongDTO.of(repository.findById(id).get());
    }

    public SongDTO edit(SongDTO s, Long id){
        Song result = repository.findById(id).get();
        s.setId(id);
        return SongDTO.of(repository.save(SongDTO.of(s)));
    }

    public void remove(Long id){
        Optional<Song> result = repository.findById(id);
        plService.songFullRemove(result.get());
        repository.delete(result.get());

    }

}
