package tp;

import tp.person.Doctor;
import tp.person.Patient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class AppointmentList {
    public static String boundary = "____________________________________________________________"
                                            + System.lineSeparator();
    protected ArrayList<Appointment> appointments;
    protected int countAppointment;

    public AppointmentList() {
        appointments = new ArrayList<>();
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
    public void addAppointment(Doctor doctor, Patient patient, LocalDateTime time) throws IHospitalException {
        if (searchAppointmentByTime(time) != null) {
            Doctor busyDoctor = searchAppointmentByTime(time).getDoctor();
            if (Objects.equals(doctor.getId(), busyDoctor.getId())) {
                throw new IHospitalException("This doctor is busy at this time.\n");
            }
        }
        appointments.add(new Appointment(doctor, patient, time));
        countAppointment++;
    }

    public void addAppointment(Appointment appointment) throws IHospitalException {
        LocalDateTime time = appointment.getTime();
        Doctor doctor = appointment.getDoctor();
        if (searchAppointmentByTime(time) != null) {
            Doctor busyDoctor = searchAppointmentByTime(time).getDoctor();
            if (Objects.equals(doctor.getId(), busyDoctor.getId())) {
                throw new IHospitalException("This doctor is busy at this time.\n");
            }
        }
        appointments.add(appointment);
        countAppointment++;
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
    public AppointmentList getAppointmentListOfDoctorById(String id) throws IHospitalException {
        AppointmentList res = new AppointmentList();
        for (Appointment appointment : appointments) {
            if (appointment.getDoctor().getId().contains(id)) {
                res.addAppointment(appointment);
            }
        }
        return res;
    }

    public AppointmentList getAppointmentListOfPatientById(String id) throws IHospitalException {
        AppointmentList patientBookings = new AppointmentList();
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getId().contains(id)) {
                patientBookings.addAppointment(appointment);
            }
        }
        return patientBookings;
    }

    public Appointment searchAppointmentByTime(String time) {
        for (Appointment appointment : appointments) {
            if (appointment.getTime().equals(LocalDateTime.parse(time))) {
                return appointment;
            }
        }
        return null;
    }

    public Appointment searchAppointmentByTime(LocalDateTime time) {
        for (Appointment appointment : appointments) {
            if (appointment.getTime().equals(time)) {
                return appointment;
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
        toPrint += ("You have " + countAppointment
                            + " appointments recorded in the system." + System.lineSeparator()
                            + boundary);
        return toPrint;
    }
}
