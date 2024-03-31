package com.example.springprojectexample.service;

import com.example.springprojectexample.client.PatientClient;
import com.example.springprojectexample.entity.Patient;
import com.example.springprojectexample.pojo.AdmissionDetails;
import com.example.springprojectexample.pojo.PatientCreditDetails;
import com.example.springprojectexample.pojo.PatientDetails;
import com.example.springprojectexample.repository.PatientRepository;
import com.example.springprojectexample.validation.PatientValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;        //Controller -> when url hits it goes to controller.
    @Autowired
    private AdmissionService admissionService;
    @Autowired
    private PatientClient patientClient;
    @Autowired
    private PatientValidator patientValidator;

    public PatientDetails createPatient(PatientDetails patient) {
        patientValidator.validatePatientRequest(patient);   //Need to validate before inserting the request data

        Patient newPatient = new Patient();
        newPatient.setFirstName(patient.getFirstName());
        newPatient.setLastName(patient.getLastName());
        newPatient.setGender(patient.getGender());
        newPatient.setBirthDate(patient.getBirthDate());
        newPatient.setCity(patient.getCity());
        newPatient.setProvinceId(patient.getProvinceId());
        newPatient.setAllergies(patient.getAllergies());
        newPatient.setHeight(patient.getHeight());
        newPatient.setWeight(patient.getWeight());

        Patient savedPatient = patientRepository.save(newPatient);  //Saving Created data to Repository

        PatientCreditDetails newPatientDetails = new PatientCreditDetails();
        newPatientDetails.setId(savedPatient.getId());
        newPatientDetails.setSsn(patient.getSsn());
        newPatientDetails.setCreditScore(patient.getCreditScore());

        patientClient.createPatientDetails(newPatientDetails);
        PatientDetails patientDetails=new PatientDetails();
        BeanUtils.copyProperties(savedPatient,patientDetails);
        patientDetails.setSsn(newPatientDetails.getSsn());
        patientDetails.setCreditScore(newPatientDetails.getCreditScore());
        return patientDetails;
    }

    public PatientDetails getPatient(Long id) {
        Patient patientById = patientRepository.findById(id).orElse(null);  //Connect to repository to get details.
        if (patientById == null) {
            throw new RuntimeException("No Patient Found.");        //If id not found throw exception.
        }
        PatientDetails patientDetails = new PatientDetails();     //Copying Entity properties to Pojo
        BeanUtils.copyProperties(patientById, patientDetails);

        List<AdmissionDetails> admissionsDetailsByPatientIdList = admissionService.getAdmissionsDetailsByPatientId(id);
        //Set Admission details list in pojo with  admission details by patient id list
        patientDetails.setAdmissionDetailsList(admissionsDetailsByPatientIdList);
        PatientCreditDetails details = patientClient.getPatientDetails(id);
        patientDetails.setSsn(details.getSsn());
        patientDetails.setCreditScore(details.getCreditScore());

        return patientDetails;          //We can't return entity so, we create new pojo object and return that.
    }


}
