package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name="users_roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rolesId;
    private String role;

    @OneToOne(mappedBy = "roles")
    @JsonIgnore
    private User user;
//@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//@JoinColumn(name = "user_id")
//@JsonIgnore
//private User user;
}
