package com.example.springprojectexample.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "admissions")
public class Admission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate admission_date;

    private LocalDate discharge_date;

    private String diagnosis;

    private Long doctor_id;


    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
