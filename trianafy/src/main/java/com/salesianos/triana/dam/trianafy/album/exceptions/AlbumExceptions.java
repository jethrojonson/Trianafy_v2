package com.salesianos.triana.dam.trianafy.album.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.Null;

public interface AlbumExceptions {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class AlbumNotFoundException extends RuntimeException{
        public AlbumNotFoundException (Long id) {
            super("Unable to find album with ID: " + id);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class EmptyAlbumListException extends RuntimeException{
        public EmptyAlbumListException () {
            super("Unable to find any album");
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public class AlbumTitleNullException extends RuntimeException{
        public AlbumTitleNullException () {
            super("Album title is null");
        }
    }
}
