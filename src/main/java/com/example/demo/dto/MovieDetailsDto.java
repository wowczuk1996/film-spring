package com.example.demo.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDetailsDto {
    private int acceptableAge;
    private String nameMovie;
    private String director;
    private int yearOfProduction;
    private String description;
    private String image;
    private String species;
    private double price;
    private List<ActorsDto> actorsDtos;
}

