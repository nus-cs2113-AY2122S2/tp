package seedu.sherpass.constant;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class DateAndTimeFormat {
    public static final DateTimeFormatter inputWithoutTimeFormat = DateTimeFormatter.ofPattern("d/M/uuuu");
    public static final DateTimeFormatter inputWithTimeFormat = DateTimeFormatter.ofPattern("d/M/uuuu HH:mm");
    public static final DateTimeFormatter inputTimeIndependentFormat = new DateTimeFormatterBuilder()
            .appendPattern("d/M/uuuu")
            .optionalStart()
            .appendPattern(" HH:mm")
            .optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();
    public static final DateTimeFormatter outputWithTimeFormat = DateTimeFormatter.ofPattern("EEE, dd/MM/uuuu HH:mm");
    public static final DateTimeFormatter outputWithoutTimeFormat = DateTimeFormatter.ofPattern("EEE, dd/MM/uuuu");
    public static final DateTimeFormatter inputDateOnlyFormat = DateTimeFormatter.ofPattern("d/M/uuuu");
    public static final DateTimeFormatter outputDateOnlyFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    public static final DateTimeFormatter dayOnlyFormat = DateTimeFormatter.ofPattern("EEE");
    public static final DateTimeFormatter timeOnlyFormat = DateTimeFormatter.ofPattern("HH:mm");
}
