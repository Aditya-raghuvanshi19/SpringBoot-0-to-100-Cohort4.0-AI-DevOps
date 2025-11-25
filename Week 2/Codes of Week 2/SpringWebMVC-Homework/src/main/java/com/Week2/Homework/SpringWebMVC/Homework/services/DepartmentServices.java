package com.Week2.Homework.SpringWebMVC.Homework.services;

import com.Week2.Homework.SpringWebMVC.Homework.dto.DepartmentDto;
import com.Week2.Homework.SpringWebMVC.Homework.entity.Department;
import com.Week2.Homework.SpringWebMVC.Homework.exceptions.ResourceNotFoundException;
import com.Week2.Homework.SpringWebMVC.Homework.repositories.DepartmentsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServices {

    private final DepartmentsRepository departmentsRepository;
    private final ModelMapper modelMapper;

    public DepartmentServices(DepartmentsRepository departmentsRepository, ModelMapper modelMapper) {
        this.departmentsRepository = departmentsRepository;
        this.modelMapper = modelMapper;
    }

    public List<DepartmentDto> getDepartments(){
        List<Department> allDepartments= departmentsRepository.findAll();
        List<DepartmentDto> listDepartmentDto = allDepartments
                .stream()
                .map((department -> modelMapper.map(department,DepartmentDto.class)))
                .collect(Collectors.toList());
        return listDepartmentDto;
    }
    public DepartmentDto getDepartmentById(long departmentId){
        isDepartmentExist(departmentId);
        Department department= departmentsRepository.findById(departmentId).orElseThrow(()->new ResourceNotFoundException("Department not found with the id: "+departmentId));
        return modelMapper.map(department,DepartmentDto.class);

    }

    private boolean isDepartmentExist(Long departmentId){
        boolean exist = departmentsRepository.existsById(departmentId);
        if(!exist) throw new ResourceNotFoundException("Department not found with the id: "+departmentId);
        return true;
    }

    public DepartmentDto saveNewDepartment(DepartmentDto departmentDto){
        Department department = modelMapper.map(departmentDto , Department.class);
       Department savedDepartment = departmentsRepository.save(department);
        return modelMapper.map(savedDepartment,DepartmentDto.class);
    }

    public DepartmentDto updateDepartment(Long departmentId , DepartmentDto departmentDto){
        isDepartmentExist(departmentId);
        Department updateDepartment = modelMapper.map(departmentDto,Department.class);
        updateDepartment.setId(departmentId);
        Department savedDepartment = departmentsRepository.save(updateDepartment);

        return modelMapper.map(savedDepartment,DepartmentDto.class);
    }

    public boolean deleteDepartment(Long departmentId){
        isDepartmentExist(departmentId);
        departmentsRepository.deleteById(departmentId);
        return  true;
    }






















}
