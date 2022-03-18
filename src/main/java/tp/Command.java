package tp;

public abstract class Command {

    public boolean isExit() {
        return false;
    }

    public abstract void execute(DoctorList doctorList, PatientList patientList,
                                   AppointmentList appointmentList, Ui ui, Storage storage) throws IHospitalException;
}
