package com.example.tuyendung1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TuyenDung1Application {

    public static void main(String[] args) {
        SpringApplication.run(TuyenDung1Application.class, args);
    }

}
