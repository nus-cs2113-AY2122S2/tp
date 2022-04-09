package seedu.duke.assets;

public class Doctor extends Person {
    private String specialization;

    public Doctor(String nric, String fullName, int age, char gender, String address,
                  String dob, String specialization) {
        super(nric, fullName, age, gender, address, dob);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    // @override toString()

    public void edit(String fullName, int age, String address, char gender, String dob,
                     String specialization) {
        //this.nric = nric;
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

}
