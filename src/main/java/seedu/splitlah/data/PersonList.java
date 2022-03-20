package seedu.splitlah.data;public class PersonList {
    private ArrayList<Person> personList;
    public PersonList() {
        this.personList = new ArrayList<>();
    }
    public int getSize() {
        return personList.size();
    }
    public boolean isEmpty() {
        return personList.isEmpty();
    }
    public Person getPerson(int index) {
        return personList.get(index);
    }
    public void addPerson(Person person) {
        if (!personList.contains(person)) {
            personList.add(person);
        }
    }
    public ArrayList<Person> getPersonList() {
        return personList;
    }
    public void convertToPersonList(String[] personNames) {
        for (String name : personNames) {
            Person newPerson = new Person(name);
            addPerson(newPerson);
        }
    }
    public void mergeListOfPersons(ArrayList<Person> groupPersonList) {
        for (Person person : groupPersonList) {
            addPerson(person);
        }
    }
}
