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

public class EditDoctorCommand extends Command {
    private int index;
    private String type;
    private String newInformation;

    public EditDoctorCommand(int index, String type, String newInformation) {
        this.index = index;
        this.type = type;
        this.newInformation = newInformation;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList,
                          AppointmentList appointmentList, Ui ui,
                          DoctorStorage doctorStorage, PatientStorage patientStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        if (index > doctorList.getSize()) {
            throw new IHospitalException("The doctor does not exist\n");
        }

        if (type.contains("e")) {
            doctorList.getDoctor(index).editEmail(newInformation);
            return String.format(boundary + "Updated already!\n" + doctorList.getDoctor(index) + "\n" + boundary);
        } else if (type.contains("ph")) {
            doctorList.getDoctor(index).editPhoneNumber(newInformation);
            return String.format(boundary + "Updated already!\n" + doctorList.getDoctor(index) + "\n" + boundary);
        } else {
            doctorList.getDoctor(index).editName(newInformation);
            return String.format(boundary + "Updated already!\n" + doctorList.getDoctor(index) + "\n" + boundary);
        }
    }
}
