package seedu.duke.helper;


import seedu.duke.assets.Appointment;
import seedu.duke.assets.AppointmentList;
import seedu.duke.assets.Doctor;
import seedu.duke.assets.DoctorList;
import seedu.duke.assets.Medicine;
import seedu.duke.assets.MedicineList;
import seedu.duke.assets.Patient;
import seedu.duke.assets.PatientList;
import seedu.duke.exception.DuplicateEntryException;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    static final String DIR = "data";
    static final String PATH_DOC = "data/doctor.txt";
    static final String PATH_PAT = "data/patient.txt";
    static final String PATH_MED = "data/medicine.txt";
    static final String PATH_APT = "data/appointment.txt";
    public DoctorList doctors = new DoctorList();
    public PatientList patients = new PatientList();
    public MedicineList medicines = new MedicineList();
    public AppointmentList appointments = new AppointmentList();

    public Storage() {
        loadData();
    }

    private void loadMedicineData() throws FileNotFoundException {
        File data = new File(PATH_MED);
        Scanner reader = new Scanner(data);
        while (reader.hasNext()) {
            String line = reader.nextLine();
            String[] parameters = line.split(",");
            try {
                medicines.add(parameters);
            } catch (DuplicateEntryException e) {
                continue;
            }
        }
    }

    private void loadPatientData() throws FileNotFoundException {
        File data = new File(PATH_PAT);
        Scanner reader = new Scanner(data);
        while (reader.hasNext()) {
            String line = reader.nextLine();
            String[] parameters = line.split(",");
            try {
                patients.add(parameters);
            } catch (DuplicateEntryException e) {
                continue;
            }

        }
    }

    private void loadDoctorData() throws FileNotFoundException {
        File data = new File(PATH_DOC);
        Scanner reader = new Scanner(data);
        while (reader.hasNext()) {
            String line = reader.nextLine();
            String[] parameters = line.split(",");
            try {
                doctors.add(parameters);
            } catch (DuplicateEntryException e) {
                continue;
            }
        }
    }

    private void loadAppointmentData() throws FileNotFoundException {
        File data = new File(PATH_APT);
        Scanner reader = new Scanner(data);
        while (reader.hasNext()) {
            String line = reader.nextLine();
            String[] parameters = line.split(",");
            appointments.add(parameters);
        }
    }

    public void loadData() {
        try {
            loadDoctorData();
            loadPatientData();
            loadMedicineData();
            loadAppointmentData();
        } catch (FileNotFoundException f) {
            UI.printParagraph("No saved data found!");
        }

    }

    private void saveMedicineData() {
        File medicineFile = new File(PATH_MED);
        if (!medicineFile.exists()) {
            try {
                medicineFile.createNewFile();
            } catch (IOException ioException) {
                UI.printParagraph("medicine.txt cannot be created");
                return;
            }
        }
        try {
            FileWriter dataWrite = new FileWriter(PATH_MED,false);
            for (Medicine m : medicines.getList()) {
                dataWrite.write(m.saveString() + "\n");
            }
            dataWrite.close();
        } catch (IOException e) {
            UI.printParagraph("Unable to save data...");
        }
    }

    private void savePatientData() {
        File patientFile = new File(PATH_PAT);
        if (!patientFile.exists()) {
            try {
                patientFile.createNewFile();
            } catch (IOException ioException) {
                UI.printParagraph("patient.txt cannot be created");
                return;
            }
        }
        try {
            FileWriter dataWrite = new FileWriter(PATH_PAT,false);
            for (Patient p : patients.getList()) {
                dataWrite.write(p.saveString() + "\n");
            }
            dataWrite.close();
        } catch (IOException e) {
            UI.printParagraph("Unable to save data...");
        }
    }

    private void saveAppointmentData() {
        File appointmentFile = new File(PATH_APT);
        if (!appointmentFile.exists()) {
            try {
                appointmentFile.createNewFile();
            } catch (IOException ioException) {
                UI.printParagraph("appointment.txt cannot be created");
                return;
            }
        }
        try {
            FileWriter dataWrite = new FileWriter(PATH_APT,false);
            for (Appointment appointment : appointments.getList()) {
                dataWrite.write(appointment.saveString() + "\n");
            }
            dataWrite.close();
        } catch (IOException e) {
            UI.printParagraph("Unable to save data...");
        }
    }

    private void saveDoctorData() {
        File doctorFile = new File(PATH_DOC);
        if (!doctorFile.exists()) {
            try {
                doctorFile.createNewFile();
            } catch (IOException ioException) {
                UI.printParagraph("doctor.txt cannot be created");
                return;
            }
        }
        try {
            FileWriter dataWrite = new FileWriter(PATH_DOC,false);
            for (Doctor d : doctors.getList()) {
                dataWrite.write(d.saveString() + "\n");
            }
            dataWrite.close();
        } catch (IOException e) {
            UI.printParagraph("Unable to save data...");
        }
    }

    public void saveData() {
        File dir = new File(DIR);
        try {
            dir.mkdir();
        } catch (SecurityException s) {
            UI.printParagraph("Cannot Create Dir");
        }
        saveDoctorData();
        savePatientData();
        saveMedicineData();
        saveAppointmentData();
    }
}
