package com.mysmarthome.homesystemmanagement.application.dtos;

public record HomeSystemResponse(String id,
                                 String identityId,
                                 String name,
                                 String description,
                                 HomeSystemAddress address) {
}
