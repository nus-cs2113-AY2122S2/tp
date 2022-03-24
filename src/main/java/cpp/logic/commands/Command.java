package cpp.logic.commands;

import cpp.exceptions.IllegalCommandException;
import cpp.model.ProjectList;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    public abstract String execute(ProjectList projectList);

}
