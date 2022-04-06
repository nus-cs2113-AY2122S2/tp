package tp.command;

import tp.AppointmentList;
import tp.DoctorList;
import tp.PatientList;
import tp.Ui;
import tp.person.Doctor;
import tp.DoctorStorage;
import tp.PatientStorage;
import tp.IHospitalException;
import tp.AppointmentStorage;
import tp.person.Doctor;

public class DeleteDoctorCommand extends Command {
    private final int index;

    public DeleteDoctorCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList,
                          AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                          PatientStorage patientStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        if (index > doctorList.getSize()) {
            throw new IHospitalException("The doctor does not exist.\n");
        }

        Doctor curr = doctorList.deleteDoctor(index);
        return String.format(boundary + "Noted. I've removed this doctor:" + curr
                                     + "\n" + "Now you have " + doctorList.getSize()
                                     + " doctors in the system." + System.lineSeparator() + boundary);
    }
}
