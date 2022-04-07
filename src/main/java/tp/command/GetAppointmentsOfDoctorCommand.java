package tp.command;

import tp.*;

public class GetAppointmentsOfDoctorCommand extends Command {
    private final String id;

    public GetAppointmentsOfDoctorCommand(String id) {
        this.id = id;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                          WardList wardList, AppointmentList appointmentList, Ui ui,
                          DoctorStorage doctorStorage, WardStorage wardStorage,
                          PatientStorage patientStorage, NurseStorage nurseStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        return appointmentList.getAppointmentListOfDoctorById(id).toString();
    }
}
