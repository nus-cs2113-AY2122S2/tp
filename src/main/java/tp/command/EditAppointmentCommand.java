package tp.command;

import tp.*;

import java.time.LocalDateTime;

public class EditAppointmentCommand extends Command {
    private int index;
    private String type;
    private String newInformation;

    public EditAppointmentCommand(int index, String type, String newInformation) {
        this.index = index;
        this.type = type;
        this.newInformation = newInformation;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                          AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                          PatientStorage patientStorage,NurseStorage nurseStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        if (index > appointmentList.getSize()) {
            throw new IHospitalException("The appointment does not exist.\n");
        }

        if (type.contains("d")) {
            int doctorIndex = Integer.parseInt(newInformation.trim());
            if (doctorIndex > doctorList.getSize()) {
                throw new IHospitalException("The doctor does not exist.\n");
            }
            appointmentList.getAppointment(index).editDoctor(doctorList.getDoctor(doctorIndex));
            return String.format(boundary + "Updated already!\n"
                                         + appointmentList.getAppointment(index) + "\n" + boundary);
        } else if (type.contains("p")) {
            int patientIndex = Integer.parseInt(newInformation.trim());
            if (patientIndex > patientList.getSize()) {
                throw new IHospitalException("The patient does not exist.\n");
            }
            appointmentList.getAppointment(index).editPatient(patientList.getPatient(patientIndex));
            return String.format(boundary + "Updated already!\n"
                                         + appointmentList.getAppointment(index) + "\n" + boundary);
        } else if (type.contains("t")) {
            LocalDateTime time = LocalDateTime.parse(newInformation);
            appointmentList.getAppointment(index).editTime(time);
            return String.format(boundary + "Updated already!\n"
                                         + appointmentList.getAppointment(index) + "\n" + boundary);
        } else {
            return null;
        }
    }
}
