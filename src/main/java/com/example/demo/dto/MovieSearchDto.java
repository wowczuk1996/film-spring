package com.example.demo.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieSearchDto {
    private Long movieId;
    private String nameMovie;
    private int year;
    private String image;
    private String director;
}
