package seedu.duke.helper;

import seedu.duke.exception.HalpmiException;


public class Parser {
    public static String[] commandParser(String userInput) {
        return userInput.trim().split("/info");
    }

    private static String[] minParameterCheck(String parameters, int length) throws HalpmiException {
        try {
            String[] parametersArray = parameters.split(",");
            if (parametersArray.length != length) {
                throw new HalpmiException("There is one or more parameters missing");
            }
            for (int i = 0; i < parametersArray.length; i++) {
                parametersArray[i] = parametersArray[i].trim();
            }
            return parametersArray;
        } catch (Exception e) {
            throw new HalpmiException("There is one or more parameters missing");
        }
    }

    public static String[] parseAddPatient(String parameters) throws HalpmiException {
        String[] addPatientParameters = minParameterCheck(parameters, 7);
        Validator.validateAddPatient(addPatientParameters);
        return addPatientParameters;
    }

    public static String[] parseAddDoctor(String parameters) throws HalpmiException {
        String[] addDoctorParameters = minParameterCheck(parameters, 7);
        Validator.validateAddDoctor(addDoctorParameters);
        return  addDoctorParameters;
    }

    public static String[] parseAddMedicine(String parameters) throws HalpmiException {
        String[] medicineParameters = minParameterCheck(parameters, 6);
        Validator.validateMedicine(medicineParameters);
        return medicineParameters;
    }

    public static String[] parseAddAppointment(String parameters) throws HalpmiException {
        String[] addAppointmentParameters = minParameterCheck(parameters, 6);
        Validator.validateAddAppointment(addAppointmentParameters);
        return addAppointmentParameters;
    }

    public static String[] parseFindAppointment(String parameters) throws HalpmiException {
        String[] findAppointmentParameters = minParameterCheck(parameters, 2);
        return findAppointmentParameters;
    }
}
