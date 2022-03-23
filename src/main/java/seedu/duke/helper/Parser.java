package seedu.duke.helper;

import seedu.duke.exception.HalpmiException;

public class Parser {


    public static String[] commandParser(String userInput) {
        return userInput.trim().split("/info");
    }

    private static String[] minParameterCheck(String parameters, int length) throws HalpmiException {
        String[] parametersArray = parameters.split(",");
        if (parametersArray.length != length) {
            throw new HalpmiException("There is one or more parameters missing");
        }

        for (int i = 0; i < parametersArray.length; i++) {
            parametersArray[i] = parametersArray[i].trim();
        }
        return parametersArray;
    }

    public static String[] parseAddPatient(String parameters) {
        String[] addPatientParameters = minParameterCheck(parameters, 7);
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
