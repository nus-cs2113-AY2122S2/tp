package commands;

import data.exercises.ExerciseList;
import werkit.UI;
import textcolors.TextColor;

import java.util.ArrayList;
import java.util.Locale;

public class SearchCommand extends Command {
    public static final String BASE_KEYWORD = "search";
    public static final String SEARCH_EXERCISE_ACTION_KEYWORD = "/exercise";
    public static final String SEARCH_WORKOUT_ACTION_KEYWORD = "/workout";
    public static final String SEARCH_PLAN_ACTION_KEYWORD = "/plan";

    private UI ui;
    private ExerciseList exerciseList;

    private String userAction;
    private String userArguments;

    public SearchCommand(String userInput, UI ui, ExerciseList exerciseList,
                         String userAction, String userArguments) throws InvalidCommandException {
        super(userInput);
        this.ui = ui;
        this.exerciseList = exerciseList;
        setUserAction(userAction);
        this.userArguments = userArguments.toLowerCase();
    }

    public void setUserAction(String userAction) throws InvalidCommandException {
        switch (userAction) {
        case SEARCH_EXERCISE_ACTION_KEYWORD:
            this.userAction = userAction;
            break;
        default:
            String className = this.getClass().getSimpleName();
            throw new InvalidCommandException(className, InvalidCommandException.INVALID_ACTION_ERROR_MSG);
        }
    }

    public void execute() {
        try {
            switch (userAction) {
            case SEARCH_EXERCISE_ACTION_KEYWORD:
                ArrayList<String> exerciseListToSearch = exerciseList.getExerciseList();
                int count = 0;
                for (int i = 0; i < exerciseListToSearch.size(); i++) {
                    if (exerciseListToSearch.get(i).toLowerCase().contains(userArguments)) {
                        count += 1;
                        if (count == 1) {
                            System.out.println("The exercise(s) containing keywords"
                                    + ui.getColorText(TextColor.COLOR_YELLOW, " [" + userArguments + "] ")
                                    + "is(are) listed below.");
                            ui.printLine();
                        }
                        ui.printColorText(TextColor.COLOR_YELLOW, count + ". " + exerciseListToSearch.get(i));
                    }
                }
                if (count == 0) {
                    System.out.println("Sorry, no matching exercise found.");
                }
                break;
            default:
                String className = this.getClass().getSimpleName();
                throw new InvalidCommandException(className, InvalidCommandException.INVALID_ACTION_ERROR_MSG);
            }
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("Please try again");
        }

    }
}
