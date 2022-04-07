package tp.command;

import tp.*;
import tp.person.Doctor;
import tp.person.Nurse;
import tp.person.Patient;

public class AddWardCommand extends Command{
    protected int doctorIndex;
    protected int patientIndex;
    protected int nurseIndex;
    protected int wardNumber;

    public AddWardCommand() {
    }

    public AddWardCommand(int doctorIndex, int patientIndex, int nurseIndex, int wardNumber) {
        this.doctorIndex = doctorIndex;
        this.patientIndex = patientIndex;
        this.nurseIndex = nurseIndex;
        this.wardNumber = wardNumber;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                          WardList wardList, AppointmentList appointmentList, Ui ui,
                          DoctorStorage doctorStorage, WardStorage wardStorage,
                          PatientStorage patientStorage, NurseStorage nurseStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        if (wardNumber <= 0 || wardNumber > wardList.getSize()) {
            throw new IHospitalException("The ward does not exist\n");
        }
        if (patientIndex <= 0 || patientIndex > patientList.getSize()) {
            throw new IHospitalException("The patient does not exist\n");
        }
        if(doctorIndex <= 0 || doctorIndex > doctorList.getSize()){
            throw new IHospitalException("The doctor does not exist\n");
        }
        if(nurseIndex <= 0 || nurseIndex > nurseList.getSize()){
            throw new IHospitalException("The nurse does not exist\n");
        }

        Doctor doctor =  doctorList.getDoctor(doctorIndex);
        Patient patient = patientList.getPatient(patientIndex);
        Nurse nurse = nurseList.getNurse(nurseIndex);
        String WardNumber = String.valueOf(wardNumber);
        wardList.addWard(doctor, patient, nurse,WardNumber);
        return String.format(boundary + "Noted. I've added this ward:"
                + "\n" + wardList.getWard(wardList.getSize())
                + "\n" + "Now you have " + wardList.getSize()
                + " wards recorded in the system." + System.lineSeparator() + boundary);
    }

}
