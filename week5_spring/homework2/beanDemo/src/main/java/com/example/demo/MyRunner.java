package com.example.demo;

import com.example.demo.pojo.Printer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements ApplicationRunner {
    @Autowired
    @Qualifier(value = "printerA")
    private Printer greatPrinter;

    @Autowired
    @Qualifier(value = "printerB")
    private Printer normalPrinter;

    @Override
    public void run(ApplicationArguments args) {
        assembleBeanByXml();
        assembleBeanByBean();
    }

    private void assembleBeanByBean() {
        System.out.println("======正在通过Annotation装配Bean...======");
        greatPrinter.print();
        normalPrinter.print();
        System.out.println("======装配完成======");
    }

    private static void assembleBeanByXml() {
        System.out.println("======正在通过xml装配Bean...======");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("PrinterContext.xml");
        Printer printerA = (Printer) context.getBean("printerA");
        printerA.print();
        Printer printerB = (Printer) context.getBean("printerB");
        printerB.print();
        System.out.println("======装配完成======");
    }
}
