package ru.prokurornsk.germes.app.rest.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.prokurornsk.germes.app.infra.exception.flow.ValidationException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Jersey exception handler that catches validation errors
 *
 * @author ProkurorNSk
 *
 */

@Provider
public class ValidationExceptionHandler implements ExceptionMapper<ValidationException> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationExceptionHandler.class);

    @Override
    public Response toResponse(ValidationException exception) {
        LOGGER.error(exception.getMessage(), exception);
        return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
    }
}
