package commands;

import data.exercises.ExerciseList;
import data.plans.Plan;
import data.plans.PlanList;
import storage.LogHandler;
import werkit.UI;
import textcolors.TextColor;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchCommand extends Command {
    public static final String BASE_KEYWORD = "search";
    public static final String SEARCH_EXERCISE_ACTION_KEYWORD = "/exercise";
    public static final String SEARCH_WORKOUT_ACTION_KEYWORD = "/workout";
    public static final String SEARCH_PLAN_ACTION_KEYWORD = "/plan";

    private UI ui;
    private ExerciseList exerciseList;
    private PlanList planList;

    private String userAction;
    private String userArguments;
    private int matchCount;

    private static Logger logger = Logger.getLogger(SearchCommand.class.getName());

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
        default:
            logger.log(Level.WARNING, "User has entered an invalid search command action.");
            String className = this.getClass().getSimpleName();
            throw new InvalidCommandException(className, InvalidCommandException.INVALID_ACTION_ERROR_MSG);
        }
    }

    private void printHeadingMessage(String category) {
        System.out.println("The " + category + "(s)" + " containing keywords"
                + ui.getColorText(TextColor.COLOR_YELLOW, " [" + userArguments + "] ")
                + "is(are) listed below.");
        ui.printLine();
    }

    private int getMatchCount() {
        return matchCount;
    }

    private boolean isFirstMatch() {
        return getMatchCount() == 1;
    }

    private void incrementMatchCount() {
        this.matchCount += 1;
    }

    private boolean isZeroMatch() {
        return matchCount == 0;
    }

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

    public boolean isMatch(String record) {
        return record.contains(userArguments);
    }

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

    public void execute() {
        try {
            switch (userAction) {
            case SEARCH_EXERCISE_ACTION_KEYWORD:
                searchExercise();
                break;
            case SEARCH_PLAN_ACTION_KEYWORD:
                searchPlan();
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
