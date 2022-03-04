package seedu.duke.command;public class SessionCreateCommand {
package seedu.duke.command;

import seedu.duke.data.Profile;
import seedu.duke.ui.TextUI;

import java.time.LocalDate;

public class SessionCreateCommand extends Command {

    private String sessionName;
    private String[] personNames;
    private LocalDate sessionDate;

    @Override
    public void run(TextUI ui, Profile profile) {

    }
}
