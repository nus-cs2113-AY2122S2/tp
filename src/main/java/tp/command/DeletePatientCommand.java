package tp.command;

import tp.*;
import tp.person.Patient;

public class DeletePatientCommand extends Command {
    private final int index;

    public DeletePatientCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                          WardList wardList, AppointmentList appointmentList, Ui ui,
                          DoctorStorage doctorStorage, WardStorage wardStorage,
                          PatientStorage patientStorage, NurseStorage nurseStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        if (index <= 0 || index > patientList.getSize()) {
            throw new IHospitalException("The patient does not exist.\n");
        }
        Patient curr = patientList.deletePatient(index);
        return String.format(boundary + "Noted. I've removed this patient:" + curr
                                     + "\n" + "Now you have " + patientList.getSize()
                                     + " patients in the system." + System.lineSeparator() + boundary);
    }
}
