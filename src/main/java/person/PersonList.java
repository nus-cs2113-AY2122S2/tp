package person;

import java.util.ArrayList;

public class PersonList {
    private static ArrayList<Person> personList;
    private static int numberOfMembers;

    public PersonList() {
        personList = new ArrayList<Person>();
        numberOfMembers = 0;
    }

    public static int getNumberOfMembers() {
        return numberOfMembers;
    }

    public static void addPerson(String name) {
        person = new Person(name);
        personList.add(Person);
    }
}
