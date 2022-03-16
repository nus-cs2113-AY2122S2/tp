package command;

import org.junit.jupiter.api.Assertions;
import seedu.planitarium.commands.AddPersonCommand;
import seedu.planitarium.commands.Command;
import org.junit.jupiter.api.Test;
import seedu.planitarium.person.PersonList;

import static org.junit.jupiter.api.Assertions.fail;

public class AddPersonCommandTest {

    AddPersonCommand addPersonCommand;
    String ADD_PERSON = "add /n Alice";
    PersonList personList;
    Command c = new Command(ADD_PERSON, personList);

    @Test
    public void addPerson_getPerson_success() {
        try {
            addPersonCommand = new AddPersonCommand(ADD_PERSON, personList);
        } catch (Exception e) {
            fail();
        }
        int uid = personList.getNumberOfMembers() - 1;
        Assertions.assertEquals("Alice", personList.getPerson(uid).getName());
    }
}
