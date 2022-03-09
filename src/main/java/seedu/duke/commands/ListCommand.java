package seedu.duke.commands;

import seedu.duke.tasks.Module;
import seedu.duke.tasks.ModuleList;

public class ListCommand extends Command {
    private static final String LIST_MESSAGE = "Ok! Here are the task(s) in your list:" + LS + "%s";

    /**
     * Lists all tasks.
     */
    @Override
    public CommandResult execute(ModuleList moduleList) {
        String res = "";
        for (Module m : moduleList.getModuleList()) {
            res += m.printModuleTaskList() + LS;
        }
        res += moduleList.getGeneralTasks().printModuleTaskList();
        return new CommandResult(String.format(LIST_MESSAGE, res));
    }
}
