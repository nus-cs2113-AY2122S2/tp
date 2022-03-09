package seedu.sherpass.utills;

public class Timer {
    public static void start(int duration, Ui ui) {
        ui.showToUser("Got it! Starting the timer...");
    }

    public static void resume(Ui ui) {
        ui.showToUser("Okay! I've resumed the timer.");
    }

    public static void pause(Ui ui) {
        ui.showToUser("Got it! I've paused the timer.\n"
            + "Feel free to resume whenever you're ready.");
    }

    public static void stop(Ui ui) {
        ui.showToUser("Alright, I've stopped the timer.");
    }
}
