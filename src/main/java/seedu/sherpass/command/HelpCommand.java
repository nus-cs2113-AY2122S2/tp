package seedu.sherpass.command;

import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import seedu.sherpass.task.TaskList;

import static seedu.sherpass.constant.Message.HELP_MESSAGE_QUICK_START_COMMAND;
import static seedu.sherpass.constant.Message.HELP_MESSAGE_SPECIFIC_COMMAND;
import static seedu.sherpass.constant.CommandMessage.COMMAND_WORD_ADD;
import static seedu.sherpass.constant.CommandMessage.COMMAND_WORD_CLEAR;
import static seedu.sherpass.constant.CommandMessage.COMMAND_WORD_DELETE;
import static seedu.sherpass.constant.CommandMessage.COMMAND_WORD_EDIT;
import static seedu.sherpass.constant.CommandMessage.COMMAND_WORD_EXIT;
import static seedu.sherpass.constant.CommandMessage.COMMAND_WORD_HELP_VARIANT;
import static seedu.sherpass.constant.CommandMessage.COMMAND_WORD_SHOW;
import static seedu.sherpass.constant.CommandMessage.COMMAND_WORD_MARK;
import static seedu.sherpass.constant.CommandMessage.COMMAND_WORD_UNMARK;
import static seedu.sherpass.constant.CommandMessage.COMMAND_WORD_STUDY;
import static seedu.sherpass.constant.CommandMessage.MESSAGE_USAGE_ADD;
import static seedu.sherpass.constant.CommandMessage.MESSAGE_USAGE_CLEAR;
import static seedu.sherpass.constant.CommandMessage.MESSAGE_USAGE_DELETE;
import static seedu.sherpass.constant.CommandMessage.MESSAGE_USAGE_MARK;
import static seedu.sherpass.constant.CommandMessage.MESSAGE_USAGE_UNMARK;
import static seedu.sherpass.constant.CommandMessage.MESSAGE_USAGE_EDIT;
import static seedu.sherpass.constant.CommandMessage.MESSAGE_USAGE_SHOW;
import static seedu.sherpass.constant.CommandMessage.MESSAGE_USAGE_STUDY;
import static seedu.sherpass.constant.CommandMessage.MESSAGE_USAGE_EXIT;


public class HelpCommand extends Command {

    private String helpInput;

    /**
     * Creates a constructor for the HelpCommand. Accepts the help input.
     * Help input contains a keyword to obtain detailed information on
     * available commands to use.
     *
     * @param filteredHelpInput Help keyword.
     */
    public HelpCommand(String filteredHelpInput) {
        helpInput = filteredHelpInput.trim().toLowerCase();
    }

    private void showHelpList(Ui ui) {
        System.out.println("Here is a list of commands available:");
        String[] helpCommands = {COMMAND_WORD_MARK, COMMAND_WORD_UNMARK,
            COMMAND_WORD_ADD, COMMAND_WORD_SHOW,
            COMMAND_WORD_EDIT, COMMAND_WORD_DELETE, COMMAND_WORD_CLEAR,
            COMMAND_WORD_STUDY, COMMAND_WORD_EXIT, };
        for (String helpCommand : helpCommands) {
            ui.showLine();
            showSpecificHelpCommand(ui, helpCommand);
        }
        ui.showLine();
        System.out.println("For a quick summary of what commands to execute, \n"
                + "enter 'help quick start'.");
    }


    private void showSpecificHelpCommand(Ui ui, String helpCommand) {
        switch (helpCommand) {
        case COMMAND_WORD_MARK:
            ui.showToUser(MESSAGE_USAGE_MARK);
            break;
        case COMMAND_WORD_UNMARK:
            ui.showToUser(MESSAGE_USAGE_UNMARK);
            break;
        case COMMAND_WORD_ADD:
            ui.showToUser(MESSAGE_USAGE_ADD);
            break;
        case COMMAND_WORD_DELETE:
            ui.showToUser(MESSAGE_USAGE_DELETE);
            break;
        case COMMAND_WORD_CLEAR:
            ui.showToUser(MESSAGE_USAGE_CLEAR);
            break;
        case COMMAND_WORD_SHOW:
            ui.showToUser(MESSAGE_USAGE_SHOW);
            break;
        case COMMAND_WORD_STUDY:
            ui.showToUser(MESSAGE_USAGE_STUDY);
            break;
        case COMMAND_WORD_EXIT:
            ui.showToUser(MESSAGE_USAGE_EXIT);
            break;
        case COMMAND_WORD_EDIT:
            ui.showToUser(MESSAGE_USAGE_EDIT);
            break;
        case COMMAND_WORD_HELP_VARIANT:
            ui.showToUser(HELP_MESSAGE_QUICK_START_COMMAND + HELP_MESSAGE_SPECIFIC_COMMAND);
            break;
        default:
            showHelpList(ui);
        }
    }

    /**
     * Executes the help command. Checks for the search keyword
     * in the help command to list detailed information of available
     * commands according to what the keyword is looking for.
     *
     * @param taskList Task array.
     * @param ui       Ui for printing.
     * @param storage  Storage for overwriting/appending save data. Not used in this method.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        showSpecificHelpCommand(ui, helpInput);
    }
}

