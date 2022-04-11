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

/**
 * Reads a .ics file from nusmods.com which represents an NUS student's academic schedule.
 * Parses the module parameters from this file for each module and creates a module list.
 */
public class ModuleCalendarReader {
    protected static String icsFilePath;

    //Constant messages and strings
    private static final String LOGGER_IDENTIFIER = "mylogger";
    private static final String FILE_NOT_FOUND_MESSAGE = "No such file found! Please ensure you have the correct name."
            +  System.lineSeparator() + "Then place the file in the same directory as AllOnUs.jar.";
    private static final String LOGGER_FILE_NOT_FOUND = "No .ics file was found under the given name.";
    private static final String SINGAPORE_TIMEZONE_KEYWORD = "Asia/Singapore";
    private static final String ICS_DATE_FORMAT = "yyyyMMdd'T'HHmmss'Z'";
    private static final String PARSE_SUCCESS_MESSAGE =  System.lineSeparator()
            + "I have found these modules from your ics file:" + System.lineSeparator();
    private static final String ADDED_TO_SCHEDULE_MESSAGE =  System.lineSeparator()
            + "I have added these to your existing schedule!";
    private static final String ICS_FILE_FORMAT = ".ics";
    private static final String NOT_ICS_ERROR_MESSAGE = "Your filename does not contain .ics.";
    private static final String CHECK_DIRECTORY_ERROR_MESSAGE = "Please ensure you have the correct name and "
            + "the .ics file is in the home directory of the application.";
    private static final String PARSE_FAILURE_MESSAGE = "Could not be parsed from the given ics file. "
            + System.lineSeparator() + "Please ensure that the ics from nusmods.com has not been edited.";
    private static final String INCORRECT_DAY_ERROR_MESSAGE = "Not a day of the week!";
    private static final String DATE_WAS_NULL_MESSAGE = "DateStart was found to be null";
    private static final String TIME_FORMAT_WITH_AMPM = "h:mm a";
    private static final String STANDARD_DATE_FORMAT = "dd-MM-yyyy";
    private static final String ICS_COMPONENT_SUMMARY = "SUMMARY";
    private static final String ICS_COMPONENT_START_DATE = "DTSTART";
    private static final String ICS_COMPONENT_END_DATE = "DTEND";
    private static final String FILE_NOT_FOUND_ERROR = "File %s was not found" + System.lineSeparator();
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
    private static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone(UTC_TIMEZONE_KEYWORD);
    private static final ZoneId SG_ZONE_ID = ZoneId.of(SINGAPORE_TIMEZONE_KEYWORD);
    private static final DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern(TIME_FORMAT_WITH_AMPM)
            .toFormatter();
    private static final CalendarBuilder  builder = new CalendarBuilder();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(ICS_DATE_FORMAT);


    /**
     * Reads a .ics file based on a given filename.
     * @param fileName Name of the .ics file from nusmods.com that contains NUS module details.
     * @return modulesList that contains the modules parsed from .ics file or null if there was a parse error
     */
    public ArrayList<Module> readIcsFile(String fileName) {
        logger.setLevel(Level.WARNING);
        String directoryPath = PROJECT_PATH + File.separator + fileName;
        File icsFile = new File(directoryPath);
        if (icsFile.isFile()) {
            icsFilePath = directoryPath;
            return parseIcsCalendar();
        } else {
            StudyManager.printMessageWithDivider(FILE_NOT_FOUND_MESSAGE);
            logger.log(Level.INFO, LOGGER_FILE_NOT_FOUND);
            return null;
        }
    }

    //Class level variables used by multiple functions
    ArrayList<Module> modulesList = new ArrayList<>();
    String moduleCode = "";
    String moduleCategory = "";
    String moduleDay = "";
    String moduleTime;
    Date dateStart = null;


    /**
     * Parses the .ics file based on icsFileName
     * @return modulesList that contains the modules parsed from the .ics file.
     */
    public ArrayList<Module> parseIcsCalendar() {
        try {
            logger.setLevel(Level.WARNING);
            // Initially the data comes in utc timezone, we would later be converting it to sg timezone
            dateFormat.setTimeZone(UTC_TIME_ZONE);
            iterateThroughCalendar();
            StudyManager.printMessageWithDivider(PARSE_SUCCESS_MESSAGE);
            listIcsModules();

        } catch (ParseException | ParserException e) {
            handleParseException();
        } catch (FileNotFoundException e) {
            System.out.printf(FILE_NOT_FOUND_ERROR, icsFilePath);
        } catch (IOException e) {
            StudyManager.printMessageWithDivider(PARSE_FAILURE_MESSAGE);
        }
        return modulesList;
    }

    private void iterateThroughCalendar() throws IOException, ParserException, ParseException {
        final UnfoldingReader unfoldingReader = new UnfoldingReader(new FileReader(icsFilePath), true);
        Calendar calendar = builder.build(unfoldingReader);

        for (final Object componentObject : calendar.getComponents()) {
            Component calendarComponent = (Component)componentObject;
            StringBuilder timeSlot = new StringBuilder();
            iterateEachCalendarComponent(calendarComponent, timeSlot);
            moduleTime = timeSlot.toString().toLowerCase();
            getExamDate();
            standardizeModuleCategory();
            modulesList.add(new Module(moduleCode,moduleCategory,moduleDay,moduleTime));

        }
    }

    private void iterateEachCalendarComponent(Component calendarComponent, StringBuilder timeSlot)
            throws ParseException {
        // Iterates through all the components of ics file and only take the necessary properties
        for (final Object propertyObject : calendarComponent.getProperties()) {
            Property property = (Property)propertyObject;
            switch (property.getName()) {
            case ICS_COMPONENT_SUMMARY:
                getModuleSummary(property);
                break;
            case ICS_COMPONENT_START_DATE:
                getModuleStartTime(property, timeSlot);
                break;
            case ICS_COMPONENT_END_DATE:
                getModuleEndTime(property, timeSlot);
                break;
            default:
                continue;
            }
        }
    }


    private void handleParseException() {
        if (!icsFilePath.contains(ICS_FILE_FORMAT)) {
            StudyManager.printMessageWithDivider(NOT_ICS_ERROR_MESSAGE + System.lineSeparator()
                    + CHECK_DIRECTORY_ERROR_MESSAGE);
        } else {
            StudyManager.printMessageWithDivider(PARSE_FAILURE_MESSAGE);
        }
    }

    private void listIcsModules() {
        int i = 1;
        for (Module m: modulesList) {
            System.out.println((i++) + ": " + m.toString());
        }
        StudyManager.printMessageWithDivider(ADDED_TO_SCHEDULE_MESSAGE);
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

    private void getModuleEndTime(Property property, StringBuilder timeSlot) throws ParseException {
        String endTime = property.getValue();
        Date dateEnd = dateFormat.parse(endTime);
        Instant instant = dateEnd.toInstant();
        ZonedDateTime zonedDateEnd = instant.atZone(SG_ZONE_ID);
        timeSlot.append("-" +  zonedDateEnd.format(TIME_FORMATTER));
    }

    private void getModuleStartTime(Property property, StringBuilder timeSlot) throws ParseException {
        String startTime = property.getValue();
        dateStart = dateFormat.parse(startTime);
        Instant instant = dateStart.toInstant();
        ZonedDateTime zonedDateStart = instant.atZone(SG_ZONE_ID);
        timeSlot.append(zonedDateStart.format(TIME_FORMATTER));

        java.util.Calendar calendarNew = java.util.Calendar.getInstance();
        calendarNew.setTime(dateStart);
        int day = calendarNew.get(java.util.Calendar.DAY_OF_WEEK);
        moduleDay = convertDayToString(day);
    }

    private void getModuleSummary(Property property) {
        String[] summary = property.getValue().split(" ",2);
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
            StudyManager.printMessageWithDivider(e.getMessage());
        }
        return day;
    }

}
