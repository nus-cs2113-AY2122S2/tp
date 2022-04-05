package tp.Command;

import tp.*;

public class ListDoctorListCommand extends Command {

    @Override
    public String execute(DoctorList doctorList, PatientList patientList,
                          AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                          PatientStorage patientStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        return doctorList.toString();
    }
}
