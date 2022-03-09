package seedu.planitarium.person;

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
        personList.add(person);
        numberOfMembers++;
    }

    public static void removePerson(int index) {
        personList.remove(index);
    }

    public static float getRemain() {
        float sum = 0;
        for (Person person: personList) {
            sum += person.getDisposable();
        }
        return sum;
    }

    public static void list() {
        for (int i = 0; i < personList.size(); i++) {
            System.out.println((i+1) + ". " + name + System.lineSeparator() +
                    "Income: " + personList.get(i).listIncome() + System.lineSeparator() +
                    "Expenditure: " + personList.get(i).listExpenditure());
        }
    }
}
