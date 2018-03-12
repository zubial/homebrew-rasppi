import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;
import com.pi4j.util.ExecUtil;

import java.io.IOException;

public class ExecUtilExample {


    public static void main(String args[]) throws InterruptedException, UnsupportedBusNumberException, IOException {

        System.out.println("ExecUtilExample starting");

        String[] result = ExecUtil.execute("echo 'Homebrew shutdown the server !'");

        if (result != null) {
            for (int i = 0; i < result.length; i++) {
                System.out.println(result[i]);
            }
        }

        System.out.println("Exiting");
    }
}

