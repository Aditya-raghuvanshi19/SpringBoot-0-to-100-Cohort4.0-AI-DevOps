package com.Week2.Homework.SpringWebMVC.Homework.controllers;

import com.Week2.Homework.SpringWebMVC.Homework.dto.DepartmentDto;
import com.Week2.Homework.SpringWebMVC.Homework.services.DepartmentServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private  final DepartmentServices services;

    public DepartmentController(DepartmentServices services) {
        this.services = services;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getDepartments(){
        List<DepartmentDto> allDepartment = services.getDepartments();
        return new ResponseEntity<>(allDepartment, HttpStatus.OK);
    }

    @PostMapping
    public  ResponseEntity<DepartmentDto> saveDepartment(@RequestBody @Valid DepartmentDto departmentDto){
        DepartmentDto departmentDto1 = services.saveNewDepartment(departmentDto);
        return new ResponseEntity<>(departmentDto1,HttpStatus.CREATED);
    }

    @PutMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDto> updateDepartmentById(@PathVariable Long departmentId , @RequestBody @Valid DepartmentDto departmentDto){
        DepartmentDto departmentDto1 = services.updateDepartment(departmentId,departmentDto);
        return new ResponseEntity<>(departmentDto1,HttpStatus.OK);
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable Long departmentId){
        Boolean isDeleted = services.deleteDepartment(departmentId);
        return new ResponseEntity<>(isDeleted,HttpStatus.OK);
    }
    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long departmentId){
        DepartmentDto departmentDto = services.getDepartmentById(departmentId);
        return new ResponseEntity<>(departmentDto,HttpStatus.OK);
    }




}
