package tp.command;

import tp.AppointmentList;
import tp.AppointmentStorage;
import tp.DoctorList;
import tp.DoctorStorage;
import tp.IHospitalException;
import tp.NurseList;
import tp.NurseStorage;
import tp.PatientList;
import tp.PatientStorage;
import tp.Ui;
import tp.WardList;
import tp.WardStorage;

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
    public String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                          WardList wardList, AppointmentList appointmentList, Ui ui,
                          DoctorStorage doctorStorage, WardStorage wardStorage,
                          PatientStorage patientStorage, NurseStorage nurseStorage,
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
