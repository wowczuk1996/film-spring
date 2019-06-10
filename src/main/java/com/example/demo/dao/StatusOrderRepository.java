package com.example.demo.dao;
import com.example.demo.model.Movie;
import com.example.demo.model.MovieOrder;
import com.example.demo.model.StatusOrder;
import com.example.demo.model.en.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusOrderRepository extends JpaRepository<StatusOrder,Long> {
    List<StatusOrder> findAllByStatus(Status status);
}
