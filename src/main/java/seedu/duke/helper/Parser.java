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
        

        return null;
    }

    private static boolean validateNric(String nric) {
        Pattern nricPattern = Pattern.compile("[A-Z][0-9]{7}[A-Z]");
        Matcher nricMatcher = nricPattern.matcher(nric);
        return nricMatcher.matches();
    }

    private static boolean validateFullName(String fullName) {
        Pattern fullNamePattern = Pattern.compile("([a-zA-Z]+( [a-zA-Z]+)+)");
        Matcher fullNameMatcher = fullNamePattern.matcher(fullName);
        return fullNameMatcher.matches();
    }

    private static boolean validateAge(String ageString) {
        int age = Integer.parseInt(ageString);
        if (1 <= age && age <= 120) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean validateGender(String gender) {
        Pattern genderPattern = Pattern.compile("M|F");
        Matcher genderMatcher = genderPattern.matcher(gender);
        return genderMatcher.matches();
    }

    private static boolean validateAddress(String address) {
        Pattern addressPattern = Pattern.compile("[\\w\\-\\s'()]*");
        Matcher addressMatcher = addressPattern.matcher(address);
        return addressMatcher.matches();
    }

    private static boolean validateDob(String dobString) {
        LocalDate dob = LocalDate.parse(dobString);
        LocalDate today = LocalDate.now();
        LocalDate dobLimit = LocalDate.parse("1900-01-01");
        if (dob.isAfter(dobLimit) && dob.isBefore(today)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean validateAdmissionDate(String admissionDateString) {
        LocalDate admissionDate = LocalDate.parse(admissionDateString);
        LocalDate today = LocalDate.now();
        LocalDate admissionDateLimit = LocalDate.parse("1980-01-01");
        if (admissionDate.isAfter(admissionDateLimit) && admissionDate.isBefore(today)) {
            return true;
        } else {
            return false;
        }
    }
}
