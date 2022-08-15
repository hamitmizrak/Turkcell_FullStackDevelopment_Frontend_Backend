package com.hamitmizrak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@SpringBootApplication
//// websecurity exclude
@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class
})

//Auditing
@EnableJpaAuditing(auditorAwareRef = "auditorAwareMethod")
public class TurkcellFullStackDevelopmentApplication {

    //PSVM
    public static void main(String[] args) {
        //devtool enabled tru
        System.setProperty("spring.devtools.restart.enabled", "true");

        //JOptionalPane calisamasi icin
        System.setProperty("java.awt.headless", "false"); // Disables headless

        //Main Running
        SpringApplication.run(TurkcellFullStackDevelopmentApplication.class, args);
    }

    // @Bean yazma yeri
}
