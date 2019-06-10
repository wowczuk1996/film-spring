package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "movie_order")
public class MovieOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
//
//    @OneToOne(mappedBy = "order")
//    @JoinColumn(name = "status_id")
//    @JsonIgnore
//    private StatusOrder statusOrder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    @JsonIgnore
    private StatusOrder statusOrder;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    @JsonIgnore
    private Movie movies;
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
}
