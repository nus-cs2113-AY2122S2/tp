package seedu.sherpass.util;

import static seedu.sherpass.util.Parser.parseStudyMode;
import static seedu.sherpass.util.Parser.parseTimerInput;

public class TimerLogic {
    public static boolean isTimerRunning = false;
    private static Ui ui;
    private static Timer timer;

    /**
     * Creates a constructor for TimerLogic.
     * @param ui UI
     */
    public TimerLogic(Ui ui) {
        this.ui = ui;
        timer = new Timer(ui);
    }

    /**
     * Method is called when user chooses to enter Study mode. User is able to start, pause and stop a timer in Study
     * mode. Only one timer can be running at a time. User can leave Study mode by typing "leave".
     */
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

    /**
     * Creates a thread using timer.start() to start the timer with the user's specified duration.
     *
     * @param parsedInput Parsed input of the user
     */
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

    /**
     * Resets the timer by creating a new timer object, which can then be started by the user.
     *
     * @return New timer object
     */
    private Timer resetTimer() {
        return new Timer(ui);
    }
}
