package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private long movieId;
    private int acceptableAge;
    private String nameMovie;
    private String director;
    private int yearOfProduction;
    private String description;
    private String image;
    private String species;
    private double price;

    //    @OneToOne(mappedBy = "movie",cascade = CascadeType.ALL,orphanRemoval = true)
//    @JoinColumn(name = "id_movie")
//    @JsonIgnore
//    private MovieActors movieActors;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_movie")
    @JsonIgnore
    private List<MovieActors> movieActors;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    @JsonIgnore
    private List<MovieOrder> movieOrders;

}
