package seedu.duke.helper;

import seedu.duke.assets.DoctorList;
import seedu.duke.assets.Medicine;
import seedu.duke.assets.MedicineList;
import seedu.duke.assets.PatientList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;

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
        File medicineFile = new File(PATH_MED);
        if (!medicineFile.exists()) {
            try {
                medicineFile.createNewFile();
            } catch (IOException ioException) {
                System.out.println("medicine.txt cannot be created");
                return;
            }
        }
        try {
            FileWriter dataWrite = new FileWriter(PATH_MED,false);
            for(Medicine m : ) {
                dataWrite.write(a.saveString() + "\n");
            }
            dataWrite.close();
        } catch (IOException e) {
            System.out.println("Unable to save data...");
        }
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
