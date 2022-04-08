package seedu.storage;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;

class LocalDateAdapter extends TypeAdapter<LocalDate> {
    /**
     * Write a LocalDate to a JsonWriter.
     * Source: https://stackoverflow.com/questions/39192945/serialize-java-8-localdate-as-yyyy-mm-dd-with-gson
     * @param jsonWriter the JsonWriter to write to.
     * @param localDate the LocalDate to write.
     * @throws IOException if an error occurs while writing.
     */
    @Override
    public void write(final JsonWriter jsonWriter, final LocalDate localDate) throws IOException {
        if (localDate == null) {
            jsonWriter.nullValue();
        } else {
            jsonWriter.value(localDate.toString());
        }
    }

    /**
     * Read a LocalDate from a JsonReader.
     * Source: https://stackoverflow.com/questions/39192945/serialize-java-8-localdate-as-yyyy-mm-dd-with-gson
     * @param jsonReader the JsonReader to read from.
     * @return the LocalDate read.
     * @throws IOException if an error occurs while reading.
     */
    @Override
    public LocalDate read(final JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        } else {
            return LocalDate.parse(jsonReader.nextString());
        }
    }
}
