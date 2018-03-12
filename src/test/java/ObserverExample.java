import com.homebrew.exception.BaseException;
import com.homebrew.io.managers.impl.TimerManager;
import com.homebrew.io.observers.ObserverTest;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;

import java.io.IOException;

public class ObserverExample {


    public static void main(String args[]) throws InterruptedException, UnsupportedBusNumberException, IOException, BaseException {

        System.out.println("ObserverExample starting");

        ObserverTest observer1 = new ObserverTest();

        TimerManager watchDog = new TimerManager();
        watchDog.addObserver(observer1);

        System.out.println("WatchDog1 is scheduled.");

        watchDog.scheduleOnce(1);
        Thread.sleep(181000);

        watchDog.stop();

        System.out.println("Exiting");
    }
}

