package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieDto {
    private int acceptableAge;
    private String nameMovie;
    private String director;
    private int yearOfProduction;
    private String description;
    private String image;
    private String species;
    private MovieActorsDto movieActors;
    private double price;
}
