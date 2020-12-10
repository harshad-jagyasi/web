package com.example.web.resource;

import com.example.web.dao.DbRepository;
import com.example.web.dao.TransactionalDbUserDao;
import com.example.web.entity.DbUser;
import com.example.web.entity.CustomerPersonalDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Named
@Path("/tailor")
public class ApplicationResource {

    @Autowired
    private DbRepository dbRepository;

    @Inject
    private TransactionalDbUserDao transactionalDbUserDao;

    private static final String Phone_Number = "phoneNumber";
    @GET
    @Path("/{phoneNumber}/customer_details")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerDetails(@PathParam(Phone_Number) String ph_No){
        //return Response.status(Response.Status.ACCEPTED).build();
        final List<DbUser> usersAll = transactionalDbUserDao.getAllUser();
        return Response.ok(ph_No).build();
    }

    @POST
    @Path("/put_personalDetails")
    @Produces(MediaType.APPLICATION_JSON)
        public Response putPersonalDetails(@RequestBody CustomerPersonalDetails customerPersonalDetails){
        Integer id = customerPersonalDetails.getId();
        String name = customerPersonalDetails.getName();
        String email = customerPersonalDetails.getEmail();

        DbUser user = new DbUser();
        user.setName(name);
        user.setEmail(email);
        dbRepository.save(user);
        return Response.ok("Personal details saved to database").build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<DbUser> getAllUsers() {
        // This returns a JSON or XML with the users
        return dbRepository.findAll();
    }

}
