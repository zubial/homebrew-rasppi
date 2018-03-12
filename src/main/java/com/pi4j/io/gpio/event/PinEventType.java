package com.pi4j.io.gpio.event;

public enum PinEventType {
    DIGITAL_STATE_CHANGE,
    ANALOG_VALUE_CHANGE,
    ANALOG_SHORT_VALUE_CHANGE;

    private PinEventType() {
    }
}