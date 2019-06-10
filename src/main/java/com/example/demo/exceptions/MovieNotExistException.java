package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class MovieNotExistException extends Exception {
    public MovieNotExistException(String movie_not_exist) {
    }
    public static ResponseEntity getResponseEntity() {
        Map<Object,Object> model = new HashMap<>();
        model.put("statusMessage","Movie not exist in database");
        model.put("statusCode","404");
        return new ResponseEntity<>(model, HttpStatus.NOT_FOUND);
    }
}
