package seedu.sherpass.timetable;

import seedu.sherpass.task.Task;
import seedu.sherpass.util.Ui;

import java.util.ArrayList;

import static seedu.sherpass.constant.TimetableConstant.BLANK_MARK_STATUS;
import static seedu.sherpass.constant.TimetableConstant.BLANK_TIME_PERIOD;
import static seedu.sherpass.constant.TimetableConstant.EMPTY_TIMETABLE_SIZE;
import static seedu.sherpass.constant.TimetableConstant.STRING_COMPARE_OFFSET;
import static seedu.sherpass.constant.TimetableConstant.TIMETABLE_SIZE_OFFSET_ONE;
import static seedu.sherpass.constant.TimetableConstant.TIMETABLE_SIZE_OFFSET_TWO;

public class TimetablePrinting {
    protected static void printEmptyTimetable(Ui ui, String day, String date, long partitionLength) {
        ui.showToUser(ui.getRepeatedCharacters("-", partitionLength));
        ui.showToUser("|  Day       |  Time         |  Mark status |  Task Description    |  To complete by  |");
        String thirdRow = "|  " + day + "       |             Your schedule is empty for the day!";
        ui.showToUser(thirdRow + ui.getRepeatedCharacters(" ",
                partitionLength - thirdRow.length() - 1) + "|");
        String fourthRow = "| " + date + " |";
        ui.showToUser(fourthRow + ui.getRepeatedCharacters(" ",
                partitionLength - fourthRow.length() - 1) + "|");
        ui.showToUser(ui.getRepeatedCharacters("-", partitionLength));
    }

    protected static void printTimetable(String day, String date, ArrayList<Task> filteredTasks,
                                         Ui ui, long taskLength, long byDateLength, long partitionLength) {
        int j = 0;
        String colOne;
        String colTwo = "Time";
        String colThree = "Mark Status";
        String colFour = "Task Description";
        String colFive = "To complete by";
        int loopNumber = (filteredTasks.size() > EMPTY_TIMETABLE_SIZE)
                ? filteredTasks.size() + TIMETABLE_SIZE_OFFSET_ONE
                : filteredTasks.size() + TIMETABLE_SIZE_OFFSET_TWO;
        for (int i = 0; i < loopNumber; i++) {
            if ((i == 0) || (i == loopNumber - 1)) {
                ui.showToUser(ui.getRepeatedCharacters("-", partitionLength));
                continue;
            }

            colOne = (i == 1) ? "Day" : ((i == 2) ? day : date);
            if (i >= 2) {
                colTwo = (j < filteredTasks.size()) ? filteredTasks.get(j).getTimePeriod() : BLANK_TIME_PERIOD;
                colThree = (j < filteredTasks.size()) ? filteredTasks.get(j).getStatusIcon() : BLANK_MARK_STATUS;
                colFour = (j < filteredTasks.size())
                        ? (filteredTasks.get(j).getIndex() + ". " + filteredTasks.get(j).getDescription())
                        : ui.getRepeatedCharacters(" ", taskLength - STRING_COMPARE_OFFSET);
                colFive = (j < filteredTasks.size()) ? filteredTasks.get(j).getByDateString()
                        : ui.getRepeatedCharacters(" ", byDateLength - STRING_COMPARE_OFFSET);
                j++;
            }
            printRow(colOne, colTwo, colThree, colFour, colFive, taskLength, byDateLength, i, ui);
        }
    }

    private static void printRow(String colOne, String colTwo, String colThree,
                                 String colFour, String colFive,
                                 long taskLength, long doOnDateLength, int rowNumber, Ui ui) {
        String taskColumnBackWhiteSpace = ui.getRepeatedCharacters(" ",
                TimetableLogic.calculateColBackWhiteSpace(taskLength, colFour));
        String doOnDateColumnBackWhiteSpace = ui.getRepeatedCharacters(" ",
                TimetableLogic.calculateColBackWhiteSpace(doOnDateLength, colFive));
        if (rowNumber == 1) {
            ui.showToUser("|  " + colOne + "       |  " + colTwo + "         |  "
                    + colThree + " |  " + colFour + taskColumnBackWhiteSpace
                    + "|  " + colFive + doOnDateColumnBackWhiteSpace + "|");
        } else if (rowNumber == 2) {
            ui.showToUser("|  " + colOne + "       | " + colTwo + " |      "
                    + colThree + "       |  " + colFour + taskColumnBackWhiteSpace
                    + "|  " + colFive + doOnDateColumnBackWhiteSpace + "|");
        } else if (rowNumber == 3) {
            ui.showToUser("| " + colOne + " | " + colTwo + " |      " + colThree
                    + "       |  " + colFour + taskColumnBackWhiteSpace + "|  "
                    + colFive + doOnDateColumnBackWhiteSpace + "|");
        } else {
            ui.showToUser("|            | " + colTwo + " |      " + colThree
                    + "       |  " + colFour + taskColumnBackWhiteSpace + "|  "
                    + colFive + doOnDateColumnBackWhiteSpace + "|");
        }
    }
}
