package werkit;


import commands.Command;
import commands.ExitCommand;
import commands.InvalidCommandException;
import commands.PlanCommand;
import commands.WorkoutCommand;
import data.exercises.ExerciseList;
import data.plans.PlanList;
import data.schedule.DayList;
import data.workouts.WorkoutList;
import storage.FileManager;
import storage.LogHandler;
import storage.UnknownFileException;

import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

import static commands.WorkoutCommand.ACTION_KEYWORD_DELETE;

/**
 * This class initiates the various classes/components of WerkIt! and contains the logic code for
 * prompting the user for an input continuously until the user enters the exit command.
 */
public class WerkIt {
    private UI ui;
    private Parser parser;
    private ExerciseList exerciseList;
    private WorkoutList workoutList;
    private FileManager fileManager;
    private PlanList planList;
    private DayList dayList;
    private static Logger logger = Logger.getLogger(WerkIt.class.getName());


    /**
     * Initialises the components of the WerkIt! application, greets the user, and loads the
     * various files stored in the system's local disk into this instance of WerkIt! (if applicable).
     *
     * @throws IOException If the application is unable to load the required directory and/or file(s).
     */
    public WerkIt() throws IOException {
        // Initialise Components
        this.ui = new UI();
        this.exerciseList = new ExerciseList();
        this.workoutList = new WorkoutList(getExerciseList());
        this.planList = new PlanList(getWorkoutList());
        this.fileManager = new FileManager(getPlanList());
        this.dayList = new DayList(getPlanList());
        this.parser = new Parser(getUI(), getExerciseList(), getWorkoutList(),
                getFileManager(), getPlanList(), getDayList());

        LogHandler.linkToFileLogger(logger);
        logger.log(Level.INFO, "Components instantiated.");

        getUI().printGreetings();

        // Do file imports
        loadRequiredDirectoryAndFiles();
    }

    /**
     * Gets the UI object stored in this WerkIt object.
     *
     * @return The UI object.
     */
    public UI getUI() {
        return this.ui;
    }

    /**
     * Gets the Parser object stored in this WerkIt object.
     *
     * @return The Parser object.
     */
    public Parser getParser() {
        return this.parser;
    }

    /**
     * Gets the ExerciseList object stored in this WerkIt object.
     *
     * @return The ExerciseList object.
     */
    public ExerciseList getExerciseList() {
        return this.exerciseList;
    }

    /**
     * Gets the WorkoutList object stored in this WerkIt object.
     *
     * @return The WorkoutList object.
     */
    public WorkoutList getWorkoutList() {
        return this.workoutList;
    }


    public DayList getDayList() {
        return this.dayList;
    }

    /**
     * Gets the FileManager object stored in this WerkIt object.
     *
     * @return The FileManager object.
     */
    public FileManager getFileManager() {
        return this.fileManager;
    }

    /**
     * Gets the PlanList object stored in this WerkIt object.
     *
     * @return The PlanList object.
     */
    public PlanList getPlanList() {
        return this.planList;
    }

    /**
     * Checks if the required resource directory and files already exists in the user's filesystem. If not,
     * call the relevant method(s) to create the required directory and/or file(s). In addition, once all the
     * data is loaded into the respective data structures, all resource files will be re-written to ensure
     * that the data in the files are of a correct format (e.g. lower-case formatting).
     *
     * @throws IOException If the application is unable to create the required directory and/or file(s).
     */
    private void loadRequiredDirectoryAndFiles() throws IOException {
        getUI().printCheckingDirectoryAndFilesMessage();
        getFileManager().checkAndCreateDirectoriesAndFiles();
        getUI().printEmptyLineOrStatus(getFileManager().checkIfAllDirectoryAndFilesExists());

        assert (Files.exists(getFileManager().getWorkoutFilePath())) : "Workout file does not exist.";
        assert (Files.exists(getFileManager().getExerciseFilePath())) : "Exercise file does not exist.";
        assert (Files.exists(getFileManager().getPlanFilePath())) : "Plan file does not exist.";
        assert (Files.exists(getFileManager().getScheduleFilePath())) : "Schedule file does not exist.";

        if (getFileManager().checkIfAtLeastOneFileExists()) {
            getUI().printLoadingFileDataMessage();
        }
        populateExercises();
        if (getFileManager().isWasWorkoutsFileAlreadyMade()) {
            loadWorkoutFile();
        }
        if (getFileManager().isWasPlansFileAlreadyMade()) {
            loadPlanFile();
        }
        if (getFileManager().isWasScheduleFileAlreadyMade()) {
            loadScheduleFile();
        }
        getFileManager().rewriteAllExercisesToFile(getExerciseList());
        getFileManager().rewriteAllWorkoutsToFile(getWorkoutList());
        getFileManager().rewriteAllPlansToFile(getPlanList());
        getFileManager().rewriteAllDaysScheduleToFile(getDayList());
    }

    /**
     * Continuously prompts the user for an input (and thereafter executing the necessary
     * actions) until the exit command is entered.
     * Method adapted from Team Member Alan Low's iP codebase.
     * Link: https://github.com/alanlowzies/ip/blob/8556dd6a5106d190f5ac0458c6d2c34f98737a91/src/main/java/sora/Sora.java
     *
     * @throws IOException If the application is unable to process the user input.
     */
    public void startContinuousUserPrompt() throws IOException {
        boolean isFirstPrompt = true;

        Command newCommand;
        do {
            getUI().printUserInputPrompt(isFirstPrompt);
            isFirstPrompt = false;
            String userInput = getUI().getUserInput();
            newCommand = parseUserInput(userInput);

            if (newCommand == null) {
                continue;
            }

            assert (newCommand != null);

            newCommand.execute();
            conductPostExecuteChecks(newCommand);
        } while (!(newCommand instanceof ExitCommand));

        // User is exiting the program
        getUI().printGoodbye();
    }

    /**
     * Conducts certain procedures after a Command#execute() has been called. Currently, the only procedure
     * carried out post-execution is to reload schedule.txt if the command ran is a 'workout /delete' or
     * 'plan /delete' command.
     *
     * @param newCommand A Command object that represents the user input.
     * @throws IOException If the reloading of schedule.txt is unsuccessful.
     */
    public void conductPostExecuteChecks(Command newCommand) throws IOException {
        boolean isWorkoutOrPlanCommand = (newCommand instanceof WorkoutCommand) || (newCommand instanceof PlanCommand);

        if (!isWorkoutOrPlanCommand) {
            return;
        }

        boolean isDeleteAction = newCommand.getUserAction().equals(ACTION_KEYWORD_DELETE);

        if (isDeleteAction) {
            reloadScheduleFile();
        }
    }

    /**
     * Acts as an intermediary method to call Parser#parseUserInput(). This method was created to flatten
     * the arrowhead code that existed in startContinuousUserPrompt().
     *
     * @param userInput The input given by the user.
     * @return A command object that represents the user input. However, null is returned if the parsing
     *         is unsuccessful.
     */
    public Command parseUserInput(String userInput) {
        Command newCommand = null;

        try {
            newCommand = getParser().parseUserInput(userInput);
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("Please try again.");
            logger.log(Level.WARNING, "User has entered an invalid command.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Uh oh, the command entered is invalid.");
            System.out.println("Please try again.");
            logger.log(Level.WARNING, "User has entered an array index out of bound invalid command.");
        }

        return newCommand;
    }

    /**
     * Populates a set of exercises to exerciseList.
     */
    public void populateExercises() {
        getExerciseList().populateExercisesToList();
    }

    /**
     * Loads the workout file's data that is stored in the user's filesystem into the current
     * session's list of workouts.
     *
     * @throws IOException If the application is unable to open the workout file.
     */
    private void loadWorkoutFile() throws IOException {
        boolean isWorkoutFileLoadSuccessful;
        isWorkoutFileLoadSuccessful = fileManager.loadWorkoutsFromFile(getWorkoutList());
        try {
            getUI().printFileLoadStatusMessage(FileManager.NAME_WORKOUT_FILE, isWorkoutFileLoadSuccessful);
        } catch (UnknownFileException e) {
            System.out.println(e.getMessage());
            logger.log(Level.WARNING, "Unknown file name was encountered.");
        }
        if (!isWorkoutFileLoadSuccessful) {
            fileManager.deleteAndRecreateWorkoutFile();
            fileManager.rewriteAllWorkoutsToFile(getWorkoutList());
            System.out.println("The corrupted workout data has been removed.");
            logger.log(Level.INFO, "Workout file data loaded with corrupted data removed.");
        }
        logger.log(Level.INFO, "Workout file data loaded.");
    }

    private void loadPlanFile() throws IOException {
        boolean isPlanFileLoadSuccessful;
        isPlanFileLoadSuccessful = fileManager.loadPlansFromFile(getPlanList());
        try {
            getUI().printFileLoadStatusMessage(FileManager.NAME_PLAN_FILE, isPlanFileLoadSuccessful);
        } catch (UnknownFileException e) {
            System.out.println(e.getMessage());
            logger.log(Level.WARNING, "Unknown file name was encountered.");
        }

        if (!isPlanFileLoadSuccessful) {
            fileManager.deleteAndRecreatePlanFile();
            fileManager.rewriteAllPlansToFile(getPlanList());
            System.out.println("The corrupted plan data has been removed.");
            logger.log(Level.INFO, "Plan file data loaded with corrupted data removed.");
        }

        logger.log(Level.INFO, "Plan file data loaded.");
    }

    /**
     * Loads the schedule file's data that is stored in the user's filesystem into the current
     * session's list of workouts.
     *
     * @throws IOException If the application is unable to open the workout file.
     */
    private void loadScheduleFile() throws IOException {
        boolean isScheduleFileLoadSuccessful;
        isScheduleFileLoadSuccessful = fileManager.loadScheduleFromFile(getDayList());
        try {
            getUI().printFileLoadStatusMessage(FileManager.NAME_SCHEDULE_FILE, isScheduleFileLoadSuccessful);
        } catch (UnknownFileException e) {
            System.out.println(e.getMessage());
            logger.log(Level.WARNING, "Unknown file name was encountered.");
        }

        if (!isScheduleFileLoadSuccessful) {
            fileManager.deleteAndRecreateScheduleFile();
            fileManager.rewriteAllDaysScheduleToFile(getDayList());
            System.out.println("The corrupted schedule data has been removed.");
            logger.log(Level.INFO, "Schedule file data loaded with corrupted data removed.");
        }

        logger.log(Level.INFO, "Schedule file data loaded.");
    }

    /**
     * Reloads the schedule file to capture the effects in Daylist due to the deletion of workout and plan.
     * @throws IOException  If the application is unable to open the schedule file.
     */
    private void reloadScheduleFile() throws IOException {
        boolean isScheduleFileLoadSuccessful;
        String[] schedule = dayList.getPrintSchedule();
        this.dayList.clearAllSchedule();
        String[] updatedSchedule;
        for (int i = 0; i < schedule.length; i++) {
            if (schedule[i] == null) {
                schedule[i] = "rest day";
            }
        }
        isScheduleFileLoadSuccessful = fileManager.reloadScheduleFromFile(getDayList());
        if (!isScheduleFileLoadSuccessful) {
            fileManager.deleteAndRecreateScheduleFile();
            fileManager.rewriteAllDaysScheduleToFile(getDayList());
            updatedSchedule = dayList.getPrintSchedule();
            for (int i = 0; i < schedule.length; i++) {
                if (!updatedSchedule[i].equals(schedule[i])) {
                    var dayNum = i + 1;
                    System.out.println("Schedule '" + schedule[i] + "' on " + dayList.covertDayNumberToDay(dayNum)
                            + " has been removed.");
                }
            }
            logger.log(Level.INFO, "Schedule file data updated due to deletion on workout/plan.");
        }
    }
}
