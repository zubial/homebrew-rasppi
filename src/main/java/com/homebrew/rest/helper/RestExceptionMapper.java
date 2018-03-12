package com.homebrew.rest.helper;

import com.homebrew.enums.ErrorHttpEnum;
import com.homebrew.rest.type.ErrorType;
import org.apache.log4j.Logger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.io.PrintWriter;
import java.io.StringWriter;

@Provider
public class RestExceptionMapper implements ExceptionMapper<Throwable> {

    private static final String DEFAULT_ERROR_CODE = "-1";
    private static final Logger LOGGER = Logger.getLogger(RestExceptionMapper.class);

    @Override
    public Response toResponse(Throwable ex) {

        ErrorType errorType = new ErrorType();

        setHttpStatus(ex, errorType);

        errorType.setCode(DEFAULT_ERROR_CODE);

        String message = ex.getMessage();
        if (message == null) {
            ErrorHttpEnum error = ErrorHttpEnum.findById(errorType.getStatus());
            if (error != null)
                message = error.label();
        }
        errorType.setMessage(message);

        if (LOGGER.isDebugEnabled()) {
            StringWriter errorStackTrace = new StringWriter();
            ex.printStackTrace(new PrintWriter(errorStackTrace));
            errorType.setDeveloperMessage(errorStackTrace.toString());
        }

        return Response.status(errorType.getStatus()).entity(errorType).type(MediaType.APPLICATION_JSON).build();
    }

    private void setHttpStatus(Throwable ex, ErrorType errorType) {
        if (ex instanceof WebApplicationException) {
            errorType.setStatus(((WebApplicationException) ex).getResponse().getStatus());
        } else {
            errorType.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        }
    }
}
