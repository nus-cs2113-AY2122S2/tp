package tp.command;

import tp.*;
import tp.person.Doctor;

import java.util.Scanner;

public class DeleteDoctorCommand extends Command {
    private final int index;

    public DeleteDoctorCommand(int index) {
        this.index = index;
    }
    Scanner scanner=new Scanner(System.in);
    @Override
    public String execute(DoctorList doctorList, PatientList patientList,
                          AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                          PatientStorage patientStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        Doctor curr = doctorList.deleteDoctor(index);
        while (curr == null){
            System.out.println("The doctor you request to delete does not exist.");
            System.out.println("Please reenter a valid doctor index or enter 0 to change command.");
            int com=scanner.nextInt();
            if (com==0)
                return("Exiting delect doctor command.");
            curr=doctorList.deleteDoctor(com);
        }
            return String.format(boundary + "Noted. I've removed this doctor:" + "\n" + curr
                    + "\n" + "Now you have " + doctorList.getSize()
                    + " doctors in the system." + System.lineSeparator() + boundary);
    }
}
