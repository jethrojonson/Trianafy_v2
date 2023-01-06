package com.salesianos.triana.dam.trianafy.artist.error;

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
    public class EmptyListException extends RuntimeException{

        public EmptyListException (){ super("Unable to find any artist");}

    }

}
