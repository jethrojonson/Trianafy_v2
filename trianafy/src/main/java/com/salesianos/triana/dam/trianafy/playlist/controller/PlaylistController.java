package com.salesianos.triana.dam.trianafy.playlist.controller;

import com.salesianos.triana.dam.trianafy.playlist.service.PlaylistService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/playlist")
@RequiredArgsConstructor
@Tag(name = "Playlist Controller", description = "Controller of the Playlist operations")
public class PlaylistController {

    private final PlaylistService service;
}
