package seedu.sherpass.constant;

import java.time.format.DateTimeFormatter;

public class DateAndTimeFormat {
    public static final DateTimeFormatter parseFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
    public static final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
    public static final DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("EEE, dd/MM/yyyy");
}
