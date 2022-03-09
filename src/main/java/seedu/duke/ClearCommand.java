package seedu.duke;

import static seedu.duke.Timetable.list;

public class ClearCommand {

    public static void clearList(){
        for (int i = 0; i < list.size(); i++){
            list.remove(i);
        }
    }
}
