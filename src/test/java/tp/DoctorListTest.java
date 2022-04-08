package tp;

import org.junit.jupiter.api.Test;
import tp.DoctorList;
import tp.IHospitalException;
import tp.Ui;
import tp.person.Doctor;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DoctorListTest {
    private final Doctor firstDoctor = new Doctor("1234", "Rose",
            "12341234", "3600@gmail.com", 10);
    private final Doctor secondDoctor = new Doctor("2233", "Mary",
            "66667777", "Mary123@gmail.com", 22);
    private Ui ui = new Ui();

    @Test
    void getDoctor() {
        DoctorList doctors = new DoctorList();

        try {
            doctors.addDoctor(firstDoctor);
            doctors.addDoctor(secondDoctor);
            assertEquals(secondDoctor, doctors.getDoctor(2));
        } catch (IHospitalException e) {
            ui.generateResponse(e.getMessage());
        }
    }

    @Test
    void getSize() {
        DoctorList doctors = new DoctorList();

        try {
            doctors.addDoctor(firstDoctor);
            doctors.addDoctor(secondDoctor);
            assertEquals(2, doctors.getSize());
        } catch (IHospitalException e) {
            ui.generateResponse(e.getMessage());
        }
    }

    @Test
    void searchDoctor() {
        DoctorList doctors = new DoctorList();

        try {
            doctors.addDoctor(firstDoctor);
            doctors.addDoctor(secondDoctor);
            assertEquals(secondDoctor, doctors.searchDoctor("2233"));
        } catch (IHospitalException e) {
            ui.generateResponse(e.getMessage());
        }
    }

    @Test
    void testToString() {
        DoctorList doctors = new DoctorList();

        String toPrint = "____________________________________________________________"
                                 + System.lineSeparator()
                                 + "Here are the doctors in this hospital:"
                                 + System.lineSeparator()
                                 + "1. * DOCTOR [1234] || Name: Rose || Contact No.: 12341234 "
                                 + "|| Email: 3600@gmail.com || Department: General || Ward: 10"
                                 + System.lineSeparator()
                                 + "2. * DOCTOR [2233] || Name: Mary || Contact No.: 66667777 "
                                 + "|| Email: Mary123@gmail.com || Department: Oral || Ward: 22"
                                 + System.lineSeparator()
                                 + "You have 2 doctors recorded in the system."
                                 + System.lineSeparator()
                                 + "____________________________________________________________"
                                 + System.lineSeparator();

        try {
            doctors.addDoctor(firstDoctor);
            doctors.addDoctor(secondDoctor);
            assertEquals(secondDoctor, doctors.searchDoctor("2233"));
        } catch (IHospitalException e) {
            ui.generateResponse(e.getMessage());
        }
        assertEquals(toPrint, doctors.toString());
    }
}