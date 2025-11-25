package com.cohort3.week2.springmvcRESTAPI.Week2L_4ServiceLayer.dto;

import com.cohort3.week2.springmvcRESTAPI.Week2L_4ServiceLayer.annotations.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
//it is itself equal to :
// @Getter
//@Setter
//@ToString
//@EqualsAndHashCode
//@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;

    //@NotNull(message = "required field in employee: name")
    //@NotEmpty(message = "name can not be null and the size of the value is greater than zero")
    @NotBlank(message = "trimmed length should be greater than zero")
    @Size(min = 3,max = 10,message = "number of character in the name should be in the range -> [3,10]")
    private String name;

    @NotBlank(message = "email is required")
    @Email(message = "email should be a valid email")
    private String email;

    @NotNull(message = "age is necessary")
    @Max(value = 80 , message = "age can not be greater than 80")
    @Min(value = 18, message = "age can not be less than 18")
    private Integer age;

    @NotNull @PastOrPresent(message = "date of joining should be of the past or the present")
    private LocalDate dateOfJoining;

    @NotBlank(message = "role of the employee can not be blank")
    //@Pattern(regexp = "^(ADMIN|USER)$" , message = "role of the employee can be USER or ADMIN only")
    @EmployeeRoleValidation
    private String role;

    @NotNull(message = "salary should be not null") @Positive(message = "salary should be positive")
    @Digits(integer = 6,fraction = 2,message = "the salary can not be greater than 6 figure")
    @DecimalMax(value = "100000.99")
    @DecimalMin(value = "100.50")
    private Double salary;
    //either you use integer or double if you are using digits annotation then you have to mention the integer and the fraction part even though the both is required or not
    //integer means the number of digits before decimal and fractions means the number of digits after decimal

    @JsonProperty("isActive")
    @AssertTrue(message = "Employee must be active")
    private Boolean isActive;

     //size is used for string or array
    //min max is used for number or integer kind of values
}
// if any of this fails it throw run time exception , so  for that we have to create a global exception handler