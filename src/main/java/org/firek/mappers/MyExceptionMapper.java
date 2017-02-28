package org.firek.mappers;

import org.firek.exceptions.MyException;
import org.firek.mappers.responses.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import static javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE;

public class MyExceptionMapper implements ExceptionMapper<MyException> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public Response toResponse(MyException exception) {
        logger.error(exception.getMessage(), exception);
        return Response
                .status(SERVICE_UNAVAILABLE)
                .entity(new ErrorResponse("My exception"))
                .build();
    }
}
