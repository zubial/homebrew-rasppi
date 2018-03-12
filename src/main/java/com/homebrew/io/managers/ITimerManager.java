package com.homebrew.io.managers;

import com.homebrew.exception.BaseException;

import java.util.Observer;

public interface ITimerManager {

    void createStep(String stepName) throws BaseException;
    void scheduleOnce(Integer secondes) throws BaseException;
    Integer getScheduledCountdown();

    void stop();
    void pause() throws BaseException;
    void resume() throws BaseException;

    void addObserver(Observer o);
}
