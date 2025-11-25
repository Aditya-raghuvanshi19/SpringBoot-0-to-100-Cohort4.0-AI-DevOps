package com.cohort3.springmvc.restapi.week2RestAPI.repository;

import com.cohort3.springmvc.restapi.week2RestAPI.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
}
