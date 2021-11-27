package com.gmail.elbaglikov.palmetto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PalmettotApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PalmettotApplication.class, args);
    }
}
