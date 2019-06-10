package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DeleteMovieDto {
    private String nameMovie;
    private String director;
    private int yearOfProduction;
}
