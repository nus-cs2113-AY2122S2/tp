package seedu.meetingjio.timetables;

import seedu.meetingjio.exceptions.TimetableNotFoundException;

import java.util.ArrayList;

public class MasterTimetable {
    private final ArrayList<Timetable> timetables;

    public MasterTimetable() {
        this.timetables = new ArrayList<>();
    }

    public Timetable getByName(String name) throws TimetableNotFoundException {
        for (Timetable timetable : timetables) {
            if (timetable.nameEquals(name)) {
                return timetable;
            }
        }
        throw new TimetableNotFoundException();
    }

    public Timetable getByIndex(int index) {
        return timetables.get(index);
    }
}
