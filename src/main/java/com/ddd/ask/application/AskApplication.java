package com.ddd.ask.application;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AskApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(AskApplication.class).web(false).run(args);
    }
}
