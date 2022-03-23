package seedu.duke.assets;

import seedu.duke.helper.UI;

import java.util.ArrayList;

public class AppointmentList {
    private ArrayList<Appointment> appointments = new ArrayList<>();

    public ArrayList<Appointment> getList() {
        return appointments;
    }

    public void add(String[] addAppointmentParameters) {
        Appointment newAppointment = new Appointment(addAppointmentParameters[0], addAppointmentParameters[1],
                addAppointmentParameters[2], addAppointmentParameters[3], addAppointmentParameters[4],
                addAppointmentParameters[5]);
        appointments.add(newAppointment);
    }

    public void showAll() {
        for (int i = 0; i < appointments.size(); i++) {
            appointments.get(i).show();
        }
    }

    public void find(String criteria, String input) {
        switch (criteria) {
        case "patient name":
            for (int i = 0; i < appointments.size(); i++) {
                if (appointments.get(i).getPatientName().equals(input)) {
                    appointments.get(i).show();
                }
            }
            break;
        case "doctor name":
            for (int i = 0; i < appointments.size(); i++) {
                if (appointments.get(i).getDoctorName().equals(input)) {
                    appointments.get(i).show();
                }
            }
            break;
        case "date":
            for (int i = 0; i < appointments.size(); i++) {
                if (appointments.get(i).getAppointmentDate().equals(input)) {
                    appointments.get(i).show();
                }
            }
            break;
        case "nric":
            for (int i = 0; i < appointments.size(); i++) {
                if (appointments.get(i).getPatientNric().equals(input)) {
                    appointments.get(i).show();
                }
            }
            break;
        default:
            UI.printParagraph("Invalid search criteria! The valid criteria are:\n"
                    + "patient name\n"
                    + "patient name\n"
                    + "doctor name\n"
                    + "date\n"
                    + "nric\n"
                    + "Please try again!");
            break;
        }
    }
}


