package com.example.springprojectexample.service;

import com.example.springprojectexample.entity.Admission;
import com.example.springprojectexample.entity.Doctor;
import com.example.springprojectexample.pojo.AdmissionDetails;
import com.example.springprojectexample.pojo.DoctorDetails;
import com.example.springprojectexample.repository.AdmissionRepository;
import com.example.springprojectexample.repository.DoctorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private AdmissionRepository admissionRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AdmissionService admissionService;

    public DoctorDetails getDoctorDetailsById(Long doctorId) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        if (!doctorOptional.isPresent()){
            throw new RuntimeException("Doctor Record Not found.");
        }
        Doctor doctor = doctorOptional.get();
        DoctorDetails doctorDetails = new DoctorDetails();
        BeanUtils.copyProperties(doctor, doctorDetails);

        List<Admission> admissionList = admissionRepository.getAdmissionDetailsByDoctorId(doctorId);
        List<AdmissionDetails> admissionDetailsList = new ArrayList<>();
        for (Admission admission : admissionList) {
            AdmissionDetails admissionDetails = new AdmissionDetails();
            BeanUtils.copyProperties(admission, admissionDetails);
            admissionDetailsList.add(admissionDetails);
        }
        doctorDetails.setAdmissionDetailsList(admissionDetailsList);
        return doctorDetails;
    }

    public void deleteDoctorById(Long doctorId) {
        doctorRepository.deleteById(doctorId);
    }

    public DoctorDetails updateDoctor(DoctorDetails doctorDetails) {
        Doctor existingDoctorRecord = doctorRepository.findById(doctorDetails.getId()).orElse(null);
        if (existingDoctorRecord == null) {
            throw new RuntimeException("Invalid Doctor Id");
        }
        existingDoctorRecord.setFirstName(doctorDetails.getFirstName());
        existingDoctorRecord.setLastName(doctorDetails.getLastName());
        existingDoctorRecord.setSpecialty(doctorDetails.getSpecialty());

        Doctor updatedDoctorDetails = doctorRepository.save(existingDoctorRecord);

        DoctorDetails updatedDoctorRecord = new DoctorDetails();
        BeanUtils.copyProperties(updatedDoctorDetails, updatedDoctorRecord);

        return updatedDoctorRecord;
    }


}
