package com.homebrew.io.helper;

import com.pi4j.io.gpio.PinState;

public class GpioHelper {

    private GpioHelper() {
        // No const
    }

    public static PinState getDigitalPinState(Integer value) {
        return ("1".equals(value)? PinState.HIGH:PinState.LOW);
    }
}
