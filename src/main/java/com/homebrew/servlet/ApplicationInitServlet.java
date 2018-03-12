package com.homebrew.servlet;

import com.homebrew.enums.ErrorCodeEnum;
import com.homebrew.exception.BaseException;
import com.homebrew.io.helper.GpioHelper;
import com.homebrew.io.managers.IGpioAnalogManager;
import com.homebrew.io.managers.IGpioDigitalManager;
import com.homebrew.io.managers.IGpioImpulseManager;
import com.homebrew.io.managers.ISocketIOManager;
import com.pi4j.io.gpio.RaspiPin;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "ApplicationInitServlet", loadOnStartup = 1, urlPatterns = {"/initServlet/*"})
public class ApplicationInitServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(ApplicationInitServlet.class);

    @Autowired
    private ISocketIOManager socketIOManager;

    @Autowired
    private IGpioAnalogManager gpioAnalogManager;

    @Autowired
    private IGpioDigitalManager gpioDigitalManager;

    @Autowired
    private IGpioImpulseManager gpioImpulseManager;

    // Analog Input
    @Value("${gpio.analogInput.00.enable}")
    private Boolean analogInput00Enable;
    @Value("${gpio.analogInput.00.name}")
    private String analogInput00Name;
    @Value("${gpio.analogInput.00.eventToDb}")
    private Boolean analogInput00EventToDb;
    @Value("${gpio.analogInput.00.eventToSocket}")
    private Boolean analogInput00EventToSocket;

    @Value("${gpio.analogInput.01.enable}")
    private Boolean analogInput01Enable;
    @Value("${gpio.analogInput.01.name}")
    private String analogInput01Name;
    @Value("${gpio.analogInput.01.eventToDb}")
    private Boolean analogInput01EventToDb;
    @Value("${gpio.analogInput.01.eventToSocket}")
    private Boolean analogInput01EventToSocket;

    @Value("${gpio.analogInput.02.enable}")
    private Boolean analogInput02Enable;
    @Value("${gpio.analogInput.02.name}")
    private String analogInput02Name;
    @Value("${gpio.analogInput.02.eventToDb}")
    private Boolean analogInput02EventToDb;
    @Value("${gpio.analogInput.02.eventToSocket}")
    private Boolean analogInput02EventToSocket;

    @Value("${gpio.analogInput.03.enable}")
    private Boolean analogInput03Enable;
    @Value("${gpio.analogInput.03.name}")
    private String analogInput03Name;
    @Value("${gpio.analogInput.03.eventToDb}")
    private Boolean analogInput03EventToDb;
    @Value("${gpio.analogInput.03.eventToSocket}")
    private Boolean analogInput03EventToSocket;

    @Value("${gpio.analogInput.04.enable}")
    private Boolean analogInput04Enable;
    @Value("${gpio.analogInput.04.name}")
    private String analogInput04Name;
    @Value("${gpio.analogInput.04.eventToDb}")
    private Boolean analogInput04EventToDb;
    @Value("${gpio.analogInput.04.eventToSocket}")
    private Boolean analogInput04EventToSocket;

    @Value("${gpio.analogInput.05.enable}")
    private Boolean analogInput05Enable;
    @Value("${gpio.analogInput.05.name}")
    private String analogInput05Name;
    @Value("${gpio.analogInput.05.eventToDb}")
    private Boolean analogInput05EventToDb;
    @Value("${gpio.analogInput.05.eventToSocket}")
    private Boolean analogInput05EventToSocket;


    // Digital Output
    @Value("${gpio.digitalOutput.00.enable}")
    private Boolean digitalOutput00Enable;
    @Value("${gpio.digitalOutput.00.name}")
    private String digitalOutput00Name;
    @Value("${gpio.digitalOutput.00.initialValue}")
    private Integer digitalOutput00InitialValue;
    @Value("${gpio.digitalOutput.00.shutdownValue}")
    private Integer digitalOutput00ShutdownValue;
    @Value("${gpio.digitalOutput.00.eventToDb}")
    private Boolean digitalOutput00EventToDb;
    @Value("${gpio.digitalOutput.00.eventToSocket}")
    private Boolean digitalOutput00EventToSocket;

    @Value("${gpio.digitalOutput.01.enable}")
    private Boolean digitalOutput01Enable;
    @Value("${gpio.digitalOutput.01.name}")
    private String digitalOutput01Name;
    @Value("${gpio.digitalOutput.01.initialValue}")
    private Integer digitalOutput01InitialValue;
    @Value("${gpio.digitalOutput.01.shutdownValue}")
    private Integer digitalOutput01ShutdownValue;
    @Value("${gpio.digitalOutput.01.eventToDb}")
    private Boolean digitalOutput01EventToDb;
    @Value("${gpio.digitalOutput.01.eventToSocket}")
    private Boolean digitalOutput01EventToSocket;

    @Value("${gpio.digitalOutput.02.enable}")
    private Boolean digitalOutput02Enable;
    @Value("${gpio.digitalOutput.02.name}")
    private String digitalOutput02Name;
    @Value("${gpio.digitalOutput.02.initialValue}")
    private Integer digitalOutput02InitialValue;
    @Value("${gpio.digitalOutput.02.shutdownValue}")
    private Integer digitalOutput02ShutdownValue;
    @Value("${gpio.digitalOutput.02.eventToDb}")
    private Boolean digitalOutput02EventToDb;
    @Value("${gpio.digitalOutput.02.eventToSocket}")
    private Boolean digitalOutput02EventToSocket;

    @Value("${gpio.digitalOutput.03.enable}")
    private Boolean digitalOutput03Enable;
    @Value("${gpio.digitalOutput.03.name}")
    private String digitalOutput03Name;
    @Value("${gpio.digitalOutput.03.initialValue}")
    private Integer digitalOutput03InitialValue;
    @Value("${gpio.digitalOutput.03.shutdownValue}")
    private Integer digitalOutput03ShutdownValue;
    @Value("${gpio.digitalOutput.03.eventToDb}")
    private Boolean digitalOutput03EventToDb;
    @Value("${gpio.digitalOutput.03.eventToSocket}")
    private Boolean digitalOutput03EventToSocket;

    @Value("${gpio.digitalOutput.04.enable}")
    private Boolean digitalOutput04Enable;
    @Value("${gpio.digitalOutput.04.name}")
    private String digitalOutput04Name;
    @Value("${gpio.digitalOutput.04.initialValue}")
    private Integer digitalOutput04InitialValue;
    @Value("${gpio.digitalOutput.04.shutdownValue}")
    private Integer digitalOutput04ShutdownValue;
    @Value("${gpio.digitalOutput.04.eventToDb}")
    private Boolean digitalOutput04EventToDb;
    @Value("${gpio.digitalOutput.04.eventToSocket}")
    private Boolean digitalOutput04EventToSocket;

    @Value("${gpio.digitalOutput.05.enable}")
    private Boolean digitalOutput05Enable;
    @Value("${gpio.digitalOutput.05.name}")
    private String digitalOutput05Name;
    @Value("${gpio.digitalOutput.05.initialValue}")
    private Integer digitalOutput05InitialValue;
    @Value("${gpio.digitalOutput.05.shutdownValue}")
    private Integer digitalOutput05ShutdownValue;
    @Value("${gpio.digitalOutput.05.eventToDb}")
    private Boolean digitalOutput05EventToDb;
    @Value("${gpio.digitalOutput.05.eventToSocket}")
    private Boolean digitalOutput05EventToSocket;

    // Digital Input
    @Value("${gpio.digitalInput.00.enable}")
    private Boolean digitalInput00Enable;
    @Value("${gpio.digitalInput.00.name}")
    private String digitalInput00Name;
    @Value("${gpio.digitalInput.00.eventToDb}")
    private Boolean digitalInput00EventToDb;
    @Value("${gpio.digitalInput.00.eventToSocket}")
    private Boolean digitalInput00EventToSocket;

    @Value("${gpio.digitalInput.01.enable}")
    private Boolean digitalInput01Enable;
    @Value("${gpio.digitalInput.01.name}")
    private String digitalInput01Name;
    @Value("${gpio.digitalInput.01.eventToDb}")
    private Boolean digitalInput01EventToDb;
    @Value("${gpio.digitalInput.01.eventToSocket}")
    private Boolean digitalInput01EventToSocket;

    @Override
    public void init(final ServletConfig config)
            throws ServletException {
        super.init(config);

        // Init method
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        // Socket I/O
        try {
            socketIOManager.start();
            LOGGER.debug("SocketIOServlet Started");
        } catch (Exception e) {
            LOGGER.error(ErrorCodeEnum.SOCKETIO_INIT_FAILED, e);
            throw new ServletException("Initialize failed", e);
        }

        // GPIO
        try {
            // Analog Input
            if (analogInput00Enable) {
                gpioAnalogManager.getPin00(analogInput00Name);
                if (analogInput00EventToDb) {
                    gpioAnalogManager.startLogsEvent(0);
                }
                if (analogInput00EventToSocket) {
                    gpioAnalogManager.startSocketIOEvent(0);
                }
            }

            if (analogInput01Enable) {
                gpioAnalogManager.getPin01(analogInput01Name);
                if (analogInput01EventToDb) {
                    gpioAnalogManager.startLogsEvent(1);
                }
                if (analogInput01EventToSocket) {
                    gpioAnalogManager.startSocketIOEvent(1);
                }
            }

            if (analogInput02Enable) {
                gpioAnalogManager.getPin02(analogInput02Name);
                if (analogInput02EventToDb) {
                    gpioAnalogManager.startLogsEvent(2);
                }
                if (analogInput02EventToSocket) {
                    gpioAnalogManager.startSocketIOEvent(2);
                }
            }

            if (analogInput03Enable) {
                gpioAnalogManager.getPin03(analogInput03Name);
                if (analogInput03EventToDb) {
                    gpioAnalogManager.startLogsEvent(3);
                }
                if (analogInput03EventToSocket) {
                    gpioAnalogManager.startSocketIOEvent(3);
                }
            }

            if (analogInput04Enable) {
                gpioAnalogManager.getPin04(analogInput04Name);
                if (analogInput04EventToDb) {
                    gpioAnalogManager.startLogsEvent(4);
                }
                if (analogInput04EventToSocket) {
                    gpioAnalogManager.startSocketIOEvent(4);
                }
            }

            if (analogInput05Enable) {
                gpioAnalogManager.getPin05(analogInput05Name);
                if (analogInput05EventToDb) {
                    gpioAnalogManager.startLogsEvent(5);
                }
                if (analogInput05EventToSocket) {
                    gpioAnalogManager.startSocketIOEvent(5);
                }
            }


            // Digital Output
            if (digitalOutput00Enable) {
                gpioDigitalManager.createPin(0, RaspiPin.GPIO_00, digitalOutput00Name, GpioHelper.getDigitalPinState(digitalOutput00InitialValue), GpioHelper.getDigitalPinState(digitalOutput00ShutdownValue));
                addDigitalOutputEventListener(0, digitalOutput00EventToDb, digitalOutput00EventToSocket);
            }

            if (digitalOutput01Enable) {
                gpioDigitalManager.createPin(1, RaspiPin.GPIO_01, digitalOutput01Name, GpioHelper.getDigitalPinState(digitalOutput01InitialValue), GpioHelper.getDigitalPinState(digitalOutput01ShutdownValue));
                addDigitalOutputEventListener(1, digitalOutput01EventToDb, digitalOutput01EventToSocket);
            }

            if (digitalOutput02Enable) {
                gpioDigitalManager.createPin(2, RaspiPin.GPIO_02, digitalOutput02Name, GpioHelper.getDigitalPinState(digitalOutput02InitialValue), GpioHelper.getDigitalPinState(digitalOutput02ShutdownValue));
                addDigitalOutputEventListener(2, digitalOutput02EventToDb, digitalOutput02EventToSocket);
            }

            if (digitalOutput03Enable) {
                gpioDigitalManager.createPin(3, RaspiPin.GPIO_03, digitalOutput03Name, GpioHelper.getDigitalPinState(digitalOutput03InitialValue), GpioHelper.getDigitalPinState(digitalOutput03ShutdownValue));
                addDigitalOutputEventListener(3, digitalOutput03EventToDb, digitalOutput03EventToSocket);
            }

            if (digitalOutput04Enable) {
                gpioDigitalManager.createPin(4, RaspiPin.GPIO_04, digitalOutput04Name, GpioHelper.getDigitalPinState(digitalOutput04InitialValue), GpioHelper.getDigitalPinState(digitalOutput04ShutdownValue));
                addDigitalOutputEventListener(4, digitalOutput04EventToDb, digitalOutput04EventToSocket);
            }

            if (digitalOutput05Enable) {
                gpioDigitalManager.createPin(5, RaspiPin.GPIO_05, digitalOutput05Name, GpioHelper.getDigitalPinState(digitalOutput05InitialValue), GpioHelper.getDigitalPinState(digitalOutput05ShutdownValue));
                addDigitalOutputEventListener(5, digitalOutput05EventToDb, digitalOutput05EventToSocket);
            }

            // Digital Input - Impulse
            if (digitalInput00Enable) {
                gpioImpulseManager.createPin(6, RaspiPin.GPIO_06, digitalInput00Name);
                addDigitalInputEventListener(6, digitalInput00EventToDb, digitalInput00EventToSocket);
            }

            if (digitalInput01Enable) {
                gpioImpulseManager.createPin(7, RaspiPin.GPIO_07, digitalInput01Name);
                addDigitalInputEventListener(7, digitalInput01EventToDb, digitalInput01EventToSocket);
            }

        } catch (Exception e) {
            LOGGER.error(ErrorCodeEnum.GPIO_INIT_FAILED, e);
            throw new ServletException("Initialize failed", e);
        }
    }

    private void addDigitalOutputEventListener(Integer pinIndex, Boolean eventToDb, Boolean eventToSocket) throws BaseException {
        if (eventToDb) {
            gpioDigitalManager.startLogsEvent(pinIndex);
        }
        if (eventToSocket) {
            gpioDigitalManager.startSocketIOEvent(pinIndex);
        }
    }

    private void addDigitalInputEventListener(Integer pinIndex, Boolean eventToDb, Boolean eventToSocket) throws BaseException {
        if (eventToDb) {
            gpioImpulseManager.startLogsEvent(pinIndex);
        }
        if (eventToSocket) {
            gpioImpulseManager.startSocketIOEvent(pinIndex);
        }
        gpioImpulseManager.startCountEvent(pinIndex);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.getWriter().append("OK");

    }
}
