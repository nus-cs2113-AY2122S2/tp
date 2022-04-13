package tp.command;

import tp.*;

public class EditWardCommand extends Command {
    private int index;
    private String type;
    private int newInformation;

    public EditWardCommand(int index, String type, int newInformation) {
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
        if (index <= 0 || index > wardList.getSize()) {
            throw new IHospitalException("The ward does not exist\n");
        }
        if (type.contains("id")) {
            for (int i = 1; i <= wardList.getSize() ; i++) {
                if (wardList.getWard(i).getNumber() == newInformation) {
                    throw new IHospitalException("Ward with this ID already exists.\n");
                }
            }
            wardList.getWard(index).editNumber(newInformation);
            return String.format(boundary + "Updated already!\n" + wardList.getWard(index) + "\n" + boundary);
        } else {
            return String.format(boundary + "Ward details are already up to date!\n");
        }
    }
}
