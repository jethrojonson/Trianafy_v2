package com.salesianos.triana.dam.trianafy.playlist.service;

import com.salesianos.triana.dam.trianafy.album.dto.AlbumDTO;
import com.salesianos.triana.dam.trianafy.playlist.dto.PlaylistDTO;
import com.salesianos.triana.dam.trianafy.playlist.model.Playlist;
import com.salesianos.triana.dam.trianafy.playlist.repository.PlaylistRepository;
import com.salesianos.triana.dam.trianafy.song.dto.SongDTO;
import com.salesianos.triana.dam.trianafy.song.model.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository repository;

    public PlaylistDTO add(PlaylistDTO playlist){
        return PlaylistDTO.of(repository.save(PlaylistDTO.of(playlist)));
    }

    public List<PlaylistDTO> getAll(){
        return repository.findAll().stream().map(PlaylistDTO::of).toList();
    }

    public PlaylistDTO getById(Long id){
        return PlaylistDTO.of(repository.findById(id).get());
    }

    public PlaylistDTO edit(PlaylistDTO p, Long id){
        Playlist result = repository.findById(id).get();
        result.setName(p.getName());
        result.setDescription(p.getDescription());
        return PlaylistDTO.of(repository.save(result));
    }

    public void remove(Long id){
        repository.delete(repository.findById(id).get());
    }

    //Song-Playlist
    public void songFullRemove(Song song){
        repository.findAll().forEach(p-> p.getSongs().remove(song));
    }

}
