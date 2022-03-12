package seedu.sherpass.command;

import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Parser;
import seedu.sherpass.util.Timer;
import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

public class StudyCommand extends Command {
    public static final String COMMAND_WORD = "study";
    public static boolean isTimerRunning = false;

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showToUser("Gotcha! Entering study mode...");
        ui.showToUser("Done! To get started, enter one of the three default timers:\n"
                + "1) 30 minutes\n"
                + "2) 1 hour\n"
                + "3) 1.5 hours\n\n"
                + "For testing purposes, you can use a 30s timer with option 0.\n"
                + "Otherwise, feel free to choose your own timer with \n'start /custom <timer_duration>'.");
        ui.showLine();
        Timer timer = new Timer(ui);
        String userInput = ui.readCommand();
        while (!userInput.contains("leave")) {
            ui.showLine();
            Parser.parseStudyMode(userInput, ui, timer);
            ui.showLine();
            userInput = ui.readCommand();
            if (userInput.contains("leave")) {
                if (isTimerRunning) {
                    timer.stopTimer();
                }
            } else if (userInput.contains("start") && !isTimerRunning) {
                Timer newTimer = new Timer(ui);
                timer = newTimer;
            }
        }
        ui.showLine();
        ui.showToUser("Leaving study session...\n"
                + "Welcome back to the main session! How can I help you?");
    }
}
