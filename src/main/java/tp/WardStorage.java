package tp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


//@@author Demonshaha
public class WardStorage {
    private static final String root = System.getProperty("user.dir");
    private static final Path filePath = Paths.get(root, "data", "IHospitalWards.txt");
    private static final Path dirPath = Paths.get(root, "data");

    /**
     * The constructor of storage.
     * @throws IHospitalException IHospitalException
     *
     */
    //@@author Demonshaha
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
    //@@author Demonshaha
    public void saveWardList(WardList wards) throws IHospitalException {
        try {
            FileWriter fw = new FileWriter(filePath.toString());
            int amount = wards.getSize();
            fw.write(String.format("%d\n", amount));
            for (int i = 1; i <= amount; i++) {
                Ward curWard = wards.getWard(i);
                fw.write(String.format("%d. Ward:\n", i));
                fw.write(String.format("Doctor: ") + Arrays.toString(curWard.getDoctorIndexes()) + "\n");
                fw.write(String.format("Patient: ") + Arrays.toString(curWard.getPatientIndexes()) + "\n");
                fw.write(String.format("Nurse: ") + Arrays.toString(curWard.getNurseIndexes()) + "\n");
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

    //@@author Demonshaha
    public WardList loadWardList() throws IHospitalException {
        try {
            File dataFile = new File(filePath.toString());
            Scanner scanner = new Scanner(dataFile);
            WardList result = new WardList();
            if (!scanner.hasNext()) {
                return result;
            }
            int n = scanner.nextInt();
            scanner.nextLine();
            String data;

            int[] arr1 = new int[10000];
            int num1 = 0;
            int[] arr2 = new int[10000];
            int num2 = 0;
            int[] arr3 = new int[10000];
            int num3 = 0;

            for (int i = 0; i < n; i++) {
                num1 = 0;
                num2 = 0;
                num3 = 0;
                Arrays.fill(arr1, 0);
                Arrays.fill(arr2, 0);
                Arrays.fill(arr3, 0);
                data = scanner.nextLine();
                String doctorString = scanner.nextLine();
                int frontIndex = doctorString.indexOf("[") + 1;
                int behindIndex = doctorString.indexOf(",");
                if (behindIndex == -1) {
                    behindIndex = doctorString.indexOf("]");
                }

                while (frontIndex < behindIndex) {
                    arr1[num1++] = (Integer.parseInt(doctorString.substring(frontIndex, behindIndex).trim()));
                    frontIndex = behindIndex + 2;
                    behindIndex = doctorString.indexOf(",", frontIndex);
                    if (behindIndex == -1) {
                        behindIndex = doctorString.indexOf("]");
                    }
                }

                String patientString = scanner.nextLine();
                frontIndex = patientString.indexOf("[") + 1;
                behindIndex = patientString.indexOf(",");
                if (behindIndex == -1) {
                    behindIndex = patientString.indexOf("]");
                }

                while (frontIndex < behindIndex) {
                    arr2[num2++] = (Integer.parseInt(patientString.substring(frontIndex, behindIndex).trim()));
                    frontIndex = behindIndex + 2;
                    behindIndex = patientString.indexOf(",", frontIndex);
                    if (behindIndex == -1) {
                        behindIndex = patientString.indexOf("]");
                    }
                }

                String nurseString = scanner.nextLine();
                frontIndex = nurseString.indexOf("[") + 1;
                behindIndex = nurseString.indexOf(",");
                if (behindIndex == -1) {
                    behindIndex = nurseString.indexOf("]");
                }
                while (frontIndex < behindIndex) {
                    arr3[num3++] = (Integer.parseInt(nurseString.substring(frontIndex, behindIndex).trim()));
                    frontIndex = behindIndex + 2;
                    behindIndex = nurseString.indexOf(",", frontIndex);
                    if (behindIndex == -1) {
                        behindIndex = nurseString.indexOf("]");
                    }
                }

                String num = scanner.nextLine();
                int wardNumber = Integer.parseInt(num);
                result.addWard(Arrays.copyOf(arr1, num1),
                        Arrays.copyOf(arr2, num2), Arrays.copyOf(arr3, num3), wardNumber);
            }

            return result;
        } catch (FileNotFoundException e) {
            throw new IHospitalException("Cannot load task list from file: " + filePath.toString());
        }
    }
}
