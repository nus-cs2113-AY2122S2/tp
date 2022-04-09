package seedu.sherpass.util.parser;

import org.junit.jupiter.api.Test;
import seedu.sherpass.command.Command;
import seedu.sherpass.command.ShowCommand;

import javax.lang.model.type.NullType;
import java.time.LocalDate;
import java.time.format.ResolverStyle;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sherpass.constant.DateAndTimeFormat.inputWithoutTimeFormat;

public class TimetableParserTest {

    @Test
    void prepareShow_Expects_todayShowCommand() {
        String[] input = new String[] {"show", "today"};

        Command test = TimetableParser.prepareShow(input);
        assertEquals(test, new ShowCommand(null, "today"));
    }

    @Test
    void prepareShow_Expects_monthlyShowCommand() {
        String[] input = new String[] {"show", "Jan"};

        Command test = TimetableParser.prepareShow(input);
        assertEquals(test, new ShowCommand(null, "jan"));
    }

    @Test
    void prepareShow_Expects_dateShowCommand() {
        String[] input = new String[] {"show", "8/5/2022"};

        Command test = TimetableParser.prepareShow(input);
        assertEquals(test, new ShowCommand(LocalDate.parse("8/5/2022",
                inputWithoutTimeFormat.withResolverStyle(ResolverStyle.STRICT)), null));
    }
}
