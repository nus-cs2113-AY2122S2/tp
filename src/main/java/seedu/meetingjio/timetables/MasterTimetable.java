package seedu.meetingjio.timetables;

import seedu.meetingjio.exceptions.TimetableNotFoundException;

import java.util.ArrayList;

public class MasterTimetable {
    public static ArrayList<Timetable> timetables;

    public MasterTimetable() {
        this.timetables = new ArrayList<>();
    }

    public Timetable getByName(String name) throws TimetableNotFoundException {
        for (Timetable timetable : timetables) {
            if (name.equals(timetable.getName())) {
                return timetable;
            }
        }
        throw new TimetableNotFoundException();
    }

    public void removeByName(String name) throws TimetableNotFoundException {
        for (Timetable timetable : timetables) {
            if (name.equals(timetable.getName())) {
                timetables.remove(timetable);
            }
        }
        throw new TimetableNotFoundException();
    }

    public Timetable getByIndex(int index) {
        return timetables.get(index);
    }

    public void removeByIndex(int index) {
        timetables.remove(index);
    }

    public void add(Timetable timetable) {
        timetables.add(timetable);
    }

    public static void checkUserExist(String user) throws TimetableNotFoundException {
        for (Timetable timetable : timetables) {
            if (user.equals(timetable.getName())) {
                return;
            }
        }
        throw new TimetableNotFoundException();
    }

}
