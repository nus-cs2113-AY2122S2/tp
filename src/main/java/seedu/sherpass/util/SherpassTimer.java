package seedu.sherpass.util;

public class SherpassTimer extends Thread {

    private volatile boolean threadSuspended = false;
    private boolean hasTimeLeft = true;
    private static Ui ui;
    private int duration;
    protected int timeLeft;

    /**
     * Initialises the parameters needed for the countdown timer.
     * @param totalDuration Number of seconds in the timer
     */
    public SherpassTimer(int totalDuration, Ui ui) {
        duration = totalDuration;
        timeLeft = totalDuration;
        this.ui = ui;
    }

    @Override
    public void run() {
        while (hasTimeLeft) {
            if (timeLeft % 5 == 0) {
                System.out.println(timeLeft + " seconds left.");
            }
            try {
                Thread.sleep(1000);
                timeLeft -= 1;
                if (timeLeft <= 0) {
                    hasTimeLeft = false;
                }
                if (threadSuspended) {
                    synchronized (this) {
                        while (threadSuspended) {
                            wait();
                        }
                    }
                }
            } catch (InterruptedException e) {
            }
        }
        if (hasTimeLeft) {
            System.out.println("Time is up!");
        }
        this.interrupt();
    }

    public void resumeTimer() {
        synchronized (this) {
            threadSuspended = false;
            notify();
        }
        System.out.println("You have " + timeLeft + " seconds left.");
        ui.showToUser("Okay! I've resumed the timer.");
    }

    public void pauseTimer() {
        ui.showToUser("Got it! I've paused the timer.\n"
                + "Feel free to resume whenever you're ready.");
        threadSuspended = true;
    }

    public void stopTimer() {
        ui.showToUser("Alright, I've stopped the timer.");
        this.interrupt();
    }
}
