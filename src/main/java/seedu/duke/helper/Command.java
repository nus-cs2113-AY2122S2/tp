package seedu.duke.helper;

import seedu.duke.assets.MedicineList;
import seedu.duke.assets.PatientList;

public class Command {
    private UI ui = new UI();

    private boolean isNull(String string) {
        return string == null;
    }

    public void viewPatient(PatientList patientList, String parameters) {
        if (isNull(parameters)) {
            return;
        }
        if (Parser.parseViewPatient(parameters) == null) {
            patientList.viewPatient();
        }
        patientList.viewPatient(parameters);
    }

    public void addPatient(PatientList patientList, String parameters) {
        if (isNull(parameters)) {
            ui.printAddPatientNullParametersMessage();
            ui.printAddPatientExampleMessage();
            return;
        }
        String[] addPatientParameters = Parser.parseAddPatient(parameters);
        if (addPatientParameters == null) {
            ui.printAddPatientExampleMessage();
        } else {
            patientList.add(addPatientParameters);
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
        if (isNull(parameters)) {
            return;
        }
        String[] parameterArray = Parser.parseAddMedicine(parameters);
        if (parameterArray == null) {
            System.out.println("Wrong Medicine Format");
            return;
        }
        medicineList.add(parameterArray);
        System.out.println("Medicine has been added");
    }
}
