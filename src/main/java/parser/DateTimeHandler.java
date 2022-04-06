package parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles date and time conversion between <code>LocalDateTime</code> and String.
 */
public class DateTimeHandler {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Creates <code>LocalDate</code> object from String input.
     *
     * @param dateString String input of format "dd/mm/yyyy".
     * @return LocalDate object.
     * @throws DateTimeParseException invalid format is used.
     */
    public static LocalDate dateParse(String dateString) throws DateTimeParseException {
        return LocalDate.parse(dateString, formatter);
    }

    /**
     * Creates String from <code>LocalDate</code> object.
     *
     * @param date LocalDate object.
     * @return String of format "dd/mm/yyyy".
     */
    public static String toString(LocalDate date) {
        return date.format(formatter);
    }
}
