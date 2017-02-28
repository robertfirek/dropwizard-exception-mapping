package org.firek.mappers;

import org.firek.mappers.responses.ErrorResponse;
import org.hibernate.exception.GenericJDBCException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import static javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE;

public class GenericJDBCExceptionMapper implements ExceptionMapper<GenericJDBCException> {
    @Override
    public Response toResponse(GenericJDBCException exception) {
        return Response
                .status(SERVICE_UNAVAILABLE)
                .entity(new ErrorResponse("JDBC exception"))
                .build();
    }
}
