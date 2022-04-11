package parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles date and time conversion between <code>LocalDateTime</code> and String.
 */
public class DateTimeHandler {
    private static DateTimeFormatter DATE_FORMAT_SLASH = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static DateTimeFormatter DATE_FORMAT_DASH = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static DateTimeFormatter DATE_FORMAT_PERIOD = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /**
     * Creates <code>LocalDate</code> object from String input.
     *
     * @param dateString String input of format "dd/mm/yyyy".
     * @return LocalDate object.
     * @throws DateTimeParseException invalid format is used.
     */
    public static LocalDate dateParse(String dateString) throws DateTimeParseException {
        if (dateString.contains("/")) {
            return LocalDate.parse(dateString, DATE_FORMAT_SLASH);
        } else if (dateString.contains("-")) {
            return LocalDate.parse(dateString, DATE_FORMAT_DASH);
        } else if (dateString.contains(".")) {
            return LocalDate.parse(dateString, DATE_FORMAT_PERIOD);
        }
        return null;
    }

    /**
     * Creates String from <code>LocalDate</code> object.
     *
     * @param date LocalDate object.
     * @return String of format "dd/mm/yyyy".
     */
    public static String toString(LocalDate date) {
        return date.format(DATE_FORMAT_DASH);
    }
}
