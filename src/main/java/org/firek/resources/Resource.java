package org.firek.resources;

import io.dropwizard.hibernate.UnitOfWork;
import org.firek.exceptions.MyException;
import org.firek.services.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
public class Resource {
    private final Service service;

    public Resource(Service service) {
        this.service = service;
    }

    @Path("/error")
    @GET()
    @UnitOfWork
    public List<ResourceResult> errorTest() {
        return service.query();
    }

    @Path("/without-error")
    @GET()
    @UnitOfWork
    public List<ResourceResult> myExceptionTest() {
        throw new MyException();
    }
}
