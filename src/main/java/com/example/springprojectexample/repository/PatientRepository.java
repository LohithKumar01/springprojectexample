package com.example.springprojectexample.repository;

import com.example.springprojectexample.entity.Patient;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer>, JpaSpecificationExecutor<Patient> {
    @Query("SELECT p FROM HospitalDB p WHERE p.city=?1")
    Patient findPatientByCity(String city);

}
