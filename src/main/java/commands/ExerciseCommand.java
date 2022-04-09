package commands;

import data.exercises.ExerciseList;
import werkit.UI;

public class ExerciseCommand extends Command {
    public static final String KEYWORD_BASE = "exercise";
    public static final String ACTION_KEYWORD_LIST = "/list";

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
        case ACTION_KEYWORD_LIST:
            this.userAction = userAction;
            break;
        default:
            String className = this.getClass().getSimpleName();
            throw new InvalidCommandException(className, InvalidCommandException.INVALID_ACTION_ERROR_MSG);
        }
    }

    @Override
    public String getUserAction() {
        return userAction;
    }

    @Override
    public void execute() {
        try {
            switch (userAction) {
            case ACTION_KEYWORD_LIST:
                exerciseList.printExerciseList();
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
