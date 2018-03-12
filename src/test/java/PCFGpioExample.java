import com.pi4j.gpio.extension.pcf.PCFAnalogShortGpioProvider;
import com.pi4j.gpio.extension.pcf.PCFAnalogShortPin;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinAnalogInput;
import com.pi4j.io.gpio.event.GpioPinAnalogShortValueChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerAnalogShort;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;

import java.io.IOException;
import java.text.DecimalFormat;

public class PCFGpioExample {


    public static void main(String args[]) throws InterruptedException, UnsupportedBusNumberException, IOException {

        System.out.println("<--Pi4J--> GPIO Example ... started.");

        // number formatters
        final DecimalFormat df = new DecimalFormat("#.##");
        final DecimalFormat pdf = new DecimalFormat("###.#");

        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();

        // create custom GPIO provider
        final PCFAnalogShortGpioProvider gpioProvider = new PCFAnalogShortGpioProvider(I2CBus.BUS_1, 72);

         // provision gpio analog input pins
        GpioPinAnalogInput myInputs[] = {
                gpio.provisionAnalogInputPin(gpioProvider, PCFAnalogShortPin.GPIO_00, "Potar"),
                gpio.provisionAnalogInputPin(gpioProvider, PCFAnalogShortPin.GPIO_01, "Light"),
                gpio.provisionAnalogInputPin(gpioProvider, PCFAnalogShortPin.GPIO_03, "Temperature")
        };

       // create analog pin value change listener
        GpioPinListenerAnalogShort listener = new GpioPinListenerAnalogShort() {
            @Override
            public void handleGpioPinAnalogShortValueChangeEvent(GpioPinAnalogShortValueChangeEvent event) {
                // RAW value
                double value = event.getValue();

                // display output
                System.out.println(" (" + event.getPin().getName() +") : RAW=" + value + "       ");
            }
        };

        myInputs[0].addListener(listener);
        myInputs[1].addListener(listener);
        myInputs[2].addListener(listener);

        System.out.println(" GPIO O0 : " + gpioProvider.getImmediateShortValue(myInputs[0].getPin()) + "  ");
        System.out.println(" GPIO O1 : " + gpioProvider.getImmediateShortValue(myInputs[1].getPin()) + "  ");
        System.out.println(" GPIO O3 : " + gpioProvider.getImmediateShortValue(myInputs[2].getPin()) + "  ");


        // keep program running for 10 minutes
        Thread.sleep(600000);

        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        gpio.shutdown();

        System.out.println("Exiting");
    }
}

