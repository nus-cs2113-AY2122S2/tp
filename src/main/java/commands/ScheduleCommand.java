package commands;

import data.plans.Plan;
import data.plans.PlanList;
import data.schedule.Day;
import data.schedule.DayList;
import data.schedule.InvalidScheduleException;
import storage.FileManager;
import storage.LogHandler;
import werkit.UI;

import java.util.logging.Level;
import java.util.logging.Logger;

import static data.schedule.InvalidScheduleException.INDEX_OUT_OF_BOUND;
import static data.schedule.InvalidScheduleException.INPUT_NOT_NUMBER_FORMATTABLE;

public class ScheduleCommand extends Command {
    public static final String BASE_KEYWORD = "schedule";
    public static final String UPDATE_ACTION_KEYWORD = "/update";
    public static final String LIST_ACTION_KEYWORD = "/list";
    public static final String CLEAR_ACTION_KEYWORD = "/clear";
    public static final String CLEAR_ALL_ACTION_KEYWORD = "/clearall";

    private FileManager fileManager;
    private UI ui = new UI();
    private PlanList planList;
    private DayList scheduleList;

    private String userAction;
    private String userArguments;

    private static Logger logger = Logger.getLogger(commands.ScheduleCommand.class.getName());


    public ScheduleCommand(String userInput, FileManager fileManager, DayList scheduleList,
                           String userAction, String userArguments) throws InvalidCommandException {
        super(userInput);
        this.fileManager = fileManager;
        this.scheduleList = scheduleList;
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
     * Gets the action of the workout command specified by the user.
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
        case UPDATE_ACTION_KEYWORD:
            //Fallthrough
        case LIST_ACTION_KEYWORD:
            //Fallthrough
        case CLEAR_ACTION_KEYWORD:
            //Fallthrough
        case CLEAR_ALL_ACTION_KEYWORD:
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


    @Override
    public void execute() {
        try {
            switch (getUserAction()) {
            case UPDATE_ACTION_KEYWORD:
                Day newDay = scheduleList.updateDay(getUserArguments());
                getUI().printNewScheduleCreatedMessage(newDay);
                break;
            case LIST_ACTION_KEYWORD:
                scheduleList.printSchedule();
                break;
            case CLEAR_ACTION_KEYWORD:
                scheduleList.clearDayPlan(getUserArguments());
                break;
            case CLEAR_ALL_ACTION_KEYWORD:
                scheduleList.clearAllSchedule();
                getUI().printClearedScheduleMessage();
                break;
            default:
                String className = this.getClass().getSimpleName();
                logger.log(Level.WARNING, "Invalid action under schedule command is entered!");
                throw new InvalidCommandException(className, InvalidCommandException.INVALID_ACTION_ERROR_MSG);
            }
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("Please try again");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(INDEX_OUT_OF_BOUND);
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "A non-formattable number was received!");
            System.out.println(INPUT_NOT_NUMBER_FORMATTABLE);
        } catch (InvalidScheduleException e) {
            System.out.println(e.getMessage());
        }
    }
}
