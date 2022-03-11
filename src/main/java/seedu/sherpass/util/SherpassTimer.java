package seedu.sherpass.util;

import static seedu.sherpass.constant.Timer.*;

import seedu.sherpass.command.StudyCommand;

public class SherpassTimer extends Thread {

    private volatile boolean timerPaused = false;
    private boolean hasTimeLeft = false;
    private static Ui ui;
    protected int timeLeft;

    /**
     * Initialises the parameters needed for the countdown timer.
     * @param
     */
    public SherpassTimer(Ui ui) {
        timeLeft = NO_TIME_LEFT;
        this.ui = ui;
    }

    @Override
    public void run() {
        while (hasTimeLeft) {
            if (timeLeft % TIME_INTERVAL == NO_TIME_LEFT) {
                System.out.println(timeLeft + " seconds left.");
            }
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
            }
        }
        if (!hasTimeLeft) {
            System.out.println("Time is up!");
        }
        this.interrupt();
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
        ui.showToUser("Alright, I've stopped the timer.");
        StudyCommand.isTimerRunning = false;
        timeLeft = NO_TIME_LEFT;
        hasTimeLeft = false;
        this.interrupt();
    }
}
