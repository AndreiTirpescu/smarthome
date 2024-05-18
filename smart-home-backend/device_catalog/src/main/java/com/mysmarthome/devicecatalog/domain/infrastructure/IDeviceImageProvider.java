package com.mysmarthome.devicecatalog.domain.infrastructure;

import org.springframework.web.multipart.MultipartFile;

public interface IDeviceImageProvider {
    String uploadImageForDevice(MultipartFile file);
}
