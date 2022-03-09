package seedu.duke.tasks;

import java.util.ArrayList;

public class TaskList {
    private static final String LS = System.lineSeparator();
    private static final String ITEMIZE_FORMAT = "%d. %s" + LS;

    private final ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public int size() {
        return list.size();
    }

    public Task addTask(Task t) {
        list.add(t);
        return t;
    }

    public Task getTask(int index) {
        return list.get(index);
    }

    public String getAllTasks() {
        String res = "";
        for (int i = 0; i < list.size(); i++) {
            res += String.format(ITEMIZE_FORMAT, i + 1, list.get(i));
        }
        return res;
    }

}
