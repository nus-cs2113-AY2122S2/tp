package seedu.duke.helper;

import seedu.duke.assets.AppointmentList;
import seedu.duke.assets.DoctorList;
import seedu.duke.assets.MedicineList;
import seedu.duke.assets.PatientList;
import seedu.duke.exception.HalpmiException;


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
        try {
            String[] addPatientParameters = Parser.parseAddPatient(parameters);
            patientList.add(addPatientParameters);
            UI.printParagraph("The patient above has been added.");
        } catch (HalpmiException e) {
            UI.printParagraph(e.toString());
            ui.printAddPatientExampleMessage();
        }
    }

    public void deletePatient(PatientList patientList, String stringIndex) {
        int index;
        try {
            index = Integer.parseInt(stringIndex);
            patientList.removePatient(index - 1);
            UI.printParagraph("The patient with the specified index has been removed.");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.printDeletePatientExampleMessage(patientList);
        }

    }

    public void viewDoctor(DoctorList doctorList, String nric) {
        if (isNull(nric)) {
            doctorList.viewDoctor();
        } else {
            doctorList.viewDoctor(nric);
        }
    }

    public void deleteDoctor(DoctorList doctorList, String stringIndex) {
        int index;
        try {
            index = Integer.parseInt(stringIndex);
            doctorList.removeDoctor(index - 1);
            UI.printParagraph("The patient with the above index number has been removed.");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.printDeleteDoctorErrorMessage(doctorList);
        }
    }

    public void addMedicine(MedicineList medicineList, String parameters) {
        try {
            String[] addMedicineParameters = Parser.parseAddMedicine(parameters);
            medicineList.add(addMedicineParameters);
            UI.printParagraph("Medicine has been added");
        } catch (HalpmiException e) {
            UI.printParagraph(e.toString());
            ui.printAddMedicineExampleMessage();;
        }
    }

    public void viewMedicine(MedicineList medicineList, String parameters) {
        if (isNull(parameters)) {
            medicineList.viewMedicine();
        } else {
            medicineList.viewMedicine(parameters);
        }
    }

    public void addDoctor(DoctorList doctorList, String parameters) {
        try {
            String[] addDoctorParameters = Parser.parseAddDoctor(parameters);
            doctorList.add(addDoctorParameters);
            UI.printParagraph("The doctor above has been added.");
        } catch (HalpmiException e) {
            UI.printParagraph(e.toString());
            ui.printAddDoctorExampleMessage();
        }
    }

    public void deleteMedicine(MedicineList medicineList, String stringIndex) {
        try {
            int index = Integer.parseInt(stringIndex);
            medicineList.deleteMedicine(index - 1);
            UI.printParagraph("The medicine record at index " + index + " has been deleted.");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.printDeleteMedicineExampleMessage(medicineList);
        }
    }

    public void addAppointment(AppointmentList appointmentList, PatientList patientList,
                               DoctorList doctorList, String parameters) {
        try {
            String[] addAppointmentParameters = Parser.parseAddAppointment(parameters);
            appointmentList.add(addAppointmentParameters);
            UI.printParagraph("The above appointment has been added.");
        } catch (HalpmiException e) {
            UI.printParagraph(e.toString());
            ui.printAddAppointmentExampleMessage();
        }
    }
}
