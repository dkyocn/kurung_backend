package com.kurung;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class KurungBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(KurungBackendApplication.class, args);
    }

}
