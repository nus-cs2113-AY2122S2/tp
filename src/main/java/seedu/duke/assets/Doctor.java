package seedu.duke.assets;

import java.util.ArrayList;

public class Doctor extends Person {
    private String specialization;
    public ArrayList<String> appointmentDate = new ArrayList<>();

    public Doctor(String nric, String fullName, int age, char gender, String address,
                  String dob, String specialization) {
        super(nric, fullName, age, gender, address, dob);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }


    // @override toString()

    public void edit(String fullName, int age, char gender, String address, String dob,
                     String specialization) {
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.dob = dob;
        this.specialization = specialization;
    }

    public String saveString() {
        return nric + "," + fullName + "," + age + "," + gender + "," + address
                + "," + dob + "," + specialization;
    }


    public void addAppointmentDate(String date) {
        appointmentDate.add(date);
    }

    @Override
    public String toString() {
        return "Nric='" + getNric() + '\''
                + ", Full Name='" + getFullName() + '\''
                + ", Age=" + getAge()
                + ", Address='" + getAddress()
                + ", Gender=" + getGender()
                + '\'' + ", DOB='" + getDob() + '\''
                + ", Specialization='" + getSpecialization() + '\'';
    }

    public ArrayList<String> appointmentDateDoctor() {
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
