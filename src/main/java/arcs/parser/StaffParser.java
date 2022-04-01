package arcs.parser;

import arcs.commands.Command;
import arcs.commands.UndefinedCommand;
import arcs.commands.staff.AddStaffCommand;
import arcs.commands.staff.DeleteStaffCommand;

public class StaffParser {
    public static Command prepareAddStaffCommand(String argumentLine) {
        if (argumentLine == null || argumentLine.isEmpty()) {
            return new AddStaffCommand(null, null, null, null,null,null);
        }

        int idIdx = argumentLine.indexOf("id/");
        int passwordIdx = argumentLine.indexOf("pw/");
        int nameIdx = argumentLine.indexOf("n/");
        int jobIdx = argumentLine.indexOf("j/");
        int phoneIdx = argumentLine.indexOf("p/");
        int emailIdx = argumentLine.indexOf("e/");

        String id = parseField(argumentLine, idIdx, passwordIdx,nameIdx,jobIdx, phoneIdx, emailIdx, 3);
        String pw = parseField(argumentLine, passwordIdx, idIdx, nameIdx, jobIdx,phoneIdx, emailIdx, 3);
        String name = parseField(argumentLine, nameIdx, idIdx, passwordIdx, jobIdx, phoneIdx, emailIdx, 2);
        String job = parseField(argumentLine, jobIdx, idIdx, passwordIdx,nameIdx, phoneIdx, emailIdx, 2);
        String phone = parseField(argumentLine, phoneIdx, idIdx,passwordIdx, nameIdx, jobIdx,emailIdx, 2);
        String email = parseField(argumentLine, emailIdx, idIdx, passwordIdx,nameIdx, jobIdx, phoneIdx, 2);

        return new AddStaffCommand(id, pw, name, job, phone, email);
    }

    private static String parseField(String argumentLine, int startIdx,
                                     int fieldA, int fieldB, int fieldC, int fieldD, int fieldE, int offset) {
        String value = null;
        if (startIdx != -1) {
            int idEndIdx = argumentLine.length();
            if (fieldA > startIdx && fieldA < idEndIdx) {
                idEndIdx = fieldA;
            }
            if (fieldB > startIdx && fieldB < idEndIdx) {
                idEndIdx = fieldB;
            }
            if (fieldC > startIdx && fieldC < idEndIdx) {
                idEndIdx = fieldC;
            }
            if (fieldD > startIdx && fieldD < idEndIdx) {
                idEndIdx = fieldD;
            }
            if (fieldE > startIdx && fieldE < idEndIdx) {
                idEndIdx = fieldE;
            }
            value = argumentLine.substring(startIdx + offset, idEndIdx);
            value = value.trim();
        }
        return value;
    }


    public static Command prepareDeleteStaffCommand(String argumentLine) {
        if (argumentLine == null || argumentLine.isEmpty()) {
            return new UndefinedCommand("Index is not specified");
        }
        Command result;
        try {
            int index = Integer.parseInt(argumentLine);
            result = new DeleteStaffCommand(index);
        } catch (NumberFormatException e) {
            result = new UndefinedCommand("Index should be an integer.");
        }

        return result;
    }

}
