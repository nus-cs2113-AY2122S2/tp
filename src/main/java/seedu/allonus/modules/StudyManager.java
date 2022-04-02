package seedu.allonus.modules;


import seedu.allonus.modules.exceptions.ModuleCodeException;
import seedu.allonus.modules.exceptions.ModuleCategoryException;
import seedu.allonus.modules.exceptions.ModuleDayException;
import seedu.allonus.modules.exceptions.ModuleTimeException;

import seedu.allonus.storage.StorageFile;

import seedu.allonus.modules.exceptions.InvalidFindInputException;


import seedu.allonus.ui.TextUi;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Represents the class that will handle Modules created by the user.
 */
public class StudyManager {



    private static ArrayList<Module> modulesList = new ArrayList<>();

    /**
     * Study manager messages, commands and constants.
     */
    private static final String UNKNOWN_INPUT_MESSAGE = "Sorry I did not get that!";
    private static final String MENU_COMMAND = "menu";
    private static final String LIST_COMMAND = "list";
    private static final String DELETE_COMMAND = "rm";
    private static final String ADD_COMMAND = "add";
    private static final String EDIT_COMMAND = "edit";
    private static final String FIND_COMMAND = "find";
    public static final String READ_ICS_COMMAND = "read ics";
    private static final String MODULE_TIME_DELIMITER = "t/";
    private static final String MODULE_DAY_DELIMITER = "d/";
    private static final String MODULE_CODE_DELIMITER = "m/";
    private static final String MODULE_CATEGORY_DELIMITER = "c/";
    private static final String MODULE_CATEGORY_LEC = "Lecture";
    private static final String MODULE_CATEGORY_TUT = "Tutorial";
    private static final String MODULE_CATEGORY_EXAM = "Exam";
    private static final String MODULE_CATEGORY_LAB = "Laboratory";
    private static final String CATEGORY_LECTURE_SHORTHAND = "lec";
    private static final String CATEGORY_TUTORIAL_SHORTHAND = "tut";
    private static final String CATEGORY_EXAM_SHORTHAND = "exam";
    private static final String CATEGORY_LAB_SHORTHAND = "lab";
    private static final String WELCOME_MESSAGE = "Welcome to Modules Tracker, where you can track all your "
            + "classes.";
    public static final String STRING_SPACE_CHARACTER = " ";
    public static final String EMPTY_STRING = "";

    /**
     * Edit module messages.
     */
    private static final String EDIT_MODULE_OPENING_MESSAGE = "Here is the module that you have chosen to edit:";
    private static final String EDIT_MODULE_CHOOSE_MESSAGE = "Choose the part that you would like to edit:";
    private static final String EDIT_MODULE_SUCCESS_MESSAGE = "Your Module was successfully edited! "
            + "Here are the changes";
    private static final String EDIT_MODULE_EXIT_MESSAGE = "Exiting the edit mode";
    private static final String EDIT_NO_MODULES_ERROR = "There are no modules to edit!";
    private static final String EDIT_NO_INDEX_ERROR = "Please enter the index of the module you would like to edit";
    private static final String LOGGER_WRONG_EDIT_INDEX = "wrong index for edit";
    private static final String LOGGER_NO_EDIT_INDEX = "no index number specified for edit";

    /**
     * Delete module messages.
     */
    private static final String DELETE_MODULE_SUCCESS_MESSAGE = "Noted I have removed this module from your schedule:";
    private static final String DELETE_NO_MODULES_ERROR = "There are no modules to delete!";
    private static final String DELETE_NO_INDEX_ERROR = "Please enter the index of the module you would like to delete";
    private static final String LOGGER_WRONG_INDEX_DELETE = "wrong index for delete";
    private static final String LOGGER_NO_INDEX_DELETE = "no index number specified for delete";

    /**
     * Add module messages.
     */
    private static final String ADD_MODULE_SUCCESS_MESSAGE = "Okay, I have added a new module to the schedule";
    private static final String ADD_WRONG_FORMAT_MESSAGE = "Please ensure that your input follows the form:";
    private static final String ADD_SAMPLE_FORMAT_MESSAGE = "add m/CS2113 c/lec d/Thursday t/2pm-4pm";
    private static final String LOGGER_WRONG_ADD_FORMAT = "Wrong format for add module";
    private static final String LOGGER_MISSING_DAY_IN_ADD = "Day was not specified for add module";
    private static final String LOGGER_MISSING_CAT_IN_ADD = "Category was not specified for add module";
    private static final String LOGGER_MISSING_TIME_IN_ADD = "Time was not specified for add module";
    private static final String LOGGER_MISSING_CODE_IN_ADD = "Code was not specified for add module";

    /**
     * Find modules messages.
     */
    private static final String FIND_NO_MATCHES_MESSAGE = "There are no modules that match";
    private static final String FIND_LIST_MATCHES_MESSAGE = "Here are the matching modules in your list:";
    private static final String LOGGER_NO_FIND_QUERY = "no search query was entered for find";

    /**
     * List modules messages.
     */
    private static final String EMPTY_MODULE_LIST_MESSAGE = "There are no modules in your list yet!";
    private static final String LIST_MODULES_MESSAGE = "Here are the modules in your schedule:";

    /**
     * Messages for exceptions and errors.
     */
    private static final String MISSING_MODULE_DAY_MESSAGE = "Please enter the day of your module";
    private static final String MISSING_MODULE_CATEGORY_MESSAGE = "Please enter the category of your module";
    private static final String MISSING_MODULE_TIME_MESSAGE = "Please enter the time of your module's class";
    private static final String MISSING_MODULE_CODE_MESSAGE = "Please enter the code for your module";
    private static final String WRONG_CATEGORY_FORMAT_MESSAGE = "Category has to be one of lec, tut, lab or exam";

    public static final String LOGGER_IDENTIFIER = "mylogger";

    private static Logger logger = Logger.getLogger(LOGGER_IDENTIFIER);

    private static StorageFile storageFile = new StorageFile();
    private static boolean isModified = false;

    public ArrayList<Module> getModulesList() {
        return modulesList;
    }

    /**
     * Runs the Study Manager to manage the modules.
     * @param ui Contains the input by the user.
     */
    public void studyManagerRunner(TextUi ui) {
        ModuleCalendarReader icsParser = new ModuleCalendarReader();
        logger.setLevel(Level.WARNING);
        printWelcomeMessage();
        String userInput;
        boolean isRunning = true;
        while (isRunning) {
            isModified = false;
            userInput = ui.getUserInput();
            if (userInput.equals(MENU_COMMAND)) {
                isRunning = false;
            } else if (userInput.equals(LIST_COMMAND)) {
                listModules();
            } else if (userInput.startsWith(DELETE_COMMAND)) {
                deleteModule(userInput);
            } else if (userInput.startsWith(ADD_COMMAND)) {
                addModule(userInput, true);
            } else if (userInput.startsWith(EDIT_COMMAND)) {
                editModule(userInput,ui);
            } else if (userInput.startsWith(FIND_COMMAND)) {
                findModule(userInput);
            } else if (userInput.startsWith(READ_ICS_COMMAND)) {
                openIcsFile(ui, icsParser);
            } else {
                printMessage(UNKNOWN_INPUT_MESSAGE);
            }
            if (isModified) {
                storageFile.saveData();
            }
        }
    }

    private void openIcsFile(TextUi ui, ModuleCalendarReader icsParser) {
        printMessage("Please enter the name of your .ics file from nusmods:");
        String input = ui.getUserInput();
        ArrayList<Module> icsModulesList = icsParser.readIcsFile(input);
        if (icsModulesList != null) {
            modulesList.addAll(icsModulesList);
            isModified = true;
        }
        printMessage("Exiting read ics mode");
    }

    /**
     * Prints a given input string using system output.
     * @param message String that is to be printed on the console.
     */
    private void printMessage(String message) {
        System.out.println(message);
    }

    private void printWelcomeMessage() {
        printMessage(WELCOME_MESSAGE);
    }

    /**
     * Prints the modules currently in the module list.
     */
    public void listModules() {
        if (modulesList.size() == 0) {
            printMessage(EMPTY_MODULE_LIST_MESSAGE);
            return;
        }
        printMessage(LIST_MODULES_MESSAGE);
        int i = 1;
        for (Module m: modulesList) {
            printMessage((i++) + ": " + m);
        }
    }

    /**
     * Returns current number of items in module list.
     *
     * @return number of items in module list.
     */
    public int getModuleCount() {
        return modulesList.size();
    }

    /**
     * Returns logger attribute of this class StudyManager.
     *
     * @return logger, an instance of class <code>Logger</code>, belonging to this <code>StudyManager</code> class.
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * Deletes an existing module denoted by its index from the module list.
     * @param userInput Input string that contains the rm command and the module index
     *                  that is to be deleted.
     */
    public void deleteModule(String userInput) {
        try {
            String moduleIndexString = userInput.replace(DELETE_COMMAND + " ",EMPTY_STRING);
            int moduleIndex = Integer.parseInt(moduleIndexString) - 1;
            if (modulesList.get(moduleIndex) != null) {
                Module removedModule = modulesList.get(moduleIndex);
                modulesList.remove(moduleIndex);
                printMessage(DELETE_MODULE_SUCCESS_MESSAGE);
                printMessage(removedModule.toString());
                isModified = true;
            }
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, LOGGER_WRONG_INDEX_DELETE);
            if (modulesList.size() == 0) {
                printMessage(DELETE_NO_MODULES_ERROR);
            } else {
                printListSizeErrorMessage();
            }
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, LOGGER_NO_INDEX_DELETE);
            printMessage(DELETE_NO_INDEX_ERROR);
        }

    }

    private void printListSizeErrorMessage() {
        String listSizeError = "Oops there are only " + modulesList.size() + " modules left in your schedule";
        printMessage(listSizeError);
    }

    /**
     * Edits a module in the list.
     * @param userInput String containing edit command and module index.
     * @param ui Captures user input on console.
     */
    public void editModule(String userInput, TextUi ui) {
        try {
            String moduleIndexString = userInput.replace(EDIT_COMMAND + " ", EMPTY_STRING);
            int moduleIndex = Integer.parseInt(moduleIndexString) - 1;
            if (modulesList.get(moduleIndex) != null) {
                editModuleRunner(ui, moduleIndex);
            }
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, LOGGER_WRONG_EDIT_INDEX);
            if (modulesList.size() == 0) {
                printMessage(EDIT_NO_MODULES_ERROR);
            } else {
                printListSizeErrorMessage();
            }
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, LOGGER_NO_EDIT_INDEX);
            printMessage(EDIT_NO_INDEX_ERROR);
        }
    }

    private void editModuleRunner(TextUi ui, int moduleIndex) {
        Module moduleToEdit = modulesList.get(moduleIndex);
        printEditWelcomeMessage(moduleToEdit);
        boolean isEditFinished = false;
        String editUserInput;
        while (!isEditFinished) {
            editUserInput = ui.getUserInput();
            if (editUserInput.startsWith(MODULE_CATEGORY_DELIMITER)) {
                editModuleCategory(moduleToEdit, editUserInput);
            } else if (editUserInput.startsWith(MODULE_CODE_DELIMITER)) {
                editModuleCode(moduleToEdit, editUserInput);
            } else if (editUserInput.startsWith(MODULE_DAY_DELIMITER)) {
                editModuleDay(moduleToEdit, editUserInput);
            } else if (editUserInput.startsWith(MODULE_TIME_DELIMITER)) {
                editModuleTime(moduleToEdit, editUserInput);
            } else if (editUserInput.equals("done")) {
                printMessage(EDIT_MODULE_SUCCESS_MESSAGE);
                printMessage(moduleToEdit.toString());
                isEditFinished = true;
                isModified = true;
            } else {
                printMessage(UNKNOWN_INPUT_MESSAGE);
            }
        }
        printMessage(EDIT_MODULE_EXIT_MESSAGE);
    }

    private void printEditWelcomeMessage(Module moduleToEdit) {
        printMessage(EDIT_MODULE_OPENING_MESSAGE);
        printMessage(moduleToEdit.toString());
        printMessage(EDIT_MODULE_CHOOSE_MESSAGE);
    }

    private void editModuleTime(Module moduleToEdit, String editUserInput) {
        try {
            String moduleTime = editUserInput.replace(MODULE_TIME_DELIMITER, EMPTY_STRING);
            if (moduleTime.equals("")) {
                throw new ModuleTimeException(MISSING_MODULE_TIME_MESSAGE);
            }
            moduleToEdit.setTimeSlot(moduleTime);
            printMessage(moduleToEdit.toString());
        } catch (ModuleTimeException e) {
            printMessage(e.getMessage());
        }
    }

    private void editModuleDay(Module moduleToEdit, String editUserInput) {
        try {
            String moduleDay = editUserInput.replace(MODULE_DAY_DELIMITER,EMPTY_STRING);
            if (moduleDay.equals("")) {
                throw new ModuleDayException(MISSING_MODULE_DAY_MESSAGE);
            }
            moduleToEdit.setDay(moduleDay);
            printMessage(moduleToEdit.toString());
        } catch (ModuleDayException e) {
            printMessage(e.getMessage());
        }

    }

    private void editModuleCode(Module moduleToEdit, String editUserInput) {
        try {
            String moduleCode = editUserInput.replace(MODULE_CODE_DELIMITER, EMPTY_STRING);
            if (moduleCode.equals("")) {
                throw new ModuleCodeException(MISSING_MODULE_CODE_MESSAGE);
            }
            moduleToEdit.setModuleCode(moduleCode);
            printMessage(moduleToEdit.toString());
        } catch (ModuleCodeException e) {
            printMessage(e.getMessage());
        }
    }

    private void editModuleCategory(Module moduleToEdit, String editUserInput) {
        try {
            String moduleCategory = editUserInput.replace(MODULE_CATEGORY_DELIMITER,EMPTY_STRING);
            if (moduleCategory.equals("")) {
                throw new ModuleCategoryException(MISSING_MODULE_CATEGORY_MESSAGE);
            }
            moduleCategory = validateModuleCategory(moduleCategory);
            moduleToEdit.setCategory(moduleCategory);
            printMessage(moduleToEdit.toString());
        } catch (ModuleCategoryException e) {
            printMessage(e.getMessage());
        }
    }


    /**
     * Adds a new module to the module list.
     * Calls addModuleParser to parse the input string.
     * @param userInput String input that contains the add command and module attributes.
     */
    public void addModule(String userInput, boolean fromCommandLine) {
        Module newModule = addModuleParser(userInput);
        if (newModule == null) {
            return;
        }
        modulesList.add(newModule);
        if (fromCommandLine) {
            printMessage(ADD_MODULE_SUCCESS_MESSAGE);
            printMessage(newModule.toString());
        }
        isModified = true;
    }

    /**
     * Executes <code>addModule</code> method with saved module entry from data file.
     *
     * @param savedModule the saved module entry
     */
    public void loadAdd(String savedModule) {
        addModule(savedModule, false);
    }

    /**
     * Parses the input string for the module attributes and creates a new module object.
     * Handles the exceptions if there are any missing attributes.
     * @param userInput String input that contains the add command and module attributes.
     * @return Module object that was created.
     */
    public Module addModuleParser(String userInput) {
        //add m/CS2113 c/lec d/Thursday t/2pm-4pm
        try {
            String[] rawInput = userInput.split(" ", 2);
            //String[] parameters = rawInput[1].split(" ", 4);
            //String[] parameters = rawInput[1].split("[mcdt]/ ", 4);
            String[] parameters = getSplitParameters(rawInput[1]);
            String[] checkedParameters = validateAddInputs(parameters);
            String module = checkedParameters[0];
            String category = checkedParameters[1];
            String day = checkedParameters[2];
            String time = checkedParameters[3];

            return new Module(module, category, day, time);
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, LOGGER_WRONG_ADD_FORMAT);
            printMessage(ADD_WRONG_FORMAT_MESSAGE);
            printMessage(ADD_SAMPLE_FORMAT_MESSAGE);
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
        String regex = "[mcdt]/" + ".*?(?=(" + "[mcdt]/" + "|$))";
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
     * Finds a module in the list using a find query.
     * @param userInput String containing the find command and find query.
     */
    public void findModule(String userInput) {
        try {
            String moduleKeyword = validateFindQuery(userInput);
            moduleKeyword = moduleKeyword.toLowerCase();
            ArrayList<Module> matches = getFindMatches(moduleKeyword);
            if (matches.size() == 0) {
                printMessage(FIND_NO_MATCHES_MESSAGE + " \"" + moduleKeyword + "\"");
            } else {
                listMatches(matches);
            }
        } catch (InvalidFindInputException e) {
            logger.log(Level.WARNING, LOGGER_NO_FIND_QUERY);
            printMessage(e.getMessage());
        }
    }

    private ArrayList<Module> getFindMatches(String moduleKeyword) {
        ArrayList<Module> matches = new ArrayList<>();
        for (Module m : modulesList) {
            String moduleStringLowerCase = m.toString().toLowerCase();
            if (moduleStringLowerCase.contains(moduleKeyword)) {
                matches.add(m);
            }
        }
        return matches;
    }

    /**
     * Validates find query entered by user and throws an error if the input is invalid.
     * @param userInput User input that contains find command and search query.
     * @return valid module keyword to be used as search query.
     * @throws InvalidFindInputException for empty spaces and special characters in find query.
     */
    private String validateFindQuery(String userInput) throws InvalidFindInputException {
        String moduleKeyword = userInput.replace(FIND_COMMAND + STRING_SPACE_CHARACTER, EMPTY_STRING);
        if (moduleKeyword.equals(STRING_SPACE_CHARACTER) || moduleKeyword.equals(EMPTY_STRING)
                || !userInput.contains(" ")) {
            throw new InvalidFindInputException("You have not entered a search keyword to find modules!");
        } else if (moduleKeyword.equals(":") || moduleKeyword.equals("[") || moduleKeyword.equals("]")
                || moduleKeyword.equals(",") || moduleKeyword.equals("-")) {
            throw new InvalidFindInputException("You have entered a special character."
                    + " Please refine your search query!");
        }
        return moduleKeyword;
    }

    private void listMatches(ArrayList<Module> matches) {
        System.out.println(FIND_LIST_MATCHES_MESSAGE);
        int i = 1;
        for (Module m: matches) {
            System.out.println((i++) + ": " + m);
        }
    }


    /**
     * Validates the attributes for a new module.
     * Throws relevant exceptions for the respective missing attributes.
     * @param parameters String array containing the user inputs for each attribute.
     * @return String array containing the validated parameters.
     * @throws ModuleCodeException If the module code is absent in user input.
     * @throws ModuleCategoryException If the module category is absent in user input.
     * @throws ModuleDayException If the module day is absent in user input.
     * @throws ModuleTimeException If the module timeslot is absent in user input.
     */
    public String[] validateAddInputs(String[] parameters) throws ModuleCodeException, ModuleCategoryException,
            ModuleDayException, ModuleTimeException {
        String module = moduleCodeChecker(parameters);
        String category = moduleCategoryChecker(parameters);
        String day = moduleDayChecker(parameters);
        String time = moduleTimeChecker(parameters);

        return new String[]{module,category,day,time};
    }

    /**
     * Checks that the module code is valid.
     * @param parameters String array containing the user inputs for each attribute.
     * @return String containing the verified module code.
     * @throws ModuleCodeException If the module code is absent in user input.
     */
    private String moduleCodeChecker(String[] parameters) throws ModuleCodeException {
        String module;
        try {
            if (parameters[0].substring(2).equals(EMPTY_STRING)
                    || !parameters[0].substring(0, 2).equals(MODULE_CODE_DELIMITER)) {
                throw new ModuleCodeException(MISSING_MODULE_CODE_MESSAGE);
            } else {
                module = parameters[0].substring(2);
            }
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new ModuleCodeException(MISSING_MODULE_CODE_MESSAGE);
        }
        return module;
    }

    /**
     * Checks that the module category is valid.
     * @param parameters String array containing the user inputs for each attribute.
     * @return String containing the verified module category.
     * @throws ModuleCategoryException If the module category is absent in user input.
     */
    private String moduleCategoryChecker(String[] parameters) throws ModuleCategoryException {
        String category;
        try {
            if (parameters[1].substring(2).equals(EMPTY_STRING)
                    || !parameters[1].substring(0, 2).equals(MODULE_CATEGORY_DELIMITER)) {
                throw new ModuleCategoryException(MISSING_MODULE_CATEGORY_MESSAGE);
            } else {
                category = parameters[1].substring(2);
                category = validateModuleCategory(category);
            }
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new ModuleCategoryException(MISSING_MODULE_CATEGORY_MESSAGE);
        }
        return category;
    }

    private String validateModuleCategory(String category) throws ModuleCategoryException {
        assert (category.equals(CATEGORY_LECTURE_SHORTHAND) || category.equals(CATEGORY_TUTORIAL_SHORTHAND)
                || category.equals(CATEGORY_EXAM_SHORTHAND) || category.equals(CATEGORY_LAB_SHORTHAND))
                : WRONG_CATEGORY_FORMAT_MESSAGE;
        switch (category) {
        case CATEGORY_LECTURE_SHORTHAND:
            category = MODULE_CATEGORY_LEC;
            break;
        case CATEGORY_TUTORIAL_SHORTHAND:
            category = MODULE_CATEGORY_TUT;
            break;
        case CATEGORY_EXAM_SHORTHAND:
            category = MODULE_CATEGORY_EXAM;
            break;
        case CATEGORY_LAB_SHORTHAND:
            category = MODULE_CATEGORY_LAB;
            break;
        default:
            throw new ModuleCategoryException(WRONG_CATEGORY_FORMAT_MESSAGE);
        }
        return category;
    }

    /**
     * Checks that the module day is valid.
     * @param parameters String array containing the user inputs for each attribute.
     * @return String containing the verified module day.
     * @throws ModuleDayException If the module day is absent in user input.
     */
    private String moduleDayChecker(String[] parameters) throws ModuleDayException {
        String day;
        try {
            if (parameters[2].substring(2).equals(EMPTY_STRING)
                    || !parameters[2].substring(0, 2).equals(MODULE_DAY_DELIMITER)) {
                throw new ModuleDayException(MISSING_MODULE_DAY_MESSAGE);
            } else {
                day = parameters[2].substring(2);
            }
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new ModuleDayException(MISSING_MODULE_DAY_MESSAGE);
        }
        return day;
    }

    /**
     * Checks that the module time is valid.
     * @param parameters String array containing the user inputs for each attribute.
     * @return String containing the verified module time.
     * @throws ModuleTimeException If the module time is absent in user input.
     */
    private String moduleTimeChecker(String[] parameters) throws ModuleTimeException {
        String time;
        try {
            if (parameters[3].substring(2).equals(EMPTY_STRING)
                    || !parameters[3].substring(0, 2).equals(MODULE_TIME_DELIMITER)) {
                throw new ModuleTimeException(MISSING_MODULE_TIME_MESSAGE);
            } else {
                time = parameters[3].substring(2);
            }
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new ModuleTimeException(MISSING_MODULE_TIME_MESSAGE);
        }
        return time;
    }

}

