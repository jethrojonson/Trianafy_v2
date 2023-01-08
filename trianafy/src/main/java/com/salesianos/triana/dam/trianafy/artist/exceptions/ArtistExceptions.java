package com.salesianos.triana.dam.trianafy.artist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface ArtistExceptions {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class ArtistNotFoundException extends RuntimeException{
        public ArtistNotFoundException (Long id){
            super("Unable to find artist with ID: " + id);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class EmptyArtistListException extends RuntimeException{
        public EmptyArtistListException (){ super("Unable to find any artist");}
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public class ArtistNameNullException extends RuntimeException{
        public ArtistNameNullException(){
            super("Artist name is null");
        }
    }

}
