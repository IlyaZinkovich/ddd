package com.ddd.ask.presentation.configuration;

import com.ddd.ask.application.service.query.commands.CreateQueryCommand;
import com.ddd.ask.presentation.query.converters.CreateQueryCommandJsonConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfiguration {

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .registerTypeAdapter(CreateQueryCommand.class, new CreateQueryCommandJsonConverter())
                .create();
    }
}
