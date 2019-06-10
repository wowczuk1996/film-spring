package com.example.demo.dao;

import com.example.demo.model.MovieOrder;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovieOrderRepository extends JpaRepository<MovieOrder,Long> {

}
