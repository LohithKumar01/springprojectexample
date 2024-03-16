package com.example.springprojectexample.service;

import com.example.springprojectexample.entity.Admission;
import com.example.springprojectexample.entity.Patient;
import com.example.springprojectexample.pojo.AdmissionDetails;
import com.example.springprojectexample.pojo.PatientDetails;
import com.example.springprojectexample.repository.AdmissionRepository;
import com.example.springprojectexample.repository.PatientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdmissionService {
    @Autowired
    private AdmissionRepository admissionRepository;
    @Autowired
    private PatientRepository patientRepository;

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

    public List<Admission> getAdmissionsDetailsByPatientId(Long patientId) {
        List<Admission> admissionsList = admissionRepository.getAdmissionsByPatientId(patientId);

        for (Admission admission: admissionsList){
            Long admissionId= admission.getId();
            LocalDate admissionDate = admission.getAdmissionDate();
            LocalDate dischargeDate = admission.getDischargeDate();
            String diagnosis = admission.getDiagnosis();
            Long doctorId = admission.getDoctorId();
        }
        return admissionsList;
    }

}
