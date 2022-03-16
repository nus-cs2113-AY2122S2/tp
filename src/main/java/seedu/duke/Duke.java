package seedu.duke;

import tp.*;
import tp.person.Patient;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private static final Ui ui = new Ui();
    private static final Parser parser = new Parser();
    private static PatientList patientList = new PatientList();
    private static DoctorList doctorList = new DoctorList();
    private static AppointmentList appointmentList = new AppointmentList();
    private static Storage storage = new Storage();
    public static void main(String[] args) throws IHospitalException {
        ui.sayHello();
        String fullCommand = parser.getCommand();
        while(!fullCommand.equals("bye")) {
            Command command = parser.parse(fullCommand);
            command.execute(doctorList, patientList, appointmentList, ui, storage);
            fullCommand = parser.getCommand();
        }
        ui.sayGoodbye();
    }
}