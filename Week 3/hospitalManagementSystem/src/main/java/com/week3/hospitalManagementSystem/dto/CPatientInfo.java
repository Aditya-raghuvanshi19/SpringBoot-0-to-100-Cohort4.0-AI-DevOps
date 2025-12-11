package com.week3.hospitalManagementSystem.dto;

import lombok.Data;

@Data
public class CPatientInfo {
    private final  Long id;
    private final String name;
}

//? this is the actual way where we can get data and if want to modify data we can do so but with interface way we can not do
//? we have to make field as final so that @Data use requiredargsconstructor and put data into the properties