package seedu.sherpass.util;

import seedu.sherpass.task.TaskList;

import static seedu.sherpass.constant.TimerConstant.TIME_INTERVAL;
import static seedu.sherpass.constant.TimerConstant.NO_TIME_LEFT;
import static seedu.sherpass.constant.TimerConstant.ONE_MINUTE;
import static seedu.sherpass.constant.TimerConstant.ONE_HOUR;

public class Countdown extends Timer {

    private boolean hasTimeLeft = false;
    protected int timeLeft;

    /**
     * Creates a constructor for timer. Initialises the parameters needed for the countdown timer.
     *
     * @param ui UI
     */
    public Countdown(TaskList taskList, Ui ui) {
        super(taskList, ui);
        timeLeft = NO_TIME_LEFT;
    }

    /**
     * Creates a new thread to run the timer. The timer will continue to run until it has run out of time, or has been
     * stopped by the user.
     */
    @Override
    public void run() {
        isTimerRunning = true;
        printTimerStart();
        while (hasTimeLeft) {
            assert timeLeft > NO_TIME_LEFT;
            printTimeLeft();
            update();
        }
        if (timerRanOutOfTime()) {
            assert timeLeft <= NO_TIME_LEFT;
            isTimerRunning = false;
            ui.showToUser("Time is up! Would you like to mark any tasks as done?");
            taskList.printAllTasks(ui);
            ui.showLine();
        }
        this.interrupt();
    }

    /**
     * Updates the timer by letting the thread sleep for 1 second, then updating timeLeft. The timer will not update
     * if it is paused and will instead wait for the user to resume the timer.
     */
    protected void update() {
        try {
            Thread.sleep(1000);
            timeLeft -= 1;
            updateHasTimeLeft();
            if (isTimerPaused) {
                waitForTimerToResume();
            }
        } catch (InterruptedException e) {
            return;
        }
    }

    /**
     * Stops the timer if it is running, else prints an error message.
     */
    public void stopTimer() {
        if (isTimerRunning) {
            ui.showToUser("Alright, I've stopped the timer.");
            isTimerRunning = false;
            forcedStop = true;
            timeLeft = NO_TIME_LEFT;
            hasTimeLeft = false;
            this.interrupt();
        } else {
            ui.showToUser("The timer has already stopped.");
        }
    }

    private void updateHasTimeLeft() {
        if (timeLeft <= NO_TIME_LEFT) {
            hasTimeLeft = false;
        }
    }

    /**
     * Returns whether timer ran out of time.
     *
     * @return Boolean of whether timer ran out of time.
     */
    private boolean timerRanOutOfTime() {
        return (!hasTimeLeft && !forcedStop);
    }

    /**
     * Prints the time left on the timer at certain intervals.
     * When timer has more than a minute remaining, it prints time remaining every minute (X min:00s).
     * It will print out the time remaining every TIME_INTERVAL seconds when less than a minute remains.
     */
    public void printTimeLeft() {
        if (timeLeft > ONE_MINUTE) {
            if (timeLeft % ONE_MINUTE == 0) {
                int minutesLeft = timeLeft / ONE_MINUTE;
                ui.showToUser(minutesLeft + " minutes left.");
            }
            return;
        }
        if (timeLeft % TIME_INTERVAL == 0) {
            ui.showToUser(timeLeft + " seconds left.");
        }
    }

    /**
     * Prints the timer selected by the user.
     */
    protected void printTimerStart() {
        int hours;
        int minutes;
        int seconds;
        if (timeLeft >= ONE_HOUR) {
            hours = timeLeft / ONE_HOUR;
            minutes = (timeLeft - hours * ONE_HOUR) / ONE_MINUTE;
            seconds = timeLeft - hours * ONE_HOUR - minutes * ONE_MINUTE;
            ui.showToUser("Timer of " + hours + " hours " + minutes + " minutes "
                    + seconds + " seconds started.");
        } else if (timeLeft >= ONE_MINUTE) {
            minutes = timeLeft / ONE_MINUTE;
            seconds = timeLeft - (minutes * ONE_MINUTE);
            ui.showToUser("Timer of " + minutes + " minutes "
                    + seconds + " seconds started.");
        } else {
            ui.showToUser("Timer of " + timeLeft + " seconds started.");
        }
    }

    /**
     * Sets the duration of the timer, as specified by the user.
     *
     * @param duration Duration of timer in seconds.
     */
    public void setDuration(int duration) {
        timeLeft = duration;
        hasTimeLeft = true;
    }

    /**
     * Resumes the timer by calling notify() on the waiting thread.
     */
    public void resumeTimer() {
        synchronized (this) {
            isTimerPaused = false;
            notify();
        }
        ui.showToUser("Okay! I've resumed the timer. You have " + timeLeft + " seconds left.");
    }

}
