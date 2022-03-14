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
    
    @Test
    void parseName_delimiterExistsWithoutArgument_exceptionThrown() {
        String argumentWithoutNameDelimiter = "/n /d 23-02-2022 /pl Alice Alice Bob";
        try {
            String output = Parser.parseName(argumentWithoutNameDelimiter);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_MISSING_ARGUMENT + Parser.NAME_DELIMITER;
            assertEquals(errorMessage, exception.getMessage());
        }
    }
    
    @Test
    void parseName_delimiterAndArgumentExists_argumentString() {
        String argumentWithoutNameDelimiter = "/n Class outing /d 23-02-2022 /pl Alice Alice Bob";
        try {
            String output = Parser.parseName(argumentWithoutNameDelimiter);
            assertEquals("Class outing", output);
        } catch (InvalidFormatException exception) {
            fail();
        }
    }
}
