package com.homebrew.rest;

import com.homebrew.rest.helper.RestResponseHelper;
import com.homebrew.service.gpio.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Controller
@Path("/gpioAnalog")
@Produces(value = "application/json")
public class GpioAnalogRestImpl extends SpringBeanAutowiringSupport {

    private static final Logger LOGGER = Logger.getLogger(GpioAnalogRestImpl.class);

    @Autowired
    private IGpioAnalogShutdown cmdAnalogShutdown;

    @Autowired
    private IGpioGetAnalogPinShortValue cmdGetAnalogPinShortValue;

    @Autowired
    private IGpioGetAllAnalogPinShortValue cmdGetAllAnalogPinShortValue;

	@GET
    @Path("isAlive")
    public Response isAlive() {
        try {
            return RestResponseHelper.createResponseIsAlive();
        } catch (final Exception e) {
            return RestResponseHelper.createResponseException(e);
        }
    }

    @GET
    @Path("shutdown")
    public Response analogShutdown() {
        try {
            return RestResponseHelper.createResponseOk(cmdAnalogShutdown.process());

        } catch (Exception e) {
            return RestResponseHelper.createResponseException(e);
        }
    }

    @GET
    @Path("{pinIndex}/getPinShortValue")
    public Response analogGetPinShortValue(@PathParam("pinIndex") Integer pinIndex) {
        try {
            return RestResponseHelper.createResponseOk(cmdGetAnalogPinShortValue.process(pinIndex));

        } catch (Exception e) {
            return RestResponseHelper.createResponseException(e);
        }
    }

    @GET
    @Path("getAllAnalogPinShortValue")
    public Response getAllAnalogPinShortValue() {
        try {
            return RestResponseHelper.createResponseOk(cmdGetAllAnalogPinShortValue.process());

        } catch (Exception e) {
            return RestResponseHelper.createResponseException(e);
        }
    }
}