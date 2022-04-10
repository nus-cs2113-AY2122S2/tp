package commands;

import data.exercises.ExerciseList;
import data.exercises.InvalidExerciseException;
import data.plans.InvalidPlanException;
import data.plans.PlanList;
import data.workouts.InvalidWorkoutException;
import data.workouts.WorkoutList;
import storage.LogHandler;
import werkit.UI;
import textcolors.TextColor;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class that will handle the commands relating to search.
 */
public class SearchCommand extends Command {
    public static final String KEYWORD_BASE = "search";
    public static final String ACTION_KEYWORD_EXERCISE = "/exercise";
    public static final String ACTION_KEYWORD_WORKOUT = "/workout";
    public static final String ACTION_KEYWORD_PLAN = "/plan";
    public static final String ACTION_KEYWORD_ALL = "/all";
    public static final String CATEGORY_EXERCISE = "exercise";
    public static final String CATEGORY_WORKOUT = "workout";
    public static final String CATEGORY_PLAN = "plan";

    private UI ui;
    private ExerciseList exerciseList;
    private WorkoutList workoutList;
    private PlanList planList;

    private String userAction;
    private String userArguments;
    private int matchCount;

    private static Logger logger = Logger.getLogger(SearchCommand.class.getName());

    /**
     * Constructs a new instance of the SearchCommand. Constructed when the user enters an
     * exercise-related search command.
     *
     * @param userInput     The user's full original input.
     * @param ui            An instance of the ui class.
     * @param exerciseList  An instance of the ExerciseList class.
     * @param userAction    The action that was parsed from the user's input.
     * @param userArguments The arguments that are accompanied by the user action.
     * @throws InvalidCommandException  If the command entered by the user is incorrect.
     */
    public SearchCommand(String userInput, UI ui, ExerciseList exerciseList,
                         String userAction, String userArguments) throws InvalidCommandException {
        super(userInput);
        this.ui = ui;
        this.exerciseList = exerciseList;
        setUserAction(userAction);
        this.userArguments = userArguments.toLowerCase();
        this.matchCount = 0;

        LogHandler.linkToFileLogger(logger);
    }

    /**
     * Constructs a new instance of the SearchCommand. Constructed when the user enters a
     * plan-related search command.
     *
     * @param userInput     The user's full original input.
     * @param ui            An instance of the ui class.
     * @param planList      An instance of the PlanList class.
     * @param userAction    The action that was parsed from the user's input.
     * @param userArguments The arguments that are accompanied by the user action.
     * @throws InvalidCommandException  If the command entered by the user is incorrect.
     */
    public SearchCommand(String userInput, UI ui, PlanList planList,
                             String userAction, String userArguments) throws InvalidCommandException {
        super(userInput);
        this.ui = ui;
        this.planList = planList;
        setUserAction(userAction);
        this.userArguments = userArguments.toLowerCase();
        this.matchCount = 0;

        LogHandler.linkToFileLogger(logger);
    }

    /**
     * Constructs a new instance of the SearchCommand. Constructed when the user enters a
     * search command for all types.
     *
     * @param userInput     The user's full original input.
     * @param ui            An instance of the ui class.
     * @param exerciseList  An instance of the ExerciseList class.
     * @param workoutList   An instance of the WorkoutList class.
     * @param planList      An instance of the PlanList class.
     * @param userAction    The action that was parsed from the user's input.
     * @param userArguments The arguments that are accompanied by the user action.
     * @throws InvalidCommandException  If the command entered by the user is incorrect.
     */
    public SearchCommand(String userInput, UI ui, ExerciseList exerciseList, WorkoutList workoutList, PlanList planList,
                         String userAction, String userArguments) throws InvalidCommandException {
        super(userInput);
        this.ui = ui;
        this.exerciseList = exerciseList;
        this.workoutList = workoutList;
        this.planList = planList;
        setUserAction(userAction);
        this.userArguments = userArguments.toLowerCase();
        this.matchCount = 0;

        LogHandler.linkToFileLogger(logger);
    }

    /**
     * Constructs a new instance of the SearchCommand. Constructed when the user enters a
     * workout-related search command.
     *
     * @param userInput     The user's full original input.
     * @param ui            An instance of the ui class.
     * @param workoutList   An instance of the WorkoutList class.
     * @param userAction    The action that was parsed from the user's input.
     * @param userArguments The arguments that are accompanied by the user action.
     * @throws InvalidCommandException  If the command entered by the user is incorrect.
     */
    public SearchCommand(String userInput, UI ui, WorkoutList workoutList,
                         String userAction, String userArguments) throws InvalidCommandException {
        super(userInput);
        this.ui = ui;
        this.workoutList = workoutList;
        setUserAction(userAction);
        this.userArguments = userArguments.toLowerCase();
        this.matchCount = 0;

        LogHandler.linkToFileLogger(logger);
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
     * Gets the user argument stored in the class instance.
     *
     * @return A string containing the user argument.
     */
    public String getUserArguments() {
        return this.userArguments;
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
        case ACTION_KEYWORD_WORKOUT:
            //Fallthrough
        case ACTION_KEYWORD_PLAN:
            //Fallthrough
        case ACTION_KEYWORD_EXERCISE:
            //Fallthrough
        case ACTION_KEYWORD_ALL:
            this.userAction = userAction;
            break;
        default:
            logger.log(Level.WARNING, "User has entered an invalid search command action.");
            String className = this.getClass().getSimpleName();
            throw new InvalidCommandException(className, InvalidCommandException.INVALID_ACTION_ERROR_MSG);
        }
    }

    /**
     * Prints the heading of search result base on the userAction stored in the class field.
     *
     * @throws InvalidCommandException  If the action specified by the user is invalid.
     */
    private void printSearchHeading() throws InvalidCommandException {
        switch (userAction) {
        case ACTION_KEYWORD_EXERCISE:
            printSearchExerciseHeading();
            break;
        case ACTION_KEYWORD_PLAN:
            printSearchPlanHeading();
            break;
        case ACTION_KEYWORD_WORKOUT:
            printSearchWorkoutHeading();
            break;
        default:
            logger.log(Level.WARNING, "User has entered an invalid search command action.");
            String className = this.getClass().getSimpleName();
            throw new InvalidCommandException(className, InvalidCommandException.INVALID_ACTION_ERROR_MSG);
        }
    }

    /**
     * Prints the heading message for search for exercise.
     */
    private void printSearchExerciseHeading() {
        if (isFirstMatch()) {
            printHeadingMessage(CATEGORY_EXERCISE);
        }
    }

    /**
     * Prints the heading message for search for workout.
     */
    private void printSearchWorkoutHeading() {
        if (isFirstMatch()) {
            printHeadingMessage(CATEGORY_WORKOUT);
        }
    }

    /**
     * Prints the heading message for search for plan.
     */
    private void printSearchPlanHeading() {
        if (isFirstMatch()) {
            printHeadingMessage(CATEGORY_PLAN);
        }
    }

    /**
     * Prints the heading of search result base on the category of userAction.
     *
     * @param category The category that will be searched.
     */
    private void printHeadingMessage(String category) {
        switch (userAction) {
        case ACTION_KEYWORD_WORKOUT:
            try {
                Integer.parseInt(userArguments);
                System.out.println("The " + category + "(s) with "
                        + ui.getColorText(TextColor.COLOR_YELLOW, "reps = " + userArguments)
                        + " is(are) listed below together\nwith its(their) original index number(s).");
                ui.printLine();
            } catch (NumberFormatException e) {
                System.out.println("The " + category + "(s)" + " containing keyword(s)"
                        + ui.getColorText(TextColor.COLOR_YELLOW, " [" + userArguments + "] ")
                        + "is(are) listed below together\nwith its(their) original index number(s).");
                ui.printLine();
            }
            break;
        default:
            System.out.println("The " + category + "(s)" + " containing keyword(s)"
                    + ui.getColorText(TextColor.COLOR_YELLOW, " [" + userArguments + "] ")
                    + "is(are) listed below together\nwith its(their) original index number(s).");
            ui.printLine();
        }
    }

    /**
     * Returns the number of matching results.
     *
     * @return  The number of matching results.
     */
    private int getMatchCount() {
        return matchCount;
    }

    /**
     * Returns true if the matchCount has a value of 1.
     *
     * @return True if the matchCount has a value of 1.
     */
    private boolean isFirstMatch() {
        return getMatchCount() == 1;
    }

    /**
     * Increments the matchCount value by 1.
     */
    private void incrementMatchCount() {
        this.matchCount += 1;
    }

    /**
     * Returns true if the matchCount value has a value of 0.
     *
     * @return True if the matchCount value has a value of 0.
     */
    private boolean isZeroMatch() {
        return matchCount == 0;
    }

    /**
     * Sets the matchCount to zero.
     */
    private void clearMatchCount() {
        this.matchCount = 0;
    }

    /**
     * Prints all the matching exercises if there exist at least one matching exercise, prints
     * "Sorry, no matching exercise found." if no matching exercise could be found.
     *
     * @throws InvalidCommandException If the action specified by the user is invalid.
     * @throws InvalidExerciseException If the exercise specified by the user is invalid.
     */
    public void searchExercise() throws InvalidCommandException, InvalidExerciseException {
        ArrayList<String> exerciseListToSearch = exerciseList.getExerciseList();
        for (String exerciseToSearch : exerciseListToSearch) {
            if (isMatch(exerciseToSearch.toLowerCase())) {
                incrementMatchCount();
                printSearchHeading();
                ui.printColorText(TextColor.COLOR_YELLOW,
                        exerciseList.getIndexNumFromExerciseName(exerciseToSearch) + ". " + exerciseToSearch);
            }
        }
        if (isZeroMatch()) {
            ui.printSearchNotFoundMessage(CATEGORY_EXERCISE, getUserArguments());
        }
        clearMatchCount();
    }

    /**
     * Prints message when no matching result is found.
     *
     * @param category The category that will be searched.
     */
    public void printNotFoundMessage(String category) {
        System.out.println("Sorry, no matching " + category + " found.");
    }

    /**
     * Returns true if the String record contains userArguments.
     *
     * @param record    A record stored in local resource file to be compared with userArguments.
     * @return          True if the String record contains userArguments.
     */
    public boolean isMatch(String record) {
        return record.contains(userArguments);
    }

    /**
     * Prints all the matching plans if there exist at least one matching plan, prints
     * "Sorry, no matching plan found." if no matching plan could be found.
     *
     * @throws InvalidCommandException If the action specified by the user is invalid.
     * @throws InvalidPlanException If the plan specified by the user is invalid.
     */
    public void searchPlan() throws InvalidCommandException, InvalidPlanException {
        var planListToSearch = planList.getPlansDisplayList();
        for (String planToSearch : planListToSearch) {
            String planToBeCompared = planToSearch.toLowerCase();
            if (isMatch(planToBeCompared)) {
                incrementMatchCount();
                printSearchHeading();
                ui.printColorText(TextColor.COLOR_YELLOW,
                        planList.getIndexNumFromPlanName(planToBeCompared)
                                + ". " + planToBeCompared);
            }
        }
        if (isZeroMatch()) {
            ui.printSearchNotFoundMessage(CATEGORY_PLAN, getUserArguments());
        }
        clearMatchCount();
    }

    /**
     * Parses the number of reps of a given workout.
     *
     * @param workoutDisplayName    The workout name to be parsed.
     * @return                      The integer value of reps.
     */
    public int parseWorkoutReps(String workoutDisplayName) {
        var beginningOfReps = workoutDisplayName.indexOf("(") + 1;
        var endingOfReps = workoutDisplayName.indexOf(" reps)");
        var reps = workoutDisplayName.substring(beginningOfReps, endingOfReps).trim();
        return Integer.parseInt(reps);
    }

    /**
     * Returns true if the int value of userArguments is the same as quantity.
     *
     * @param quantity                  Number of reps of a workout.
     * @return                          True if the int value of userArguments is the same as quantity.
     * @throws NumberFormatException    If userArguments cannot be parsed to integer.
     */
    public boolean isMatchQuantity(int quantity) throws NumberFormatException {
        return quantity == Integer.parseInt(userArguments);
    }

    /**
     * Prints all the matching workouts if there exist at least one matching workout, prints
     * "Sorry, no matching workout found." if no matching workout could be found.
     *
     * @throws InvalidCommandException If the action specified by the user is invalid.
     * @throws InvalidWorkoutException If the workout specified by the user is invalid.
     */
    public void searchWorkout() throws InvalidCommandException, InvalidWorkoutException {
        var workoutListToSearch = workoutList.getWorkoutsDisplayList();
        for (String workoutToSearch : workoutListToSearch) {
            String workoutToBeCompared = workoutToSearch.toLowerCase();
            var reps = parseWorkoutReps(workoutToBeCompared);
            var beginningOfReps = workoutToBeCompared.indexOf("(");
            var exerciseName = workoutToBeCompared.substring(0, beginningOfReps).trim();
            try {
                if (isMatchQuantity(reps)) {
                    incrementMatchCount();
                    printSearchHeading();
                    ui.printColorText(TextColor.COLOR_YELLOW,
                            workoutList.getIndexNumFromWorkoutName(workoutToBeCompared)
                                    + ". " + workoutToBeCompared);
                }
            } catch (NumberFormatException e) {
                if (isMatch(exerciseName)) {
                    incrementMatchCount();
                    printSearchHeading();
                    ui.printColorText(TextColor.COLOR_YELLOW,
                            workoutList.getIndexNumFromWorkoutName(workoutToBeCompared)
                                    + ". " + workoutToBeCompared);
                }
            } catch (InvalidWorkoutException e) {
                e.printStackTrace();
            }
        }
        if (isZeroMatch()) {
            ui.printSearchNotFoundMessage(CATEGORY_WORKOUT, getUserArguments());
        }
        clearMatchCount();
    }

    /**
     * Prints the relevant exercises, workouts and plans that match the userArguments.
     *
     * @throws InvalidCommandException  If the action specified by the user is invalid.
     * @throws InvalidPlanException  If the plan specified by the user is invalid.
     * @throws InvalidWorkoutException  If the workout specified by the user is invalid.
     * @throws InvalidExerciseException  If the exercise specified by the user is invalid.
     */
    public void searchAll() throws InvalidCommandException, InvalidPlanException,
            InvalidWorkoutException, InvalidExerciseException {
        setUserAction(ACTION_KEYWORD_EXERCISE);
        searchExercise();
        ui.printLine();
        setUserAction(ACTION_KEYWORD_WORKOUT);
        searchWorkout();
        ui.printLine();
        setUserAction(ACTION_KEYWORD_PLAN);
        searchPlan();
        setUserAction(ACTION_KEYWORD_ALL);
        clearMatchCount();
    }

    /**
     * Executes a search-related command based on the action and arguments that is stored in the
     * class fields. If the action and/or arguments specified are invalid, this method will handle the
     * exceptions and print appropriate responses.
     */
    @Override
    public void execute() {
        try {
            switch (userAction) {
            case ACTION_KEYWORD_EXERCISE:
                searchExercise();
                break;
            case ACTION_KEYWORD_PLAN:
                searchPlan();
                break;
            case ACTION_KEYWORD_WORKOUT:
                searchWorkout();
                break;
            case ACTION_KEYWORD_ALL:
                searchAll();
                break;
            default:
                logger.log(Level.WARNING, "User has entered an invalid search command action.");
                String className = this.getClass().getSimpleName();
                throw new InvalidCommandException(className, InvalidCommandException.INVALID_ACTION_ERROR_MSG);
            }
        } catch (InvalidCommandException | InvalidPlanException
                | InvalidWorkoutException | InvalidExerciseException e) {
            System.out.println(e.getMessage());
            System.out.println("Please try again");
        }
    }
}
