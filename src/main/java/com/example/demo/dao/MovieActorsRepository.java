package com.example.demo.dao;

import com.example.demo.model.MovieActors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieActorsRepository extends JpaRepository<MovieActors,Long> {
}
