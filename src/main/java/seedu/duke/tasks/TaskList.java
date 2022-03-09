package seedu.duke.tasks;

import java.util.ArrayList;

public class TaskList {
    private static final String LS = System.lineSeparator();
    private static final String ITEMIZE_FORMAT = "%d. %s" + LS;
    private static final String EMPTY_LIST = "(empty)";

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

    public String getAllTasks(String indent) {
        String res = "";
        for (int i = 0; i < list.size(); i++) {
            res += indent + String.format(ITEMIZE_FORMAT, i + 1, list.get(i));
        }
        if (res.length() == 0) {
            res = indent + EMPTY_LIST;
        }
        return res;
    }

}
