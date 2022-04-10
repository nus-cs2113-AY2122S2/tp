package seedu.duke.assets;

public class Doctor extends Person {
    private String specialization;
    protected String appointmentDate;

    public Doctor(String nric, String fullName, int age, char gender, String address,
                  String dob, String specialization) {
        super(nric, fullName, age, gender, address, dob);
        this.specialization = specialization;
        this.appointmentDate = appointmentDate;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getAppointmentDate() {
        return appointmentDate;
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
                + "," + dob + "," + specialization + "," + appointmentDate ;
    }



    @Override
    public String toString() {
        return "Nric='" + getNric() + '\''
                + ", Full Name='" + getFullName() + '\''
                + ", Age=" + getAge()
                + ", Address='" + getAddress()
                + ", Gender=" + getGender()
                + '\'' + ", DOB='" + getDob() + '\''
                + ", Specialization='" + getSpecialization() + '\''
                + ", Appointment date='" + getAppointmentDate() + '\'';
    }

    public String appointmentDate() {
        return appointmentDate;
    }
}
