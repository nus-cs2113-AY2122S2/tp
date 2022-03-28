package arcs.parser;

import arcs.commands.Command;
import arcs.commands.flightbooking.BookCommand;

public class FlightBookingParser {
    public static Command prepareBookCommand(String argumentLine) {
        if (argumentLine == null || argumentLine.isEmpty()) {
            return new BookCommand(null, null);
        }
        String ic = parseField("ic/", "fid/", argumentLine);
        String fid = parseField("fid/", "ic/", argumentLine);
        if (ic != null) {
            ic = ic.trim();
        }
        if (fid != null) {
            fid = fid.trim();
        }

        return new BookCommand(ic, fid);
    }

    private static String parseField(String field, String field2, String argumentLine) {
        int startIdx = argumentLine.indexOf(field);
        if (startIdx == -1) {
            return null;
        }
        int endIdx = argumentLine.length();
        int field2Idx = argumentLine.indexOf(field2);
        if (field2Idx > startIdx && field2Idx < endIdx) {
            endIdx = field2Idx;
        }

        return argumentLine.substring(startIdx + field.length(), endIdx);
    }
}
