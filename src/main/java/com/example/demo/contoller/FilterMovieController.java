package com.example.demo.contoller;

import com.example.demo.service.SearchMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/movie/filter")
public class FilterMovieController {
    private final SearchMovieService searchMovieService;

    @Autowired
    public FilterMovieController(SearchMovieService searchMovieService) {
        this.searchMovieService = searchMovieService;
    }

    @PostMapping(value = "/search")
    public ResponseEntity searchMovieForName(@RequestParam String name) {
        return searchMovieService.filterNameMovie(name);
    }

    @PostMapping(value = "/filter/species")
    public ResponseEntity filterMovie(@RequestParam String species) {
        return searchMovieService.filterSpecies(species);
    }

    @PostMapping(value = "/filter/species/age")
    public ResponseEntity filterMovie(@RequestParam String species, @RequestParam String age) {
        return searchMovieService.filterSpeciesAndAge(species, age);
    }

    @PostMapping(value = "/filter/all")
    public ResponseEntity filterMovie(@RequestParam String species, @RequestParam String age, @RequestParam String year) {
        return searchMovieService.filterSpeciesAndAgeAndYear(species, age, year);
    }
}
