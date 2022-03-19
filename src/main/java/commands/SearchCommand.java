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

    private static Logger logger = Logger.getLogger(PlanCommand.class.getName());

    public SearchCommand(String userInput, UI ui, ExerciseList exerciseList,
                         String userAction, String userArguments) throws InvalidCommandException {
        super(userInput);
        this.ui = ui;
        this.exerciseList = exerciseList;
        setUserAction(userAction);
        this.userArguments = userArguments.toLowerCase();

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
        System.out.println("The exercise(s) containing keywords"
                + ui.getColorText(TextColor.COLOR_YELLOW, " [" + userArguments + "] ")
                + "is(are) listed below.");
        ui.printLine();
    }

    public void findMatchExercise() {
        ArrayList<String> exerciseListToSearch = exerciseList.getExerciseList();
        int count = 0;
        for (String listToSearch : exerciseListToSearch) {
            if (listToSearch.toLowerCase().contains(userArguments)) {
                count += 1;
                if (count == 1) {
                    printSearchHeading();
                }
                ui.printColorText(TextColor.COLOR_YELLOW, count + ". " + listToSearch);
            }
        }
        if (count == 0) {
            System.out.println("Sorry, no matching exercise found.");
        }
    }

    public void execute() {
        try {
            switch (userAction) {
            case SEARCH_EXERCISE_ACTION_KEYWORD:
                findMatchExercise();
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
