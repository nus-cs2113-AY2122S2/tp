package seedu.duke.helper;

import seedu.duke.assets.Patient;
import seedu.duke.assets.PatientList;

public class Command {
    private UI ui = new UI();
    //contains all the commands required for patient, doctor and medicine

    public void viewPatient(PatientList patientList, String parameters) {
        if (Parser.parseViewPatient(parameters) == null) {
            patientList.viewPatient();
        }
        patientList.viewPatient(parameters);
    }

    public void addPatient(PatientList patientList, String parameters) {
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
}
