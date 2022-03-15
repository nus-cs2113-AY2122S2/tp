package command;

import commands.Command;
import org.junit.jupiter.api.Test;

public class CommandTest {
    String ADD_PERSON = "add Alice";
    Command c = new Command(ADD_PERSON);

    @Test
    void execute() {

    }
}
