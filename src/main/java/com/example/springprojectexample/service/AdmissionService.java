package com.example.springprojectexample.service;

import com.example.springprojectexample.entity.Admission;
import com.example.springprojectexample.entity.Patient;
import com.example.springprojectexample.pojo.AdmissionDetails;
import com.example.springprojectexample.pojo.PatientDetails;
import com.example.springprojectexample.repository.AdmissionRepository;
import com.example.springprojectexample.repository.DoctorRepository;
import com.example.springprojectexample.repository.PatientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdmissionService {
    @Autowired
    private AdmissionRepository admissionRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    public AdmissionDetails addNewAdmission(AdmissionDetails admissionDetails){

        Patient existingAdmission= patientRepository.findById(admissionDetails.getPatientId()).orElse(null);
        if (existingAdmission==null) {
            throw new RuntimeException("Invalid Patient ID");
        }
        Admission newAdmission=new Admission();
        BeanUtils.copyProperties(admissionDetails,newAdmission);

        newAdmission.setPatient(existingAdmission);

        Admission createedAdmission = admissionRepository.save(newAdmission);


        PatientDetails admittedPatientDetails = new PatientDetails();
        BeanUtils.copyProperties(createedAdmission.getPatient(),admittedPatientDetails);

        AdmissionDetails newAdmissionDetails = new AdmissionDetails();
        BeanUtils.copyProperties(createedAdmission,newAdmissionDetails);

        newAdmissionDetails.setPatientDetails(admittedPatientDetails);
        return newAdmissionDetails;
    }


    public List<AdmissionDetails> getAdmissionsDetailsByPatientId(Long patientId) {
        //Get values from admission repository by patient id and saved it in a list
        List<Admission> admissionsList = admissionRepository.getAdmissionsByPatientId(patientId);
        //As we should not return list of entity we convert it into pojo list and add admission details into that list
        List<AdmissionDetails> admissionDetailsList = new ArrayList<>() ;
        //Iterate each entity object and save details into a new object.
        for (Admission admission: admissionsList){
            AdmissionDetails admissionDetails=new AdmissionDetails();
            //We have same attributes and we copy to the new object.
            BeanUtils.copyProperties(admission,admissionDetails);

            admissionDetailsList.add(admissionDetails);
        }
        return admissionDetailsList;
    }

    public void deleteAdmissionByPatientId(Long patientID){
        admissionRepository.deleteAdmissionByPatientId(patientID);
    }
}