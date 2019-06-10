package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieActors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieActorsId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_actors")
    @JsonIgnore
    private Actors actors;
//
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
//    @JoinColumn(name = "id_movie")
//    @JsonIgnore
//    private Movie movie;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_movie")
    @JsonIgnore
    private Movie movie;



}
