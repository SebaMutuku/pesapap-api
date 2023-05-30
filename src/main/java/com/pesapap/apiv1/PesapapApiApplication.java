package com.pesapap.apiv1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class PesapapApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PesapapApiApplication.class, args);
    }

}
