package seedu.duke.tasks;

import java.util.ArrayList;

public class Task {

    protected String instruction;
    public static int number = 0;

    public Task(int number) {
        Task.number = number;
    }

    public static ArrayList<String> taskList = new ArrayList<>();

}
