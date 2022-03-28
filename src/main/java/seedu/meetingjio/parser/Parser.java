package seedu.meetingjio.parser;

import seedu.meetingjio.commands.AddLessonCommand;
import seedu.meetingjio.commands.Command;
import seedu.meetingjio.commands.ListCommand;
import seedu.meetingjio.commands.DeleteCommand;
import seedu.meetingjio.commands.ClearCommand;
import seedu.meetingjio.commands.AddMeetingCommand;
import seedu.meetingjio.commands.FreeCommand;
import seedu.meetingjio.commands.CommandResult;
import seedu.meetingjio.commands.HelpCommand;

import seedu.meetingjio.exceptions.InvalidDayException;
import seedu.meetingjio.exceptions.InvalidModeException;
import seedu.meetingjio.exceptions.InvalidTimeException;
import seedu.meetingjio.exceptions.MissingValueException;

import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.meetingjio.common.ErrorMessages.ERROR_MISSING_VALUES;
import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_COMMAND;
import static seedu.meetingjio.common.ErrorMessages.ERROR_MISSING_PARAMETERS;
import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_TIME;
import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_DAY;
import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_MODE;
import static seedu.meetingjio.common.ErrorMessages.ERROR_INDEX_OUT_OF_BOUND;
import static seedu.meetingjio.common.ErrorMessages.ERROR_MISSING_PARAMETERS_DELETE;
import static seedu.meetingjio.common.ErrorMessages.ERROR_MISSING_VALUES_DELETE;
import static seedu.meetingjio.common.ErrorMessages.ERROR_MISSING_PARAMETERS_ADD_MEETING;
import static seedu.meetingjio.common.ErrorMessages.ERROR_MISSING_VALUES_ADD_MEETING;

import static seedu.meetingjio.common.Messages.MESSAGE_HELP;

public class Parser {

    private final String command;
    private final String arguments;
    public static Logger logger = Logger.getLogger(Parser.class.getName());
    private ParserHelperMethods parserHelperMethods = new ParserHelperMethods();
    private ParserArguments parserArguments = new ParserArguments();

    private static final int NAME_INDEX = 0;
    private static final int TITLE_INDEX = 1;
    private static final int DAY_INDEX = 2;
    private static final int START_TIME_INDEX = 3;
    private static final int END_TIME_INDEX = 4;
    private static final int MODE_INDEX = 5;

    protected static final String[] HEADINGS_ADD_LESSON = {"n/", "t/", "d/", "st/", "et/", "m/"};
    protected static final String[] HEADINGS_ADD_MEETING = {"t/", "d/", "st/", "et/", "m/"};
    protected static final String[] HEADINGS_DELETE_EVENT = {"n/", "i/"};

    public Parser(String input) {
        this.command = parserArguments.getCommandFromInput(input);
        this.arguments = parserArguments.getArgumentsFromInput(input);
    }

    public String getArguments() {
        return arguments;
    }

    public Command parseCommand() {
        switch (command) {
        case AddLessonCommand.COMMAND_WORD:
            return prepareAddLesson();
        case ListCommand.COMMAND_WORD:
            return new ListCommand(arguments);
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete();
        case ClearCommand.COMMAND_WORD:
            return new ClearCommand(arguments);
        case FreeCommand.COMMAND_WORD:
            return new FreeCommand(arguments);
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case AddMeetingCommand.COMMAND_WORD:
            return prepareAddMeeting();
        default:
            String feedback = ERROR_INVALID_COMMAND + '\n' + MESSAGE_HELP;
            return new CommandResult(feedback);
        }
    }
    
    public Command prepareAddLesson() {
        try {
            String[] eventDescription = parserArguments.splitArgumentsAddLesson(this.getArguments());
            parserHelperMethods.checkNonNullValues(eventDescription,HEADINGS_ADD_LESSON.length - 1);

            String day = eventDescription[DAY_INDEX].toLowerCase();
            int startTime = Integer.parseInt(eventDescription[START_TIME_INDEX]);
            int endTime = Integer.parseInt(eventDescription[END_TIME_INDEX]);
            String mode = eventDescription[MODE_INDEX].toLowerCase();

            parserHelperMethods.checkDay(day);
            parserHelperMethods.checkTime(startTime, endTime);
            parserHelperMethods.checkMode(mode);

            String name = eventDescription[NAME_INDEX];
            String title = eventDescription[TITLE_INDEX];
            return new AddLessonCommand(name, title, day, startTime, endTime, mode);

        } catch (ArrayIndexOutOfBoundsException | NullPointerException npe) {
            return new CommandResult(ERROR_MISSING_PARAMETERS);
        } catch (MissingValueException mve) {
            return new CommandResult(ERROR_MISSING_VALUES);
        } catch (InvalidTimeException | NumberFormatException ite) {
            return new CommandResult(ERROR_INVALID_TIME);
        } catch (InvalidDayException ide) {
            return new CommandResult(ERROR_INVALID_DAY);
        } catch (InvalidModeException ime) {
            return new CommandResult(ERROR_INVALID_MODE);
        } catch (AssertionError ae) {
            logger.log(Level.INFO, "Assertion Error");
            return new CommandResult(ae.getMessage());
        }
    }

    /**
     * Try to parse the delete command to see if index has been done.
     */
    public Command prepareDelete() {
        try {
            String[] eventDescription = parserArguments.splitArgumentsDeleteCommand(this.getArguments());
            parserHelperMethods.checkNonNullValues(eventDescription,HEADINGS_DELETE_EVENT.length);

            String name = eventDescription[0];
            int index = Integer.parseInt(eventDescription[1]);
            return new DeleteCommand(name,index);
        } catch (ArrayIndexOutOfBoundsException | NullPointerException npe) {
            return new CommandResult(ERROR_MISSING_PARAMETERS_DELETE);
        } catch (MissingValueException mve) {
            return new CommandResult(ERROR_MISSING_VALUES_DELETE);
        } catch (NumberFormatException nfe) {
            return new CommandResult(ERROR_INDEX_OUT_OF_BOUND);
        } catch (AssertionError ae) {
            logger.log(Level.INFO, "Assertion Error");
            return new CommandResult(ae.getMessage());
        }
    }

    public Command prepareAddMeeting() {
        try {
            String[] eventDescription = parserArguments.splitArgumentsAddMeeting(this.getArguments());
            parserHelperMethods.checkNonNullValues(eventDescription,HEADINGS_ADD_MEETING.length - 1);

            //there is no name for meeting because meeting applies to everyone
            String day = eventDescription[DAY_INDEX - 1].toLowerCase();
            int startTime = Integer.parseInt(eventDescription[START_TIME_INDEX - 1]);
            int endTime = Integer.parseInt(eventDescription[END_TIME_INDEX - 1]);
            String mode = eventDescription[MODE_INDEX - 1].toLowerCase();

            parserHelperMethods.checkDay(day);
            parserHelperMethods.checkTime(startTime, endTime);
            parserHelperMethods.checkMode(mode);

            String title = eventDescription[TITLE_INDEX - 1];
            return new AddMeetingCommand(title, day, startTime, endTime, mode);

        } catch (ArrayIndexOutOfBoundsException | NullPointerException npe) {
            return new CommandResult(ERROR_MISSING_PARAMETERS_ADD_MEETING);
        } catch (MissingValueException mve) {
            return new CommandResult(ERROR_MISSING_VALUES_ADD_MEETING);
        } catch (InvalidTimeException | NumberFormatException ite) {
            return new CommandResult(ERROR_INVALID_TIME);
        } catch (InvalidDayException ide) {
            return new CommandResult(ERROR_INVALID_DAY);
        } catch (InvalidModeException ime) {
            return new CommandResult(ERROR_INVALID_MODE);
        } catch (AssertionError ae) {
            logger.log(Level.INFO, "Assertion Error");
            return new CommandResult(ae.getMessage());
        }
    }


}
