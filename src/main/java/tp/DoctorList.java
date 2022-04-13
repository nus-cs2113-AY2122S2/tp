package tp;

import tp.person.Doctor;

import java.util.ArrayList;

public class DoctorList {
    public static String boundary = "____________________________________________________________"
                                            + System.lineSeparator();
    protected static ArrayList<Doctor> doctors = new ArrayList<>();
    protected int size = 0;

    public DoctorList() {
        size = 0;
    }

    public static Doctor getDoctor(int index) {
        return doctors.get(index - 1);
    }

    /**
     * Add a doctor to the list.
     *
     * @param doctor Doctor to be added.
     * @throws IHospitalException If ID of the doctor is the same as other doctors.
     */
    public void addDoctor(Doctor doctor) throws IHospitalException {
        boolean isDuplicate = false;
        for (Doctor value : doctors) {
            if (value.getId().trim().equals(doctor.getId())) {
                isDuplicate = true;
                break;
            }
        }

        if (isDuplicate) {
            throw new IHospitalException("Doctor with this ID already exists.\n");
        } else {
            doctors.add(doctor);
            size++;
        }
    }

    public int getSize() {
        return size;
    }

    public Doctor deleteDoctor(int index) {
        if (index <= 0 || index > doctors.size()) {
            return null;
        }
        Doctor curr = doctors.get(index - 1);
        doctors.remove((index - 1));
        size -= 1;
        return curr;
    }

    /**
     * Search for the doctor with a given ID.
     *
     * @param id ID of the doctor to find.
     * @return Doctor with the ID given.
     */
    public static Doctor searchDoctor(String id) {
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getId().trim().equals(id)) {
                return doctors.get(i);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String toPrint = boundary + "Here are the doctors in this hospital:" + System.lineSeparator();
        for (int i = 1; i <= size; i++) {
            toPrint += (i + ". " + getDoctor(i) + System.lineSeparator());

        }
        toPrint += ("You have " + size + " doctors recorded in the system."
                            + System.lineSeparator() + boundary);
        return toPrint;
    }
}
