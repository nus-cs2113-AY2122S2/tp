package tp;

import tp.person.Patient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class PatientStorage {
    private static final String root = System.getProperty("user.dir");
    private static final Path filePath = Paths.get(root, "data", "IHospitalPatients.txt");
    private static final Path dirPath = Paths.get(root, "data");

    /**
     * The constructor of storage.
     * @throws IHospitalException IHospitalException
     */
    //@@author Demonshaha
    public PatientStorage() throws IHospitalException {
        try {
            File fileDirectory = new File(dirPath.toString());
            if (!fileDirectory.exists()) {
                fileDirectory.mkdir();
            }

            File dataFile = new File(filePath.toString());
            dataFile.createNewFile();
        } catch (IOException e) {
            throw new IHospitalException("FILE ERROR");
        }
    }

    /**
     * This function is to save patients.
     * @param patients doctor list
     * @throws IHospitalException IHospitalException
     */
    //@@author Demonshaha
    public void savePatientList(PatientList patients) throws IHospitalException {
        try {
            FileWriter fw = new FileWriter(filePath.toString());
            int amount = patients.getSize();
            fw.write(String.format("%d\n", amount));
            for (int i = 1; i <= amount; i++) {
                Patient currPatient = (Patient) patients.getPatient(i);
                fw.write(String.format("%d. Patient:\n", i));
                fw.write(currPatient.getId() + "\n");
                fw.write(currPatient.getName() + "\n");
                fw.write(currPatient.getPhoneNumber() + "\n");
                fw.write(currPatient.getEmail() + "\n");
                fw.write(currPatient.getSymptom() + "\n");
                fw.write(currPatient.getDescription() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new IHospitalException("Cannot save task list to file: " + filePath.toString());
        }
    }

    /**
     * A method that will load duke.txt and store the taskList into the current duke's taskList
     * @return The taskList of the duke.txt
     * @throws IHospitalException IHospitalException
     */
    //@@author Demonshaha
    public PatientList loadPatientList() throws IHospitalException {
        try {
            File dataFile = new File(filePath.toString());
            Scanner scanner = new Scanner(dataFile);
            PatientList result = new PatientList();
            if (!scanner.hasNext()) {
                return result;
            }
            int n = scanner.nextInt();
            scanner.nextLine();//read enter
            String data;

            for (int i = 0; i < n; i++) {
                data = scanner.nextLine();
                String id = scanner.nextLine();
                String name = scanner.nextLine();
                String phoneNumber = scanner.nextLine();
                String email = scanner.nextLine();
                String symptom = scanner.nextLine();
                String description = scanner.nextLine();
                Patient patient = new Patient(id, name, phoneNumber, email, symptom, description);
                result.addPatient(patient);
            }
            return result;
        } catch (FileNotFoundException e) {
            throw new IHospitalException("Cannot load task list from file: " + filePath.toString());
        }
    }
}
