package seedu.sherpass.util;

import seedu.sherpass.task.TaskList;

public abstract class Timer extends Thread {
    protected volatile boolean isTimerRunning = false;
    protected volatile boolean isTimerPaused = false;
    protected boolean forcedStop = false;
    protected static Ui ui;
    protected static TaskList taskList;


    /**
     * Creates a constructor for timer. Initialises the parameters needed for the countdown timer.
     *
     * @param ui UI
     */
    public Timer(TaskList taskList, Ui ui) {
        Timer.ui = ui;
        Timer.taskList = taskList;
    }

    protected abstract void update();

    protected abstract void printTimerStart();

    public abstract void resumeTimer();

    public abstract void stopTimer();

    public boolean getIsStopped() {
        return forcedStop;
    }

    public void pauseTimer() {
        ui.showToUser("Got it! I've paused the timer.\n"
                + "Feel free to resume whenever you're ready.");
        isTimerPaused = true;
    }

    /**
     * Method causes the thread which the timer is running on to wait when it is paused, until the user resumes the
     * timer.
     */
    protected void waitForTimerToResume() throws InterruptedException {
        synchronized (this) {
            while (isTimerPaused) {
                wait();
            }
        }
    }

    public boolean isTimerRunning() {
        return isTimerRunning;
    }

    public boolean isTimerPaused() {
        return isTimerPaused;
    }
}
