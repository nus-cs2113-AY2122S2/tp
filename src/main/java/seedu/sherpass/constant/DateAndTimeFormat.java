package seedu.sherpass.constant;

import java.time.format.DateTimeFormatter;

public class DateAndTimeFormat {
    public static final DateTimeFormatter parseFormat = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");
    public static final DateTimeFormatter outputWithTimeFormat = DateTimeFormatter.ofPattern("EEE, dd/MM/yyyy HH:mm");
    public static final DateTimeFormatter outputWithoutTimeFormat = DateTimeFormatter.ofPattern("EEE, dd/MM/yyyy");
}
