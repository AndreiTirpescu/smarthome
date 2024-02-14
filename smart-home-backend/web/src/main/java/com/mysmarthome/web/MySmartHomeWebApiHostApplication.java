package com.mysmarthome.web;

import com.mysmarthome.identityandaccess.application.EnableIdentityAndAccessModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableIdentityAndAccessModule
public class MySmartHomeWebApiHostApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySmartHomeWebApiHostApplication.class);
    }
}
