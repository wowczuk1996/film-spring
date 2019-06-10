package com.example.demo.service;

import com.example.demo.dto.MovieDto;
import org.springframework.http.ResponseEntity;

public interface MovieService {
    ResponseEntity addNewMovie(MovieDto movieDto);
    ResponseEntity getMovieDetails(Long id);
    ResponseEntity getAllMovie();

    ResponseEntity getOrderClient(String username);
}
