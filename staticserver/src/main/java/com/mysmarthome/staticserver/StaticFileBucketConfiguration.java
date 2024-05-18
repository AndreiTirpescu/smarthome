package com.mysmarthome.staticserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class StaticFileBucketConfiguration {

    @Value("${static.file.bucket.path}")
    private String path;

    @Bean
    public Path bucketFilePath() {
        return Paths.get(path);
    }

}
