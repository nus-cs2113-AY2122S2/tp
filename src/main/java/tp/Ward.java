package tp;

import java.util.Arrays;
import java.util.Objects;

public class Ward {
    protected int[] doctorIndexes;
    protected int[] patientIndexes;
    protected int[] nurseIndexes;
    protected int wardNumber;

    public Ward(int[] doctorIndexes, int[] patientIndexes, int[] nurseIndexes, int wardNumber) {
        this.doctorIndexes = doctorIndexes;
        this.patientIndexes = patientIndexes;
        this.nurseIndexes = nurseIndexes;
        this.wardNumber = wardNumber;
    }

    public int getNumber() {
        return wardNumber;
    }

    public void editNumber(int number) {
        this.wardNumber = number;
    }

    //@@author Demonshaha
    public String[] getDoctors() {
        int size = doctorIndexes.length;
        String[] docNames = new String[size];
        int j = 0;
        for (int i = 1; i <= 20; i++) {
            for (int doctorIndex : doctorIndexes) {
                if (i == doctorIndex) {
                    docNames[j++] = Objects.requireNonNull(DoctorList.getDoctor(i).getName());
                }
            }
        }
        return docNames;
    }

    //@@author cczhouqi
    public String[] getPatients() {
        int size = patientIndexes.length;
        String[] patientNames = new String[size];
        int j = 0;
        for (int i = 1; i <= 20; i++) {
            for (int patientIndex : patientIndexes) {
                if (i == patientIndex) {
                    patientNames[j++] = Objects.requireNonNull(PatientList.getPatient(i).getName());
                }
            }
        }
        return patientNames;
    }

    //@@author Demonshaha
    public String[] getNurses() {
        int size = nurseIndexes.length;
        String[] nurseNames = new String[size];
        int j = 0;
        for (int i = 1; i <= 20; i++) {
            for (int nurseIndex : nurseIndexes) {
                if (i == nurseIndex) {
                    nurseNames[j++] = Objects.requireNonNull(NurseList.getNurse(i).getName());
                }
            }
        }
        return nurseNames;
    }

    //@@author
    @Override
    public String toString() {
        return   "Doctor: " + Arrays.toString(getDoctors()) + " || Patient: "
                + Arrays.toString(getPatients()) + " || Nurse: "
                + Arrays.toString(getNurses()) + " || Ward number: " + wardNumber;
    }
}

