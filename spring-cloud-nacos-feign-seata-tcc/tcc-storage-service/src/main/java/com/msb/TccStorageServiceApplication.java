package com.msb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class TccStorageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TccStorageServiceApplication.class, args);
    }

}
