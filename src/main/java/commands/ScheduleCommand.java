package commands;

import data.plans.Plan;
import data.plans.PlanList;
import data.schedule.Day;
import data.schedule.DayList;
import data.schedule.InvalidScheduleException;
import storage.FileManager;
import storage.LogHandler;
import werkit.UI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static data.schedule.InvalidScheduleException.INDEX_OUT_OF_BOUND;
import static data.schedule.InvalidScheduleException.INPUT_NOT_NUMBER_FORMATTABLE;

public class ScheduleCommand extends Command {
    public static final String KEYWORD_BASE = "schedule";
    public static final String ACTION_KEYWORD_UPDATE = "/update";
    public static final String ACTION_KEYWORD_LIST = "/list";
    public static final String ACTION_KEYWORD_CLEAR= "/clear";
    public static final String ACTION_KEYWORD_CLEAR_ALL = "/clearall";

    private FileManager fileManager;
    private UI ui = new UI();
    private PlanList planList;
    private DayList scheduleList;

    private String userAction;
    private String userArguments;

    private static Logger logger = Logger.getLogger(commands.ScheduleCommand.class.getName());

    /**
     * Constructs a new instance of the ScheduleCommand. Constructed when the user enters a
     * schedule-related command.
     *
     * @param userInput     The user's full original input.
     * @param fileManager   An instance of the FileManager class.
     * @param scheduleList   An instance of the DayList class.
     * @param userAction    The action that was parsed from the user's input.
     * @param userArguments The arguments that are accompanied by the user action.
     * @throws InvalidCommandException If the command entered by the user is incorrect.
     */
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
     * Gets the instance of the DayList class.
     *
     * @return An instance of the DayList class.
     */
    public DayList getScheduleList() {
        return this.scheduleList;
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
        case ACTION_KEYWORD_UPDATE:
            //Fallthrough
        case ACTION_KEYWORD_LIST:
            //Fallthrough
        case ACTION_KEYWORD_CLEAR:
            //Fallthrough
        case ACTION_KEYWORD_CLEAR_ALL:
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
     * Executes a schedule-related command based on the action and arguments that is stored in the
     * class fields. If the action and/or arguments specified are invalid, this method will handle the
     * exceptions and print appropriate responses.
     */
    @Override
    public void execute() {
        try {
            switch (getUserAction()) {
            case ACTION_KEYWORD_UPDATE:
                Day newDay = getScheduleList().updateDay(getUserArguments());
                getUI().printNewScheduleCreatedMessage(newDay);
                getFileManager().rewriteAllDaysScheduleToFile(getScheduleList());
                break;
            case ACTION_KEYWORD_LIST:
                getScheduleList().printSchedule();
                break;
            case ACTION_KEYWORD_CLEAR:
                String dayName = getScheduleList().clearDayPlan(getUserArguments());
                getUI().printClearedScheduleOnADay(dayName);
                getFileManager().rewriteAllDaysScheduleToFile(getScheduleList());
                break;
            case ACTION_KEYWORD_CLEAR_ALL:
                getScheduleList().clearAllSchedule();
                getUI().printClearedScheduleMessage();
                getFileManager().rewriteAllDaysScheduleToFile(getScheduleList());
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
        } catch (IOException e) {
            System.out.println(UI.IOEXCEPTION_ERROR_MESSAGE);
            System.exit(-1);
        }
    }
}
