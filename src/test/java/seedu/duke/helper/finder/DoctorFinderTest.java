package seedu.duke.helper.finder;

import org.junit.jupiter.api.Test;
import seedu.duke.assets.Doctor;
import seedu.duke.assets.DoctorList;
import seedu.duke.exception.DuplicateEntryException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoctorFinderTest {
    @Test
    void findDoctorByNric_addedNewDoctor_returnsSameDoctor() throws DuplicateEntryException {
        DoctorList doctorList = new DoctorList();
        String[] newDoctor = {"S1234567Z", "Bruce Wayne", "40", "M", "Wayne Mansion", "1982-10-11", "Psychology"};
        doctorList.add(newDoctor);
        Doctor expectedDoctor = DoctorFinder.findDoctorByNric(doctorList, "S1234567Z");
        assert expectedDoctor != null;
        assertEquals(expectedDoctor.getNric(), doctorList.getList().get(0).getNric());
    }

    @Test
    void findDoctorByName_addedNewDoctor_returnsSameDoctor() throws DuplicateEntryException {
        DoctorList doctorList = new DoctorList();
        String[] newDoctor = {"S1234567Z", "Bruce Wayne", "40", "M", "Wayne Mansion", "1982-10-11", "Psychology"};
        doctorList.add(newDoctor);
        Doctor expectedDoctor = DoctorFinder.findDoctorByName(doctorList, "Bruce Wayne");
        assert expectedDoctor != null;
        assertEquals(expectedDoctor.getFullName(), doctorList.getList().get(0).getFullName());
    }

    @Test
    void findDoctorByAge_addedNewDoctor_returnsSameDoctor() throws DuplicateEntryException {
        DoctorList doctorList = new DoctorList();
        String[] newDoctor = {"S1234567Z", "Bruce Wayne", "40", "M", "Wayne Mansion", "1982-10-11", "Psychology"};
        doctorList.add(newDoctor);
        Doctor expectedDoctor = DoctorFinder.findDoctorByAge(doctorList, 40);
        assert expectedDoctor != null;
        assertEquals(expectedDoctor.getAge(), doctorList.getList().get(0).getAge());
    }

    @Test
    void findDoctorByGender_addedNewDoctor_returnsSameDoctor() throws DuplicateEntryException {
        DoctorList doctorList = new DoctorList();
        String[] newDoctor = {"S1234567Z", "Bruce Wayne", "40", "M", "Wayne Mansion", "1982-10-11", "Psychology"};
        doctorList.add(newDoctor);
        Doctor expectedDoctor = DoctorFinder.findDoctorByGender(doctorList, 'M');
        assert expectedDoctor != null;
        assertEquals(expectedDoctor.getGender(), doctorList.getList().get(0).getGender());
    }

    @Test
    void findDoctorByAddress_addedNewDoctor_returnsSameDoctor() throws DuplicateEntryException {
        DoctorList doctorList = new DoctorList();
        String[] newDoctor = {"S1234567Z", "Bruce Wayne", "40", "M", "Wayne Mansion", "1982-10-11", "Psychology"};
        doctorList.add(newDoctor);
        Doctor expectedDoctor = DoctorFinder.findDoctorByAddress(doctorList, "Wayne Mansion");
        assert expectedDoctor != null;
        assertEquals(expectedDoctor.getAddress(), doctorList.getList().get(0).getAddress());
    }

    @Test
    void findDoctorByDob_addedNewDoctor_returnsSameDoctor() throws DuplicateEntryException {
        DoctorList doctorList = new DoctorList();
        String[] newDoctor = {"S1234567Z", "Bruce Wayne", "40", "M", "Wayne Mansion", "1982-10-11", "Psychology"};
        doctorList.add(newDoctor);
        Doctor expectedDoctor = DoctorFinder.findDoctorByDob(doctorList, "1982-10-11");
        assert expectedDoctor != null;
        assertEquals(expectedDoctor.getDob(), doctorList.getList().get(0).getDob());
    }

    @Test
    void findDoctorBySpecialization_addedNewDoctor_returnsSameDoctor() throws DuplicateEntryException {
        DoctorList doctorList = new DoctorList();
        String[] newDoctor = {"S1234567Z", "Bruce Wayne", "40", "M", "Wayne Mansion", "1982-10-11", "Psychology"};
        doctorList.add(newDoctor);
        Doctor expectedDoctor = DoctorFinder.findDoctorBySpecialization(doctorList, "Psychology");
        assert expectedDoctor != null;
        assertEquals(expectedDoctor.getSpecialization(), doctorList.getList().get(0).getSpecialization());
    }
}

