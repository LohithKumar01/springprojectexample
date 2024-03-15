package com.example.springprojectexample.service;

import com.example.springprojectexample.entity.Admission;
import com.example.springprojectexample.entity.Patient;
import com.example.springprojectexample.pojo.AdmissionDetails;
import com.example.springprojectexample.pojo.PatientDetails;
import com.example.springprojectexample.repository.PatientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;        //Controller -> when url hits it goes to controller.

    public PatientDetails createPatient(PatientDetails patient){

        int admissionId=patient.getPatient_id();
        Admission admission= patientRepository.findById(admissionId).orElse(null);
        if (admission==null){
            throw new RuntimeException("Invalid Admission");
        }

        Patient newPatient = new Patient();
        newPatient.setFirst_name(patient.getFirst_name());
        newPatient.setLast_name(patient.getLast_name());
        newPatient.setGender(patient.getGender());
        newPatient.setBirth_date(patient.getBirth_date());
        newPatient.setCity(patient.getCity());
        newPatient.setProvince_id(patient.getProvince_id());
        newPatient.setAllergies(patient.getAllergies());
        newPatient.setHeight(patient.getHeight());
        newPatient.setWeight(patient.getWeight());

        Patient savedPatient = patientRepository.save(newPatient);

        PatientDetails updatedPatient = new PatientDetails();
        BeanUtils.copyProperties(savedPatient, updatedPatient);

        AdmissionDetails admissionDetails=new AdmissionDetails();
        BeanUtils.copyProperties(admission,admissionDetails);

        updatedPatient.setAdmissionDetails(admissionDetails);
        return updatedPatient;
    }


}
