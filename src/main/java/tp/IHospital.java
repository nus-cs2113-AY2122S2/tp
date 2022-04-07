package tp;

import tp.command.Command;
import tp.command.ExitCommand;

public class IHospital {
    private static final Ui ui = new Ui();
    private static final Parser parser = new Parser();

    private static DoctorList doctors = new DoctorList();
    private static PatientList patients = new PatientList();
    private static AppointmentList appointments = new AppointmentList();
    private static NurseList nurses = new NurseList();
    private static WardList wards = new WardList();

    public IHospital() {
    }

    public void run() throws IHospitalException {
        ui.sayHello();
        String fullCommand;
        fullCommand = Parser.getCommand();
        DoctorStorage doctorStorage = new DoctorStorage();
        PatientStorage patientStorage = new PatientStorage();
        AppointmentStorage appointmentStorage = new AppointmentStorage();
        NurseStorage nurseStorage = new NurseStorage();
        WardStorage wardStorage = new WardStorage();
        doctors = doctorStorage.loadDoctorList();
        nurses = nurseStorage.loadNurseList();
        wards = wardStorage.loadWardList();
        patients = patientStorage.loadPatientList();
        appointments = appointmentStorage.loadAppointmentList();

        while (!fullCommand.equals("bye")) {
            try {
                Command c = parser.parse(fullCommand);
                ui.generateResponse(c.execute(doctors, patients, nurses, wards, appointments, ui,
                        doctorStorage, wardStorage, patientStorage, nurseStorage, appointmentStorage));
                fullCommand = Parser.getCommand();
            } catch (IHospitalException e) {
                ui.generateResponse(e.getMessage());
                fullCommand = Parser.getCommand();
            }
        }
        doctorStorage.saveDoctorList(doctors);
        patientStorage.savePatientList(patients);
        appointmentStorage.saveAppointmentList(appointments);
        nurseStorage.saveNurseList(nurses);
        Command c = new ExitCommand();
        c.execute(doctors, patients, nurses, wards, appointments, ui,
                doctorStorage, wardStorage, patientStorage, nurseStorage,appointmentStorage);
        ui.sayGoodbye();
    }

    public static void main(String[] args) throws IHospitalException {
        new IHospital().run();
    }

}
