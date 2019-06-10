package com.example.demo.service.impl;

import com.example.demo.dao.*;
import com.example.demo.dto.*;
import com.example.demo.model.Actors;
import com.example.demo.model.Movie;
import com.example.demo.model.MovieActors;
import com.example.demo.model.MovieOrder;
import com.example.demo.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final ActorsRepository actorsRepository;
    private final MovieActorsRepository movieActorsRepository;
    private final StatusOrderRepository statusOrderRepository;
    private final MovieOrderRepository movieOrderRepository;
    private ModelMapper modelMapper;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, ActorsRepository actorsRepository, MovieActorsRepository movieActorsRepository, StatusOrderRepository statusOrderRepository, MovieOrderRepository movieOrderRepository) {
        this.movieRepository = movieRepository;
        this.actorsRepository = actorsRepository;
        this.movieActorsRepository = movieActorsRepository;
        this.statusOrderRepository = statusOrderRepository;
        this.movieOrderRepository = movieOrderRepository;
    }

    @Override
    public ResponseEntity addNewMovie(MovieDto movieDto) {
        Movie movie = Movie.builder()
                .description(movieDto.getDescription())
                .director(movieDto.getDirector())
                .acceptableAge(movieDto.getAcceptableAge())
                .nameMovie(movieDto.getNameMovie())
                .yearOfProduction(movieDto.getYearOfProduction())
                .image(movieDto.getImage())
                .price(movieDto.getPrice())
                .species(movieDto.getSpecies())
                .build();

        List<ActorsDto> actors = movieDto.getMovieActors().getActors();
        List<Actors> actors1 = new ArrayList<>();

        actors.stream()
                .filter(actor -> actorsRepository.findByFirstNameAndLastNameAndDateOfBirth(actor.getFirstName(), actor.getLastName(), actor.getDateOfBirth()) == null)
                .forEach(actor -> actorsRepository.save(modelMapper.map(actor, Actors.class)));

        actors.forEach(actor -> actors1.add(actorsRepository.findByFirstNameAndLastNameAndDateOfBirth(actor.getFirstName(), actor.getLastName(), actor.getDateOfBirth())));
        List<MovieActors> movieActors = new ArrayList<>();

        actors1.forEach(actor -> {
            MovieActors build = MovieActors.builder()
                    .actors(actor)
                    .movie(movie)
                    .build();
            movieActors.add(build);
        });
        movieActorsRepository.saveAll(movieActors);

        Map<Object, Object> model = new HashMap<>();
        model.put("statusCode", "201");
        model.put("statusMessage", "Movie has been added");

        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity getMovieDetails(Long id) {
        Optional<Movie> byId = movieRepository.findById(id);
        if (!byId.isPresent()) {
            Map<Object, Object> model = new HashMap<>();
            model.put("statusCode", "404");
            model.put("statusMessage", "We dont have movie details");
            return new ResponseEntity<>(model, HttpStatus.NOT_FOUND);
        }

        List<Actors> actors = actorsRepository.findAllByActorsByMovieId(id);
        if (actors == null) {
            Map<Object, Object> model = new HashMap<>();
            model.put("statusCode", "404");
            model.put("statusMessage", "We dont have actors");
            return new ResponseEntity<>(model, HttpStatus.NOT_FOUND);
        }

        List<ActorsDto> listActors = new ArrayList<>();
        actors.forEach(actor -> listActors.add(ActorsDto.builder()
                .firstName(actor.getFirstName())
                .lastName(actor.getLastName())
                .dateOfBirth(actor.getDateOfBirth())
                .build())
        );

        MovieDetailsDto movieDetailsDto = MovieDetailsDto.builder()
                .acceptableAge(byId.get().getAcceptableAge())
                .description(byId.get().getDescription())
                .director(byId.get().getDirector())
                .image(byId.get().getImage())
                .nameMovie(byId.get().getNameMovie())
                .price(byId.get().getPrice())
                .species(byId.get().getSpecies())
                .yearOfProduction(byId.get().getYearOfProduction())
                .actorsDtos(listActors)
                .build();
        return new ResponseEntity<>(movieDetailsDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getOrderClient(String username) {
        Map<Object, Object> model = new HashMap<>();
        List<Movie> movies = movieRepository.findDuringStatus(username);
        if (movies == null) {
            model.put("statusCode", "404");
            model.put("statusMessage", "Client don't have orders");
            return new ResponseEntity<>(model, HttpStatus.NOT_FOUND);
        }


        List<ReturnOrderUserDto> movieSearchDtos = new ArrayList<>();
        for(Movie movie: movies){
           for(MovieOrder movieOrder: movie.getMovieOrders() ){
               movieSearchDtos.add(ReturnOrderUserDto.builder()
                       .movieId(movie.getMovieId())
                       .director(movie.getDirector())
                       .image(movie.getImage())
                       .nameMovie(movie.getNameMovie())
                       .year(movie.getYearOfProduction())
                       .date(movieOrder.getTime())
                       .build());

           }
        }
        return new ResponseEntity<>(movieSearchDtos, HttpStatus.OK);
    }


    @Override
    public ResponseEntity getAllMovie() {
        List<Movie> movies = movieRepository.findAll();
        if (movies == null) {
            Map<Object, Object> model = new HashMap<>();
            model.put("statusCode", "404");
            model.put("statusMessage", "We dont have movie");
            return new ResponseEntity<>(model, HttpStatus.NOT_FOUND);
        }
        List<MovieSearchDto> listMovieSearchDto = getListMovieSearchDto(movies);
        return new ResponseEntity<>(listMovieSearchDto, HttpStatus.OK);
    }

    private List<MovieSearchDto> getListMovieSearchDto(List<Movie> movies) {
        List<MovieSearchDto> movieSearchDtos = new ArrayList<>();
        movies.forEach(movie -> movieSearchDtos.add(MovieSearchDto.builder()
                .movieId(movie.getMovieId())
                .director(movie.getDirector())
                .image(movie.getImage())
                .nameMovie(movie.getNameMovie())
                .year(movie.getYearOfProduction())
                .build())
        );
        return movieSearchDtos;
    }

//    @Override
//    public ResponseEntity deleteMovie(DeleteMovieDto deleteMovieDto) {
//        Map<Object,Object> model = new HashMap<>();
//        Movie movie = movieRepository.findByNameMovieAndDirectorAndYearOfProduction(deleteMovieDto.getNameMovie(), deleteMovieDto.getDirector(), deleteMovieDto.getYearOfProduction());
//        if(movie == null){
//            return MovieNotExistException.getResponseEntity();
//        }
//        List<StatusOrder> statusOrder = statusOrderRepository.findAllByStatusAndIdMovie(movie.getMovieId());
//        if(statusOrder == null){
//            model.put("statusMessage","Movie is during by user");
//            model.put("statusCode","409");
//            movieRepository.delete(movie);
//        }else{
//            model.put("statusMessage","Movie is during by user");
//            model.put("statusCode","409");
//            return new ResponseEntity<>(model, HttpStatus.CONFLICT);
//        }
//        return new ResponseEntity<>(model, HttpStatus.OK);
//    }
}
