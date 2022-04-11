package seedu.duke.helper;

import seedu.duke.exception.UserInputErrorException;
import seedu.duke.helper.command.AddAppointmentCommand;
import seedu.duke.helper.command.AddDoctorCommand;
import seedu.duke.helper.command.AddMedicineCommand;
import seedu.duke.helper.command.AddPatientCommand;
import seedu.duke.helper.command.CheckIfAppointmentTodayCommand;
import seedu.duke.helper.command.CheckIfDoctorExistsCommand;
import seedu.duke.helper.command.CheckIfPatientExistsCommand;
import seedu.duke.helper.command.ClearExpiredMedicineCommand;
import seedu.duke.helper.command.Command;
import seedu.duke.helper.command.DeleteAppointmentCommand;
import seedu.duke.helper.command.DeleteDoctorCommand;
import seedu.duke.helper.command.DeleteMedicineCommand;
import seedu.duke.helper.command.DeletePatientCommand;
import seedu.duke.helper.command.DispenseMedicineCommand;
import seedu.duke.helper.command.EditAppointmentCommand;
import seedu.duke.helper.command.EditDoctorCommand;
import seedu.duke.helper.command.EditMedicineCommand;
import seedu.duke.helper.command.EditPatientCommand;
import seedu.duke.helper.command.FindAppointmentCommand;
import seedu.duke.helper.command.FindDoctorCommand;
import seedu.duke.helper.command.FindMedicineCommand;
import seedu.duke.helper.command.FindPatientCommand;
import seedu.duke.helper.command.CheckForMedicineStockCommand;
import seedu.duke.helper.command.UpdateMedicineInventoryCommand;
import seedu.duke.helper.command.ViewAppointmentCommand;
import seedu.duke.helper.command.ViewDoctorCommand;
import seedu.duke.helper.command.ViewMedicineCommand;
import seedu.duke.helper.command.ViewPatientCommand;


public class Parser {
    public static String[] commandParser(String userInput) {
        return userInput.trim().split("/info");
    }

    private static String[] stringSplitter(String parameters) throws UserInputErrorException {
        try {
            String[] parametersArray = parameters.split(",");
            for (int i = 0; i < parametersArray.length; i++) {
                parametersArray[i] = parametersArray[i].trim();
            }
            return parametersArray;
        } catch (Exception e) {
            throw new UserInputErrorException("There is one or more parameters missing");
        }
    }

    private static boolean isNull(String string) {
        return string == null;
    }

    public static Command parseAddPatient(String parameters) throws UserInputErrorException {
        String[] addPatientParameters = stringSplitter(parameters);
        Validator.validateAddPatient(addPatientParameters);
        return new AddPatientCommand(addPatientParameters);
    }

    public static Command parseDeletePatient(String parameters) throws UserInputErrorException {
        String[] deletePatientParameters = stringSplitter(parameters);
        Validator.validateNric(parameters);
        return new DeletePatientCommand(deletePatientParameters);
    }

    public static Command parseViewPatient(String parameters) throws UserInputErrorException {
        if (isNull(parameters)) {
            return new ViewPatientCommand(null);
        }
        return parseFindPatient(parameters);
    }

    public static Command parseEditPatient(String parameters) throws UserInputErrorException {
        String[] patientParameters = stringSplitter(parameters);
        Validator.validateAddPatient(patientParameters);
        return new EditPatientCommand(patientParameters);
    }

    public static Command parseAddDoctor(String parameters) throws UserInputErrorException {
        String[] addDoctorParameters = stringSplitter(parameters);
        Validator.validateAddDoctor(addDoctorParameters);
        return new AddDoctorCommand(addDoctorParameters);
    }

    public static Command parseDeleteDoctor(String parameters) throws UserInputErrorException {
        String[] deleteDoctorParameters = stringSplitter(parameters);
        Validator.validateNric(parameters);
        return new DeleteDoctorCommand(deleteDoctorParameters);
    }

    public static Command parseViewDoctor(String parameters) throws UserInputErrorException {
        if (isNull(parameters)) {
            return new ViewDoctorCommand(null);
        }
        return parseFindDoctor(parameters);
    }

    public static Command parseEditDoctor(String parameters) throws UserInputErrorException {
        String[] doctorParameters = stringSplitter(parameters);
        Validator.validateAddDoctor(doctorParameters);
        return new EditDoctorCommand(doctorParameters);
    }


    public static Command parseAddMedicine(String parameters) throws UserInputErrorException {
        String[] medicineParameters = stringSplitter(parameters);
        Validator.validateMedicine(medicineParameters);
        return new AddMedicineCommand(medicineParameters);
    }

    public static Command parseDeleteMedicine(String parameters) throws UserInputErrorException {
        String[] deleteMedicineParameters = stringSplitter(parameters);
        if (deleteMedicineParameters.length != 1) {
            throw new UserInputErrorException("Delete medicine only takes in one parameter");
        }
        return new DeleteMedicineCommand(deleteMedicineParameters);
    }

    public static Command parseViewMedicine(String parameters) throws UserInputErrorException {
        if (isNull(parameters)) {
            return new ViewMedicineCommand(null);
        }
        return parseFindMedicine(parameters);
    }

    public static Command parseEditMedicine(String parameters) throws UserInputErrorException {
        String[] medicineParameters = stringSplitter(parameters);
        Validator.validateMedicine(medicineParameters);
        return new EditMedicineCommand(medicineParameters);
    }

    public static Command parseUpdateMedicineStock(String parameters) throws UserInputErrorException {
        if (!isNull(parameters)) {
            throw new UserInputErrorException("The update medicines command does not take in any parameters!");
        }
        return new UpdateMedicineInventoryCommand();
    }

    public static Command parseClearExpiredMedicine(String parameters) throws UserInputErrorException {
        if (!isNull(parameters)) {
            throw new UserInputErrorException("The clear old medicines command does not take in any parameters!");
        }
        return new ClearExpiredMedicineCommand();
    }

    public static Command parseDispenseMedicine(String parameters) throws UserInputErrorException {
        String[] dispenseMedicineParameters = stringSplitter(parameters);
        Validator.validateDispenseMedicine(dispenseMedicineParameters);
        return new DispenseMedicineCommand(dispenseMedicineParameters);
    }

    public static Command parseCheckMedicineStock(String parameters) throws UserInputErrorException {
        String[] retrieveMedicineParameters = stringSplitter(parameters);
        return new CheckForMedicineStockCommand(retrieveMedicineParameters);
    }

    public static Command parseCheckForPatientAppointment(String parameters) throws UserInputErrorException {
        String[] checkForPatientAppointmentParameters = stringSplitter(parameters);
        return new CheckIfAppointmentTodayCommand(checkForPatientAppointmentParameters);
    }

    public static Command parseAddAppointment(String parameters) throws UserInputErrorException {
        String[] addAppointmentParameters = stringSplitter(parameters);
        Validator.validateAddAppointment(addAppointmentParameters);
        return new AddAppointmentCommand(addAppointmentParameters);
    }

    public static Command checkIfPatientExists(String parameters) throws UserInputErrorException {
        String[] checkIfPatientExistsParameters = stringSplitter(parameters);
        Validator.validateAddAppointment(checkIfPatientExistsParameters);
        return new CheckIfPatientExistsCommand(checkIfPatientExistsParameters);
    }

    public static Command checkIfDoctorExists(String parameters) throws UserInputErrorException {
        String[] checkIfDoctorExistsParameters = stringSplitter(parameters);
        Validator.validateAddAppointment(checkIfDoctorExistsParameters);
        return new CheckIfDoctorExistsCommand(checkIfDoctorExistsParameters);
    }


    public static Command parseViewAppointment(String parameters) throws UserInputErrorException {
        if (isNull(parameters)) {
            return new ViewAppointmentCommand(null);
        }
        return parseFindAppointment(parameters);
    }

    public static Command parseFindAppointment(String parameters) throws UserInputErrorException {
        String[] findAppointmentParameters = stringSplitter(parameters);
        Validator.validateFindAppointment(findAppointmentParameters);
        return new FindAppointmentCommand(findAppointmentParameters);
    }

    public static Command parseEditAppointment(String parameters) throws UserInputErrorException {
        String[] editAppointmentParameters = stringSplitter(parameters);
        Validator.validateEditAppointment(editAppointmentParameters);
        return new EditAppointmentCommand(editAppointmentParameters);
    }

    public static Command parseFindDoctor(String parameters) throws UserInputErrorException {
        String[] findDoctorParameters = stringSplitter(parameters);
        Validator.validateFindDoctor(findDoctorParameters);
        return new FindDoctorCommand(findDoctorParameters);
    }

    public static Command parseFindPatient(String parameters) throws UserInputErrorException {
        String[] findPatientParameters = stringSplitter(parameters);
        Validator.validateFindPatient(findPatientParameters);
        return new FindPatientCommand(findPatientParameters);
    }

    public static Command parseFindMedicine(String parameters) throws UserInputErrorException {
        String[] findMedicineParameters = stringSplitter(parameters);
        Validator.validateFindMedicine(findMedicineParameters);
        return new FindMedicineCommand(findMedicineParameters);
    }

    public static Command parseDeleteAppointment(String parameters) throws UserInputErrorException {
        String[] deleteAppointmentParameters = stringSplitter(parameters);
        return new DeleteAppointmentCommand(deleteAppointmentParameters);
    }
}
