package seedu.duke.command;

public class RoomHelper {
    public static boolean isValidIntNumber(String numberStr) {
        if (numberStr == null) {
            return false;
        }
        try {
            int num = Integer.parseInt(numberStr);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


}
