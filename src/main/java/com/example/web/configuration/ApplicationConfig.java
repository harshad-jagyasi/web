package com.example.web.configuration;

import com.example.web.controller.ResourceController;
import com.example.web.resource.ApplicationResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ApplicationPath("api")
public class ApplicationConfig extends ResourceConfig {
    public ApplicationConfig(){ registerClasses(ApplicationResource.class, ResourceController.class); }

}
