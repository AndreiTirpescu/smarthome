package com.mysmarthome.web.exceptions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVParser;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Configuration
public class SmartHomeExceptionTranslator {
    private Map<String, ErrorResponse> exceptionMap;

    public SmartHomeExceptionTranslator() {
        this.exceptionMap = new HashMap<>();
    }

    @SneakyThrows
    @PostConstruct
    public void loadExceptionsOnStartup() {
        exceptionMap = new HashMap<>();
        var resource = getClass().getClassLoader().getResource("exceptions.csv");

        if (resource == null) {
            throw new RuntimeException("Invalid configuration");
        }

        try (Stream<String> lines = java.nio.file.Files.lines(Path.of(resource.getPath()))) {
            lines.forEach(line -> {
                var errorResp = parseTranslationRow(line);
                exceptionMap.put(errorResp.key(), errorResp);
            });
        }
    }

    @SneakyThrows
    private ErrorResponse parseTranslationRow(String line) {
        var row = new CSVParser().parseLine(line);
        Type listType = new TypeToken<List<ErrorDetails>>(){}.getType();
        List<ErrorDetails> detailList = new Gson().fromJson(row[2], listType);

        return new ErrorResponse(row[0].toLowerCase(), HttpStatus.valueOf(row[1]).value(), null, detailList);
    }

    @Bean("exceptionMap")
    @Scope("singleton")
    public Map<String, ErrorResponse> exceptionMap() {
        return exceptionMap;
    }
}
