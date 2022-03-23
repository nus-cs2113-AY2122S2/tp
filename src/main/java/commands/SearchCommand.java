package commands;

import data.exercises.ExerciseList;
import data.plans.PlanList;
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
    public static final String BASE_KEYWORD = "search";
    public static final String SEARCH_EXERCISE_ACTION_KEYWORD = "/exercise";
    public static final String SEARCH_WORKOUT_ACTION_KEYWORD = "/workout";
    public static final String SEARCH_PLAN_ACTION_KEYWORD = "/plan";

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
     * workout-related search command.
     *
     * @param userInput     The user's full original input.
     * @param ui            An instance of the ui class.
     * @param workoutList   An instance of the PlanList class.
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
        case SEARCH_WORKOUT_ACTION_KEYWORD:
            //Fallthrough
        case SEARCH_PLAN_ACTION_KEYWORD:
            //Fallthrough
        case SEARCH_EXERCISE_ACTION_KEYWORD:
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
        case SEARCH_EXERCISE_ACTION_KEYWORD:
            if (isFirstMatch()) {
                printHeadingMessage("exercise");
            }
            break;
        case SEARCH_PLAN_ACTION_KEYWORD:
            if (isFirstMatch()) {
                printHeadingMessage("plan");
            }
            break;
        case SEARCH_WORKOUT_ACTION_KEYWORD:
            if (isFirstMatch()) {
                printHeadingMessage("workout");
            }
        default:
            logger.log(Level.WARNING, "User has entered an invalid search command action.");
            String className = this.getClass().getSimpleName();
            throw new InvalidCommandException(className, InvalidCommandException.INVALID_ACTION_ERROR_MSG);
        }
    }

    /**
     * Prints the heading of search result base on the category of userAction.
     *
     * @param category The category that will be searched.
     */
    private void printHeadingMessage(String category) {
        System.out.println("The " + category + "(s)" + " containing keywords"
                + ui.getColorText(TextColor.COLOR_YELLOW, " [" + userArguments + "] ")
                + "is(are) listed below.");
        ui.printLine();
    }

    /**
     * Returns the number of matching results.
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
     * Prints all the matching exercises if there exist at least one matching exercise, prints
     * "Sorry, no matching exercise found." if no matching exercise could be found.
     */
    public void searchExercise() throws InvalidCommandException {
        ArrayList<String> exerciseListToSearch = exerciseList.getExerciseList();
        for (String exerciseToSearch : exerciseListToSearch) {
            if (isMatch(exerciseToSearch.toLowerCase())) {
                incrementMatchCount();
                printSearchHeading();
                ui.printColorText(TextColor.COLOR_YELLOW, matchCount + ". " + exerciseToSearch);
            }
        }
        if (isZeroMatch()) {
            System.out.println("Sorry, no matching exercise found.");
        }
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
     */
    public void searchPlan() throws InvalidCommandException {
        var planListToSearch = planList.getPlansDisplayList();
        for (int i = 0; i < planListToSearch.size(); i++) {
            String planToBeCompared = planListToSearch.get(i).toLowerCase();
            if (isMatch(planToBeCompared)) {
                incrementMatchCount();
                printSearchHeading();
                ui.printColorText(TextColor.COLOR_YELLOW, matchCount + ". " + planToBeCompared);
            }
        }
        if (isZeroMatch()) {
            System.out.println("Sorry, no matching plan found.");
        }
    }

    /**
     * Prints all the matching workouts if there exist at least one matching workout, prints
     * "Sorry, no matching workout found." if no matching workout could be found.
     */
    public void searchWorkout() throws InvalidCommandException {
        var workoutListToSearch = workoutList.getWorkoutsDisplayList();
        for (int i = 0; i < workoutListToSearch.size(); i++) {
            String workoutToBeCompared = workoutListToSearch.get(i).toLowerCase();
            if (isMatch(workoutToBeCompared)) {
                incrementMatchCount();
                printSearchHeading();
                ui.printColorText(TextColor.COLOR_YELLOW, matchCount + ". " + workoutToBeCompared);
            }
        }
        if (isZeroMatch()) {
            System.out.println("Sorry, no matching plan found.");
        }
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
            case SEARCH_EXERCISE_ACTION_KEYWORD:
                searchExercise();
                break;
            case SEARCH_PLAN_ACTION_KEYWORD:
                searchPlan();
                break;
            case SEARCH_WORKOUT_ACTION_KEYWORD:
                searchWorkout();
                break;
            default:
                logger.log(Level.WARNING, "User has entered an invalid search command action.");
                String className = this.getClass().getSimpleName();
                throw new InvalidCommandException(className, InvalidCommandException.INVALID_ACTION_ERROR_MSG);
            }
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("Please try again");
        }
    }
}
