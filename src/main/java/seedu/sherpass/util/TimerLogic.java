package seedu.sherpass.util;

import seedu.sherpass.exception.InvalidTimeException;

import static seedu.sherpass.constant.Message.GOODBYE_MESSAGE_STUDY;

import static seedu.sherpass.util.Parser.parseStudyMode;

public class TimerLogic {
    public static boolean isTimerRunning = false;
    private static Ui ui;
    private static Timer timer;

    /**
     * Creates a constructor for TimerLogic.
     * @param ui UI
     */
    public TimerLogic(Ui ui) {
        TimerLogic.ui = ui;
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
            parseStudyMode(userInput, ui);
            ui.showLine();
            userInput = ui.readCommand();
            if (userInput.contains("leave")) {
                leaveStudyMode();
                return;
            }
            if (userInput.contains("start") && !isTimerRunning) {
                timer = resetTimer();
            }
        }
    }

    /**
     * Creates a thread using timer.start() to start the timer with the user's specified duration.
     *
     * @param parsedInput Parsed input of the user
     */
    public static void startTimer(String[] parsedInput) throws InvalidTimeException {
        if (timer.getHasTimeLeft()) {
            ui.showToUser("You already have a timer running!");
            return;
        }
        int duration = Parser.parseTimerInput(parsedInput, ui);
        if (isValidDuration(duration)) {
            timer.setDuration(duration);
            timer.start();
        } else {
            throw new InvalidTimeException();
        }
    }

    private static boolean isValidDuration(int duration) {
        if (duration > 0) {
            return true;
        }
        return false;
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
        ui.showLine();
        ui.showToUser(GOODBYE_MESSAGE_STUDY);
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
