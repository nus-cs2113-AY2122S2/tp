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

public class AddPatientDescriptionCommand extends Command {
    protected static String description;
    protected static int index;


    public AddPatientDescriptionCommand(String description, int index) {
        this.description = description;
        this.index = index;
    }


    @Override
    public String execute(DoctorList doctorList, PatientList patientList,
                          AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                          PatientStorage patientStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        PatientList.addPatientDescription(description,index);
        return String.format("Already add description for patient " + index + " \n");
    }
}
