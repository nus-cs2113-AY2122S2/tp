package seedu.duke.helper;

import seedu.duke.assets.MedicineList;
import seedu.duke.assets.PatientList;
import seedu.duke.assets.DoctorList;


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
            ui.printNullParametersMessage();
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
        if (patientList.getSize() == 0) {
            System.out.println("There is nothing to delete in patientList.");
            return;
        }
        int index;
        try {
            index = Integer.parseInt(stringIndex);
        } catch (NumberFormatException numberFormatException) {
            ui.printDeletePatientExampleMessage(patientList);
            return;
        }
        if (1 <= index && index <= patientList.getSize()) {
            patientList.removePatient(index - 1);
            System.out.println("The patient with the above index number has been removed.");
        } else {
            ui.printDeletePatientExampleMessage(patientList);
        }
    }

    public void viewDoctor(DoctorList doctorList, String parameters) {
        if (isNull(parameters)) {
            return;
        }
        if (Parser.parseViewDoctor(parameters) == null) {
            doctorList.viewDoctor();
        }
        doctorList.viewDoctor(parameters);
    }

    public void deleteDoctor(DoctorList doctorList, String stringIndex) {
        int indexDoctor;
        try {
            indexDoctor = Integer.parseInt(stringIndex);
        } catch (NumberFormatException numberFormatException) {
            ui.printDeleteDoctorErrorMessage(doctorList);
            return;
        }
        if (1 <= indexDoctor && indexDoctor <= doctorList.getSizeDoctor()) {
            doctorList.removeDoctor(indexDoctor - 1);
            System.out.println("The doctor with the specified index has been removed.");
        } else {
            ui.printDeleteDoctorErrorMessage(doctorList);
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
