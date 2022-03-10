package seedu.duke.helper;

import seedu.duke.assets.Patient;
import seedu.duke.assets.PatientList;

public class Command {

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
            System.out.println("Please implement the above format changes and try again!");
        } else {
            Patient newPatient = new Patient(parametersArray[0], parametersArray[1],
                    Integer.parseInt(parametersArray[2]), parametersArray[3].charAt(0),
                    parametersArray[4], parametersArray[5], parametersArray[6]);
            System.out.println("Got it. The patient above has been added!");
        }
    }
}
