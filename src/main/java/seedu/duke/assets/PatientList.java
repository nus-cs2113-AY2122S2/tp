package seedu.duke.assets;

import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.UserInputErrorException;
import seedu.duke.exception.NotFoundException;
import seedu.duke.helper.CommandLineTable;
import seedu.duke.helper.UI;
import seedu.duke.helper.finder.PatientFinder;

import java.util.ArrayList;


public class PatientList extends List {

    private ArrayList<Patient> patients = new ArrayList<>();
    private ArrayList<Patient> returnedFinderArray = new ArrayList<>();

    public Patient getPatient(String nric) {
        for (Patient patient : patients) {
            if (patient.getNric().equals(nric)) {
                return patient;
            }
        }
        return null;
    }


    public Patient search(String nric) {
        for (Patient patient : patients) {
            if (patient.getPatientNric().equals(nric)) {
                return patient;
            }
        }
        return null;
    }

    public void add(String[] addPatientParameters) throws DuplicateEntryException {
        final int numberOfPatientsBefore = patients.size();
        if (getPatient(addPatientParameters[0]) != null) {
            throw new DuplicateEntryException("Patient with given NRIC already exists!");
        }
        Patient newPatient = new Patient(addPatientParameters[0], addPatientParameters[1],
                Integer.parseInt(addPatientParameters[2]),
                addPatientParameters[3].charAt(0), addPatientParameters[4], addPatientParameters[5],
                addPatientParameters[6]);
        patients.add(newPatient);
        assert patients.size() == numberOfPatientsBefore + 1;
    }

    public String getPatientInfo(Patient patient) {
        return (patient.getPatientNric() + ": "
                + patient.getPatientName() + ", "
                + Integer.toString(patient.getPatientAge()) + ", " + patient.getPatientAddress()
                + ", " + patient.getPatientGender() + ", " + patient.getPatientDob()
                + ", " + patient.getDateOfAdmission());
    }

    public int getSize() {
        return patients.size();
    }

    //view particular patient
    public void view(String nric) throws UserInputErrorException {
        Patient patient = getPatient(nric);
        if (patient == null) {
            throw new UserInputErrorException("Patient doesn't exist please try again!");
        }
        CommandLineTable patientTable = new CommandLineTable();
        patientTable.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
        patientTable.setHeaders("Nric", "FullName", "Age", "Address", "Gender", "Dob",
                "DateRegistration");
        patientTable.addRow(patient.getPatientNric(), patient.getPatientName(),
                String.valueOf(patient.getPatientAge()),
                patient.getPatientAddress(), String.valueOf(patient.getPatientGender()),
                patient.getPatientDob(),
                patient.getDateOfAdmission());
        patientTable.print();
    }


    //view all patients
    public void view() throws UserInputErrorException {
        CommandLineTable patientTable = new CommandLineTable();
        patientTable.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
        patientTable.setHeaders("Nric", "FullName", "Age", "Address", "Gender", "Dob",
                "DateRegistration");
        if (patients.size() == 0) {
            throw new UserInputErrorException("Patient list is empty, please add patient");
        }
        for (Patient patient : patients) {
            patientTable.addRow(patient.getPatientNric(), patient.getPatientName(),
                    String.valueOf(patient.getPatientAge()),
                    patient.getPatientAddress(), String.valueOf(patient.getPatientGender()),
                    patient.getPatientDob(),
                    patient.getDateOfAdmission());
        }
        patientTable.print();
    }

    public void remove(String nric) throws NotFoundException {
        int numberOfPatientsBefore = patients.size();
        for (int i = 0; i < getSize(); i++) {
            if (patients.get(i).getNric().equals(nric)) {
                patients.remove(i);
                assert patients.size() == numberOfPatientsBefore - 1;
                return;
            }
        }
        throw new NotFoundException("There are no patients with given NRIC!");
    }

    public void edit(String[] parameterArray) throws NotFoundException {
        if (search(parameterArray[0]) != null) {
            Patient patient = search(parameterArray[0]);
            patient.edit(parameterArray[1], Integer.parseInt(parameterArray[2]), parameterArray[3].charAt(0),
                    parameterArray[4], parameterArray[5], parameterArray[6]);
            return;
        }
        throw new NotFoundException("There are no patients with given NRIC!");
    }

    public ArrayList<Patient> getList() {
        return patients;
    }

    @Override
    public String toString() {
        if (getSize() == 0) {
            return "There are no patients currently.";
        }
        String output = "";
        int number = 1;
        for (Patient patient : this.patients) {
            output += String.format("%d. %s", number, patient.toString());
            if (number != this.getSize()) {
                output += "\n";
            }
            number++;
        }
        assert !output.isEmpty();
        return output;
    }

    public void findByNric(String[] parameters) {
        try {
            this.returnedFinderArray = PatientFinder.findPatientByNric(patients, parameters[1]);
            displayFoundPatientList();
        } catch (NullPointerException e) {
            UI.printParagraph("Patient with given NRIC doesn't exist. Please try again!");
        }
    }

    public void findByName(String[] parameters) {
        try {
            this.returnedFinderArray = PatientFinder.findPatientByName(patients, parameters[1]);
            displayFoundPatientList();
        } catch (NullPointerException e) {
            UI.printParagraph("Patient with given name doesn't exist. Please try again!");
        }
    }

    public void findByAge(String[] parameters) {
        try {
            this.returnedFinderArray = PatientFinder.findPatientByAge(patients, Integer.parseInt(parameters[1]));
            displayFoundPatientList();
        } catch (NullPointerException e) {
            UI.printParagraph("Patient with given age doesn't exist. Please try again!");
        }
    }

    public void findByGender(String[] parameters) {
        try {
            this.returnedFinderArray = PatientFinder.findPatientByGender(patients, parameters[1].charAt(0));
            displayFoundPatientList();
        } catch (NullPointerException e) {
            UI.printParagraph("Patient with given gender doesn't exist. Please try again!");
        }
    }

    public void findByAddress(String[] parameters) {
        try {
            this.returnedFinderArray = PatientFinder.findPatientByAddress(patients, parameters[1]);
            displayFoundPatientList();
        } catch (NullPointerException e) {
            UI.printParagraph("Patient with given address doesn't exist. Please try again!");
        }
    }

    public void findByDob(String[] parameters) {
        try {
            this.returnedFinderArray = PatientFinder.findPatientByDob(patients, parameters[1]);
            displayFoundPatientList();
        } catch (NullPointerException e) {
            UI.printParagraph("Patient with given DOB doesn't exist. Please try again!");
        }
    }

    public void findByDateAdmission(String[] parameters) {
        try {
            this.returnedFinderArray = PatientFinder.findPatientByDateAdmission(patients, parameters[1]);
            displayFoundPatientList();
        } catch (NullPointerException e) {
            UI.printParagraph("Patient with given registration date doesn't exist. Please try again!");
        }
    }

    private void displayFoundPatientList() {
        if (returnedFinderArray.isEmpty()) {
            UI.printParagraph("Patient doesn't exist please try again!");
        } else {
            CommandLineTable findPatientTable = new CommandLineTable();
            for (int i = 0; i < returnedFinderArray.size(); i++) {

                findPatientTable.setShowVerticalLines(true);
                findPatientTable.setHeaders("Nric", "Full Name", "Age", "Address", "Gender", "Dob",
                        "Registration Date");
                findPatientTable.addRow(returnedFinderArray.get(i).getNric(),
                        returnedFinderArray.get(i).getFullName(),
                        String.valueOf(returnedFinderArray.get(i).getAge()),
                        returnedFinderArray.get(i).getAddress(),
                        String.valueOf(returnedFinderArray.get(i).getGender()),
                        returnedFinderArray.get(i).getDob(),
                        returnedFinderArray.get(i).getDateAdmission());
            }
            findPatientTable.print();
        }
    }

}

