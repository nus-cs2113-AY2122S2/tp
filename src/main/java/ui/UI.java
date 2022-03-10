package ui;

/**
 * Prints messages with horizontal lines.
 */
public class UI {
    protected static final String HORI_LINE = "---------------------------";
    protected static final String EXIT_MSG = "Goodbye. Hope to see you again.";
    protected static final String LOGO =
            "$$$$$$$  $$                   $$ $$$$$$$$               $$\n"
                    + "$$$$$$$  $$                   $$ $$$$$$$$\n"
                    + "$$ $$$$  $$  $$$$$$  $$$$$$$  $$    $$    $$$$$$  $$$$$ $$  $$   $$  $$$$$$$$$$\n"
                    + "$$ $$$$  $$  $$$$$$  $$$$$$$  $$    $$    $$$$$$  $$$$$ $$  $$   $$  $$$$$$$$$$\n"
                    + "$$$$$$$  $$    $$$$  $$$$$$$  $$    $$      $$$$  $$$$$ $$  $$   $$  $$$$$$$$$$\n"
                    + "$$$$$$$  $$   $$$$$  $$$ $$$  $$    $$     $$$$$  $$$   $$  $$  $$$  $$$$$$$$$$\n"
                    + "$$       $$  $$$$$$  $$$  $$  $$    $$    $$$$$$  $$$   $$  $$$ $$$  $$  $$  $$\n"
                    + "$$       $$  $$$$$$$ $$   $$  $$    $$    $$$$$$$ $$    $$  $$$$$$$  $$  $$  $$\n"
                    + "$$       $$  $$$$$$$ $$   $$  $$    $$    $$$$$$$ $$    $$  $$$$$$$  $$  $$  $$\n"
                    + "$$       $$  $$$$$$$ $$   $$  $$    $$    $$$$$$$ $$    $$  $$$$$$$  $$  $$  $$\n";
    protected String content;

    public UI() {
    }

    public void printMsg(String message) {
        System.out.println(HORI_LINE);
        System.out.println(message);
        System.out.println(HORI_LINE);
    }

    public static void exit() {
        System.out.println(EXIT_MSG);
    }

    private static void printWelcomeMessage() {
        System.out.println(LOGO);
    }
}
