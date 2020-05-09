package com.example.web.controller;

import com.example.web.model.ResourceModel;
import com.example.web.resource.ApplicationResource;
import com.example.web.resource.ProducerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Named
@Path("/tailor")
@RestController
public class ResourceController {

    Logger logger = LoggerFactory.getLogger(ApplicationResource.class);

    @Autowired
    public ProducerResource pr;

    @GET
    @Path("/model")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getData() throws IOException {

        logger.info("Get data method accessed");

        ResourceModel a1 = new ResourceModel();
        a1.setPhone_Number("9560779836");
        a1.setMessage("Hi Jyoti! Good to see you");

        pr.publish(a1);
        return Response.ok("Successful").build();
    }
}