package com.sk02.sk02_eureka_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Sk02EurekaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sk02EurekaServiceApplication.class, args);
    }

}
