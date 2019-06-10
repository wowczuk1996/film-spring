package com.example.demo.service;

import org.springframework.http.ResponseEntity;

public interface SearchMovieService {
    ResponseEntity filterNameMovie(String name);
    ResponseEntity filterSpecies(String species);
    ResponseEntity filterSpeciesAndAge(String species,String age);
    ResponseEntity filterSpeciesAndAgeAndYear(String species,String age,String year);
}
