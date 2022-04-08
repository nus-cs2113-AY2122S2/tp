package tp.command;

import tp.AppointmentList;
import tp.AppointmentStorage;
import tp.DoctorList;
import tp.DoctorStorage;
import tp.IHospitalException;
import tp.NurseList;
import tp.NurseStorage;
import tp.PatientList;
import tp.PatientStorage;
import tp.Ui;
import tp.WardList;
import tp.WardStorage;

//@@author Demonshaha
public class EditPatientCommand extends Command {
    private int index;
    private String type;
    private String newInformation;

    public EditPatientCommand(int index, String type, String newInformation) {
        this.index = index;
        this.type = type;
        this.newInformation = newInformation;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                          WardList wardList, AppointmentList appointmentList, Ui ui,
                          DoctorStorage doctorStorage, WardStorage wardStorage,
                          PatientStorage patientStorage, NurseStorage nurseStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        if (index > patientList.getSize()) {
            throw new IHospitalException("The patient does not exist\n");
        }

        if (type.contains("e")) {
            patientList.getPatient(index).editEmail(newInformation);
            return String.format(boundary + "Updated already!\n" + patientList.getPatient(index) + "\n" + boundary);
        } else if (type.contains("ph")) {
            patientList.getPatient(index).editPhoneNumber(newInformation);
            return String.format(boundary + "Updated already!\n" + patientList.getPatient(index) + "\n" + boundary);
        } else {
            patientList.getPatient(index).editName(newInformation);
            return String.format(boundary + "Updated already!\n" + patientList.getPatient(index) + "\n" + boundary);
        }
    }
}
