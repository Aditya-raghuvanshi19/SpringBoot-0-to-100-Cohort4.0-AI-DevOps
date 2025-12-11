package com.week3.hospitalManagementSystem;

import com.week3.hospitalManagementSystem.dto.BloodGroupStats;
import com.week3.hospitalManagementSystem.dto.CPatientInfo;
import com.week3.hospitalManagementSystem.dto.IPatientInfo;
import com.week3.hospitalManagementSystem.entities.Patient;
import com.week3.hospitalManagementSystem.repositories.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testPatient(){
       // List<Patient> patientList = patientRepository.findAll();
       // List<IPatientInfo> patientInfoList = patientRepository.getAllPatientInfo(); // this list is read only we can not modify data as the proxy class will create of the interface , their is no any concrete class
//        List<CPatientInfo> cPatientInfos = patientRepository.getAllPatientInfoConcrete();  // here is concrete class and we can do whatever we want
//        List<BloodGroupStats> bloodGroupStatsList = patientRepository.getBloodGroupStats();
//        for(BloodGroupStats p:bloodGroupStatsList){
//            System.out.println(p.getId() + " " + p.getName() + " " + p.getEmail());
//            System.out.println(p);
//        }

        int rowsAffected = patientRepository.updatePatientNameWithId("Aditya" , 1L);
        System.out.println(rowsAffected);
    }


}
