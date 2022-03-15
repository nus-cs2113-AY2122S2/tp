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
        String[] addPatientParameters = parameters.split(",");
        for (int i = 0; i < addPatientParameters.length; i++) {
            addPatientParameters[i] = addPatientParameters[i].trim();
        }
        if (addPatientParameters.length != 7) {
            System.out.println("There is one or more parameters missing.");
            return null;
        }
        if (validateAddPatient(addPatientParameters)) {
            return addPatientParameters;
        } else {
            return null;
        }
    }

    public static ArrayList<String> parseViewDoctor(String description) {
        //no specific doctor
        if (description == null) {
            return null;
        }

        //if specific doctor
        ArrayList<String> parameters = new ArrayList<String>();
        parameters.add(description);
        return parameters;
    }

    public static String[] parseAddMedicine(String parameters) {
        String[] medicineParameters = parameters.trim().split(",");
        if (medicineParameters.length == 5 && validateMedicine(medicineParameters)) {
            return medicineParameters;
        } else {
            return null;
        }
    }

    private static boolean validateAddPatient(String[] parameters) {
        boolean isValid = true;
        if (!validateNric(parameters[0])) {
            System.out.println("NRIC must start with a capital letter, "
                    + "followed by 7 digits and end with a capital letter.");
            isValid = false;
        }
        if (!validateFullName(parameters[1])) {
            System.out.println("Full name must contain only alphabets and no special characters.");
            isValid = false;
        }
        if (!validateAge(parameters[2])) {
            System.out.println("Age must be between 1 and 120 inclusive.");
            isValid = false;
        }
        if (!validateGender(parameters[3])) {
            System.out.println("Gender must be a single character: M or F.");
            isValid = false;
        }
        if (!validateAddress(parameters[4])) {
            System.out.println("Address must be alphanumeric. "
                    + "Only these specific special characters are allowed: ' ( ) #");
            isValid = false;
        }
        if (!validateDob(parameters[5])) {
            System.out.println("Date of birth must be in YYYY-MM-DD format. "
                    + "It cannot be before 1900-01-01 or be today and after.");
            isValid = false;
        }
        if (!validateAdmissionDate(parameters[6])) {
            System.out.println("Date of birth must be in YYYY-MM-DD format. "
                    + "It cannot be before 1980-01-01 or be today and after.");
            isValid = false;
        }
        return isValid;
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

    private static boolean validateNric(String nric) {
        Pattern nricPattern = Pattern.compile("[A-Z][0-9]{7}[A-Z]");
        Matcher nricMatcher = nricPattern.matcher(nric);
        return nricMatcher.matches();
    }

    private static boolean validateFullName(String fullName) {
        Pattern fullNamePattern = Pattern.compile("[a-zA-Z ]*");
        Matcher fullNameMatcher = fullNamePattern.matcher(fullName);
        return fullNameMatcher.matches();
    }

    private static boolean validateAge(String ageString) {
        int age;
        try {
            age = Integer.parseInt(ageString);
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
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
        Pattern addressPattern = Pattern.compile("[\\w\\-\\s'()#]*");
        Matcher addressMatcher = addressPattern.matcher(address);
        return addressMatcher.matches();
    }

    private static boolean validateDob(String dobString) {
        LocalDate dob;
        try {
            dob = LocalDate.parse(dobString);
        } catch (DateTimeParseException dateTimeParseException) {
            return false;
        }
        LocalDate today = LocalDate.now();
        LocalDate dobLimit = LocalDate.parse("1900-01-01");
        if (dob.isAfter(dobLimit) && dob.isBefore(today)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean validateAdmissionDate(String admissionDateString) {
        LocalDate admissionDate;
        try {
            admissionDate = LocalDate.parse(admissionDateString);
        } catch (DateTimeParseException dateTimeParseException) {
            return false;
        }
        LocalDate today = LocalDate.now();
        LocalDate admissionDateLimit = LocalDate.parse("1980-01-01");
        if (admissionDate.isAfter(admissionDateLimit) && admissionDate.isBefore(today)) {
            return true;
        } else {
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

}