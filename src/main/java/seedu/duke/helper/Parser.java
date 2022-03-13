package seedu.duke.helper;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
        for (int i = 0; i < parametersArray.length; i++) {
            parametersArray[i] = parametersArray[i].trim();
        }
        if (parametersArray.length == 7 && validateNric(parametersArray[0])
                && validateFullName(parametersArray[1]) && validateAge(parametersArray[2])
                && validateGender(parametersArray[3]) && validateAddress(parametersArray[4])
                && validateDob(parametersArray[5]) && validateAdmissionDate(parametersArray[6])) {
            return parametersArray;
        } else {
            return null;
        }
    }

    public static String[] parseAddMedicine(String parameters) {
        String[] medicineParameters = parameters.trim().split(",");
        if (medicineParameters.length ==  5 && validateMedicine(medicineParameters)) {
            return medicineParameters;
        } else {
            return null;
        }
    }

    private static boolean validateNric(String nric) {
        Pattern nricPattern = Pattern.compile("[A-Z][0-9]{7}[A-Z]");
        Matcher nricMatcher = nricPattern.matcher(nric);
        //System.out.println(nric + " " +nricMatcher.matches());
        return nricMatcher.matches();
    }

    private static boolean validateFullName(String fullName) {
        Pattern fullNamePattern = Pattern.compile("([a-zA-Z]+( [a-zA-Z]+)+)");
        Matcher fullNameMatcher = fullNamePattern.matcher(fullName);
        //System.out.println(fullName + " " + fullNameMatcher.matches());
        return fullNameMatcher.matches();
    }

    private static boolean validateAge(String ageString) {
        int age = Integer.parseInt(ageString);
        if (1 <= age && age <= 120) {
            //System.out.println("Age true");
            return true;
        } else {
            //System.out.println("Age false;");
            return false;
        }
    }

    private static boolean validateGender(String gender) {
        Pattern genderPattern = Pattern.compile("M|F");
        Matcher genderMatcher = genderPattern.matcher(gender);
        //System.out.println(gender + " " + genderMatcher.matches());
        return genderMatcher.matches();
    }

    private static boolean validateAddress(String address) {
        Pattern addressPattern = Pattern.compile("[\\w\\-\\s'()]*");
        Matcher addressMatcher = addressPattern.matcher(address);
        //System.out.println(address + " " + addressMatcher.matches());
        return addressMatcher.matches();
    }

    private static boolean validateDob(String dobString) {
        LocalDate dob = LocalDate.parse(dobString);
        LocalDate today = LocalDate.now();
        LocalDate dobLimit = LocalDate.parse("1900-01-01");
        if (dob.isAfter(dobLimit) && dob.isBefore(today)) {
            //System.out.println("dob true");
            return true;
        } else {
            //System.out.println("dob false");
            return false;
        }
    }

    private static boolean validateAdmissionDate(String admissionDateString) {
        LocalDate admissionDate = LocalDate.parse(admissionDateString);
        LocalDate today = LocalDate.now();
        LocalDate admissionDateLimit = LocalDate.parse("1980-01-01");
        if (admissionDate.isAfter(admissionDateLimit) && admissionDate.isBefore(today)) {
            //System.out.println("Admission true");
            return true;
        } else {
            //System.out.println("Admission false");
            return false;
        }
    }

    private static boolean validateMedicineName(String medicineName) {
        return medicineName.matches("[a-zA-z]+");
    }

    private static boolean validateDosage(String dosage) {
        try {
            int dosageInt = Integer.parseInt(dosage);
            return dosageInt > 0;
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
    }

    private static boolean validateExpiry(String expiry) {
        try {
            LocalDate expiryDate = LocalDate.parse(expiry);
            LocalDate minimumDate = LocalDate.now().plusMonths(6);
            if (expiryDate.isBefore(minimumDate)) {
                return false;
            }
            return true;
        } catch (DateTimeParseException dateTimeParseException) {
            return false;
        }
    }

    private static  boolean validateQuantity(String quantity) {
        try {
            int quantityInt = Integer.parseInt(quantity);
            return quantityInt > 0;
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
    }

    private static boolean validateMedicine(String[] parameters) {
        boolean check = true;
        for (int i = 0; i < 5; i++) {
            switch (i) {
            case 0:
                check = validateMedicineName(parameters[i]);
                break;
            case 1:
                check = check && validateDosage(parameters[i]);
                break;
            case 2:
                check = check && validateExpiry(parameters[i]);
                break;
            case 4:
                check = check && validateQuantity(parameters[i]);
                break;
            default:
                break;
            }
        }
        return check;
    }













}
