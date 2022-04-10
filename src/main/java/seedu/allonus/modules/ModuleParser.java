package seedu.allonus.modules;

import seedu.allonus.modules.exceptions.ModuleTimeException;
import seedu.allonus.modules.exceptions.ModuleDayException;
import seedu.allonus.modules.exceptions.ModuleCodeException;
import seedu.allonus.modules.exceptions.ModuleCategoryException;
import seedu.allonus.modules.exceptions.InvalidFindInputException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Represents the class that will parse and validate user inputs for module parameters.
 */
public class ModuleParser {
    /**
     * ModuleParser messages and constants.
     */
    public static final String STRING_SPACE_CHARACTER = " ";
    public static final String EMPTY_STRING = "";
    public static final String REGEX_MODULE_CODE = "^[a-zA-Z0-9]+$";
    public static final String REGEX_MODULE_DAY = "([sS]unday|[mM]onday|[tT]uesday|[wW]ednesday|[tT]hursday"
            + "|[fF]riday|[sS]aturday)";
    public static final String REGEX_MODULE_TIME = "(0?[1-9]|1[0-2]):([0-5]?\\d)\\s*([pa]m)\\s*"
            + "-\\s*(0?[1-9]|1[0-2]):([0-5]?\\d)\\s*([pa]m)";
    public static final String REGEX_MODULE_DATE = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$";
    public static final String WRONG_DAY_FORMAT_MESSAGE = "Accepted module day inputs are either a day of "
            + "the week or a valid date of type DD-MM-YYYY";
    public static final String WRONG_CODE_FORMAT_MESSAGE = "Your module code must be an alphanumeric parameter!";


    public static final String INVALID_DATE_MESSAGE = "You have entered an invalid date";
    public static final String INVALID_WEEKDAY_MESSAGE = "You have entered an invalid day of the week";
    public static final String WRONG_TIME_FORMAT_MESSAGE = "Accepted module time slot input is"
            + " a valid timeslot of type HH:MM am/pm - HH:MM am/pm";

    private static final String TIME_FORMAT_WITH_AMPM = "h:mm a";
    private static final DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .parseLenient()
            .appendPattern(TIME_FORMAT_WITH_AMPM)
            .toFormatter();



    /**
     * Add module messages.
     */
    private static final String ADD_WRONG_FORMAT_MESSAGE = "Please ensure that your input follows the form:";
    private static final String ADD_SAMPLE_FORMAT_MESSAGE = "add m/CS2113 c/lec d/Thursday t/2:00 pm-4:00 pm";
    private static final String LOGGER_WRONG_ADD_FORMAT = "Wrong format for add module";
    private static final String LOGGER_MISSING_DAY_IN_ADD = "Day was not specified for add module";
    private static final String LOGGER_MISSING_CAT_IN_ADD = "Category was not specified for add module";
    private static final String LOGGER_MISSING_TIME_IN_ADD = "Time was not specified for add module";
    private static final String LOGGER_MISSING_CODE_IN_ADD = "Code was not specified for add module";

    /**
     * Find modules messages.
     */
    public static final String FIND_MISSING_QUERY_MESSAGE = "You have not entered a search keyword to find modules!";
    public static final String FIND_SPECIAL_CHARACTERS_MESSAGE = "You have entered a special character."
            + " Please refine your search query!";
    public static final String FIND_COMMON_SEARCH_QUERY_MESSAGE = "You have entered a query common to all modules. "
            + "Please refine your search query!";

    /**
     * Messages for exceptions and errors.
     */
    private static final String MISSING_MODULE_DAY_MESSAGE = "Please enter the day of your module";
    private static final String MISSING_MODULE_CATEGORY_MESSAGE = "Please enter the category of your module";
    private static final String MISSING_MODULE_TIME_MESSAGE = "Please enter the time of your module's class";
    private static final String MISSING_MODULE_CODE_MESSAGE = "Please enter the code for your module";
    private static final String WRONG_CATEGORY_FORMAT_MESSAGE = "Category has to be one of lec, tut, lab or exam";

    public static final String LOGGER_IDENTIFIER = "mylogger";
    public static final String REGEX_ALL_MODULE_PARAMETERS = "[mcdt]/";
    public static final String INVALID_EQUAL_TIMESLOT_MESSAGE = "You have entered an invalid timeSlot. "
            + "Start time cannot be equal to end time!";
    public static final String INVALID_GREATER_START_TIME_MESSAGE = "You have entered an invalid timeslot. "
            + "Start time cannot be later than end time!";


    private static Logger logger = Logger.getLogger(LOGGER_IDENTIFIER);

    private void printMessage(String message) {
        StudyManager.printMessageWithDivider(message);
    }

    /**
     * Parses the input string for the module attributes and creates a new module object.
     * Handles the exceptions if there are any missing attributes.
     *
     * @param userInput String input that contains the add command and module attributes.
     * @return Module object that was created.
     */
    public Module addModuleParser(String userInput) {
        //Accepts input of the form add m/CS2113 c/lec d/Thursday t/2pm-4pm
        try {
            String[] rawInput = userInput.split(" ", 2);
            String[] parameters = getSplitParameters(rawInput[1]);
            String[] checkedParameters = validateAddInputs(parameters);
            String module = checkedParameters[0];
            String category = checkedParameters[1];
            String day = checkedParameters[2];
            String time = checkedParameters[3];

            return new Module(module, category, day, time);
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, LOGGER_WRONG_ADD_FORMAT);
            printMessage(ADD_WRONG_FORMAT_MESSAGE + System.lineSeparator() + ADD_SAMPLE_FORMAT_MESSAGE);
            return null;
        } catch (ModuleDayException e) {
            logger.log(Level.WARNING, LOGGER_MISSING_DAY_IN_ADD);
            printMessage(e.getMessage());
            return null;
        } catch (ModuleCategoryException e) {
            printMessage(e.getMessage());
            logger.log(Level.WARNING, LOGGER_MISSING_CAT_IN_ADD);
            return null;
        } catch (ModuleTimeException e) {
            logger.log(Level.WARNING, LOGGER_MISSING_TIME_IN_ADD);
            printMessage(e.getMessage());
            return null;
        } catch (ModuleCodeException e) {
            logger.log(Level.WARNING, LOGGER_MISSING_CODE_IN_ADD);
            printMessage(e.getMessage());
            return null;
        }
    }

    private String[] getSplitParameters(String userInput) {
        String regex = REGEX_ALL_MODULE_PARAMETERS + ".*?(?=(" + REGEX_ALL_MODULE_PARAMETERS + "|$))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userInput);

        String[] splitParameters = new String[4];
        int i = 0;
        while (matcher.find()) {
            splitParameters[i] = matcher.group().trim();
            i++;
        }
        return splitParameters;
    }

    /**
     * Validates the attributes for a new module.
     * Throws relevant exceptions for the respective missing attributes.
     *
     * @param parameters String array containing the user inputs for each attribute.
     * @return String array containing the validated parameters.
     * @throws ModuleCodeException     If the module code is absent in user input.
     * @throws ModuleCategoryException If the module category is absent in user input.
     * @throws ModuleDayException      If the module day is absent in user input.
     * @throws ModuleTimeException     If the module timeslot is absent in user input.
     */
    public String[] validateAddInputs(String[] parameters) throws ModuleCodeException, ModuleCategoryException,
            ModuleDayException, ModuleTimeException {
        String module = moduleCodeChecker(parameters);
        String category = moduleCategoryChecker(parameters);
        String day = moduleDayChecker(parameters);
        String time = moduleTimeChecker(parameters);

        return new String[]{module, category, day, time};
    }

    /**
     * Checks that the module code is valid.
     *
     * @param parameters String array containing the user inputs for each attribute.
     * @return String containing the verified module code.
     * @throws ModuleCodeException If the module code is absent in user input.
     */
    private String moduleCodeChecker(String[] parameters) throws ModuleCodeException {
        String module;
        try {
            if (parameters[0].substring(2).equals(EMPTY_STRING)
                    || !parameters[0].substring(0, 2).equals(StudyManager.MODULE_CODE_DELIMITER)) {
                throw new ModuleCodeException(MISSING_MODULE_CODE_MESSAGE);
            } else {
                module = parameters[0].substring(2);
                module = validateModuleCode(module);
            }
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new ModuleCodeException(MISSING_MODULE_CODE_MESSAGE);
        }
        return module;
    }

    /**
     * Validates the Module code parameter and returns it after validation.
     * @param code User input for the Module code parameter
     * @return String that represents validated Module code
     * @throws ModuleCodeException when the user input code is invalid
     */
    public String validateModuleCode(String code) throws ModuleCodeException {
        String regexModuleCode = REGEX_MODULE_CODE;
        Pattern pattern = Pattern.compile(regexModuleCode);
        Matcher matcher = pattern.matcher(code);
        String moduleCode = null;
        if (matcher.find()) {
            moduleCode = matcher.group().trim();
            return moduleCode;
        } else {
            throw new ModuleCodeException(WRONG_CODE_FORMAT_MESSAGE);
        }
    }

    /**
     * Checks that the module category is valid.
     *
     * @param parameters String array containing the user inputs for each attribute.
     * @return String containing the verified module category.
     * @throws ModuleCategoryException If the module category is absent in user input.
     */
    private String moduleCategoryChecker(String[] parameters) throws ModuleCategoryException {
        String category;
        try {
            if (parameters[1].substring(2).equals(EMPTY_STRING)
                    || !parameters[1].substring(0, 2).equals(StudyManager.MODULE_CATEGORY_DELIMITER)) {
                throw new ModuleCategoryException(MISSING_MODULE_CATEGORY_MESSAGE);
            } else {
                category = parameters[1].substring(2);
                category = validateModuleCategory(category);
                assert category.equals(StudyManager.MODULE_CATEGORY_LEC)
                        || category.equals(StudyManager.MODULE_CATEGORY_TUT)
                        || category.equals(StudyManager.MODULE_CATEGORY_EXAM)
                        || category.equals(StudyManager.MODULE_CATEGORY_LAB);
            }
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new ModuleCategoryException(MISSING_MODULE_CATEGORY_MESSAGE);
        }
        return category;
    }

    /**
     * Validates the Module category parameter and returns it after validation.
     * @param category User input for the Module category parameter
     * @return String that represents validated Module category
     * @throws ModuleCategoryException when the user input category is invalid
     */
    public String validateModuleCategory(String category) throws ModuleCategoryException {
        switch (category) {
        case StudyManager.CATEGORY_LECTURE_SHORTHAND:
            category = StudyManager.MODULE_CATEGORY_LEC;
            break;
        case StudyManager.CATEGORY_TUTORIAL_SHORTHAND:
            category = StudyManager.MODULE_CATEGORY_TUT;
            break;
        case StudyManager.CATEGORY_EXAM_SHORTHAND:
            category = StudyManager.MODULE_CATEGORY_EXAM;
            break;
        case StudyManager.CATEGORY_LAB_SHORTHAND:
            category = StudyManager.MODULE_CATEGORY_LAB;
            break;
        default:
            throw new ModuleCategoryException(WRONG_CATEGORY_FORMAT_MESSAGE);
        }
        return category;
    }

    /**
     * Checks that the module day is valid.
     *
     * @param parameters String array containing the user inputs for each attribute.
     * @return String containing the verified module day.
     * @throws ModuleDayException If the module day is absent in user input.
     */
    private String moduleDayChecker(String[] parameters) throws ModuleDayException {
        String day;
        try {
            if (parameters[2].substring(2).equals(EMPTY_STRING)
                    || !parameters[2].substring(0, 2).equals(StudyManager.MODULE_DAY_DELIMITER)) {
                throw new ModuleDayException(MISSING_MODULE_DAY_MESSAGE);
            } else {
                day = parameters[2].substring(2);
                day = validateModuleDay(day);
            }
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new ModuleDayException(MISSING_MODULE_DAY_MESSAGE);
        } catch (IllegalArgumentException e) {
            throw new ModuleDayException(e.getMessage());
        }
        return day;
    }

    /**
     * Validates the Module day parameter and returns it after validation.
     * @param day User input for the Module day parameter
     * @return String that represents validated Module day
     * @throws ModuleDayException when the user input day is invalid
     */
    public String validateModuleDay(String day) throws ModuleDayException {
        //Accepts either day of the week or date
        String regexDay = REGEX_MODULE_DAY;
        String regexDate = REGEX_MODULE_DATE;

        Pattern pattern = Pattern.compile(regexDay);
        Matcher matcher = pattern.matcher(day);

        Pattern patternDate = Pattern.compile(regexDate);
        Matcher matcherDate = patternDate.matcher(day);

        String dayOfWeek = null;
        String date = null;
        if (matcher.find()) {
            dayOfWeek = matcher.group().trim();
        }
        if (matcherDate.find()) {
            date = matcherDate.group().trim();
        }

        if (date != null && dayOfWeek == null) {
            return date;
        }
        if (date == null && dayOfWeek != null) {
            return dayOfWeek;
        } else {
            if (day.matches(".*\\d.*")) {
                printMessage(INVALID_DATE_MESSAGE);
            } else {
                printMessage(INVALID_WEEKDAY_MESSAGE);
            }
            throw new ModuleDayException(WRONG_DAY_FORMAT_MESSAGE);
        }
    }

    /**
     * Checks that the module time is valid.
     *
     * @param parameters String array containing the user inputs for each attribute.
     * @return String containing the verified module time.
     * @throws ModuleTimeException If the module time is absent in user input.
     */
    private String moduleTimeChecker(String[] parameters) throws ModuleTimeException {
        String time;
        try {
            if (parameters[3].substring(2).equals(EMPTY_STRING)
                    || !parameters[3].substring(0, 2).equals(StudyManager.MODULE_TIME_DELIMITER)) {
                throw new ModuleTimeException(MISSING_MODULE_TIME_MESSAGE);
            } else {
                time = parameters[3].substring(2);
                time = validateModuleTime(time);
            }
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new ModuleTimeException(MISSING_MODULE_TIME_MESSAGE);
        }
        return time;
    }

    /**
     * Validates a given input String time and returns it after validation.
     * @param time User input for the Module time parameter
     * @return String that represents validated Module time
     * @throws ModuleTimeException when the user input time is invalid
     */
    public String validateModuleTime(String time) throws ModuleTimeException {
        String regexTimeSlot = REGEX_MODULE_TIME;
        Pattern pattern = Pattern.compile(regexTimeSlot);
        Matcher matcher = pattern.matcher(time);
        String timeSlot = null;
        if (matcher.find()) {
            timeSlot = matcher.group().trim();
            timeSlot = checkTimeStartEarlier(timeSlot);
            return timeSlot;
        } else {
            throw new ModuleTimeException(WRONG_TIME_FORMAT_MESSAGE);
        }
    }

    // Checks if given start time is earlier than given end time in a String timeSlot
    private String checkTimeStartEarlier(String timeSlot) throws ModuleTimeException {
        try {
            timeSlot = timeSlot.replace(" - ","-");
            // Convert to uppercase so that it is recognized by parser from LocalDateTime which expects AM/PM
            timeSlot = timeSlot.toUpperCase();
            String[] splitTimeArray = timeSlot.split("-", 2);
            splitTimeArray[0] = splitTimeArray[0].trim();
            splitTimeArray[1] = splitTimeArray[1].trim();

            LocalTime startTime = LocalTime.parse(splitTimeArray[0], TIME_FORMATTER);
            LocalTime endTime = LocalTime.parse(splitTimeArray[1], TIME_FORMATTER);

            if (endTime.compareTo(startTime) > 0) {
                return timeSlot.toLowerCase();
            } else if (endTime.compareTo(startTime) == 0) {
                throw new ModuleTimeException(INVALID_EQUAL_TIMESLOT_MESSAGE);
            } else {
                throw new ModuleTimeException(INVALID_GREATER_START_TIME_MESSAGE);
            }
        } catch (DateTimeParseException e) {
            throw new ModuleTimeException(WRONG_TIME_FORMAT_MESSAGE);
        }
    }

    /**
     * Validates find query entered by user and throws an error if the input is invalid.
     * @param userInput User input that contains find command and search query.
     * @return valid module keyword to be used as search query.
     * @throws InvalidFindInputException for empty spaces and special characters in find query.
     */
    public String validateFindQuery(String userInput) throws InvalidFindInputException {
        String moduleKeyword = userInput.replace(StudyManager.FIND_COMMAND + STRING_SPACE_CHARACTER, EMPTY_STRING);
        if (moduleKeyword.equals(STRING_SPACE_CHARACTER) || moduleKeyword.equals(EMPTY_STRING)
                || !userInput.contains(" ")) {
            throw new InvalidFindInputException(FIND_MISSING_QUERY_MESSAGE);
        } else if (moduleKeyword.equals(":") || moduleKeyword.equals("[") || moduleKeyword.equals("]")
                || moduleKeyword.equals(",") || moduleKeyword.equals("-")) {
            throw new InvalidFindInputException(FIND_SPECIAL_CHARACTERS_MESSAGE);
        } else if (moduleKeyword.equals("am") || moduleKeyword.equals("pm")
                || moduleKeyword.equalsIgnoreCase("module")) {
            throw new InvalidFindInputException(FIND_COMMON_SEARCH_QUERY_MESSAGE);
        } else {
            return moduleKeyword;
        }
    }
}
