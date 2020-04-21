package com.example.web.configuration;

import com.example.web.resource.ApplicationResource;
import org.springframework.context.annotation.Configuration;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Configuration
//@ApplicationPath("api")
public class ApplicationConfig extends ResourceConfig {
    public ApplicationConfig(){ register(ApplicationResource.class); }

}
