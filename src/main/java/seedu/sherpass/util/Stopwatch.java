package seedu.sherpass.util;

import seedu.sherpass.task.TaskList;

import static seedu.sherpass.constant.TimerConstant.ONE_MINUTE;
import static seedu.sherpass.constant.TimerConstant.STOPWATCH_TIME_INTERVAL;

public class Stopwatch extends Timer {

    private int timeElapsed;

    public Stopwatch(TaskList taskList, Ui ui) {
        super(taskList, ui);
        timeElapsed = 0;
    }

    /**
     * Creates a new thread to run the stopwatch. The timer will continue to run until it has been stopped by the user.
     */
    @Override
    public void run() {
        isTimerRunning = true;
        printTimerStart();
        while (!forcedStop) {
            update();
        }
    }

    /**
     * Updates the timer by letting the thread sleep for 1 second, then updating timeLeft. The timer will not update
     * if it is paused and will instead wait for the user to resume the timer.
     */
    protected void update() {
        try {
            Thread.sleep(1000);
            timeElapsed += 1;
            printTimeElapsed();
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
            this.interrupt();
        } else {
            ui.showToUser("The timer has already stopped.");
        }
    }

    private void printTimeElapsed() {
        if (timeElapsed > ONE_MINUTE) {
            if (timeElapsed % ONE_MINUTE == 0) {
                int minutesLeft = timeElapsed / ONE_MINUTE;
                ui.showToUser(minutesLeft + " minutes have passed.");
            }
            return;
        }
        if (timeElapsed % STOPWATCH_TIME_INTERVAL == 0) {
            ui.showToUser(timeElapsed + " seconds have passed.");
        }
    }

    /**
     * Prints the timer selected by the user.
     */
    protected void printTimerStart() {
        ui.showToUser("Alright, your timer is started. Feel free to pause or stop the timer whenever you're ready.");
    }

    /**
     * Resumes the timer by calling notify() on the waiting thread.
     */
    public void resumeTimer() {
        synchronized (this) {
            isTimerPaused = false;
            notify();
        }
        ui.showToUser("Okay! I've resumed the timer.");
    }

}
