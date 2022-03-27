package seedu.allonus.modules;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.UnfoldingReader;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;
import seedu.allonus.modules.exceptions.ModuleDayException;

import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModuleCalendarReader {
    private static Logger logger = Logger.getLogger("mylogger");
    static String ics = "nusmods_calendar.ics";

    public void readIcsFile(String fileName) {
        File icsFile = new File(fileName);
        if (icsFile.isFile()) {
            parseIcsCalendar();
        } else {
            System.out.println("No such file found! Please ensure you have the correct name."
                    + "\nThen place the file in the same directory as AllOnUs.jar.");
        }
    }

    public void parseIcsCalendar() {
        try {
            logger.setLevel(Level.OFF);
            CalendarBuilder builder = new CalendarBuilder();

            Date dateStart = null;
            Date dateEnd;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
            TimeZone tz = TimeZone.getTimeZone("SGT");
            sdf.setTimeZone(tz);
            final UnfoldingReader ufrdr =
                    new UnfoldingReader(new FileReader(ics),
                            true);

            Calendar calendar = builder.build(ufrdr);

            String moduleCode = "";
            String moduleCategory = "";
            String moduleDay = "";
            String moduleTime;
            String[] summary;
            boolean isExam = false;
            StringBuilder timeSlot;
            ArrayList<Module> modulesList = new ArrayList<>();
            for (final Object o : calendar.getComponents()) {
                Component component = (Component)o;
                System.out.println("\n");
                timeSlot = new StringBuilder();
                for (final Object o1 : component.getProperties()) {
                    Property property = (Property)o1;
                    switch (property.getName()) {
                    case "SUMMARY":
                        summary = property.getValue().split(" ",2);
                        moduleCode = summary[0];
                        moduleCategory = summary[1];
                        isExam = moduleCategory.equals("Exam");
                        System.out.printf("Module code: %s \nModule Category: %s\n",summary[0],summary[1]);
                        break;
                    case "DTSTART":
                        String startTime = property.getValue();
                        dateStart = sdf.parse(startTime);
                        System.out.println("Start: " + dateStart);
                        timeSlot.append(new SimpleDateFormat("h:mm a").format(dateStart));

                        java.util.Calendar calendarNew = java.util.Calendar.getInstance();
                        calendarNew.setTime(dateStart);
                        int day = calendarNew.get(java.util.Calendar.DAY_OF_WEEK);
                        moduleDay = convertDayToString(day);
                        break;
                    case "DTEND":
                        String endTime = property.getValue();
                        dateEnd = sdf.parse(endTime);
                        System.out.println("End: " + dateEnd);
                        timeSlot.append("-" + new SimpleDateFormat("h:mm a").format(dateEnd));
                        break;
                    case "RRULE":
                        System.out.println(
                                property.getName() + ": " + property.getValue());
                        break;
                    default:
                        continue;
                    }
                }
                moduleTime = timeSlot.toString();
                if (isExam) {
                    //If exam then we also add the exam date to the module day.
                    moduleDay += " " + new SimpleDateFormat("dd-MM-yyyy").format(dateStart);
                }
                if (moduleCategory.startsWith("Packaged")) {
                    moduleCategory = moduleCategory.replace("Packaged ","");
                }
                modulesList.add(new Module(moduleCode,moduleCategory,moduleDay,moduleTime));

            }

            System.out.println("\nMODULES list: ");
            for (Module m: modulesList) {
                System.out.println(m.toString());
            }
            logger.setLevel(Level.WARNING);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }


    public String convertDayToString(int dayOfWeek) {
        String day = "";
        try {
            switch (dayOfWeek) {
            case 1:
                day = "Sunday";
                break;
            case 2:
                day = "Monday";
                break;
            case 3:
                day = "Tuesday";
                break;
            case 4:
                day = "Wednesday";
                break;
            case 5:
                day = "Thursday";
                break;
            case 6:
                day = "Friday";
                break;
            case 7:
                day = "Saturday";
                break;
            default:
                throw new ModuleDayException("Not a day of the week!");
            }
        }  catch (ModuleDayException e) {
            System.out.println(e.getMessage());
        }
        return day;
    }

}
