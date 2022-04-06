package tp.command;

import tp.AppointmentList;
import tp.DoctorList;
import tp.PatientList;
import tp.Ui;
import tp.DoctorStorage;
import tp.PatientStorage;
import tp.IHospitalException;
import tp.AppointmentStorage;
import tp.person.Patient;

public class DeletePatientCommand extends Command {
    private final int index;

    public DeletePatientCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList,
                          AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                          PatientStorage patientStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        Patient curr = patientList.deletePatient(index);
        if (curr == null){
            return String.format("The index " + index + " is not valid in the patient list");
        } else {
            return String.format(boundary + "Noted. I've removed this patient:" + "\n" + curr
                    + "\n" + "Now you have " + patientList.getSize()
                    + " patients in the system." + System.lineSeparator() + boundary);
        }
    }
}
