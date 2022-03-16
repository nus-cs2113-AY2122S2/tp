package seedu.duke.assets;

public abstract class Person {
    protected String nric;
    protected String fullName;
    protected int age;
    protected char gender;
    protected String address;
    protected String dob;

    public Person(String nric, String fullName, int age, char gender, String address,
                  String dob) {
        this.nric = nric;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.dob = dob;
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
}
