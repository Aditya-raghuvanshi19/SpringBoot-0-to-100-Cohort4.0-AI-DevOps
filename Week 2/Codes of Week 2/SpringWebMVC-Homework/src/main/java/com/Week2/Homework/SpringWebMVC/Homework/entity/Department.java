package com.Week2.Homework.SpringWebMVC.Homework.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String departmentEmail;
    private String password;
    private String websiteLink;
    @Column(name = "isActive")
    @JsonProperty(value = "isActive")
    private boolean active;

    private LocalDate createdAt;
    private Double averageSalary;

    private Integer numberOfEmployees;

}
