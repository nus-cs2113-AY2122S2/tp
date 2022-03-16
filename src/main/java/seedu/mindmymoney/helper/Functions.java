package seedu.mindmymoney.helper;

import seedu.mindmymoney.constants.Indexes;

public class Functions {
    /**
     * Separates the user input into the command and description for easy reference.
     *
     * @return String array of user input.
     */
    public static String[] parseInput(String inputCommand) {
        String[] inputAsArray = inputCommand.split(" ", Indexes.SPLIT_LIMIT);
        return inputAsArray;
    }

    /**
     * Separates the user input into the command and description for easy reference.
     *
     * @return String array of user input.
     */
    public static String[] parseInputWithCommandFlag(String inputCommandWithFlag) {
        String inputCommandWithoutFlag = inputCommandWithFlag.replace("-c ","");
        String[] inputAsArray = inputCommandWithoutFlag.split(" ", Indexes.SPLIT_LIMIT);
        return inputAsArray;
    }

}
