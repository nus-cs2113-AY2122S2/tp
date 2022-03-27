package seedu.allonus.modules;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.data.UnfoldingReader;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;
import seedu.allonus.modules.exceptions.ModuleDayException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModuleCalendarReader {
    private static Logger logger = Logger.getLogger("mylogger");
    static String ics;

    public ArrayList<Module> readIcsFile(String fileName) {
        logger.setLevel(Level.WARNING);
        File icsFile = new File(fileName);
        if (icsFile.isFile()) {
            ics = fileName;
            return parseIcsCalendar();
        } else {
            System.out.println("No such file found! Please ensure you have the correct name."
                    + "\nThen place the file in the same directory as AllOnUs.jar.");
            logger.log(Level.WARNING, "No .ics file was found under the given name.");
            return null;
        }
    }

    public ArrayList<Module> parseIcsCalendar() {
        ArrayList<Module> modulesList = new ArrayList<>();
        try {
            logger.setLevel(Level.WARNING);
            CalendarBuilder builder = new CalendarBuilder();

            Date dateStart = null;
            Date dateEnd;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
            TimeZone tz = TimeZone.getTimeZone("SGT");
            dateFormat.setTimeZone(tz);
            final UnfoldingReader unfoldingReader =
                    new UnfoldingReader(new FileReader(ics),
                            true);

            Calendar calendar = builder.build(unfoldingReader);

            String moduleCode = "";
            String moduleCategory = "";
            String moduleDay = "";
            String moduleTime;
            String[] summary;
            boolean isExam = false;
            StringBuilder timeSlot;

            for (final Object componentObject : calendar.getComponents()) {
                Component calendarComponent = (Component)componentObject;
                // System.out.println("\n");
                timeSlot = new StringBuilder();
                for (final Object propertyObject : calendarComponent.getProperties()) {
                    Property property = (Property)propertyObject;
                    switch (property.getName()) {
                    case "SUMMARY":
                        summary = property.getValue().split(" ",2);
                        moduleCode = summary[0];
                        moduleCategory = summary[1];
                        isExam = moduleCategory.equals("Exam");
                        // System.out.printf("Module code: %s \nModule Category: %s\n",summary[0],summary[1]);
                        break;
                    case "DTSTART":
                        String startTime = property.getValue();
                        dateStart = dateFormat.parse(startTime);
                        // System.out.println("Start: " + dateStart);
                        timeSlot.append(new SimpleDateFormat("h:mm a").format(dateStart));

                        java.util.Calendar calendarNew = java.util.Calendar.getInstance();
                        calendarNew.setTime(dateStart);
                        int day = calendarNew.get(java.util.Calendar.DAY_OF_WEEK);
                        moduleDay = convertDayToString(day);
                        break;
                    case "DTEND":
                        String endTime = property.getValue();
                        dateEnd = dateFormat.parse(endTime);
                        // System.out.println("End: " + dateEnd);
                        timeSlot.append("-" + new SimpleDateFormat("h:mm a").format(dateEnd));
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

            System.out.println("\nI have found these modules from your ics file:\n");
            int i = 1;
            for (Module m: modulesList) {
                System.out.println((i++) + ": " + m.toString());
            }
            System.out.println("\nI have added these to your existing schedule!");
        } catch (ParseException | ParserException e) {
            if (!ics.contains(".ics")) {
                System.out.println("Your filename does not contain .ics.");
                System.out.println("Please ensure you have the correct name and the .ics file is in the home"
                        + " directory of the application.");
            } else {
                System.out.println("Could not be parsed from the given ics file. \n"
                        + "Please ensure that the ics from nusmods.com has not been edited.");
            }
        } catch (FileNotFoundException e) {
            System.out.printf("File %s was not found\n",ics);
        } catch (IOException e) {
            System.out.println("Could not be parsed from the given ics file. \n"
                    + "Please ensure that the ics from nusmods.com has not been edited.");
        }
        return modulesList;
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
