package seedu.duke;

public class SherpassTimer extends Thread {

    private volatile boolean threadSuspended = false;
    private boolean hasTimeLeft = true;
    protected int timeLeft;

    /**
     * Initialises the parameters needed for the countdown timer.
     * @param seconds Number of seconds in the timer
     */
    public SherpassTimer(int seconds) {
        timeLeft = seconds;
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

    public void stopTimer() {
        this.interrupt();
    }

    public void suspendTimer() {
        threadSuspended = true;
    }

    public void resumeTimer() {
        synchronized (this) {
            threadSuspended = false;
            notify();
        }
        System.out.println("You have " + timeLeft + " seconds left.");
    }
}
