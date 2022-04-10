package tp;

import org.junit.jupiter.api.Test;
import tp.command.Command;
import tp.command.AddAppointmentCommand;
import tp.command.AddDoctorCommand;
import tp.command.AddNurseCommand;
import tp.command.AddPatientCommand;
import tp.command.AddPatientDescriptionCommand;
import tp.command.AddWardCommand;
import tp.command.DeleteAppointmentCommand;
import tp.command.DeleteNurseCommand;
import tp.command.DeletePatientCommand;
import tp.command.DeleteDoctorCommand;
import tp.command.DeleteWardCommand;
import tp.command.EditAppointmentCommand;
import tp.command.EditNurseCommand;
import tp.command.EditWardCommand;
import tp.command.EditDoctorCommand;
import tp.command.EditPatientCommand;
import tp.command.GetAppointmentsOfDoctorCommand;
import tp.command.HelpCommand;
import tp.command.ListAppointmentListCommand;
import tp.command.ListDoctorListCommand;
import tp.command.ListPatientListCommand;
import tp.command.ListNurseListCommand;
import tp.command.ListWardListCommand;
import tp.command.SearchAppointmentCommand;
import tp.command.SearchDoctorCommand;
import tp.command.SearchNurseCommand;
import tp.command.SearchWardCommand;
import tp.command.SearchPatientCommand;
import tp.command.SortAppointmentByTimeCommand;
import tp.command.ViewDoctorPageCommand;
import tp.command.ViewPatientPageCommand;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


//@@author Demonshaha
class ParserTest {
    Parser parser = new Parser();
    private static final String CORRECT_ADD_PATIENT_ONE = "add patient /id 333 /n Draco Malfoy "
            + "/ph 88888888 /e poorPotter@gmail.com /s cough"
            + "/d Allergic to Harry Potter";
    private static final String CORRECT_ADD_PATIENT_TWO = "add patient /id 333    /n Draco Malfoy     "
            + "/ph 88888888 /e poorPotter@gmail.com /s cough "
            + "/d Allergic to Harry Potter";
    private static final String CORRECT_ADD_DOCTOR_ONE = "add doctor /id 222 /n Harry Potter "
            + "/ph 22223333 /e theChosenOne@gmail.com /dep Defensive";
    private static final String CORRECT_ADD_DOCTOR_TWO = "add doctor /id 222    /n Harry Potter  "
            + "/ph 22223333   /e theChosenOne@gmail.com  /dep Defensive";
    private static final String CORRECT_HELP_PAGE_ONE = "help";
    private static final String CORRECT_HELP_PAGE_TWO = "help  ";
    private static final String CORRECT_ADD_NURSE_ONE = "add nurse /id 001 /n Hermione Granger "
            + "/ph 11111111 /e ministerForMagic@gmail.com /t Minister";
    private static final String CORRECT_ADD_NURSE_TWO = "add nurse /id 001 /n Hermione Granger "
            + "/ph 11111111   /e ministerForMagic@gmail.com   /t Minister  ";
    private static final String CORRECT_SORT_APPOINTMENT_ONE = "sort appointment";
    private static final String CORRECT_SORT_APPOINTMENT_TWO = "sort appointment    ";
    private static final String CORRECT_LIST_DOCTOR_ONE = "list doctor";
    private static final String CORRECT_LIST_DOCTOR_TWO = "list doctor ";
    private static final String CORRECT_LIST_PATIENT_ONE = "list patient";
    private static final String CORRECT_LIST_PATIENT_TWO = "list patient ";
    private static final String CORRECT_LIST_WARD_ONE = "list ward";
    private static final String CORRECT_LIST_WARD_TWO = "list ward ";
    private static final String CORRECT_LIST_APPOINTMENT_ONE = "list appointment";
    private static final String CORRECT_LIST_APPOINTMENT_TWO = "list appointment ";
    private static final String CORRECT_SEARCH_DOCTOR_ONE = "search doctor 1";
    private static final String CORRECT_SEARCH_DOCTOR_TWO = "search doctor 1 ";
    private static final String CORRECT_SEARCH_PATIENT_ONE = "search patient 1";
    private static final String CORRECT_SEARCH_PATIENT_TWO = "search patient 1 ";
    private static final String CORRECT_SEARCH_NURSE_ONE = "search nurse 1";
    private static final String CORRECT_SEARCH_NURSE_TWO = "search nurse 1 ";
    private static final String CORRECT_SEARCH_WARD_ONE = "search ward 1";
    private static final String CORRECT_SEARCH_WARD_TWO = "search ward 1 ";
    private static final String CORRECT_EDIT_DOCTOR_ONE = "edit /d 1 /ph 12341234";
    private static final String CORRECT_EDIT_DOCTOR_TWO = "edit /d 1 /e 12341234@gmail.com";
    private static final String CORRECT_EDIT_PATIENT_ONE = "edit /p 1 /n Mike";
    private static final String CORRECT_EDIT_PATIENT_TWO = "edit /p 1 /e 12341234@gmail.com";
    private static final String CORRECT_EDIT_NURSE_ONE = "edit /nu 1 /t Senior";
    private static final String CORRECT_EDIT_NURSE_TWO = "edit /nu 1 /t Senior  ";
    private static final String CORRECT_EDIT_WARD_ONE = "edit /w 1 /id 2";
    private static final String CORRECT_EDIT_WARD_TWO = "edit /w 1 /id 2 ";
    private static final String CORRECT_DELETE_DOCTOR_ONE = "delete doctor 1";
    private static final String CORRECT_DELETE_DOCTOR_TWO = "delete doctor 1 ";
    private static final String CORRECT_DELETE_PATIENT_ONE = "delete patient 1";
    private static final String CORRECT_DELETE_PATIENT_TWO = "delete patient 1 ";
    private static final String CORRECT_DELETE_APPOINTMENT_ONE = "delete appointment 1";
    private static final String CORRECT_DELETE_APPOINTMENT_TWO = "delete appointment 1 ";
    private static final String CORRECT_DELETE_NURSE_ONE = "delete nurse 1";
    private static final String CORRECT_DELETE_NURSE_TWO = "delete nurse 1 ";
    private static final String CORRECT_DELETE_WARD_ONE = "delete ward 1";
    private static final String CORRECT_DELETE_WARD_TWO = "delete ward 1 ";

    @Test
    void parse_addPatientCommand_correctCommandCreated() throws IHospitalException {
        parseAndAssertCommandType(CORRECT_ADD_PATIENT_ONE, AddPatientCommand.class);

        //white space test
        parseAndAssertCommandType(CORRECT_ADD_PATIENT_TWO, AddPatientCommand.class);
    }

    @Test
    void parse_addDoctorCommand_correctCommandCreated() throws IHospitalException {
        parseAndAssertCommandType(CORRECT_ADD_DOCTOR_ONE, AddDoctorCommand.class);

        //white space test
        parseAndAssertCommandType(CORRECT_ADD_DOCTOR_TWO, AddDoctorCommand.class);
    }

    @Test
    void parse_helpCommand_correctCommandCreated() throws IHospitalException {
        parseAndAssertCommandType(CORRECT_HELP_PAGE_ONE, HelpCommand.class);

        //white space test
        parseAndAssertCommandType(CORRECT_HELP_PAGE_TWO, HelpCommand.class);
    }

    @Test
    void parse_addNurseCommand_correctCommandCreated() throws IHospitalException {
        parseAndAssertCommandType(CORRECT_ADD_NURSE_ONE, AddNurseCommand.class);

        //white space test
        parseAndAssertCommandType(CORRECT_ADD_NURSE_TWO, AddNurseCommand.class);
    }

    @Test
    void parse_sortAppointmentCommand_correctCommandCreated() throws IHospitalException {
        parseAndAssertCommandType(CORRECT_SORT_APPOINTMENT_ONE, SortAppointmentByTimeCommand.class);

        //white space test
        parseAndAssertCommandType(CORRECT_SORT_APPOINTMENT_TWO, SortAppointmentByTimeCommand.class);
    }

    @Test
    void parse_listDoctorCommand_correctCommandCreated() throws IHospitalException {
        parseAndAssertCommandType(CORRECT_LIST_DOCTOR_ONE, ListDoctorListCommand.class);

        //white space test
        parseAndAssertCommandType(CORRECT_LIST_DOCTOR_TWO, ListDoctorListCommand.class);
    }

    @Test
    void parse_listPatientCommand_correctCommandCreated() throws IHospitalException {
        parseAndAssertCommandType(CORRECT_LIST_PATIENT_ONE, ListPatientListCommand.class);

        //white space test
        parseAndAssertCommandType(CORRECT_LIST_PATIENT_TWO, ListPatientListCommand.class);
    }

    @Test
    void parse_listWardCommand_correctCommandCreated() throws IHospitalException {
        parseAndAssertCommandType(CORRECT_LIST_WARD_ONE, ListWardListCommand.class);

        //white space test
        parseAndAssertCommandType(CORRECT_LIST_WARD_TWO, ListWardListCommand.class);
    }

    @Test
    void parse_listAppointmentCommand_correctCommandCreated() throws IHospitalException {
        parseAndAssertCommandType(CORRECT_LIST_APPOINTMENT_ONE, ListAppointmentListCommand.class);

        //white space test
        parseAndAssertCommandType(CORRECT_LIST_APPOINTMENT_TWO, ListAppointmentListCommand.class);
    }

    @Test
    void parse_search_correctCommandCreated() throws IHospitalException {
        parseAndAssertCommandType(CORRECT_SEARCH_DOCTOR_ONE, SearchDoctorCommand.class);
        parseAndAssertCommandType(CORRECT_SEARCH_DOCTOR_TWO, SearchDoctorCommand.class);
        parseAndAssertCommandType(CORRECT_SEARCH_PATIENT_ONE, SearchPatientCommand.class);
        parseAndAssertCommandType(CORRECT_SEARCH_PATIENT_TWO, SearchPatientCommand.class);
        parseAndAssertCommandType(CORRECT_SEARCH_NURSE_ONE, SearchNurseCommand.class);
        parseAndAssertCommandType(CORRECT_SEARCH_NURSE_TWO, SearchNurseCommand.class);
        parseAndAssertCommandType(CORRECT_SEARCH_WARD_ONE, SearchWardCommand.class);
        parseAndAssertCommandType(CORRECT_SEARCH_WARD_TWO, SearchWardCommand.class);
    }

    @Test
    void parse_edit_correctCommandCreated() throws IHospitalException {
        parseAndAssertCommandType(CORRECT_EDIT_DOCTOR_ONE, EditDoctorCommand.class);
        parseAndAssertCommandType(CORRECT_EDIT_DOCTOR_TWO, EditDoctorCommand.class);
        parseAndAssertCommandType(CORRECT_EDIT_PATIENT_ONE, EditPatientCommand.class);
        parseAndAssertCommandType(CORRECT_EDIT_PATIENT_TWO, EditPatientCommand.class);
        parseAndAssertCommandType(CORRECT_EDIT_NURSE_ONE, EditNurseCommand.class);
        parseAndAssertCommandType(CORRECT_EDIT_NURSE_TWO, EditNurseCommand.class);
        parseAndAssertCommandType(CORRECT_EDIT_WARD_ONE, EditWardCommand.class);
        parseAndAssertCommandType(CORRECT_EDIT_WARD_TWO, EditWardCommand.class);
    }

    @Test
    void parse_delete_correctCommandCreated() throws IHospitalException {
        parseAndAssertCommandType(CORRECT_DELETE_DOCTOR_ONE, DeleteDoctorCommand.class);
        parseAndAssertCommandType(CORRECT_DELETE_DOCTOR_TWO, DeleteDoctorCommand.class);
        parseAndAssertCommandType(CORRECT_DELETE_PATIENT_ONE, DeletePatientCommand.class);
        parseAndAssertCommandType(CORRECT_DELETE_PATIENT_TWO, DeletePatientCommand.class);
        parseAndAssertCommandType(CORRECT_DELETE_NURSE_ONE, DeleteNurseCommand.class);
        parseAndAssertCommandType(CORRECT_DELETE_NURSE_TWO, DeleteNurseCommand.class);
        parseAndAssertCommandType(CORRECT_DELETE_WARD_ONE, DeleteWardCommand.class);
        parseAndAssertCommandType(CORRECT_DELETE_WARD_TWO, DeleteWardCommand.class);
        parseAndAssertCommandType(CORRECT_DELETE_APPOINTMENT_ONE, DeleteAppointmentCommand.class);
        parseAndAssertCommandType(CORRECT_DELETE_APPOINTMENT_TWO, DeleteAppointmentCommand.class);
    }

    private <T extends Command> void parseAndAssertCommandType(String input, Class<T> expectedCommandClass)
        throws IHospitalException {
        final Command result = parser.parse(input);
        assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
    }

    private void assertParseFailure(String input, String expectedMessage) {
        try {
            parser.parse(input);
            throw new AssertionError("The expected exception was not thrown");
        } catch (IHospitalException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }
}