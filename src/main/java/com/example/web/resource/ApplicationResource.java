package com.example.web.resource;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Named
@Path("/tailor")
public class ApplicationResource {

    private static final String Phone_Number = "phoneNumber";
    @GET
    @Path("/{phoneNumber}/customer_details")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerDetails(@PathParam(Phone_Number) String ph_No){
        //return Response.status(Response.Status.ACCEPTED).build();
        return Response.ok(ph_No).build();
    }
}
