package werkit;

import commands.*;
import data.exercises.ExerciseList;
import data.workouts.WorkoutList;
import storage.FileManager;
import storage.LogHandler;

import java.util.logging.Logger;
import java.util.logging.Level;

import static commands.WorkoutCommand.CREATE_ACTION_KEYWORD;
import static commands.WorkoutCommand.LIST_ACTION_KEYWORD;
import static commands.WorkoutCommand.DELETE_ACTION_KEYWORD;
import static commands.WorkoutCommand.UPDATE_ACTION_KEYWORD;
import static commands.SearchCommand.SEARCH_EXERCISE_ACTION_KEYWORD;
import static commands.SearchCommand.SEARCH_WORKOUT_ACTION_KEYWORD;
import static commands.SearchCommand.SEARCH_PLAN_ACTION_KEYWORD;



/**
 * This class will parse the input that the user enters into the WerkIt! application into data
 * that can be further processed by other classes in this application.
 * Design of the commands is inspired by the AddressBook-Level2 project
 * Link: https://se-education.org/addressbook-level2/
 */
public class Parser {
    private UI ui;
    private ExerciseList exerciseList;
    private WorkoutList workoutList;
    private FileManager fileManager;
    public static final int EXPECTED_NUMBER_OF_PARAMETERS_NO_ARGUMENTS = 2;
    public static final int EXPECTED_NUMBER_OF_PARAMETERS_WITH_ARGUMENTS = 3;
    private static Logger logger = Logger.getLogger(Parser.class.getName());

    /**
     * Constructs the Parser object with the required components for this instance to operate.
     *
     * @param ui           An instance of the UI class.
     * @param exerciseList An instance of the ExerciseList class.
     * @param workoutList  An instance of the WorkoutList class.
     * @param fileManager  An instance of the FileManager class.
     */
    public Parser(UI ui, ExerciseList exerciseList, WorkoutList workoutList, FileManager fileManager) {
        this.ui = ui;
        this.exerciseList = exerciseList;
        this.workoutList = workoutList;
        this.fileManager = fileManager;

        LogHandler.linkToFileLogger(logger);
    }

    /**
     * Gets the UI object stored in this Parser object.
     *
     * @return The UI object.
     */
    public UI getUi() {
        return ui;
    }

    /**
     * Gets the ExerciseList object stored in this Parser object.
     *
     * @return The ExerciseList object.
     */
    public ExerciseList getExerciseList() {
        return exerciseList;
    }

    /**
     * Gets the WorkoutList object stored in this Parser object.
     *
     * @return The WorkoutList object.
     */
    public WorkoutList getWorkoutList() {
        return workoutList;
    }

    /**
     * Parses the user's input and determines the correct command object to instantiate.
     *
     * @param userInput The unparsed input given by the user.
     * @return A subclass of the Command object. The type of Command object is determined based on the
     *         user's input.
     * @throws ArrayIndexOutOfBoundsException If the user's input contains insufficient information to parse.
     * @throws InvalidCommandException        If the user's input contains invalid or insufficient information
     *                                        to parse.
     */
    public Command parseUserInput(String userInput) throws ArrayIndexOutOfBoundsException,
            InvalidCommandException {
        // Check for illegal characters
        boolean hasIllegalCharacters = checkInputForIllegalCharacters(userInput);
        String className = this.getClass().getSimpleName();
        if (hasIllegalCharacters) {
            logger.log(Level.WARNING, "Illegal character used by user.");
            throw new InvalidCommandException(className, InvalidCommandException.ILLEGAL_CHARACTER_USED_ERROR_MSG);
        }

        // Determine the type of Command subclass to instantiate
        String commandKeyword = userInput.split(" ", 2)[0];

        switch (commandKeyword) {
        case WorkoutCommand.BASE_KEYWORD:
            return createWorkoutCommand(userInput);
        case ExitCommand.BASE_KEYWORD:
            return createExitCommand(userInput);
        case HelpCommand.BASE_KEYWORD:
            return createHelpCommand(userInput);
        case ExerciseCommand.BASE_KEYWORD:
            return createExerciseCommand(userInput);
        case SearchCommand.BASE_KEYWORD:
            return createSearchCommand(userInput);
        default:
            logger.log(Level.WARNING, "Unknown command entered by user.");
            throw new InvalidCommandException(className, InvalidCommandException.INVALID_COMMAND_ERROR_MSG);
        }
    }

    /**
     * Checks if the user's input contains illegal characters. Illegal characters are characters that are
     * required by the application for purposes such as reading/writing to resource files. Thus, these
     * characters cannot be used by the user.
     *
     * @param userInput The user's input.
     * @return Returns true if the user's input contains at least one illegal character. Otherwise,
     *         returns false.
     */
    private boolean checkInputForIllegalCharacters(String userInput) {
        for (String illegalCharacter : FileManager.ILLEGAL_CHARACTERS) {
            if (userInput.contains(illegalCharacter)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a new workout command with the appropriate parameters stored into the object.
     *
     * @param userInput The user's input.
     * @return A WorkoutCommand object containing the parsed parameters obtained from the user's input.
     * @throws ArrayIndexOutOfBoundsException If the user's input contains insufficient information to parse.
     * @throws InvalidCommandException        If the user's input contains invalid or insufficient information
     *                                        to parse.
     */
    public WorkoutCommand createWorkoutCommand(String userInput) throws ArrayIndexOutOfBoundsException,
            InvalidCommandException {
        // Determine the action the user has entered
        String actionKeyword = userInput.split(" ", 3)[1];
        String arguments = null;
        String className = this.getClass().getSimpleName();
        switch (actionKeyword) {
        case CREATE_ACTION_KEYWORD:
            if (userInput.split(" ", 3).length < EXPECTED_NUMBER_OF_PARAMETERS_WITH_ARGUMENTS) {
                logger.log(Level.WARNING, "User has entered an invalid create workout command action.");
                throw new InvalidCommandException(className,
                        InvalidCommandException.INVALID_NEW_WORKOUT_COMMAND_ERROR_MSG);
            }
            arguments = userInput.split(" ", 3)[2];
            break;
        case DELETE_ACTION_KEYWORD:
            if (userInput.split(" ", 3).length < EXPECTED_NUMBER_OF_PARAMETERS_WITH_ARGUMENTS) {
                logger.log(Level.WARNING, "User has entered an invalid delete workout command action.");
                throw new InvalidCommandException(className,
                        InvalidCommandException.INVALID_DELETE_WORKOUT_COMMAND_ERROR_MSG);
            }
            arguments = userInput.split(" ", 3)[2];
            break;
        case UPDATE_ACTION_KEYWORD:
            if (userInput.split(" ", 3).length < EXPECTED_NUMBER_OF_PARAMETERS_WITH_ARGUMENTS) {
                logger.log(Level.WARNING, "User has entered an invalid update workout command action.");
                throw new InvalidCommandException(className,
                        InvalidCommandException.INVALID_UPDATE_WORKOUT_COMMAND_ERROR_MSG);
            }
            arguments = userInput.split(" ", 3)[2];
            break;
        case LIST_ACTION_KEYWORD:
            if (userInput.split(" ", -1).length > EXPECTED_NUMBER_OF_PARAMETERS_NO_ARGUMENTS) {
                logger.log(Level.WARNING, "User has entered an invalid list workout command action.");
                throw new InvalidCommandException(className,
                        InvalidCommandException.INVALID_WORKOUT_LIST_COMMAND_ERROR_MSG);
            }
            break;
        default:
            logger.log(Level.WARNING, "User has entered an invalid workout command action.");
            throw new InvalidCommandException(className,
                    InvalidCommandException.INVALID_ACTION_ERROR_MSG);
        }
        return new WorkoutCommand(userInput, fileManager, workoutList, actionKeyword, arguments);
    }

    public ExitCommand createExitCommand(String userInput) {
        ExitCommand newCommand = new ExitCommand(userInput);
        return newCommand;
    }

    public ExerciseCommand createExerciseCommand(String userInput) throws
            InvalidCommandException, ArrayIndexOutOfBoundsException {
        logger.entering(getClass().getName(), "createExerciseCommand");
        String className = this.getClass().getSimpleName();
        String actionKeyword = userInput.split(" ", 3)[1];
        String arguments = null;
        switch (actionKeyword) {
        case LIST_ACTION_KEYWORD:
            if (userInput.split(" ", -1).length > EXPECTED_NUMBER_OF_PARAMETERS_NO_ARGUMENTS) {
                logger.log(Level.WARNING, "User has entered an invalid list exercise command action.");
                throw new InvalidCommandException(className,
                        InvalidCommandException.INVALID_EXERCISE_LIST_COMMAND_ERROR_MSG);
            }
            break;
        default:
            logger.log(Level.WARNING, "User has entered an invalid action under exercise command.");
            throw new InvalidCommandException(className, InvalidCommandException.INVALID_ACTION_ERROR_MSG);
        }
        logger.exiting(getClass().getName(), "createExerciseCommand");
        return new ExerciseCommand(userInput, ui, exerciseList, actionKeyword, arguments);

    }

    public HelpCommand createHelpCommand(String userInput) {
        return new HelpCommand(userInput);
    }

    public SearchCommand createSearchCommand(String userInput) throws
            InvalidCommandException, ArrayIndexOutOfBoundsException {
        String actionKeyword = userInput.split(" ", 3)[1];
        String arguments = null;
        String className = this.getClass().getSimpleName();
        switch (actionKeyword) {
        case SEARCH_EXERCISE_ACTION_KEYWORD:
            if (userInput.split(" ", 3).length < EXPECTED_NUMBER_OF_PARAMETERS_WITH_ARGUMENTS) {
                logger.log(Level.WARNING, "User has entered an invalid update search exercise command action.");
                throw new InvalidCommandException(className,
                        InvalidCommandException.INVALID_SEARCH_EXERCISE_COMMAND_ERROR_MSG);
            }
            arguments = userInput.split(" ", 3)[2];
            break;
        default:
            logger.log(Level.WARNING, "User has entered an invalid workout command action.");
            throw new InvalidCommandException(className,
                    InvalidCommandException.INVALID_ACTION_ERROR_MSG);
        }
        return new SearchCommand(userInput, ui, exerciseList, actionKeyword, arguments);
    }
}
