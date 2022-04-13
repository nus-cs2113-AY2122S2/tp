package tp;

import tp.person.Patient;

import java.util.ArrayList;

public class PatientList {
    public static String boundary = "____________________________________________________________"
                                            + System.lineSeparator();
    protected static ArrayList<Patient> patients = new ArrayList<>();
    protected int countPatient;

    public PatientList() {
        countPatient = 0;
    }

    public static void addPatientDescription(String description, int index) {
        Patient patient = patients.get(index - 1);
        patient.addDescription(description);
    }

    public static Patient getPatient(int index) {
        return patients.get(index - 1);
    }

    public int getSize() {
        return countPatient;
    }

    /**
     * Add a patient to the list.
     *
     * @param patient Patient to be added.
     * @throws IHospitalException If ID is the same as other patients.
     */
    public void addPatient(Patient patient) throws IHospitalException {
        boolean isDuplicate = false;
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getId().trim().equals(patient.getId())) {
                isDuplicate = true;
            }
        }

        if (isDuplicate) {
            throw new IHospitalException("Patient with this ID already exists.\n");
        } else {
            patients.add(patient);
            countPatient++;
        }
    }

    /**
     * Deletes a patient from the list.
     *
     * @param index Index of the patient to be deleted.
     */
    public Patient deletePatient(int index) {
        if (index <= 0 || index > patients.size()) {
            return null;
        }
        Patient curr = patients.get(index - 1);
        patients.remove(index - 1);
        countPatient -= 1;
        return curr;
    }

    public static Patient searchPatient(String id) {
        for (Patient patient : patients) {
            if (patient.getId().trim().equals(id)) {
                return patient;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String toPrint = boundary + "Here are the patients recorded:" + System.lineSeparator();
        for (int i = 1; i <= countPatient; i++) {
            toPrint += (i + ". " + getPatient(i) + System.lineSeparator());
        }
        toPrint += ("You have " + countPatient + " patients recorded in the system."
                            + System.lineSeparator() + boundary);
        return toPrint;
    }
}

