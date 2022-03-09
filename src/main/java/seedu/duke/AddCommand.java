package seedu.duke;

public class AddCommand {
    public static Event execute(String[] addDescription) {
        // check parameter value errors
        Event newInput = new Lesson(addDescription);
        EventList.add(newInput);
        return newInput;
    }

    public static void printConfirmation(Event event) {
        System.out.println("The following event has been added to your timetable:");
        System.out.println(event);
    }
}
