package storage;

import commands.WorkoutCommand;
import data.exercises.ExerciseList;
import data.exercises.InvalidExerciseException;
import data.plans.Plan;
import data.plans.PlanList;
import data.schedule.Day;
import data.schedule.DayList;
import data.schedule.InvalidScheduleException;
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
    public static final String PLAN_FILENAME = "plans.txt";
    public static final String SCHEDULE_FILENAME = "schedule.txt";

    // Delimiters for processing file data
    private static final String FILE_DATA_DELIMITER_REGEX = "\\|";
    private static final String FILE_DATA_DELIMITER = " | ";
    private static final String FILE_DATA_DELIMITER_PLAN = ",";

    private UI ui = new UI();
    private Path directoryPath;
    private Path exerciseFilePath;
    private Path workoutFilePath;
    private Path planFilePath;
    private Path scheduleFilePath;

    // These booleans indicate whether the directory and/or files already exist
    // prior to the current application's session.
    private boolean wasDirectoryAlreadyMade = false;
    private boolean wasExercisesFileAlreadyMade = false;
    private boolean wasWorkoutsFileAlreadyMade = false;
    private boolean wasPlansFileAlreadyMade = false;
    private boolean wasScheduleFileAlreadyMade = false;

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
        this.planFilePath = Paths.get(workingDirectory, DATA_DIRECTORY_NAME, PLAN_FILENAME);
        this.scheduleFilePath = Paths.get(workingDirectory, DATA_DIRECTORY_NAME, SCHEDULE_FILENAME);

        LogHandler.linkToFileLogger(logger);
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

    public Path getPlanFilePath() {
        return this.planFilePath;
    }

    /**
     * Gets the Paths object that stores the URI of the WerkIt! data file containing
     * the schedule.
     *
     * @return A Paths object with the URI of the application's schedule file.
     */
    public Path getScheduleFilePath() {
        return this.scheduleFilePath;
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

    public boolean isWasPlansFileAlreadyMade() {
        return this.wasPlansFileAlreadyMade;
    }

    public void setWasPlansFileAlreadyMade(boolean wasPlansFileAlreadyMade) {
        this.wasPlansFileAlreadyMade = wasPlansFileAlreadyMade;
    }

    /**
     * Gets the status of whether the schedule file already exists prior to the current session
     * of the application.
     *
     * @return Returns true if the schedule file already exists. Otherwise, returns false.
     */
    public boolean isWasScheduleFileAlreadyMade() {
        return this.wasScheduleFileAlreadyMade;
    }

    /**
     * Sets the status of whether the schedule file already exists prior to the current session
     * of the application.
     *
     * @param wasScheduleFileAlreadyMade The status of the schedule file's existence.
     */
    public void setWasScheduleFileAlreadyMade(boolean wasScheduleFileAlreadyMade) {
        this.wasScheduleFileAlreadyMade = wasScheduleFileAlreadyMade;
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
        if (!isWasPlansFileAlreadyMade()) {
            return false;
        }
        if (!isWasScheduleFileAlreadyMade()) {
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

        checkIfPlanFileAlreadyExists();
        if (!isWasPlansFileAlreadyMade()) {
            ui.printPlanFileNotFoundMessage();
            createPlanFile();
            ui.printPlanFileCreatedMessage();
        }

        checkIfScheduleFileAlreadyExists();
        if (!isWasScheduleFileAlreadyMade()) {
            ui.printScheduleFileNotFoundMessage();
            createScheduleFile();
            ui.printScheduleFileCreatedMessage();
        }

        assert (Files.exists(getDirectoryPath())) : "Directory does not exist, but it should.";
        assert (Files.exists(getExerciseFilePath())) : "Exercise file does not exist, but it should.";
        assert (Files.exists(getWorkoutFilePath())) : "Workout file does not exist, but it should.";
        // TODO: assert for plans (Haofeng?)
        assert (Files.exists(getScheduleFilePath())) : "Schedule file does not exist, but it should.";
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
        logger.log(Level.INFO, "A new data directory was created.");
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
        logger.log(Level.INFO, "A new exercise file was created.");

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
        logger.log(Level.INFO, "A new workout file was created.");
    }

    public void checkIfPlanFileAlreadyExists() {
        if (Files.exists(getPlanFilePath())) {
            logger.log(Level.INFO, "Plan file already exists.");
            setWasPlansFileAlreadyMade(true);
        }
    }

    public void createPlanFile() throws IOException {
        Files.createFile(getPlanFilePath());
        logger.log(Level.INFO, "A new plan file was created.");
    }

    public void checkIfScheduleFileAlreadyExists() {
        if (Files.exists(getScheduleFilePath())) {
            logger.log(Level.INFO, "Schedule file already exists.");
            setWasScheduleFileAlreadyMade(true);
        }
    }

    public void createScheduleFile() throws IOException {
        Files.createFile(getScheduleFilePath());
        logger.log(Level.INFO, "A new schedule file was created.");
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
     * Reads the workouts from the local workout file and stores them into a WorkoutList object.
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
                System.out.println("File data error: " + e.getMessage());
                hasNoErrorsDuringLoad = false;
            }
        }

        return hasNoErrorsDuringLoad;
    }

    public boolean loadPlansFromFile(PlanList planList) throws IOException {
        boolean hasNoErrorsDuringLoad = true;
        Scanner planFileReader = new Scanner(getPlanFilePath());
        while (planFileReader.hasNext()) {
            try {
                String planFileDataLine = planFileReader.nextLine();
                String[] parsedPlanFileDataLine = parsePlansFileData(planFileDataLine);
                addFilePlanToList(planList, parsedPlanFileDataLine);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("File data error: insufficient parameters in plan data.");
                hasNoErrorsDuringLoad = false;
            } catch (InvalidExerciseException | InvalidWorkoutException e) {
                System.out.println("File data error: " + e.getMessage());
                hasNoErrorsDuringLoad = false;
            }
        }

        return hasNoErrorsDuringLoad;
    }

    /**
     * Reads the days and corresponding plans from the local schedule file and stores the
     * parsed data into a DayList object.
     *
     * @param dayList The DayList object to store the day schedule in.
     * @return Returns true if the entire schedule is loaded into the application successfully.
     *         Otherwise, returns false.
     * @throws IOException If the method is unable to open the schedule file.
     */
    public boolean loadScheduleFromFile(DayList dayList) throws IOException {
        boolean hasNoErrorsDuringLoad = true;
        Scanner scheduleFileReader = new Scanner(getScheduleFilePath());
        while (scheduleFileReader.hasNext()) {
            try {
                String scheduleFileDataLine = scheduleFileReader.nextLine();
                String[] parsedScheduleFileDataLine = parseFileDataLine(scheduleFileDataLine);
                addFileScheduleToList(dayList, parsedScheduleFileDataLine);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("File data error: insufficient parameters in plan data.");
                hasNoErrorsDuringLoad = false;
            } catch (InvalidScheduleException e) {
                System.out.println("File data error: " + e.getMessage());
                hasNoErrorsDuringLoad = false;
            }
        }

        return hasNoErrorsDuringLoad;
    }

    public String[] parsePlansFileData(String fileDataLine) {
        String[] parsedPlansData = fileDataLine.split(FILE_DATA_DELIMITER_REGEX, 2);
        return parsedPlansData;
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
     * Adds a parsed workout data that is read from the resource file 'workouts.txt' into
     * the current application session's list of workouts.
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

    public void addFilePlanToList(PlanList planList, String[] planFileDataLine)
            throws ArrayIndexOutOfBoundsException, InvalidExerciseException, InvalidWorkoutException {
        String planName = planFileDataLine[0].trim();
        String workoutsInPlan = planFileDataLine[1].trim();
        String[] individualWorkout = workoutsInPlan.split(FILE_DATA_DELIMITER_PLAN, -1);
        ArrayList<Workout> workoutsToAddInPlanList = new ArrayList<Workout>();
        for (int i = 0; i < individualWorkout.length; i++) {
            String[] workoutDetail = individualWorkout[i].split(FILE_DATA_DELIMITER_REGEX);
            workoutsToAddInPlanList.add(new Workout(workoutDetail[0].trim(),
                    Integer.parseInt(workoutDetail[1].trim())));
        }
        Plan planToBeAdded = new Plan(planName, workoutsToAddInPlanList);
        planList.insertPlanIntoList(planName, planToBeAdded);
    }

    /**
     * Adds a parsed day schedule data that is read from the resource file 'schedule.txt' into
     * the current application session's schedule of the days.
     *
     * @param dayList              An instance of the DayList class.
     * @param scheduleFileDataLine An array of the parsed day schedule data read from the resource file.
     * @throws ArrayIndexOutOfBoundsException If the parsed data contains insufficient information.
     * @throws InvalidScheduleException       If the parsed data contains invalid day schedule data or format.
     */
    public void addFileScheduleToList(DayList dayList, String[] scheduleFileDataLine)
            throws ArrayIndexOutOfBoundsException, InvalidScheduleException {
        String dayNumber = scheduleFileDataLine[0];
        String planName = scheduleFileDataLine[1];

        String userArguments = dayNumber + " " + planName;
        dayList.updateDay(userArguments);
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

    public void writeNewPlanToFile(Plan newPlan) throws IOException, NullPointerException {
        String planInFileFormat = convertPlanToFileDataFormat(newPlan);

        FileWriter fileWriter = new FileWriter(getPlanFilePath().toString(), true);
        fileWriter.append(planInFileFormat);
        fileWriter.append(System.lineSeparator());
        fileWriter.close();
    }

    public String convertPlanToFileDataFormat(Plan plan) throws NullPointerException {
        if (plan == null) {
            throw new NullPointerException("Plan object inputted into convertPlanToFileDataFormat() is null.");
        }

        StringBuilder planInFileFormat = new StringBuilder();
        planInFileFormat.append(plan.getPlanName());
        planInFileFormat.append(FILE_DATA_DELIMITER);
        var workoutInPlan = plan.getWorkoutsInPlanList();
        for (int i = 0; i < workoutInPlan.size(); i++) {
            planInFileFormat.append(convertWorkoutToFileDataFormat(workoutInPlan.get(i)));
            if (i != workoutInPlan.size() - 1) {
                planInFileFormat.append(FILE_DATA_DELIMITER_PLAN);
            }
        }

        return planInFileFormat.toString();
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

    /**
     * Rewrites the entire list of Days and their corresponding plans stored in the DayList object into
     * the schedule resource file.
     *
     * @param dayList An instance of the DayList class.
     * @throws IOException If the application is unable to open the schedule resource file.
     */
    public void rewriteAllDaysScheduleToFile(DayList dayList) throws IOException {
        Day[] listOfDaysPlans = dayList.getDayList();

        FileWriter fileWriter = new FileWriter(getScheduleFilePath().toString());
        for (int i = 0; i < listOfDaysPlans.length; i += 1) {
            if (listOfDaysPlans[i] == null) {
                // Day has no plan, nothing to write to the file. Proceed to the next Day.
                continue;
            }
            assert (listOfDaysPlans[i] != null) : "Element referenced is null, but it should not be.";
            String dayScheduleInFileFormat = convertDayScheduleToFileDataFormat(listOfDaysPlans[i]);
            fileWriter.append(dayScheduleInFileFormat);
            fileWriter.append(System.lineSeparator());
        }
        fileWriter.close();
    }

    /**
     * Converts the data stored in the Day object into a string that will be written to the
     * schedule resource file.
     *
     * @param day The Day object whose data will be written to the schedule resource file.
     * @return A string representing the Day object data.
     */
    public String convertDayScheduleToFileDataFormat(Day day) {
        assert (day != null) : "Day object is null, but it should not be.";

        StringBuilder dayScheduleInFileFormat = new StringBuilder();
        dayScheduleInFileFormat.append(day.getDayNumber());
        dayScheduleInFileFormat.append(FILE_DATA_DELIMITER);
        dayScheduleInFileFormat.append(day.getPlanForThisDay());

        return dayScheduleInFileFormat.toString();
    }
}
