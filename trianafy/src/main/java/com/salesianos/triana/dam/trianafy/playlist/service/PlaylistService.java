package com.salesianos.triana.dam.trianafy.playlist.service;

import com.salesianos.triana.dam.trianafy.album.dto.AlbumDTO;
import com.salesianos.triana.dam.trianafy.playlist.dto.PlaylistDTO;
import com.salesianos.triana.dam.trianafy.playlist.exceptions.PlaylistExceptions;
import com.salesianos.triana.dam.trianafy.playlist.model.Playlist;
import com.salesianos.triana.dam.trianafy.playlist.repository.PlaylistRepository;
import com.salesianos.triana.dam.trianafy.song.dto.SongDTO;
import com.salesianos.triana.dam.trianafy.song.exceptions.SongExceptions;
import com.salesianos.triana.dam.trianafy.song.model.Song;
import com.salesianos.triana.dam.trianafy.song.repository.SongRepository;
import com.salesianos.triana.dam.trianafy.song.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository repository;

    private final SongRepository sRepo;

    public PlaylistDTO add(PlaylistDTO p){
        if(p.getName()==null)
            throw new PlaylistExceptions.PlaylistNameNullException();
        return PlaylistDTO.of(repository.save(PlaylistDTO.of(p)));
    }

    public List<PlaylistDTO> getAll(){
        List<Playlist> result = repository.findAll();
        if(result.isEmpty())
            throw new PlaylistExceptions.PlaylistEmptyListException();
        return result.stream().map(PlaylistDTO::of).toList();
    }

    public PlaylistDTO getById(Long id){
        return PlaylistDTO.of(repository.findById(id).orElseThrow(()->new PlaylistExceptions.PlaylistNotFoundException(id)));
    }

    public PlaylistDTO edit(PlaylistDTO p, Long id){
        Playlist result = repository.findById(id).orElseThrow(()->new PlaylistExceptions.PlaylistNotFoundException(id));
        if(p.getName()==null)
            throw new PlaylistExceptions.PlaylistNameNullException();
        result.setName(p.getName());
        result.setDescription(p.getDescription());
        return PlaylistDTO.of(repository.save(result));
    }

    public void remove(Long id){
        repository.delete(repository.findById(id).orElseThrow(()->new PlaylistExceptions.PlaylistNotFoundException(id)));
    }

    //****************//
    //*PLAYLIST SONGS*//
    //****************//

    public void songFullRemove(Song song){
        repository.findAll().forEach(p-> p.getSongs().remove(song));
    }

    public PlaylistDTO addSongToPlaylist(Long listId, Long songId){
        Playlist p = repository.findById(listId).orElseThrow(()->new PlaylistExceptions.PlaylistNotFoundException(listId));
        Song s = sRepo.findById(songId).orElseThrow(()->new SongExceptions.SongNotFoundException(songId));
        p.getSongs().add(s);
        return PlaylistDTO.of(repository.save(p));
    }

    public SongDTO getSongInPlaylist (Long listId, Long songId){
        Playlist p = repository.findById(listId).orElseThrow(()->new PlaylistExceptions.PlaylistNotFoundException(listId));
        Song s = sRepo.findById(songId).orElseThrow(()->new SongExceptions.SongNotFoundException(songId));
        if(!p.getSongs().contains(s))
            throw new PlaylistExceptions.SongNotInPlaylistException();
        return SongDTO.of(s);
    }

    public void removeSongFromPlaylist(Long listId, Long songId){
        Playlist p = repository.findById(listId).orElseThrow(()->new PlaylistExceptions.PlaylistNotFoundException(listId));
        Song s = sRepo.findById(songId).orElseThrow(()->new SongExceptions.SongNotFoundException(songId));
        if(!p.getSongs().contains(s))
            throw new PlaylistExceptions.SongNotInPlaylistException();
        p.getSongs().remove(s);
        repository.save(p);
    }

}
