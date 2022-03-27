package seedu.duke.assets;

import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.NotFoundException;
import seedu.duke.helper.UI;

import java.util.ArrayList;

public class AppointmentList extends List {
    private ArrayList<Appointment> appointments = new ArrayList<>();

    public ArrayList<Appointment> getList() {
        return appointments;
    }

    @Override
    public void add(String[] addAppointmentParameters) throws DuplicateEntryException {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equals(addAppointmentParameters[0])) {
                throw new DuplicateEntryException("Appointment with given appointment ID already exist!");
            }
        }
        Appointment newAppointment = new Appointment(addAppointmentParameters[0], addAppointmentParameters[1],
                addAppointmentParameters[2], addAppointmentParameters[3], addAppointmentParameters[4],
                addAppointmentParameters[5], addAppointmentParameters[6]);
        appointments.add(newAppointment);
    }

    @Override
    public void remove(String appointmentId) throws NotFoundException {
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getAppointmentId().equals(appointmentId)) {
                appointments.remove(i);
                return;
            }
        }
        throw new NotFoundException("There is no appointments with the given appointment id.\n"
                + "Please search by patient's nric or doctor's nric to find out the correct id if needed.");
    }

    @Override
    public void edit(String[] parameters) throws NotFoundException {
        System.out.println("CURRENTLY APPOINTMENT EDIT DOES NOTHING.");
    }

    @Override
    public void view() {
        UI.printParagraph(toString());
    }

    @Override
    public void view(String parameters) {
        String[] parametersArray = parameters.split(",");
        String criteria = parametersArray[0].trim();
        String input = parametersArray[1].trim();
        ArrayList<Appointment> foundAppointments = new ArrayList<>();
        switch (criteria) {
        case "appointment id":
            for (int i = 0; i < appointments.size(); i++) {
                if (appointments.get(i).getAppointmentId().equals(input)) {
                    foundAppointments.add(appointments.get(i));
                }
            }
            break;
        case "patient name":
            for (int i = 0; i < appointments.size(); i++) {
                if (appointments.get(i).getPatientName().equals(input)) {
                    foundAppointments.add(appointments.get(i));
                }
            }
            break;
        case "doctor name":
            for (int i = 0; i < appointments.size(); i++) {
                if (appointments.get(i).getDoctorName().equals(input)) {
                    foundAppointments.add(appointments.get(i));
                }
            }
            break;
        case "date":
            for (int i = 0; i < appointments.size(); i++) {
                if (appointments.get(i).getAppointmentDate().equals(input)) {
                    foundAppointments.add(appointments.get(i));
                }
            }
            break;
        case "nric":
            for (int i = 0; i < appointments.size(); i++) {
                if (appointments.get(i).getPatientNric().equals(input)) {
                    foundAppointments.add(appointments.get(i));
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
        UI.printParagraph(toString(foundAppointments));
    }

    public String toString(ArrayList<Appointment> appointments) {
        if (appointments.size() == 0) {
            return "There are no appointments.";
        }
        String appointmentsString = "";
        int index = 1;
        for (Appointment appointment : appointments) {
            appointmentsString += String.format("%d. %s", index, appointment.toString());
            if (index != appointments.size()) {
                appointmentsString += "\n";
            }
            index++;
        }
        return appointmentsString;
    }
}


