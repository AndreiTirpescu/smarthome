package com.mysmarthome.homesystemmanagement.application.addhomesystem;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/homesystems")
@Tag(name = "home-systems")
public class ConnectHomeSystemCommandHandler {
}
