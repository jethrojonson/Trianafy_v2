package com.salesianos.triana.dam.trianafy.error;

import com.salesianos.triana.dam.trianafy.album.exceptions.AlbumExceptions;
import com.salesianos.triana.dam.trianafy.artist.exceptions.ArtistExceptions;
import com.salesianos.triana.dam.trianafy.playlist.exceptions.PlaylistExceptions;
import com.salesianos.triana.dam.trianafy.song.exceptions.SongExceptions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiControlAdvice extends ResponseEntityExceptionHandler {

    //*********************//
    //* ARTIST EXCEPTIONS *//
    //*********************//

    @ExceptionHandler(ArtistExceptions.ArtistNotFoundException.class)
    public ResponseEntity<ApiError> handleArtistNotFound(ArtistExceptions.ArtistNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(ArtistExceptions.EmptyArtistListException.class)
    public ResponseEntity<ApiError> handleArtistEmptyList(ArtistExceptions.EmptyArtistListException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(ArtistExceptions.ArtistNameNullException.class)
    public ResponseEntity<ApiError> handleArtistNameNull(ArtistExceptions.ArtistNameNullException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError(HttpStatus.BAD_REQUEST,ex.getMessage()));
    }

    //********************//
    //* ALBUM EXCEPTIONS *//
    //********************//

    @ExceptionHandler(AlbumExceptions.AlbumNotFoundException.class)
    public ResponseEntity<ApiError> handleAlbumNotFound(AlbumExceptions.AlbumNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(AlbumExceptions.EmptyAlbumListException.class)
    public ResponseEntity<ApiError> handleAlbumEmptyList(AlbumExceptions.EmptyAlbumListException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(AlbumExceptions.AlbumTitleNullException.class)
    public ResponseEntity<ApiError> handleAlbumTitleNull(AlbumExceptions.AlbumTitleNullException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError(HttpStatus.BAD_REQUEST,ex.getMessage()));
    }

    //*******************//
    //* SONG EXCEPTIONS *//
    //*******************//

    @ExceptionHandler(SongExceptions.SongNotFoundException.class)
    public ResponseEntity<ApiError> handleSongNotFound(SongExceptions.SongNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(SongExceptions.EmptySongListException.class)
    public ResponseEntity<ApiError> handleSongEmptyList(SongExceptions.EmptySongListException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(SongExceptions.SongTitleNullException.class)
    public ResponseEntity<ApiError> handleSongTitleNull(SongExceptions.SongTitleNullException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError(HttpStatus.BAD_REQUEST,ex.getMessage()));
    }

    //***********************//
    //* PLAYLIST EXCEPTIONS *//
    //***********************//

    @ExceptionHandler(PlaylistExceptions.PlaylistNotFoundException.class)
    public ResponseEntity<ApiError> handlePlaylistNotFound(PlaylistExceptions.PlaylistNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(PlaylistExceptions.PlaylistEmptyListException.class)
    public ResponseEntity<ApiError> handlePlaylistEmptyList(PlaylistExceptions.PlaylistEmptyListException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(PlaylistExceptions.PlaylistNameNullException.class)
    public ResponseEntity<ApiError> handlePlaylistNameNull(PlaylistExceptions.PlaylistNameNullException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError(HttpStatus.BAD_REQUEST,ex.getMessage()));
    }

    @ExceptionHandler(PlaylistExceptions.SongNotInPlaylistException.class)
    public ResponseEntity<ApiError> handleSongNotInPlaylist(PlaylistExceptions.SongNotInPlaylistException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(HttpStatus.NOT_FOUND,ex.getMessage()));
    }

    //---------------------------//
    //-- JSON MAPPING EXCEPTION--//
    //---------------------------//

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(status).headers(headers).body(new ApiError(status, ex.getMessage()));
    }
}
