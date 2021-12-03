package com.example.demo.config;

import com.example.demo.pojo.Printer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrinterConfig {
    @Bean(name = "printerA")
    public Printer getGreatPrinter(){
        Printer printer = new Printer();
        printer.setId(1);
        printer.setName("greatPrinter");
        return printer;
    }

    @Bean(name = "printerB")
    public Printer getNormalPrinter(){
        Printer printer = new Printer();
        printer.setId(2);
        printer.setName("normalPrinter");
        return printer;
    }
}
