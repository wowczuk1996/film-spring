package com.example.demo.service.impl;

import com.example.demo.dao.MovieOrderRepository;
import com.example.demo.dao.MovieRepository;
import com.example.demo.dao.StatusOrderRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.dto.MovieSearchDto;
import com.example.demo.dto.MovieTimeDto;
import com.example.demo.dto.OrderDto;
import com.example.demo.exceptions.MovieNotExistException;
import com.example.demo.exceptions.OptionalException;
import com.example.demo.model.Movie;
import com.example.demo.model.MovieOrder;
import com.example.demo.model.StatusOrder;
import com.example.demo.model.en.Status;
import com.example.demo.model.User;
import com.example.demo.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StatusServiceImpl implements StatusService {
    private final StatusOrderRepository statusRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final MovieOrderRepository movieOrderRepository;

    @Autowired
    public StatusServiceImpl(StatusOrderRepository statusRepository, UserRepository userRepository, MovieRepository movieRepository, MovieOrderRepository movieOrderRepository) {
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.movieOrderRepository = movieOrderRepository;
    }

    @Override
    public void createOrder(String username, OrderDto orderDto) throws OptionalException {
        Optional<User> user = userRepository.findByEmail(username);
        List<Optional<Movie>> movies = new ArrayList<>();
        orderDto.getMovieTimes().forEach(id -> {
            try {
                movieRepository.findById(id.getMovieId()).orElseThrow(() -> new MovieNotExistException("Movie not exist"));
                movies.add(movieRepository.findById(id.getMovieId()));
            } catch (MovieNotExistException e) {
                e.printStackTrace();
            }
        });

        StatusOrder statusOrder = new StatusOrder();
        double priceOrder = countPrice(movies, orderDto);
        List<MovieOrder> movieOrder1 = obiectOrder(movies, orderDto, statusOrder);

        statusOrder.setStatus(Status.DURING);
        statusOrder.setPaid(true);
        statusOrder.setPrice(priceOrder);
        statusOrder.setDateOrder(new Date());
        statusOrder.setDatePaid(new Date());
        statusOrder.setUser(user.orElseThrow(() -> new OptionalException("This object is not user")));
        statusOrder.setOrder(movieOrder1);

        statusRepository.save(statusOrder);
    }


    private double countPrice(List<Optional<Movie>> movies, OrderDto orderDto) {
        double priceOrder = 0;
        for (Optional<Movie> m : movies) {
            for (MovieTimeDto mt : orderDto.getMovieTimes()) {
                System.out.println(mt.getMovieId());
                if (mt.getMovieId() == m.get().getMovieId())
                    priceOrder += mt.getNumberDay() * m.get().getPrice();
            }

        }
        return priceOrder;
    }

    private List<MovieOrder> obiectOrder(List<Optional<Movie>> movies, OrderDto orderDto, StatusOrder statusOrder) throws OptionalException {
        List<MovieOrder> orders = new ArrayList<>();
        MovieOrder order = null;
        for (Optional<Movie> m : movies) {
            for (MovieTimeDto mt : orderDto.getMovieTimes()) {
                System.out.println(mt.getMovieId());
                if (mt.getMovieId() == m.get().getMovieId()) {
                    order = new MovieOrder();
                    order.setMovies(m.orElseThrow(() -> new OptionalException("This object is not movie")));
                    order.setTime(countDate(mt.getNumberDay()));
                    order.setStatusOrder(statusOrder);
                    orders.add(order);
                }
            }
        }
        return orders;
    }

    private Date countDate(int numberOfDay) {
        Date date = new Date();
        long timeOrder = date.getTime() + numberOfDay * 86400000;
        return new Date(timeOrder);
    }


}
