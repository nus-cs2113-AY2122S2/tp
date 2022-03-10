package seedu.duke;

public class Timer extends Thread {
    private volatile boolean threadSuspended = false;
    protected int time;

    /**
     * Initialises the parameters needed for the countdown timer.
     * @param seconds Number of seconds in the timer
     */
    public Timer(int seconds) {
        time = seconds;
    }


    @Override
    public void run() {
        while (time > 0) {
            System.out.println(time + " seconds");
            time--;
            try {
                Thread.sleep(1000);
                if (threadSuspended) {
                    synchronized (this) {
                        while (threadSuspended) {
                            wait();
                        }
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupt");
            }
        }
        return;
    }

    public void suspendTimer() {
        threadSuspended = true;
    }

    public void resumeTimer() {
        synchronized (this) {
            threadSuspended = false;
            notify();
        }
    }
}
