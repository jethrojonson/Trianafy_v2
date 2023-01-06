package com.salesianos.triana.dam.trianafy.album.controller;

import com.salesianos.triana.dam.trianafy.album.service.AlbumService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/album")
@RequiredArgsConstructor
@Tag(name = "Album Controller", description = "Controller of the Album operations")
public class AlbumController {

    private final AlbumService service;
}
