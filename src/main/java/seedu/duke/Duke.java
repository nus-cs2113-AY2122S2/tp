package seedu.duke;

import tp.DoctorList;
import tp.Parser;
import tp.PatientList;
import tp.Ui;
import tp.AppointmentList;
import tp.IHospitalException;
import tp.Command;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private static final Ui ui = new Ui();
    private static final Parser parser = new Parser();
    private static PatientList patientList = new PatientList();
    private static DoctorList doctorList = new DoctorList();
    private static AppointmentList appointmentList = new AppointmentList();
    private static Storage storage;

    static {
        try {
            storage = new Storage();
        } catch (IHospitalException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IHospitalException {
        assert false : "dummy assertion set to fail";

        ui.sayHello();
        String fullCommand = parser.getCommand();
        while (!fullCommand.equals("bye")) {
            Command command = parser.parse(fullCommand);
            command.execute(doctorList, patientList, appointmentList, ui, storage);
            fullCommand = parser.getCommand();
        }
        ui.sayGoodbye();
    }
}