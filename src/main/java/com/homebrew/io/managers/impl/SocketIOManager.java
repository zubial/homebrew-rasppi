package com.homebrew.io.managers.impl;

import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.listener.DataListener;
import com.homebrew.enums.ErrorCodeEnum;
import com.homebrew.exception.BaseException;
import com.homebrew.exception.SocketIOException;
import com.homebrew.io.managers.ISocketIOManager;
import com.homebrew.io.models.GpioEventModel;
import com.homebrew.io.models.TimerEventModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SocketIOManager implements ISocketIOManager {

    private static final Logger LOGGER = Logger.getLogger(SocketIOManager.class);

    private SocketIOServer server;
    private SocketIONamespace analogInputNamespace;
    private SocketIONamespace digitalOutputNamespace;
    private SocketIONamespace digitalImpulseNamespace;
    private SocketIONamespace timerNamespace;

    @Value("${socketio.hostname}")
    private String socketioHostname;
    @Value("${socketio.port}")
    private Integer socketioPort;
    @Override
    public SocketIOServer getSocketIOServer() throws BaseException {
        return server;
    }

    public void start() throws BaseException {
        try {
            if (server == null) {
                Configuration socketIOConfig = new Configuration();
                socketIOConfig.setHostname(socketioHostname);
                socketIOConfig.setPort(socketioPort);

                server = new SocketIOServer(socketIOConfig);
            }

            if (analogInputNamespace == null) {
                analogInputNamespace = server.addNamespace("/analogInput");
                analogInputNamespace.addEventListener("message", GpioEventModel.class, new DataListener<GpioEventModel>() {
                    @Override
                    public void onData(SocketIOClient client, GpioEventModel data, AckRequest ackRequest) {
                        // broadcast messages to all clients
                        analogInputNamespace.getBroadcastOperations().sendEvent("message", data);
                    }
                });
            }

            if (digitalOutputNamespace == null) {
                digitalOutputNamespace = server.addNamespace("/digitalOutput");
                digitalOutputNamespace.addEventListener("message", GpioEventModel.class, new DataListener<GpioEventModel>() {
                    @Override
                    public void onData(SocketIOClient client, GpioEventModel data, AckRequest ackRequest) {
                        // broadcast messages to all clients
                        digitalOutputNamespace.getBroadcastOperations().sendEvent("message", data);
                    }
                });
            }

            if (digitalImpulseNamespace == null) {
                digitalImpulseNamespace = server.addNamespace("/digitalImpulse");
                digitalImpulseNamespace.addEventListener("message", GpioEventModel.class, new DataListener<GpioEventModel>() {
                    @Override
                    public void onData(SocketIOClient client, GpioEventModel data, AckRequest ackRequest) {
                        // broadcast messages to all clients
                        digitalImpulseNamespace.getBroadcastOperations().sendEvent("message", data);
                    }
                });
            }

            if (timerNamespace == null) {
                timerNamespace = server.addNamespace("/timer");
                timerNamespace.addEventListener("message", TimerEventModel.class, new DataListener<TimerEventModel>() {
                    @Override
                    public void onData(SocketIOClient client, TimerEventModel data, AckRequest ackRequest) {
                        // broadcast messages to all clients
                        timerNamespace.getBroadcastOperations().sendEvent("message", data);
                    }
                });
            }
            server.start();

            LOGGER.info("Socket I/O Server Started");

        } catch (Exception e) {
            throw new SocketIOException(ErrorCodeEnum.SOCKETIO_INIT_FAILED, e);
        }
    }

    public void shutdown() throws BaseException {
        try {
            if (server != null) {
                server.removeNamespace("/analogInput");
                analogInputNamespace = null;

                server.removeNamespace("/digitalOutput");
                digitalOutputNamespace = null;

                server.removeNamespace("/digitalImpulse");
                digitalImpulseNamespace = null;

                server.removeNamespace("/timer");
                timerNamespace = null;

                server.stop();
            }
        } catch (Exception e) {
            throw new SocketIOException(ErrorCodeEnum.SOCKETIO_STOP_FAILED, e);
        }
    }

    public SocketIONamespace getAnalogInputNamespace() throws BaseException {
        return analogInputNamespace;
    }

    public SocketIONamespace getDigitalOutputNamespace() throws BaseException {
        return digitalOutputNamespace;
    }

    public SocketIONamespace getDigitalImpulseNamespace() throws BaseException {
        return digitalImpulseNamespace;
    }

    public SocketIONamespace getTimerNamespace() throws BaseException {
        return timerNamespace;
    }
}
