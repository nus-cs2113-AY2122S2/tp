import werkIt.WerkIt;

/**
 * Main entry-point for the WerkIt! application.
 */
public class Main {
    public static void main(String[] args) {
        // Start up the WerkIt! application
        WerkIt werkIt = new WerkIt();
        werkIt.startContinuousUserPrompt();
    }
}
