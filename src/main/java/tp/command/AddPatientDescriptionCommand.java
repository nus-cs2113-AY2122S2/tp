package tp.command;

import tp.*;

public class AddPatientDescriptionCommand extends Command {
    protected static String description;
    protected static int index;


    public AddPatientDescriptionCommand(String description, int index) {
        this.description = description;
        this.index = index;
    }


    @Override
    public String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                          AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                          PatientStorage patientStorage,NurseStorage nurseStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        PatientList.addPatientDescription(description,index);
        return String.format("Already add description for patient " + index + " \n");
    }
}
