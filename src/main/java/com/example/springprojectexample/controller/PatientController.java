package com.example.springprojectexample.controller;

import com.example.springprojectexample.pojo.PatientDetails;
import com.example.springprojectexample.service.PatientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping("/patient/{id}")
    public PatientDetails getPatient(@PathVariable("id") Long id) {
        log.info("Patient Record Found: {}", id);
        return patientService.getPatient(id);
    }

    @DeleteMapping("/patient")
    public String deletePatient(@RequestParam("id") int id) {
        System.out.println(id);
        return "Record Deleted";            //url: /patient?id=110
    }

    @PostMapping("/patient")
    public PatientDetails createPatient(@RequestBody PatientDetails patient) {
        log.info("New Patient Details: {}", patient);
        return patientService.createPatient(patient);
    }

    @PutMapping("/patient/{id}")
    public PatientDetails updatePatient(@PathVariable("id") int id, @RequestBody PatientDetails patient) {
        return patient;
    }
}