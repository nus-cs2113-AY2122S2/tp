package seedu.duke.helper;

import java.util.ArrayList;

public class Parser {

    public static ArrayList<String> parseViewPatient(String description) {
        //if no NRIC
        if (description == null) {
            return null;
        }

        //if NRIC
        ArrayList<String> parameters = new ArrayList<String>();
        parameters.add(description);
        return parameters;
    }

    public static String[] commandParser(String userInput) {
        return userInput.trim().split("/info");
    }
}
