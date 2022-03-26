package seedu.sherpass.util.parser;

import org.json.JSONException;
import org.json.JSONObject;
import seedu.sherpass.enums.Frequency;
import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static seedu.sherpass.constant.DateAndTimeFormat.inputWithTimeFormat;

public class StorageParser {

    /**
     * Returns a task object parsed from the data file.
     *
     * @param taskData The data of a task in JSON.
     * @return Task containing the saved data for adding into program's task array.
     * @throws InvalidInputException If saved data is missing content, i.e. task description or date.
     */
    public static Task parseSaveData(JSONObject taskData) throws InvalidInputException {
        Task parsedTask;
        try {
            int identifier = taskData.getInt("identifier");
            String description = taskData.getString("description");
            String byDateString = taskData.getString("by_date");
            String doOnStartDateString = taskData.getString("do_date_start");
            String doOnEndDateString = taskData.getString("do_date_end");
            String frequencyString = taskData.getString("frequency");
            int index = taskData.getInt("index");

            Frequency repeatFrequency = frequencyString.isBlank()
                    ? null : Frequency.valueOf(frequencyString);
            LocalDateTime byDate = (byDateString.isBlank()
                    ? null : LocalDateTime.parse(byDateString, inputWithTimeFormat));
            LocalDateTime doOnStartDateTime = LocalDateTime.parse(doOnStartDateString, inputWithTimeFormat);
            LocalDateTime doOnEndDateTime = LocalDateTime.parse(doOnEndDateString, inputWithTimeFormat);


            parsedTask = new Task(identifier, description, byDate, doOnStartDateTime,
                    doOnEndDateTime, repeatFrequency, index);
            String status = taskData.getString("status");
            if (status.equals("X")) {
                parsedTask.markAsDone();
            }
            return parsedTask;
        } catch (JSONException | DateTimeParseException | NumberFormatException exception) {
            throw new InvalidInputException(exception.getMessage());
        }
    }
}
