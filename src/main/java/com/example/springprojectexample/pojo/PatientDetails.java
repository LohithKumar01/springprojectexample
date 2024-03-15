package com.example.springprojectexample.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class PatientDetails {
    private Integer patient_id;
    private String first_name;
    private String last_name;
    private String gender;
    private LocalDate birth_date;
    private String city;
    private String province_id;
    private String allergies;
    private Integer height;
    private Integer weight;
}
