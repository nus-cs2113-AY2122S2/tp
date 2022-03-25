package seedu.duke.commands;

import java.util.Objects;

import seedu.duke.data.Module;
import seedu.duke.data.ModuleList;
import seedu.duke.util.Configuration;
import seedu.duke.util.StringConstants;

public class ListCommand extends Command {
    private static final String LIST_MESSAGE = StringConstants.LIST_MESSAGE;
    private final String argument;

    public String getArgument() {
        return argument;
    }

    public ListCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Lists all tasks when no argument is provided. Otherwise, list only tasks with matching tag.
     * Depending on config settings, completed tasks may be hidden from the output.
     */
    @Override
    public CommandResult execute(ModuleList moduleList, Configuration configuration) {
        boolean showCompletedTasks = Boolean.parseBoolean(configuration.getConfigurationValue(
                Configuration.ConfigurationGroup.COMPLETED_TASKS_SHOWN));
        StringBuilder res = new StringBuilder();
        if (Objects.isNull(argument)) {
            for (Module m : moduleList.getModuleList()) {
                res.append(m.printModuleTaskList(showCompletedTasks)).append(LS);
            }
            res.append(moduleList.getGeneralTasks().printModuleTaskList(showCompletedTasks));
        } else {
            for (Module m : moduleList.getModuleList()) {
                res.append(m.printModuleTaskListWithTag(argument, showCompletedTasks)).append(LS);
            }
            res.append(moduleList.getGeneralTasks().printModuleTaskListWithTag(argument, showCompletedTasks));
        }
        return new CommandResult(String.format(LIST_MESSAGE, res));
    }

}
