package com.mysmarthome.devicecatalog.infrastructure.service;

import com.mysmarthome.devicecatalog.domain.infrastructure.IDeviceImageProvider;
import com.mysmarthome.devicecatalog.infrastructure.clients.StaticServerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class StaticServerImageProvider implements IDeviceImageProvider {

    private final StaticServerClient staticServerClient;

    @Override
    public String uploadImageForDevice(MultipartFile file) {
        return staticServerClient.uploadFile(file).url();
    }
}
