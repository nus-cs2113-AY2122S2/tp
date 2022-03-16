package seedu.duke.assets;

public abstract class Person {
    private String nric;
    private String fullName;
    private int age;
    private char gender;
    private String address;
    private String dob;

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
