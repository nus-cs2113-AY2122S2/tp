package seedu.duke;

public class Duke {

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        assert true : "dummy assertion set to pass";
        Manager manager = new Manager();
        manager.runLoop();
        System.out.println("Terminated");
    }
}
