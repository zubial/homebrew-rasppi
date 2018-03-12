package com.homebrew.service.system.impl;

import com.homebrew.exception.BaseException;
import com.homebrew.rest.type.ResponseType;
import com.homebrew.service.system.ISystemRestart;
import com.pi4j.util.ExecUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SystemRestart implements ISystemRestart {

    private static final Logger LOGGER = Logger.getLogger(SystemRestart.class);

    @Override
    public ResponseType process() throws BaseException {

        ResponseType response = new ResponseType();

        try {
            String[] result = ExecUtil.execute("shutdown --reboot now 'Homebrew restart the server !'");
            response.setDone(true);
            response.setMessage("Homebrew restart the server !");

        } catch (IOException | InterruptedException e) {
            response.setDone(false);
            response.setMessage(e.getMessage());
        }

        return response;
    }
}
