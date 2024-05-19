package com.mysmarthome.devicecatalog.infrastructure.clients;

import com.mysmarthome.devicecatalog.infrastructure.dtos.FileUploadResponse;
import com.mysmarthome.exceptions.SmartHomeException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class StaticServerClient {

    private final WebClient staticServerWebClient;

    @Value("${static.server.address}")
    private String baseUrl;

    public StaticServerClient(@Qualifier("staticServerWebClient") WebClient staticServerWebClient) {
        this.staticServerWebClient = staticServerWebClient;
    }

    public FileUploadResponse uploadFile(@RequestParam("file") MultipartFile file) {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("file", file.getResource());

        var result = staticServerWebClient.post()
                .uri("/static/upload")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .exchangeToMono(response -> response.bodyToMono(FileUploadResponse.class))
                .block();

        if (result == null) {
            throw new RuntimeException("Internal Server Error");
        }

        return new FileUploadResponse(result.name(), String.format("%s%s", baseUrl, result.url()));
    }
}
