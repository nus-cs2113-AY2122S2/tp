package seedu.duke.assets;

import java.util.ArrayList;

public class Patient extends Person {
    String dateAdmission;
    protected ArrayList<String> appointmentDate = new ArrayList<>();

    public Patient(String nric, String fullName, int age, char gender, String address, String dob,
                   String dateAdmission) {
        super(nric, fullName, age, gender, address, dob);
        this.dateAdmission = dateAdmission;
    }

    public String getDateAdmission() {
        return dateAdmission;
    }

    public void edit(String fullName, int age, char gender, String address, String dob,
                     String dateAdmission) {
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.dob = dob;
        this.dateAdmission = dateAdmission;
    }

    public String getPatientNric() {
        return nric;
    }

    public String getPatientName() {
        return fullName;
    }

    public int getPatientAge() {
        return age;
    }

    public char getPatientGender() {
        return gender;
    }

    public String getPatientAddress() {
        return address;
    }

    public String getPatientDob() {
        return dob;
    }

    public String getDateOfAdmission() {
        return dateAdmission;
    }



    public void addAppointmentDate(String date) {
        appointmentDate.add(date);
    }

    public String saveString() {
        return nric + "," + fullName + "," + age + "," + gender + "," + address
                + "," +  dob + "," + dateAdmission;
    }

    public ArrayList<String> appointmentDate() {
        return appointmentDate;
    }

    public String saveDateString() {
        String returnString = "";
        for (int i = 0; i < appointmentDate.size(); i++) {
            if (i != 0) {
                returnString += "," + appointmentDate.get(i);
            } else {
                returnString += nric + "," + appointmentDate.get(i);
            }
        }
        return returnString;
    }
}
