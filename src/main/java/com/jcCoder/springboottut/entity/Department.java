package com.jcCoder.springboottut.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.jcCoder.springboottut.entity.Hod;
import org.hibernate.engine.internal.Cascade;


@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long departmentId;

    @NotBlank(message = "Enter the Department name")
    private String departmentName;

    @NotNull(message = "Address Can't be null")
    private String departmentAddress;

    private String departmentCode;

    @Email(message = "Invalid Email Address , Please Enter a valid mail")
    private String departmentEmailAddress;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "hod", referencedColumnName = "id")
    private Hod raghavaHod;

}
