package seedu.duke.helper;

import seedu.duke.exception.HalpmiException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    /* Validating person attributes */

    private static void validateNric(String nric) throws HalpmiException {
        Pattern nricPattern = Pattern.compile("[A-Z][0-9]{7}[A-Z]");
        Matcher nricMatcher = nricPattern.matcher(nric);
        if (!nricMatcher.matches()) {
            throw new HalpmiException("NRIC must start with a capital letter, "
                    + "followed by 7 digits and end with a capital letter.");
        }
    }

    private static void validateFullName(String fullName) throws HalpmiException {
        Pattern fullNamePattern = Pattern.compile("[a-zA-Z ]*");
        Matcher fullNameMatcher = fullNamePattern.matcher(fullName);
        if (!fullNameMatcher.matches()) {
            throw new HalpmiException("Full name must contain only alphabets and no special characters.");
        }
    }

    private static void validateAge(String ageString) throws HalpmiException {
        int age;
        try {
            age = Integer.parseInt(ageString);
        } catch (NumberFormatException numberFormatException) {
            throw new HalpmiException("Please enter a number!");
        }
        //age must be within 1 and 120
        if (!(1 <= age && age <= 120)) {
            throw new HalpmiException("Age must be between 1 and 120 inclusive.");
        }
    }

    private static void validateGender(String gender) throws HalpmiException {
        Pattern genderPattern = Pattern.compile("M|F");
        Matcher genderMatcher = genderPattern.matcher(gender);
        if (!genderMatcher.matches()) {
            throw new HalpmiException("Gender must be a single character: M or F.");
        }
    }

    private static void validateAddress(String address) throws HalpmiException {
        Pattern addressPattern = Pattern.compile("[\\w\\-\\s'()#]*");
        Matcher addressMatcher = addressPattern.matcher(address);
        if (!addressMatcher.matches()) {
            throw new HalpmiException("Address must be alphanumeric. "
                    + "Only these specific special characters are allowed: ' ( ) #");
        }
    }

    private static void validateDob(String dobString) throws HalpmiException {
        LocalDate dob;
        try {
            dob = LocalDate.parse(dobString);
        } catch (DateTimeParseException dateTimeParseException) {
            throw new HalpmiException("Date of birth must be in YYYY-MM-DD format. "
                    + "It cannot be before 1900-01-01 or be today and after.");
        }
        LocalDate today = LocalDate.now();
        LocalDate dobLimit = LocalDate.parse("1900-01-01");
        // dob is within the range of 1900 - today
        if (!(dob.isAfter(dobLimit) && dob.isBefore(today))) {
            throw new HalpmiException("Date of birth must be in YYYY-MM-DD format. "
                    + "It cannot be before 1900-01-01 or be today and after.");
        }
    }

    // todo : admission date logic (w respect to dob)
    private static void validateAdmissionDate(String admissionDateString) throws HalpmiException {
        LocalDate admissionDate;
        try {
            admissionDate = LocalDate.parse(admissionDateString);
        } catch (DateTimeParseException dateTimeParseException) {
            throw new HalpmiException("Date of birth must be in YYYY-MM-DD format. "
                    + "It cannot be before 1900-01-01 or be today and after.");
        }
        LocalDate today = LocalDate.now();
        LocalDate admissionDateLimit = LocalDate.parse("1980-01-01");

        // admission date is after 1980 and before today
        if (!(admissionDate.isAfter(admissionDateLimit) && admissionDate.isBefore(today))) {
            throw new HalpmiException("Date of birth must be in YYYY-MM-DD format. "
                    + "It cannot be before 1900-01-01 or be today and after.");
        }
    }

    /* Validating person */
    private static void validateAddPerson(String[] parameters) throws HalpmiException {
        validateNric(parameters[0]);
        validateFullName(parameters[1]);
        validateAge(parameters[2]);
        validateGender(parameters[3]);
        validateAddress(parameters[4]);
        validateDob(parameters[5]);
    }

    private static void validateSpecialization(String fullName) throws HalpmiException {
        Pattern fullNamePattern = Pattern.compile("[a-zA-Z ]*");
        Matcher fullNameMatcher = fullNamePattern.matcher(fullName);
        if (!fullNameMatcher.matches()) {
            throw new HalpmiException("Specialization must contain only alphabets and no special characters.");
        }
    }

    static void validateAddDoctor(String[] parameters) throws HalpmiException {
        validateAddPerson(Arrays.copyOfRange(parameters, 0, 6));
        validateSpecialization(parameters[6]);
    }

    static void validateAddPatient(String[] parameters) throws HalpmiException {
        validateAddPerson(Arrays.copyOfRange(parameters, 0, 6));
        validateAdmissionDate(parameters[6]);
    }

    /* Validate medicine attributes */
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

    private static boolean validateQuantity(String quantity) {
        try {
            int quantityInt = Integer.parseInt(quantity);
            return quantityInt > 0;
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
    }

    /* Validate medicine */
    public static void validateMedicine(String[] parameters) throws HalpmiException{
        assert parameters.length == 6 : "Validate failed to check parameter length";
        boolean check = true;
        for (int i = 0; i < 5; i++) {
            switch (i) {
            case 1:
                check = validateMedicineName(parameters[i]);
                break;
            case 2:
                check = check && validateDosage(parameters[i]);
                break;
            case 3:
                check = check && validateExpiry(parameters[i]);
                break;
            case 5:
                check = check && validateQuantity(parameters[i]);
                break;
            default:
                break;
            }
        }
        if (!check) {
            throw new HalpmiException("Some Parameters are invalid!");
        }
    }

    /* Validate appointment */
    private static void validateAppointmentDetails(String appointmentDetails) throws HalpmiException {
        if (appointmentDetails.isBlank() || appointmentDetails.isEmpty()) {
            throw new HalpmiException("Appointment details cannot be empty. Please indicate some details.");
        }
    }

    private static void validateDate(String inputDate, String type) throws HalpmiException {
        LocalDate newDate;
        try {
            newDate = LocalDate.parse(inputDate);
        } catch (DateTimeParseException dateTimeParseException) {
            throw new HalpmiException("Date must be in YYYY-MM-DD format. "
                    + "It cannot be before 1900-01-01 or be today and after.");
        }
        LocalDate today = LocalDate.now();
        LocalDate admissionDateLimit = LocalDate.parse("1980-01-01");
        boolean isTrue = false;
        if (type.equals("appointment") && newDate.isAfter(today)) {
            isTrue = true;
        } else if (type.equals("patient") && newDate.isAfter(admissionDateLimit) && newDate.isBefore(today)) {
            isTrue = true;
        }
        if (!isTrue) {
            throw new HalpmiException("Date must be in YYYY-MM-DD format. "
                    + "It cannot be before 1900-01-01 or be today and after.");
        }
    }

    public static void validateAddAppointment(String[] parameters) throws HalpmiException {
        validateNric(parameters[0]);
        validateFullName(parameters[1]);
        validateNric(parameters[2]);
        validateFullName(parameters[3]);
        validateDate(parameters[4], "appointment");
        validateAppointmentDetails(parameters[5]);

    }
}
