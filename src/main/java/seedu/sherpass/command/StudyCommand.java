package seedu.sherpass.command;

import seedu.sherpass.task.TaskList;

import seedu.sherpass.util.Storage;
import seedu.sherpass.util.TimerLogic;
import seedu.sherpass.util.Ui;

import java.io.IOException;

import static seedu.sherpass.constant.Message.GOODBYE_MESSAGE_STUDY;
import static seedu.sherpass.constant.Message.HELP_MESSAGE_STUDY;
import static seedu.sherpass.constant.Message.WELCOME_MESSAGE_STUDY;
import static seedu.sherpass.util.Parser.parseStudyMode;

public class StudyCommand extends Command {
    public static final String COMMAND_WORD = "study";
    public static final String COMMAND_USAGE = "Study: Enters a study session.\n\n"
            + "User can access timer features while in a study session.\n\n"
            + HELP_MESSAGE_STUDY;

    /**
     * Method is called when user chooses to enter Study mode. User is able to start, pause and stop a timer in Study
     * mode. Only one timer can be running at a time. User can leave Study mode by typing "leave".
     */
    private void enterStudyMode(Ui ui, Storage storage, TimerLogic timerLogic) {

        ui.showToUser(WELCOME_MESSAGE_STUDY);
        ui.showLine();
        String userInput = ui.readCommand();
        while (!userInput.contains("leave")) {
            ui.showLine();
            try {
                parseStudyMode(ui, storage, userInput, timerLogic);
            } catch (IOException e) {
                ui.showToUser("IOException occurred.");
            }
            ui.showLine();
            userInput = ui.readCommand();
        }
    }

    public void leaveStudyMode(Ui ui, TimerLogic timerLogic) {
        if (timerLogic.isTimerRunning()) {
            timerLogic.callStopTimer();
        }
        ui.showLine();
        ui.showToUser(GOODBYE_MESSAGE_STUDY);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        TimerLogic timerLogic = new TimerLogic(taskList, ui);
        enterStudyMode(ui, storage, timerLogic);
        leaveStudyMode(ui, timerLogic);
    }
}
