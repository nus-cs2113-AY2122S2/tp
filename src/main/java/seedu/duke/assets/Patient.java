package seedu.duke.assets;

public class Patient extends Person {
    String dateAdmission;

    public Patient(String nric, String fullName, int age, char gender, String address, String dob,
                   String dateAdmission) {
        super(nric, fullName, age, gender, address, dob);
        this.dateAdmission = dateAdmission;
    }

    public String getDateAdmission() {
        return dateAdmission;
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
                + ", DateOfAdmission='" + admissionDate + '\'' + '}';
    }
}
