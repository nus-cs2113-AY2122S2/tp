package storage;

import commands.WorkoutCommand;
import data.exercises.ExerciseList;
import data.exercises.InvalidExerciseException;
import data.workouts.InvalidWorkoutException;
import data.workouts.Workout;
import data.workouts.WorkoutList;
import werkit.UI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    private boolean wasDirectoryAlreadyMade = false;
    private boolean wasExercisesFileAlreadyMade = false;
    private boolean wasWorkoutsFileAlreadyMade = false;

    public FileManager() throws IOException {
        String workingDirectory = System.getProperty(USER_WORKING_DIRECTORY_PROPERTY);
        this.directoryPath = Paths.get(workingDirectory, DATA_DIRECTORY_NAME);
        this.exerciseFilePath = Paths.get(workingDirectory, DATA_DIRECTORY_NAME, EXERCISE_FILENAME);
        this.workoutFilePath = Paths.get(workingDirectory, DATA_DIRECTORY_NAME, WORKOUT_FILENAME);
    }

    public Path getDirectoryPath() {
        return this.directoryPath;
    }

    public Path getExerciseFilePath() {
        return this.exerciseFilePath;
    }

    public Path getWorkoutFilePath() {
        return this.workoutFilePath;
    }

    public boolean isWasDirectoryAlreadyMade() {
        return this.wasDirectoryAlreadyMade;
    }

    public void setWasDirectoryAlreadyMade(boolean wasDirectoryAlreadyMade) {
        this.wasDirectoryAlreadyMade = wasDirectoryAlreadyMade;
    }

    public boolean isWasExercisesFileAlreadyMade() {
        return this.wasExercisesFileAlreadyMade;
    }

    public void setWasExercisesFileAlreadyMade(boolean wasExercisesFileAlreadyMade) {
        this.wasExercisesFileAlreadyMade = wasExercisesFileAlreadyMade;
    }

    public boolean isWasWorkoutsFileAlreadyMade() {
        return this.wasWorkoutsFileAlreadyMade;
    }

    public void setWasWorkoutsFileAlreadyMade(boolean wasWorkoutsFileAlreadyMade) {
        this.wasWorkoutsFileAlreadyMade = wasWorkoutsFileAlreadyMade;
    }

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
    }

    public void checkIfDataDirectoryAlreadyExists() {
        if (Files.exists(getDirectoryPath())) {
            setWasDirectoryAlreadyMade(true);
        }
    }

    public void createDataDirectory() throws IOException {
        Files.createDirectory(getDirectoryPath());
    }

    public void checkIfExerciseFileAlreadyExists() {
        if (Files.exists(getExerciseFilePath())) {
            setWasExercisesFileAlreadyMade(true);
        }
    }

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

    public void checkIfWorkoutFileAlreadyExists() {
        if (Files.exists(getWorkoutFilePath())) {
            setWasWorkoutsFileAlreadyMade(true);
        }
    }

    public void createWorkoutFile() throws IOException {
        Files.createFile(getWorkoutFilePath());
    }

    /**
     * Reads the exercises from local file and store them into an ArrayList named exercises.
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

    public String[] parseFileDataLine(String fileDataLine) {
        String[] parsedFileDataLine = fileDataLine.split(FILE_DATA_DELIMITER_REGEX);
        return parsedFileDataLine;
    }

    public void addFileWorkoutToList(WorkoutList workoutList, String[] workoutFileDataLine)
            throws ArrayIndexOutOfBoundsException, InvalidExerciseException, InvalidWorkoutException {
        String workoutName = workoutFileDataLine[0];
        String workoutReps = workoutFileDataLine[1];

        String userArguments = workoutName + " " + WorkoutCommand.CREATE_ACTION_REPS_KEYWORD + " " + workoutReps;
        workoutList.createAndAddWorkout(userArguments);
    }

    public void writeNewWorkoutToFile(Workout newWorkout) throws IOException {
        String workoutInFileFormat = convertWorkoutToFileDataFormat(newWorkout);

        FileWriter fileWriter = new FileWriter(getWorkoutFilePath().toString(), true);
        fileWriter.append(workoutInFileFormat);
        fileWriter.append(System.lineSeparator());
        fileWriter.close();
    }

    public String convertWorkoutToFileDataFormat(Workout workout) {
        StringBuilder workoutInFileFormat = new StringBuilder();
        workoutInFileFormat.append(workout.getExerciseName());
        workoutInFileFormat.append(FILE_DATA_DELIMITER);
        workoutInFileFormat.append(workout.getRepetitions());

        return workoutInFileFormat.toString();
    }

    public void rewriteAllWorkoutsToFile(WorkoutList workoutList) throws IOException {
        ArrayList<Workout> listOfWorkouts = workoutList.getWorkoutsList();

        FileWriter fileWriter = new FileWriter(getWorkoutFilePath().toString());
        for (Workout workout : listOfWorkouts) {
            String workoutInFileFormat = convertWorkoutToFileDataFormat(workout);
            fileWriter.append(workoutInFileFormat);
            fileWriter.append(System.lineSeparator());
        }
        fileWriter.close();
    }
}
