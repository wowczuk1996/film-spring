package com.example.demo.dto;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActorsDto {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
}
