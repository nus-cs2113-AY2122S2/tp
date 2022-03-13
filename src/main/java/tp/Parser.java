package tp;

import java.util.Scanner;

public class Parser {
    public Parser(){

    }

    /**
     * A method to get fullCommand from the user
     * @return The fullCommand get from the user
     */
    public static String getCommand() {
        String command;
        Scanner in = new Scanner(System.in);
        command = in.nextLine();
        return command;
    }

    /**
     * A method to parse fullCommand
     * @param fullCommand
     * @return The command typed by the user
     * @throws IHospitalException
     */
    public Command parse(String fullCommand) throws IHospitalException {
        if(fullCommand.contains("add doctor")) {
            String dummy = fullCommand.trim();
            int idIndex = dummy.indexOf("/id") + 4;
            int nameIndex = dummy.indexOf("/n");
            String id = dummy.substring(idIndex, nameIndex);
            nameIndex += 3;
            int phoneNumberIndex = dummy.indexOf("/ph");
            String name = dummy.substring(nameIndex, phoneNumberIndex);
            phoneNumberIndex += 4;
            int emailIndex = dummy.indexOf("/e");
            String phoneNumber = dummy.substring(phoneNumberIndex, emailIndex);
            emailIndex += 3;
            String email = dummy.substring(emailIndex);
            return new AddDoctorCommand(id, name, phoneNumber, email, false);
        }
        else if(fullCommand.contains("add patient")) {
            String dummy = fullCommand.trim();
            int idIndex = dummy.indexOf("/id") + 4;
            int nameIndex = dummy.indexOf("/n");
            String id = dummy.substring(idIndex, nameIndex);
            nameIndex += 3;
            int phoneNumberIndex = dummy.indexOf("/ph");
            String name = dummy.substring(nameIndex, phoneNumberIndex);
            phoneNumberIndex += 4;
            int emailIndex = dummy.indexOf("/e");
            String phoneNumber = dummy.substring(phoneNumberIndex, emailIndex);
            emailIndex += 3;
            String email = dummy.substring(emailIndex);
            return new AddPatientCommand(id, name, phoneNumber, email);
        }
        else if(fullCommand.contains("add appointment")) {
            String dummy = fullCommand.trim();
            int timeIndex = dummy.indexOf("/t");
            int doctorIndex = dummy.indexOf("/d");
            String time = dummy.substring(timeIndex, doctorIndex);
            int patientIndex = dummy.indexOf("/p");
            String s = dummy.substring(doctorIndex, patientIndex);
            doctorIndex = Integer.parseInt(s);
            patientIndex += 3;
            s = dummy.substring(patientIndex);
            patientIndex = Integer.parseInt(s);
            return new AddAppointmentCommand(doctorIndex, patientIndex, time);
        }
        else if(fullCommand.contains("delete doctor")) {
            String dummy = fullCommand.trim();
            int index = Integer.parseInt(dummy.substring(dummy.length() - 1));
            return new DeleteDoctorCommand(index);
        }
        else if(fullCommand.contains("delete patient")) {
            String dummy = fullCommand.trim();
            int index = Integer.parseInt(dummy.substring(dummy.length() - 1));
            return new DeletePatientCommand(index);
        }
        else if(fullCommand.contains("delete appointment")) {
            String dummy = fullCommand.trim();
            int index = Integer.parseInt(dummy.substring(dummy.length() - 1));
            return new DeleteAppointmentCommand(index);
        }
        else if(fullCommand.contains("list doctor")) {
            return new ListDoctorListCommand();
        }
        else if(fullCommand.contains("list appointment")) {
            return new ListAppointmentListCommand();
        }
        else if(fullCommand.contains("list patient")) {
            return new ListPatientListCommand();
        }
        return null;
    }


}
