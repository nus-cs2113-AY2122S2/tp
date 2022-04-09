package seedu.duke.storage;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import seedu.duke.data.BorrowRecord;
import seedu.duke.data.BorrowStatus;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BorrowRecordAdapter extends TypeAdapter<LocalDate> {

    private static final String BORROWER_NAME_FIELD = "borrowerName";
    private static final String END_DATE_FIELD = "endDate";
    private static final String START_DATE_FIELD = "startDate";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public BorrowRecord read(JsonReader reader) throws IOException {
        String startDateString = null;
        String endDateString = null;
        String borrowerNameString = null;

        reader.beginObject();
        String fieldname = null;
        while (reader.hasNext()) {
            JsonToken token = reader.peek();
            if (token.equals(JsonToken.NAME)) {
                fieldname = reader.nextName();
            }

            if (START_DATE_FIELD.equals(fieldname)) {
                startDateString = reader.nextString();
            }
            if (END_DATE_FIELD.equals(fieldname)) {
                endDateString = reader.nextString();
            }
            if (BORROWER_NAME_FIELD.equals(fieldname)) {
                borrowerNameString = reader.nextString();
            }
        }
        reader.endObject();

        LocalDate currentDate = LocalDate.now();
        LocalDate endDate = LocalDate.parse(endDateString,DATE_FORMAT);
        LocalDate startDate = LocalDate.parse(startDateString,DATE_FORMAT);
        if (currentDate.isAfter(endDate)) {
            return new BorrowRecord(startDate, endDate, borrowerNameString, BorrowStatus.FUTURE);
        }
        if (currentDate.isBefore(startDate)) {
            return new BorrowRecord(startDate, endDate, borrowerNameString, BorrowStatus.PAST);
        }
        return new BorrowRecord(startDate, endDate, borrowerNameString, BorrowStatus.PRESENT);
    }

    @Override
    public void write(JsonWriter writer, BorrowRecord borrowRecord) throws IOException {
        writer.beginObject();
        writer.name("startDate").value(borrowRecord.getStartDate().format(DATE_FORMAT));
        writer.name("endDate").value(borrowRecord.getEndDate().format(DATE_FORMAT));
        writer.name("borrowerName").value(borrowRecord.getBorrowerName());
        writer.endObject();
    }

}
