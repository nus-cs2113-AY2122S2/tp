package seedu.duke.command;

import seedu.duke.data.Profile;
import seedu.duke.ui.TextUI;

import java.time.LocalDate;

public class SessionCreateCommand extends Command {

    private String sessionName;
    private String[] personNames;
    private LocalDate sessionDate;

    public String getSessionName() {
        return sessionName;
    }
    public String[] getPersonNames() {
        return personNames;
    }
    public LocalDate getSessionDate() {
        return sessionDate;
    }
    @Override
    public void run(TextUI ui, Profile profile) {

    }
}
