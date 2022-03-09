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

}
