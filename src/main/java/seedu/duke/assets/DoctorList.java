package seedu.duke.assets;

import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.NotFoundException;
import seedu.duke.helper.UI;
import seedu.duke.helper.command.CommandLineTable;
import seedu.duke.helper.finder.DoctorFinder;

import java.util.ArrayList;


public class DoctorList extends List {

    private ArrayList<Doctor> doctors = new ArrayList<>();
    private ArrayList<Doctor> returnedFinderArray = new ArrayList<>();

    public Doctor getDoctor(String nric) {
        for (Doctor doctor : doctors) {
            if (doctor.getNric().equals(nric)) {
                return doctor;
            }
        }
        return null;
    }

    public Doctor search(String nric) {
        for (Doctor doctor : doctors) {
            if (doctor.getNric().equals(nric)) {
                return doctor;
            }
        }
        return null;
    }

    public void find(String[] command){
    }

    public void add(String[] addDoctorParameters) throws DuplicateEntryException {
        if (getDoctor(addDoctorParameters[0]) != null) {
            throw new DuplicateEntryException("Doctor with given NRIC already exists!");
        }
        Doctor newDoctor = new Doctor(addDoctorParameters[0],addDoctorParameters[1],
                Integer.parseInt(addDoctorParameters[2]),
                addDoctorParameters[3].charAt(0), addDoctorParameters[4],addDoctorParameters[5],
                addDoctorParameters[6]);
        doctors.add(newDoctor);
    }

    //view particular doctor
    public void view(String nric) {
        Doctor doctor = getDoctor(nric);
        if (doctor == null) {
            UI.printParagraph("Doctor doesn't exist please try again!");
            return;
        }
        CommandLineTable doctorTable = new CommandLineTable();
        doctorTable.setShowVerticalLines(true);
        doctorTable.setHeaders("Nric", "FullName", "Age", "Address", "Gender", "Dob",
                "Specialization");
        doctorTable.addRow(doctor.getNric(), doctor.getFullName(), String.valueOf(doctor.getAge()),
                doctor.getAddress(), String.valueOf(doctor.getGender()), doctor.getDob(),
                doctor.getSpecialization());
        doctorTable.print();
    }

    //view all doctor
    public void view() {
        CommandLineTable doctorTable = new CommandLineTable();
        //st.setRightAlign(true);//if true then cell text is right aligned
        doctorTable.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
        doctorTable.setHeaders("Nric", "FullName","Age", "Address", "Gender", "Dob",
                "Specialization");
        for (Doctor doctor: doctors) {
            doctorTable.addRow(doctor.getNric(), doctor.getFullName(), String.valueOf(doctor.getAge()),
                    doctor.getAddress(), String.valueOf(doctor.getGender()), doctor.getDob(),
                    doctor.getSpecialization());
        }
        doctorTable.print();
    }

    public void edit(String[] parameterArray) throws NotFoundException {
        if (search(parameterArray[0]) != null) {
            Doctor doctor = search(parameterArray[0]);
            doctor.edit(parameterArray[1], Integer.parseInt(parameterArray[2]), parameterArray[3],
                    (parameterArray[4].charAt(0)), parameterArray[5], parameterArray[6]);
            return;
        }
        throw new NotFoundException("There are no patients with given NRIC!");
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
        throw new NotFoundException("There are no doctors with given NRIC!");
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

    public void findByNric(String[] parameterArray) {
        this.returnedFinderArray = DoctorFinder.findDoctorByNric(doctors, parameterArray[1]);
        createArrayOfFoundDoctors();
    }

    public void findByName(String[] parameterArray) {
        this.returnedFinderArray = DoctorFinder.findDoctorByName(doctors, parameterArray[1]);
        createArrayOfFoundDoctors();
    }

    public void findByAge(String[] parameterArray) {
        this.returnedFinderArray = DoctorFinder.findDoctorByAge(doctors, Integer.parseInt(parameterArray[1]));
        createArrayOfFoundDoctors();
    }

    public void findByGender(String[] parameterArray) {
        this.returnedFinderArray = DoctorFinder.findDoctorByGender(doctors, parameterArray[1].charAt(0));
        createArrayOfFoundDoctors();
    }

    public void findByAddress(String[] parameterArray) {
        this.returnedFinderArray = DoctorFinder.findDoctorByAddress(doctors, parameterArray[1]);
        createArrayOfFoundDoctors();
    }

    public void findByDob(String[] parameterArray) {
        this.returnedFinderArray = DoctorFinder.findDoctorByDob(doctors, parameterArray[1]);
        createArrayOfFoundDoctors();
    }

    public void findBySpecialization(String[] parameterArray) {
        this.returnedFinderArray = DoctorFinder.findDoctorBySpecialization(doctors, parameterArray[1]);
        createArrayOfFoundDoctors();
    }

    private void createArrayOfFoundDoctors() {
        if (returnedFinderArray.isEmpty()) {
            UI.printParagraph("Doctor doesn't exist please try again!");
        } else {
            ArrayList<String> stringArrayLists = new ArrayList<>();
            for (int i = 0; i < returnedFinderArray.size(); i++) {
                CommandLineTable findPatientTable = new CommandLineTable();
                findPatientTable.setShowVerticalLines(true);
                findPatientTable.setHeaders("Nric", "FullName", "Age", "Address", "Gender", "Dob",
                        "Specilization");
                findPatientTable.addRow(returnedFinderArray.get(i).getNric(),
                        returnedFinderArray.get(i).getFullName(),
                        String.valueOf(returnedFinderArray.get(i).getAge()),
                        returnedFinderArray.get(i).getAddress(),
                        String.valueOf(returnedFinderArray.get(i).getGender()),
                        returnedFinderArray.get(i).getDob(),
                        returnedFinderArray.get(i).getSpecialization());
                findPatientTable.print();
            }
            }
        }


    public void findByDateAdmission(String[] parameters) {
        // Intentionally left blank
    }

    public void findById(String[] parameters) {
        // Intentionally left blank
    }

    public void findByDosage(String[] parameters) {
        // Intentionally left blank
    }

    public void findByExpiry(String[] parameters) {
        // Intentionally left blank
    }

    public void findBySideEffects(String[] parameters) {
        // Intentionally left blank
    }

    public void findByQuantity(String[] parameters) {
        // Intentionally left blank
    }
}

