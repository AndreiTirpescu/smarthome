package com.mysmarthome.eventstore.application.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static String identityFromContext() {
        var principal = new Gson().toJson(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        var jsonElement = new Gson().fromJson(principal, JsonObject.class);

        return jsonElement.get("id").getAsJsonObject().get("id").getAsString();
    }
}
