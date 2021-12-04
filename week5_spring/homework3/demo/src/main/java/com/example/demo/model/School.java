package com.example.demo.model;

import lombok.Data;

import java.util.List;

@Data
public class School {
    private String name;

    private List<Klass> klasses;
}
