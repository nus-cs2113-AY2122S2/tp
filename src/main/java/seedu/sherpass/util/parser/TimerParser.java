package seedu.sherpass.util.parser;

import seedu.sherpass.exception.InvalidTimeException;
import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;
import seedu.sherpass.timer.TimerLogic;

import java.io.IOException;

import static seedu.sherpass.constant.CommandParameter.TIMER_CUSTOM_DELIMITER;
import static seedu.sherpass.constant.Index.INDEX_CUSTOM_COMMAND;
import static seedu.sherpass.constant.Index.INDEX_CUSTOM_TIMER;
import static seedu.sherpass.constant.Index.INDEX_DEFAULT_TIMER;
import static seedu.sherpass.constant.Index.DEFAULT_TIMER_ONE;
import static seedu.sherpass.constant.Index.DEFAULT_TIMER_THREE;
import static seedu.sherpass.constant.Index.DEFAULT_TIMER_TWO;
import static seedu.sherpass.constant.Index.DEFAULT_TIMER_ZERO;
import static seedu.sherpass.constant.Index.INDEX_STUDY_COMMAND;
import static seedu.sherpass.constant.Index.INDEX_TIME_FORMAT;
import static seedu.sherpass.constant.Message.ERROR_INVALID_STUDY_INPUT_MESSAGE;

public class TimerParser {

    /**
     * Parses the default timer modes.
     *
     * @param defaultTimerChoice Mode number.
     * @return Returns the duration of the timer mode selected in seconds.
     * @throws InvalidTimeException If defaultTimerChoice does not match
     *                              with the given choices.
     */
    public static int selectDefaultTimer(String defaultTimerChoice) throws InvalidTimeException {
        switch (defaultTimerChoice) {
        case "0":
            return DEFAULT_TIMER_ZERO;
        case "1":
            return DEFAULT_TIMER_ONE;
        case "2":
            return DEFAULT_TIMER_TWO;
        case "3":
            return DEFAULT_TIMER_THREE;
        default:
            throw new InvalidTimeException();
        }
    }

    private static boolean isValidDuration(int duration) {
        return duration > 0;
    }

    /**
     * Parses input to the timer.
     *
     * @param parsedInput Parsed input.
     * @return Returns duration of custom timer input, or the duration of
     *         selected default timer mode in seconds.
     * @throws InvalidTimeException If timer input less than or equals to 0 or there is
     *                              multiple timer inputs.
     */
    public static int parseTimerInput(String[] parsedInput) throws InvalidTimeException {
        if (parsedInput[INDEX_TIME_FORMAT].trim().contains(TIMER_CUSTOM_DELIMITER)) {
            if (parsedInput[INDEX_TIME_FORMAT].trim().indexOf(TIMER_CUSTOM_DELIMITER) != INDEX_CUSTOM_COMMAND) {
                throw new InvalidTimeException();
            }
            String[] customTimerInput = parsedInput[INDEX_TIME_FORMAT].split(TIMER_CUSTOM_DELIMITER, 2);
            int customDuration = Integer.parseInt(customTimerInput[INDEX_CUSTOM_TIMER].trim());
            if (!isValidDuration(customDuration)) {
                throw new InvalidTimeException();
            }
            return customDuration;
        }
        return selectDefaultTimer(parsedInput[INDEX_DEFAULT_TIMER].trim());
    }

    /**
     * Parses commands for study mode.
     *
     * @param rawUserInput Raw user input.
     * @param ui           UI.
     * @param timerLogic Logic class to handle timer functions
     */
    public static void parseStudyMode(Ui ui, Storage storage, String rawUserInput,
                                      TimerLogic timerLogic) throws IOException {
        String[] parsedInput = rawUserInput.trim().split(" ", 2);
        switch (parsedInput[INDEX_STUDY_COMMAND].trim().toLowerCase()) {
        case "start":
            timerLogic.callStartTimer(parsedInput);
            break;
        case "pause":
            timerLogic.callPauseTimer();
            break;
        case "resume":
            timerLogic.callResumeTimer();
            break;
        case "stop":
            timerLogic.callStopTimer();
            break;
        case "mark":
            //Fallthrough
        case "unmark":
            //Fallthrough
        case "show":
            timerLogic.markOrShowTask(storage, rawUserInput);
            break;
        default:
            ui.showToUser(ERROR_INVALID_STUDY_INPUT_MESSAGE);
        }
    }
}
