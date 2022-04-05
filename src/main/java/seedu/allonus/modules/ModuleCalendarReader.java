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
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModuleCalendarReader {
    private static final String LOGGER_IDENTIFIER = "mylogger";
    private static final String FILE_NOT_FOUND_MESSAGE = "No such file found! Please ensure you have the correct name."
            + "\nThen place the file in the same directory as AllOnUs.jar.";
    private static final String LOGGER_FILE_NOT_FOUND = "No .ics file was found under the given name.";
    private static final String SINGAPORE_TIMEZONE_KEYWORD = "Asia/Singapore";
    private static final String ICS_DATE_FORMAT = "yyyyMMdd'T'HHmmss'Z'";
    private static final String PARSE_SUCCESS_MESSAGE = "\nI have found these modules from your ics file:\n";
    private static final String ADDED_TO_SCHEDULE_MESSAGE = "\nI have added these to your existing schedule!";
    private static final String ICS_FILE_FORMAT = ".ics";
    private static final String NOT_ICS_ERROR_MESSAGE = "Your filename does not contain .ics.";
    private static final String CHECK_DIRECTORY_ERROR_MESSAGE = "Please ensure you have the correct name and "
            + "the .ics file is in the home directory of the application.";
    private static final String PARSE_FAILURE_MESSAGE = "Could not be parsed from the given ics file. \n"
            + "Please ensure that the ics from nusmods.com has not been edited.";
    private static final String INCORRECT_DAY_ERROR_MESSAGE = "Not a day of the week!";
    private static final String DATE_WAS_NULL_MESSAGE = "DateStart was found to be null";
    private static final String TIME_FORMAT_WITH_AMPM = "h:mm a";
    private static final String STANDARD_DATE_FORMAT = "dd-MM-yyyy";
    private static final String ICS_COMPONENT_SUMMARY = "SUMMARY";
    private static final String ICS_COMPONENT_START_DATE = "DTSTART";
    private static final String ICS_COMPONENT_END_DATE = "DTEND";
    private static final String FILE_NOT_FOUND_ERROR = "File %s was not found\n";
    private static final String MODULE_CATEGORY_PACKAGED_TYPE = "Packaged";
    private static final String SUNDAY = "Sunday";
    private static final String MONDAY = "Monday";
    private static final String TUESDAY = "Tuesday";
    private static final String WEDNESDAY = "Wednesday";
    private static final String THURSDAY = "Thursday";
    private static final String FRIDAY = "Friday";
    private static final String SATURDAY = "Saturday";
    private static final String MODULE_CATEGORY_EXAM = "Exam";
    public static final String UTC_TIMEZONE_KEYWORD = "UTC";
    private static Logger logger = Logger.getLogger(LOGGER_IDENTIFIER);
    public static final String PROJECT_PATH = System.getProperty("user.dir");

    static String icsFilePath;

    public ArrayList<Module> readIcsFile(String fileName) {
        logger.setLevel(Level.WARNING);
        String directoryPath = PROJECT_PATH + File.separator + fileName;
        File icsFile = new File(directoryPath);
        if (icsFile.isFile()) {
            icsFilePath = directoryPath;
            return parseIcsCalendar();
        } else {
            System.out.println(FILE_NOT_FOUND_MESSAGE);
            logger.log(Level.WARNING, LOGGER_FILE_NOT_FOUND);
            return null;
        }
    }

    ArrayList<Module> modulesList = new ArrayList<>();
    String moduleCode = "";
    String moduleCategory = "";
    String moduleDay = "";
    String moduleTime;
    String[] summary;
    StringBuilder timeSlot;
    Date dateStart = null;
    Date dateEnd;
    CalendarBuilder builder;
    SimpleDateFormat dateFormat;
    Calendar calendar;
    TimeZone utcTimeZone = TimeZone.getTimeZone(UTC_TIMEZONE_KEYWORD);
    ZoneId sgZoneId = ZoneId.of(SINGAPORE_TIMEZONE_KEYWORD);
    DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .appendPattern(TIME_FORMAT_WITH_AMPM)
            .toFormatter();

    public ArrayList<Module> parseIcsCalendar() {
        try {
            logger.setLevel(Level.WARNING);
            builder = new CalendarBuilder();
            dateFormat = new SimpleDateFormat(ICS_DATE_FORMAT);

            // Initially the data comes in utc timezone, we would later be converting it to sg timezone
            dateFormat.setTimeZone(utcTimeZone);
            final UnfoldingReader unfoldingReader = new UnfoldingReader(new FileReader(icsFilePath), true);


            calendar = builder.build(unfoldingReader);

            for (final Object componentObject : calendar.getComponents()) {
                Component calendarComponent = (Component)componentObject;
                timeSlot = new StringBuilder();
                // Iterates through all the components of ics file and only take the necessary properties
                for (final Object propertyObject : calendarComponent.getProperties()) {
                    Property property = (Property)propertyObject;
                    switch (property.getName()) {
                    case ICS_COMPONENT_SUMMARY:
                        getModuleSummary(property);
                        break;
                    case ICS_COMPONENT_START_DATE:
                        getModuleStartTime(property);
                        break;
                    case ICS_COMPONENT_END_DATE:
                        getModuleEndTime(property);
                        break;
                    default:
                        continue;
                    }
                }
                moduleTime = timeSlot.toString().toLowerCase();
                getExamDate();
                standardizeModuleCategory();
                modulesList.add(new Module(moduleCode,moduleCategory,moduleDay,moduleTime));

            }
            System.out.println(PARSE_SUCCESS_MESSAGE);
            listIcsModules();

        } catch (ParseException | ParserException e) {
            if (!icsFilePath.contains(ICS_FILE_FORMAT)) {
                System.out.println(NOT_ICS_ERROR_MESSAGE);
                System.out.println(CHECK_DIRECTORY_ERROR_MESSAGE);
            } else {
                System.out.println(PARSE_FAILURE_MESSAGE);
            }
        } catch (FileNotFoundException e) {
            System.out.printf(FILE_NOT_FOUND_ERROR, icsFilePath);
        } catch (IOException e) {
            System.out.println(PARSE_FAILURE_MESSAGE);
        }
        return modulesList;
    }

    private void listIcsModules() {
        int i = 1;
        for (Module m: modulesList) {
            System.out.println((i++) + ": " + m.toString());
        }
        System.out.println(ADDED_TO_SCHEDULE_MESSAGE);
    }

    private void standardizeModuleCategory() {
        if (moduleCategory.startsWith(MODULE_CATEGORY_PACKAGED_TYPE)) {
            moduleCategory = moduleCategory.replace(MODULE_CATEGORY_PACKAGED_TYPE + " ","");
        }
    }

    private void getExamDate() {
        if (moduleCategory.startsWith(MODULE_CATEGORY_EXAM)) {
            //If category = exam then change the module day to include the exam date instead
            assert (dateStart != null) : DATE_WAS_NULL_MESSAGE;
            moduleDay = new SimpleDateFormat(STANDARD_DATE_FORMAT).format(dateStart);
        }
    }

    private void getModuleEndTime(Property property) throws ParseException {
        String endTime = property.getValue();
        dateEnd = dateFormat.parse(endTime);
        Instant instant = dateEnd.toInstant();
        ZonedDateTime zonedDateEnd = instant.atZone(sgZoneId);
        timeSlot.append("-" +  zonedDateEnd.format(formatter));
    }

    private void getModuleStartTime(Property property) throws ParseException {
        String startTime = property.getValue();
        dateStart = dateFormat.parse(startTime);
        Instant instant = dateStart.toInstant();
        ZonedDateTime zonedDateStart = instant.atZone(sgZoneId);
        timeSlot.append(zonedDateStart.format(formatter));

        java.util.Calendar calendarNew = java.util.Calendar.getInstance();
        calendarNew.setTime(dateStart);
        int day = calendarNew.get(java.util.Calendar.DAY_OF_WEEK);
        moduleDay = convertDayToString(day);
    }

    private void getModuleSummary(Property property) {
        summary = property.getValue().split(" ",2);
        moduleCode = summary[0];
        moduleCategory = summary[1];
    }


    public String convertDayToString(int dayOfWeek) {
        String day = "";
        try {
            switch (dayOfWeek) {
            case 1:
                day = SUNDAY;
                break;
            case 2:
                day = MONDAY;
                break;
            case 3:
                day = TUESDAY;
                break;
            case 4:
                day = WEDNESDAY;
                break;
            case 5:
                day = THURSDAY;
                break;
            case 6:
                day = FRIDAY;
                break;
            case 7:
                day = SATURDAY;
                break;
            default:
                throw new ModuleDayException(INCORRECT_DAY_ERROR_MESSAGE);
            }
        }  catch (ModuleDayException e) {
            System.out.println(e.getMessage());
        }
        return day;
    }

}
