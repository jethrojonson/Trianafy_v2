package com.salesianos.triana.dam.trianafy.artist.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianos.triana.dam.trianafy.artist.dto.ArtistDTO;
import com.salesianos.triana.dam.trianafy.artist.error.ArtistError;
import com.salesianos.triana.dam.trianafy.artist.error.ArtistExceptions;
import com.salesianos.triana.dam.trianafy.artist.model.Artist;
import com.salesianos.triana.dam.trianafy.artist.service.ArtistService;
import com.salesianos.triana.dam.trianafy.artist.view.ArtistViews;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artist")
@RequiredArgsConstructor
@Tag(name = "Artist Controller", description = "Controller of the Artist operations")
public class ArtistController {

    private final ArtistService service;

    @Operation(summary = "Add a new artist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Artist added successfully",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ArtistDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id" : 1,
                                                "name" : "The name"
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
                    schema = @Schema(implementation = ArtistDTO.class),
                    examples = @ExampleObject(
                            value = """
                                    {
                                        "name" : "The name"
                                    }
                                    """
                    )
            )
    )
    @JsonView(ArtistViews.SimpleArtist.class)
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistDTO addNewArtist(@JsonView(ArtistViews.NewArtist.class) @RequestBody ArtistDTO artist) {
        return service.add(artist);
    }

    @Operation(summary = "Brings the list of artists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Artists found",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ArtistDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "id" : 1,
                                                    "name" : "The name"
                                                },
                                                {
                                                    "id" : 2,
                                                    "name" : "The name"
                                                },
                                                {
                                                    "id" : 3,
                                                    "name" : "The name"
                                                }
                                            ]
                                            """
                            )}
                    )
            ),
            @ApiResponse(responseCode = "404",
                    description = "Artists not found",
                    content = @Content
            )
    })
    @JsonView(ArtistViews.SimpleArtist.class)
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<ArtistDTO> getAllArtists() {
        return service.getAll();
    }

    @Operation(summary = "Bring an artist with details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Artist found",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ArtistDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 1,
                                                "name": "Artist name",
                                                "totalSongs": 1,
                                                "totalAlbums": 1,
                                                "songs": [
                                                    {
                                                        "id": 2,
                                                        "title": "Song title",
                                                        "release": "2000-01-01"
                                                    }
                                                ],
                                                "albums": [
                                                    {
                                                        "id": 3,
                                                        "title": "Album title",
                                                        "year": 2000
                                                    }
                                                ]
                                            }
                                            """
                            )}
                    )
            ),
            //SCHEMA RARUNO EN SWAGGER
            @ApiResponse(responseCode = "404",
                    description = "Artist not found",
                    content = @Content
            )
    })
    @JsonView(ArtistViews.FullArtist.class)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArtistDTO getArtistById(@PathVariable Long id) {
        return service.getById(id);
    }

    @Operation(summary = "Update an artist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Artist updated successfully",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ArtistDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id" : 1,
                                                "name" : "The name"
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
                    description = "Artist not found",
                    content = @Content
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Input body format",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ArtistDTO.class),
                    examples = @ExampleObject(
                            value = """
                                    {
                                        "name" : "The name"
                                    }
                                    """
                    )
            )
    )
    @JsonView(ArtistViews.SimpleArtist.class)
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArtistDTO updateArtist(@PathVariable Long id, @JsonView(ArtistViews.NewArtist.class) @RequestBody ArtistDTO artist){
        return service.edit(artist,id);
    }

    @Operation(summary = "Delete an artist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "No content",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404",
                    description = "Artist not found",
                    content = @Content
            )
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnArtist(@PathVariable Long id){
        service.remove(id);
    }




}
