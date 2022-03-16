package seedu.duke.helper;

import seedu.duke.assets.DoctorList;
import seedu.duke.assets.MedicineList;
import seedu.duke.assets.PatientList;

public class Storage {
    private final String PATH_DOC = "data/doctor.txt";
    private final String PATH_PAT = "data/patient.txt";
    private final String PATH_MED = "data/medicine.txt";
    public DoctorList doctors = new DoctorList();
    public PatientList patients = new PatientList();
    public MedicineList medicines = new MedicineList();

    private void loadMedicineData() {
    }

    private void loadPatientData() {
    }

    private void loadDoctorData() {
    }

    public void loadData() {
        loadDoctorData();
        loadPatientData();
        loadMedicineData();
    }

    private void saveMedicineData() {
    }

    private void savePatientData() {
    }

    private void saveDoctorData() {
    }

    public void saveData() {
        saveDoctorData();
        savePatientData();
        saveMedicineData();
    }
}
