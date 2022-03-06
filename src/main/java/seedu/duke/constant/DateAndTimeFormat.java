package seedu.duke.constant;

import java.time.format.DateTimeFormatter;

import static seedu.duke.constant.Messages.DATE_FORMAT_WITHOUT_TIME;
import static seedu.duke.constant.Messages.DATE_FORMAT_WITH_TIME;

public class DateAndTimeFormat {

    public static final DateTimeFormatter savedTaskWithTimeFormat = DateTimeFormatter.ofPattern(DATE_FORMAT_WITH_TIME);
    public static final DateTimeFormatter savedTaskNoTimeFormat = DateTimeFormatter.ofPattern(DATE_FORMAT_WITHOUT_TIME);
    public static final DateTimeFormatter withTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
    public static final DateTimeFormatter noTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
}
