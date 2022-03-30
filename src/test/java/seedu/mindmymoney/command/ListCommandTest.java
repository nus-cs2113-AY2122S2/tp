package seedu.mindmymoney.command;

import org.junit.jupiter.api.Test;
import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.data.IncomeList;
import seedu.mindmymoney.userfinancial.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ListCommandTest {
    /**
     * Tests list command with no date on a non-empty list. Prints list of size 1 first,
     * followed by list of size 2, to check for formatting.
     */
    @Test
    void listToString_normalInputsWithNoDate_expectString() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/pm cash /c Personal /d Nike Shoes /a 300 /t 30/03/2022";
        new AddCommand(inputString, user).executeCommand();
        String listInString = new ListCommand("/e", user).expenditureListToString();
        assertEquals("1. $300.0 was spent on Nike Shoes(Personal) using Cash [30/03/2022]\n", listInString);

        String inputString2 = "/pm cash /c Food /d Cream Pie /a 69 /t 30/03/2022";
        new AddCommand(inputString2, user).executeCommand();
        listInString = new ListCommand("/e ", user).expenditureListToString();
        assertEquals("1. $300.0 was spent on Nike Shoes(Personal) using Cash [30/03/2022]\n"
                + "2. $69.0 was spent on Cream Pie(Food) using Cash [30/03/2022]\n", listInString);
        String listInString2 = new ListCommand("/expenses ", user).expenditureListToString();
        assertEquals("1. $300.0 was spent on Nike Shoes(Personal) using Cash [30/03/2022]\n"
                + "2. $69.0 was spent on Cream Pie(Food) using Cash [30/03/2022]\n", listInString2);
    }

    /**
     * Tests list command with exact date, month, year on a non-empty list. Prints list of size 1 first,
     * followed by list of size 2, to check for formatting.
     */
    @Test
    void listToString_normalInputsWithExactDate_expectString() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);

        String inputString = "/pm cash /c Personal /d Nike Shoes /a 300 /t 30/03/2022";
        new AddCommand(inputString, user).executeCommand();
        String listInString = new ListCommand("/e 30/03/2022", user).expenditureListToString();
        assertEquals("1. $300.0 was spent on Nike Shoes(Personal) using Cash [30/03/2022]\n", listInString);
        String inputString2 = "/pm cash /c Food /d Cream Pie /a 69 /t 30/03/2022";
        new AddCommand(inputString2, user).executeCommand();
        String inputString3 = "/pm cash /c Food /d Cream Pie /a 69 /t 30/04/2022";
        new AddCommand(inputString3, user).executeCommand();
        listInString = new ListCommand("/e 30/03/2022", user).expenditureListToString();
        assertEquals("1. $300.0 was spent on Nike Shoes(Personal) using Cash [30/03/2022]\n"
                + "2. $69.0 was spent on Cream Pie(Food) using Cash [30/03/2022]\n", listInString);
    }

    /**
     * Tests list command with exact month, year on a non-empty list. Prints list of size 1 first,
     * followed by list of size 2, to check for formatting.
     */
    @Test
    void listToString_normalInputsWithExactMonth_expectString() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/pm cash /c Personal /d Nike Shoes /a 300 /t 30/03/2022";
        new AddCommand(inputString, user).executeCommand();
        String listInString = new ListCommand("/e 03/2022", user).expenditureListToString();
        assertEquals("1. $300.0 was spent on Nike Shoes(Personal) using Cash [30/03/2022]\n", listInString);
        String inputString2 = "/pm cash /c Food /d Cream Pie /a 69 /t 30/03/2022";
        new AddCommand(inputString2, user).executeCommand();
        String inputString3 = "/pm cash /c Food /d Cream Pie /a 69 /t 30/04/2022";
        new AddCommand(inputString3, user).executeCommand();
        listInString = new ListCommand("/e 03/2022", user).expenditureListToString();
        assertEquals("1. $300.0 was spent on Nike Shoes(Personal) using Cash [30/03/2022]\n"
                + "2. $69.0 was spent on Cream Pie(Food) using Cash [30/03/2022]\n", listInString);
    }

    /**
     * Tests list command with exact year on a non-empty list. Prints list of size 1 first,
     * followed by list of size 2, to check for formatting.
     */
    @Test
    void listToString_normalInputsWithExactYear_expectString() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);

        String inputString = "/pm cash /c Personal /d Nike Shoes /a 300 /t 30/03/2022";
        new AddCommand(inputString, user).executeCommand();
        String listInString = new ListCommand("/e 2022", user).expenditureListToString();
        assertEquals("1. $300.0 was spent on Nike Shoes(Personal) using Cash [30/03/2022]\n", listInString);
        String inputString2 = "/pm cash /c Food /d Cream Pie /a 69 /t 30/03/2022";
        new AddCommand(inputString2, user).executeCommand();
        String inputString3 = "/pm cash /c Food /d Cream Pie /a 69 /t 30/04/2021";
        new AddCommand(inputString3, user).executeCommand();
        listInString = new ListCommand("/e 2022", user).expenditureListToString();
        assertEquals("1. $300.0 was spent on Nike Shoes(Personal) using Cash [30/03/2022]\n"
                + "2. $69.0 was spent on Cream Pie(Food) using Cash [30/03/2022]\n", listInString);
    }

    /**
     * Tests list command on an empty list. Should expect an exception thrown
     */
    @Test
    void listCommand_emptyList_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        ListCommand listCommandTest = new ListCommand("/e", user);

        assertThrows(MindMyMoneyException.class, () -> listCommandTest.executeCommand());
    }

    /**
     * Tests list command for date, month and year on an empty list. Should expect an exception thrown
     */
    @Test
    void listCommand_wrongDateFormat_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        ListCommand listCommandTest = new ListCommand("/e 39/14/2022", user);

        assertThrows(MindMyMoneyException.class, () -> listCommandTest.executeCommand());
    }

    /**
     * Tests list command for month and year on an empty list. Should expect an exception thrown
     */
    @Test
    void listCommand_wrongMonthFormat_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        ListCommand listCommandTest = new ListCommand("/e 4/2022", user);

        assertThrows(MindMyMoneyException.class, () -> listCommandTest.executeCommand());
    }

    /**
     * Tests list command for year on an empty list. Should expect an exception thrown
     */
    @Test
    void listCommand_wrongYearFormat_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        ListCommand listCommandTest = new ListCommand("/e /2022/", user);

        assertThrows(MindMyMoneyException.class, () -> listCommandTest.executeCommand());
    }
}
