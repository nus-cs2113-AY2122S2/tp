package tp;

import org.junit.jupiter.api.Test;
import tp.person.Nurse;

import static org.junit.jupiter.api.Assertions.assertEquals;


class NurseListTest {
    private final Nurse firstNurse = new Nurse("1234", "Rose",
            "12341234", "3600@gmail.com", "Junior", 10);
    private final Nurse secondNurse = new Nurse("2233", "Mary",
            "66667777", "Mary123@gmail.com", "Senior", 22);
    private Ui ui = new Ui();

    @Test
    void getNurse() {
        NurseList nurses = new NurseList();

        try {
            nurses.addNurse(firstNurse);
            nurses.addNurse(secondNurse);
            assertEquals(secondNurse, nurses.getNurse(2));
        } catch (IHospitalException e) {
            ui.generateResponse(e.getMessage());
        }
    }

    @Test
    void getSize() {
        NurseList nurses = new NurseList();

        try {
            nurses.addNurse(firstNurse);
            nurses.addNurse(secondNurse);
            assertEquals(2, nurses.getSize());
        } catch (IHospitalException e) {
            ui.generateResponse(e.getMessage());
        }
    }

    @Test
    void searchDoctor() {
        NurseList nurses = new NurseList();

        try {
            nurses.addNurse(firstNurse);
            nurses.addNurse(secondNurse);
            assertEquals(secondNurse, nurses.searchNurse("2233"));
        } catch (IHospitalException e) {
            ui.generateResponse(e.getMessage());
        }
    }

    @Test
    void testToString() {
        NurseList nurses = new NurseList();

        String toPrint = "____________________________________________________________"
                + System.lineSeparator()
                + "Here are the nurses in this hospital:"
                + System.lineSeparator()
                + "1. * NURSE [1234] || Name: Rose || Contact No.: 12341234 "
                + "|| Email: 3600@gmail.com || Title: Junior || Ward: 10"
                + System.lineSeparator()
                + "2. * NURSE [2233] || Name: Mary || Contact No.: 66667777 "
                + "|| Email: Mary123@gmail.com || Title: Senior || Ward: 22"
                + System.lineSeparator()
                + "You have 2 nurses recorded in the system."
                + System.lineSeparator()
                + "____________________________________________________________"
                + System.lineSeparator();

        try {
            nurses.addNurse(firstNurse);
            nurses.addNurse(secondNurse);
            assertEquals(secondNurse, nurses.searchNurse("2233"));
        } catch (IHospitalException e) {
            ui.generateResponse(e.getMessage());
        }
        assertEquals(toPrint, nurses.toString());
    }
}