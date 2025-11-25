package com.Week2.Homework.SpringWebMVC.Homework.dto;

import com.Week2.Homework.SpringWebMVC.Homework.annotations.PasswordValidation;
import com.Week2.Homework.SpringWebMVC.Homework.annotations.PrimeNumberValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    private Long id;
    @Size(min = 2,max = 10,message = "the title should be under the limit -> [2,10]")
    @NotBlank
    private String title;

    @Email
    private String departmentEmail;

    @PasswordValidation
    private String password;
    @URL
    private String websiteLink;

    @JsonProperty(value = "isActive")
    @AssertTrue
    private boolean active;

    @PastOrPresent
    private LocalDate createdAt;

    @NotNull(message = "salary should be not null") @Positive(message = "salary should be positive")
    @DecimalMin(value = "1000.50",message = "Average salary at least greater then 1000")
    @DecimalMax(value = "100000.00")
    @Digits(integer = 6,fraction = 2)
    private Double averageSalary;
    @PrimeNumberValidation
    private Integer numberOfEmployees;
}
