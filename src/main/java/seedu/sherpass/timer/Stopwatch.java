package seedu.sherpass.timer;

import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Ui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import static seedu.sherpass.constant.Message.EMPTY_STRING;
import static seedu.sherpass.constant.TimerConstant.ONE_HOUR;
import static seedu.sherpass.constant.TimerConstant.ONE_MINUTE;

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
    }

    private String convertTimeToString() {
        long hour;
        long minute;
        long second;
        if ((timeElapsed / ONE_HOUR) > 0) {
            hour = timeElapsed / ONE_HOUR;
            minute = (long) ((timeElapsed * 1.0) / ONE_HOUR) * 60;
            second = timeElapsed - (hour * ONE_HOUR) - (minute * ONE_MINUTE);
            String zeroStringHour = (hour > 9) ? EMPTY_STRING : "0";
            String zeroStringMinute = (minute > 9) ? EMPTY_STRING : "0";
            String zeroStringSecond = (second > 9) ? EMPTY_STRING : "0";
            return zeroStringHour + hour + " hour(s) " + zeroStringMinute + minute
                    + " minute(s) " + zeroStringSecond + second + " second(s)";
        } else if ((timeElapsed / ONE_MINUTE) > 0) {
            minute = timeElapsed / ONE_MINUTE;
            second = timeElapsed - (minute * ONE_MINUTE);
            String zeroStringMinute = (minute > 9) ? EMPTY_STRING : "0";
            String zeroStringSecond = (second > 9) ? EMPTY_STRING : "0";
            return zeroStringMinute + minute + " minute(s) " + zeroStringSecond + second + " second(s)";
        } else {
            second = timeElapsed;
            String zeroStringSecond = (second > 9) ? EMPTY_STRING : "0";
            return zeroStringSecond + second + " second(s)";
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
            String timeShownToUser = convertTimeToString();
            jlabel.setText("Elapsed time: " + timeShownToUser);
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
            jframe.setVisible(false);
            ui.showToUser("Alright, I've stopped the timer.");
            isTimerRunning = false;
            forcedStop = true;
            this.interrupt();
        } else {
            ui.showToUser("The timer has already stopped.");
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
