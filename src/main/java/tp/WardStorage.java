package tp;

import tp.person.Doctor;
import tp.person.Nurse;
import tp.person.Patient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;



public class WardStorage {
    private static final String root = System.getProperty("user.dir");
    private static final Path filePath = Paths.get(root, "data", "IHospitalDoctors.txt");
    private static final Path dirPath = Paths.get(root, "data");

    /**
     * The constructor of storage.
     * @throws IHospitalException IHospitalException
     *
     */
    //@@author   DemonShaha
    public WardStorage() throws IHospitalException {
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
     * The function to save Ward list.
     * @param wards new ward
     * @throws IHospitalException IHospitalException
     */
    //@@author  DemonShaha
    public void saveWardList(WardList wards) throws IHospitalException {
        try {
            FileWriter fw = new FileWriter(filePath.toString());
            int amount = wards.getSize();
            fw.write(String.format("%d\n", amount));
            for (int i = 1; i <= amount; i++) {
                Ward curWard =  wards.getWard(i);
                fw.write(String.format("%d. Nurse:\n", i));
                //@@ author  DolphXty
                fw.write(curWard.getDoctor() + "\n");
                fw.write(curWard.getPatient() + "\n");
                fw.write(curWard.getNurse() + "\n");
                fw.write(curWard.getNumber() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new IHospitalException("Cannot save task list to file: " + filePath.toString());
        }
    }

    /**
     * The method is to load ward list.
     * @return The wardList of the IHospitalWard.txt
     * @throws IHospitalException IHospitalException
     */
    //@@author DemonShaha
    public WardList loadWardList() throws IHospitalException {
        try {
            File dataFile = new File(filePath.toString());
            Scanner scanner = new Scanner(dataFile);
            WardList result = new WardList();
            if (!scanner.hasNext()) {
                return result;
            }
            int n = scanner.nextInt();
            scanner.nextLine();//read enter
            String data;

            for (int i = 0; i < n; i++) {
                data = scanner.nextLine();
                String dId = scanner.nextLine();
                String dName = scanner.nextLine();
                String dPhoneNumber = scanner.nextLine();
                String dEmail = scanner.nextLine();
                String dWardNumber = scanner.nextLine();
                String id = scanner.nextLine();
                String name = scanner.nextLine();
                String phoneNumber = scanner.nextLine();
                String email = scanner.nextLine();
                String symptom = scanner.nextLine();
                String description = scanner.nextLine();
                Doctor doctor = new Doctor(dId, dName, dPhoneNumber, dEmail, dWardNumber);
                Patient patient = new Patient(id, name, phoneNumber, email, symptom, description);
                id = scanner.nextLine();
                name = scanner.nextLine();
                phoneNumber = scanner.nextLine();
                email = scanner.nextLine();
                String title = scanner.nextLine();
                String wardNumber = scanner.nextLine();
                Nurse nurse = new Nurse(id, name, phoneNumber, email, title, wardNumber);
                wardNumber = scanner.nextLine();
                result.addWard(doctor, patient, nurse, wardNumber);
            }
            return result;
        } catch (FileNotFoundException e) {
            throw new IHospitalException("Cannot load task list from file: " + filePath.toString());
        }
    }
}
