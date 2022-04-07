package seedu.sherpass.timer;

import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Ui;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class Stopwatch extends Timer {

    private int timeElapsed;

    private final JFrame jframe;
    private final JLabel jlabel;

    public Stopwatch(TaskList taskList, Ui ui, JFrame jframe, JLabel jlabel) {
        super(taskList, ui);
        timeElapsed = 0;
        this.jframe = jframe;
        this.jlabel = jlabel;
    }

    /**
     * Creates a new thread to run the stopwatch. The timer will continue to run until it has been stopped by the user.
     */
    @Override
    public void run() {
        isTimerRunning = true;
        printTimerStart();
        jframe.setVisible(true);
        while (!forcedStop) {
            update();
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
            timeElapsed += 1;
            String timeShownToUser = convertTimeToString(timeElapsed);
            jlabel.setText("Elapsed time: " + timeShownToUser);
            if (isTimerPaused) {
                waitForTimerToResume();
            }
        } catch (InterruptedException e) {
            ui.showToUser("Alright, I've stopped the stopwatch.");
            forcedStop = true;
            this.interrupt();
        }
    }

    /**
     * Stops the timer if it is running, else prints an error message.
     */
    public void stopTimer() {
        if (isTimerRunning) {
            jframe.setVisible(false);
            isTimerRunning = false;
            forcedStop = true;
            this.interrupt();
        } else {
            ui.showToUser("The stopwatch has already stopped.");
        }
    }

    public void pauseTimer() {
        isTimerPaused = true;
        ui.showToUser("Got it! I've paused the stopwatch.\n"
                + convertTimeToString(timeElapsed) + " have elapsed.\n"
                + "Feel free to resume whenever you're ready.");
    }

    /**
     * Prints the timer selected by the user.
     */
    protected void printTimerStart() {
        ui.showToUser("Alright, your stopwatch is started. Feel free to pause "
                + "or stop the stopwatch whenever you're ready.");
    }

    /**
     * Resumes the timer by calling notify() on the waiting thread.
     */
    public void resumeTimer() {
        ui.showToUser("Okay! I've resumed the stopwatch.\n"
                + "Elapsed time: " + convertTimeToString(timeElapsed - 1));
        synchronized (this) {
            isTimerPaused = false;
            notify();
        }
    }
}
