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

    public static void addIncome(int value) {
        incomeList.add(value);
        System.out.println("An income of " + value + " has been added");
    }

    public static void delIncome(int index) {
        int value = incomeList.get(index).value;
        incomeList.remove(index);
        System.out.println("An income of " + value + " has been removed");
    }

    public static void addExpend(String description, int value) {
        expenditureList.add(description, value);
        System.out.println("An expenditure of " + value + " for " + description + " has been added");
    }

    public static void delExpend(int index) {
        String description = expenditureList.get(index).description;
        int value = expenditureList.get(index).value;
        expenditureList.remove(index);
        System.out.println("An expenditure of " + value + " for " + description + " has been removed");
    }
}
