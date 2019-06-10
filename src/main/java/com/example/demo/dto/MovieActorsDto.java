package com.example.demo.dto;
import com.example.demo.model.Actors;
import com.example.demo.model.Movie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class MovieActorsDto {
    private List<ActorsDto> actors;
    private MovieDto movie; // przez modelmapper musi byc

}
