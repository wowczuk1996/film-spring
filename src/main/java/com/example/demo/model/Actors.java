package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Actors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actorsId;
    private String firstName;
    private String lastName;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_actors")
    @JsonIgnore
    private List<MovieActors> movieActors;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "id_actors")
//    @JsonIgnore
//    private MovieActors movieActors;



}
