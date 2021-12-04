package com.example.jdbc;

import com.example.jdbc.service.impl.StudentDAOImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@SpringBootApplication
public class JdbcApplication {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("jdbcConfig.xml");
		StudentDAOImpl service = context.getBean("studentDao", StudentDAOImpl.class);
		service.run();
	}

}
