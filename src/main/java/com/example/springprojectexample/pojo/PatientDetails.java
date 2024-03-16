package com.example.springprojectexample.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientDetails {
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate birthDate;
    private String city;
    private String provinceId;
    private String allergies;
    private Integer height;
    private Integer weight;
    private List<AdmissionDetails> AdmissionDetailsList;
}
