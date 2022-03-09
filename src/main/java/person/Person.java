package person;

public class Person {
    private static String name;
    private static ArrayList<Income> incomeList;
    private static ArrayList<Expenditure> expenditureList;

    public Person(String name) {
        this.name = name;
        incomeList = new IncomeList();
        expenditureList = new ExpenditureList();
    }
}
