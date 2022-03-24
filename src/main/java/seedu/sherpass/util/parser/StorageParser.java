package seedu.sherpass.util.parser;

import org.json.JSONException;
import org.json.JSONObject;
import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static seedu.sherpass.constant.DateAndTimeFormat.inputFormat;

public class StorageParser {

    /**
     * Returns a task object parsed from the data file.
     *
     * @param taskData The data of a task in JSON.
     * @return Task containing the saved data for adding into program's task array.
     * @throws InvalidInputException If saved data is missing content, i.e. task description or date.
     */
    public static Task parseSavedData(JSONObject taskData) throws InvalidInputException {
        Task parsedTask;
        try {
            String description = taskData.getString("description");
            String byDateString = taskData.getString("by_date");
            String doOnDateString = taskData.getString("do_date");
            LocalDate byDate = null;
            LocalDate doOnDate = null;
            if (!byDateString.equals("null")) {
                byDate = LocalDate.parse(byDateString, inputFormat);
            }
            if (!doOnDateString.equals("null")) {
                doOnDate = LocalDate.parse(doOnDateString, inputFormat);
            }
            parsedTask = new Task(description, byDate, doOnDate);
            String status = taskData.getString("status");
            if (status.equals("X")) {
                parsedTask.markAsDone();
            }
            return parsedTask;
        } catch (JSONException | DateTimeParseException exception) {
            throw new InvalidInputException(exception.getMessage());
        }
    }
}
