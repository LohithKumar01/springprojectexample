package com.example.springprojectexample.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Patient {
    private int id;
    private String name;
    private String city;
    private String allergies;
}
