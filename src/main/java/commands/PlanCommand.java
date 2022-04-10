package commands;

import data.plans.InvalidPlanException;
import data.plans.Plan;
import data.plans.PlanList;
import storage.FileManager;
import storage.LogHandler;
import werkit.UI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class that will handle the commands relating to plan.
 */
public class PlanCommand extends Command {
    public static final String KEYWORD_BASE = "plan";
    public static final String ACTION_KEYWORD_CREATE = "/new";
    public static final String ACTION_KEYWORD_CREATE_WORKOUTS = "/workouts";
    public static final String ACTION_KEYWORD_LIST = "/list";
    public static final String ACTION_KEYWORD_DETAILS = "/details";
    public static final String ACTION_KEYWORD_DELETE = "/delete";

    private FileManager fileManager;
    private UI ui = new UI();
    private PlanList planList;

    private String userAction;
    private String userArguments;

    private static Logger logger = Logger.getLogger(PlanCommand.class.getName());

    /**
     * Constructs a new instance of the PlanCommand. Constructed when the user enters a
     * plan-related command.
     *
     * @param userInput The user's full original input.
     * @param fileManager An instance of the FileManager class.
     * @param planList An instance of the PlanList class.
     * @param userAction The action that was parsed from the user's input.
     * @param userArguments The arguments that are accompanied by the user action.
     * @throws InvalidCommandException If the command entered by the user is incorrect.
     */
    public PlanCommand(String userInput, FileManager fileManager, PlanList planList,
                       String userAction, String userArguments) throws InvalidCommandException {
        super(userInput);
        this.fileManager = fileManager;
        this.planList = planList;
        setUserAction(userAction);
        this.userArguments = userArguments;

        LogHandler.linkToFileLogger(logger);
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
     * Gets the instance of the PlanList class.
     *
     * @return An instance of the PlanList class.
     */
    public PlanList getPlanList() {
        return this.planList;
    }

    /**
     * Gets the action of the plan command specified by the user.
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
        case ACTION_KEYWORD_CREATE:
            //Fallthrough
        case ACTION_KEYWORD_LIST:
            //Fallthrough
        case ACTION_KEYWORD_DELETE:
            //Fallthrough
        case ACTION_KEYWORD_DETAILS:
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
     * Executes a plan-related command based on the action and arguments that is stored in the
     * class fields. If the action and/or arguments specified are invalid, this method will handle the
     * exceptions and print appropriate responses.
     */
    @Override
    public void execute() {
        try {
            switch (getUserAction()) {
            case ACTION_KEYWORD_CREATE:
                Plan newPlan = getPlanList().createNewPlan(getUserArguments());
                getPlanList().addNewPlanToLists(newPlan);
                getUI().printNewPlanCreatedMessage(newPlan);
                getFileManager().writeNewPlanToFile(newPlan);
                break;
            case ACTION_KEYWORD_LIST:
                getPlanList().listAllPlan();
                break;
            case ACTION_KEYWORD_DETAILS:
                getPlanList().listPlanDetails(getUserArguments(), ui);
                break;
            case ACTION_KEYWORD_DELETE:
                Plan deletedPlan = getPlanList().deletePlan(getUserArguments());
                getUI().printDeletePlanMessage(deletedPlan);
                getFileManager().rewriteAllPlansToFile(getPlanList());
                break;
            default:
                String className = this.getClass().getSimpleName();
                logger.log(Level.WARNING, "Invalid action under plan command is entered!");
                throw new InvalidCommandException(className, InvalidCommandException.INVALID_ACTION_ERROR_MSG);
            }
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("Please try again");
        } catch (InvalidPlanException e) {
            System.out.println(e.getMessage());
            System.out.println("Please try again");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Uh oh, it seems like too few arguments were entered.");
            System.out.println("Please try again. Alternatively, type 'help' if you need\n"
                    + "more information on the commands.");
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "A non-formattable number was received!");
            System.out.println("Uh oh, the user argument supplied is invalid.");
            System.out.println("Please try again. Alternatively, type 'help' if you need\n"
                    + "more information on the commands.");
        } catch (IOException e) {
            System.out.println(UI.IOEXCEPTION_ERROR_MESSAGE);
            System.exit(-1);
        }
    }
}

