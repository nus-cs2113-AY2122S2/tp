package tp.command;

import tp.*;

public class EditNurseCommand extends Command {
        private int index;
        private String type;
        private String newInformation;

        public EditNurseCommand(int index, String type, String newInformation) {
            this.index = index;
            this.type = type;
            this.newInformation = newInformation;
        }

        @Override
        public String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                              AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                              PatientStorage patientStorage, NurseStorage nurseStorage,
                              AppointmentStorage appointmentStorage) throws IHospitalException {
            if (index > nurseList.getSize()) {
                throw new IHospitalException("The nurse does not exist\n");
            }

            if (type.contains("e")) {
                nurseList.getNurse(index).editEmail(newInformation);
                return String.format(boundary + "Updated already!\n" + doctorList.getDoctor(index) + "\n" + boundary);
            } else if (type.contains("ph")) {
                nurseList.getNurse(index).editPhoneNumber(newInformation);
                return String.format(boundary + "Updated already!\n" + doctorList.getDoctor(index) + "\n" + boundary);
            } else {
                nurseList.getNurse(index).editName(newInformation);
                return String.format(boundary + "Updated already!\n" + doctorList.getDoctor(index) + "\n" + boundary);
            }
        }
}
