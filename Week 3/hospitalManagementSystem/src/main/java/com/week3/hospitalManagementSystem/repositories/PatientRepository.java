package com.week3.hospitalManagementSystem.repositories;

import com.week3.hospitalManagementSystem.dto.BloodGroupStats;
import com.week3.hospitalManagementSystem.dto.CPatientInfo;
import com.week3.hospitalManagementSystem.dto.IPatientInfo;
import com.week3.hospitalManagementSystem.entities.Patient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    List<Patient> findByEmailContaining(String e);

    //? now by help of spring data jpa write queries that return specific field and get that data as a List of PatientInfo interface

    @Query("select p.id as id, p.name as name, p.email as email from Patient p")
    List<IPatientInfo> getAllPatientInfo();
    //* the proxy class of the IPatientInfo class will be created by Spring Boot and populate all the fields and by the help of methods we can access the data.


    @Query("select new com.week3.hospitalManagementSystem.dto.CPatientInfo(p.id, p.name) "  + "from Patient p")
    List<CPatientInfo> getAllPatientInfoConcrete();

    //* earlier we was using ModelMapper to convert object into DTO class objects now in the actual projection way where we explicitly define our query and required fields we have to create object on the query by passing DTO class path reference
    //* in model mapper way we fetch all the fields as a PatientEntity and then define DTO with required fields and then do mapping

    //? adding extra field which is not present in the table like : count(*)

    @Query("select new com.week3.hospitalManagementSystem.dto.BloodGroupStats(p.bloodGroup, " + "COUNT(p)) from Patient p group by p.bloodGroup order by COUNT(p)")
    List<BloodGroupStats> getBloodGroupStats();

    //? this is update query return number of row affected

    @Transactional
    @Modifying // this is let the data jpa that the query is modifying the table
    @Query("update Patient p set p.name = :name where p.id = :id")
    int updatePatientNameWithId(@Param("name") String name, @Param("id") Long id);
}
