package com.salesianos.triana.dam.trianafy.artist.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.awt.*;
import java.time.LocalDateTime;


@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ArtistError {

    @NonNull
    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime date = LocalDateTime.now();

    @NonNull
    private String message;
}
