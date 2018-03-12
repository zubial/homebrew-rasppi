package com.homebrew.io.managers;


import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.homebrew.exception.BaseException;

public interface ISocketIOManager {

    void start() throws BaseException;
    void shutdown() throws BaseException;

    SocketIOServer getSocketIOServer() throws BaseException;

    SocketIONamespace getAnalogInputNamespace() throws BaseException;
    SocketIONamespace getDigitalOutputNamespace() throws BaseException;
    SocketIONamespace getDigitalImpulseNamespace() throws BaseException;
    SocketIONamespace getTimerNamespace() throws BaseException;
}
