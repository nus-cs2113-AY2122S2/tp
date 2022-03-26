package seedu.duke.assets;

public class Patient extends Person {
    String dateAdmission;

    public Patient(String nric, String fullName, int age, String address, char gender, String dob,
                   String dateAdmission) {
        super(nric, fullName, age, address, gender, dob);
        this.dateAdmission = dateAdmission;
    }

    public String getDateAdmission() {
        return dateAdmission;
    }

    public void edit(String fullName, int age, String address, char gender, String dob,
                     String dateAdmission) {
        //this.nric = nric;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.dob = dob;
        this.dateAdmission = dateAdmission;
    }
/*
    @Override
    public String toString() {
        return  "NRIC='" + getNric() + '\''
                + ", Full Name='" + getFullName() + '\''
                + ", Age=" + getAge()
                + ", Gender=" + getGender()
                + ", Address='" + getAddress()
                + '\'' + ", DOB='" + getDob() + '\''
                + ", DateOfAdmission='" + getDateAdmission() + '\'';
    }

 */

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

    public String getPatientDOB() {
        return dob;
    }

    public String getDateOfAdmission() {
        return dateAdmission;
    }


    public String saveString() {
        return nric + "," + fullName + "," + age + "," + address + "," + gender
                + "," +  dob + "," + dateAdmission;
    }
}
