package seedu.duke.assets;

import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.NotFoundException;
import seedu.duke.helper.UI;
import seedu.duke.helper.command.CommandLineTable;

import java.util.ArrayList;

public class AppointmentList extends List {
    private ArrayList<Appointment> appointments = new ArrayList<>();

    public ArrayList<Appointment> getList() {
        return appointments;
    }

    public int getSize() {
        return appointments.size();
    }

    @Override
    public void add(String[] addAppointmentParameters) throws DuplicateEntryException {
        int numberOfAppointmentsBefore = appointments.size();
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equals(addAppointmentParameters[0])) {
                throw new DuplicateEntryException("Appointment with given appointment ID already exist!");
            }
        }
        Appointment newAppointment = new Appointment(addAppointmentParameters[0], addAppointmentParameters[1],
                addAppointmentParameters[2], addAppointmentParameters[3], addAppointmentParameters[4],
                addAppointmentParameters[5], addAppointmentParameters[6]);
        appointments.add(newAppointment);
        assert appointments.size() == numberOfAppointmentsBefore + 1;
    }

    @Override
    public void remove(String appointmentId) throws NotFoundException {
        int numberOfAppointmentsBefore = appointments.size();
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getAppointmentId().equals(appointmentId)) {
                appointments.remove(i);
                assert appointments.size() == numberOfAppointmentsBefore - 1;
                return;
            }
        }
        throw new NotFoundException("There is no appointment with the given appointment id.\n"
                + "Please search by patient's nric or doctor's nric to find out the correct id if needed.");
    }

    @Override
    public void edit(String[] parameters) throws NotFoundException {
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getAppointmentId().equals(parameters[0])) {
                appointments.remove(i);
                Appointment editAppointment = new Appointment(parameters[0], parameters[1],
                        parameters[2], parameters[3], parameters[4],
                        parameters[5], parameters[6]);
                appointments.add(editAppointment);
                return;
            }
        }
        throw new NotFoundException("There is no appointment with the given appointment id.\n"
                + "Please search by patient's nric or doctor's nric to find out the correct id if needed.");
    }

    @Override
    public void view() {
        CommandLineTable appointmentTable = new CommandLineTable();
        appointmentTable.setShowVerticalLines(true);
        appointmentTable.setHeaders("Appointment Id", "Patient Name", "Patient NRIC", "Doctor Name", "Doctor NRIC",
                "Appointment Date", "Appointment Details");
        for (Appointment appointment : appointments) {
            appointmentTable.addRow(appointment.getAppointmentId(), appointment.getPatientName(),
                    appointment.getPatientNric(), appointment.getDoctorName(), appointment.getDoctorNric(),
                    appointment.getAppointmentDate(), appointment.getAppointmentDetails());
        }
        appointmentTable.print();
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
                    assert foundAppointments.size() != 0;
                }
            }
            break;
        case "patient name":
            for (int i = 0; i < appointments.size(); i++) {
                if (appointments.get(i).getPatientName().equals(input)) {
                    foundAppointments.add(appointments.get(i));
                    assert foundAppointments.size() != 0;
                }
            }
            break;
        case "doctor name":
            for (int i = 0; i < appointments.size(); i++) {
                if (appointments.get(i).getDoctorName().equals(input)) {
                    foundAppointments.add(appointments.get(i));
                    assert foundAppointments.size() != 0;
                }
            }
            break;
        case "date":
            for (int i = 0; i < appointments.size(); i++) {
                if (appointments.get(i).getAppointmentDate().equals(input)) {
                    foundAppointments.add(appointments.get(i));
                    assert foundAppointments.size() != 0;
                }
            }
            break;
        case "patient nric":
            for (int i = 0; i < appointments.size(); i++) {
                if (appointments.get(i).getPatientNric().equals(input)) {
                    foundAppointments.add(appointments.get(i));
                    assert foundAppointments.size() != 0;
                }
            }
            break;
        case "doctor nric":
            for (int i = 0; i < appointments.size(); i++) {
                if (appointments.get(i).getDoctorNric().equals(input)) {
                    foundAppointments.add(appointments.get(i));
                    assert foundAppointments.size() != 0;
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
            assert foundAppointments.size() == 0;
            return;
        }
        CommandLineTable appointmentTable = new CommandLineTable();
        appointmentTable.setShowVerticalLines(true);
        appointmentTable.setHeaders("Appointment Id", "Patient Name", "Patient NRIC", "Doctor Name", "Doctor NRIC",
                "Appointment Date", "Appointment Details");
        for (Appointment appointment : foundAppointments) {
            appointmentTable.addRow(appointment.getAppointmentId(), appointment.getPatientName(),
                    appointment.getPatientNric(), appointment.getDoctorName(), appointment.getDoctorNric(),
                    appointment.getAppointmentDate(), appointment.getAppointmentDetails());
        }
        appointmentTable.print();
    }

}


