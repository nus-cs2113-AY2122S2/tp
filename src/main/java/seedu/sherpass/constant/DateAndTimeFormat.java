package seedu.sherpass.constant;

import java.time.format.DateTimeFormatter;

public class DateAndTimeFormat {
    public static final DateTimeFormatter inputWithoutTimeFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
    public static final DateTimeFormatter inputWithTimeFormat = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");
    public static final DateTimeFormatter outputWithTimeFormat = DateTimeFormatter.ofPattern("EEE, dd/MM/yyyy HH:mm");
    public static final DateTimeFormatter outputWithoutTimeFormat = DateTimeFormatter.ofPattern("EEE, dd/MM/yyyy");
    public static final DateTimeFormatter dateOnlyFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter dayOnlyFormat = DateTimeFormatter.ofPattern("EEE");
}
