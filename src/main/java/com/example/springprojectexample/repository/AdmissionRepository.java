package com.example.springprojectexample.repository;

import com.example.springprojectexample.entity.Admission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionRepository extends JpaRepository<Admission,Long> {
}