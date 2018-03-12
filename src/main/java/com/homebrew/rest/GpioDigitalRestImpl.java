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
@Path("/gpioDigital")
@Produces(value = "application/json")
public class GpioDigitalRestImpl extends SpringBeanAutowiringSupport {

    private static final Logger LOGGER = Logger.getLogger(GpioDigitalRestImpl.class);

    @Autowired
    private IGpioDigitalShutdown cmdDigitalShutdown;

    @Autowired
    private IGpioGetDigitalPinState cmdGetDigitalPinState;

    @Autowired
    private IGpioGetDigitalImpulseValue cmdGetDigitalImpulseValue;

    @Autowired
    private IGpioResetDigitalImpulseValue cmdResetDigitalImpulseValue;

    @Autowired
    private IGpioGetAllDigitalPinState cmdGetAllDigitalPinState;

    @Autowired
    private IGpioGetAllDigitalImpulseValue cmdGetAllDigitalImpulseValue;

    @Autowired
    private IGpioSetDigitalPinToggle cmdSetDigitalPinToggle;

    @Autowired
    private IGpioSetDigitalPinOn cmdSetDigitalPinOn;

    @Autowired
    private IGpioSetDigitalPinOff cmdSetDigitalPinOff;

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
    public Response digitalShutdown() {
        try {
            return RestResponseHelper.createResponseOk(cmdDigitalShutdown.process());

        } catch (Exception e) {
            return RestResponseHelper.createResponseException(e);
        }
    }

    @GET
    @Path("getAllDigitalPinState")
    public Response getAllDigitalPinState() {
        try {
            return RestResponseHelper.createResponseOk(cmdGetAllDigitalPinState.process());

        } catch (Exception e) {
            return RestResponseHelper.createResponseException(e);
        }
    }

    @GET
    @Path("getAllDigitalImpulseValue")
    public Response getAllDigitalImpulseValue() {
        try {
            return RestResponseHelper.createResponseOk(cmdGetAllDigitalImpulseValue.process());

        } catch (Exception e) {
            return RestResponseHelper.createResponseException(e);
        }
    }


    @GET
    @Path("{pinIndex}/getDigitalPinState")
    public Response digitalGetPinState(@PathParam("pinIndex") Integer pinIndex) {
        try {
            return RestResponseHelper.createResponseOk(cmdGetDigitalPinState.process(pinIndex));

        } catch (Exception e) {
            return RestResponseHelper.createResponseException(e);
        }
    }

    @GET
    @Path("{pinIndex}/getDigitalImpulseValue")
    public Response getDigitalImpulseValue(@PathParam("pinIndex") Integer pinIndex) {
        try {
            return RestResponseHelper.createResponseOk(cmdGetDigitalImpulseValue.process(pinIndex));

        } catch (Exception e) {
            return RestResponseHelper.createResponseException(e);
        }
    }

    @GET
    @Path("{pinIndex}/resetDigitalImpulseValue")
    public Response resetDigitalImpulseValue(@PathParam("pinIndex") Integer pinIndex) {
        try {
            return RestResponseHelper.createResponseOk(cmdResetDigitalImpulseValue.process(pinIndex));

        } catch (Exception e) {
            return RestResponseHelper.createResponseException(e);
        }
    }


    @GET
    @Path("{pinIndex}/setPinToggle")
    public Response digitalGetPinToggle(@PathParam("pinIndex") Integer pinIndex) {
        try {
            return RestResponseHelper.createResponseOk(cmdSetDigitalPinToggle.process(pinIndex));

        } catch (Exception e) {
            return RestResponseHelper.createResponseException(e);
        }
    }

    @GET
    @Path("{pinIndex}/setPinOn")
    public Response digitalSetPinOn(@PathParam("pinIndex") Integer pinIndex) {
        try {
            return RestResponseHelper.createResponseOk(cmdSetDigitalPinOn.process(pinIndex));

        } catch (Exception e) {
            return RestResponseHelper.createResponseException(e);
        }
    }

    @GET
    @Path("{pinIndex}/setPinOff")
    public Response digitalSetPinOff(@PathParam("pinIndex") Integer pinIndex) {
        try {
            return RestResponseHelper.createResponseOk(cmdSetDigitalPinOff.process(pinIndex));

        } catch (Exception e) {
            return RestResponseHelper.createResponseException(e);
        }
    }
}