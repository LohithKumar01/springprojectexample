package com.example.springprojectexample.controller;

import com.example.springprojectexample.pojo.AdmissionDetails;
import com.example.springprojectexample.service.AdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdmissionController {

    @Autowired
    private AdmissionService admissionService;

    @PostMapping("/admission")
    public AdmissionDetails addNewAdmission(@RequestBody final AdmissionDetails admissionDetails){
        return admissionService.addNewAdmission(admissionDetails);
    }
}
