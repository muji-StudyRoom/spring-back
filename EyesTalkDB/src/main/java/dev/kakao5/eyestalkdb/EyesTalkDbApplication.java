package dev.kakao5.eyestalkdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.Locale;
import java.util.TimeZone;


@EnableJpaAuditing
@SpringBootApplication
public class EyesTalkDbApplication {

    @PostConstruct
    public void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
        Locale.setDefault(Locale.KOREA);
    }


    public static void main(String[] args) {
        SpringApplication.run(EyesTalkDbApplication.class, args);
    }

}
