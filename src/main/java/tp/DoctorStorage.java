package tp;

import tp.person.Doctor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
//@@author Demonshaha

public class DoctorStorage {
    private static final String root = System.getProperty("user.dir");
    private static final Path filePath = Paths.get(root, "data", "IHospitalDoctors.txt");
    private static final Path dirPath = Paths.get(root, "data");

    //@@author Demonshaha
    /**
     * The constructor of storage.
     * @throws IHospitalException IHospitalException
     */
    public DoctorStorage() throws IHospitalException {
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
     * The function to save Doctor list.
     * @param doctors doctor list
     * @throws IHospitalException IHospitalException
     */
    //@@author Demonshaha
    public void saveDoctorList(DoctorList doctors) throws IHospitalException {
        try {
            FileWriter fw = new FileWriter(filePath.toString());
            int amount = doctors.getSize();
            fw.write(String.format("%d\n", amount));
            for (int i = 1; i <= amount; i++) {
                Doctor currDoctor = (Doctor) doctors.getDoctor(i);
                fw.write(String.format("%d. Doctor:\n", i));
                fw.write(currDoctor.getId() + "\n");
                fw.write(currDoctor.getName() + "\n");
                fw.write(currDoctor.getPhoneNumber() + "\n");
                fw.write(currDoctor.getEmail() + "\n");
                fw.write(currDoctor.getDepartment() + "\n");
                fw.write(currDoctor.getWardNumber());
            }
            fw.close();
        } catch (IOException e) {
            throw new IHospitalException("Cannot save task list to file: " + filePath.toString());
        }
    }

    /**
     * A method that will load duke.txt and store the taskList into the current duke's taskList
     * @return The doctorList of the IHospitalDoctors.txt
     * @throws IHospitalException IHospitalException
     */
    //@@author Demonshaha
    public DoctorList loadDoctorList() throws IHospitalException {
        try {
            File dataFile = new File(filePath.toString());
            Scanner scanner = new Scanner(dataFile);
            DoctorList result = new DoctorList();
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
                String department = scanner.nextLine();
                int wardNumber = Integer.parseInt(scanner.nextLine());
                Doctor doctor = new Doctor(id, name, phoneNumber, email, department, wardNumber);
                result.addDoctor(doctor);
            }
            return result;
        } catch (FileNotFoundException e) {
            throw new IHospitalException("Cannot load task list from file: " + filePath.toString());
        }
    }
}
