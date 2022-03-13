package seedu.allonus.modules;


import seedu.allonus.ui.TextUi;

import java.util.ArrayList;


public class StudyManager {
    private static ArrayList<Module> modulesList = new ArrayList<>();
    private static final String WELCOME_MESSAGE = "Welcome to Modules Tracker, where you can track all your "
            + "classes.";

    public void studyManagerRunner(TextUi ui) {
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
            } else {
                printMessage("Sorry I did not get that!");
            }
        }
    }

    private void printMessage(String message) {
        System.out.println(message);
    }

    private void printWelcomeMessage() {
        printMessage(WELCOME_MESSAGE);
    }

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

    public void deleteModule(String userInput) {
        String moduleNumber = userInput.replace("rm ","");
        int moduleIndex = Integer.parseInt(moduleNumber) - 1;
        if (modulesList.get(moduleIndex) != null) {
            Module removedModule = modulesList.get(moduleIndex);
            modulesList.remove(moduleIndex);
            printMessage("Noted I have removed this module from your schedule:");
            printMessage(removedModule.toString());
        }
    }

    public void addModule(String userInput) {
        Module newModule = addModuleParser(userInput);
        modulesList.add(newModule);
        printMessage("Okay, I have added a new module to the schedule");
        printMessage(newModule.toString());

    }

    public Module addModuleParser(String userInput) {
        //add m/CS2113 c/lec d/Thursday t/2pm-4pm
        String[] rawInput = userInput.split(" ", 2);
        String[] parameters = rawInput[1].split(" ", 4);
        String module = parameters[0].substring(2);
        String category = parameters[1].substring(2);
        String day = parameters[2].substring(2);
        String time = parameters[3].substring(2);

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
            return null;
        }
        return new Module(module,category,day,time);
    }
}

