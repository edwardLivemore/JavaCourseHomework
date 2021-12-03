package com.edward.starter.entity;

import lombok.Data;

@Data
public class School implements ISchool {
    // Resource
//    @Autowired(required = true) //primary
    private Klass class1;

    private String name;

    @Override
    public void ding() {
        System.out.println("school name is : " + name);
        System.out.println("Class1 have " + this.class1.getStudents().size() + " students");
        class1.getStudents().forEach(student -> {
            System.out.println("student is : " + student.getName());
        });
    }
}
