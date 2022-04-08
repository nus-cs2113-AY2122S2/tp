package arcs.data.staff;


import arcs.data.staff.Staff;
import arcs.data.staff.StaffManager;
import arcs.data.exception.DuplicateDataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class StaffManagerTest {
    @Test
    public void addStaff_duplicateIDs_exceptionThrown() {
        StaffManager staffManager = new StaffManager();
        Staff newStaff = new Staff("A12", "3123", "Mike", "Ground Staff", "11111111", "gmail@gmail.com");
        Staff duplicateStaffID = new Staff("A12", "1111", "Mark", "Flight Crew", "33333333", "Mark@gmail.com");
        try {
            staffManager.addStaff(newStaff);
        } catch (DuplicateDataException e) {
            System.out.println(e.getMessage());
        }

        DuplicateDataException thrown = Assertions
                .assertThrows(DuplicateDataException.class, () -> {
                    staffManager.addStaff(duplicateStaffID);
                }, "DuplicateIdException error was expected");
        Assertions.assertEquals("Sorry! This Staff is already in the system.", thrown.getMessage());
    }
}
