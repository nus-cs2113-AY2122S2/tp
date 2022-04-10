import storage.LogHandler;
import werkit.UI;
import werkit.WerkIt;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main entry-point for the WerkIt! application.
 */
public class Main {
    private static Logger logger = Logger.getLogger(WerkIt.class.getName());

    public static void main(String[] args) {
        // Start up the WerkIt! application
        LogHandler.startLogHandler();

        try {
            WerkIt werkIt = new WerkIt();
            werkIt.startContinuousUserPrompt();
        } catch (IOException e) {
            System.out.println(UI.IOEXCEPTION_ERROR_MESSAGE);
            logger.log(Level.SEVERE, "Program encountered an IO Exception and needs to terminate.");
            System.exit(-1);
        }

        logger.log(Level.INFO, "Program has finished and is exiting.");
    }
}
