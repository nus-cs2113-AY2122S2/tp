package seedu.duke.helper;

import seedu.duke.assets.MedicineList;
import seedu.duke.assets.Patient;
import seedu.duke.assets.PatientList;

public class Command {
    private UI ui = new UI();
    //contains all the commands required for patient, doctor and medicine

    private boolean nullChecker(String string) {
        return string != null;
    }

    public void viewPatient(PatientList patientList, String parameters) {
        if (!nullChecker(parameters)) {
            return;
        }
        if (Parser.parseViewPatient(parameters) == null) {
            patientList.viewPatient();
        }
        patientList.viewPatient(parameters);
    }

    public void addPatient(PatientList patientList, String parameters) {
        if (!nullChecker(parameters)) {
            return;
        }
        String[] parametersArray = Parser.parseAddPatient(parameters);
        if (parametersArray == null) {
            ui.printAddPatientWrongFormatMessage();
        } else {
            Patient newPatient = new Patient(parametersArray[0], parametersArray[1],
                    Integer.parseInt(parametersArray[2]), parametersArray[3].charAt(0),
                    parametersArray[4], parametersArray[5], parametersArray[6]);
            System.out.println("The patient above has been added!");
        }
    }

    public void deletePatient(PatientList patientList, String stringIndex) {
        int index = Integer.parseInt(stringIndex);
        if (0 <= index && index <= patientList.getSize()) {
            patientList.removePatient(index);
            System.out.println("The patient with the above index number has been removed!");
        } else {
            System.out.println("Oops! Please input a valid index number!");
        }
    }

    public void addMedicine(MedicineList medicineList, String parameters) {
        if (!nullChecker(parameters)) {
            return;
        }
        String[] parameterArray = Parser.parseAddMedicine(parameters);
        if (parameterArray == null) {
            System.out.println("Wrong Medicine Format");
            return;
        }
        medicineList.add(parameterArray);
        System.out.println("Medicine has been added");;
    }

    public void deleteMedicine(MedicineList medicineList, String stringIndex) {
        if (!nullChecker(stringIndex)) {
            return;
        }
        try {
            int index = Integer.parseInt(stringIndex);
            if (index < 1 || index > medicineList.size()) {
                System.out.println("Number is not within range of 1 - " + medicineList.size());
                return;
            }
            medicineList.delete(index);
            System.out.println("The medicine record at index " + index + " has been deleted.");
        } catch (NumberFormatException numberFormatException) {
            System.out.println("Parameter given is not a number.");
        }
    }
}
