package com.cohort3.week2.springmvcRESTAPI.Week2L_4ServiceLayer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddEmployeeDTO {
    private String name;
    private String email;
    private Integer age;

    private LocalDate dateOfJoining;
    private Boolean isActive;

}
