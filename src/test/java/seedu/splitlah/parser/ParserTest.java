package seedu.splitlah.parser;

import org.junit.jupiter.api.Test;
import seedu.splitlah.command.Command;
import seedu.splitlah.command.InvalidCommand;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.ui.Message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {

    // getCommand()
    /**
     * Checks if an InvalidCommand is returned when an empty String object is provided by the user.
     */
    @Test
    void getCommand_emptyString_InvalidCommand() {
        String emptyString = "";
        Command command = Parser.getCommand(emptyString);
        assertEquals(InvalidCommand.class, command.getClass());
    }

    /**
     * Checks if an InvalidCommand is returned when a String object containing only whitespaces is provided by the user.
     */
    @Test
    void getCommand_whitespaceInput_InvalidCommand() {
        String whitespaceString = "     ";
        Command command = Parser.getCommand(whitespaceString);
        assertEquals(InvalidCommand.class, command.getClass());
    }

    // getCommandType()
    /**
     * Checks if an empty String object is returned as the command string
     * when an empty String object is provided by the user.
     */
    @Test
    void getCommandType_emptyStringInput_emptyString() {
        String emptyString = "";
        String output = Parser.getCommandType(emptyString);
        assertEquals("", output);
    }

    /**
     * Checks if a String object containing identical contents as the input is returned as the command string
     * when a single token of input is provided by the user.
     * Matching command types: "help", "list", "exit"
     */
    @Test
    void getCommandType_singleTokenInput_inputEqualsOutput() {
        String singleTokenString = "randomTest123";
        String output = Parser.getCommandType(singleTokenString);
        assertEquals(singleTokenString, output);
    }

    /**
     * Checks if null is returned when an input, with a second token does not start with a delimiter,
     * is provided by the user.
     */
    @Test
    void getCommandType_doubleTokenNoDelimiterInput_null() {
        String doubleTokenWithNoDelimiterString = "help apple";
        String output = Parser.getCommandType(doubleTokenWithNoDelimiterString);
        assertNull(output);
    }

    // getRemainingArgument()
    /**
     * Checks if an empty String object is returned as the remaining arguments of the command
     * when an empty String object is provided by the user.
     */
    @Test
    void getRemainingArgument_emptyStringInput_emptyString() {
        String emptyString = "";
        String output = Parser.getRemainingArgument(emptyString);
        assertEquals("", output);
    }

    /**
     * Checks if an empty String object is returned as the remaining arguments of the command
     * when an input of only a single token is provided by the user.
     */
    @Test
    void getRemainingArgument_singleTokenInput_emptyString() {
        String singleTokenString = "randomTest123";
        String output = Parser.getRemainingArgument(singleTokenString);
        assertEquals("", output);
    }

    /**
     * Checks if an empty String object is returned as the remaining arguments of the command
     * when an input of only two tokens is provided by the user.
     */
    @Test
    void getRemainingArgument_twoInputTokens_emptyString() {
        String twoInputTokensString = "brownFox jumpsOver";
        String output = Parser.getRemainingArgument(twoInputTokensString);
        assertEquals("", output);
    }

    /**
     * Checks if the third token is correctly returned as the remaining arguments of the command
     * when an input of three tokens and multiple whitespaces is provided by the user.
     */
    @Test
    void getRemainingArgument_threeInputTokensAndWhitespace_thirdToken() {
        String threeInputTokensAndWhitespaceString = "brownFox jumpsOver theLazyDog  ";
        String output = Parser.getRemainingArgument(threeInputTokensAndWhitespaceString);
        assertEquals("theLazyDog", output);
    }

    /**
     * Checks if the third token and onwards is correctly returned as the remaining arguments of the command
     * when an input of four tokens is provided by the user.
     */
    @Test
    void getRemainingArgument_fourInputTokens_thirdTokenOnwards() {
        String fourInputTokensString = "brownFox jumpsOver theLazy Dog";
        String output = Parser.getRemainingArgument(fourInputTokensString);
        assertEquals("theLazy Dog", output);
    }

    //  parseName()
    /**
     * Checks if an exception is properly thrown when the Name delimiter is not provided by the user.
     */
    @Test
    void parseName_missingDelimiter_exceptionThrown() {
        String argumentWithoutNameDelimiter = "n Class outing /d 23-02-2022 /pl Alice Alice Bob";
        try {
            String output = Parser.parseName(argumentWithoutNameDelimiter);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_DELIMITER_NOT_FOUND + Parser.NAME_DELIMITER;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if an exception is properly thrown when the Name delimiter is provided but no arguments
     * following the Name delimiter are provided by the user.
     */
    @Test
    void parseName_delimiterExistsWithoutArgument_exceptionThrown() {
        String argumentWithoutNameArgument = "/n /d 23-02-2022 /pl Alice Alice Bob";
        try {
            String output = Parser.parseName(argumentWithoutNameArgument);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_MISSING_ARGUMENT + Parser.NAME_DELIMITER;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if a String object containing the name is properly returned when the Name delimiter and arguments
     * following it are properly provided by the user.
     */
    @Test
    void parseName_delimiterAndArgumentExists_argumentString() {
        String argumentWithDelimiterAndArgument = "/n Class outing /d 23-02-2022 /pl Alice Alice Bob";
        try {
            String output = Parser.parseName(argumentWithDelimiterAndArgument);
            assertEquals("Class outing", output);
        } catch (InvalidFormatException exception) {
            fail();
        }
    }

    // parsePersonList()
    /**
     * Checks if an exception is properly thrown when the Person list delimiter is not provided by the user.
     */
    @Test
    void parsePersonList_missingDelimiter_exceptionThrown() {
        String argumentWithoutPersonListDelimiter = "/n Class outing /d 23-02-2022 pl Alice Alice Bob";
        try {
            String[] output = Parser.parsePersonList(argumentWithoutPersonListDelimiter);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_DELIMITER_NOT_FOUND + Parser.PERSON_LIST_DELIMITER;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if an exception is properly thrown when the Person list delimiter is provided but no arguments
     * following the Person list delimiter are provided by the user.
     */
    @Test
    void parsePersonList_delimiterExistsWithoutArgument_exceptionThrown() {
        String argumentWithoutPersonListArgument = "/n Class outing /d 23-02-2022 /pl";
        try {
            String[] output = Parser.parsePersonList(argumentWithoutPersonListArgument);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_MISSING_ARGUMENT + Parser.PERSON_LIST_DELIMITER;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if a String array object containing names of persons is properly returned when the Person list delimiter
     * and arguments following it are properly provided by the user.
     */
    @Test
    void parsePersonList_delimiterAndArgumentExists_personList() {
        String argumentWithDelimiterAndArgument = "/n Class outing /d 23-02-2022 /pl Alice Charles Bob";
        try {
            String[] output = Parser.parsePersonList(argumentWithDelimiterAndArgument);
            assertEquals(3, output.length);
            assertEquals("Alice", output[0]);
            assertEquals("Charles", output[1]);
            assertEquals("Bob", output[2]);
        } catch (InvalidFormatException exception) {
            fail();
        }
    }
    
    // parseInvolved()
    /**
     * Checks if an exception is properly thrown when the Involved delimiter is not provided by the user.
     */
    @Test
    void parseInvolved_missingDelimiter_exceptionThrown() {
        String argumentWithoutInvolvedDelimiter = "/sid 1 /n Lunch /p Alice i Alice Bob Charlie /co 15";
        try {
            String[] output = Parser.parseInvolved(argumentWithoutInvolvedDelimiter);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_DELIMITER_NOT_FOUND + Parser.INVOLVED_DELIMITER;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if an exception is properly thrown when the Involved delimiter is provided but no arguments
     * following the Involved delimiter are provided by the user.
     */
    @Test
    void parseInvolved_delimiterExistsWithoutArgument_exceptionThrown() {
        String argumentWithoutInvolvedArgument = "/sid 1 /n Lunch /p Alice /i /co 15";
        try {
            String[] output = Parser.parseInvolved(argumentWithoutInvolvedArgument);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_MISSING_ARGUMENT + Parser.INVOLVED_DELIMITER;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if a String array object containing names of persons is properly returned when the Involved delimiter
     * and arguments following it are properly provided by the user.
     */
    @Test
    void parseInvolved_delimiterAndArgumentExists_personList() {
        String argumentWithDelimiterAndArgument = "/sid 1 /n Lunch /p Alice /i Alice Bob Charlie /co 15";
        try {
            String[] output = Parser.parseInvolved(argumentWithDelimiterAndArgument);
            assertEquals(3, output.length);
            assertEquals("Alice", output[0]);
            assertEquals("Bob", output[1]);
            assertEquals("Charlie", output[2]);
        } catch (InvalidFormatException exception) {
            fail();
        }
    }

}
