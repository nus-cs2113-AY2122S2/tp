package seedu.duke.assets;

public abstract class Person {
    protected String nric;
    protected String fullName;
    protected int age;
    protected String address;
    protected char gender;
    protected String dob;

    public Person(String nric, String fullName, int age, char gender, String address, String dob) {
        this.nric = nric;
        this.fullName = fullName;
        this.age = age;
        this.address = address;
        this.gender = gender;
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
