package seedu.splitlah.parser.commandparser;

import org.junit.jupiter.api.Test;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.ui.Message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ActivityCreateCommandParserTest {

    /**
     * Checks if an InvalidFormatException is thrown when both cost and cost list are not provided by the user
     * and if the exception message is correct.
     */
    @Test
    public void getCommand_hasMissingCostAndCostList_InvalidFormatExceptionThrown() {
        String userInput = "activity /create /sid 1 /n Dinner /p Alice /i Alice Bob Charlie";
        String arguments = Parser.getRemainingArgument(userInput);
        ActivityCreateCommandParser activityCreateCommandParser = new ActivityCreateCommandParser();
        try {
            activityCreateCommandParser.getCommand(arguments);
            fail();
        } catch (InvalidFormatException e) {
            assertEquals(e.getMessage(), Message.ERROR_ACTIVITYCREATE_MISSING_COST_AND_COST_LIST
                    + "\n" + ActivityCreateCommandParser.COMMAND_FORMAT
                    + ActivityCreateCommandParser.COMMAND_FORMAT_FIRST + "\n\t"
                    + ActivityCreateCommandParser.COMMAND_FORMAT_SECOND);
        }
    }

    /**
     * Checks if an InvalidFormatException is thrown when both cost and cost list are provided by the user
     * and if the exception message is correct.
     */
    @Test
    public void getCommand_hasBothCostAndCostList_InvalidFormatExceptionThrown() {
        String userInput = "activity /create /sid 1 /n Dinner /p Alice /i Alice Bob Charlie /co 30 /cl 10 10 10";
        String arguments = Parser.getRemainingArgument(userInput);
        ActivityCreateCommandParser activityCreateCommandParser = new ActivityCreateCommandParser();
        try {
            activityCreateCommandParser.getCommand(arguments);
            fail();
        } catch (InvalidFormatException e) {
            assertEquals(e.getMessage(), Message.ERROR_ACTIVITYCREATE_HAS_BOTH_COST_AND_COST_LIST
                    + "\n" + ActivityCreateCommandParser.COMMAND_FORMAT
                    + ActivityCreateCommandParser.COMMAND_FORMAT_FIRST + "\n\t"
                    + ActivityCreateCommandParser.COMMAND_FORMAT_SECOND);
        }
    }

    /**
     * Checks if an InvalidFormatException is thrown when the cost list and involved list that are provided by the user
     * are of different lengths and if the exception message is correct.
     */
    @Test
    public void getCommand_costListAndInvolvedListDifferentLength_InvalidFormatExceptionThrown() {
        ActivityCreateCommandParser activityCreateCommandParser = new ActivityCreateCommandParser();

        //Case 1: Involved list longer than cost list
        String userInputOne = "activity /create /sid 1 /n Dinner /p Alice /i Alice Bob Charlie /cl 10 10";
        String argumentsOne = Parser.getRemainingArgument(userInputOne);
        try {
            activityCreateCommandParser.getCommand(argumentsOne);
            fail();
        } catch (InvalidFormatException e) {
            assertEquals(e.getMessage(), Message.ERROR_ACTIVITYCREATE_INVOLVED_AND_COST_DIFFERENT_LENGTH
                    + "\n" + ActivityCreateCommandParser.COMMAND_FORMAT
                    + ActivityCreateCommandParser.COMMAND_FORMAT_FIRST + "\n\t"
                    + ActivityCreateCommandParser.COMMAND_FORMAT_SECOND);
        }

        //Case 2: Involved list shorter than cost list
        String userInputTwo = "activity /create /sid 1 /n Dinner /p Alice /i Alice Bob /cl 10 10 10";
        String argumentsTwo = Parser.getRemainingArgument(userInputTwo);
        try {
            activityCreateCommandParser.getCommand(argumentsTwo);
            fail();
        } catch (InvalidFormatException e) {
            assertEquals(e.getMessage(), Message.ERROR_ACTIVITYCREATE_INVOLVED_AND_COST_DIFFERENT_LENGTH
                    + "\n" + ActivityCreateCommandParser.COMMAND_FORMAT
                    + ActivityCreateCommandParser.COMMAND_FORMAT_FIRST + "\n\t"
                    + ActivityCreateCommandParser.COMMAND_FORMAT_SECOND);
        }
    }

    /**
     * Checks if an InvalidFormatException is thrown when missing delimiters are provided by the user
     * and if the exception message is correct.
     */
    @Test
    public void getCommand_hasMissingDelimiter_InvalidCommand() {
        ActivityCreateCommandParser activityCreateCommandParser = new ActivityCreateCommandParser();

        // Case 1: Missing /sid delimiter
        String inputMissingSessionIdDelimiter = "activity /create /n Dinner /p Alice /i Alice Bob Charlie /co 15";
        String argsMissingSessionIdDelimiter = Parser.getRemainingArgument(inputMissingSessionIdDelimiter);
        try {
            activityCreateCommandParser.getCommand(argsMissingSessionIdDelimiter);
            fail();
        } catch (InvalidFormatException e) {
            assertEquals(e.getMessage(), Message.ERROR_PARSER_DELIMITER_NOT_FOUND + "/sid"
                    + "\n" + ActivityCreateCommandParser.COMMAND_FORMAT
                    + ActivityCreateCommandParser.COMMAND_FORMAT_FIRST + "\n\t"
                    + ActivityCreateCommandParser.COMMAND_FORMAT_SECOND);
        }

        // Case 2: Missing /n delimiter
        String inputMissingNameDelimiter = "activity /create /sid 1 /p Alice /i Alice Bob Charlie /co 15";
        String argsMissingNameDelimiter = Parser.getRemainingArgument(inputMissingNameDelimiter);
        try {
            activityCreateCommandParser.getCommand(argsMissingNameDelimiter);
            fail();
        } catch (InvalidFormatException e) {
            assertEquals(e.getMessage(), Message.ERROR_PARSER_DELIMITER_NOT_FOUND + "/n"
                    + "\n" + ActivityCreateCommandParser.COMMAND_FORMAT
                    + ActivityCreateCommandParser.COMMAND_FORMAT_FIRST + "\n\t"
                    + ActivityCreateCommandParser.COMMAND_FORMAT_SECOND);
        }

        // Case 3: Missing /p delimiter
        String inputMissingPayerDelimiter = "activity /create /sid 1 /n Dinner /i Alice Bob Charlie /co 15";
        String argsMissingPayerDelimiter = Parser.getRemainingArgument(inputMissingPayerDelimiter);
        try {
            activityCreateCommandParser.getCommand(argsMissingPayerDelimiter);
            fail();
        } catch (InvalidFormatException e) {
            assertEquals(e.getMessage(), Message.ERROR_PARSER_DELIMITER_NOT_FOUND + "/p"
                    + "\n" + ActivityCreateCommandParser.COMMAND_FORMAT
                    + ActivityCreateCommandParser.COMMAND_FORMAT_FIRST + "\n\t"
                    + ActivityCreateCommandParser.COMMAND_FORMAT_SECOND);
        }

        // Case 4: Missing /i delimiter
        String inputMissingInvolvedListDelimiter = "activity /create /sid 1 /n Dinner /p Alice /co 15";
        String argsMissingInvolvedListDelimiter = Parser.getRemainingArgument(inputMissingInvolvedListDelimiter);
        try {
            activityCreateCommandParser.getCommand(argsMissingInvolvedListDelimiter);
            fail();
        } catch (InvalidFormatException e) {
            assertEquals(e.getMessage(), Message.ERROR_PARSER_DELIMITER_NOT_FOUND + "/i"
                    + "\n" + ActivityCreateCommandParser.COMMAND_FORMAT
                    + ActivityCreateCommandParser.COMMAND_FORMAT_FIRST + "\n\t"
                    + ActivityCreateCommandParser.COMMAND_FORMAT_SECOND);
        }
    }

    /**
     * Checks if an InvalidFormatException is thrown when missing arguments are provided by the user
     * and if the exception message is correct.
     */
    @Test
    public void getCommand_hasMissingArguments_InvalidCommand() {
        ActivityCreateCommandParser activityCreateCommandParser = new ActivityCreateCommandParser();

        // Case 1: Missing session ID
        String inputMissingSessionIdArgument = "activity /create /sid /n Dinner /p Alice /i Alice Bob Charlie /co 15";
        String argsMissingSessionIdArgument = Parser.getRemainingArgument(inputMissingSessionIdArgument);
        try {
            activityCreateCommandParser.getCommand(argsMissingSessionIdArgument);
            fail();
        } catch (InvalidFormatException e) {
            assertEquals(e.getMessage(), Message.ERROR_PARSER_MISSING_ARGUMENT + "/sid"
                    + "\n" + ActivityCreateCommandParser.COMMAND_FORMAT
                    + ActivityCreateCommandParser.COMMAND_FORMAT_FIRST + "\n\t"
                    + ActivityCreateCommandParser.COMMAND_FORMAT_SECOND);
        }

        // Case 2: Missing Activity Name
        String inputMissingNameArgument = "activity /create /sid 1 /n /p Alice /i Alice Bob Charlie /co 15";
        String argsMissingNameArgument = Parser.getRemainingArgument(inputMissingNameArgument);
        try {
            activityCreateCommandParser.getCommand(argsMissingNameArgument);
            fail();
        } catch (InvalidFormatException e) {
            assertEquals(e.getMessage(), Message.ERROR_PARSER_MISSING_ARGUMENT + "/n"
                    + "\n" + ActivityCreateCommandParser.COMMAND_FORMAT
                    + ActivityCreateCommandParser.COMMAND_FORMAT_FIRST + "\n\t"
                    + ActivityCreateCommandParser.COMMAND_FORMAT_SECOND);
        }

        // Case 3: Missing Payer
        String inputMissingPayerArgument = "activity /create /sid 1 /n Dinner /p /i Alice Bob Charlie /co 15";
        String argsMissingPayerArgument = Parser.getRemainingArgument(inputMissingPayerArgument);
        try {
            activityCreateCommandParser.getCommand(argsMissingPayerArgument);
            fail();
        } catch (InvalidFormatException e) {
            assertEquals(e.getMessage(), Message.ERROR_PARSER_MISSING_ARGUMENT + "/p"
                    + "\n" + ActivityCreateCommandParser.COMMAND_FORMAT
                    + ActivityCreateCommandParser.COMMAND_FORMAT_FIRST + "\n\t"
                    + ActivityCreateCommandParser.COMMAND_FORMAT_SECOND);
        }

        // Case 4: Missing involved list
        String inputMissingInvolvedListArgument = "activity /create /sid 1 /n Dinner /p Alice /i /co 15";
        String argsMissingInvolvedListArgument = Parser.getRemainingArgument(inputMissingInvolvedListArgument);
        try {
            activityCreateCommandParser.getCommand(argsMissingInvolvedListArgument);
            fail();
        } catch (InvalidFormatException e) {
            assertEquals(e.getMessage(), Message.ERROR_PARSER_MISSING_ARGUMENT + "/i"
                    + "\n" + ActivityCreateCommandParser.COMMAND_FORMAT
                    + ActivityCreateCommandParser.COMMAND_FORMAT_FIRST + "\n\t"
                    + ActivityCreateCommandParser.COMMAND_FORMAT_SECOND);
        }

        // Case 5: Missing total cost
        String inputMissingTotalCostArgument = "activity /create /sid 1 /n Dinner /p Alice /i Alice Bob Charlie /co";
        String argsMissingTotalCostArgument = Parser.getRemainingArgument(inputMissingTotalCostArgument);
        try {
            activityCreateCommandParser.getCommand(argsMissingTotalCostArgument);
            fail();
        } catch (InvalidFormatException e) {
            assertEquals(e.getMessage(), Message.ERROR_PARSER_MISSING_ARGUMENT + "/co"
                    + "\n" + ActivityCreateCommandParser.COMMAND_FORMAT
                    + ActivityCreateCommandParser.COMMAND_FORMAT_FIRST + "\n\t"
                    + ActivityCreateCommandParser.COMMAND_FORMAT_SECOND);
        }

        // Case 6: Missing cost list
        String inputMissingCostListArgument = "activity /create /sid 1 /n Dinner /p Alice /i Alice Bob Charlie /cl";
        String argsMissingCostListArgument = Parser.getRemainingArgument(inputMissingCostListArgument);
        try {
            activityCreateCommandParser.getCommand(argsMissingCostListArgument);
            fail();
        } catch (InvalidFormatException e) {
            assertEquals(e.getMessage(), Message.ERROR_PARSER_MISSING_ARGUMENT + "/cl"
                    + "\n" + ActivityCreateCommandParser.COMMAND_FORMAT
                    + ActivityCreateCommandParser.COMMAND_FORMAT_FIRST + "\n\t"
                    + ActivityCreateCommandParser.COMMAND_FORMAT_SECOND);
        }
    }
}