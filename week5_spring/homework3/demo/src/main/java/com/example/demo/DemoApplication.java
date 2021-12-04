package com.example.demo;

import com.example.demo.model.Klass;
import com.example.demo.model.School;
import com.example.demo.model.Student;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoApplication {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context
				= new ClassPathXmlApplicationContext("applicationContext.xml");

		School school = context.getBean("school", School.class);
		System.out.println("学校名称: + " + school.getName());

		for (Klass klass : school.getKlasses()) {
			System.out.println("班级名称: " + klass.getName());
			for (Student student : klass.getStudents()) {
				System.out.println("学生姓名: " + student.getName() + ", 年龄: " + student.getAge());
			}
		}
	}

}
