package com.ddd.ask.presentation;

import com.ddd.ask.application.configuration.ApplicationConfiguration;
import com.ddd.ask.presentation.configuration.ApiConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ApplicationConfiguration.class, ApiConfiguration.class})
public class AskWebService {

    public static void main(String[] args) {
        new SpringApplicationBuilder(AskWebService.class).run(args);
    }
}
