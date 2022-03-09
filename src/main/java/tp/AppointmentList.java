package tp;

import tp.person.Doctor;
import tp.person.Patient;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AppointmentList {
    public static String boundary = "____________________________________________________________" + System.lineSeparator();
    protected ArrayList<Appointment> appointments = new ArrayList<>();
    protected int countAppointment;

    public AppointmentList() {
        countAppointment = 0;
    }

    public Appointment getAppointment(int index) {
        return appointments.get(index);
    }

    /**
     * Adds an appointment to appointment list.
     *
     * @param doctor Doctor assigned for this appointment.
     * @param patient Patient coming for this appointment.
     * @param time The time reserved.
     */
    public void addAppointment(Doctor doctor, Patient patient, LocalDateTime time) {
        appointments.add(new Appointment(doctor, patient, time));
        countAppointment++;
        System.out.println(boundary + "Noted. I've added this appointment:");
        System.out.println(appointments.get(countAppointment - 1));
        System.out.print("Now you have " + countAppointment
                                 + " appointments recorded in the system." + System.lineSeparator() + boundary);
    }

    /**
    * Prints the current list of Appointment.
    */
    public void printAppointmentList() {
        System.out.println(boundary + "Here are the existing appointments:");
        for (int i = 0; i < countAppointment; i++) {
            System.out.println((i + 1) + ". " + getAppointment(i));
        }
        System.out.print("Now you have " + countAppointment
                                 + " appointments recorded in the system." + System.lineSeparator() + boundary);
    }

    /**
     * Deletes an appointment from the list
     *
     * @param index Index of the appointment to be deleted.
     */
    public void deleteAppointment(int index) {
        System.out.println(boundary + "Noted. I've removed this appointment:");
        System.out.println(appointments.get(index));
        System.out.print("Now you have " + (countAppointment - 1)
                                 + " appointments recorded in the system." + System.lineSeparator() + boundary);
        appointments.remove(index);
        countAppointment -= 1;
    }
}
