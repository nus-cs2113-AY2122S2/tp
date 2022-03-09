package seedu.sherpass.command;

import seedu.sherpass.task.TaskList;
import seedu.sherpass.utills.Parser;
import seedu.sherpass.utills.Storage;
import seedu.sherpass.utills.Ui;

import static seedu.sherpass.constant.Indexes.STUDY_COMMAND_INDEX;

public class StudyCommand extends Command {
    public static final String COMMAND_WORD = "study";

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showToUser("Gotcha! Entering study mode...");
        ui.showToUser("Done! To get started, enter one of the three default timers:\n"
            + "1) 30 minutes\n"
            + "2) 1 hour\n"
            + "3) 1.5 hours\n\n"
            + "Otherwise, feel free to choose your own timer with \n'start /custom <timer_duration>'.");
        ui.showLine();
        String userInput = ui.readCommand();
        while (!userInput.contains("leave")) {
            ui.showLine();
            Parser.parseStudyMode(userInput, ui);
            ui.showLine();
            userInput = ui.readCommand();
        }
        ui.showLine();
        ui.showToUser("leaving study session...\n"
            + "Welcome back to the main session! How can I help you?");
    }

}
