package storage;

import commands.WorkoutCommand;
import data.exercises.ExerciseList;
import data.exercises.InvalidExerciseException;
import data.workouts.InvalidWorkoutException;
import data.workouts.Workout;
import data.workouts.WorkoutList;
import werkit.UI;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

/**
 * This class manages the reading and writing of the various files used by the WerkIt! application.
 */
public class FileManager {
    /**
     * These characters are used for internal parsing (e.g. reading from data files)
     * and thus cannot be used by the user in his/her input.
     */
    public static final String[] ILLEGAL_CHARACTERS = {"|"};

    // Directory and filenames for data storage
    public static final String USER_WORKING_DIRECTORY_PROPERTY = "user.dir";
    public static final String DATA_DIRECTORY_NAME = "werkItResources";
    public static final String EXERCISE_FILENAME = "exercises.txt";
    public static final String WORKOUT_FILENAME = "workouts.txt";

    // Delimiters for processing file data
    private static final String FILE_DATA_DELIMITER_REGEX = "\\|";
    private static final String FILE_DATA_DELIMITER = " | ";

    private UI ui = new UI();
    private Path directoryPath;
    private Path exerciseFilePath;
    private Path workoutFilePath;

    // These booleans indicate whether the directory and/or files already exist
    // prior to the current application's session.
    private boolean wasDirectoryAlreadyMade = false;
    private boolean wasExercisesFileAlreadyMade = false;
    private boolean wasWorkoutsFileAlreadyMade = false;

    private static Logger logger = Logger.getLogger(FileManager.class.getName());

    /**
     * Constructs a FileManager object. While instantiating, Paths objects of the various URIs
     * are also instantiated.
     */
    public FileManager() {
        String workingDirectory = System.getProperty(USER_WORKING_DIRECTORY_PROPERTY);
        this.directoryPath = Paths.get(workingDirectory, DATA_DIRECTORY_NAME);
        this.exerciseFilePath = Paths.get(workingDirectory, DATA_DIRECTORY_NAME, EXERCISE_FILENAME);
        this.workoutFilePath = Paths.get(workingDirectory, DATA_DIRECTORY_NAME, WORKOUT_FILENAME);
    }

    /**
     * Gets the Paths object that stores the URI of the WerkIt! resource directory.
     *
     * @return A Paths object with the URI of the application's resource directory.
     */
    public Path getDirectoryPath() {
        return this.directoryPath;
    }

    /**
     * Gets the Paths object that stores the URI of the WerkIt! data file containing
     * a list of exercises.
     *
     * @return A Paths object with the URI of the application's exercise file.
     */
    public Path getExerciseFilePath() {
        return this.exerciseFilePath;
    }

    /**
     * Gets the Paths object that stores the URI of the WerkIt! data file containing
     * a list of workouts.
     *
     * @return A Paths object with the URI of the application's workouts file.
     */
    public Path getWorkoutFilePath() {
        return this.workoutFilePath;
    }

    /**
     * Gets the status of whether the directory already exists prior to the current session
     * of the application.
     *
     * @return Returns true if the directory already exists. Otherwise, returns false.
     */
    public boolean isWasDirectoryAlreadyMade() {
        return this.wasDirectoryAlreadyMade;
    }

    /**
     * Sets the status of whether the directory already exists prior to the current session
     * of the application.
     *
     * @param wasDirectoryAlreadyMade The status of the directory's existence.
     */
    public void setWasDirectoryAlreadyMade(boolean wasDirectoryAlreadyMade) {
        this.wasDirectoryAlreadyMade = wasDirectoryAlreadyMade;
    }

    /**
     * Gets the status of whether the exercise file already exists prior to the current session
     * of the application.
     *
     * @return Returns true if the exercise file already exists. Otherwise, returns false.
     */
    public boolean isWasExercisesFileAlreadyMade() {
        return this.wasExercisesFileAlreadyMade;
    }

    /**
     * Sets the status of whether the exercise file already exists prior to the current session
     * of the application.
     *
     * @param wasExercisesFileAlreadyMade The status of the exercise file's existence.
     */
    public void setWasExercisesFileAlreadyMade(boolean wasExercisesFileAlreadyMade) {
        this.wasExercisesFileAlreadyMade = wasExercisesFileAlreadyMade;
    }

    /**
     * Gets the status of whether the workout file already exists prior to the current session
     * of the application.
     *
     * @return Returns true if the workout file already exists. Otherwise, returns false.
     */
    public boolean isWasWorkoutsFileAlreadyMade() {
        return this.wasWorkoutsFileAlreadyMade;
    }

    /**
     * Sets the status of whether the workout file already exists prior to the current session
     * of the application.
     *
     * @param wasWorkoutsFileAlreadyMade The status of the workout file's existence.
     */
    public void setWasWorkoutsFileAlreadyMade(boolean wasWorkoutsFileAlreadyMade) {
        this.wasWorkoutsFileAlreadyMade = wasWorkoutsFileAlreadyMade;
    }

    /**
     * Checks if all the required directory and files already exist prior to the current session
     * of the application.
     *
     * @return Returns true if all the required directory and files already exists. Otherwise,
     *         returns false.
     */
    public boolean checkIfAllDirectoryAndFilesExists() {
        if (!isWasDirectoryAlreadyMade()) {
            return false;
        }
        if (!isWasExercisesFileAlreadyMade()) {
            return false;
        }
        if (!isWasWorkoutsFileAlreadyMade()) {
            return false;
        }

        return true;
    }

    /**
     * Checks if the required directory and files already exist prior to the current session
     * of the application. If any of them does not exist, create them.
     *
     * @throws IOException If the application is unable to create the required directory
     *         and/or files.
     */
    public void checkAndCreateDirectoriesAndFiles() throws IOException {
        checkIfDataDirectoryAlreadyExists();
        if (!isWasDirectoryAlreadyMade()) {
            ui.printDirectoryNotFoundMessage();
            createDataDirectory();
            ui.printDirectoryCreatedMessage();
        }

        checkIfExerciseFileAlreadyExists();
        if (!isWasExercisesFileAlreadyMade()) {
            ui.printExerciseFileNotFoundMessage();
            createExerciseFile();
            ui.printExerciseFileCreatedMessage();
        }

        checkIfWorkoutFileAlreadyExists();
        if (!isWasWorkoutsFileAlreadyMade()) {
            ui.printWorkoutFileNotFoundMessage();
            createWorkoutFile();
            ui.printWorkoutFileCreatedMessage();
        }

        assert (Files.exists(getDirectoryPath()));
        assert (Files.exists(getExerciseFilePath()));
        assert (Files.exists(getWorkoutFilePath()));
    }

    /**
     * Checks for the existence of the resource directory. If the directory exists,
     * update the status of wasDirectoryAlreadyMade.
     */
    public void checkIfDataDirectoryAlreadyExists() {
        if (Files.exists(getDirectoryPath())) {
            logger.log(Level.INFO, "Resource directory already exists.");
            setWasDirectoryAlreadyMade(true);
        }
    }

    /**
     * Creates the resource directory of the application.
     *
     * @throws IOException If the application is unable to create the resource directory.
     */
    public void createDataDirectory() throws IOException {
        Files.createDirectory(getDirectoryPath());
    }

    /**
     * Checks for the existence of the exercise file. If the file exists,
     * update the status of wasExercisesFileAlreadyMade.
     */
    public void checkIfExerciseFileAlreadyExists() {
        if (Files.exists(getExerciseFilePath())) {
            logger.log(Level.INFO, "Exercise file already exists.");
            setWasExercisesFileAlreadyMade(true);
        }
    }

    /**
     * Creates the exercise file of the application.
     *
     * @throws IOException If the application is unable to create the exercise file.
     */
    public void createExerciseFile() throws IOException {
        Files.createFile(getExerciseFilePath());

        // Populate file with default exercises
        FileWriter fileWriter = new FileWriter(getExerciseFilePath().toString());
        for (String exerciseName : ExerciseList.getDefaultExerciseList()) {
            fileWriter.append(exerciseName);
            fileWriter.append(System.lineSeparator());
        }
        fileWriter.close();
    }

    /**
     * Checks for the existence of the workout file. If the file exists,
     * update the status of wasWorkoutsFileAlreadyMade.
     */
    public void checkIfWorkoutFileAlreadyExists() {
        if (Files.exists(getWorkoutFilePath())) {
            logger.log(Level.INFO, "Workout file already exists.");
            setWasWorkoutsFileAlreadyMade(true);
        }
    }

    /**
     * Creates the workout file of the application.
     *
     * @throws IOException If the application is unable to create the workout file.
     */
    public void createWorkoutFile() throws IOException {
        Files.createFile(getWorkoutFilePath());
    }

    /**
     * Reads the exercises from local exercise file and stores them into an ArrayList named exercises.
     *
     * @throws IOException when the local file is missing.
     */
    public void loadExercisesFromFile(ExerciseList exerciseList) throws IOException {
        Scanner scanner = new Scanner(getExerciseFilePath());
        while (scanner.hasNextLine()) {
            var exercise = scanner.nextLine();
            exerciseList.addExerciseToList(exercise);
        }
    }

    /**
     * Reads the workouts from the local workout file and stores them into an ArrayList in a
     * WorkoutList object.
     *
     * @param workoutList An instance of the WorkoutList class.
     * @return Returns true if all workouts have been loaded into the application successfully.
     *         Otherwise, returns false.
     * @throws IOException If the method is unable to open the workout file.
     */
    public boolean loadWorkoutsFromFile(WorkoutList workoutList) throws IOException {
        boolean hasNoErrorsDuringLoad = true;
        Scanner workoutFileReader = new Scanner(getWorkoutFilePath());
        while (workoutFileReader.hasNext()) {
            try {
                String workoutFileDataLine = workoutFileReader.nextLine();
                String[] parsedWorkoutFileDataLine = parseFileDataLine(workoutFileDataLine);
                addFileWorkoutToList(workoutList, parsedWorkoutFileDataLine);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("File data error: insufficient parameters in workout data.");
                hasNoErrorsDuringLoad = false;
            } catch (InvalidExerciseException | InvalidWorkoutException e) {
                System.out.println("File data error:" + e.getMessage());
                hasNoErrorsDuringLoad = false;
            }
        }

        return hasNoErrorsDuringLoad;
    }

    /**
     * Where applicable, parses a line of data read from a resource file by splitting the data according
     * to the delimiters.
     *
     * @param fileDataLine The line of data that is read from a resource file.
     * @return An array containing the parsed line of data split by the delimiter.
     */
    public String[] parseFileDataLine(String fileDataLine) {
        String[] parsedFileDataLine = fileDataLine.split(FILE_DATA_DELIMITER_REGEX);
        return parsedFileDataLine;
    }

    /**
     * Adds a parsed workout data that is read from the resource file into the current application
     * session's list of workouts.
     *
     * @param workoutList         An instance of the WorkoutList class.
     * @param workoutFileDataLine An array of the parsed workout data read from the resource file.
     * @throws ArrayIndexOutOfBoundsException If the parsed data contains insufficient information.
     * @throws InvalidExerciseException       If the parsed data contains an invalid exercise name.
     * @throws InvalidWorkoutException        If the parsed data contains invalid or insufficient information needed
     *                                        to create a Workout object.
     */
    public void addFileWorkoutToList(WorkoutList workoutList, String[] workoutFileDataLine)
            throws ArrayIndexOutOfBoundsException, InvalidExerciseException, InvalidWorkoutException {
        String workoutName = workoutFileDataLine[0];
        String workoutReps = workoutFileDataLine[1];

        String userArguments = workoutName + " " + WorkoutCommand.CREATE_ACTION_REPS_KEYWORD + " " + workoutReps;
        workoutList.createAndAddWorkout(userArguments);
    }

    /**
     * Writes a newly-created Workout object into the workout resource file with the correct data format.
     *
     * @param newWorkout The newly-created Workout object to be added into the resource file.
     * @throws IOException          If the workout resource file cannot be opened.
     * @throws NullPointerException If the Workout parameter is null.
     */
    public void writeNewWorkoutToFile(Workout newWorkout) throws IOException, NullPointerException {
        String workoutInFileFormat = convertWorkoutToFileDataFormat(newWorkout);

        FileWriter fileWriter = new FileWriter(getWorkoutFilePath().toString(), true);
        fileWriter.append(workoutInFileFormat);
        fileWriter.append(System.lineSeparator());
        fileWriter.close();
    }

    /**
     * Converts the data stored in the Workout object into a string that will be written to the
     * workout resource file.
     *
     * @param workout The Workout object whose data will be written to the resource file.
     * @return The string that will be written to the resource file.
     * @throws NullPointerException If the Workout parameter is null.
     */
    public String convertWorkoutToFileDataFormat(Workout workout) throws NullPointerException {
        if (workout == null) {
            throw new NullPointerException("Workout object inputted into convertWorkoutToFileDataFormat() is null.");
        }

        StringBuilder workoutInFileFormat = new StringBuilder();
        workoutInFileFormat.append(workout.getExerciseName());
        workoutInFileFormat.append(FILE_DATA_DELIMITER);
        workoutInFileFormat.append(workout.getRepetitions());

        return workoutInFileFormat.toString();
    }

    /**
     * Rewrites the entire list of workouts stored in the WorkoutList object into the workout resource file.
     *
     * @param workoutList An instance of the WorkoutList class.
     * @throws IOException          If the application is unable to open the workout resource file.
     * @throws NullPointerException If a null object is given as a parameter for any of the methods called
     *                              by this method.
     */
    public void rewriteAllWorkoutsToFile(WorkoutList workoutList) throws IOException, NullPointerException {
        ArrayList<String> listOfWorkouts = workoutList.getWorkoutsDisplayList();

        FileWriter fileWriter = new FileWriter(getWorkoutFilePath().toString());
        for (String workoutKey : listOfWorkouts) {
            Workout workoutObject = workoutList.getWorkoutFromKey(workoutKey);
            assert (workoutObject != null) : "Workout object is NULL";
            String workoutInFileFormat = convertWorkoutToFileDataFormat(workoutObject);
            fileWriter.append(workoutInFileFormat);
            fileWriter.append(System.lineSeparator());
        }
        fileWriter.close();
    }
}
