package com.jcCoder.springboottut.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hod {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String hodName;

    private String hodGender;


}
