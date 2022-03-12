package seedu.sherpass.util;

import seedu.sherpass.command.StudyCommand;

import static seedu.sherpass.constant.Timer.TIME_INTERVAL;
import static seedu.sherpass.constant.Timer.NO_TIME_LEFT;
import static seedu.sherpass.constant.Timer.ONE_MINUTE;
import static seedu.sherpass.constant.Timer.ONE_HOUR;

public class Timer extends Thread {

    private volatile boolean timerPaused = false;
    private boolean forcedStop = false;
    private boolean hasTimeLeft = false;
    private static Ui ui;
    protected int timeLeft;

    /**
     * Initialises the parameters needed for the countdown timer.
     * @param ui ui
     */
    public Timer(Ui ui) {
        timeLeft = NO_TIME_LEFT;
        this.ui = ui;
    }

    @Override
    public void run() {
        StudyCommand.isTimerRunning = true;
        printTimerStart();
        while (hasTimeLeft) {
            printTimeLeft();
            try {
                Thread.sleep(1000);
                timeLeft -= 1;
                if (timeLeft <= NO_TIME_LEFT) {
                    hasTimeLeft = false;
                }
                if (timerPaused) {
                    synchronized (this) {
                        while (timerPaused) {
                            wait();
                        }
                    }
                }
            } catch (InterruptedException e) {
                return;
            }
        }
        if (!hasTimeLeft && !forcedStop) {
            StudyCommand.isTimerRunning = false;
            ui.showToUser("Time is up! Would you like to start another timer?");
        }
        this.interrupt();
    }

    public void printTimeLeft() {
        if (timeLeft > ONE_MINUTE) {
            if (timeLeft % ONE_MINUTE == 0) {
                int minutesLeft = timeLeft/ONE_MINUTE;
                ui.showToUser(minutesLeft + " minutes left.");
            }
        } else {
            if (timeLeft % TIME_INTERVAL == 0) {
                ui.showToUser(timeLeft + " seconds left.");
            }
        }
    }

    public void printTimerStart() {
        int hours;
        int minutes;
        int seconds;
        if (timeLeft >= ONE_HOUR) {
            hours = timeLeft/ONE_HOUR;
            minutes = (timeLeft - hours * ONE_HOUR) / ONE_MINUTE;
            seconds = timeLeft - hours * ONE_HOUR - minutes * ONE_MINUTE;
            ui.showToUser("Timer of " + hours + " hours " + minutes + " minutes "
                    + seconds + " seconds started.");
        } else if (timeLeft >= ONE_MINUTE){
            minutes = timeLeft / ONE_MINUTE;
            seconds = timeLeft - (minutes * ONE_MINUTE);
            ui.showToUser("Timer of " + minutes + " minutes "
                    + seconds + " seconds started.");
        } else {
            ui.showToUser("Timer of " + timeLeft + " seconds started.");
        }
    }

    public void setDuration(int duration) {
        timeLeft = duration;
        hasTimeLeft = true;
    }

    public boolean getHasTimeLeft() { return hasTimeLeft; }

    public boolean isTimerPaused() { return timerPaused; }

    public void resumeTimer() {
        synchronized (this) {
            timerPaused = false;
            notify();
        }
        ui.showToUser("Okay! I've resumed the timer. You have " + timeLeft + " seconds left.");
    }

    public void pauseTimer() {
        ui.showToUser("Got it! I've paused the timer.\n"
                + "Feel free to resume whenever you're ready.");
        timerPaused = true;
    }

    public void stopTimer() {
        if (StudyCommand.isTimerRunning) {
            ui.showToUser("Alright, I've stopped the timer.");
            StudyCommand.isTimerRunning = false;
            forcedStop = true;
            timeLeft = NO_TIME_LEFT;
            hasTimeLeft = false;
            this.interrupt();
        } else {
            ui.showToUser("The timer has already stopped.");
        }
    }
}
