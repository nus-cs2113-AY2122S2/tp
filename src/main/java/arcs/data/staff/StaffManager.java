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

    public boolean hasDuplicateIc(String ic) {
        for (Staff Staff: staffs) {
            if (ic.equals(Staff.getId())) {
                return true;
            }
        }
        return false;
    }

    public void addStaff(Staff c) throws DuplicateDataException  {
        boolean hasDuplicateStaff = hasDuplicateIc(c.getId());
        if (hasDuplicateStaff) {
            throw new DuplicateDataException("This Staff is already in the system.");
        }

        staffs.add(c);
    }

    public ArrayList<Staff> getAllstaffs() {
        return staffs;
    }

    public Staff deleteStaff(int index) throws ArcsException {
        assert staffs != null : "Staff list is null";
        if (index <= 0 || index > staffs.size()) {
            throw new ArcsException("Index out of bound.");
        }
        Staff deleted = staffs.get(index - 1);
        staffs.remove(index - 1);
        return deleted;
    }

    public Staff findStaff(String ic) throws ArcsException {
        assert ic != null : "IC is null";
        if (!Staff.isValidIc(ic)) {
            throw new ArcsException("IC number is invalid.");
        }
        for (Staff Staff: staffs) {
            if (ic.equals(Staff.getId())) {
                return Staff;
            }
        }
        return null;
    }


}
