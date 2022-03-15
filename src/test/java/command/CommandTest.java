package command;

import commands.Command;
import org.junit.jupiter.api.Test;
import seedu.planitarium.person.PersonList;

public class CommandTest {
    String ADD_PERSON = "add /n Alice";
    PersonList personList;
    Command c = new Command(ADD_PERSON, personList);

    @Test
    void execute() {
    }
}
