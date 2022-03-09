package person;

import java.util.ArrayList;

public class PersonList {
    private static ArrayList<Person> personList;
    private static int numberOfMembers;

    public PersonList() {
        personList = new ArrayList<Person>();
        numberOfMembers = 0;
    }
}
