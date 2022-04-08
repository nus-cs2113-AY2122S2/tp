package tp.command;

import tp.*;


public class DeleteWardCommand extends Command{
    private final int index;

    public DeleteWardCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                          AppointmentList appointmentList, WardList wardList, Ui ui, DoctorStorage doctorStorage,
                          PatientStorage patientStorage, NurseStorage nurseStorage,
                          AppointmentStorage appointmentStorage, WardStorage wardStorage) throws IHospitalException {
        if (index <= 0 || index > wardList.getSize()) {
            throw new IHospitalException("The ward does not exist.\n");
        }
        ward cur = wardList.deleteward(index);
        return String.format(boundary + "Noted. I've removed this ward:" + cur
                + "\n" + "Now you have " + wardList.getSize()
                + " wards in the system." + System.lineSeparator() + boundary);
    }
}
