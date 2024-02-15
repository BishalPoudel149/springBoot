package com.jcCoder.springboottut.entity.UserEntity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Data
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;

    private String name;
}
