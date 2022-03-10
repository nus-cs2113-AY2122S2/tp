package seedu.duke.helper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static String[] parseAddPatient(String parameters) {
        // Currently the basic input order should be to just follow the order
        // in Patient class: nric, fullName, age, gender, address, dob, dateAdmission
        String[] parametersArray = parameters.split(",");
        for (String parameter: parametersArray) {
            parameter = parameter.trim();
        }

        Pattern nricPattern = Pattern.compile("[A-Z][0-9]{7}[A-Z]");
        Matcher nricMatcher = nricPattern.matcher(parametersArray[0]);

        Pattern fullNamePattern = Pattern.compile("([a-zA-Z]+( [a-zA-Z]+)+)");
        Matcher fullNameMatcher = fullNamePattern.matcher(parametersArray[1]);

        int age = Integer.parseInt(parametersArray[2]);

        Pattern genderPattern = Pattern.compile("M|F");
        Matcher genderMatcher = genderPattern.matcher(parametersArray[3]);

        Pattern addressPattern = Pattern.compile("[\\w\\-\\s'()]*");
        Matcher addressMatcher = addressPattern.matcher(parametersArray[4]);

        LocalDate dob = LocalDate.parse(parametersArray[5]);

        LocalDate dateAdmission = LocalDate.parse(parametersArray[6]);

        return null;
    }
}
