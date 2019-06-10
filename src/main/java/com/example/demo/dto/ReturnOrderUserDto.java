package com.example.demo.dto;

import lombok.*;

import java.util.Date;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class ReturnOrderUserDto {
    private Long movieId;
    private String nameMovie;
    private int year;
    private String image;
    private String director;
    private Date date;
}
