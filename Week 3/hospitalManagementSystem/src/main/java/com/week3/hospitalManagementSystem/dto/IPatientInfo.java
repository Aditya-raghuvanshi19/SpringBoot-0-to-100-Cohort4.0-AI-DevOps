package com.week3.hospitalManagementSystem.dto;

public interface IPatientInfo {

    //* to perform the Projection
    //* we have to create DTO for this
    //? their are multiple ways to create DTO the ways are
    //? 1. by using the interface
    //? 2. by using class or record type

    //! this is 1st way (by interface)

    Long getId();
    String getName();
    String getEmail();
}
