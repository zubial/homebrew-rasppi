package com.homebrew.rest.helper;

import com.homebrew.enums.ErrorHttpEnum;
import com.homebrew.exception.BaseException;
import com.homebrew.exception.NoDataException;
import com.homebrew.exception.PersistanceException;
import com.homebrew.exception.ServiceException;
import com.homebrew.rest.type.ErrorType;
import com.homebrew.rest.type.ResponseType;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;

public class RestResponseHelper {

    private static final Logger LOGGER = Logger.getLogger(RestResponseHelper.class);

    private RestResponseHelper() {
        // hidden constructor
    }


    public static Response createResponseIsAlive() {
        ResponseType response = new ResponseType();
        response.setDone(true);
        response.setMessage("I'm alive !");

        return Response.status(Response.Status.OK).entity(response).build();
    }


    public static Response createResponseOk(Object entity) {
        return Response.status(Response.Status.OK).entity(entity).build();
    }

    public static Response createResponseException(Exception e) {
        if (isBadRequest(e)) {
            LOGGER.error(((BaseException) e).getErrorCode() + " : " + ((BaseException) e).getErrorMessage());
            return createResponse(Response.Status.BAD_REQUEST, e);

        } else if (isInternalServerError(e)) {
            LOGGER.fatal(((BaseException) e).getErrorCode() + " : " + ((BaseException) e).getErrorMessage());
            return createResponse(Response.Status.INTERNAL_SERVER_ERROR, e);

        } else {
            LOGGER.fatal(e.getMessage(), e);
            return createResponse(Response.Status.INTERNAL_SERVER_ERROR, e);
        }
    }

    private static boolean isInternalServerError(Exception e) {
        return e instanceof PersistanceException;
    }

    private static boolean isBadRequest(Exception e) {
        return e instanceof ServiceException
                || e instanceof NoDataException;
    }

    private static Response createResponse(Response.Status status, Exception e) {
        ErrorType errorType = new ErrorType();
        errorType.setStatus(status.getStatusCode());

        if (e instanceof BaseException) {
            errorType.setCode(((BaseException) e).getErrorCode());
            errorType.setMessage(((BaseException) e).getErrorMessage());
        } else {
            errorType.setCode("-1");
            String message = e.getMessage();
            if (message == null) {
                ErrorHttpEnum error = ErrorHttpEnum.findById(errorType.getStatus());
                if (error != null)
                    message = error.label();
            }
            errorType.setMessage(message);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.fatal(e.getMessage(), e);
            errorType.setDeveloperMessage(ExceptionUtils.getStackTrace(e));
        }

        return Response.status(status).entity(errorType).build();
    }
}
