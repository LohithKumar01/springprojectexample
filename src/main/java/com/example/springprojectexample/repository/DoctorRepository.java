package com.example.springprojectexample.repository;

import com.example.springprojectexample.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {

//    @Modifying
//    @Transactional
//    @Query("DELETE FROM Doctor d WHERE d.doctor.id = ?1")
//    public void deleteDoctorById(Long doctorId);
}
