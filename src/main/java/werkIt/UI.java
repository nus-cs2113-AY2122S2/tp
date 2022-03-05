package werkIt;

/**
 * This class contains all the user interface-related texts and methods for the WerkIt! Application.
 */
public class UI {
    // WerkIt ASCII Banner Logo Art
    public static final String WERKIT_BANNER_LOGO = " __        __        _    ___ _   _ \n" +
            " \\ \\      / /__ _ __| | _|_ _| |_| |\n" +
            "  \\ \\ /\\ / / _ \\ '__| |/ /| || __| |\n" +
            "   \\ V  V /  __/ |  |   < | || |_|_|\n" +
            "    \\_/\\_/ \\___|_|  |_|\\_\\___|\\__(_)\n" +
            "                                    ";

    public static final String WELCOME_MESSAGE = "Welcome to WerkIt!, your personal exercise planner.";

    // The default parameters for printing the formatting lines
    public static final int DEFAULT_LINE_LENGTH = 80;
    public static final String DEFAULT_LINE_CHAR = "-";

    /**
     * Prints a line on the console based on the default parameters defined in this Java class.
     * <p>
     * Source: Team Member Alan Low's iP codebase
     * Link: https://github.com/alanlowzies/ip/blob/8556dd6a5106d190f5ac0458c6d2c34f98737a91/src/main/java/sora/SoraUI.java
     */
    protected void printLine() {
        // Prints a line based on the default parameters
        printLine(DEFAULT_LINE_LENGTH, DEFAULT_LINE_CHAR);
    }

    /**
     * Prints a line on the console based on the specified length and the character/symbol to use
     * <p>
     * Source: Team Member Alan Low's iP codebase
     * Link: https://github.com/alanlowzies/ip/blob/8556dd6a5106d190f5ac0458c6d2c34f98737a91/src/main/java/sora/SoraUI.java
     *
     * @param lineLen The length of the line (measured in 'number of characters').
     * @param character The character that should be used to print the line.
     */
    protected void printLine(int lineLen, String character) {
        // Generate a line of whitespaces
        String lineOfWhitespaces = String.format("%" + lineLen + "s", " ");

        // Replace each whitespace with the specified character
        String lineOfChars = lineOfWhitespaces.replaceAll(" ", character);

        // Finally, print out the line of characters
        System.out.println(lineOfChars);
    }

    protected void printGreetings() {
        printLine(DEFAULT_LINE_LENGTH, "=");
        System.out.println(WERKIT_BANNER_LOGO);
        System.out.println(WELCOME_MESSAGE);
    }
}
