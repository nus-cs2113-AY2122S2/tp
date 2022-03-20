package seedu.mindmymoney.helper;

import seedu.mindmymoney.MindMyMoneyException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TimeFunctions {
    public static final String TEMPORARY_DAY = "-01";

    public static String convertTime(String inputTime) throws MindMyMoneyException {
        if (inputTime == null) {
            throw new MindMyMoneyException("Time cannot be null!");
        }
        try {
            LocalDate date;
            inputTime = inputTime + TEMPORARY_DAY;
            date = LocalDate.parse(inputTime);
            return date.format(DateTimeFormatter.ofPattern("MMM yyyy"));
        } catch (DateTimeException e) {
            throw new MindMyMoneyException("Input the correct date time format!");
        }
    }
}
