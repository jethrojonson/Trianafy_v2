package com.salesianos.triana.dam.trianafy.song.controller;

import com.salesianos.triana.dam.trianafy.song.service.SongService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/song")
@RequiredArgsConstructor
@Tag(name = "Song Controller", description = "Controller of the Song operations")
public class SongController {

    private final SongService service;
}
