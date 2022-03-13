package seedu.duke.controllers;

import seedu.duke.exceptions.OperationTerminationException;

import java.util.Scanner;

public final class InputParser {
    private static final Scanner scanner = new Scanner(System.in);
    public static final String TERMINATOR = "-";

    /**
     * Helper method to get a valid string.
     *
     * @param msg Message to show user when getting input.
     * @return Returns a valid string.
     * @throws OperationTerminationException When user inputs terminator.
     */
    public static String getString(String msg) throws OperationTerminationException {
        System.out.print(msg);
        String line = scanner.nextLine();
        if (line.equals(TERMINATOR)) {
            throw new OperationTerminationException();
        }
        return line;
    }

    /**
     * Helper method to get a valid integer.
     *
     * @param msg Message to show user when getting input.
     * @return Returns a valid integer.
     * @throws OperationTerminationException When user inputs terminator.
     */
    public static int getInteger(String msg) throws OperationTerminationException {
        while (true) {
            try {
                String line = getString(msg).toLowerCase();
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.printf("Error parsing quantity - %s\n", e.getMessage());
            }
        }
    }
}
