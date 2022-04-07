package seedu.planitarium.global;

import java.util.Collections;

/**
 * Prints messages with horizontal lines.
 */
public class UI {
    public static final String HORI_LINE = String.join("", Collections.nCopies(79, "-"));
    protected static final String WELCOME_MSG = "Welcome to PlanITarium, your personal expenditure manager.";
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

    public static void printWelcomeMessage() {
        System.out.println(LOGO);
        System.out.println(WELCOME_MSG);
        System.out.println(HORI_LINE);
    }

    public static void printHelpMsg() {
        for (int i = 0;i < Help.CommandSummary.size(); i++) {
            System.out.println(Help.CommandSummary.get(i));
        }
    }

    public static void printHoriLine() {
        System.out.println(HORI_LINE);
    }

    /**
     * Returns the formatting of a monetary value depending on its polarity.
     *
     * @param entry The monetary value
     * @return The formatting of the value
     */
    public static String formatValue(Double entry) {
        boolean isNegative = false;
        if (entry < 0) {
            isNegative = true;
            entry = (-entry);
        }
        String strDouble = String.format("%.2f", entry);
        if (isNegative) {
            return ("-$" + strDouble);
        }
        return ("$" + strDouble);
    }
}
