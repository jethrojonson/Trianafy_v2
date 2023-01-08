package com.salesianos.triana.dam.trianafy.song.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianos.triana.dam.trianafy.artist.dto.ArtistDTO;
import com.salesianos.triana.dam.trianafy.song.dto.SongDTO;
import com.salesianos.triana.dam.trianafy.song.model.Song;
import com.salesianos.triana.dam.trianafy.song.service.SongService;
import com.salesianos.triana.dam.trianafy.song.view.SongViews;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/song")
@RequiredArgsConstructor
@Tag(name = "Song Controller", description = "Controller of the Song operations")
public class SongController {

    private final SongService service;

    @Operation(summary = "Add a new song")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Song added successfully",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = SongDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "id": 1,
                                                 "title": "Song title",
                                                 "release": "01/01/2000",
                                                 "album": {
                                                     "title": "Album title"
                                                 },
                                                 "authors": [
                                                     {
                                                         "name": "Artist name"
                                                     }
                                                 ]
                                             }
                                            """
                            )}
                    )
            ),
            @ApiResponse(responseCode = "400",
                    description = "Incorrect input format",
                    content = @Content
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Input body format (album and artist are optional)",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SongDTO.class),
                    examples = @ExampleObject(
                            value = """
                                    {
                                         "title": "Song title",
                                         "release": "01/01/2000",
                                         "album": {
                                             "id": 1
                                         },
                                         "authors": [
                                             {
                                                 "id": 2
                                             }
                                         ]
                                     }
                                    """
                    )
            )
    )
    @JsonView(SongViews.SimpleSong.class)
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public SongDTO addNewSong(@JsonView(SongViews.NewSong.class) @RequestBody SongDTO song){
        return service.add(song);
    }

    @Operation(summary = "Brings the list of songs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Songs found",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = SongDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                 {
                                                     "id": 1,
                                                     "title": "Song title",
                                                     "release": "01/01/2000",
                                                     "album": {
                                                         "title": "Album title"
                                                     },
                                                     "authors": [
                                                         {
                                                             "name": "1º Artist name"
                                                         },
                                                         {
                                                             "name": "2º Artist name"
                                                         }
                                                     ]
                                                 },
                                                 {
                                                     "id": 2,
                                                     "title": "Song title",
                                                     "release": "01/01/2000",
                                                     "album": {
                                                         "title": "Album title"
                                                     },
                                                     "authors": [
                                                         {
                                                             "name": "Artist name"
                                                         }
                                                     ]
                                                 }
                                             ]
                                            """
                            )}
                    )
            ),
            @ApiResponse(responseCode = "404",
                    description = "Songs not found",
                    content = @Content
            )
    })
    @JsonView(SongViews.SimpleSong.class)
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<SongDTO> getAllSongs(){
        return service.getAll();
    }

    @Operation(summary = "Bring a song with details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Song found",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = SongDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "id": 1,
                                                 "title": "Song title",
                                                 "release": "01/01/2000",
                                                 "album": {
                                                     "id": 2,
                                                     "title": "Album title",
                                                     "year": 2000
                                                 },
                                                 "authors": [
                                                     {
                                                         "id": 3,
                                                         "name": "Artist name"
                                                     }
                                                 ]
                                             }
                                            """
                            )}
                    )
            ),
            @ApiResponse(responseCode = "404",
                    description = "Song not found",
                    content = @Content
            )
    })
    @JsonView(SongViews.FullSong.class)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SongDTO getSongById(@PathVariable Long id){
        return service.getById(id);
    }

    @Operation(summary = "Update a song")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Song updated successfully",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = SongDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "id": 1,
                                                 "title": "Song title",
                                                 "release": "01/01/2000",
                                                 "album": {
                                                     "title": "Album title"
                                                 },
                                                 "authors": [
                                                     {
                                                         "name": "Artist name"
                                                     }
                                                 ]
                                             }
                                            """
                            )}
                    )
            ),
            @ApiResponse(responseCode = "400",
                    description = "Incorrect input format",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404",
                    description = "Song not found",
                    content = @Content
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Input body format (album and artist are optional)",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SongDTO.class),
                    examples = @ExampleObject(
                            value = """
                                    {
                                         "title": "Song title",
                                         "release": "01/01/2000",
                                         "album": {
                                             "id": 1
                                         },
                                         "authors": [
                                             {
                                                 "id": 2
                                             }
                                         ]
                                     }
                                    """
                    )
            )
    )
    @JsonView(SongViews.SimpleSong.class)
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SongDTO updateSong(@PathVariable Long id, @JsonView(SongViews.NewSong.class) @RequestBody SongDTO song){
        return service.edit(song,id);
    }

    @Operation(summary = "Delete a song")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "No content",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404",
                    description = "Song not found",
                    content = @Content
            )
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteASong(@PathVariable Long id){
        service.remove(id);
    }

}
