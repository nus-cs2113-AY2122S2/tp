package commands;

import data.exercises.InvalidExerciseException;
import data.workouts.InvalidWorkoutException;
import data.workouts.WorkoutOutOfRangeException;
import data.workouts.Workout;
import data.workouts.WorkoutList;
import storage.FileManager;
import werkit.UI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class that will handle the commands relating to workout.
 */
public class WorkoutCommand extends Command {
    public static final String BASE_KEYWORD = "workout";
    public static final String CREATE_ACTION_KEYWORD = "/new";
    public static final String CREATE_ACTION_REPS_KEYWORD = "/reps";
    public static final String LIST_ACTION_KEYWORD = "/list";
    public static final String DELETE_ACTION_KEYWORD = "/delete";
    public static final String UPDATE_ACTION_KEYWORD = "/update";

    private FileManager fileManager;
    private UI ui = new UI();
    private WorkoutList workoutList;

    private String userAction;
    private String userArguments;

    private static Logger logger = Logger.getLogger(WorkoutCommand.class.getName());

    /**
     * Constructs a new instance of the WorkoutCommand. Constructed when the user enters a
     * workout-related command.
     *
     * @param userInput     The user's full original input.
     * @param fileManager   An instance of the FileManager class.
     * @param workoutList   An instance of the WorkoutList class.
     * @param userAction    The action that was parsed from the user's input.
     * @param userArguments The arguments that are accompanied by the user action.
     * @throws InvalidCommandException If the command entered by the user is incorrect.
     */
    public WorkoutCommand(String userInput, FileManager fileManager, WorkoutList workoutList,
            String userAction, String userArguments) throws InvalidCommandException {
        super(userInput);
        this.fileManager = fileManager;
        this.workoutList = workoutList;
        setUserAction(userAction);
        this.userArguments = userArguments;
    }

    /**
     * Gets the instance of the UI class.
     *
     * @return An instance of the UI class.
     */
    public UI getUI() {
        return this.ui;
    }

    /**
     * Gets the instance of the FileManager class.
     *
     * @return An instance of the FileManager class.
     */
    public FileManager getFileManager() {
        return this.fileManager;
    }

    /**
     * Gets the instance of the WorkoutList class.
     *
     * @return An instance of the WorkoutList class.
     */
    public WorkoutList getWorkoutList() {
        return this.workoutList;
    }

    /**
     * Gets the action of the workout command specified by the user.
     *
     * @return A string containing the action specified by the user.
     */
    public String getUserAction() {
        return this.userAction;
    }

    /**
     * Checks for the validity of the action specified by the user and sets the class field
     * userAction to the action (if valid).
     *
     * @param userAction A string containing the action specified by the user.
     * @throws InvalidCommandException If the action specified by the user is invalid.
     */
    public void setUserAction(String userAction) throws InvalidCommandException {
        switch (userAction) {
        case CREATE_ACTION_KEYWORD:
            // Fallthrough
        case LIST_ACTION_KEYWORD:
            // Fallthrough
        case DELETE_ACTION_KEYWORD:
            // Fallthrough
        case UPDATE_ACTION_KEYWORD:
            this.userAction = userAction;
            break;
        default:
            String className = this.getClass().getSimpleName();
            throw new InvalidCommandException(className, InvalidCommandException.INVALID_ACTION_ERROR_MSG);
        }
    }

    /**
     * Gets the user argument stored in the class instance.
     *
     * @return A string containing the user argument.
     */
    public String getUserArguments() {
        return this.userArguments;
    }

    /**
     * Executes a workout-related command based on the action and arguments that is stored in the
     * class fields. If the action and/or arguments specified are invalid, this method will handle the
     * exceptions and print appropriate responses.
     */
    @Override
    public void execute() {
        try {
            switch (getUserAction()) {
            case CREATE_ACTION_KEYWORD:
                Workout newWorkout = getWorkoutList().createAndAddWorkout(getUserArguments());
                getUI().printNewWorkoutCreatedMessage(newWorkout);
                getFileManager().writeNewWorkoutToFile(newWorkout);
                break;
            case LIST_ACTION_KEYWORD:
                getWorkoutList().listWorkout();
                break;
            case DELETE_ACTION_KEYWORD:
                Workout deletedWorkout = getWorkoutList().deleteWorkout(getUserArguments());
                getUI().printDeleteWorkoutMessage(deletedWorkout);
                getFileManager().rewriteAllWorkoutsToFile(getWorkoutList());
                break;
            case UPDATE_ACTION_KEYWORD:
                Workout updatedWorkout = getWorkoutList().updateWorkout(getUserArguments());
                getUI().printUpdateWorkoutMessage(updatedWorkout);
                getFileManager().rewriteAllWorkoutsToFile(getWorkoutList());
                break;
            default:
                String className = this.getClass().getSimpleName();
                logger.log(Level.WARNING, "Invalid action under workout command is entered!");
                throw new InvalidCommandException(className, InvalidCommandException.INVALID_ACTION_ERROR_MSG);
            }
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("Please try again");

        } catch (InvalidExerciseException e) {
            System.out.println(e.getMessage());
            System.out.println("Please try again. Enter 'exercise /list' for a list\nof available exercises.");

        } catch (InvalidWorkoutException e) {
            System.out.println(e.getMessage());
            System.out.println("Please try again.");

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Uh oh, it seems like too few arguments were entered.");
            System.out.println("Please try again. Alternatively, type 'help' if you need\n"
                    + "more information on the commands.");

        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "A non-formattable number was received!");
            System.out.println("Uh oh, a number was expected in your input, but a non-formattable\n"
                    + "number was received.");
            System.out.println("Please try again. Alternatively, type 'help' if you need\n"
                    + "more information on the commands.");

        } catch (WorkoutOutOfRangeException e) {
            System.out.println(e.getMessage());
            System.out.println("Please try again.");

        } catch (IOException e) {
            System.out.println(UI.IOEXCEPTION_ERROR_MESSAGE);
            System.exit(-1);

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            System.out.println("Please try again.");
        }
    }
}
