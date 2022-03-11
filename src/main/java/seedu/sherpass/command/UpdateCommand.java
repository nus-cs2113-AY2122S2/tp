package seedu.sherpass.command;

import seedu.sherpass.util.Storage;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Ui;

import static seedu.sherpass.constant.Message.ERROR_SYSTEM_FAULT_MESSAGE;

public class UpdateCommand extends Command {
    String taskDescriptionForSearchingTask;
    String toUpdateTaskDate;

    /**
     * Creates constructor for UpdateCommand class.
     * Saves task description and date.
     *
     * @param taskDescription Task Description to search for.
     * @param dateInput       Task Date to replace existing task date.
     */
    public UpdateCommand(String taskDescription, String dateInput) {
        taskDescriptionForSearchingTask = taskDescription;
        toUpdateTaskDate = dateInput;
    }

    private boolean isTaskBeingReplaced(Ui ui) {
        boolean isOldTaskReplaced = false;
        ui.showToUser("Oops! It seems that you've already added this task.\n"
                + "Would you like to override the\nexisting time and/or date "
                + "with the new input? [Y/N]");
        ui.showLine();
        while (true) {
            String input = ui.readCommand();
            ui.showLine();
            if (input.trim().equalsIgnoreCase("Y")
                    || input.trim().equalsIgnoreCase("Yes")) {
                isOldTaskReplaced = true;
                ui.showToUser("Understood. Proceeding to change"
                        + "\nthe old task with the new one..........");
                break;
            }
            if (input.trim().equalsIgnoreCase("N")
                    || input.trim().equalsIgnoreCase("No")) {
                ui.showToUser("Okay, we'll keep it as it is.");
                break;
            }
            ui.showToUser("Please confirm your choice with either Y (Yes) or N (No).");
            ui.showLine();
        }
        return isOldTaskReplaced;
    }

    /**
     * Executes replacement of task date with new date input in
     * an existing task.
     *
     * @param taskList Task array
     * @param ui       Ui for printing messages.
     * @param storage  Storage for overwriting save data after replacing task date with new input.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (isTaskBeingReplaced(ui)) {
            int taskIndexToReplace = taskList.findIndexToReplace(taskDescriptionForSearchingTask);
            if (taskIndexToReplace == -1) {
                System.out.println(ERROR_SYSTEM_FAULT_MESSAGE);
                return;
            }
            taskList.replaceTaskDate(taskIndexToReplace, toUpdateTaskDate, ui);
            storage.writeSaveData(taskList);
        }
    }
}
