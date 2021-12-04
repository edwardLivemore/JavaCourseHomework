package com.example.demo.model;

import lombok.Data;

import java.util.List;

@Data
public class Klass {
    private String name;

    private List<Student> students;
}
