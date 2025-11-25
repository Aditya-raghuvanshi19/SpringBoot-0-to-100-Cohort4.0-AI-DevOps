package com.cohort3.week2.springmvcRESTAPI.Week2L_4ServiceLayer.services;

import com.cohort3.week2.springmvcRESTAPI.Week2L_4ServiceLayer.dto.AddEmployeeDTO;
import com.cohort3.week2.springmvcRESTAPI.Week2L_4ServiceLayer.dto.EmployeeDTO;
import com.cohort3.week2.springmvcRESTAPI.Week2L_4ServiceLayer.entities.EmployeeEntity;
import com.cohort3.week2.springmvcRESTAPI.Week2L_4ServiceLayer.exceptions.ResourceNotFoundException;
import com.cohort3.week2.springmvcRESTAPI.Week2L_4ServiceLayer.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServices {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper; //to get the bean of this we have to define it as a bean so for this use config file

    public EmployeeServices(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    //    public EmployeeEntity getEmployeeByID(Long id) {
//        return employeeRepository.findById(id).orElseThrow(()->new IllegalArgumentException("no employee find by this id: "+id));
//    } //this is not secure way as we expose the actual employeeEntity to the controller side so better to use the DTO

    // 1. way by manual convert the returned employeeEntity by repository to DTO
//    public EmployeeDTO getEmployeeByID(Long id) {
//        EmployeeEntity employeeEntity= employeeRepository.findById(id).orElseThrow(()->new IllegalArgumentException("no employee find by this id: "+id));
//        return new EmployeeDTO(employeeEntity.getId(),employeeEntity.getName(),employeeEntity.getEmail(),employeeEntity.getAge(),employeeEntity.getDateOfJoining(),employeeEntity.getIsActive());
//    }

    // 2 . way is use the ModelMapper
//    public EmployeeDTO getEmployeeByID(Long id) {
//        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no employee find by this id: " + id));
//        return modelMapper.map(employeeEntity, EmployeeDTO.class);
//
//    }
    //3. by returning the optional entity , this is more professional way as in this case we are not getting
    public Optional<EmployeeDTO> getEmployeeByID(Long id) {
//        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
//        return employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity1,EmployeeDTO.class));

        return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class));
    }

    //    public List<EmployeeEntity> getAllEmployees() {
//        return employeeRepository.findAll();
//    }
//
//    public EmployeeEntity createNewEmployee(EmployeeEntity employeeEntity) {
//        return employeeRepository.save(employeeEntity);
//    }
    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        // now here we use stream API for conversion one list into another without affecting the previous one
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        EmployeeEntity savedemployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedemployeeEntity, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {

//        boolean exist = isExistsByEmployeeId(employeeId);
//        //if(!exist)return null;
//        if(!exist) throw new ResourceNotFoundException("Employee not found with the id: " + employeeId);

        isExistsByEmployeeId(employeeId);

        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }



//    public EmployeeDTO updateEmployeeById(Long employeeID, AddEmployeeDTO addEmployeeDTO) {
//        EmployeeEntity existing = employeeRepository.findById(employeeID).orElseThrow(() -> new IllegalArgumentException("Employee not found: " + employeeID));
//        modelMapper.map(addEmployeeDTO, existing);
//        EmployeeEntity saved = employeeRepository.save(existing);
//        return modelMapper.map(saved, EmployeeDTO.class);
//    } //this is most professional way to update something by the help of some other DTO, which is created only with some specified properties for which user only eligible to update.(like user can not update the id )

//    public EmployeeDTO updateEmployeeById(Long employeeID, AddEmployeeDTO addEmployeeDTO) {
//        EmployeeEntity employeeEntity = modelMapper.map(addEmployeeDTO,EmployeeEntity.class);
//        employeeEntity.setId(employeeID);
//        EmployeeEntity saved = employeeRepository.save(employeeEntity);
//       return modelMapper.map(saved,EmployeeDTO.class);
//}
//        public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
//            EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
//            employeeEntity.setId(employeeId);
//            EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
//            return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
//        }
    //above two way to update employee by PUT method is fails if we apply the logic of course that : if the employee of provided id is not present then create a new employee with that id because:
    //PUT is used only for update entire entity if any field left the value will be null and the entity then saved with the value null , and that is why we choose specific DTO and also
    // for creating a new record we choose POST method as we as a user not able to give ID .The system auto generate that if user specify the id by itself then the version issues arises the same problem is occur with PUT if we create a new employee by the specific ID
//Key problem: modelMapper.map(employeeDTO, existing) is overwriting the managed entity’s identifier with null (or changing it), so when Hibernate flushes it throws:
//
//    Identifier of an instance ... was altered from 52 to null
//    and earlier you saw
//
//    IllegalArgumentException: Employee not found: 3 (from your orElseThrow when findById returned empty).
//
//    Both are symptoms of the same mapping problem: your DTO doesn't carry the id (or has null id) and ModelMapper is copying that null into the existing EmployeeEntity, changing its identifier — Hibernate forbids changing the identifier of a managed entity.

    public boolean deleteEmployeeById(Long employeeID) {

//        boolean exist = isExistsByEmployeeId(employeeID);
//       //if(!exist)return false;
//        if(!exist) throw new ResourceNotFoundException("Employee not found with the id: " + employeeID);

        isExistsByEmployeeId(employeeID);
        employeeRepository.deleteById(employeeID);
        return  true;
    }

    public boolean isExistsByEmployeeId(Long employeeId){
        boolean exist = employeeRepository.existsById(employeeId);
        if(!exist) throw new ResourceNotFoundException("Employee not found with the id: " + employeeId);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeID, Map<String, Object> updates) {

//        boolean exist = isExistsByEmployeeId(employeeID);
//        //if(!exist)return null;
//        if(!exist) throw new ResourceNotFoundException("Employee not found with the id: " + employeeID);

        isExistsByEmployeeId(employeeID);
        EmployeeEntity employeeEntity=employeeRepository.findById(employeeID).get();

        updates.forEach((field , value) -> {
            Field fieldToUpdate = ReflectionUtils.getRequiredField(EmployeeEntity.class,field);
            // from this way we actually fetch the field in our control of that class amd ,make that public for our use
            fieldToUpdate.setAccessible(true);
            ReflectionUtils.setField(fieldToUpdate,employeeEntity,value);
        });
        return modelMapper.map(employeeRepository.save(employeeEntity) , EmployeeDTO.class);
    }
}