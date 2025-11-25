package com.Week2.Homework.SpringWebMVC.Homework.repositories;

import com.Week2.Homework.SpringWebMVC.Homework.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentsRepository extends JpaRepository<Department,Long> {
}
