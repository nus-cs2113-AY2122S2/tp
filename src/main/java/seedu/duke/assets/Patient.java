package seedu.duke.assets;

public class Patient {
    String nric;
    String fullName;
    int age;
    char gender;
    String address;
    String dob;
    String AdmissionDate;

    public Patient(String nric, String fullName, int age, char gender, String address, String dob,
                   String AdmissionDate) {
        this.nric = nric;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.dob = dob;
        this.AdmissionDate = AdmissionDate;
    }

    public String getNric() {
        return nric;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public char getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getDob() {
        return dob;
    }

    public String getAdmissionDate() {
        return AdmissionDate;
    }

    @Override
    public String toString() {
        return "Patient{"
                + "NRIC='" + nric + '\''
                + ", Full Name='" + fullName + '\''
                + ", Age=" + age
                + ", Gender=" + gender
                + ", Address='" + address
                + '\'' + ", DOB='" + dob + '\''
                + ", DateOfAdmission='" + AdmissionDate + '\'' + '}';
    }
}
