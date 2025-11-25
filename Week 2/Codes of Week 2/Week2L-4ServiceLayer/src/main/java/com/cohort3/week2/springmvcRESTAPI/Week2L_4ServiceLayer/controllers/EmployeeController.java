package com.cohort3.week2.springmvcRESTAPI.Week2L_4ServiceLayer.controllers;

import com.cohort3.week2.springmvcRESTAPI.Week2L_4ServiceLayer.dto.AddEmployeeDTO;
import com.cohort3.week2.springmvcRESTAPI.Week2L_4ServiceLayer.dto.EmployeeDTO;
import com.cohort3.week2.springmvcRESTAPI.Week2L_4ServiceLayer.entities.EmployeeEntity;
import com.cohort3.week2.springmvcRESTAPI.Week2L_4ServiceLayer.exceptions.ResourceNotFoundException;
import com.cohort3.week2.springmvcRESTAPI.Week2L_4ServiceLayer.services.EmployeeServices;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeServices employeeServices;

    public EmployeeController(EmployeeServices employeeServices) {
        this.employeeServices = employeeServices;
    }
//    public EmployeeController(EmployeeServices employeeServices) {
//        this.employeeServices = employeeServices;
//    }

    //    @GetMapping("/{employeeID}")
//    public EmployeeEntity getEmployeeByID(@PathVariable(name = "employeeID") Long id){
//        return employeeServices.getEmployeeByID(id);
//    }
//        @GetMapping("/{employeeID}")
//    public EmployeeDTO getEmployeeByID(@PathVariable(name = "employeeID") Long id){
//        return employeeServices.getEmployeeByID(id);
//    }
//    @GetMapping("/{employeeID}")
//    public ResponseEntity<EmployeeDTO> getEmployeeByID(@PathVariable(name = "employeeID") Long id){
//        Optional<EmployeeDTO> employeeDTO = employeeServices.getEmployeeByID(id);
//        return employeeDTO.map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
//                .orElse(ResponseEntity.notFound().build());
//    }
    @GetMapping("/{employeeID}")
    public ResponseEntity<EmployeeDTO> getEmployeeByID(@PathVariable(name = "employeeID") Long id){
        Optional<EmployeeDTO> employeeDTO = employeeServices.getEmployeeByID(id);
        return employeeDTO.map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElseThrow(()->new ResourceNotFoundException("Employee not found with the id: " + id));
               // .orElseThrow(()->new NoSuchElementException("Employee is not found"));
    }

//    @ExceptionHandler(NoSuchElementException.class) //all the NoSuchElementException in the class EmployeeController is handle by this ExceptionHandler as it will catch that exception and pass into the function
//    public String handleEmployeeNotFound(NoSuchElementException exception){
//        return "employee not found";
//    }
//    @ExceptionHandler(NoSuchElementException.class) //all the NoSuchElementException in the class EmployeeController is handle by this ExceptionHandler as it will catch that exception and pass into the function
//    public ResponseEntity<String> handleEmployeeNotFound(NoSuchElementException exception){
//        return new ResponseEntity<>("Employee not found in DB" , HttpStatus.NOT_FOUND);
//    }


//    @GetMapping
//    public List<EmployeeEntity> getAllEmployees(@RequestParam(name = "sortByName",required = false) String name ){
//        return employeeServices.getAllEmployees();
//    }
//    @GetMapping
//    public List<EmployeeDTO> getAllEmployees(@RequestParam(name = "sortByName",required = false) String name ){
//        return employeeServices.getAllEmployees();
//    }
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(name = "sortByName",required = false) String name ){
        return ResponseEntity.ok(employeeServices.getAllEmployees());
    }

//    @PostMapping
//    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity employeeEntity){
//        return employeeServices.createNewEmployee(employeeEntity);
//    }
//    @PostMapping
//    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO employeeDTO){  //as we also accept the request body in the form of the DTO from the user as the user do not know what is are the fields actually storing in the DB
//        return employeeServices.createNewEmployee(employeeDTO);
//    }
    // in this case lombok creates issue as serializing employeeDTO into JSON by jackson it fails and getting 406 not acceptable output , because jackson not find getter method by lombok  so either use own getter setter(but in this case getting null in the value of response) or config lombok in pom.xml by version

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO employeeDTO){  //as we also accept the request body in the form of the DTO from the user as the user do not know what is are the fields actually storing in the DB
        EmployeeDTO savedEmployee = employeeServices.createNewEmployee(employeeDTO);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

//    @PutMapping("/{employeeID}")
//    public EmployeeDTO updateEmployeeById(@PathVariable Long employeeID , @RequestBody AddEmployeeDTO addEmployeeDTO){
//        return employeeServices.updateEmployeeById(employeeID,addEmployeeDTO);
//    }
//    @PutMapping("/{employeeID}")
//    public EmployeeDTO updateEmployeeById(@PathVariable Long employeeID , @RequestBody EmployeeDTO employeeDTO){
//        return employeeServices.updateEmployeeById(employeeID,employeeDTO);
//    }
    //Put - so here if the employee of the particular ID is present then it updates(change) the entire employee by the provided request body , if employee of the ID not present it can not create a new employee of that id. for creating we use post mapping
    @PutMapping("/{employeeID}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@PathVariable Long employeeID , @RequestBody @Valid EmployeeDTO employeeDTO){
        EmployeeDTO updatedEmployee = employeeServices.updateEmployeeById(employeeID,employeeDTO);
        if(updatedEmployee == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updatedEmployee);
    }

//    @DeleteMapping("/{employeeID}")
//    public boolean deleteEmployeeById(@PathVariable Long employeeID){
//        return employeeServices.deleteEmployeeById(employeeID);
//    }
    @DeleteMapping("/{employeeID}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeID){
        boolean gotDeleted =  employeeServices.deleteEmployeeById(employeeID);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

//    @PatchMapping(path = "/{employeeID}")
//    public EmployeeDTO updatePartialEmployeeById(@PathVariable Long employeeID, @RequestBody Map<String,Object> updates){
//        return employeeServices.updatePartialEmployeeById(employeeID,updates);
//    }
    @PatchMapping(path = "/{employeeID}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@PathVariable Long employeeID, @RequestBody Map<String,Object> updates){

        EmployeeDTO partialUpdateEmployee = employeeServices.updatePartialEmployeeById(employeeID,updates);
        if (partialUpdateEmployee == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(partialUpdateEmployee);
    }


}
