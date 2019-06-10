package com.example.demo.contoller;

import com.example.demo.dto.MovieDto;
import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/movie")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity saveMovieToDatabase(@RequestBody final MovieDto movieDto) {
        return movieService.addNewMovie(movieDto);
    }
    @GetMapping(value = "/show/all")
    public ResponseEntity getAllMovie() {
        return movieService.getAllMovie();
    }

    @GetMapping(value = "/show")
    public ResponseEntity getAllMovie(@RequestParam Long id) {
        return movieService.getMovieDetails(id);
    }
    @GetMapping(value = "/get")
    public ResponseEntity getOrderClient(@AuthenticationPrincipal UserDetails userDetails){
        return movieService.getOrderClient(userDetails.getUsername());
    }

}
