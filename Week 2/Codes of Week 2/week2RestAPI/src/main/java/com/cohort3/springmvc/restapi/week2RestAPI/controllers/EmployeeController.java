package com.cohort3.springmvc.restapi.week2RestAPI.controllers;

import com.cohort3.springmvc.restapi.week2RestAPI.dto.EmployeeDTO;
import com.cohort3.springmvc.restapi.week2RestAPI.entities.EmployeeEntity;
import com.cohort3.springmvc.restapi.week2RestAPI.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/employees") //we use this when we have to declare that mapping in this class is all for employees and url path is starts with /employees and then followed by the url mapping mark on the function
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path = "/") //this path is in actual like this : http://localhost:8080/employees/
    public String Landingpage(){
    return "Hii welcome Buddy ";
    }

    @GetMapping(path = "/getSecretMessage")
    public String getMySecretMessage(){
    return "Secret message: aasdfghjkl";
    }

    //@GetMapping("/employees/{employeeID}")
//    @GetMapping("/{employeeID}")
//    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeID") Long employeeID){ // we use name here because in the function parameter if we do not want to use this long name employeeID then simple refer that parameter for the pathvariable by the name passing, this name is actually for the variable which we pass in the URL
//        return new EmployeeDTO(employeeID,"Aditya","aditya@gmail.com",22, LocalDate.of(2025,11,8),true);
//    }

    @GetMapping("/{employeeID}")
    public EmployeeEntity getEmployeeByIdfromDb(@PathVariable(name = "employeeID") Long id){
        return employeeRepository.findById(id).orElseThrow(()->new IllegalArgumentException("no employee by this id"));
    }
    @GetMapping
    public List<EmployeeEntity> getallEmloyee(){
        return employeeRepository.findAll();
    }

    @PostMapping
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity employeeEntity){
        return employeeRepository.save(employeeEntity);
    }


//    @GetMapping(path = "/employees")
//    public String getInfoByrequestparams(@RequestParam Integer age ,@RequestParam String name,@RequestParam String sortBy ){ //same name thing we can use here also
//        return "hii "+name+" your age is: "+age+" sort By: "+sortBy;
//    }
// here if we go to /employees without passing the request params then it give error, because for calling this function we have to pass these request params

    //To make the request params as a optional we use required = false
   // @GetMapping(path = "/employees")
//    @GetMapping //this mapping is like : http://localhost:8080/employees
//    public String getInfoByrequestparams(@RequestParam(required = false) Integer age ,@RequestParam(required = false) String name,@RequestParam(required = false) String sortBy ){
//        return "hii "+name+" your age is: "+age+" sort By: "+sortBy;
//    }

//    @PostMapping
//    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO newEmployee){
//        newEmployee.setId(100L);
//        return newEmployee;
//    }
}
