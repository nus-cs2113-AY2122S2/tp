package tp.command;

import tp.*;

public class EditPatientCommand extends Command {
    private int index;
    private String type;
    private String newInformation;

    public EditPatientCommand(int index, String type, String newInformation) {
        this.index = index;
        this.type = type;
        this.newInformation = newInformation;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                          AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                          PatientStorage patientStorage,NurseStorage nurseStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        if (index > patientList.getSize()) {
            throw new IHospitalException("The patient does not exist\n");
        }

        if (type.contains("e")) {
            patientList.getPatient(index).editEmail(newInformation);
            return String.format(boundary + "Updated already!\n" + patientList.getPatient(index) + "\n" + boundary);
        } else if (type.contains("ph")) {
            patientList.getPatient(index).editPhoneNumber(newInformation);
            return String.format(boundary + "Updated already!\n" + patientList.getPatient(index) + "\n" + boundary);
        } else {
            patientList.getPatient(index).editName(newInformation);
            return String.format(boundary + "Updated already!\n" + patientList.getPatient(index) + "\n" + boundary);
        }
    }
}
