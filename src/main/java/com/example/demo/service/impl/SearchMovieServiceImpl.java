package com.example.demo.service.impl;

import com.example.demo.dao.MovieRepository;
import com.example.demo.dto.MovieSearchDto;
import com.example.demo.exceptions.MovieNotExistException;
import com.example.demo.model.Movie;
import com.example.demo.service.SearchMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchMovieServiceImpl implements SearchMovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public SearchMovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public ResponseEntity filterNameMovie(String name) {
        List<Movie> movies = movieRepository.listSearchMovie("%" + name + "%");
        return convertToMovieSearchDto(movies);
    }

    @Override
    public ResponseEntity filterSpecies(String species) {
        List<Movie> movies = movieRepository.findAllBySpecies(species);
        return convertToMovieSearchDto(movies);
    }

    @Override
    public ResponseEntity filterSpeciesAndAge(String species, String age) {
        int acceptableAge = Integer.parseInt(age);
        List<Movie> movies = movieRepository.findAllBySpeciesAndAcceptableAge(species,acceptableAge);
        return convertToMovieSearchDto(movies);
    }

    @Override
    public ResponseEntity filterSpeciesAndAgeAndYear(String species, String age, String year) {
        int acceptableAge = Integer.parseInt(age);
        int yearOfProduction = Integer.parseInt(year);
        List<Movie> movies = movieRepository.findAllBySpeciesAndAcceptableAgeAndYearOfProduction(species,acceptableAge,yearOfProduction);
        return convertToMovieSearchDto(movies);
    }

    private ResponseEntity convertToMovieSearchDto(List<Movie> movies){
        List<MovieSearchDto> listMovie = new ArrayList<>();
        if (movies.isEmpty() || movies == null) {
            return MovieNotExistException.getResponseEntity();
        }
        movies.forEach(movie -> listMovie.add(MovieSearchDto.builder()
                .director(movie.getDirector())
                .image(movie.getImage())
                .nameMovie(movie.getNameMovie())
                .year(movie.getYearOfProduction())
                .build())
        );
        return new ResponseEntity<>(listMovie, HttpStatus.OK);
    }


}
