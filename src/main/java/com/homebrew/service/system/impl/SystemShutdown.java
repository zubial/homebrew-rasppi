package com.homebrew.service.system.impl;

import com.homebrew.service.system.ISystemShutdown;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.homebrew.exception.BaseException;
import com.homebrew.rest.type.ResponseType;
import com.pi4j.util.ExecUtil;

import java.io.IOException;

@Service
public class SystemShutdown implements ISystemShutdown {

    private static final Logger LOGGER = Logger.getLogger(SystemShutdown.class);

    @Override
    public ResponseType process() throws BaseException {

        ResponseType response = new ResponseType();

        try {
            String[] result = ExecUtil.execute("shutdown --poweroff now 'Homebrew shutdown the server !'");
            response.setDone(true);
            response.setMessage("Homebrew shutdown the server !");

        } catch (IOException | InterruptedException e) {
            response.setDone(false);
            response.setMessage(e.getMessage());
        }

        return response;
    }
}
