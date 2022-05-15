package com.melody.druidsql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
//@ServletComponentScan("com.melody.druidsql.filter")
public class DruidSqlApplication {
    public static void main(String[] args) {
        SpringApplication.run(DruidSqlApplication.class, args);
    }
}