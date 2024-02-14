package com.mysmarthome.identityandaccess.application.dtos;

public record TokenResponse(String accessToken, String refreshToken) {
}
