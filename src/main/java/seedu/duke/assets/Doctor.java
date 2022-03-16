package seedu.duke.assets;

public class Doctor extends Person {
    String specialization;

    public Doctor(String nric, String fullName, int age, char gender, String address,
                  String dob, String specialization) {
        super(nric, fullName, age, gender, address, dob);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }
    
    // @override toString()

    public String saveString() {
        return nric + "," + fullName + "," + age + "," + gender
                + "," + address + "," + dob + "," + specialization;
    }
}
