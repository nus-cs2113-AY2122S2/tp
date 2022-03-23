package seedu.duke.helper;

import seedu.duke.exception.HalpmiException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    /* Validating person attributes */

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
        //age must be within 1 and 120
        return 1 <= age && age <= 120;
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
        // dob is within the range of 1900 - today
        return dob.isAfter(dobLimit) && dob.isBefore(today);
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

        // admission date is after 1980 and before today
        return admissionDate.isAfter(admissionDateLimit) && admissionDate.isBefore(today);
    }

    /* Validating person */
    private static void validateAddPerson(String[] parameters) throws HalpmiException {
        if (!validateNric(parameters[0])) {
            throw new HalpmiException("NRIC must start with a capital letter, "
                    + "followed by 7 digits and end with a capital letter.");
        }
        if (!validateFullName(parameters[1])) {
            throw new HalpmiException("Full name must contain only alphabets and no special characters.");

        }
        if (!validateAge(parameters[2])) {
            throw new HalpmiException("Age must be between 1 and 120 inclusive.");

        }
        if (!validateGender(parameters[3])) {
            throw new HalpmiException("Gender must be a single character: M or F.");

        }
        if (!validateAddress(parameters[4])) {
            throw new HalpmiException("Address must be alphanumeric. "
                    + "Only these specific special characters are allowed: ' ( ) #");

        }
        if (!validateDob(parameters[5])) {
            throw new HalpmiException("Date of birth must be in YYYY-MM-DD format. "
                    + "It cannot be before 1900-01-01 or be today and after.");

        }

    }

    static void validateAddDoctor(String[] parameters) throws HalpmiException {
       validateAddPerson(Arrays.copyOfRange(parameters, 0, 6));
        //validate full name cause specialization is also just a name
        if (!validateFullName(parameters[6])) {
            throw new HalpmiException("Specialization must be a name");
        }
    }

    static void validateAddPatient(String[] parameters) throws HalpmiException {
    validateAddPerson(Arrays.copyOfRange(parameters, 0, 6));
        if (!validateAdmissionDate(parameters[6])) {
            throw new HalpmiException("Date of birth must be in YYYY-MM-DD format. "
                    + "It cannot be before 1980-01-01 or be today and after.");
        }
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
    public static boolean validateMedicine(String[] parameters) {
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

    /* Validate appointment */
    
    private static boolean validateAppointmentDetails(String appointmentDetails) {
        if (appointmentDetails.isBlank() || appointmentDetails.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean validateDate(String inputDate, String type) {
        LocalDate newDate;
        try {
            newDate = LocalDate.parse(inputDate);
        } catch (DateTimeParseException dateTimeParseException) {
            return false;
        }
        LocalDate today = LocalDate.now();
        LocalDate admissionDateLimit = LocalDate.parse("1980-01-01");
        if (type.equals("appointment") && newDate.isAfter(today)) {
            return true;
        } else if (type.equals("patient") && newDate.isAfter(admissionDateLimit) && newDate.isBefore(today)) {
            return true;
        } else {
            return false;
        }
    }

    public static void validateAddAppointment(String[] parameters) throws HalpmiException {
        if (!validateNric(parameters[0])) {
            throw new HalpmiException("Patient NRIC must start with a capital letter, " + "followed by 7 digits and end with a capital letter.");

        }
        if (!validateFullName(parameters[1])) {
            throw new HalpmiException("Patient name must contain only alphabets and no special characters.");
        }
        if (!validateNric(parameters[2])) {
            throw new HalpmiException("Doctor NRIC must start with a capital letter, "
                    + "followed by 7 digits and end with a capital letter.");
        }
        if (!validateFullName(parameters[3])) {
            throw new HalpmiException("Doctor name must contain only alphabets and no special characters.");
        }
        if (!validateDate(parameters[4], "appointment")) {
            throw new HalpmiException("Date of birth must be in YYYY-MM-DD format."
                    + "It cannot be today or before.");
        }
        if (!validateAppointmentDetails(parameters[5])) {
            throw new HalpmiException("Appointment details cannot be empty. Please indicate some details.");

        }
    }
}
