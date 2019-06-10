package com.example.demo.dao;

import com.example.demo.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    List<Movie> findAllBySpecies(String species);
    List<Movie> findAllBySpeciesAndAcceptableAge(String species,int acceptableAge);
    List<Movie> findAllBySpeciesAndAcceptableAgeAndYearOfProduction(String species,int acceptableAge,int yearOfProduction);
    Movie findByNameMovieAndDirectorAndYearOfProduction(String nameMovie,String director,int yearOfProduction);

    @Query(
            value = "Select * from movie where name_movie ilike ?1",
            nativeQuery =true
    )
    List<Movie> listSearchMovie(String name);
    @Query(
            value = "Select * from movie where movie_id in (Select movie_id from movie_order where status_id in (Select status_id from status_order where status='DURING' and  user_id in (Select user_id from users where email=?1)))",
            nativeQuery = true
    )
    List<Movie> findDuringStatus(String username);
}
