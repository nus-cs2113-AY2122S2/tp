package seedu.duke.helper;

import seedu.duke.assets.DoctorList;
import seedu.duke.assets.MedicineList;
import seedu.duke.assets.PatientList;


public class Command {
    private UI ui = new UI();

    private boolean isNull(String string) {
        return string == null;
    }

    public void viewPatient(PatientList patientList, String nric) {
        if (isNull(nric)) {
            patientList.viewPatient();
        } else {
            patientList.viewPatient(nric);
        }
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
            ui.printParagraph("The patient above has been added.");
        }
    }

    public void deletePatient(PatientList patientList, String stringIndex) {
        if (patientList.getSize() == 0) {
            ui.printParagraph("There is nothing to delete in patientList.");
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
            ui.printParagraph("The patient with the above index number has been removed.");
        } else {
            ui.printDeletePatientExampleMessage(patientList);
        }
    }

    public void viewDoctor(DoctorList doctorList, String nric) {
        if (nric == null) {
            doctorList.viewDoctor();
            return;
        }
        doctorList.viewDoctor(nric);
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
            ui.printParagraph("The doctor with the specified index has been removed.");
        } else {
            ui.printDeleteDoctorErrorMessage(doctorList);
        }
    }


    public void addMedicine(MedicineList medicineList, String parameters) {
        if (isNull(parameters)) {
            ui.printParagraph("Invalid format. Please follow the below example and try again.\n"
                    + "add medicine /info Paracetamol,500,2023-12-12,Headaches,10");
            return;
        }
        String[] parameterArray = Parser.parseAddMedicine(parameters);
        if (parameterArray == null) {
            ui.printParagraph("There are missing parameters. Please follow the below example and try again.\n"
                    + "add medicine /info Paracetamol,500,2023-12-12,Headaches,10");
            return;
        }
        medicineList.add(parameterArray);
        ui.printParagraph("Medicine has been added");
    }

    public void viewMedicine(MedicineList medicineList, String parameters) {
        if (parameters == null) {
            medicineList.viewMedicine();
        } else {
            try {
                int index = Integer.parseInt(parameters);
                if (index < 1 || medicineList.size() < index) {
                    ui.printParagraph("Index is out of range.");
                    return;
                }
                medicineList.viewMedicine(index);
            } catch (NumberFormatException e) {
                ui.printParagraph("Index is out of range.");
            }
        }
    }

    public void addDoctor(DoctorList doctorList, String parameters) {
        if (isNull(parameters)) {
            ui.printNullParametersMessage();
            ui.printAddDoctorExampleMessage();
            return;
        }
        String[] addDoctorParameters = Parser.parseAddDoctor(parameters);
        if (addDoctorParameters == null) {
            ui.printAddPatientExampleMessage();
        } else {
            doctorList.add(addDoctorParameters);
            ui.printParagraph("The doctor above has been added.");
        }
    }

    public void deleteMedicine(MedicineList medicineList, String stringIndex) {
        if (isNull(stringIndex)) {
            ui.printParagraph("Parameter missing.");
            return;
        }
        try {
            int index = Integer.parseInt(stringIndex);
            if (index < 1 || index > medicineList.size()) {
                ui.printParagraph("Number is not within range.");
                return;
            }
            medicineList.delete(index);
            ui.printParagraph("The medicine record at index " + index + " has been deleted.");
        } catch (NumberFormatException numberFormatException) {
            ui.printParagraph("Parameter given is not a number.");
        }
    }
}
