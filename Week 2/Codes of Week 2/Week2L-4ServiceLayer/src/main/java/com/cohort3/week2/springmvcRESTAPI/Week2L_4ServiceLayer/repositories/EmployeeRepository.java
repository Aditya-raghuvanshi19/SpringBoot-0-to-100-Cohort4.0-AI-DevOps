package com.cohort3.week2.springmvcRESTAPI.Week2L_4ServiceLayer.repositories;

import com.cohort3.week2.springmvcRESTAPI.Week2L_4ServiceLayer.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity , Long> {
}
