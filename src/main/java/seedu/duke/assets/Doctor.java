package seedu.duke.assets;

public class Doctor extends Person {
    private String nric;
    private String fullName;
    private int age;
    private char gender;
    private String address;
    private String dob;
    private String specialization;

    public Doctor(String nric, String fullName, int age, char gender, String address,
                  String dob, String specialization) {
        super(nric, fullName, age, gender, address, dob);
        this.specialization = specialization;
    }
    /*
    public String getSpecialization() {
        return specialization;
    }
    */

}
