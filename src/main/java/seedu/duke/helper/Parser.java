package seedu.duke.helper;

import seedu.duke.exception.HalpmiException;
import seedu.duke.helper.command.AddAppointmentCommand;
import seedu.duke.helper.command.AddDoctorCommand;
import seedu.duke.helper.command.AddMedicineCommand;
import seedu.duke.helper.command.AddPatientCommand;
import seedu.duke.helper.command.CheckIfDoctorExistsCommand;
import seedu.duke.helper.command.CheckIfPatientExistsCommand;
import seedu.duke.helper.command.ClearExpiredMedicineCommand;
import seedu.duke.helper.command.Command;
import seedu.duke.helper.command.DeleteAppointmentCommand;
import seedu.duke.helper.command.DeleteDoctorCommand;
import seedu.duke.helper.command.DeleteMedicineCommand;
import seedu.duke.helper.command.DeletePatientCommand;
import seedu.duke.helper.command.EditAppointmentCommand;
import seedu.duke.helper.command.EditDoctorCommand;
import seedu.duke.helper.command.EditMedicineCommand;
import seedu.duke.helper.command.EditPatientCommand;
import seedu.duke.helper.command.FindAppointmentCommand;
import seedu.duke.helper.command.FindDoctorCommand;
import seedu.duke.helper.command.FindMedicineCommand;
import seedu.duke.helper.command.FindPatientCommand;
import seedu.duke.helper.command.UpdateMedicineInventoryCommand;
import seedu.duke.helper.command.ViewAppointmentCommand;
import seedu.duke.helper.command.ViewDoctorCommand;
import seedu.duke.helper.command.ViewMedicineCommand;
import seedu.duke.helper.command.ViewPatientCommand;


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

    private static boolean isNull(String string) {
        return string == null;
    }

    public static Command parseAddPatient(String parameters) throws HalpmiException {
        String[] addPatientParameters = minParameterCheck(parameters, 7);
        Validator.validateAddPatient(addPatientParameters);
        return new AddPatientCommand(addPatientParameters);
    }

    public static Command parseDeletePatient(String parameters) throws HalpmiException {
        String[] deletePatientParameters = minParameterCheck(parameters, 1);
        Validator.validateNric(parameters);
        return new DeletePatientCommand(deletePatientParameters);
    }

    public static Command parseViewPatient(String parameters) throws HalpmiException {
        if (isNull(parameters)) {
            return new ViewPatientCommand(null);
        }
        return parseFindPatient(parameters);
    }

    public static Command parseEditPatient(String parameters) throws HalpmiException {
        String[] patientParameters = minParameterCheck(parameters, 7);
        Validator.validateAddPatient(patientParameters);
        return new EditPatientCommand(patientParameters);
    }

    public static Command parseAddDoctor(String parameters) throws HalpmiException {
        String[] addDoctorParameters = minParameterCheck(parameters, 7);
        Validator.validateAddDoctor(addDoctorParameters);
        return new AddDoctorCommand(addDoctorParameters);
    }

    public static Command parseDeleteDoctor(String parameters) throws HalpmiException {
        String[] deleteDoctorParameters = minParameterCheck(parameters, 1);
        Validator.validateNric(parameters);
        return new DeleteDoctorCommand(deleteDoctorParameters);
    }

    public static Command parseViewDoctor(String parameters) throws HalpmiException {
        if (isNull(parameters)) {
            return new ViewDoctorCommand(null);
        }
        return parseFindDoctor(parameters);
    }

    public static Command parseEditDoctor(String parameters) throws HalpmiException {
        String[] doctorParameters = minParameterCheck(parameters, 7);
        Validator.validateAddDoctor(doctorParameters);
        return new EditDoctorCommand(doctorParameters);
    }


    public static Command parseAddMedicine(String parameters) throws HalpmiException {
        String[] medicineParameters = minParameterCheck(parameters, 6);
        Validator.validateMedicine(medicineParameters);
        return new AddMedicineCommand(medicineParameters);
    }

    public static Command parseDeleteMedicine(String parameters) throws HalpmiException {
        String[] deleteMedicineParameters = minParameterCheck(parameters, 1);
        return new DeleteMedicineCommand(deleteMedicineParameters);
    }

    public static Command parseViewMedicine(String parameters) throws HalpmiException {
        if (isNull(parameters)) {
            return new ViewMedicineCommand(null);
        }
        return parseFindMedicine(parameters);
    }

    public static Command parseEditMedicine(String parameters) throws HalpmiException {
        String[] medicineParameters = minParameterCheck(parameters, 6);
        Validator.validateMedicine(medicineParameters);
        return new EditMedicineCommand(medicineParameters);
    }

    public static Command parseUpdateMedicineStock(String parameters) throws HalpmiException {
        if (!isNull(parameters)) {
            throw new HalpmiException("The update medicines command does not take in any parameters!");
        }
        return new UpdateMedicineInventoryCommand();
    }

    public static Command parseClearExpiredMedicine(String parameters) throws HalpmiException {
        if (!isNull(parameters)) {
            throw new HalpmiException("The clear old medicines command does not take in any parameters!");
        }
        return new ClearExpiredMedicineCommand();
    }

    public static Command parseAddAppointment(String parameters) throws HalpmiException {
        String[] addAppointmentParameters = minParameterCheck(parameters, 4);
        Validator.validateAddAppointment(addAppointmentParameters);
        return new AddAppointmentCommand(addAppointmentParameters);
    }

    public static Command checkIfPatientExists(String parameters) throws HalpmiException {
        String[] checkIfPatientExistsParameters = minParameterCheck(parameters, 4);
        Validator.validateAddAppointment(checkIfPatientExistsParameters);
        return new CheckIfPatientExistsCommand(checkIfPatientExistsParameters);
    }

    public static Command checkIfDoctorExists(String parameters) throws HalpmiException {
        String[] checkIfDoctorExistsParameters = minParameterCheck(parameters, 4);
        Validator.validateAddAppointment(checkIfDoctorExistsParameters);
        return new CheckIfDoctorExistsCommand(checkIfDoctorExistsParameters);
    }

    public static Command parseViewAppointment(String parameters) throws HalpmiException {
        if (isNull(parameters)) {
            return new ViewAppointmentCommand(null);
        }
        return parseFindAppointment(parameters);
    }

    public static Command parseFindAppointment(String parameters) throws HalpmiException {
        String[] findAppointmentParameters = minParameterCheck(parameters, 2);
        Validator.validateFindAppointment(findAppointmentParameters);
        return new FindAppointmentCommand(findAppointmentParameters);
    }

    public static Command parseEditAppointment(String parameters) throws HalpmiException {
        String[] editAppointmentParameters = minParameterCheck(parameters, 7);
        Validator.validateEditAppointment(editAppointmentParameters);
        return new EditAppointmentCommand(editAppointmentParameters);
    }

    public static Command parseFindDoctor(String parameters) throws HalpmiException {
        String[] findDoctorParameters = minParameterCheck(parameters, 2);
        Validator.validateFindDoctor(findDoctorParameters);
        return new FindDoctorCommand(findDoctorParameters);
    }

    public static Command parseFindPatient(String parameters) throws HalpmiException {
        String[] findPatientParameters = minParameterCheck(parameters, 2);
        Validator.validateFindPatient(findPatientParameters);
        return new FindPatientCommand(findPatientParameters);
    }

    public static Command parseFindMedicine(String parameters) throws HalpmiException {
        String[] findMedicineParameters = minParameterCheck(parameters, 2);
        Validator.validateFindMedicine(findMedicineParameters);
        return new FindMedicineCommand(findMedicineParameters);
    }

    public static Command parseDeleteAppointment(String parameters) throws HalpmiException {
        String[] deleteAppointmentParameters = minParameterCheck(parameters, 1);
        return new DeleteAppointmentCommand(deleteAppointmentParameters);
    }
}
