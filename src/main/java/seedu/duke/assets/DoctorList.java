package seedu.duke.assets;

import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.UserInputErrorException;
import seedu.duke.exception.NotFoundException;
import seedu.duke.helper.CommandLineTable;
import seedu.duke.helper.UI;
import seedu.duke.helper.finder.DoctorFinder;

import java.util.ArrayList;


public class DoctorList extends List {

    private ArrayList<Doctor> doctors = new ArrayList<>();
    private ArrayList<Doctor> returnedFinderArray = new ArrayList<>();
    private final String title = "Table of doctors";


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

    public void find(String[] command) {
    }

    public void add(String[] addDoctorParameters) throws DuplicateEntryException {
        if (getDoctor(addDoctorParameters[0]) != null) {
            throw new DuplicateEntryException("Doctor with given NRIC already exists!");
        }
        Doctor newDoctor = new Doctor(addDoctorParameters[0], addDoctorParameters[1],
                Integer.parseInt(addDoctorParameters[2]),
                addDoctorParameters[3].charAt(0), addDoctorParameters[4], addDoctorParameters[5],
                addDoctorParameters[6]);
        doctors.add(newDoctor);
    }

    //view all doctor
    public void view() throws UserInputErrorException {
        CommandLineTable doctorTable = new CommandLineTable(title);
        doctorTable.setShowVerticalLines(true);
        doctorTable.setHeaders("Nric", "FullName", "Age", "Address", "Gender", "Dob",
                "Specialization");
        if (doctors.size() == 0) {
            throw new UserInputErrorException("Doctor list is empty, please add doctor");
        }
        for (Doctor doctor : doctors) {
            doctorTable.addRow(doctor.getNric(), doctor.getFullName(), String.valueOf(doctor.getAge()),
                    doctor.getAddress(), String.valueOf(doctor.getGender()), doctor.getDob(),
                    doctor.getSpecialization());
        }
        doctorTable.print();
    }

    //view particular doctor
    public void view(String nric) throws UserInputErrorException {
        Doctor doctor = getDoctor(nric);
        if (doctor == null) {
            throw new UserInputErrorException("Doctor doesn't exist please try again!");
        }
        CommandLineTable doctorTable = new CommandLineTable(title);
        doctorTable.setShowVerticalLines(true);
        doctorTable.setHeaders("Nric", "FullName", "Age", "Address", "Gender", "Dob",
                "Specialization");
        doctorTable.addRow(doctor.getNric(), doctor.getFullName(), String.valueOf(doctor.getAge()),
                doctor.getAddress(), String.valueOf(doctor.getGender()), doctor.getDob(),
                doctor.getSpecialization());
        doctorTable.print();
    }

    public void addAppointmentDate(String nric, String date) {
        for (Doctor doctor : doctors) {
            if (doctor.getNric().equals(nric)) {
                doctor.addAppointmentDate(date);
                break;
            }
        }
    }

    public void removeAppointmentDate(String nric, String date) {
        for (Doctor doctor : doctors) {
            if (doctor.getNric().equals(nric)) {
                doctor.removeAppointmentDate(date);
                break;
            }
        }
    }


    public boolean hasDoctorDate(String nric, String date) throws DuplicateEntryException {
        ArrayList<String> doctorDateList = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if (doctor.getNric().equals(nric)) {
                doctorDateList = doctor.appointmentDateDoctor();
                for (String dateDoctor : doctorDateList) {
                    if (dateDoctor.equals(date)) {
                        return true;
                    }
                    return false;
                }
                return false;
            }
        }
        throw new DuplicateEntryException("Doctor not found");
    }




    public void edit(String[] parameterArray) throws NotFoundException {
        if (search(parameterArray[0]) != null) {
            Doctor doctor = search(parameterArray[0]);
            doctor.edit(parameterArray[1], Integer.parseInt(parameterArray[2]), parameterArray[3].charAt(0),
                    (parameterArray[4]), parameterArray[5], parameterArray[6]);
            return;
        }
        throw new NotFoundException("There are no doctors with given NRIC!");
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
        try {
            this.returnedFinderArray = DoctorFinder.findDoctorByNric(doctors, parameterArray[1]);
            displayFoundDoctorList();
        } catch (NullPointerException e) {
            UI.printParagraph("Doctor with given NRIC doesn't exist. Please try again!");
        }
    }

    public void findByName(String[] parameterArray) {
        try {
            this.returnedFinderArray = DoctorFinder.findDoctorByName(doctors, parameterArray[1]);
            displayFoundDoctorList();
        } catch (NullPointerException e) {
            UI.printParagraph("Doctor with given name doesn't exist. Please try again!");
        }
    }

    public void findByAge(String[] parameterArray) {
        try {
            this.returnedFinderArray = DoctorFinder.findDoctorByAge(doctors, Integer.parseInt(parameterArray[1]));
            displayFoundDoctorList();
        } catch (NullPointerException e) {
            UI.printParagraph("Doctor with given age doesn't exist. Please try again!");
        }
    }

    public void findByGender(String[] parameterArray) {
        try {
            this.returnedFinderArray = DoctorFinder.findDoctorByGender(doctors, parameterArray[1].charAt(0));
            displayFoundDoctorList();
        } catch (NullPointerException e) {
            UI.printParagraph("Doctor with given gender doesn't exist. Please try again!");
        }
    }

    public void findByAddress(String[] parameterArray) {
        try {
            this.returnedFinderArray = DoctorFinder.findDoctorByAddress(doctors, parameterArray[1]);
            displayFoundDoctorList();
        } catch (NullPointerException e) {
            UI.printParagraph("Doctor with given address doesn't exist. Please try again!");
        }
    }

    public void findByDob(String[] parameterArray) {
        try {
            this.returnedFinderArray = DoctorFinder.findDoctorByDob(doctors, parameterArray[1]);
            displayFoundDoctorList();
        } catch (NullPointerException e) {
            UI.printParagraph("Doctor doesn't exist please try again!");
        }
    }

    public void findBySpecialization(String[] parameterArray) {
        try {
            this.returnedFinderArray = DoctorFinder.findDoctorBySpecialization(doctors, parameterArray[1]);
            displayFoundDoctorList();
        } catch (NullPointerException e) {
            UI.printParagraph("Doctor with given specialization doesn't exist. Please try again!");
        }
    }

    private void displayFoundDoctorList() {
        if (returnedFinderArray.isEmpty()) {
            UI.printParagraph("Doctor doesn't exist please try again!");
        } else {
            CommandLineTable findPatientTable = new CommandLineTable(title);
            for (int i = 0; i < returnedFinderArray.size(); i++) {

                findPatientTable.setShowVerticalLines(true);
                findPatientTable.setHeaders("Nric", "Full Name", "Age", "Address", "Gender", "Dob",
                        "Specialization");
                findPatientTable.addRow(returnedFinderArray.get(i).getNric(),
                        returnedFinderArray.get(i).getFullName(),
                        String.valueOf(returnedFinderArray.get(i).getAge()),
                        returnedFinderArray.get(i).getAddress(),
                        String.valueOf(returnedFinderArray.get(i).getGender()),
                        returnedFinderArray.get(i).getDob(),
                        returnedFinderArray.get(i).getSpecialization());
            }
            findPatientTable.print();
        }
    }

    public void loadDate(String[] parameters) {
        String doctorNric = parameters[0];
        for (Doctor a : doctors) {
            if (a.getNric().equals(doctorNric)) {
                for (int i = 1; i < parameters.length; i++) {
                    a.addAppointmentDate(parameters[i]);
                }
            }
        }
    }

}

