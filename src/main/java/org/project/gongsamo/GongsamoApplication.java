package org.project.gongsamo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GongsamoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GongsamoApplication.class, args);
    }

}
