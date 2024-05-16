package com.mysmarthome.homesystemmanagement.application.addhomesystem;

import jakarta.validation.constraints.NotNull;

public record ConnectHomeSystemCommand(@NotNull String name) {
}
