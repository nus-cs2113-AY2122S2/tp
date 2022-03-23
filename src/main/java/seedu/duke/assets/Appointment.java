package seedu.duke.assets;

public class Appointment {
    protected String patientNric;
    protected String patientName;
    protected String doctorNric;
    protected String doctorName;
    protected String appointmentDate;
    protected String appointmentDetails;

    public Appointment(String patientName, String patientNric, String doctorName, String doctorNric,
                       String appointmentDate, String appointmentDetails) {
        this.patientName = patientName;
        this.patientNric = patientNric;
        this.doctorName = doctorName;
        this.doctorNric = doctorNric;
        this.appointmentDate = appointmentDate;
        this.appointmentDetails = appointmentDetails;
    }

    public void show() {
        System.out.println("-------------------------");
        System.out.println("Patient: " + patientName + " (" + patientNric + ")");
        System.out.println("Doctor: " + doctorName + " (" + doctorNric + ")");
        System.out.println("Appointment date: " + appointmentDate);
        System.out.println("Appointment details: " + appointmentDetails);
        System.out.println("-------------------------");
    }

    public String getPatientNric() {
        return patientNric;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorNric() {
        return doctorNric;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentDetails() {
        return appointmentDetails;
    }

    public String saveString() {
        return patientNric + "," + patientName + "," + doctorNric + "," + doctorName + ","
                + appointmentDate + "," + appointmentDetails;
    }
}
