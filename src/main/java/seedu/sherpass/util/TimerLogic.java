package seedu.sherpass.util;

import static seedu.sherpass.util.Parser.parseStudyMode;
import static seedu.sherpass.util.Parser.parseTimerInput;

public class TimerLogic {
    public static boolean isTimerRunning = false;
    private static Ui ui;
    private static Timer timer;

    public TimerLogic(Ui ui) {
        this.ui = ui;
        timer = new Timer(ui);
    }

    public void enterStudyMode() {
        String userInput = ui.readCommand();
        while (!userInput.contains("leave")) {
            ui.showLine();
            parseStudyMode(userInput, ui, timer);
            ui.showLine();
            userInput = ui.readCommand();
            if (userInput.contains("leave")) {
                leaveStudyMode();
            } else if (userInput.contains("start") && !isTimerRunning) {
                timer = resetTimer();
            }
        }
    }
    public static void startTimer(String[] parsedInput) {
        if (timer.getHasTimeLeft()) {
            ui.showToUser("You already have a timer running!");
        } else {
            int duration = parseTimerInput(parsedInput, ui);
            if (duration >= 0) {
                timer.setDuration(duration);
                timer.start();
            }
        }
    }

    public static void pauseTimer() {
        if (timer.isTimerPaused()) {
            ui.showToUser("The timer is already paused!");
        } else if (!timer.getHasTimeLeft()) {
            ui.showToUser("The timer has already finished!");
        } else {
            timer.pauseTimer();
        }
    }

    public static void resumeTimer() {
        if (timer.isTimerPaused() && timer.getHasTimeLeft()) {
            timer.resumeTimer();
        } else if (isTimerRunning) {
            ui.showToUser("There is no timer running currently!");
        } else {
            ui.showToUser("The timer is still running!");
        }
    }

    public static void stopTimer() {
        timer.stopTimer();
    }

    private void leaveStudyMode() {
        if (isTimerRunning) {
            timer.stopTimer();
        }
    }

    private Timer resetTimer() {
        return new Timer(ui);
    }
}
