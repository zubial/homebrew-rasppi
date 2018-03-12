package com.homebrew.io.observers;

import java.util.Observable;
import java.util.Observer;

public class ObserverTest implements Observer {


    @Override
    public void update(Observable arg0, Object arg1) {
        System.out.println("Observer1 notified");
    }
}
