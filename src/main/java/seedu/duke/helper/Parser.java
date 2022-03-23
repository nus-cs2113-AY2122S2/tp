package seedu.duke.helper;

public class Parser {


    public static String[] commandParser(String userInput) {
        return userInput.trim().split("/info");
    }

    public static String[] parseAddPatient(String parameters) {
        String[] addPatientParameters = parameters.split(",");
        for (int i = 0; i < addPatientParameters.length; i++) {
            addPatientParameters[i] = addPatientParameters[i].trim();
        }
        if (addPatientParameters.length != 7) {
            UI.printParagraph("There is one or more parameters missing.");
            return null;
        }
        if (Validator.validateAddPatient(addPatientParameters)) {
            return addPatientParameters;
        } else {
            return null;
        }
    }

    public static String[] parseAddDoctor(String parameters) {
        String[] addDoctorParameters = parameters.split(",");
        for (int i = 0; i < addDoctorParameters.length; i++) {
            addDoctorParameters[i] = addDoctorParameters[i].trim();
        }
        if (addDoctorParameters.length != 7) {
            UI.printParagraph("There is one or more parameters missing.");
            return null;
        }
        if (Validator.validateAddDoctor(addDoctorParameters)) {
            return addDoctorParameters;
        } else {
            return null;
        }
    }


    public static String[] parseAddMedicine(String parameters) {
        String[] medicineParameters = parameters.trim().split(",");
        for (int i = 0; i < medicineParameters.length; i++) {
            medicineParameters[i] = medicineParameters[i].trim();
        }
        if (medicineParameters.length == 5 && Validator.validateMedicine(medicineParameters)) {
            return medicineParameters;
        } else {
            return null;
        }
    }

}
