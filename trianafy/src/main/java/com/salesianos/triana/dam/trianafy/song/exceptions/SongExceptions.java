package com.salesianos.triana.dam.trianafy.song.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface SongExceptions {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class SongNotFoundException extends RuntimeException{
        public SongNotFoundException (Long id) {
            super("Unable to find song with ID: " + id);
        }

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class EmptySongListException extends RuntimeException{
        public EmptySongListException (){
            super("Unable to find any song");
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public class SongTitleNullException extends RuntimeException{
        public SongTitleNullException(){
            super("Song title is null");
        }
    }
}
