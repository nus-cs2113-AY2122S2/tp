package seedu.planitarium.person;

import java.util.ArrayList;

public class PersonList {
    private static ArrayList<Person> personList;
    private static int numberOfMembers;

    public PersonList() {
        personList = new ArrayList<>();
        numberOfMembers = 0;
    }

    public static int getNumberOfMembers() {
        return numberOfMembers;
    }

    public static void addPerson(String name) {
        Person person = new Person(name);
        personList.add(person);
        numberOfMembers++;
    }

    public static void removePerson(int index) {
        personList.remove(index - 1);
    }

    public static double getRemain() {
        double sum = 0;
        for (Person person: personList) {
            sum += person.getDisposable();
        }
        return sum;
    }

    public static void list() {
        for (int i = 0; i < numberOfMembers; i++) {
            Person person = personList.get(i);
            System.out.println((i+1) + ". " + person.getName());
            System.out.println("Income: ");
            person.listIncome();
            System.out.println("Expenditure: ");
            person.listExpenditure();
        }
    }
}
