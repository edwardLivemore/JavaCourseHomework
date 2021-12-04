package com.example.jdbc.service;

import com.example.jdbc.model.Student;

public interface IStudentDAO {
    int add(Student student);
    int update(Student student);
    int delete(Student student);
}
