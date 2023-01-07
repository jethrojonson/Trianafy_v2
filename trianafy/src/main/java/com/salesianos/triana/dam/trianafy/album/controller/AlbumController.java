package com.salesianos.triana.dam.trianafy.album.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianos.triana.dam.trianafy.album.dto.AlbumDTO;
import com.salesianos.triana.dam.trianafy.album.service.AlbumService;
import com.salesianos.triana.dam.trianafy.album.view.AlbumViews;
import com.salesianos.triana.dam.trianafy.artist.dto.ArtistDTO;
import com.salesianos.triana.dam.trianafy.song.dto.SongDTO;
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
@RequestMapping("/album")
@RequiredArgsConstructor
@Tag(name = "Album Controller", description = "Controller of the Album operations")
public class AlbumController {

    private final AlbumService service;

    @Operation(summary = "Add a new album")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Album added successfully",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AlbumDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "id": 1,
                                                 "title": "Album title",
                                                 "year": 2000,
                                                 "artist": {
                                                     "name": "Artist name"
                                                 }
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
            description = "Input body format",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AlbumDTO.class),
                    examples = @ExampleObject(
                            value = """
                                    {
                                         "title" : "Album title",
                                         "year" : 2000,
                                         "artist" : {
                                             "id" : 1
                                         }
                                     }
                                    """
                    )
            )
    )
    @JsonView(AlbumViews.SimpleAlbum.class)
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumDTO addNewAlbum(@JsonView(AlbumViews.NewAlbum.class) @RequestBody AlbumDTO album) {
        return service.add(album);
    }

    @Operation(summary = "Brings the list of albums")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Albums found",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AlbumDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                  {
                                                      "id": 1,
                                                      "title": "Album title",
                                                      "year": 2000,
                                                      "songs": 10,
                                                      "artist": {
                                                          "name": "Artist name"
                                                      }
                                                  },
                                                  {
                                                      "id": 2,
                                                      "title": "Album title",
                                                      "year": 2000,
                                                      "songs": 12,
                                                      "artist": {
                                                          "name": "Artist name"
                                                      }
                                                  }
                                              ]
                                            """
                            )}
                    )
            ),
            @ApiResponse(responseCode = "404",
                    description = "Albums not found",
                    content = @Content
            )
    })
    @JsonView(AlbumViews.SimpleAlbum2.class)
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<AlbumDTO> getAllAlbums() {
        return service.getAll();
    }

    @Operation(summary = "Bring an album with details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Album found",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AlbumDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "id": 1,
                                                 "title": "Album title",
                                                 "year": 2000,
                                                 "songs": 2,
                                                 "artist": {
                                                     "id": 2,
                                                     "name": "Artist name"
                                                 },
                                                 "tracklist": [
                                                     {
                                                         "id": 3,
                                                         "title": "Song title"
                                                     },
                                                     {
                                                         "id": 4,
                                                         "title": "Song title"
                                                     }
                                                 ]
                                             }
                                            """
                            )}
                    )
            ),
            //SCHEMA RARUNO EN SWAGGER
            @ApiResponse(responseCode = "404",
                    description = "Album not found",
                    content = @Content
            )
    })
    @JsonView(AlbumViews.FullAlbum.class)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumDTO getAlbumById(@PathVariable Long id){
        return service.getById(id);
    }

    @Operation(summary = "Update an album")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Album updated successfully",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AlbumDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "id": 1,
                                                 "title": "Album title",
                                                 "year": 2000,
                                                 "artist": {
                                                     "name": "Artist name"
                                                 }
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
            description = "Input body format",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AlbumDTO.class),
                    examples = @ExampleObject(
                            value = """
                                    {
                                         "title" : "Album title",
                                         "year" : 2000,
                                         "artist" : {
                                             "id" : 1
                                         }
                                     }
                                    """
                    )
            )
    )
    @JsonView(AlbumViews.SimpleAlbum.class)
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumDTO updateAlbum(@PathVariable Long id, @JsonView(AlbumViews.NewAlbum.class) @RequestBody AlbumDTO album){
        return service.edit(album,id);
    }

    @Operation(summary = "Delete an album")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "No content",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404",
                    description = "Album not found",
                    content = @Content
            )
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnAlbum(@PathVariable Long id){
        service.remove(id);
    }
}
