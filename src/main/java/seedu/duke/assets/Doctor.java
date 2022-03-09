package seedu.duke.assets;

public class Doctor {
    String nric;
    String fullName;
    int age;
    char gender;
    String Address;
    String dob;
    String specialization;

    public Doctor(String nric, String fullName, int age, char gender, String address, String dob,
                  String specialization) {
        this.nric = nric;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        Address = address;
        this.dob = dob;
        this.specialization = specialization;
    }
}
