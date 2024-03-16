package com.example.springprojectexample.repository;

import com.example.springprojectexample.entity.Admission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdmissionRepository extends JpaRepository<Admission,Long> {

    @Query("SELECT a FROM Admission a WHERE a.patient.id = ?1")
    public List<Admission> getAdmissionsByPatientId(Long patientId);
}