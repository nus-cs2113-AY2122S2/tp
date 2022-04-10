package tp;

import tp.person.Doctor;
import tp.person.Patient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Scanner;

public class AppointmentStorage {
    private static final String root = System.getProperty("user.dir");
    private static final Path filePath = Paths.get(root, "data", "IHospitalAppointments.txt");
    private static final Path dirPath = Paths.get(root, "data");

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
        }
    }

    /**
     * The function to save appointment list.
     * @param appointments doctor list
     * @throws IHospitalException IHospitalException
     */
    public void saveAppointmentList(AppointmentList appointments) throws IHospitalException {
        try {
            FileWriter fw = new FileWriter(filePath.toString());
            int amount = appointments.getSize();
            fw.write(String.format("%d\n", amount));
            for (int i = 1; i <= amount; i++) {
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
     * @throws IHospitalException IHospitalException
     */
    public AppointmentList loadAppointmentList() throws IHospitalException {
        try {
            File dataFile = new File(filePath.toString());
            Scanner scanner = new Scanner(dataFile);
            AppointmentList result = new AppointmentList();
            Doctor doctor;
            if (!scanner.hasNext()) {
                return result;
            }
            int n = scanner.nextInt();
            scanner.nextLine();//read enter
            String data;

            for (int i = 0; i < n; i++) {
                data = scanner.nextLine();
                String dummy = scanner.nextLine();
                String id = dummy.substring(1, dummy.indexOf("]"));
                String name = dummy.substring(dummy.indexOf("Name:") + 6, dummy.indexOf(" || Contact"));
                String phoneNumber = dummy.substring(dummy.indexOf("No.:") + 5, dummy.indexOf(" || Email:"));
                String email = dummy.substring(dummy.indexOf("Email:") + 7, dummy.indexOf(" || Department:"));
                String department = dummy.substring(dummy.indexOf("Department:") + 12, dummy.indexOf(" || Ward"));
                int wardNumber = Integer.parseInt(dummy.substring(dummy.indexOf("Ward:") + 6).trim());
                doctor = new Doctor(id, name, phoneNumber, email, department, wardNumber);
                dummy = scanner.nextLine();
                id = dummy.substring(1, dummy.indexOf("]"));
                name = dummy.substring(dummy.indexOf("Name:") + 6, dummy.indexOf(" || Contact"));
                phoneNumber = dummy.substring(dummy.indexOf("No.:") + 5, dummy.indexOf(" || Email:"));
                email = dummy.substring(dummy.indexOf("Email:") + 7, dummy.indexOf(" || Symptom"));
                String symptom = dummy.substring(dummy.indexOf("Symptom:") + 9, dummy.indexOf(" || Description"));
                String description = dummy.substring(dummy.indexOf("Description:") + 13);
                Patient patient = new Patient(id, name, phoneNumber, email, symptom, description);
                String time = scanner.nextLine();
                result.addAppointment(doctor, patient, LocalDateTime.parse(time));
            }
            return result;
        } catch (FileNotFoundException e) {
            throw new IHospitalException("Cannot load task list from file: " + filePath.toString());
        }
    }
}
