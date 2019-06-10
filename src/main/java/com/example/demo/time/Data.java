package com.example.demo.time;

import com.example.demo.dao.MovieOrderRepository;
import com.example.demo.dao.StatusOrderRepository;
import com.example.demo.model.MovieOrder;
import com.example.demo.model.StatusOrder;
import com.example.demo.model.en.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class Data {
    private final StatusOrderRepository statusRepository;
    private final MovieOrderRepository movieOrderRepository;

    @Autowired
    public Data(StatusOrderRepository statusRepository, MovieOrderRepository movieOrderRepository) {
        this.statusRepository = statusRepository;
        this.movieOrderRepository = movieOrderRepository;
    }

    @Scheduled(fixedRate = 10000)
    public void executeTask() {
        List<StatusOrder> all = statusRepository.findAllByStatus(Status.DURING);
        for (StatusOrder s : all) {
            for (MovieOrder mo : s.getOrder()) {
                System.out.println(s.getStatus());
                if (mo.getTime().getTime() < new Date().getTime()) {
                    System.out.println(s.getStatus());
                    s.setStatus(Status.ENDED);
                    statusRepository.save(s);
                }
            }
        }
    }




}
