package com.example.springprojectexample.service;

import com.example.springprojectexample.entity.Admission;
import com.example.springprojectexample.pojo.AdmissionDetails;
import com.example.springprojectexample.repository.AdmissionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdmissionService {
    @Autowired
    private AdmissionRepository admissionRepository;

    public AdmissionDetails addNewAdmission(AdmissionDetails admissionDetails){
        Admission admission=new Admission();
        BeanUtils.copyProperties(admissionDetails,admission);

        admission=admissionRepository.save(admission);
        BeanUtils.copyProperties(admission,admissionDetails);

        return admissionDetails;
    }

}
