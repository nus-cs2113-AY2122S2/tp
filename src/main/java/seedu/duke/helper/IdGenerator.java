package seedu.duke.helper;

public class IdGenerator {
    public static String createAppointmentId(String patientNric, String doctorNric, String appointmentDate) {
        String patientNricCode = patientNric.substring(1,4) + patientNric.substring(5,8);
        String doctorNricCode = doctorNric.substring(2,6);
        String appointmentDateCode = appointmentDate.replace("-","").substring(4,8);
        return patientNricCode + appointmentDateCode + doctorNricCode;
    }
}
