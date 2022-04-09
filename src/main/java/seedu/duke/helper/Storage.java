package seedu.duke.helper;

import seedu.duke.assets.Appointment;
import seedu.duke.assets.AppointmentList;
import seedu.duke.assets.Doctor;
import seedu.duke.assets.DoctorList;
import seedu.duke.assets.List;
import seedu.duke.assets.Medicine;
import seedu.duke.assets.MedicineList;
import seedu.duke.assets.Patient;
import seedu.duke.assets.PatientList;
import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.UserInputErrorException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    static final String DIR = "data";
    static final String PATH_DOC = "data/doctor.txt";
    static final String PATH_PAT = "data/patient.txt";
    static final String PATH_MED = "data/medicine.txt";
    static final String PATH_APT = "data/appointment.txt";
    static final String PATH_APT_MEDS = "data/appointment_meds.txt";
    public DoctorList doctors = new DoctorList();
    public PatientList patients = new PatientList();
    public MedicineList medicines = new MedicineList();
    public AppointmentList appointments = new AppointmentList(patients, doctors);

    public Storage() {
        loadData();
    }

    ArrayList<String> corruptedLines = new ArrayList<>();

    private void loadGenericData(String filePath, List listType) throws FileNotFoundException {
        File data = new File(filePath);
        Scanner reader = new Scanner(data);
        String outputFilePathCorrupted = "nope";
        while (reader.hasNext()) {
            String line = reader.nextLine();
            String[] parameters = line.split(",");
            try {
                if (filePath.equals(PATH_APT_MEDS)) {
                    outputFilePathCorrupted = "data/appointment_meds_corrupted.txt";
                    appointments.loadMedicine(parameters);
                } else {
                    if (filePath.equals(PATH_DOC)) {
                        outputFilePathCorrupted = "data/doctor_corrupted.txt";
                        Validator.validateAddDoctor(parameters);
                    }
                    if (filePath.equals(PATH_PAT)) {
                        outputFilePathCorrupted = "data/patient_corrupted.txt";
                        Validator.validateAddPatient(parameters);
                    }
                    if (filePath.equals(PATH_MED)) {
                        outputFilePathCorrupted = "data/medicine_corrupted.txt";
                        Validator.validateMedicine(parameters);
                    }
                    if (filePath.equals(PATH_APT)) {
                        outputFilePathCorrupted = "data/appointment_corrupted.txt";
                        Validator.validateAddAppointment(parameters);
                    }
                    listType.add(parameters);
                }


            } catch (UserInputErrorException | DuplicateEntryException e) {
                if (e instanceof UserInputErrorException) {
                    corruptedLines.add(e + "\nLine: " + line);
                }
                // if duplicate entry just ignore
            }
        }
        if (corruptedLines.size() != 0 && !outputFilePathCorrupted.equals("nope")) {
            saveCorruptedData(outputFilePathCorrupted, corruptedLines);
            UI.printParagraph("There are some corrupted lines in your input files. "
                    + "It has been moved to another file named "
                    + outputFilePathCorrupted);
        }
    }

    private void saveCorruptedData(String filePath, ArrayList<String> stringArray) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
                UI.printParagraph("file has been created");
            } catch (IOException ioException) {
                UI.printParagraph("file cannot be created" + filePath);
                return;
            }
        }
        try {
            FileWriter dataWrite = new FileWriter(filePath,true);
            dataWrite.write("Session: " + LocalDateTime.now().toString() + "\n");
            for (String s : stringArray) {
                dataWrite.write(s + "\n");
            }
            dataWrite.write("----------------------------------------------------------\n");
            dataWrite.close();
        } catch (IOException e) {
            UI.printParagraph("Unable to save data...");
        }
    }


    public void loadData() {
        try {
            loadGenericData(PATH_DOC, doctors);
            loadGenericData(PATH_PAT, patients);
            loadGenericData(PATH_MED, medicines);
            loadGenericData(PATH_APT, appointments);
            loadGenericData(PATH_APT_MEDS, appointments);
        } catch (FileNotFoundException f) {
            UI.printParagraph("No saved data found!");
        }

    }

    //todo : clean up save file
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

    private void saveAppointmentMedData() {
        File appointmentMedFile = new File(PATH_APT_MEDS);
        if (!appointmentMedFile.exists()) {
            try {
                appointmentMedFile.createNewFile();
            } catch (IOException ioException) {
                UI.printParagraph("appointment_meds.txt cannot be created");
                return;
            }
        }
        try {
            FileWriter dataWrite = new FileWriter(PATH_APT_MEDS,false);
            for (Appointment appointment : appointments.getList()) {
                dataWrite.write(appointment.saveMedicineString() + "\n");
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
        saveAppointmentMedData();
    }
}
