package seedu.duke.helper;

import seedu.duke.exception.UserInputErrorException;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    /* Validating person attributes */
    public static void minParameterCheck(String[] parametersArray, int length) throws UserInputErrorException {
        if (parametersArray.length != length) {
            throw new UserInputErrorException("There is one or more parameters missing");
        }
    }

    public static void validateNric(String nric) throws UserInputErrorException {
        Pattern nricPattern = Pattern.compile("[A-Z][0-9]{7}[A-Z]");
        Matcher nricMatcher = nricPattern.matcher(nric);
        if (!nricMatcher.matches()) {
            throw new UserInputErrorException("NRIC must start with a capital letter, "
                    + "followed by 7 digits and end with a capital letter.");
        }
    }

    private static void validateFullName(String fullName) throws UserInputErrorException {
        Pattern fullNamePattern = Pattern.compile("[a-zA-Z ]*");
        Matcher fullNameMatcher = fullNamePattern.matcher(fullName);
        if (!fullNameMatcher.matches()) {
            throw new UserInputErrorException("Full name must contain only alphabets, "
                    + "no special characters or numbers.");
        }
    }

    private static void validateAge(String ageString) throws UserInputErrorException {
        int age;
        try {
            age = Integer.parseInt(ageString);
        } catch (NumberFormatException numberFormatException) {
            throw new UserInputErrorException("Age must be a positive number!");
        }
        //age must be within 1 and 120
        if (!(1 <= age && age <= 120)) {
            throw new UserInputErrorException("Age must be between 1 and 120 inclusive.");
        }
    }

    private static void validateGender(String gender) throws UserInputErrorException {
        Pattern genderPattern = Pattern.compile("M|F");
        Matcher genderMatcher = genderPattern.matcher(gender);
        if (!genderMatcher.matches()) {
            throw new UserInputErrorException("Gender must be a single character: M or F.");
        }
    }

    private static void validateAddress(String address) throws UserInputErrorException {
        Pattern addressPattern = Pattern.compile("[\\w\\-\\s'()#]*");
        Matcher addressMatcher = addressPattern.matcher(address);
        if (!addressMatcher.matches()) {
            throw new UserInputErrorException("Address must be alphanumeric. "
                    + "Only these specific special characters are allowed: ' ( ) #");
        }
    }

    private static void validateDob(String dobString) throws UserInputErrorException {
        LocalDate dob;
        try {
            dob = LocalDate.parse(dobString);
        } catch (DateTimeParseException dateTimeParseException) {
            throw new UserInputErrorException("Date of birth must be in YYYY-MM-DD format. "
                    + "It cannot be before 1900-01-01 or be today and after.");
        }
        LocalDate today = LocalDate.now();
        LocalDate dobLimit = LocalDate.parse("1900-01-01");
        // dob is within the range of 1900 - today
        if (!(dob.isAfter(dobLimit) && dob.isBefore(today))) {
            throw new UserInputErrorException("Date of birth must be in YYYY-MM-DD format. "
                    + "It cannot be before 1900-01-01 or be today and after.");
        }
    }

    private static void validateAdmissionDate(String admissionDateString) throws UserInputErrorException {
        LocalDate admissionDate;
        try {
            admissionDate = LocalDate.parse(admissionDateString);
        } catch (DateTimeParseException dateTimeParseException) {
            throw new UserInputErrorException("Registration date must be in YYYY-MM-DD format. "
                    + "It cannot be before 1900-01-01 or be today and after.");
        }
        LocalDate today = LocalDate.now();
        LocalDate admissionDateLimit = LocalDate.parse("1980-01-01");


        // admission date is after 1980 and before today
        if (!(admissionDate.isAfter(admissionDateLimit) && admissionDate.isBefore(today))) {
            throw new UserInputErrorException("Registration date must be in YYYY-MM-DD format. "
                    + "It cannot be before 1900-01-01 or be today and after.");
        }
    }

    private static void validateAdmissionDob(String admissionDateString, String dob) throws UserInputErrorException {
        LocalDate admissionDate = LocalDate.parse(admissionDateString);
        LocalDate dateOfBirth = LocalDate.parse(dob);
        if (!(admissionDate.isAfter(dateOfBirth))) {
            throw new UserInputErrorException("Registration date must be after date of birth!");
        }
    }

    private static void validateDobAge(String age, String dob) throws UserInputErrorException {
        validateDob(dob);
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.parse(dob);
        Period period = Period.between(birthday, today);
        int calculatedAge = period.getYears();
        int parsedAge = Integer.parseInt(age);
        if (!(parsedAge == calculatedAge)) {
            throw new UserInputErrorException("Please ensure that the date of birth matches the age provided");
        }

    }

    /* Validating person */
    private static void validateAddPerson(String[] parameters) throws UserInputErrorException {
        validateNric(parameters[0]);
        validateFullName(parameters[1]);
        validateAge(parameters[2]);
        validateGender(parameters[3]);
        validateAddress(parameters[4]);
        validateDob(parameters[5]);
        validateDobAge(parameters[2], parameters[5]);
    }

    private static void validateSpecialization(String specialization) throws UserInputErrorException {
        Pattern fullNamePattern = Pattern.compile("[a-zA-Z ]*");
        Matcher fullNameMatcher = fullNamePattern.matcher(specialization);
        if (!fullNameMatcher.matches()) {
            throw new UserInputErrorException("Specialization must contain only alphabets and no special characters.");
        }
    }


    static void validateAddDoctor(String[] parameters) throws UserInputErrorException {
        minParameterCheck(parameters, 7);
        validateAddPerson(Arrays.copyOfRange(parameters, 0, 6));
        validateSpecialization(parameters[6]);
    }

    static void validateAddPatient(String[] parameters) throws UserInputErrorException {
        minParameterCheck(parameters, 7);
        validateAddPerson(Arrays.copyOfRange(parameters, 0, 6));
        validateAdmissionDate(parameters[6]);
        validateAdmissionDob(parameters[6],  parameters[5]);
    }


    /* Validate medicine attributes */
    private static boolean validateMedicineName(String medicineName) throws UserInputErrorException {
        boolean isValid = medicineName.matches("[a-zA-z ]+");
        if (!isValid) {
            throw new UserInputErrorException("Invalid Medicine name");
        }
        return true;
    }

    private static boolean validateDosage(String dosage) throws UserInputErrorException {
        try {
            int dosageInt = Integer.parseInt(dosage);
            return dosageInt > 0;
        } catch (NumberFormatException numberFormatException) {
            throw new UserInputErrorException("Invalid Medicine dosage");
        }
    }

    private static boolean validateExpiry(String expiry) throws UserInputErrorException {
        try {
            LocalDate expiryDate = LocalDate.parse(expiry);
            LocalDate minimumDate = LocalDate.now();
            if (expiryDate.isBefore(minimumDate)) {
                throw new UserInputErrorException("Medicine expiry date is before today");
            }
            return true;
        } catch (DateTimeParseException dateTimeParseException) {
            throw new UserInputErrorException("Invalid Medicine Expiry");
        }
    }

    private static boolean validateQuantity(String quantity) throws UserInputErrorException {
        try {
            int quantityInt = Integer.parseInt(quantity);
            return quantityInt > 0;
        } catch (NumberFormatException numberFormatException) {
            throw new UserInputErrorException("Invalid Medicine Quantity");
        }
    }

    private static void validateMedicineId(String medicineId) throws UserInputErrorException {
        Pattern fullNamePattern = Pattern.compile("[A-Z][0-9][3}]");
        Matcher fullNameMatcher = fullNamePattern.matcher(medicineId);
        if (!fullNameMatcher.matches()) {
            throw new UserInputErrorException("Medicine must contain only alphabets and Numbers.");
        }
    }

    private static void validateMedicineSideEffects(String sideEffects) throws UserInputErrorException {
        Pattern fullNamePattern = Pattern.compile("[a-zA-Z ]*");
        Matcher fullNameMatcher = fullNamePattern.matcher(sideEffects);
        if (!fullNameMatcher.matches()) {
            throw new UserInputErrorException("Specialization must contain only alphabets and no special characters.");
        }
    }

    /* Validate medicine */
    public static void validateMedicine(String[] parameters) throws UserInputErrorException {
        minParameterCheck(parameters, 6);
        assert parameters.length == 6 : "Validate failed to check parameter length";
        boolean check = true;
        validateMedicineName(parameters[1);
        validateDosage(parameters[2]);
        validateExpiry(parameters[3]);
        validateQuantity(parameters[5]);

    }
    /* Validate appointment */
    private static void validateAppointmentDetails(String appointmentDetails) throws UserInputErrorException {
        if (appointmentDetails.isBlank() || appointmentDetails.isEmpty()) {
            throw new UserInputErrorException("Appointment details cannot be empty. Please indicate some details.");
        }
    }

    private static void validateDate(String inputDate, String type) throws UserInputErrorException {
        LocalDate newDate;
        try {
            newDate = LocalDate.parse(inputDate);
        } catch (DateTimeParseException dateTimeParseException) {
            throw new UserInputErrorException("Date must be in YYYY-MM-DD format. ");
        }
        LocalDate today = LocalDate.now();
        LocalDate admissionDateLimit = LocalDate.parse("1980-01-01");
        if (type.equals("appointment") && newDate.isBefore(today)) {
            throw new UserInputErrorException("Date must be in YYYY-MM-DD format. "
                    + "New appointment dates must be today and after.");
        } else if (type.equals("patient") && newDate.isAfter(admissionDateLimit)
                && newDate.isBefore(today)) {
            throw new UserInputErrorException("Date must be in YYYY-MM-DD format. "
                    + "Patient registration date must be after 1980-01-01 and today or before.");
        }
    }

    private static boolean validateDate(String expiry) throws UserInputErrorException {
        try {
            LocalDate expiryDate = LocalDate.parse(expiry);
            return true;
        } catch (DateTimeParseException dateTimeParseException) {
            throw new UserInputErrorException("Invalid Medicine Expiry");
        }
    }

    public static void validateAddAppointment(String[] parameters) throws UserInputErrorException {
        minParameterCheck(parameters, 4);
        validateNric(parameters[0]);
        validateNric(parameters[1]);
        validateDate(parameters[2], "appointment");
    }

    public static void validateEditAppointment(String[] parameters) throws UserInputErrorException {
        minParameterCheck(parameters, 7);
        validateNric(parameters[1]);
        validateFullName(parameters[2]);
        validateNric(parameters[3]);
        validateFullName(parameters[4]);
        validateDate(parameters[5], "appointment");
        validateAppointmentDetails(parameters[6]);
    }

    public static void validateFindDoctor(String[] parameters) throws UserInputErrorException {
        minParameterCheck(parameters, 2);
        switch (parameters[0].toLowerCase()) {
        case "nric":
            validateNric(parameters[1]);
            break;
        case "name":
            validateFullName(parameters[1]);
            break;
        case "age":
            validateAge(parameters[1]);
            break;
        case "gender":
            validateGender(parameters[1]);
            break;
        case "address":
            validateAddress(parameters[1]);
            break;
        case "dob":
            validateDob(parameters[1]);
            break;
        case "specialization":
            validateSpecialization(parameters[1]);
            break;
        default:
            throw new UserInputErrorException("Input must be an attribute of Doctor");
        }
    }

    public static void validateFindAppointment(String[] parameters) throws UserInputErrorException {
        minParameterCheck(parameters, 2);
        switch (parameters[0].toLowerCase()) {
        case "id":
            break;
        case "patient name":
        case "doctor name":
            validateFullName(parameters[1]);
            break;
        case "patient nric":
        case "doctor nric":
            validateNric(parameters[1]);
            break;
        case "date":
            validateDate(parameters[1], "find appointment");
            break;
        default:
            throw new UserInputErrorException("Input must be an attribute of Appointment");
        }
    }

    public static void validateFindMedicine(String[] parameters) throws UserInputErrorException {
        minParameterCheck(parameters, 2);
        boolean check = true;
        switch (parameters[0].toLowerCase()) {
        case "name":
            check = validateMedicineName(parameters[1]);
            break;
        case "dosage":
            check = check && validateDosage(parameters[1]);
            break;
        case "expiry":
            check = check && validateDate(parameters[1]);
            break;
        case "quantity":
            check = check && validateQuantity(parameters[1]);
            break;
        case "id":
        case "side effects":
        default:
            break;
        }
    }

    public static void validateFindPatient(String[] parameters) throws UserInputErrorException {
        switch (parameters[0].toLowerCase()) {
        case "nric":
            validateNric(parameters[1]);
            break;
        case "name":
            validateFullName(parameters[1]);
            break;
        case "age":
            validateAge(parameters[1]);
            break;
        case "gender":
            validateGender(parameters[1]);
            break;
        case "address":
            validateAddress(parameters[1]);
            break;
        case "dob":
            validateDob(parameters[1]);
            break;
        case "admissiondate":
            validateAdmissionDate(parameters[1]);
            break;
        default:
            throw new UserInputErrorException("Input must be an attribute of Patient");
        }
    }

    public static void validateDispenseMedicine(String[] dispenseMedicineParameters) throws UserInputErrorException {
        validateNric(dispenseMedicineParameters[0]);
        if (dispenseMedicineParameters.length < 3 || dispenseMedicineParameters.length % 2 != 1) {
            throw new UserInputErrorException("Not all medicines in list have both "
                    + "the name of the medicine and the quantity to prescribe!");
        }
        for (int i = 1; i < dispenseMedicineParameters.length; i += 2) {
            String medicineName = dispenseMedicineParameters[i];
            String medicineQuantity = dispenseMedicineParameters[i + 1];
            validateMedicineName(medicineName);
            validateQuantity(medicineQuantity);
        }
    }
}
