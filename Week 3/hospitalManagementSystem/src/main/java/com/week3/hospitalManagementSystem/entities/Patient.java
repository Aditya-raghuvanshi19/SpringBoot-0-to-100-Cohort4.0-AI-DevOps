package com.week3.hospitalManagementSystem.entities;


import com.week3.hospitalManagementSystem.entities.types.BloodGroupType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@ToString
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate birthDate;

    private String email;

    private String gender;

    @Enumerated(value = EnumType.STRING)//? by default the EnumType is ORDINAL , but the value store in the Table regarding this is index, so value so to showcase exact value we change it to STRING
    private BloodGroupType bloodGroup;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
