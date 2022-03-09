package seedu.duke.commands;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.tasks.Module;
import seedu.duke.tasks.ModuleList;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.TaskList;

import java.util.ArrayList;

public class ListCommand extends Command {

    private static final String LIST_COMMAND_WORD = "list";
    private static final String LIST_MESSAGE = "Ok! Here are the task(s) in your list:" + LS + "%s";

    public ListCommand() {
        commandName = LIST_COMMAND_WORD;
    }

    @Override
    public CommandResult execute(ModuleList moduleList) throws ModHappyException {
        String res = "";
        for (Module m : moduleList.getModuleList()) {
            res += m.printModuleTaskList() + LS;
        }
        res += moduleList.getGeneralTasks().printModuleTaskList();
        return new CommandResult(String.format(LIST_MESSAGE, res));
    }
}
