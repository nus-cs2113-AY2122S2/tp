package tp;

import tp.person.Doctor;
import tp.person.Patient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class AppointmentList {
    public static String boundary = "____________________________________________________________"
                                            + System.lineSeparator();
    protected ArrayList<Appointment> appointments = new ArrayList<>();
    protected int countAppointment;

    public AppointmentList() {
        countAppointment = 0;
    }

    public Appointment getAppointment(int index) {
        return appointments.get(index - 1);
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
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    /**
     * Deletes an appointment from the list.
     *
     * @param index Index of the appointment to be deleted.
     */
    public Appointment deleteAppointment(int index) {
        if (index <= 0 || index > appointments.size()) {
            return null;
        }
        Appointment curr = appointments.get(index - 1);
        appointments.remove(index - 1);
        countAppointment -= 1;
        return curr;
    }

    /**
     * Get the list of appointment of a given doctor.
     *
     * @param id ID of the doctor.
     * @return Appointment list of the given doctor.
     */
    public AppointmentList getAppointmentListOfDoctorById(String id) {
        AppointmentList res = new AppointmentList();
        for (Appointment appointment : appointments) {
            if (appointment.doctor.getId().equals(id)) {
                res.addAppointment(appointment);
            }
        }
        return res;
    }

    public Appointment searchAppointmentByTime(String time) {
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getTime().equals(LocalDateTime.parse(time))) {
                return appointments.get(i);
            }
        }
        return null;
    }

    public int getSize() {
        return countAppointment;
    }

    public void sortByTime() {
        Collections.sort(appointments, new Mc());
    }

    class Mc implements Comparator<Appointment> {

        @Override
        public int compare(Appointment o1, Appointment o2) {
            if (o1.time.isBefore(o2.time)) {
                return -1;
            }
            return 1;
        }
    }

    public static Comparator<LocalDateTime> myComparator = new Comparator<LocalDateTime>() {

        public int compare(LocalDateTime t1, LocalDateTime t2) {
            if (t1.isBefore(t2)) {
                return 1;
            }
            return -1;
        }
    }
            ;

    public int myComparator(LocalDateTime t1, LocalDateTime t2) {
        if (t1.isBefore(t2)) {
            return 1;
        }
        return -1;
    }

    @Override
    public String toString() {
        String toPrint = boundary + "Here are the existing appointments:" + System.lineSeparator();
        for (int i = 1; i <= countAppointment; i++) {
            toPrint += (i + ". " + getAppointment(i) + System.lineSeparator());
        }
        toPrint += ("Now you have " + countAppointment
                            + " appointments recorded in the system." + System.lineSeparator()
                            + boundary);
        return toPrint;
    }
}
