package seedu.duke.helper.finder;

import seedu.duke.assets.Appointment;

import java.util.ArrayList;

public class AppointmentFinder {
    public static ArrayList<Appointment> findAppointmentById(ArrayList<Appointment> appointments,
                                                             String appointmentId) {
        ArrayList<Appointment> appointmentArrayList = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equals(appointmentId)) {
                appointmentArrayList.add(appointment);
            }
        }
        return getAppointments(appointmentArrayList);
    }

    public static ArrayList<Appointment> findAppointmentByPatientName(ArrayList<Appointment> appointments,
                                                                      String patientName) {
        ArrayList<Appointment> appointmentArrayList = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatientName().contains(patientName)) {
                appointmentArrayList.add(appointment);
            }
        }
        return getAppointments(appointmentArrayList);
    }

    public static ArrayList<Appointment> findAppointmentByPatientNric(ArrayList<Appointment> appointments,
                                                                      String patientNric) {
        ArrayList<Appointment> appointmentArrayList = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatientNric().equals(patientNric)) {
                appointmentArrayList.add(appointment);
            }
        }
        return getAppointments(appointmentArrayList);
    }

    public static ArrayList<Appointment> findAppointmentByDoctorName(ArrayList<Appointment> appointments,
                                                                     String doctorName) {
        ArrayList<Appointment> appointmentArrayList = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorName().contains(doctorName)) {
                appointmentArrayList.add(appointment);
            }
        }
        return getAppointments(appointmentArrayList);
    }

    public static ArrayList<Appointment> findAppointmentByDoctorNric(ArrayList<Appointment> appointments,
                                                                     String doctorNric) {
        ArrayList<Appointment> appointmentArrayList = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorNric().equals(doctorNric)) {
                appointmentArrayList.add(appointment);
            }
        }
        return getAppointments(appointmentArrayList);
    }

    public static ArrayList<Appointment> findAppointmentByDate(ArrayList<Appointment> appointments,
                                                               String date) {
        ArrayList<Appointment> appointmentArrayList = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentDate().equals(date)) {
                appointmentArrayList.add(appointment);
            }
        }
        return getAppointments(appointmentArrayList);
    }

    private static ArrayList<Appointment> getAppointments(ArrayList<Appointment> appointmentArrayList) {
        if (appointmentArrayList.isEmpty()) {
            return null;
        } else {
            return appointmentArrayList;
        }
    }
}
