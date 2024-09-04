package com.neoflex.vacationpayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class VacationPayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VacationPayServiceApplication.class, args);
    }
}
