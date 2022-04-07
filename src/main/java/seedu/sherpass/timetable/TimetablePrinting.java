package seedu.sherpass.timetable;

import seedu.sherpass.task.Task;
import seedu.sherpass.util.Ui;

import java.util.ArrayList;

import static seedu.sherpass.constant.Message.WHITESPACE;
import static seedu.sherpass.constant.TimetableConstant.BLANK_MARK_STATUS;
import static seedu.sherpass.constant.TimetableConstant.BLANK_TIME_PERIOD;
import static seedu.sherpass.constant.TimetableConstant.EMPTY_TIMETABLE_SIZE;
import static seedu.sherpass.constant.TimetableConstant.STRING_COMPARE_OFFSET;
import static seedu.sherpass.constant.TimetableConstant.TASK_MARK_STATUS_SPACE_LENGTH;
import static seedu.sherpass.constant.TimetableConstant.TIMETABLE_SIZE_OFFSET_ONE;
import static seedu.sherpass.constant.TimetableConstant.TIMETABLE_SIZE_OFFSET_TWO;
import static seedu.sherpass.constant.TimetableConstant.WHITE_SPACE_FRONT_OFFSET_LENGTH_ONE;

public class TimetablePrinting {
    protected static void printEmptyFullTimetable(Ui ui, String day, String date, long partitionLength) {
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

    protected static void printFullTimetable(String day, String date, ArrayList<Task> filteredTasks,
                                             Ui ui, long taskDescriptionLength,
                                             long byDateLength, long partitionLength) {
        String colTwo = "Time";
        String colThree = "Mark Status";
        String colFour = "Task Description";
        String colFive = "To complete by";
        int arrayIndex = 0;
        int loopNumber = (filteredTasks.size() > EMPTY_TIMETABLE_SIZE)
                ? filteredTasks.size() + TIMETABLE_SIZE_OFFSET_ONE : filteredTasks.size() + TIMETABLE_SIZE_OFFSET_TWO;
        for (int rowNumber = 0; rowNumber < loopNumber; rowNumber++) {
            if ((rowNumber == 0) || (rowNumber == loopNumber - 1)) {
                ui.showToUser(ui.getRepeatedCharacters("-", partitionLength));
                continue;
            }

            String colOne = (rowNumber == 1) ? "Day" : ((rowNumber == 2) ? day : date);
            if (rowNumber >= 2) {
                colTwo = (arrayIndex < filteredTasks.size())
                        ? filteredTasks.get(arrayIndex).getDoOnDateTimePeriod() : BLANK_TIME_PERIOD;
                colThree = (arrayIndex < filteredTasks.size())
                        ? filteredTasks.get(arrayIndex).getStatusIcon() : BLANK_MARK_STATUS;
                colFour = (arrayIndex < filteredTasks.size())
                        ? (filteredTasks.get(arrayIndex).getIndex() + ". "
                        + filteredTasks.get(arrayIndex).getDescription())
                        : ui.getRepeatedCharacters(" ", taskDescriptionLength - STRING_COMPARE_OFFSET);
                colFive = (arrayIndex < filteredTasks.size()) ? filteredTasks.get(arrayIndex).getByDateString()
                        : ui.getRepeatedCharacters(" ", byDateLength - STRING_COMPARE_OFFSET);
                arrayIndex++;
            }
            printFullTimetableRow(colOne, colTwo, colThree, colFour, colFive,
                    taskDescriptionLength, byDateLength, rowNumber, ui);
        }
    }

    private static void printFullTimetableRow(String colOne, String colTwo, String colThree,
                                              String colFour, String colFive,
                                              long taskDescriptionLength, long byDateLength, int rowNumber, Ui ui) {
        String taskDescriptionColumnBackWhiteSpace = ui.getRepeatedCharacters(WHITESPACE,
                TimetableLogic.calculateColBackWhiteSpace(taskDescriptionLength,
                        colFour, WHITE_SPACE_FRONT_OFFSET_LENGTH_ONE));
        String byDateColumnBackWhiteSpace = ui.getRepeatedCharacters(WHITESPACE,
                TimetableLogic.calculateColBackWhiteSpace(byDateLength, colFive, WHITE_SPACE_FRONT_OFFSET_LENGTH_ONE));
        if (rowNumber == 1) {
            ui.showToUser("|  " + colOne + "       |  " + colTwo + "         |  "
                    + colThree + " |  " + colFour + taskDescriptionColumnBackWhiteSpace
                    + "|  " + colFive + byDateColumnBackWhiteSpace + "|");
        } else if (rowNumber == 2) {
            ui.showToUser("|  " + colOne + "       | " + colTwo + " |      "
                    + colThree + "       |  " + colFour + taskDescriptionColumnBackWhiteSpace
                    + "|  " + colFive + byDateColumnBackWhiteSpace + "|");
        } else if (rowNumber == 3) {
            ui.showToUser("| " + colOne + " | " + colTwo + " |      " + colThree
                    + "       |  " + colFour + taskDescriptionColumnBackWhiteSpace + "|  "
                    + colFive + byDateColumnBackWhiteSpace + "|");
        } else {
            ui.showToUser("|            | " + colTwo + " |      " + colThree
                    + "       |  " + colFour + taskDescriptionColumnBackWhiteSpace + "|  "
                    + colFive + byDateColumnBackWhiteSpace + "|");
        }
    }

    protected static void printEmptyCondensedTimetable(Ui ui) {
        ui.showToUser("-------------------------------------------------------------"
                + "----------------------------------------\n"
                + "|  Task Number  |  Mark Status  |  Task Description     |  To do/attend on     "
                + "|  To complete by    |\n"
                + "|                                  Your task List is empty!                  "
                + "                       |\n" + "-------------------------------------------------------"
                + "----------------------------------------------");
    }

    protected static void printCondensedTimetable(ArrayList<Task> filteredTasks, Ui ui,
                                                  long taskDescriptionLength, long byDateLength, long doOnDateLength,
                                                  long taskNumberLength, long partitionLength) {
        int arrayIndex = 0;
        for (int rowNumber = 0; rowNumber < filteredTasks.size() + TIMETABLE_SIZE_OFFSET_ONE; rowNumber++) {
            if ((rowNumber == 0) || (rowNumber == filteredTasks.size() + TIMETABLE_SIZE_OFFSET_ONE - 1)) {
                ui.showToUser(ui.getRepeatedCharacters("-", partitionLength));
                continue;
            }

            String colOne = (rowNumber >= 2 && arrayIndex < filteredTasks.size())
                    ? String.valueOf(filteredTasks.get(arrayIndex).getIndex()) : "Task Number";
            String colTwo = (rowNumber >= 2 && arrayIndex < filteredTasks.size())
                    ? filteredTasks.get(arrayIndex).getStatusIcon() : "Mark Status";
            String colThree = (rowNumber >= 2 && arrayIndex < filteredTasks.size())
                    ? filteredTasks.get(arrayIndex).getDescription() : "Task Description";
            String colFour = (rowNumber >= 2 && arrayIndex < filteredTasks.size())
                    ? filteredTasks.get(arrayIndex).getDoOnDateString() : "To do/attend on";
            String colFive = (rowNumber >= 2 && arrayIndex < filteredTasks.size())
                    ? filteredTasks.get(arrayIndex).getByDateString() : "To complete by";
            arrayIndex = (rowNumber >= 2) ? arrayIndex + 1 : arrayIndex;

            printCondensedTimetableRow(colOne, colTwo, colThree, colFour, colFive,
                    taskDescriptionLength, byDateLength, doOnDateLength, taskNumberLength, rowNumber, ui);
        }
    }



    private static void printCondensedTimetableRow(String colOne, String colTwo, String colThree,
                                                   String colFour, String colFive,
                                                   long taskDescriptionLength, long byDateLength,
                                                   long doOnDateLength, long taskNumberLength,
                                                   int rowNumber, Ui ui) {
        String taskNumberColumnBackWhiteSpace = ui.getRepeatedCharacters(WHITESPACE,
                TimetableLogic.calculateColBackWhiteSpace(taskNumberLength,
                        colOne, TimetableLogic.calcOffset(colOne.equals("Task Number"))));
        String taskMarkStatusColumnBackWhiteSpace = ui.getRepeatedCharacters(WHITESPACE,
                TimetableLogic.calculateColBackWhiteSpace(TASK_MARK_STATUS_SPACE_LENGTH,
                        colTwo, TimetableLogic.calcOffset(colTwo.equals(WHITESPACE) || colTwo.equals("X"))));
        String taskDescriptionColumnBackWhiteSpace = ui.getRepeatedCharacters(WHITESPACE,
                TimetableLogic.calculateColBackWhiteSpace(taskDescriptionLength,
                        colThree, WHITE_SPACE_FRONT_OFFSET_LENGTH_ONE));
        String taskDoOnDateColumnBackWhiteSpace = ui.getRepeatedCharacters(WHITESPACE,
                TimetableLogic.calculateColBackWhiteSpace(doOnDateLength,
                        colFour, WHITE_SPACE_FRONT_OFFSET_LENGTH_ONE));
        String taskByDateColumnBackWhiteSpace = ui.getRepeatedCharacters(WHITESPACE,
                TimetableLogic.calculateColBackWhiteSpace(byDateLength, colFive, WHITE_SPACE_FRONT_OFFSET_LENGTH_ONE));
        if (rowNumber == 1) {
            ui.showToUser("|  " + colOne + taskNumberColumnBackWhiteSpace + "|  "
                    + colTwo + taskMarkStatusColumnBackWhiteSpace + "|  " + colThree
                    + taskDescriptionColumnBackWhiteSpace + "|  " + colFour + taskDoOnDateColumnBackWhiteSpace
                    + "|  " + colFive + taskByDateColumnBackWhiteSpace + "|");
        } else {
            ui.showToUser("|      " + colOne + taskNumberColumnBackWhiteSpace + "|      "
                    + colTwo + taskMarkStatusColumnBackWhiteSpace + "|  " + colThree
                    + taskDescriptionColumnBackWhiteSpace + "|  " + colFour + taskDoOnDateColumnBackWhiteSpace
                    + "|  " + colFive + taskByDateColumnBackWhiteSpace + "|");
        }
    }
}
