package com.salesianos.triana.dam.trianafy.playlist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface PlaylistExceptions {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class PlaylistNotFoundException extends RuntimeException{
        public PlaylistNotFoundException(Long id){
            super("Unable to find playlist with ID: " + id);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class PlaylistEmptyListException extends RuntimeException{
        public PlaylistEmptyListException(){
            super("Unable to find any playlist");
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public class PlaylistNameNullException extends RuntimeException{
        public PlaylistNameNullException(){
            super("Playlist name is null");
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class SongNotInPlaylistException extends RuntimeException{
        public SongNotInPlaylistException(){
            super("The song exists but does not belong to the playlist");
        }
    }

}
