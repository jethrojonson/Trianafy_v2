package com.salesianos.triana.dam.trianafy.playlist.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianos.triana.dam.trianafy.album.dto.AlbumDTO;
import com.salesianos.triana.dam.trianafy.artist.dto.ArtistDTO;
import com.salesianos.triana.dam.trianafy.playlist.dto.PlaylistDTO;
import com.salesianos.triana.dam.trianafy.playlist.service.PlaylistService;
import com.salesianos.triana.dam.trianafy.playlist.view.PlaylistViews;
import com.salesianos.triana.dam.trianafy.song.dto.SongDTO;
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
@RequestMapping("/list")
@RequiredArgsConstructor
@Tag(name = "Playlist Controller", description = "Controller of the Playlist operations")
public class PlaylistController {

    private final PlaylistService service;

    @Operation(summary = "Add a new playlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Playlist added successfully",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PlaylistDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                  "id": 1,
                                                  "name": "Playlist name",
                                                  "description": "Playlist description"
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
                                        "name" : "Playlist name",
                                        "description" : "Playlist description"
                                    }
                                    """
                    )
            )
    )
    @JsonView(PlaylistViews.SimplePlaylist.class)
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public PlaylistDTO addNewPlaylist(@JsonView(PlaylistViews.NewPlaylist.class) @RequestBody PlaylistDTO playlist) {
        return service.add(playlist);
    }

    @Operation(summary = "Brings the list of playlists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Playlists found",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PlaylistDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                   {
                                                       "id": 1,
                                                       "name": "Playlist name",
                                                       "totalSongs": 20
                                                   },
                                                   {
                                                       "id": 2,
                                                       "name": "Playlist name",
                                                       "totalSongs": 120
                                                   }
                                               ]
                                            """
                            )}
                    )
            ),
            @ApiResponse(responseCode = "404",
                    description = "Playlists not found",
                    content = @Content
            )
    })
    @JsonView(PlaylistViews.SimplePlaylist2.class)
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<PlaylistDTO> getAllPlaylists() {
        return service.getAll();
    }

    @Operation(summary = "Bring a playlist with details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Playlist found",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PlaylistDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                  "id": 1,
                                                  "name": "Playlist name",
                                                  "description": "Playlist description",
                                                  "songs": [
                                                      {
                                                          "id": 2,
                                                          "title": "Song title",
                                                          "release": "01/01/2000",
                                                          "albumTitle": "Album title",
                                                          "artists": "Artists names"
                                                      },
                                                      {
                                                          "id": 3,
                                                          "title": "Song title",
                                                          "release": "01/01/2000",
                                                          "albumTitle": "Album title",
                                                          "artists": "Artists names"
                                                      },
                                                      {
                                                          "id": 4,
                                                          "title": "Song title",
                                                          "release": "01/01/2000",
                                                          "albumTitle": "Album title",
                                                          "artists": "Artists names"
                                                      }
                                                  ]
                                              }
                                            """
                            )}
                    )
            ),
            @ApiResponse(responseCode = "404",
                    description = "Playlist not found",
                    content = @Content
            )
    })
    @JsonView(PlaylistViews.FullPlaylist.class)
    @GetMapping({"/{id}", "/{id}/song"})
    @ResponseStatus(HttpStatus.OK)
    public PlaylistDTO getPlaylistById(@PathVariable Long id) {
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
                                                 "id": 1,
                                                 "name": "Playlist name",
                                                 "description": "Playlist description"
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
                    description = "Playlist not found",
                    content = @Content
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Input body format",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PlaylistDTO.class),
                    examples = @ExampleObject(
                            value = """
                                    {
                                         "name" : "Playlist name",
                                         "description" : "Playlist description"
                                     }
                                    """
                    )
            )
    )
    @JsonView(PlaylistViews.SimplePlaylist2.class)
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlaylistDTO updatePlaylist(@JsonView(PlaylistViews.NewPlaylist.class) @RequestBody PlaylistDTO playlist, @PathVariable Long id) {
        return service.edit(playlist, id);
    }

    @Operation(summary = "Delete a playlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "No content",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404",
                    description = "Playlist not found",
                    content = @Content
            )
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlaylist(@PathVariable Long id) {
        service.remove(id);
    }

    //FALTA \|/
    @Operation(summary = "Add an existing song to a playlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Song added to playlist",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PlaylistDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                  "id": 1,
                                                  "name": "Playlist name",
                                                  "description": "Playlist description",
                                                  "songs": [
                                                      {
                                                          "id": 2,
                                                          "title": "Song title",
                                                          "release": "01/01/2000",
                                                          "albumTitle": "Album title",
                                                          "artists": "Artists names"
                                                      },
                                                      {
                                                          "id": 3,
                                                          "title": "Song title",
                                                          "release": "01/01/2000",
                                                          "albumTitle": "Album title",
                                                          "artists": "Artists names"
                                                      },
                                                      {
                                                          "id": 4,
                                                          "title": "Song title",
                                                          "release": "01/01/2000",
                                                          "albumTitle": "Album title",
                                                          "artists": "Artists names"
                                                      }
                                                  ]
                                              }
                                            """
                            )}
                    )
            ),
            @ApiResponse(responseCode = "404",
                    description = "Playlist not found",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404",
                    description = "Song not found",
                    content = @Content
            )

    })
    @JsonView(PlaylistViews.FullPlaylist.class)
    @PostMapping("/{listId}/song/{songId}")
    @ResponseStatus(HttpStatus.OK)
    public PlaylistDTO addSongToPlaylist(@PathVariable Long listId, @PathVariable Long songId){
        return service.addSongToPlaylist(listId, songId);
    }


    @Operation(summary = "Bring a song in a playlist with details")
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
            ),
            @ApiResponse(responseCode = "404",
                    description = "List not found",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404",
                    description = "Song not in playlist",
                    content = @Content
            )
    })
    @JsonView(SongViews.FullSong.class)
    @GetMapping("/{listId}/song/{songId}")
    @ResponseStatus(HttpStatus.OK)
    public SongDTO getSongInPlaylist(@PathVariable Long listId, @PathVariable Long songId){
        return service.getSongInPlaylist(listId, songId);
    }

    @Operation(summary = "Delete a song from a playlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "No content",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404",
                    description = "Song not found",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404",
                    description = "List not found",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404",
                    description = "Song not in playlist",
                    content = @Content
            )
    })
    @DeleteMapping("/{listId}/song/{songId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSongFromPlaylist(@PathVariable Long listId, @PathVariable Long songId){
        service.removeSongFromPlaylist(listId, songId);
    }



}
