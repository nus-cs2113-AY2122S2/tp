package seedu.duke;

public class AddCommand {
    public static Event execute(String[] addDescription) {
        // check parameter value errors
        return new Lesson(addDescription);
    }
}
