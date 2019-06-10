package com.example.demo.service;
import com.example.demo.dto.OrderDto;
import com.example.demo.exceptions.OptionalException;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface StatusService {
    void createOrder(String username, OrderDto orderDto) throws OptionalException;


}
