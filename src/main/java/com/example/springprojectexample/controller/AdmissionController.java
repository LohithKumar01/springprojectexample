package com.example.springprojectexample.controller;

import com.example.springprojectexample.entity.Admission;
import com.example.springprojectexample.pojo.AdmissionDetails;
import com.example.springprojectexample.service.AdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdmissionController {

    @Autowired
    private AdmissionService admissionService;

    @PostMapping("/admission")
    public AdmissionDetails addNewAdmission(@RequestBody final AdmissionDetails admissionDetails){
        return admissionService.addNewAdmission(admissionDetails);
    }
    @GetMapping("/admission/{id}")
    public List<Admission> getAdmissionsDetailsByPatientId(@PathVariable("id") Long patientId) {
        return admissionService.getAdmissionsDetailsByPatientId(patientId);
    }
}
