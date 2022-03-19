package seedu.duke.commands;

import seedu.duke.tasks.Module;
import seedu.duke.tasks.ModuleList;
import seedu.duke.util.StringConstants;

public class ListCommand extends Command {
    private static final String LIST_MESSAGE = StringConstants.LIST_MESSAGE_TOP + LS + "%s";
    private String argument;

    public String getArgument() {
        return argument;
    }

    public ListCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Lists all tasks when no argument is provided. Otherwise, list only tasks with matching tag.
     */
    @Override
    public CommandResult execute(ModuleList moduleList) {
        StringBuilder res = new StringBuilder();
        if (argument.isEmpty()) {
            for (Module m : moduleList.getModuleList()) {
                res.append(m.printModuleTaskList()).append(LS);
            }
            res.append(moduleList.getGeneralTasks().printModuleTaskList());
        } else {
            for (Module m : moduleList.getModuleList()) {
                res.append(m.printModuleTaskListWithTag(argument)).append(LS);
            }
            res.append(moduleList.getGeneralTasks().printModuleTaskListWithTag(argument));
        }
        return new CommandResult(String.format(LIST_MESSAGE, res));
    }

}
