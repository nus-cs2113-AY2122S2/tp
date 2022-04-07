package tp.command;

import tp.*;

public class DeleteAppointmentCommand extends Command {
    int index;

    public DeleteAppointmentCommand(){

    }

    public DeleteAppointmentCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                          WardList wardList, AppointmentList appointmentList, Ui ui,
                          DoctorStorage doctorStorage, WardStorage wardStorage,
                          PatientStorage patientStorage, NurseStorage nurseStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        if (index > appointmentList.getSize()) {
            throw new IHospitalException("The appointment does not exist.\n");
        }

        Appointment curr = appointmentList.deleteAppointment(index);
        return String.format(boundary + "Noted. I've removed this appointment:\n" + curr
                                     + "\n" + "Now you have " + (appointmentList.getSize())
                                     + " appointments recorded in the system." + System.lineSeparator() + boundary);
    }
}
