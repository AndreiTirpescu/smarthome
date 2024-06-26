package com.mysmarthome.staticserver;

import com.mysmarthome.staticserver.dtos.FileInfoResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@RestController
@CrossOrigin("${static.file.allowed.origins}")
public class FileStoreService {

    private final Path bucketFilePath;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FileInfoResponse save(@NotNull(message = "file must not be null") @RequestParam("file") MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new RuntimeException("Invalid file");
        }

        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String newFileName = String.format("%s.%s", UUID.randomUUID(), extension);
        try {
            Files.copy(file.getInputStream(), bucketFilePath.resolve(newFileName));

            return new FileInfoResponse(originalFilename, String.format("/static/%s", newFileName));
        } catch (Exception ex) {
            log.error("Could not save file {}", ex.getMessage());

            throw new RuntimeException(ex);
        }
    }

    @GetMapping("/{filename}")
    public Resource get(@PathVariable String filename) {
        Path filePath = bucketFilePath.resolve(filename);
        if (!Files.exists(filePath)) {
            throw new RuntimeException("File not found");
        }

        try {
            var resource = new UrlResource(filePath.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                throw new RuntimeException("File not found");
            }

            return resource;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
