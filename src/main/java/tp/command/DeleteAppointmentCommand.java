package tp.command;

import tp.AppointmentList;
import tp.DoctorList;
import tp.PatientList;
import tp.Ui;
import tp.Appointment;
import tp.DoctorStorage;
import tp.PatientStorage;
import tp.IHospitalException;
import tp.AppointmentStorage;

public class DeleteAppointmentCommand extends Command {
    int index;

    public DeleteAppointmentCommand(){

    }

    public DeleteAppointmentCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList,
                          AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                          PatientStorage patientStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        Appointment curr = appointmentList.deleteAppointment(index);
        if (curr == null){
            return String.format("The index " + index + " is not valid in the appointment list");
        } else {
            return String.format(boundary + "Noted. I've removed this appointment:\n" + curr
                    + "\n" + "Now you have " + (appointmentList.getSize())
                    + " appointments recorded in the system." + System.lineSeparator() + boundary);
        }
    }
}
