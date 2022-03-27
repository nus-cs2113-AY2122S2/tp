package seedu.mindmymoney.command;

import org.junit.jupiter.api.Test;
import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.userfinancial.CreditCard;
import seedu.mindmymoney.userfinancial.Expenditure;
import seedu.mindmymoney.userfinancial.User;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.mindmymoney.constants.Indexes.LIST_INDEX_CORRECTION;

class AddCommandTest {
    /**
     * Asserts if user is able to add an input with cash.
     */
    @Test
    void addCommand_oneInput_expectListUpdated() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        User user = new User(expenditureTestList, creditCardTestList);
        String inputString = "/e cash /c Personal /d Nike Shoes /a 300 /t 2022-03";

        new AddCommand(inputString, user).executeCommand();
        ArrayList<Expenditure> testList = new ArrayList<>();
        testList.add(new Expenditure("Cash", "Personal", "Nike Shoes",
            300, "Mar 2022"));
        String expectedOutput = getOutput(testList);
        String actualOutput = getOutput(expenditureTestList.expenditureListArray);
        assertEquals(expectedOutput, actualOutput);
        testList.clear();
    }

    /**
     * Asserts if user is able to add an input thats not case sensitive.
     */
    @Test
    void addCommand_caseInsensitiveInput_expectListUpdated() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        User user = new User(expenditureTestList, creditCardTestList);
        String inputString = "/e cASh /c PerSONal /d Nike Shoes /a 300 /t 2022-03";

        new AddCommand(inputString, user).executeCommand();
        ArrayList<Expenditure> testList = new ArrayList<>();
        testList.add(new Expenditure("Cash", "Personal", "Nike Shoes",
            300, "Mar 2022"));
        String expectedOutput = getOutput(testList);
        String actualOutput = getOutput(expenditureTestList.expenditureListArray);
        assertEquals(expectedOutput, actualOutput);
        testList.clear();
    }

    /**
     * Asserts if user is able to add an amount that has more decimal place than float.
     */
    @Test
    void addCommand_wrongDecimalPlace_expectListUpdated() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        User user = new User(expenditureTestList, creditCardTestList);
        String inputString = "/e Cash /c Personal /d Nike Shoes /a 300.1299786222834 /t 2022-03";

        new AddCommand(inputString, user).executeCommand();
        ArrayList<Expenditure> testList = new ArrayList<>();
        testList.add(new Expenditure("Cash", "Personal", "Nike Shoes",
            (float) 300.13, "Mar 2022"));
        String expectedOutput = getOutput(testList);
        String actualOutput = getOutput(expenditureTestList.expenditureListArray);
        assertEquals(expectedOutput, actualOutput);
        testList.clear();
    }

    /**
     * Asserts if user is able to add an input with creditcard.
     */
    @Test
    void addCommand_creditCardInput_expectListUpdated() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        creditCardTestList.add(new CreditCard("posb",0.05,500,500));
        User user = new User(expenditureTestList, creditCardTestList);
        String inputString = "/e posb /c Personal /d Nike Shoes /a 300 /t 2022-03";

        new AddCommand(inputString, user).executeCommand();
        ArrayList<Expenditure> testList = new ArrayList<>();
        testList.add(new Expenditure("posb", "Personal", "Nike Shoes",
            300, "Mar 2022"));
        String expectedOutput = getOutput(testList);
        String actualOutput = getOutput(expenditureTestList.expenditureListArray);
        assertEquals(expectedOutput, actualOutput);
        testList.clear();
    }

    /**
     * Asserts if user is able to add am empty input.
     */
    @Test
    void addCommand_missingInput_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        User user = new User(expenditureTestList, creditCardTestList);
        String inputString = "";

        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(inputString, user).executeCommand());
    }

    /**
     * Asserts if user is able to add a non-numerical amount.
     */
    @Test
    void addCommand_nonNumberAmount_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        User user = new User(expenditureTestList, creditCardTestList);
        String inputString = "/e cash /c Personal /d Nike Shoes /a abcd /t 2022-03";

        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(inputString, user).executeCommand());
    }

    /**
     * Asserts if user is able to add an incorrect flag.
     */
    @Test
    void addCommand_incorrectFlags_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        User user = new User(expenditureTestList, creditCardTestList);
        String inputString = "/e cash /z Personal /d Nike Shoes /a 500 /t 2022-03";

        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(inputString, user).executeCommand());
    }

    /**
     * Asserts if user is able to add an incorrect order of flag.
     */
    @Test
    void addCommand_incorrectOrderOfFlags_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        User user = new User(expenditureTestList, creditCardTestList);
        String inputString = "/e cash /d Nike Shoes /a 500 /t 2022-03 /c Personal";

        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(inputString, user).executeCommand());
    }

    /**
     * Asserts if user is able to add an incorrect Expenditure Method.
     */
    @Test
    void addCommand_incorrectExpenditureMethod_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        User user = new User(expenditureTestList, creditCardTestList);
        String inputString = "/e casssh /c Personal /d Nike Shoes /a 500 /t 2022-03 ";

        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(inputString, user).executeCommand());
    }

    /**
     * Asserts if user is able to add an incorrect Category.
     */
    @Test
    void addCommand_incorrectCategory_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        User user = new User(expenditureTestList, creditCardTestList);
        String inputString = "/e cash /c Person /d Nike Shoes /a 500 /t 2022-03";

        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(inputString, user).executeCommand());
    }

    /**
     * Asserts if user is able to add an incorrect Date.
     */
    @Test
    void addCommand_incorrectDate_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        User user = new User(expenditureTestList, creditCardTestList);
        String inputString = "/e cash /c Person /d Nike Shoes /a 500 /t 2022-99";

        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(inputString, user).executeCommand());
    }

    /**
     * Asserts if user is able to add an empty expenditure method.
     */
    @Test
    void addCommand_nullExpenditureMethod_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        User user = new User(expenditureTestList, creditCardTestList);
        String inputString = "/e  /c Person /d Nike Shoes /a 500 /t 2022-01";

        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(inputString, user).executeCommand());
    }

    /**
     * Asserts if user is able to add an empty category.
     */
    @Test
    void addCommand_nullCategory_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        User user = new User(expenditureTestList, creditCardTestList);
        String inputString = "/e Cash /c  /d Nike Shoes /a 500 /t 2022-01";

        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(inputString, user).executeCommand());
    }

    /**
     * Asserts if user is able to add an empty description.
     */
    @Test
    void addCommand_nullDescription_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        User user = new User(expenditureTestList, creditCardTestList);
        String inputString = "/e Cash /c Food /d  /a 500 /t 2022-01";

        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(inputString, user).executeCommand());
    }

    /**
     * Asserts if user is able to add an empty amount.
     */
    @Test
    void addCommand_nullAmount_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        User user = new User(expenditureTestList, creditCardTestList);
        String inputString = "/e Cash /c Food /d Shoes /a  /t 2022-01";

        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(inputString, user).executeCommand());
    }

    /**
     * Asserts if user is able to add empty time.
     */
    @Test
    void addCommand_nullDate_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        User user = new User(expenditureTestList, creditCardTestList);
        String inputString = "/e Cash /c Food /d Shoes /a 500 /t";

        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(inputString, user).executeCommand());
    }

    /**
     * Asserts if user is able to add a field with no spaces between flags.
     */
    @Test
    void addCommand_lackSpacingBetweenFlags_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        User user = new User(expenditureTestList, creditCardTestList);
        String inputString = "/e/c Person /d Nike Shoes /a 500 /t 2022-01";

        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(inputString, user).executeCommand());
    }


    /**
     * Forms a string from the description and the amount of the last element of the list.
     *
     * @param list is the Arraylist to form the string from
     * @return description + amount if list is not empty, else it returns an empty string
     */
    public String getOutput(ArrayList<Expenditure> list) {
        if (!list.isEmpty()) {
            return list.get(list.size() + LIST_INDEX_CORRECTION).getExpenditure()
                + list.get(list.size() + LIST_INDEX_CORRECTION).getCategory()
                + list.get(list.size() + LIST_INDEX_CORRECTION).getDescription()
                + list.get(list.size() + LIST_INDEX_CORRECTION).getAmount()
                + list.get(list.size() + LIST_INDEX_CORRECTION).getTime();
        }
        return "";
    }
}
