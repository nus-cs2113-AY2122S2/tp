package seedu.sherpass.command;

import seedu.sherpass.task.TaskList;

import seedu.sherpass.util.Storage;
import seedu.sherpass.util.TimerLogic;
import seedu.sherpass.util.Ui;

import static seedu.sherpass.constant.Message.WELCOME_MESSAGE_STUDY;

public class StudyCommand extends Command {
    public static final String COMMAND_WORD = "study";

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showToUser("Gotcha! Entering study mode...");
        ui.showToUser(WELCOME_MESSAGE_STUDY);
        ui.showLine();
        TimerLogic timerLogic = new TimerLogic(ui);
        timerLogic.enterStudyMode();
    }
}
