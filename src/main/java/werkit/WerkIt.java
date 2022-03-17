package werkit;

import commands.Command;
import commands.ExitCommand;
import commands.InvalidCommandException;
import data.exercises.ExerciseList;
import data.workouts.WorkoutList;
import storage.FileManager;
import storage.LogHandler;
import storage.UnknownFileException;

import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private static Logger logger = Logger.getLogger(WerkIt.class.getName());

    /**
     * Initialises the components of the WerkIt! application, greets the user, and loads the
     * various files stored in the system's local disk into this instance of WerkIt! (if applicable).
     */
    public WerkIt() throws IOException {
        // Initialise Components
        this.ui = new UI();
        this.exerciseList = new ExerciseList();
        this.workoutList = new WorkoutList(getExerciseList());
        this.fileManager = new FileManager();
        this.parser = new Parser(getUI(), getExerciseList(), getWorkoutList(), getFileManager());

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

    /**
     * Gets the FileManager object stored in this WerkIt object.
     *
     * @return The FileManager object.
     */
    public FileManager getFileManager() {
        return this.fileManager;
    }

    /**
     * Checks if the required resource directory and files already exists in the user's filesystem. If not,
     * call the relevant method(s) to create the required directory and/or file(s).
     *
     * @throws IOException If the application is unable to create the required directory and/or file(s).
     */
    private void loadRequiredDirectoryAndFiles() throws IOException {
        getUI().printCheckingDirectoryAndFilesMessage();
        getFileManager().checkAndCreateDirectoriesAndFiles();
        getUI().printEmptyLineOrStatus(fileManager.checkIfAllDirectoryAndFilesExists());

        assert (Files.exists(fileManager.getWorkoutFilePath()));
        assert (Files.exists(fileManager.getExerciseFilePath()));
        assert (Files.exists(fileManager.getWorkoutFilePath()));

        getUI().printLoadingFileDataMessage();
        loadExerciseFile();
        if (getFileManager().isWasWorkoutsFileAlreadyMade()) {
            loadWorkoutFile();
        }
    }

    /**
     * Continuously prompts the user for an input (and thereafter executing the necessary
     * actions) until the exit command is entered.
     * Method adapted from Team Member Alan Low's iP codebase.
     * Link: https://github.com/alanlowzies/ip/blob/8556dd6a5106d190f5ac0458c6d2c34f98737a91/src/main/java/sora/Sora.java
     */
    public void startContinuousUserPrompt() throws IOException {
        boolean userWantsToExit = false;
        boolean isFirstPrompt = true;

        do {
            try {
                getUI().printUserInputPrompt(isFirstPrompt);
                isFirstPrompt = false;
                String userInput = getUI().getUserInput();
                Command newCommand = getParser().parseUserInput(userInput);

                if (newCommand instanceof ExitCommand) {
                    userWantsToExit = true;
                    continue;
                }

                newCommand.execute();

            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
                System.out.println("Please try again.");
                logger.log(Level.WARNING, "User has entered an invalid command.");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Uh oh, the command entered is invalid.");
                System.out.println("Please try again.");
                logger.log(Level.WARNING, "User has entered an array index out of bound invalid command.");
            }
        } while (!userWantsToExit);

        // User is exiting the program
        assert (userWantsToExit);
        getUI().printGoodbye();
    }

    /**
     * Loads the exercise file's data that is stored in the user's filesystem into the current
     * session's list of exercises.
     *
     * @throws IOException If the application is unable to open the exercise file.
     */
    private void loadExerciseFile() throws IOException {
        fileManager.loadExercisesFromFile(getExerciseList());
        try {
            getUI().printFileLoadStatusMessage(FileManager.EXERCISE_FILENAME, true);
        } catch (UnknownFileException e) {
            System.out.println(e.getMessage());
            logger.log(Level.WARNING, "Unknown file name was encountered.");
        }

        logger.log(Level.INFO, "Exercise file data loaded.");
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
            getUI().printFileLoadStatusMessage(FileManager.WORKOUT_FILENAME, isWorkoutFileLoadSuccessful);
        } catch (UnknownFileException e) {
            System.out.println(e.getMessage());
            logger.log(Level.WARNING, "Unknown file name was encountered.");
        }

        logger.log(Level.INFO, "Workout file data loaded.");
    }
}
