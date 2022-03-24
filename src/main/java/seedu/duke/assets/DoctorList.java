package seedu.duke.assets;

import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.NotFoundException;
import seedu.duke.helper.UI;

import java.util.ArrayList;


public class DoctorList extends List {

    private ArrayList<Doctor> doctors = new ArrayList<>();

    public Doctor getDoctor(String nric) {
        for (Doctor doctor : doctors) {
            if (doctor.getNric().equals(nric)) {
                return doctor;
            }
        }
        return null;
    }

    public void add(String[] addDoctorParameters) throws DuplicateEntryException {
        if (getDoctor(addDoctorParameters[0]) != null) {
            throw new DuplicateEntryException();
        }
        Doctor newDoctor = new Doctor(addDoctorParameters[0],addDoctorParameters[1],
                Integer.parseInt(addDoctorParameters[2]), addDoctorParameters[3].charAt(0),
                addDoctorParameters[4],addDoctorParameters[5], addDoctorParameters[6]);
        doctors.add(newDoctor);
    }

    //view particular doctor
    public void view(String nric) {
        Doctor doctor = getDoctor(nric);
        if (doctor == null) {
            UI.printParagraph("Doctor doesn't exist please try again!");
            return;
        }
        UI.printParagraph(doctor.toString());
    }

    //view all doctor
    public void view() {
        UI.printParagraph(toString());
    }


    @Override
    public void edit(String[] parameters) throws NotFoundException {

    }


    //get the number of doctors
    public int getSize() {
        return doctors.size();
    }

    //remove the specific doctor
    public void remove(String nric) throws NotFoundException {
        for (int i = 0; i < getSize(); i++) {
            if (doctors.get(i).getNric().equals(nric)) {
                doctors.remove(i);
                return;
            }
        }
        throw new NotFoundException();
    }

    @Override
    public String toString() {
        if (getSize() == 0) {
            return "There are no doctors.";
        }
        String doctorName = "";
        int index = 1;
        for (Doctor doctor : this.doctors) {
            doctorName += String.format("%d. %s", index, doctor.toString());
            if (index != this.getSize()) {
                doctorName += "\n";
            }
            index++;
        }
        return doctorName;
    }

    public ArrayList<Doctor> getList() {
        return doctors;
    }
}
