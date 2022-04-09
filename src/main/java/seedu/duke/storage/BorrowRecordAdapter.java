package seedu.duke.storage;

import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import seedu.duke.data.BorrowRecord;
import seedu.duke.exceptions.InvMgrException;
import seedu.duke.parser.ParserUtils;

import java.io.IOException;
import java.time.LocalDate;

public class BorrowRecordAdapter extends TypeAdapter<BorrowRecord> {

    private static final String BORROWER_NAME_FIELD = "borrowerName";
    private static final String END_DATE_FIELD = "endDate";
    private static final String START_DATE_FIELD = "startDate";
    private static final String QUANTITY_FIELD = "quantity";
    private static final String IS_RETURNED_FIELD = "isReturned";

    @Override
    public BorrowRecord read(JsonReader reader) throws IOException {
        String startDateString = null;
        String endDateString = null;
        String borrowerName = null;
        Integer quantity = null;
        Boolean isReturned = null;

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
                borrowerName = reader.nextString();
            }
            if (QUANTITY_FIELD.equals(fieldname)) {
                quantity = reader.nextInt();
            }
            if (IS_RETURNED_FIELD.equals(fieldname)) {
                isReturned = reader.nextBoolean();
            }
        }
        reader.endObject();

        LocalDate startDate;
        LocalDate endDate;
        try {
            endDate = ParserUtils.parseDate(endDateString);
            startDate = ParserUtils.parseDate(startDateString);
        } catch (InvMgrException e) {
            throw new JsonParseException(e.getMessage());
        }
        BorrowRecord br1 = new BorrowRecord(quantity, startDate, endDate, borrowerName);
        br1.setReturnStatus(isReturned);
        return br1;
    }

    @Override
    public void write(JsonWriter writer, BorrowRecord borrowRecord) throws IOException {
        writer.beginObject();
        writer.name("quantity").value(borrowRecord.getQuantity());
        writer.name("startDate").value(borrowRecord.getStartDate().format(ParserUtils.DATE_FORMAT));
        writer.name("endDate").value(borrowRecord.getEndDate().format(ParserUtils.DATE_FORMAT));
        writer.name("borrowerName").value(borrowRecord.getBorrowerName());
        writer.name("isReturned").value(borrowRecord.getReturnStatus());
        writer.endObject();
    }

}
