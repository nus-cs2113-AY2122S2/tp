package commands;

import data.exercises.ExerciseList;
import werkIt.UI;

public class ExerciseCommand extends Command{
    public static final String BASE_KEYWORD = "exercise";
    public static final String LIST_ACTION_KEYWORD = "/list";

    private UI ui;
    private ExerciseList exerciseList;

    private String userAction;
    private String userArguments;

    public ExerciseCommand(String userInput, UI ui, ExerciseList exerciseList,
                          String userAction, String userArguments) throws InvalidCommandException {
        super(userInput);
        this.ui = ui;
        this.exerciseList = exerciseList;
        setUserAction(userAction);
        this.userArguments = userArguments;
    }

    public void setUserAction(String userAction) throws InvalidCommandException {
        switch (userAction) {
        case LIST_ACTION_KEYWORD:
            this.userAction = userAction;
            break;
        default:
            String className = this.getClass().getSimpleName();
            throw new InvalidCommandException(className, InvalidCommandException.INVALID_ACTION_ERROR_MSG);
        }
    }

    @Override
    public void execute() {
        switch(userAction) {
        case LIST_ACTION_KEYWORD:
            exerciseList.printExerciseList();
            break;
        }
    }
}
