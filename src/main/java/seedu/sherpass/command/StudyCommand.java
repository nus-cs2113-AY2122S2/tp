package seedu.sherpass.command;

import seedu.sherpass.task.TaskList;

import seedu.sherpass.util.Storage;
import seedu.sherpass.timer.TimerLogic;
import seedu.sherpass.util.Ui;

import java.io.IOException;

import static seedu.sherpass.constant.Message.GOODBYE_MESSAGE_STUDY;
import static seedu.sherpass.constant.Message.WELCOME_MESSAGE_STUDY;
import static seedu.sherpass.util.parser.TimerParser.parseStudyMode;

public class StudyCommand extends Command {

    /**
     * Calls method when user chooses to enter Study mode.
     * User is able to start, pause and stop a timer in Study mode.
     * Only one timer can be running at a time. User can leave Study mode by typing "leave".
     *
     * @param ui            Ui for printing task content.
     * @param storage       Storage for overwriting/appending save data.
     * @param timerLogic    Logic class to handle timer functions.
     */
    private void enterStudyMode(Ui ui, Storage storage, TimerLogic timerLogic) {

        ui.showToUser(WELCOME_MESSAGE_STUDY);
        ui.showLine();
        String userInput = ui.readCommand();
        while (!userInput.equals("leave")) {
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
    
    /**
     * Cleans up the study session by destroying any running threads and frames.
     *
     * @param ui ui
     * @param timerLogic timerLogic
     */
    public void leaveStudyMode(Ui ui, TimerLogic timerLogic) {
        timerLogic.killTimer();
        TimerLogic.resetIsTimerInitialised();
        timerLogic.destroyFrame();
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
