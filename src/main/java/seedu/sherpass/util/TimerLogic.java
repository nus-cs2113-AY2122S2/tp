package seedu.sherpass.util;

import seedu.sherpass.exception.InvalidTimeException;
import seedu.sherpass.task.TaskList;

import static seedu.sherpass.constant.Message.ERROR_INVALID_TIMER_INPUT_MESSAGE;

public class TimerLogic {

    private static Ui ui;
    private static Timer timer;
    private static TaskList taskList;

    /**
     * Creates a constructor for TimerLogic.
     *
     * @param ui UI
     */
    public TimerLogic(TaskList taskList, Ui ui) {
        TimerLogic.taskList = taskList;
        TimerLogic.ui = ui;
        timer = new Timer(taskList, ui);
    }

    public boolean isTimerRunning() {
        return timer.isTimerRunning();
    }

    /**
     * Creates a thread using timer.start() to start the timer with the user's specified duration.
     *
     * @param parsedInput Parsed input of the user
     */
    public void callStartTimer(String[] parsedInput) {
        if (timer.getHasTimeLeft()) {
            ui.showToUser("You already have a timer running!");
            return;
        }
        try {
            int duration = Parser.parseTimerInput(parsedInput);
            assert (duration > 0);
            timer.setDuration(duration);
            timer.start();
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | InvalidTimeException e) {
            ui.showToUser(ERROR_INVALID_TIMER_INPUT_MESSAGE);
        }
    }

    public void callPauseTimer() {
        if (timer.isTimerPaused()) {
            ui.showToUser("The timer is already paused!");
        } else if (!timer.getHasTimeLeft()) {
            ui.showToUser("The timer has already finished!");
        } else {
            assert timer.isTimerRunning();
            timer.pauseTimer();
        }
    }

    public void callResumeTimer() {
        if (timer.isTimerPaused() && timer.getHasTimeLeft()) {
            timer.resumeTimer();
        } else if (timer.isTimerRunning()) {
            assert timer.getHasTimeLeft();
            ui.showToUser("The timer is still running!");
        } else {
            ui.showToUser("There is no timer running currently!");
        }
    }

    public void callStopTimer() {
        timer.stopTimer();
    }

    /**
     * Resets the timer by creating a new timer object, which can then be started by the user.
     *
     */
    public void callResetTimer() {
        timer = new Timer(taskList, ui);
    }
}
