package tp;

import tp.person.Doctor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class AppointmentStorage {
    private static final String root = System.getProperty("user.dir");
    private static final Path filePath = Paths.get(root, "data", "IHospitalAppointments.txt");
    private static final Path dirPath = Paths.get(root, "data");

    /**
     * The constructor of storage
     * @throws IHospitalException
     */
    public AppointmentStorage() throws IHospitalException {
        try {
            File fileDirectory = new File(dirPath.toString());
            if (!fileDirectory.exists()) {
                fileDirectory.mkdir();
            }

            File dataFile = new File(filePath.toString());
            dataFile.createNewFile();
        } catch (IOException e) {
            throw new IHospitalException("FILE ERROR");
        }++
    }

    /**
     *
     * @param appointments doctor list
     * @throws IHospitalException
     */
    public void saveAppointmentList (AppointmentList appointments) throws IHospitalException {
        try {
            FileWriter fw = new FileWriter(filePath.toString());
            int amount = appointments.getSize();
            fw.write(String.format("%d\n", amount));
            for(int i = 1; i <= amount; i++) {
                Appointment currAppointment = (Appointment)appointments.getAppointment(i);
                fw.write(String.format("%d. Appointment:\n", i));
                fw.write(currAppointment.getDoctor() + "\n");
                fw.write(currAppointment.getPatient() + "\n");
                fw.write(currAppointment.getTime() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new IHospitalException("Cannot save task list to file: " + filePath.toString());
        }
    }

    /**
     * A method that will load duke.txt and store the taskList into the current duke's taskList
     * @return The doctorList of the IHospitalDoctors.txt
     * @throws IHospitalException
     */
    public AppointmentList loadAppointmentList() throws IHospitalException {
        try {
            File dataFile = new File(filePath.toString());
            Scanner scanner = new Scanner(dataFile);
            AppointmentList result = new AppointmentList();
            if(!scanner.hasNext()) {
                return result;
            }
            int n = scanner.nextInt();
            scanner.nextLine();//read enter
            String data;

            for(int i = 0; i < n; i++) {
                data = scanner.nextLine();
                String dummy = scanner.nextLine();
                String id = dummy.substring(1, dummy.indexOf("]"));
                String name = scanner.nextLine();
                String phoneNumber = scanner.nextLine();
                String email = scanner.nextLine();
                Doctor doctor = new Doctor(id, name, phoneNumber, email);
                result.addAppointment(doctor);
            }
            return result;
        } catch (FileNotFoundException e) {
            throw new IHospitalException("Cannot load task list from file: " + filePath.toString());
        }
    }
}
