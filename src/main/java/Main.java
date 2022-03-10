import werkit.UI;
import werkit.WerkIt;

import java.io.IOException;

/**
 * Main entry-point for the WerkIt! application.
 */
public class Main {
    public static void main(String[] args) {
        // Start up the WerkIt! application
        try {
            WerkIt werkIt = new WerkIt();
            werkIt.startContinuousUserPrompt();
        } catch (IOException e) {
            System.out.println(UI.IOEXCEPTION_ERROR_MESSAGE);
            System.exit(-1);
        }
    }
}
