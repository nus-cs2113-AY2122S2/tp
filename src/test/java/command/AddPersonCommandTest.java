package command;

import org.junit.jupiter.api.Assertions;
import seedu.planitarium.commands.AddPersonCommand;
import seedu.planitarium.commands.Command;
import org.junit.jupiter.api.Test;
import seedu.planitarium.person.PersonList;

import static org.junit.jupiter.api.Assertions.fail;

public class AddPersonCommandTest {

    public static AddPersonCommand addPersonCommand;
    public static final String ADD_PERSON = "add /n Alice";
    public static PersonList personList = new PersonList();

    @Test
    public void addPerson_getPerson_success() {
        try {
            addPersonCommand = new AddPersonCommand(ADD_PERSON, personList);
            addPersonCommand.execute();
        } catch (Exception e) {
            fail();
        }
        int uid = personList.getNumberOfMembers();
        Assertions.assertEquals("Alice", personList.getPerson(uid).getName());
    }
}
