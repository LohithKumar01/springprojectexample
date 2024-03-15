package com.example.springprojectexample.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdmissionDetails {
    private Long id;
    private LocalDate admission_date;
    private LocalDate discharge_date;
    private String diagnosis;
    @Column(name = "attending_doctor_id")
    private Integer doctor_id;

    private PatientDetails patientDetails;
    private Long patient_id;
}
