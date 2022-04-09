package seedu.duke.storage;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateAdapter extends TypeAdapter<LocalDate> {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate read(JsonReader reader) throws IOException {
        JsonToken token = reader.peek();
        String dateString = null;
        if (token.equals(JsonToken.STRING)) {
            dateString = reader.nextString();
        }
        if (dateString == null) {
            return null;
        }
        return LocalDate.parse(dateString, DATE_FORMAT);
    }

    @Override
    public void write(JsonWriter writer, LocalDate date) throws IOException {
        String printDate = date.format(DATE_FORMAT);
        writer.value(printDate);
    }
}
