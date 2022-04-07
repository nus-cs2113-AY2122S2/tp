package tp.command;

import tp.*;

public class AddPatientDescriptionCommand extends Command {
    protected static String description;
    protected static int index;


    public AddPatientDescriptionCommand(String description, int index) {
        AddPatientDescriptionCommand.description = description;
        AddPatientDescriptionCommand.index = index;
    }


    @Override
    public String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                          WardList wardList, AppointmentList appointmentList, Ui ui,
                          DoctorStorage doctorStorage, WardStorage wardStorage,
                          PatientStorage patientStorage, NurseStorage nurseStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        PatientList.addPatientDescription(description,index);
        return "Already add description for patient " + index + " \n";
    }
}
