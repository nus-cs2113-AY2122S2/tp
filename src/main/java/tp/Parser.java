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

        } else if (fullCommand.contains("get") && fullCommand.contains("appointment")) {
            //get appointment /d 123456
            String dummy = fullCommand.trim();
            String id =  dummy.substring(dummy.indexOf("/d") + 3);
            return new GetAppointmentsOfDoctorCommand(id);
        } else if (fullCommand.contains("sort appointment")) {
            return new SortAppointmentByTimeCommand();
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
                int symptomIndex=dummy.indexOf("/s");
                String email = dummy.substring(emailIndex,symptomIndex);
                symptomIndex+=3;
                int descIndex=dummy.indexOf("/d");
                String symptom=dummy.substring(symptomIndex,descIndex);
                descIndex+=3;
                String description=dummy.substring(descIndex);
                return new AddPatientCommand(id, name, phoneNumber, email,symptom,description);
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
        } else if (fullCommand.contains("help")) {
            return new HelpCommand();
        } else if(fullCommand.contains("add patient description")) {
            String dummy = fullCommand.trim();
            int patientIndex = dummy.indexOf("/p");
            int descriptionIndex=dummy.indexOf("/d");
            String patientID=dummy.substring(patientIndex,descriptionIndex);
            descriptionIndex += 3;
            String description = dummy.substring(descriptionIndex);
            System.out.println("tesst");
            return new AddPatientDescriptionCommand(description,patientID);
        } else if (fullCommand.contains("search doctor")){
            String dummy = fullCommand.trim();
            dummy = dummy.substring(dummy.length() - 4);
            return new SearchDoctorCommand(dummy);
        } else if (fullCommand.contains("search patient")) {
            String dummy = fullCommand.trim();
            dummy = dummy.substring(dummy.length() - 4);
            return new SearchPatientCommand(dummy);
        } else if (fullCommand.contains("search appointment")) {
            String dummy = fullCommand.trim();
            dummy = dummy.substring(17);
            return new SearchAppointmentCommand(dummy);
        } else {
            throw new IHospitalException("Invalid command given");
        }

        return null;
    }


}


