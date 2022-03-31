package seedu.duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import seedu.duke.command.AddCommand;
import seedu.duke.command.ByeCommand;
import seedu.duke.command.Command;
import seedu.duke.command.HelpCommand;

public class ParserTest {

    @Test
    public void parse_ByeCommand_parsedCorrectly() {
        final String input = "bye";
        parseAndAssertCommandType(input, ByeCommand.class);
    }

    @Test
    public void parse_helpCommand_parsedCorrectly() {
        final String input = "help";
        parseAndAssertCommandType(input, HelpCommand.class);
    }

    @Test
    public void parse_addCommandValidPersonData_parsedCorrectly() {
        final TravelPackage testPackage = generateTestTravelPackage();
        final String input = convertPersonToAddCommandString(testPackage);
        final AddCommand result = parseAndAssertCommandType(input, AddCommand.class);
        assertEquals(result.getPackage(), testPackage);
    }

    /**
     * Generates an instance of a {@code TravelPackage} from valid test data.
     * 
     * @return an instance of a {@code TravelPackage}.
     */
    private static TravelPackage generateTestTravelPackage() {
        try {
            return new TravelPackage(
                    TravelPackage.EXAMPLENAME,
                    TravelPackage.EXAMPLEID,
                    TravelPackage.EXAMPLESTART,
                    TravelPackage.EXAMPLEEND,
                    TravelPackage.EXAMPLEHOTEL,
                    TravelPackage.EXAMPLEPRICE,
                    TravelPackage.EXAMPLECOUNTRY,
                    TravelPackage.EXAMPLEMAX);
        } catch (Exception e) {
            throw new RuntimeException("test person data should be valid by definition");
        }
    }

    /**
     * Generates an add command from a {@code TravelPackage}.
     * 
     * @param t
     *            whose data will be filled into the string.
     * @return a string describing an {@code AddCommand}.
     */
    private static String convertPersonToAddCommandString(TravelPackage t) {
        String addCommand = "add " + t.getName() + "," + t.getID() + "," + t.getStartDate() + "," + t.getEndDate() + ","
                +
                t.getHotel() + "," + t.getPrice() + "," + t.getCountry() + "," + t.getMaxParticipants();
        return addCommand;
    }

    /**
     * Parses input and asserts the class/type of the returned command object.
     *
     * @param input
     *            to be parsed
     * @param expectedCommandClass
     *            expected class of returned command
     * @return the parsed command object
     */
    private <T extends Command> T parseAndAssertCommandType(String input, Class<T> expectedCommandClass) {
        final Command result = Parser.parse(input);
        assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
        return (T) result;
    }
}
