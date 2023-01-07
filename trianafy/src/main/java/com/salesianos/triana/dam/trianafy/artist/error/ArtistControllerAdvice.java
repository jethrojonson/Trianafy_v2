package com.salesianos.triana.dam.trianafy.artist.error;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ArtistControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ArtistExceptions.ArtistNotFoundException.class)
    public ResponseEntity<ArtistError> handleArtistNotFound(ArtistExceptions.ArtistNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
               new ArtistError(HttpStatus.NOT_FOUND,ex.getMessage())
        );
    }

    @ExceptionHandler(ArtistExceptions.EmptyArtistListException.class)
    public ResponseEntity<ArtistError> handleEmptyList(ArtistExceptions.EmptyArtistListException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ArtistError(HttpStatus.NOT_FOUND,ex.getMessage())
        );
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(status).headers(headers).body(new ArtistError(status,ex.getMessage()));


    }

    //    @ExceptionHandler(JsonMappingException.class)
//    public ResponseEntity<ArtistError> handleJsonMappingException(JsonMappingException ex){
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
//                new ArtistError(HttpStatus.BAD_REQUEST,ex.getMessage())
//        );
//    }
}
