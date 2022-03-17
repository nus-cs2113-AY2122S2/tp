package seedu.splitlah.parser;

import org.junit.jupiter.api.Test;
import seedu.splitlah.command.Command;
import seedu.splitlah.command.InvalidCommand;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.ui.Message;

import java.time.LocalDate;

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

    // parsePayer()
    /**
     * Checks if an exception is properly thrown when the Payer delimiter is not provided by the user.
     */
    @Test
    void parsePayer_missingDelimiter_exceptionThrown() {
        String argumentWithoutPayerDelimiter = "/sid 1 /n Lunch p Alice /i Alice Bob Charlie /co 15";
        try {
            String output = Parser.parsePayer(argumentWithoutPayerDelimiter);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_DELIMITER_NOT_FOUND + Parser.PAYER_DELIMITER;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if an exception is properly thrown when the Payer delimiter is provided but no arguments
     * following the Payer delimiter are provided by the user.
     */
    @Test
    void parsePayer_delimiterExistsWithoutArgument_exceptionThrown() {
        String argumentWithoutPayerArgument = "/sid 1 /n Lunch /p /i Alice Bob Charlie /co 15";
        try {
            String output = Parser.parsePayer(argumentWithoutPayerArgument);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_MISSING_ARGUMENT + Parser.PAYER_DELIMITER;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if an exception is properly thrown when the Payer delimiter is provided but multiple names are
     * provided following the Payer delimiter.
     */
    @Test
    void parsePayer_delimiterExistsMultiplePayers_exceptionThrown() {
        String argumentWithoutPayerArgument = "/sid 1 /n Lunch /p Alice Bob /i Alice Bob Charlie /co 15";
        try {
            String output = Parser.parsePayer(argumentWithoutPayerArgument);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_MORE_THAN_ONE_PAYER;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if a String object containing the payer name is properly returned when the Payer delimiter and argument
     * following it are properly provided by the user.
     */
    @Test
    void parsePayer_delimiterExistsSinglePayer_argumentString() {
        String argumentWithDelimiterAndArgument = "/sid 1 /n Lunch /p Alice /i Alice Bob Charlie /co 15";
        try {
            String output = Parser.parsePayer(argumentWithDelimiterAndArgument);
            assertEquals("Alice", output);
        } catch (InvalidFormatException exception) {
            fail();
        }
    }

    // parseSessionId()
    /**
     * Checks if an exception is properly thrown when the Session Id delimiter is not provided by the user.
     */
    @Test
    void parseSessionId_missingDelimiter_exceptionThrown() {
        String argumentWithoutSessionIdDelimiter = "sid 1 /n Lunch /p Alice /i Alice Bob Charlie /co 15";
        try {
            int output = Parser.parseSessionId(argumentWithoutSessionIdDelimiter);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_DELIMITER_NOT_FOUND + Parser.SESSION_ID_DELIMITER;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if an exception is properly thrown when the Session Id delimiter is provided but no arguments
     * following the delimiter are provided by the user.
     */
    @Test
    void parseSessionId_delimiterExistsWithoutArgument_exceptionThrown() {
        String argumentWithoutSessionIdArgument = "/sid  /n Lunch /p Alice /i Alice Bob Charlie /co 15";
        try {
            int output = Parser.parseSessionId(argumentWithoutSessionIdArgument);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_MISSING_ARGUMENT + Parser.SESSION_ID_DELIMITER;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if an exception is properly thrown when the Session Id delimiter is provided but a non-integer
     * argument is provided by the user.
     */
    @Test
    void parseSessionId_delimiterExistsArgumentNotInteger_exceptionThrown() {
        String argumentWithNonIntArgument = "/sid a1 /n Lunch /p Alice /i Alice Bob Charlie /co 15";
        try {
            int output = Parser.parseSessionId(argumentWithNonIntArgument);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_NON_INTEGER_ARGUMENT + Parser.SESSION_ID_DELIMITER;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if an exception is properly thrown when the Session Id delimiter is provided but a negative integer
     * argument is provided by the user.
     */
    @Test
    void parseSessionId_delimiterExistsArgumentNegativeInteger_exceptionThrown() {
        String argumentWithNegativeIntArgument = "/sid -9 /n Lunch /p Alice /i Alice Bob Charlie /co 15";
        try {
            int output = Parser.parseSessionId(argumentWithNegativeIntArgument);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_ID_VALUE_NOT_POSITIVE;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if an integer representing a session unique identifier is properly returned when the
     * Session Id delimiter is provided along with a positive integer as the argument by the user.
     */
    @Test
    void parseSessionId_delimiterExistsArgumentPositiveInteger_sessionId() {
        String argumentWithDelimiterAndPositiveInt = "/sid 3 /n Lunch /p Alice /i Alice Bob Charlie /co 15";
        try {
            int output = Parser.parseSessionId(argumentWithDelimiterAndPositiveInt);
            assertEquals(3, output);
        } catch (InvalidFormatException exception) {
            fail();
        }
    }

    // parseActivityId()
    /**
     * Checks if an exception is properly thrown when the Activity Id delimiter is not provided by the user.
     */
    @Test
    void parseActivityId_missingDelimiter_exceptionThrown() {
        String argumentWithoutActivityIdDelimiter = "/sid 1 aid 5";
        try {
            int output = Parser.parseActivityId(argumentWithoutActivityIdDelimiter);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_DELIMITER_NOT_FOUND + Parser.ACTIVITY_ID_DELIMITER;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if an exception is properly thrown when the Activity Id delimiter is provided but no arguments
     * following the delimiter are provided by the user.
     */
    @Test
    void parseActivityId_delimiterExistsWithoutArgument_exceptionThrown() {
        String argumentWithoutActivityIdArgument = "/sid 1 /aid ";
        try {
            int output = Parser.parseActivityId(argumentWithoutActivityIdArgument);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_MISSING_ARGUMENT + Parser.ACTIVITY_ID_DELIMITER;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if an exception is properly thrown when the Activity Id delimiter is provided but a non-integer
     * argument is provided by the user.
     */
    @Test
    void parseActivityId_delimiterExistsArgumentNotInteger_exceptionThrown() {
        String argumentWithNonIntArgument = "/sid 1 /aid a5";
        try {
            int output = Parser.parseActivityId(argumentWithNonIntArgument);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_NON_INTEGER_ARGUMENT + Parser.ACTIVITY_ID_DELIMITER;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if an exception is properly thrown when the Activity Id delimiter is provided but a negative integer
     * argument is provided by the user.
     */
    @Test
    void parseActivityId_delimiterExistsArgumentNegativeInteger_exceptionThrown() {
        String argumentWithNegativeIntArgument = "/sid 1 /aid -5";
        try {
            int output = Parser.parseActivityId(argumentWithNegativeIntArgument);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_ID_VALUE_NOT_POSITIVE;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if an integer representing a activity unique identifier is properly returned when the
     * Activity Id delimiter is provided along with a positive integer as the argument by the user.
     */
    @Test
    void parseActivityId_delimiterExistsArgumentPositiveInteger_activityId() {
        String argumentWithDelimiterAndPositiveInt = "/sid 1 /aid 5";
        try {
            int output = Parser.parseActivityId(argumentWithDelimiterAndPositiveInt);
            assertEquals(5, output);
        } catch (InvalidFormatException exception) {
            fail();
        }
    }

    // parseLocalDate()
    /**
     * Checks if an exception is properly thrown when the Date delimiter is not provided by the user.
     */
    @Test
    void parseLocalDate_missingDelimiter_exceptionThrown() {
        String argumentWithoutDateDelimiter = "/n Class outing d 23-02-2022 /pl Alice Alice Bob";
        try {
            LocalDate output = Parser.parseLocalDate(argumentWithoutDateDelimiter);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_DELIMITER_NOT_FOUND + Parser.DATE_DELIMITER;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if an exception is properly thrown when the Date delimiter is provided but no arguments
     * following the Date delimiter are provided by the user.
     */
    @Test
    void parseLocalDate_delimiterExistsWithoutArgument_exceptionThrown() {
        String argumentWithoutDateArgument = "/n Class outing /d /pl Alice Alice Bob";
        try {
            LocalDate output = Parser.parseLocalDate(argumentWithoutDateArgument);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_MISSING_ARGUMENT + Parser.DATE_DELIMITER;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if an exception is properly thrown when the Date delimiter is provided by the user but the argument
     * following the Date delimiter is a date of invalid formatting.
     */
    @Test
    void parseLocalDate_invalidDateFormatting_exceptionThrown() {
        String argumentWithInvalidDateFormatting = "/n Class outing /d 2022-03-04 /pl Alice Alice Bob";
        try {
            LocalDate output = Parser.parseLocalDate(argumentWithInvalidDateFormatting);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_INVALID_DATE_FORMAT;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if a LocalDate object containing the specified date in correct formatting is properly returned
     * when the Date delimiter and arguments following it are properly provided by the user.
     */
    @Test
    void parseLocalDate_validDateFormatting_validDate() {
        String argumentWithValidDateFormatting = "/n Class outing /d 23-02-2022 /pl Alice Alice Bob";
        try {
            LocalDate output = Parser.parseLocalDate(argumentWithValidDateFormatting);
            assertEquals(23, output.getDayOfMonth());
            assertEquals(2, output.getMonthValue());
            assertEquals(2022, output.getYear());
        } catch (InvalidFormatException exception) {
            fail();
        }
    }

    /**
     * Checks if a LocalDate object containing the current date is properly returned
     * when the Date delimiter and "today" as argument are properly provided by the user.
     */
    @Test
    void parseLocalDate_todayAsInput_validDate() {
        String argumentWithTodayAsDate = "/n Class outing /d today /pl Alice Alice Bob";
        try {
            LocalDate output = Parser.parseLocalDate(argumentWithTodayAsDate);
            LocalDate today = LocalDate.now();
            assertEquals(today, output);
        } catch (InvalidFormatException exception) {
            fail();
        }
    }

    // parseTotalCost()
    /**
     * Checks if an exception is properly thrown when the Total cost delimiter is not provided by the user.
     */
    @Test
    void parseTotalCost_missingDelimiter_exceptionThrown() {
        String argumentWithoutTotalCostDelimiter = "/sid 3 /n Lunch /p Alice /i Alice Bob Charlie co 15";
        try {
            double output = Parser.parseTotalCost(argumentWithoutTotalCostDelimiter);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_DELIMITER_NOT_FOUND + Parser.TOTAL_COST_DELIMITER;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if an exception is properly thrown when the Total cost delimiter is provided but no arguments
     * following the Total cost delimiter are provided by the user.
     */
    @Test
    void parseTotalCost_delimiterExistsWithoutArgument_exceptionThrown() {
        String argumentWithoutTotalCostArgument = "/sid 3 /n Lunch /p Alice /i Alice Bob Charlie /co ";
        try {
            double output = Parser.parseTotalCost(argumentWithoutTotalCostArgument);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_MISSING_ARGUMENT + Parser.TOTAL_COST_DELIMITER;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if an exception is properly thrown when the Total cost delimiter is provided by the user but the 
     * argument following the Total cost delimiter is non-numeric.
     */
    @Test
    void parseTotalCost_delimiterExistsArgumentNotNumeric_exceptionThrown() {
        String argumentWithNonNumericArgument = "/sid 3 /n Lunch /p Alice /i Alice Bob Charlie /co apple";
        try {
            double output = Parser.parseTotalCost(argumentWithNonNumericArgument);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_NON_MONETARY_VALUE_ARGUMENT + Parser.TOTAL_COST_DELIMITER;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if an exception is properly thrown when the Total cost delimiter is provided by the user but the
     * argument following the Total cost delimiter is a negative numeric value.
     */
    @Test
    void parseTotalCost_delimiterExistsArgumentNegative_exceptionThrown() {
        String argumentWithNegativeArgument = "/sid 3 /n Lunch /p Alice /i Alice Bob Charlie /co -1.24";
        try {
            double output = Parser.parseTotalCost(argumentWithNegativeArgument);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_COST_NOT_POSITIVE;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if an exception is properly thrown when the Total cost delimiter and a positive numeric value are
     * provided as arguments, but the numeric value has more than two decimal places.
     */
    @Test
    void parseTotalCost_delimiterExistsArgumentPositiveMoreThanTwoDecimalPlaces_exceptionThrown() {
        String argumentWithPositiveArgumentMoreThan2DP = "/sid 3 /n Lunch /p Alice /i Alice Bob Charlie /co 1.2444";
        try {
            double output = Parser.parseTotalCost(argumentWithPositiveArgumentMoreThan2DP);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_COST_NOT_TWO_DP;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if an exception is properly thrown when the Total cost delimiter and a positive numeric value are
     * provided as arguments, but the numeric value has more than twelve digits before decimal point.
     */
    @Test
    void parseTotalCost_delimiterExistsArgumentPositiveMoreThanTwelveDigitsBeforeDecimalPoint_exceptionThrown() {
        String argumentWithPositiveArgumentMoreThan12DigitsBeforeDP =
                "/sid 3 /n Lunch /p Alice /i Alice Bob Charlie /co 1234567890123.1";
        try {
            double output = Parser.parseTotalCost(argumentWithPositiveArgumentMoreThan12DigitsBeforeDP);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_COST_MORE_THAN_TWELVE_DIGITS_BEFORE_DP;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if a double containing the total cost as stated is properly returned when the Total cost delimiter
     * and a valid positive numeric value as argument are properly provided by the user.
     * A valid positive numeric value has at most twelve digits before the decimal point and at most 2 decimal places.
     */
    @Test
    void parseTotalCost_delimiterExistsArgumentValid_totalCost() {
        // Testing numerical limits
        String argumentWithDelimiterAndValidArgumentTestLimit =
                "/sid 3 /n Lunch /p Alice /i Alice Bob Charlie /co 123456789012.99";
        try {
            double output = Parser.parseTotalCost(argumentWithDelimiterAndValidArgumentTestLimit);
            assertEquals(123456789012.99, output);
        } catch (InvalidFormatException exception) {
            fail();
        }

        // Testing with no decimal places
        String argumentWithDelimiterAndValidArgumentTestNoDecimal =
                "/sid 3 /n Lunch /p Alice /i Alice Bob Charlie /co 123456789012";
        try {
            double output = Parser.parseTotalCost(argumentWithDelimiterAndValidArgumentTestNoDecimal);
            assertEquals(123456789012.0, output);
        } catch (InvalidFormatException exception) {
            fail();
        }

        // Testing with commonly used values
        String argumentWithDelimiterAndValidArgumentTestNormal =
                "/sid 3 /n Lunch /p Alice /i Alice Bob Charlie /co 10.70";
        try {
            double output = Parser.parseTotalCost(argumentWithDelimiterAndValidArgumentTestNormal);
            assertEquals(10.70, output);
        } catch (InvalidFormatException exception) {
            fail();
        }
    }

    // parseCostList()
    /**
     * Checks if an exception is properly thrown when the Cost list delimiter is not provided by the user.
     */
    @Test
    void parseCostList_missingDelimiter_exceptionThrown() {
        String argumentWithoutCostListDelimiter = "/sid 3 /n Lunch /p Alice /i Alice Bob Charlie cl 10 10 10";
        try {
            double[] output = Parser.parseCostList(argumentWithoutCostListDelimiter);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_DELIMITER_NOT_FOUND + Parser.COST_LIST_DELIMITER;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if an exception is properly thrown when the Cost list delimiter is provided but no arguments
     * following the Cost list delimiter are provided by the user.
     */
    @Test
    void parseCostList_delimiterExistsWithoutArgument_exceptionThrown() {
        String argumentWithoutCostListArguments = "/sid 3 /n Lunch /p Alice /i Alice Bob Charlie /cl ";
        try {
            double[] output = Parser.parseCostList(argumentWithoutCostListArguments);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_MISSING_ARGUMENT + Parser.COST_LIST_DELIMITER;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if an exception is properly thrown when the Cost list delimiter is provided by the user but the 
     * arguments following the Cost list delimiter are non-numeric.
     */
    @Test
    void parseCostList_delimiterExistsArgumentsNotNumeric_exceptionThrown() {
        String argumentWithNonNumericArguments = "/sid 3 /n Lunch /p Alice /i Alice Bob Charlie /cl apple orange";
        try {
            double[] output = Parser.parseCostList(argumentWithNonNumericArguments);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_NON_MONETARY_VALUE_ARGUMENT + Parser.COST_LIST_DELIMITER;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if an exception is properly thrown when the Cost list delimiter is provided by the user but the 
     * argument following the Cost list delimiter contain negative numeric values.
     */
    @Test
    void parseCostList_delimiterExistsCostsNegative_exceptionThrown() {
        String argumentWithNegativeArguments =
                "/sid 3 /n Lunch /p Alice /i Alice Bob Charlie /cl 10 -10 10";
        try {
            double[] output = Parser.parseCostList(argumentWithNegativeArguments);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_COST_NOT_POSITIVE;
            assertEquals(errorMessage, exception.getMessage());
        }
    }
    
    /**
     * Checks if an exception is properly thrown when the Cost list delimiter and positive numeric values are
     * provided as arguments, but some numeric values has more than two decimal places.
     */
    @Test
    void parseCostList_delimiterExistsArgumentPositiveMoreThanTwoDecimalPlaces_exceptionThrown() {
        String argumentWithPositiveArgumentsMoreThan2DP =
                "/sid 3 /n Lunch /p Alice /i Alice Bob Charlie /cl 1.2444 15.30 20";
        try {
            double[] output = Parser.parseCostList(argumentWithPositiveArgumentsMoreThan2DP);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_COST_NOT_TWO_DP;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if an exception is properly thrown when the Cost list delimiter and positive numeric values are
     * provided as arguments, but some numeric values has more than twelve digits before decimal point.
     */
    @Test
    void parseCostList_delimiterExistsArgumentPositiveMoreThanTwelveDigitsBeforeDecimalPoint_exceptionThrown() {
        String argumentWithPositiveArgumentsMoreThan12DigitsBeforeDP =
                "/sid 3 /n Lunch /p Alice /i Alice Bob Charlie /cl 1234567890123.1 15.30 20";
        try {
            double[] output = Parser.parseCostList(argumentWithPositiveArgumentsMoreThan12DigitsBeforeDP);
            fail();
        } catch (InvalidFormatException exception) {
            String errorMessage = Message.ERROR_PARSER_COST_MORE_THAN_TWELVE_DIGITS_BEFORE_DP;
            assertEquals(errorMessage, exception.getMessage());
        }
    }

    /**
     * Checks if a double array object containing individual cost as stated is properly returned when the 
     * Cost list delimiter and valid positive numeric values as arguments are properly provided by the user.
     * A valid positive numeric value has at most twelve digits before the decimal point and at most 2 decimal places.
     */
    @Test
    void parseCostList_delimiterExistsArgumentValid_doubleArrayContainingCostList() {
        String argumentWithDelimiterAndValidArguments =
                "/sid 3 /n Lunch /p Alice /i Alice Bob Charlie /cl 123456789012.34 123456789012 10.70";
        try {
            double[] output = Parser.parseCostList(argumentWithDelimiterAndValidArguments);
            assertEquals(3, output.length);
            assertEquals(123456789012.34, output[0]);
            assertEquals(123456789012.0, output[1]);
            assertEquals(10.70, output[2]);
        } catch (InvalidFormatException exception) {
            fail();
        }
    }
}
