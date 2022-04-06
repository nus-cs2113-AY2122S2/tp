package tp.command;

import tp.AppointmentList;
import tp.DoctorList;
import tp.DoctorStorage;
import tp.PatientList;
import tp.person.Doctor;
import tp.person.Patient;
import tp.Ui;
import tp.PatientStorage;
import tp.AppointmentStorage;
import tp.IHospitalException;

import java.time.LocalDateTime;

public class AddAppointmentCommand extends Command {
    protected int doctorIndex;
    protected int patientIndex;
    protected LocalDateTime time;

    public AddAppointmentCommand() {

    }

    public AddAppointmentCommand(int doctorIndex, int patientIndex, String time) {
        this.doctorIndex = doctorIndex;
        this.patientIndex = patientIndex;
        this.time = LocalDateTime.parse(time);
    }


    @Override
    public String execute(DoctorList doctorList, PatientList patientList,
                          AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                          PatientStorage patientStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        if (doctorIndex > doctorList.getSize()) {
            throw new IHospitalException("The doctor does not exist\n");
        }
        if (patientIndex > patientList.getSize()) {
            throw new IHospitalException("The patient does not exist\n");
        }

        Doctor doctor = (Doctor) doctorList.getDoctor(doctorIndex);
        Patient patient = (Patient) patientList.getPatient(patientIndex);
        appointmentList.addAppointment(doctor, patient, time);
        return String.format(boundary + "Noted. I've added this appointment:\n"
                                     + appointmentList.getAppointment(appointmentList.getSize()) + "\n"
                                     + "Now you have " + appointmentList.getSize()
                                     + " appointments recorded in the system." + System.lineSeparator() + boundary);
    }
}
