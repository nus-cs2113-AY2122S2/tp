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

    public int[] getDoctorIndexes() {
        return doctorIndexes;
    }

    public void setDoctorIndexes(int[] doctorIndexes) {
        this.doctorIndexes = doctorIndexes;
    }

    public int[] getPatientIndexes() {
        return patientIndexes;
    }

    public void setPatientIndexes(int[] patientIndexes) {
        this.patientIndexes = patientIndexes;
    }

    public int[] getNurseIndexes() {
        return nurseIndexes;
    }

    public void setNurseIndexes(int[] nurseIndexes) {
        this.nurseIndexes = nurseIndexes;
    }

    public int getNumber() {
        return wardNumber;
    }

    public void editNumber(int number) {
        this.wardNumber = number;
    }

    public String[] getDoctors() {
        String[] docNames = new String[10];
        int j = 0;
        for (int i = 0; i < 20; i++) {
            for (int doctorIndex : doctorIndexes) {
                if (i == doctorIndex) {
                    docNames[j] = Objects.requireNonNull(DoctorList.searchDoctor(Integer.toString(i))).getName();
                }
            }
        }
        return docNames;
    }

    public String[] getPatients() {
        String[] patientNames = new String[10];
        int j = 0;
        for (int i = 0; i < 20; i++) {
            for (int patientIndex : patientIndexes) {
                if (i == patientIndex) {
                    patientNames[j] = Objects.requireNonNull(PatientList.searchPatient(Integer.toString(i))).getName();
                }
            }
        }
        return patientNames;
    }

    public String[] getNurses() {
        String[] nurseNames = new String[10];
        int j = 0;
        for (int i = 0; i < 20; i++) {
            for (int nurseIndex : nurseIndexes) {
                if (i == nurseIndex) {
                    nurseNames[j] = Objects.requireNonNull(NurseList.searchNurse(Integer.toString(i))).getName();
                }
            }
        }
        return nurseNames;
    }


    @Override
    public String toString() {
        return   "Doctor: " + Arrays.toString(getDoctors()) + " || Patient: "
                + Arrays.toString(getPatients()) + " || Nurse: "
                + Arrays.toString(getNurses()) + " || Ward number: " + wardNumber;
    }
}

