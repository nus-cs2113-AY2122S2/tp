package arcs.data.staff;

import arcs.data.staff.Staff;
import arcs.data.exception.ArcsException;
import arcs.data.exception.DuplicateDataException;

import java.util.ArrayList;

public class StaffManager {
    private ArrayList<Staff> staffs;

    public StaffManager() {
        staffs = new ArrayList<>();
    }

    public StaffManager(ArrayList<Staff> staffs) {
        this.staffs = staffs;
    }

    /**
     * Checks whether the Id already exists in the list.
     * @param id the input Idd
     * @return true if the Id already exists.
     */
    public boolean hasDuplicateIc(String id) {
        for (Staff staff: staffs) {
            if (id.equals(staff.getId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add a new staff into the list.
     * @param c the new staff to be added
     * @throws DuplicateDataException if the Id of the staff already exists.
     */
    public void addStaff(Staff c) throws DuplicateDataException  {
        boolean hasDuplicateStaff = hasDuplicateIc(c.getId());
        if (hasDuplicateStaff) {
            throw new DuplicateDataException("This Staff is already in the system.");
        }

        staffs.add(c);
    }

    public ArrayList<Staff> getAllStaffs() {
        return staffs;
    }

    /**
     * Deletes a staff at the specified index.
     * The index refers to the position of the staff in the customer list.
     * @param index index of the customer tp be deleted
     * @return the deleted staff
     */
    public Staff deleteStaff(int index) throws ArcsException {
        assert staffs != null : "Staff list is null";
        if (index <= 0 || index > staffs.size()) {
            throw new ArcsException("Index out of bound.");
        }
        Staff deleted = staffs.get(index - 1);
        staffs.remove(index - 1);
        return deleted;
    }


}



