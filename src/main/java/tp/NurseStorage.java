
package tp;

import tp.person.Nurse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

//@@author   Demonshaha
public class NurseStorage {
    private static final String root = System.getProperty("user.dir");
    private static final Path filePath = Paths.get(root, "data", "IHospitalNurses.txt");
    private static final Path dirPath = Paths.get(root, "data");

    /**
     * The constructor of storage.
     * @throws IHospitalException IHospitalException
     *
     */
    //@@author   Demonshaha
    public NurseStorage() throws IHospitalException {
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
     * The function to save Nurse list.
     * @param nurses nurse list
     * @throws IHospitalException IHospitalException
     */
    //@@author  Demonshaha
    public void saveNurseList(NurseList nurses) throws IHospitalException {
        try {
            FileWriter fw = new FileWriter(filePath.toString());
            int amount = nurses.getSize();
            fw.write(String.format("%d\n", amount));
            for (int i = 1; i <= amount; i++) {
                Nurse curNurse =  nurses.getNurse(i);
                fw.write(String.format("%d. Nurse:\n", i));
                fw.write(curNurse.getId() + "\n");
                fw.write(curNurse.getName() + "\n");
                fw.write(curNurse.getPhoneNumber() + "\n");
                fw.write(curNurse.getEmail() + "\n");
                //@@ author  DolphXty
                fw.write(curNurse.getTitle() + "\n");
                fw.write(curNurse.getWardNumber() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new IHospitalException("Cannot save task list to file: " + filePath.toString());
        }
    }

    /**
     * The function to load Nurse list.
     * @return The nurseList of the IHospitalNurses.txt
     * @throws IHospitalException IHospitalException
     */
    //@@author Demonshaha
    public NurseList loadNurseList() throws IHospitalException {
        try {
            File dataFile = new File(filePath.toString());
            Scanner scanner = new Scanner(dataFile);
            NurseList result = new NurseList();
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
                String title = scanner.nextLine();
                int wardNumber = Integer.parseInt(scanner.nextLine());
                Nurse nurse = new Nurse(id, name, phoneNumber, email, title, wardNumber);
                result.addNurse(nurse);
            }
            return result;
        } catch (FileNotFoundException e) {
            throw new IHospitalException("Cannot load task list from file: " + filePath.toString());
        }
    }
}
