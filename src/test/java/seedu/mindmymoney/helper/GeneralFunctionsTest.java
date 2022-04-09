package seedu.mindmymoney.helper;

import org.junit.jupiter.api.Test;
import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.command.AddCommand;
import seedu.mindmymoney.command.ListCommand;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.data.IncomeList;
import seedu.mindmymoney.userfinancial.CreditCard;
import seedu.mindmymoney.userfinancial.Expenditure;
import seedu.mindmymoney.userfinancial.User;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.mindmymoney.constants.ExpenditureFields.AMOUNT;
import static seedu.mindmymoney.constants.ExpenditureFields.CATEGORY;
import static seedu.mindmymoney.constants.ExpenditureFields.DESCRIPTION;
import static seedu.mindmymoney.constants.ExpenditureFields.EXPENDITURE;
import static seedu.mindmymoney.constants.ExpenditureFields.TIME;
import static seedu.mindmymoney.constants.Indexes.INDEX_OF_FIRST_ITEM;
import static seedu.mindmymoney.constants.Indexes.INDEX_OF_SECOND_ITEM;

class GeneralFunctionsTest {
    private final ByteArrayOutputStream capturedOut = new ByteArrayOutputStream();
    private final PrintStream stdout = System.out;

    public void setUp() {
        System.setOut(new PrintStream(capturedOut));
    }

    /**
     * Tests if findItemInList is able to search for the right fields.
     */
    @Test
    void generalFunction_findItemInList_expectItemFound() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        creditCardTestList.add(new CreditCard("dbs", 0.05, 50000));
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputStringOne = "/e /pm dbs /c Personal /d Nike Shoes /a 300 /t 30/03/2022";
        new AddCommand(inputStringOne, user).executeCommand();
        String inputStringTwo = "/e /pm cash /c Food /d Shoes /a 30 /t 30/04/2021";
        new AddCommand(inputStringTwo, user).executeCommand();
        String listInString = new ListCommand("/e", user).expenditureListToString();
        assertEquals("1. $300.00 was spent on Nike Shoes(Personal) using dbs [30/03/2022]\n"
            + "2. $30.00 was spent on Shoes(Food) using Cash [30/04/2021]\n", listInString);

        // Tests if findItemInList searches for the right payment method
        ArrayList<Expenditure> result = GeneralFunctions.findItemsInList("Cash",
            EXPENDITURE.toString(), expenditureTestList);
        assertEquals(expenditureTestList.get(INDEX_OF_SECOND_ITEM), result.get(INDEX_OF_FIRST_ITEM));

        // Tests if findItemInList searches for the right category
        result = GeneralFunctions.findItemsInList("o",
            CATEGORY.toString(), expenditureTestList);
        assertEquals(expenditureTestList.get(INDEX_OF_FIRST_ITEM), result.get(INDEX_OF_FIRST_ITEM));

        // Tests if findItemInList searches for the right description
        result = GeneralFunctions.findItemsInList("Shoes",
            DESCRIPTION.toString(), expenditureTestList);
        assertEquals(expenditureTestList.get(INDEX_OF_FIRST_ITEM), result.get(INDEX_OF_FIRST_ITEM));
        assertEquals(expenditureTestList.get(INDEX_OF_SECOND_ITEM), result.get(INDEX_OF_SECOND_ITEM));

        // Tests if findItemInList searches for the right amount
        result = GeneralFunctions.findItemsInList("300",
            AMOUNT.toString(), expenditureTestList);
        assertEquals(expenditureTestList.get(INDEX_OF_FIRST_ITEM), result.get(INDEX_OF_FIRST_ITEM));

        // Tests if findItemInList searches for the right time
        result = GeneralFunctions.findItemsInList("30",
            TIME.toString(), expenditureTestList);
        assertEquals(expenditureTestList.get(INDEX_OF_FIRST_ITEM), result.get(INDEX_OF_FIRST_ITEM));
        assertEquals(expenditureTestList.get(INDEX_OF_SECOND_ITEM), result.get(INDEX_OF_SECOND_ITEM));
    }

    /**
     * Tests if findItemInList is able to search with invalid input.
     */
    @Test
    void generalFunction_findItemInListInvalidInput_expectException() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        creditCardTestList.add(new CreditCard("dbs", 0.05, 50000));
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputStringOne = "/e /pm dbs /c Personal /d Nike Shoes /a 300 /t 30/03/2022";
        new AddCommand(inputStringOne, user).executeCommand();
        String inputStringTwo = "/e /pm cash /c Food /d Shoes /a 30 /t 30/04/2021";
        new AddCommand(inputStringTwo, user).executeCommand();
        String listInString = new ListCommand("/e", user).expenditureListToString();
        assertEquals("1. $300.00 was spent on Nike Shoes(Personal) using dbs [30/03/2022]\n"
            + "2. $30.00 was spent on Shoes(Food) using Cash [30/04/2021]\n", listInString);

        assertThrows(MindMyMoneyException.class,
            () -> GeneralFunctions.findItemsInList("abc",
                AMOUNT.toString(), expenditureTestList));

        assertThrows(MindMyMoneyException.class,
            () -> GeneralFunctions.findItemsInList("abc",
                "abc", expenditureTestList));

        assertThrows(MindMyMoneyException.class,
            () -> GeneralFunctions.findItemsInList("abc",
                DESCRIPTION.toString(), expenditureTestList));
    }

}