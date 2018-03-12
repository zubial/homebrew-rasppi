package com.homebrew.rest;

import com.homebrew.rest.helper.RestResponseHelper;
import com.homebrew.service.timer.ITimerPause;
import com.homebrew.service.timer.ITimerResume;
import com.homebrew.service.timer.ITimerScheduleOnce;
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
@Path("/timer")
@Produces(value = "application/json")
public class TimerRestImpl extends SpringBeanAutowiringSupport {

    private static final Logger LOGGER = Logger.getLogger(TimerRestImpl.class);

    @Autowired
    private ITimerScheduleOnce timerScheduleOnce;

    @Autowired
    private ITimerPause timerPause;

    @Autowired
    private ITimerResume timerResume;

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
    @Path("scheduleOnce/{minutes}")
    public Response scheduleOnce(@PathParam("minutes") Integer minutes) {
        try {
            return RestResponseHelper.createResponseOk(timerScheduleOnce.process(minutes));

        } catch (final Exception e) {
            return RestResponseHelper.createResponseException(e);
        }
    }

    @GET
    @Path("pause")
    public Response pause() {
        try {
            return RestResponseHelper.createResponseOk(timerPause.process());

        } catch (final Exception e) {
            return RestResponseHelper.createResponseException(e);
        }
    }

    @GET
    @Path("resume")
    public Response resume() {
        try {
            return RestResponseHelper.createResponseOk(timerResume.process());

        } catch (final Exception e) {
            return RestResponseHelper.createResponseException(e);
        }
    }
}