package com.mysmarthome.web;

import com.mysmarthome.devicecatalog.application.EnableDeviceCatalogModule;
import com.mysmarthome.eventstore.application.EnableEventStoreModule;
import com.mysmarthome.homesystemmanagement.application.EnableHomeSystemModule;
import com.mysmarthome.identityandaccess.application.EnableIdentityAndAccessModule;
import com.mysmarthome.userprofiling.application.EnableUserProfilingModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableIdentityAndAccessModule
@EnableDeviceCatalogModule
@EnableEventStoreModule
@EnableHomeSystemModule
@EnableUserProfilingModule
public class MySmartHomeWebApiHostApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySmartHomeWebApiHostApplication.class);
    }
}
