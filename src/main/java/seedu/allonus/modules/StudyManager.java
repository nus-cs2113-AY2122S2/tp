package seedu.allonus.modules;


import seedu.allonus.modules.exceptions.ModuleCategoryException;
import seedu.allonus.modules.exceptions.ModuleCodeException;
import seedu.allonus.modules.exceptions.ModuleDayException;
import seedu.allonus.modules.exceptions.ModuleTimeException;
import seedu.allonus.ui.TextUi;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Represents the class that will handle Modules created by the user.
 */
public class StudyManager {
    private static ArrayList<Module> modulesList = new ArrayList<>();
    private static final String WELCOME_MESSAGE = "Welcome to Modules Tracker, where you can track all your "
            + "classes.";
    private static Logger logger = Logger.getLogger("mylogger");

    public ArrayList<Module> getModulesList() {
        return modulesList;
    }

    /**
     * Runs the Study Manager to manage the modules.
     * @param ui Contains the input by the user.
     */
    public void studyManagerRunner(TextUi ui) {
        logger.setLevel(Level.WARNING);
        printWelcomeMessage();
        String userInput;
        boolean isRunning = true;
        while (isRunning) {
            userInput = ui.getUserInput();
            if (userInput.equals("menu")) {
                isRunning = false;
            } else if (userInput.equals("list")) {
                listModules();
            } else if (userInput.startsWith("rm")) {
                deleteModule(userInput);
            } else if (userInput.startsWith("add")) {
                addModule(userInput);
            } else if (userInput.startsWith("edit")) {
                editModule(userInput,ui);
            } else if (userInput.startsWith("find")) {
                findModule(userInput);
            } else {
                printMessage("Sorry I did not get that!");
            }
        }
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
            printMessage("There are no modules in your list yet!");
            return;
        }
        printMessage("Here are the modules in your schedule:");
        int i = 1;
        for (Module m: modulesList) {
            printMessage((i++) + ": " + m);
        }
    }

    /**
     * Deletes an existing module denoted by its index from the module list.
     * @param userInput Input string that contains the rm command and the module index
     *                  that is to be deleted.
     */
    public void deleteModule(String userInput) {
        try {
            String moduleIndexString = userInput.replace("rm ","");
            int moduleIndex = Integer.parseInt(moduleIndexString) - 1;
            if (modulesList.get(moduleIndex) != null) {
                Module removedModule = modulesList.get(moduleIndex);
                modulesList.remove(moduleIndex);
                printMessage("Noted I have removed this module from your schedule:");
                printMessage(removedModule.toString());
            }
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "wrong index for delete");
            if (modulesList.size() == 0) {
                printMessage("There are no modules to delete!");
            } else {
                printMessage(" Oops there are only " + modulesList.size() + " modules left in your schedule");
            }
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "no index number specified for delete");
            printMessage("Please enter the index of the module you would like to delete");
        }

    }

    public void editModule(String userInput, TextUi ui) {
        try {
            String moduleIndexString = userInput.replace("edit ", "");
            int moduleIndex = Integer.parseInt(moduleIndexString) - 1;
            if (modulesList.get(moduleIndex) != null) {
                Module moduleToEdit = modulesList.get(moduleIndex);
                printMessage("Here is the module that you have chosen to edit:");
                printMessage(moduleToEdit.toString());
                printMessage("Choose the part that you would like to edit: ");
                boolean isEditFinished = false;
                String editUserInput;
                while (isEditFinished == false) {
                    editUserInput = ui.getUserInput();
                    if (editUserInput.startsWith("category")) {
                        editModuleCategory(moduleToEdit, editUserInput);
                        printMessage(moduleToEdit.toString());
                    } else if (editUserInput.startsWith("code")) {
                        editModuleCode(moduleToEdit, editUserInput);
                        printMessage(moduleToEdit.toString());
                    } else if (editUserInput.startsWith("day")) {
                        editModuleDay(moduleToEdit, editUserInput);
                        printMessage(moduleToEdit.toString());
                    } else if (editUserInput.startsWith("time")) {
                        editModuleTime(moduleToEdit, editUserInput);
                        printMessage(moduleToEdit.toString());
                    } else {
                        printMessage("Your Module was successfully edited! Here are the changes");
                        printMessage(moduleToEdit.toString());
                        isEditFinished = true;
                    }
                }
                printMessage("Exiting the edit mode");
            }
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "wrong index for edit");
            if (modulesList.size() == 0) {
                printMessage("There are no modules to edit!");
            } else {
                printMessage(" Oops there are only " + modulesList.size() + " modules left in your schedule");
            }
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "no index number specified for edit");
            printMessage("Please enter the index of the module you would like to edit");
        }
    }

    private void editModuleTime(Module moduleToEdit, String editUserInput) {
        String moduleTime = editUserInput.replace("time ","");
        moduleToEdit.setTimeSlot(moduleTime);
    }

    private void editModuleDay(Module moduleToEdit, String editUserInput) {
        String moduleDay = editUserInput.replace("day ","");
        moduleToEdit.setDay(moduleDay);
    }

    private void editModuleCode(Module moduleToEdit, String editUserInput) {
        String moduleCode = editUserInput.replace("code ","");
        moduleToEdit.setModuleCode(moduleCode);
    }

    private void editModuleCategory(Module moduleToEdit, String editUserInput) {
        String moduleCategory = editUserInput.replace("category ","");
        try {
            moduleCategory = validateModuleCategory(moduleCategory);
            moduleToEdit.setCategory(moduleCategory);
        } catch (ModuleCategoryException e) {
            printMessage("Category of the module was not of the correct form");
        }
    }


    /**
     * Adds a new module to the module list.
     * Calls addModuleParser to parse the input string.
     * @param userInput String input that contains the add command and module attributes.
     */
    public void addModule(String userInput) {
        Module newModule = addModuleParser(userInput);
        if (newModule == null) {
            return;
        }
        modulesList.add(newModule);
        printMessage("Okay, I have added a new module to the schedule");
        printMessage(newModule.toString());

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
            String[] parameters = rawInput[1].split(" ", 4);
            String[] checkedParameters = validateAddInputs(parameters);
            // String module = parameters[0].substring(2);
            // String category = parameters[1].substring(2);
            // String day = parameters[2].substring(2);
            // String time = parameters[3].substring(2);
            String module = checkedParameters[0];
            String category = checkedParameters[1];
            String day = checkedParameters[2];
            String time = checkedParameters[3];

            return new Module(module, category, day, time);
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Wrong format for add module");
            printMessage("Please ensure that your input follows the form:");
            printMessage("add m/CS2113 c/lec d/Thursday t/2pm-4pm");
            return null;
        } catch (ModuleDayException e) {
            logger.log(Level.WARNING, "Day was not specified for add module");
            printMessage("Please enter the day of your module");
            return null;
        } catch (ModuleCategoryException e) {
            logger.log(Level.WARNING, "Category was not specified for add module");
            printMessage("Please enter the category of your module");
            return null;
        } catch (ModuleTimeException e) {
            logger.log(Level.WARNING, "Time was not specified for add module");
            printMessage("Please enter the time of your module's class");
            return null;
        } catch (ModuleCodeException e) {
            logger.log(Level.WARNING, "Code was not specified for add module");
            printMessage("Please enter the code for your module");
            return null;
        }
    }

    public void findModule(String userInput) {
        String moduleKeyword = userInput.replace("find ","");
        ArrayList<Module> matches = new ArrayList<>();
        for (Module m: modulesList) {
            if (m.toString().contains(moduleKeyword)) {
                matches.add(m);
            }
        }
        if (matches.size() == 0) {
            printMessage("There are no modules that match \"" + moduleKeyword + "\"");
        } else {
            listMatches(matches);
        }
    }

    private void listMatches(ArrayList<Module> matches) {
        System.out.println("    Here are the matching tasks in your list:");
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
            if (parameters[0].substring(2).equals("") || !parameters[0].substring(0, 2).equals("m/")) {
                throw new ModuleCodeException();
            } else {
                module = parameters[0].substring(2);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ModuleCodeException();
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
            if (parameters[1].substring(2).equals("") || !parameters[1].substring(0, 2).equals("c/")) {
                throw new ModuleCategoryException();
            } else {
                category = parameters[1].substring(2);
                category = validateModuleCategory(category);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ModuleCategoryException();
        }
        return category;
    }

    private String validateModuleCategory(String category) throws ModuleCategoryException {
        switch (category) {
        case "lec":
            category = "Lecture";
            break;
        case "tut":
            category = "Tutorial";
            break;
        case "exam":
            category = "Exam";
            break;
        default:
            printMessage("Category has to be one of lec,tut or exam");
            throw new ModuleCategoryException();
        }
        assert (category == "Lecture" || category == "Tutorial" || category == "Exam") : "category is not one"
                + " of lec, tut or exam";
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
            if (parameters[2].substring(2).equals("") || !parameters[2].substring(0, 2).equals("d/")) {
                throw new ModuleDayException();
            } else {
                day = parameters[2].substring(2);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ModuleDayException();
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
            if (parameters[3].substring(2).equals("") || !parameters[3].substring(0, 2).equals("t/")) {
                throw new ModuleTimeException();
            } else {
                time = parameters[3].substring(2);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ModuleTimeException();
        }
        return time;
    }

}

