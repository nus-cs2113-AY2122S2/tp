package seedu.allonus.modules;


import seedu.allonus.AllOnUs;
import seedu.allonus.mode.Mode;
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



/**
 * Represents the class that will handle Modules created by the user.
 */
public class StudyManager {
    public static final String ALREADY_IN_STUDY_MANAGER_MESSAGE = "You are already in Study Manager.";
    private static ArrayList<Module> modulesList = new ArrayList<>();

    /**
     * Study manager messages, commands and constants.
     */
    private static final String UNKNOWN_INPUT_MESSAGE = "Sorry I did not get that!";
    private static final String MENU_COMMAND = "menu";
    private static final String LIST_COMMAND = "list";
    private static final String DELETE_COMMAND = "rm";
    public static final String ADD_COMMAND = "add";
    private static final String EDIT_COMMAND = "edit";
    public static final String FIND_COMMAND = "find";
    public static final String READ_ICS_COMMAND = "read ics";

    public static final String MODULE_TIME_DELIMITER = "t/";
    public static final String MODULE_DAY_DELIMITER = "d/";
    public static final String MODULE_CODE_DELIMITER = "m/";
    public static final String MODULE_CATEGORY_DELIMITER = "c/";
    public static final String MODULE_CATEGORY_LEC = "Lecture";
    public static final String MODULE_CATEGORY_TUT = "Tutorial";
    public static final String MODULE_CATEGORY_EXAM = "Exam";
    public static final String MODULE_CATEGORY_LAB = "Laboratory";
    public static final String CATEGORY_LECTURE_SHORTHAND = "lec";
    public static final String CATEGORY_TUTORIAL_SHORTHAND = "tut";
    public static final String CATEGORY_EXAM_SHORTHAND = "exam";
    public static final String CATEGORY_LAB_SHORTHAND = "lab";

    private static final String WELCOME_MESSAGE = "Welcome to Modules Tracker, where you can track all your "
            + "classes.";
    public static final String EMPTY_STRING = "";

    /**
     * Edit module messages.
     */
    public static final String EDIT_MODULE_DONE_COMMAND = "done";
    private static final String EDIT_MODULE_OPENING_MESSAGE = "Here is the module that you have chosen to edit:";
    private static final String EDIT_MODULE_CHOOSE_MESSAGE = "Choose the part that you would like to edit:";
    private static final String EDIT_MODULE_SUCCESS_MESSAGE = "Your Module was successfully edited! "
            + "Here are the changes:";
    private static final String EDIT_MODULE_EXIT_MESSAGE = "Exiting the edit mode";
    public static final String EDIT_MODULE_CHANGES_MESSAGE = "Here are the changes so far. "
            + "You can edit more module parameters or you can enter 'done' to stop editing!";
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

    public static final String LOGGER_IDENTIFIER = "mylogger";

    private static Logger logger = Logger.getLogger(LOGGER_IDENTIFIER);

    private static StorageFile storageFile = new StorageFile();
    private static boolean isModified = false;
    private static TextUi textUi = new TextUi();

    private static ModuleParser moduleParser = new ModuleParser();


    public ArrayList<Module> getModulesList() {
        return modulesList;
    }

    /**
     * Runs the Study Manager to manage the modules.
     * @param ui Contains the input by the user.
     * @return mode value pertaining to either menu, expense tracker or contact manager.
     */
    public Mode studyManagerRunner(TextUi ui) {
        ModuleCalendarReader icsParser = new ModuleCalendarReader();
        logger.setLevel(Level.WARNING);
        printWelcomeMessage();
        String userInput;
        boolean isRunning = true;
        Mode mode = Mode.MENU;
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
            } else if (AllOnUs.isContactsManagerCommand(userInput)) {
                mode = Mode.CONTACTS_MANAGER;
                isRunning = false;
            } else if (AllOnUs.isExpenseTrackerCommand(userInput)) {
                mode = Mode.EXPENSE_TRACKER;
                isRunning = false;
            } else if (AllOnUs.isStudyManagerCommand(userInput)) {
                printAlreadyInStudyManagerMessage(ui);
            } else {
                printMessageWithDivider(UNKNOWN_INPUT_MESSAGE);
            }
            if (isModified) {
                storageFile.saveData();
            }
        }
        return mode;
    }

    /**
     * Prints a message to inform user they are already in the study manager.
     */
    private void printAlreadyInStudyManagerMessage(TextUi ui) {
        ui.showToUser(ALREADY_IN_STUDY_MANAGER_MESSAGE);
    }

    private void openIcsFile(TextUi ui, ModuleCalendarReader icsParser) {
        printMessageWithDivider("Please enter the name of your .ics file from nusmods:");
        String input = ui.getUserInput();
        ArrayList<Module> icsModulesList = icsParser.readIcsFile(input);
        if (icsModulesList != null) {
            modulesList.addAll(icsModulesList);
            isModified = true;
        }
        printMessageWithDivider("Exiting read ics mode");
    }

    /**
     * Prints a given input string using system output.
     * @param message String that is to be printed on the console.
     */
    public static void printMessageWithDivider(String message) {
        textUi.showToUser(message);
        //System.out.println(message);
    }

    private void printMessage(String message) {
        System.out.println(message);
    }

    private void printWelcomeMessage() {
        printMessageWithDivider(WELCOME_MESSAGE);
    }

    /**
     * Prints the modules currently in the module list.
     */
    public void listModules() {
        if (modulesList.size() == 0) {
            printMessageWithDivider(EMPTY_MODULE_LIST_MESSAGE);
            return;
        }
        printMessageWithDivider(LIST_MODULES_MESSAGE);
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
                performDelete(moduleIndex);
            }
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, LOGGER_WRONG_INDEX_DELETE);
            if (modulesList.size() == 0) {
                printMessageWithDivider(DELETE_NO_MODULES_ERROR);
            } else {
                printListSizeErrorMessage();
            }
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, LOGGER_NO_INDEX_DELETE);
            printMessageWithDivider(DELETE_NO_INDEX_ERROR);
        }

    }

    private void performDelete(int moduleIndex) {
        assert moduleIndex <= modulesList.size();
        assert moduleIndex >= 0;
        Module removedModule = modulesList.get(moduleIndex);
        modulesList.remove(moduleIndex);
        printMessageWithDivider(DELETE_MODULE_SUCCESS_MESSAGE + System.lineSeparator() + removedModule.toString());
        isModified = true;
    }

    private void printListSizeErrorMessage() {
        String listSizeError = "Oops there are only " + modulesList.size() + " modules left in your schedule";
        printMessageWithDivider(listSizeError);
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
                printMessageWithDivider(EDIT_NO_MODULES_ERROR);
            } else {
                printListSizeErrorMessage();
            }
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, LOGGER_NO_EDIT_INDEX);
            printMessageWithDivider(EDIT_NO_INDEX_ERROR);
        }
    }

    protected void editModuleRunner(TextUi ui, int moduleIndex) {
        assert moduleIndex <= modulesList.size();
        assert moduleIndex >= 0;
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
            } else if (editUserInput.equals(EDIT_MODULE_DONE_COMMAND)) {
                printMessageWithDivider(EDIT_MODULE_SUCCESS_MESSAGE
                        + System.lineSeparator() + moduleToEdit.toString());
                isEditFinished = true;
                isModified = true;
            } else {
                printMessageWithDivider(UNKNOWN_INPUT_MESSAGE);
            }
        }
        printMessageWithDivider(EDIT_MODULE_EXIT_MESSAGE);
    }

    private void printEditWelcomeMessage(Module moduleToEdit) {
        printMessageWithDivider(EDIT_MODULE_OPENING_MESSAGE + System.lineSeparator()
                + moduleToEdit.toString() + System.lineSeparator() + EDIT_MODULE_CHOOSE_MESSAGE);
    }

    protected void editModuleTime(Module moduleToEdit, String editUserInput) {
        try {
            String moduleTime = editUserInput.replace(MODULE_TIME_DELIMITER, EMPTY_STRING);
            if (moduleTime.equals("")) {
                throw new ModuleTimeException(MISSING_MODULE_TIME_MESSAGE);
            }
            moduleTime = moduleParser.validateModuleTime(moduleTime);
            moduleToEdit.setTimeSlot(moduleTime);
            printMessageWithDivider(EDIT_MODULE_CHANGES_MESSAGE + System.lineSeparator() + moduleToEdit.toString());
        } catch (ModuleTimeException e) {
            printMessageWithDivider(e.getMessage());
        }
    }

    protected void editModuleDay(Module moduleToEdit, String editUserInput) {
        try {
            String moduleDay = editUserInput.replace(MODULE_DAY_DELIMITER,EMPTY_STRING);
            if (moduleDay.equals("")) {
                throw new ModuleDayException(MISSING_MODULE_DAY_MESSAGE);
            }
            moduleDay = moduleParser.validateModuleDay(moduleDay);
            moduleToEdit.setDay(moduleDay);
            printMessageWithDivider(EDIT_MODULE_CHANGES_MESSAGE + System.lineSeparator() + moduleToEdit.toString());
        } catch (ModuleDayException e) {
            printMessageWithDivider(e.getMessage());
        }

    }

    protected void editModuleCode(Module moduleToEdit, String editUserInput) {
        try {
            String moduleCode = editUserInput.replace(MODULE_CODE_DELIMITER, EMPTY_STRING);
            if (moduleCode.equals("")) {
                throw new ModuleCodeException(MISSING_MODULE_CODE_MESSAGE);
            }
            moduleCode = moduleParser.validateModuleCode(moduleCode);
            moduleToEdit.setModuleCode(moduleCode);
            printMessageWithDivider(EDIT_MODULE_CHANGES_MESSAGE + System.lineSeparator() + moduleToEdit.toString());
        } catch (ModuleCodeException e) {
            printMessageWithDivider(e.getMessage());
        }
    }

    protected void editModuleCategory(Module moduleToEdit, String editUserInput) {
        try {
            String moduleCategory = editUserInput.replace(MODULE_CATEGORY_DELIMITER,EMPTY_STRING);
            if (moduleCategory.equals("")) {
                throw new ModuleCategoryException(MISSING_MODULE_CATEGORY_MESSAGE);
            }
            moduleCategory = moduleParser.validateModuleCategory(moduleCategory);
            moduleToEdit.setCategory(moduleCategory);
            printMessageWithDivider(EDIT_MODULE_CHANGES_MESSAGE + System.lineSeparator() + moduleToEdit.toString());
        } catch (ModuleCategoryException e) {
            printMessageWithDivider(e.getMessage());
        }
    }


    /**
     * Adds a new module to the module list.
     * Calls addModuleParser to parse the input string.
     * @param userInput String input that contains the add command and module attributes.
     * @param shouldPrintConsoleMessage Boolean that decides if messages should be printed onto the console.
     *                                  For testing purpose and internal calls this would be false.
     */
    public void addModule(String userInput, boolean shouldPrintConsoleMessage) {
        Module newModule = moduleParser.addModuleParser(userInput);
        if (newModule == null) {
            return;
        }
        modulesList.add(newModule);
        if (shouldPrintConsoleMessage) {
            printMessageWithDivider(ADD_MODULE_SUCCESS_MESSAGE + System.lineSeparator() + newModule.toString());
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
     * Finds a module in the list using a find query.
     * @param userInput String containing the find command and find query.
     */
    public void findModule(String userInput) {
        try {
            String moduleKeyword = moduleParser.validateFindQuery(userInput);
            moduleKeyword = moduleKeyword.toLowerCase();
            ArrayList<Module> matches = getFindMatches(moduleKeyword);
            if (matches.size() == 0) {
                printMessageWithDivider(FIND_NO_MATCHES_MESSAGE + " \"" + moduleKeyword + "\"");
            } else {
                listMatches(matches);
            }
        } catch (InvalidFindInputException e) {
            logger.log(Level.WARNING, LOGGER_NO_FIND_QUERY);
            printMessageWithDivider(e.getMessage());
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

    private void listMatches(ArrayList<Module> matches) {
        printMessageWithDivider(FIND_LIST_MATCHES_MESSAGE);
        int i = 1;
        for (Module m: matches) {
            System.out.println((i++) + ": " + m);
        }
    }

}

