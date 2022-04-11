package tp;

public class Ui {
    public static String boundary = "____________________________________________________________"
                                            + System.lineSeparator();

    // Prints welcome message.
    public void sayHello() {
        System.out.print(boundary);
        System.out.println("Hello! This is IHospital.");
        System.out.print("What can I do for you?" + System.lineSeparator() + boundary);
    }

    // Prints goodbye message.
    public void sayGoodbye() {
        System.out.print(boundary + "Bye. Hope to see you again soon!" + System.lineSeparator() + boundary);
    }

    public void generateResponse(String message) {
        System.out.println(message);
    }

    public void printDoctorPage(DoctorList doctors, AppointmentList appointments)
            throws IHospitalException {
        System.out.print(boundary);
        System.out.println("Welcome to Doctor Page.");
        System.out.println("Here are the doctors and their appointments:");
        for (int i = 1; i <= doctors.getSize(); i++) {
            System.out.println(doctors.getDoctor(i));
            System.out.println(appointments.getAppointmentListOfDoctorById(doctors.getDoctor(i).getId()));
        }
    }

    public void printNursePage(NurseList nurses) {
        System.out.print(boundary);
        System.out.println("Welcome to Nurse Page.");
        System.out.print(nurses);
    }

    public void printPatientPage(PatientList patients, AppointmentList appointments)
            throws IHospitalException {
        System.out.print(boundary);
        System.out.println("Welcome to Patient Page.");
        System.out.println("Here are the patients and their appointments:");
        for (int i = 1; i <= patients.getSize(); i++) {
            System.out.println(patients.getPatient(i));
            System.out.println(appointments.getAppointmentListOfPatientById(patients.getPatient(i).getId()));
        }
    }

    public void printWardPage(WardList wards) {
        System.out.print(boundary);
        System.out.println("Welcome to Ward Page.");
        System.out.print(wards);
    }

    public void printAddHelp() {
        String addDoctor = "add doctor /id ID /n NAME /ph PHONE /e EMAIL /dep DEPARTMENT" + System.lineSeparator();
        String addPatient = "add patient /id ID /n NAME /ph PHONE /e EMAIL /s SYMPTOM /d DESCRIPTIONS"
                                    + System.lineSeparator();
        String addNurse = "add nurse /id ID /n NAME /ph PHONE /e EMAIL /t TITLE" + System.lineSeparator();
        String addAppointment = "add appointment /t 2022-03-19T15:16:00 /d DOCTOR_NO /p PATIENT_NO"
                                        + System.lineSeparator();
        String addWard = "add ward /d DOCTOR_NOs /p PATIENT_NOs /n NURSE_NOs /id WARD_ID"
                                 + System.lineSeparator();
        System.out.println("1. To add something:" + System.lineSeparator()
                                   + addDoctor + addPatient + addNurse + addWard + addAppointment);
    }

    public void printListHelp() {
        String listDoctor = "list doctor" + System.lineSeparator();
        String listPatient = "list patient" + System.lineSeparator();
        String listNurse = "list nurse" + System.lineSeparator();
        String listWard = "list ward" + System.lineSeparator();
        String sortAppointment = "sort appointment"
                                         + System.lineSeparator();
        String listAppointment = "list appointment" + System.lineSeparator();
        System.out.println("2. To list something:" + System.lineSeparator()
                                   + listDoctor + listPatient + listNurse + listWard
                                   + sortAppointment + listAppointment);
    }

    public void printDeleteHelp() {
        String deleteDoctor = "delete doctor DOCTOR_NO" + System.lineSeparator();
        String deletePatient = "delete patient PATIENT_NO" + System.lineSeparator();
        String deleteAppointment = "delete appointment APPOINTMENT_NO." + System.lineSeparator();
        String deleteNurse = "delete nurse NURSE_NO." + System.lineSeparator();
        String deleteWard = "delete ward WARD_NO" + System.lineSeparator();
        System.out.println("3. To delete something:" + System.lineSeparator()
                                   + deleteDoctor + deletePatient + deleteNurse
                                   + deleteWard + deleteAppointment);
    }

    public void printSearchHelp() {
        String searchDoctor = "search doctor DOCTOR_NO" + System.lineSeparator();
        String searchPatient = "search patient PATIENT_NO" + System.lineSeparator();
        String searchAppointment = "search appointment DATETIME" + System.lineSeparator();
        String searchNurse = "search nurse NURSE_NO" + System.lineSeparator();
        String searchWard = "search ward WARD_NO" + System.lineSeparator();
        System.out.println("4. To search for something:" + System.lineSeparator()
                                   + searchDoctor + searchPatient + searchNurse
                                   + searchWard + searchAppointment);
    }

    public void printEditHelp() {
        String editDoctor = "edit /d DOCTOR_NO /field INFO" + System.lineSeparator();
        String editPatient = "edit /p PATIENT_NO /field INFO" + System.lineSeparator();
        String editAppointment = "edit /a APPOINTMENT_NO /field INFO" + System.lineSeparator();
        String editNurse = "edit /nu NURSE_NO /field INFO" + System.lineSeparator();
        String editWard = "edit /w WARD_NO /field INFO" + System.lineSeparator();
        System.out.println("5. To edit something:" + System.lineSeparator()
                                   + editDoctor + editPatient + editNurse
                                   + editWard + editAppointment);
    }

    public void printSortHelp() {
        String sortAppointment = "sort appointment" + System.lineSeparator();
        System.out.println("5. To sort something:" + System.lineSeparator() + sortAppointment);
    }

    public void printGetHelp() {
        String getAppointment = "get appointment DOCTOR_ID" + System.lineSeparator();
        System.out.println("6. To get appointments of a doctor: "
                                   + System.lineSeparator() + getAppointment);
    }

    public void printHelpPage() {
        System.out.print(boundary);
        System.out.println("Do not remember the command format? Here are some commands you can try:");

        printAddHelp();
        printListHelp();
        printDeleteHelp();
        printSearchHelp();
        printSortHelp();
        printGetHelp();
        printEditHelp();

        System.out.println("Hope they are helpful to you~");
        System.out.print(boundary);
    }
}
