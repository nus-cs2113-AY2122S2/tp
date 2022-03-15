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
            ui.printAddPatientNullParametersMessage();
            ui.printAddPatientExampleMessage();
            return;
        }
        String[] addPatientParameters = Parser.parseAddPatient(parameters);
        if (addPatientParameters == null) {
            ui.printAddPatientExampleMessage();
        } else {
            Patient newPatient = new Patient(addPatientParameters[0], addPatientParameters[1],
                    Integer.parseInt(addPatientParameters[2]), addPatientParameters[3].charAt(0),
                    addPatientParameters[4], addPatientParameters[5], addPatientParameters[6]);
            System.out.println("The patient above has been added.");
        }
    }

    public void deletePatient(PatientList patientList, String stringIndex) {
        int index = Integer.parseInt(stringIndex);
        if (0 <= index && index <= patientList.getSize()) {
            patientList.removePatient(index);
            System.out.println("The patient with the above index number has been removed.");
        } else {
            System.out.println("Invalid index number!");
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
}
