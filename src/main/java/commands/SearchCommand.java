package commands;

import data.exercises.ExerciseList;
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

    private String userAction;
    private String userArguments;
    private int matchCount;

    private static Logger logger = Logger.getLogger(PlanCommand.class.getName());

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

    public void setUserAction(String userAction) throws InvalidCommandException {
        switch (userAction) {
        case SEARCH_EXERCISE_ACTION_KEYWORD:
            this.userAction = userAction;
            break;
        default:
            logger.log(Level.WARNING, "User has entered an invalid search exercise command action.");
            String className = this.getClass().getSimpleName();
            throw new InvalidCommandException(className, InvalidCommandException.INVALID_ACTION_ERROR_MSG);
        }
    }

    private void printSearchHeading() {
        if (matchCount == 1) {
            System.out.println("The exercise(s) containing keywords"
                    + ui.getColorText(TextColor.COLOR_YELLOW, " [" + userArguments + "] ")
                    + "is(are) listed below.");
            ui.printLine();
        }
    }

    private void incrementMatchCount() {
        this.matchCount += 1;
    }

    private boolean isZeroMatch() {
        return matchCount == 0;
    }

    public void searchExercise() {
        ArrayList<String> exerciseListToSearch = exerciseList.getExerciseList();
        for (String exerciseToSearch : exerciseListToSearch) {
            if (isMatchExercise(exerciseToSearch.toLowerCase())) {
                incrementMatchCount();
                printSearchHeading();
                ui.printColorText(TextColor.COLOR_YELLOW, matchCount + ". " + exerciseToSearch);
            }
        }
        if (isZeroMatch()) {
            System.out.println("Sorry, no matching exercise found.");
        }
    }

    public boolean isMatchExercise(String exercise) {
        return exercise.contains(userArguments);
    }

    public void execute() {
        try {
            switch (userAction) {
            case SEARCH_EXERCISE_ACTION_KEYWORD:
                searchExercise();
                break;
            default:
                logger.log(Level.WARNING, "User has entered an invalid search exercise command action.");
                String className = this.getClass().getSimpleName();
                throw new InvalidCommandException(className, InvalidCommandException.INVALID_ACTION_ERROR_MSG);
            }
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("Please try again");
        }
    }
}
