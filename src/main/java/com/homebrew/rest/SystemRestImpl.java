package com.homebrew.rest;

import com.homebrew.rest.helper.RestResponseHelper;
import com.homebrew.service.system.ISystemRestart;
import com.homebrew.service.system.ISystemShutdown;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Controller
@Path("/system")
@Produces(value = "application/json")
public class SystemRestImpl extends SpringBeanAutowiringSupport {

    private static final Logger LOGGER = Logger.getLogger(SystemRestImpl.class);

    @Autowired
    private ISystemShutdown cmdShutdown;

    @Autowired
    private ISystemRestart cmdRestart;

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
    public Response shutdown() {
        try {
            return RestResponseHelper.createResponseOk(cmdShutdown.process());

        } catch (Exception e) {
            return RestResponseHelper.createResponseException(e);
        }
    }

    @GET
    @Path("restart")
    public Response restart() {
        try {
            return RestResponseHelper.createResponseOk(cmdRestart.process());

        } catch (Exception e) {
            return RestResponseHelper.createResponseException(e);
        }
    }
}