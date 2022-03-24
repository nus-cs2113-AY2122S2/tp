package seedu.splitlah.parser.commandparser;

import org.junit.jupiter.api.Test;
import seedu.splitlah.command.Command;
import seedu.splitlah.command.InvalidCommand;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.ui.Message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ActivityCreateCommandParserTest {

    /**
     * Checks if an InvalidCommand object is returned when both cost and cost list are not provided by the user.
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
     * Checks if an InvalidCommand object is returned when both cost and cost list are provided by the user.
     */
    @Test
    public void getCommand_hasBothCostAndCostList_InvalidCommand() {
        String userInput = "activity /create /sid 1 /n Dinner /p Alice /i Alice Bob Charlie /co 30 /cl 10 10 10";
        Command command = Parser.getCommand(userInput);
        assertEquals(InvalidCommand.class, command.getClass());
    }

    /**
     * Checks if an InvalidCommand object is returned when cost list and involved list that are provided by the user
     * are of different lengths.
     */
    @Test
    public void getCommand_costListAndInvolvedListDifferentLength_InvalidCommand() {
        String firstUserInput = "activity /create /sid 1 /n Dinner /p Alice /i Alice Bob Charlie /cl 10 10";
        Command firstCommand = Parser.getCommand(firstUserInput);
        assertEquals(InvalidCommand.class, firstCommand.getClass());
        String secondUserInput = "activity /create /sid 1 /n Dinner /p Alice /i Alice Bob /cl 10 10 10";
        Command secondCommand = Parser.getCommand(secondUserInput);
        assertEquals(InvalidCommand.class, secondCommand.getClass());
    }

    /**
     * Checks if an InvalidCommand object is returned when there are delimiters not provided by the user.
     */
    @Test
    public void getCommand_hasMissingDelimiter_InvalidCommand() {
        // Case 1: Missing /sid delimiter
        String argsMissingSessionIdDelimiter = "activity /create /n Dinner /p Alice /i Alice Bob Charlie /co 15";
        Command sessionWithMissingSessionIdDelimiter = Parser.getCommand(argsMissingSessionIdDelimiter);
        assertEquals(InvalidCommand.class, sessionWithMissingSessionIdDelimiter.getClass());

        // Case 2: Missing /n delimiter
        String argsMissingNameDelimiter = "activity /create /sid 1 /p Alice /i Alice Bob Charlie /co 15";
        Command sessionWithMissingNameDelimiter = Parser.getCommand(argsMissingNameDelimiter);
        assertEquals(InvalidCommand.class, sessionWithMissingNameDelimiter.getClass());

        // Case 3: Missing /p delimiter
        String argsMissingPayerDelimiter = "activity /create /sid 1 /n Dinner /i Alice Bob Charlie /co 15";
        Command sessionWithMissingPayerDelimiter = Parser.getCommand(argsMissingPayerDelimiter);
        assertEquals(InvalidCommand.class, sessionWithMissingPayerDelimiter.getClass());

        // Case 4: Missing /i delimiter
        String argsMissingInvolvedListDelimiter = "activity /create /sid 1 /n Dinner /p Alice /co 15";
        Command activityWithMissingInvolvedListDelimiter = Parser.getCommand(argsMissingInvolvedListDelimiter);
        assertEquals(InvalidCommand.class, activityWithMissingInvolvedListDelimiter.getClass());
    }

    /**
     * Checks if an InvalidCommand object is returned when there are arguments not provided by the user.
     */
    @Test
    public void getCommand_hasMissingArguments_InvalidCommand() {
        // Case 1: Missing session ID
        String argsMissingSessionIdArgument = "activity /create /sid /n Dinner /p Alice /i Alice Bob Charlie /co 15";
        Command sessionWithMissingSessionIdArgument = Parser.getCommand(argsMissingSessionIdArgument);
        assertEquals(InvalidCommand.class, sessionWithMissingSessionIdArgument.getClass());

        // Case 2: Missing Activity Name
        String argsMissingNameArgument = "activity /create /sid 1 /n /p Alice /i Alice Bob Charlie /co 15";
        Command sessionWithMissingNameArgument = Parser.getCommand(argsMissingNameArgument);
        assertEquals(InvalidCommand.class, sessionWithMissingNameArgument.getClass());

        // Case 3: Missing Payer
        String argsMissingPayerArgument = "activity /create /sid 1 /n Dinner /p /i Alice Bob Charlie /co 15";
        Command sessionWithMissingPayerArgument = Parser.getCommand(argsMissingPayerArgument);
        assertEquals(InvalidCommand.class, sessionWithMissingPayerArgument.getClass());

        // Case 4: Missing involved list
        String argsMissingInvolvedListArgument = "activity /create /sid 1 /n Dinner /p Alice /i /co 15";
        Command activityWithMissingInvolvedListArgument = Parser.getCommand(argsMissingInvolvedListArgument);
        assertEquals(InvalidCommand.class, activityWithMissingInvolvedListArgument.getClass());

        // Case 5: Missing total cost
        String argsMissingTotalCostArgument = "activity /create /sid 1 /n Dinner /p Alice /i Alice Bob Charlie /co";
        Command activityWithMissingTotalCostArgument = Parser.getCommand(argsMissingTotalCostArgument);
        assertEquals(InvalidCommand.class, activityWithMissingTotalCostArgument.getClass());

        // Case 6: Missing cost list
        String argsMissingCostListArgument = "activity /create /sid 1 /n Dinner /p Alice /i Alice Bob Charlie /cl";
        Command activityWithMissingCostListArgument = Parser.getCommand(argsMissingCostListArgument);
        assertEquals(InvalidCommand.class, activityWithMissingCostListArgument.getClass());
    }
}