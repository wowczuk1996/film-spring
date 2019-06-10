package com.example.demo.contoller;

import com.example.demo.dto.OrderDto;
import com.example.demo.exceptions.OptionalException;
import com.example.demo.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    private final StatusService statusService;

    @Autowired
    public OrderController(StatusService statusService) {
        this.statusService = statusService;
    }

    @PostMapping(value = "/new")
    public String saveMovieToDatabase(@AuthenticationPrincipal UserDetails userDetails, @RequestBody final OrderDto orderDto) throws OptionalException {
        statusService.createOrder(userDetails.getUsername(),orderDto);
        return userDetails.getUsername();
    }

}
