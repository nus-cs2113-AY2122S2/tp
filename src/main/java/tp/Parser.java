package tp;

import java.util.Scanner;

public class Parser {

    public Parser() {

    }

    public static String getCommand() {
        String command;
        Scanner in = new Scanner(System.in);
        command = in.nextLine();
        return command;
    }

    public Command parse(String fullCommand) throws IHospitalException {
        if (fullCommand.contains("add doctor")) {
            String id;
            String dummy = fullCommand.trim();
            try {
                int idIndex = dummy.indexOf("/id") + 4;
                int nameIndex = dummy.indexOf("/n");
                id = dummy.substring(idIndex, nameIndex);
                nameIndex += 3;
                int phoneNumberIndex = dummy.indexOf("/ph");
                String name = dummy.substring(nameIndex, phoneNumberIndex);
                phoneNumberIndex += 4;
                int emailIndex = dummy.indexOf("/e");
                String phoneNumber = dummy.substring(phoneNumberIndex, emailIndex);
                emailIndex += 3;
                String email = dummy.substring(emailIndex);
                return new AddDoctorCommand(id, name, phoneNumber, email, false);
            } catch (Exception e) {
                System.out.println("The input format of the doctor information is wrong.");
            }

        } else if (fullCommand.contains("add patient")) {
            String id;
            String dummy = fullCommand.trim();
            try {
                int idIndex = dummy.indexOf("/id") + 4;
                int nameIndex = dummy.indexOf("/n");
                id = dummy.substring(idIndex, nameIndex);
                nameIndex += 3;
                int phoneNumberIndex = dummy.indexOf("/ph");
                String name = dummy.substring(nameIndex, phoneNumberIndex);
                phoneNumberIndex += 4;
                int emailIndex = dummy.indexOf("/e");
                String phoneNumber = dummy.substring(phoneNumberIndex, emailIndex);
                emailIndex += 3;
                String email = dummy.substring(emailIndex);
                return new AddPatientCommand(id, name, phoneNumber, email);
            } catch (Exception e) {
                System.out.println("The input format of the patient information is wrong.");
            }

        } else if (fullCommand.contains("add appointment")) {
            String time;
            String dummy = fullCommand.trim();
            try {
                int timeIndex = dummy.indexOf("/t");
                int doctorIndex = dummy.indexOf("/d");
                time = dummy.substring(timeIndex, doctorIndex);
                int patientIndex = dummy.indexOf("/p");
                String s = dummy.substring(doctorIndex, patientIndex);

                doctorIndex = Integer.parseInt(s);
                patientIndex += 3;
                s = dummy.substring(patientIndex);
                patientIndex = Integer.parseInt(s);
                return new AddAppointmentCommand(doctorIndex, patientIndex, time);
            } catch (Exception e) {
                System.out.println("The input format of the appointment information is wrong.");
            }
        } else if (fullCommand.contains("delete doctor")) {
            String dummy = fullCommand.trim();
            int index = Integer.parseInt(dummy.substring(dummy.length() - 1));
            return new DeleteDoctorCommand(index);
        } else if (fullCommand.contains("delete patient")) {
            String dummy = fullCommand.trim();
            int index = Integer.parseInt(dummy.substring(dummy.length() - 1));
            return new DeletePatientCommand(index);
        } else if (fullCommand.contains("delete appointment")) {
            String dummy = fullCommand.trim();
            int index = Integer.parseInt(dummy.substring(dummy.length() - 1));
            return new DeleteAppointmentCommand(index);
        } else if (fullCommand.contains("list doctor")) {
            return new ListDoctorListCommand();
        } else if (fullCommand.contains("list appointment")) {
            return new ListAppointmentListCommand();
        } else if (fullCommand.contains("list patient")) {
            return new ListPatientListCommand();
        } else
            throw new IHospitalException("Invalid command given");

        return null;
    }


}


