package dev.kakao5.eyestalkdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class EyesTalkDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(EyesTalkDbApplication.class, args);
    }

}
