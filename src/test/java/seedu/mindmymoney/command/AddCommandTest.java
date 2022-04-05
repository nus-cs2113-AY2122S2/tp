package seedu.mindmymoney.command;

import org.junit.jupiter.api.Test;
import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.data.IncomeList;
import seedu.mindmymoney.userfinancial.CreditCard;
import seedu.mindmymoney.userfinancial.Expenditure;
import seedu.mindmymoney.userfinancial.Income;
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
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);

        String inputString = "/e /pm cash /c Personal /d Nike Shoes /a 300 /t 30/03/2022";
        new AddCommand(inputString, user).executeCommand();
        ArrayList<Expenditure> testList = new ArrayList<>();
        testList.add(new Expenditure("Cash", "Personal", "Nike Shoes",
            300, "30/03/2022"));
        String expectedOutput = getExpenditureOutput(testList);
        String actualOutput = getExpenditureOutput(expenditureTestList.expenditureListArray);
        assertEquals(expectedOutput, actualOutput);
        testList.clear();
    }

    /**
     * Asserts if user is able to add an input that is not case-sensitive.
     */
    @Test
    void addCommand_caseInsensitiveInput_expectListUpdated() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/e /pm cASh /c PerSONal /d Nike Shoes /a 300 /t 30/03/2022";

        new AddCommand(inputString, user).executeCommand();
        ArrayList<Expenditure> testList = new ArrayList<>();
        testList.add(new Expenditure("Cash", "Personal", "Nike Shoes",
            300, "30/03/2022"));
        String expectedOutput = getExpenditureOutput(testList);
        String actualOutput = getExpenditureOutput(expenditureTestList.expenditureListArray);
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
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/e /pm Cash /c Personal /d Nike Shoes /a 300.1299786222834 /t 30/03/2022";

        new AddCommand(inputString, user).executeCommand();
        ArrayList<Expenditure> testList = new ArrayList<>();
        testList.add(new Expenditure("Cash", "Personal", "Nike Shoes",
            (float) 300.13, "30/03/2022"));
        String expectedOutput = getExpenditureOutput(testList);
        String actualOutput = getExpenditureOutput(expenditureTestList.expenditureListArray);
        assertEquals(expectedOutput, actualOutput);
        testList.clear();
    }

    /**
     * Asserts if user is able to add an input with credit card.
     */
    @Test
    void addCommand_creditCardInput_expectListUpdated() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        creditCardTestList.add(new CreditCard("posb",0.05,500));
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/e /pm posb /c Personal /d Nike Shoes /a 300 /t 30/03/2022";

        new AddCommand(inputString, user).executeCommand();
        ArrayList<Expenditure> testList = new ArrayList<>();
        testList.add(new Expenditure("posb", "Personal", "Nike Shoes",
            300, "30/03/2022"));
        String expectedOutput = getExpenditureOutput(testList);
        String actualOutput = getExpenditureOutput(expenditureTestList.expenditureListArray);
        assertEquals(expectedOutput, actualOutput);
        testList.clear();
    }

    /**
     * Asserts if user is able to add an Income entry.
     *
     * @throws MindMyMoneyException when an invalid command is received.
     */
    @Test
    void addCommand_incomeInput_expectListUpdated() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);

        String inputString = "/i /a 3000 /c Salary";
        new AddCommand(inputString, user).executeCommand();

        ArrayList<Income> testList = new ArrayList<>();
        testList.add(new Income(3000, "Salary"));

        String expectedOutput = getIncomeOutput(testList);
        String actualOutput = getIncomeOutput(incomeList.incomeListArray);
        assertEquals(expectedOutput, actualOutput);

        testList.clear();
    }

    /**
     * Asserts if user is able to add an Income entry that is not case-sensitive.
     */
    @Test
    void addCommand_caseInsensitiveIncomeInput_expectListUpdated() throws MindMyMoneyException {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);

        String inputString = "/i /a 3000 /c SaLaRy";
        new AddCommand(inputString, user).executeCommand();

        ArrayList<Income> testList = new ArrayList<>();
        testList.add(new Income(3000, "Salary"));

        String expectedOutput = getIncomeOutput(testList);
        String actualOutput = getIncomeOutput(incomeList.incomeListArray);
        assertEquals(expectedOutput, actualOutput);

        testList.clear();
    }

    /**
     * Asserts if user is able to add an empty input.
     */
    @Test
    void addCommand_missingInput_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
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
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/e /pm cash /c Personal /d Nike Shoes /a abcd /t 30/03/2022";

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
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/e /pm cash /z Personal /d Nike Shoes /a 500 /t 30/03/2022";

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
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/e /pm cash /d Nike Shoes /a 500 /t 30/03/2022 /c Personal";

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
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/e /pm casssh /c Personal /d Nike Shoes /a 500 /t 30/03/2022 ";

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
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/e /pm cash /c Person /d Nike Shoes /a 500 /t 30/03/2022";

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
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);

        String firstInputString = "/e /pm cash /c Person /d Nike Shoes /a 500 /t 30/4/2022";
        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(firstInputString, user).executeCommand());
        String secondInputString = "/e /pm cash /c Person /d Nike Shoes /a 500 /t 04/2022";

        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(secondInputString, user).executeCommand());
        String thirdInputString = "/e /pm cash /c Person /d Nike Shoes /a 500 /t 2022";

        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(thirdInputString, user).executeCommand());

        String fourthInputString = "/e /pm cash /c Person /d Nike Shoes /a 500 /t 38/14/2022";
        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(fourthInputString, user).executeCommand());

        String fifthInputString = "/pm cash /c Food /d Porridge /a 4.50 /t 31/11/2021";
        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(fifthInputString, user).executeCommand());

        String sixthInputString = "/pm cash /c Food /d Porridge /a 4.50 /t 29/02/2021";
        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(sixthInputString, user).executeCommand());

        String seventhInputString = "/pm cash /c Food /d Porridge /a 4.50 /t 30/02/2020";
        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(seventhInputString, user).executeCommand());

        String eighthInputString = "/pm cash /c Food /d Porridge /a 4.50 /t 31/04/2020";
        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(eighthInputString, user).executeCommand());
    }

    /**
     * Asserts if user is able to add an empty expenditure method.
     */
    @Test
    void addCommand_nullExpenditureMethod_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/e /pm  /c Person /d Nike Shoes /a 500 /t 30/03/2022";

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
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/e /pm Cash /c  /d Nike Shoes /a 500 /t 30/03/2022";

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
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/e /pm Cash /c Food /d  /a 500 /t 30/03/2022";

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
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/e /pm Cash /c Food /d Shoes /a  /t 30/03/2022";

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
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/e /pm Cash /c Food /d Shoes /a 500 /t";

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
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/e /pm/c Person /d Nike Shoes /a 500 /t 30/03/2022";

        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(inputString, user).executeCommand());
    }

    /**
     * Asserts if user is able to add an income entry with a non-numerical amount.
     */
    @Test
    void addCommand_notNumberIncomeAmount_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);

        String inputString = "/i /a three-thousand /c Salary";
        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(inputString, user).executeCommand());
    }

    /**
     * Asserts if user is able to add an income entry with an invalid income category.
     */
    @Test
    void addCommand_notIncomeCategory_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);

        String inputString = "/i /a 3000 /c notAnIncomeCategory";
        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(inputString, user).executeCommand());
    }

    /**
     * Asserts if user is able to add an improper flag.
     */
    @Test
    void addCommand_notProperFlag_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/asdf";
        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(inputString, user).executeCommand());
    }

    /**
     * Asserts if user is able to an invalid time.
     */
    @Test
    void addCommand_notValidTime_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/e /pm cash /c Person /d Nike Shoes /a 500 /t 30/032022";

        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(inputString, user).executeCommand());

    }

    /**
     * Asserts if user is able to add a day larger than 29 feb.
     */
    @Test
    void addCommand_wrongDateForLeapYear_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/e /pm cash /c Person /d Nike Shoes /a 500 /t 30/02/2020";

        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(inputString, user).executeCommand());

    }

    /**
     * Asserts if user is able to add a day larger than 30 for months with only 30 days.
     */
    @Test
    void addCommand_wrongDateForMonthWithThirtyDays_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/e /pm cash /c Person /d Nike Shoes /a 500 /t 31/09/2018";

        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(inputString, user).executeCommand());

    }

    /**
     * Asserts if user is able to add a larger than 28 feb on a non leap year.
     */
    @Test
    void addCommand_wrongDateForNonLeapYear_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/e /pm cash /c Person /d Nike Shoes /a 500 /t 29/02/2018";

        assertThrows(MindMyMoneyException.class,
            () -> new AddCommand(inputString, user).executeCommand());

    }

    /**
     * Test if program is able to exit
     */
    @Test
    void addCommand_isExit_expectException() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        CreditCardList creditCardTestList = new CreditCardList();
        IncomeList incomeList = new IncomeList();
        User user = new User(expenditureTestList, creditCardTestList, incomeList);
        String inputString = "/e /pm cash /c Person /d Nike Shoes /a 500 /t 29/02/2018";

        assertEquals(false, new AddCommand(inputString, user).isExit());

    }
    /**
     * Gets the last expenditure entry in the expenditure list and formats it to a string.
     *
     * @param list is the expenditure list.
     * @return expenditure entry as a string if list is not empty, else it returns an empty string.
     */
    public String getExpenditureOutput(ArrayList<Expenditure> list) {
        if (!list.isEmpty()) {
            return list.get(list.size() + LIST_INDEX_CORRECTION).getPaymentMethod()
                + list.get(list.size() + LIST_INDEX_CORRECTION).getCategory()
                + list.get(list.size() + LIST_INDEX_CORRECTION).getDescription()
                + list.get(list.size() + LIST_INDEX_CORRECTION).getAmount()
                + list.get(list.size() + LIST_INDEX_CORRECTION).getTime();
        }
        return "";
    }

    /**
     * Gets the last income entry in the income list and formats it to a string.
     *
     * @param list is the income list.
     * @return income entry as a string if list is not empty, else it returns an empty string.
     */
    public String getIncomeOutput(ArrayList<Income> list) {
        if (!list.isEmpty()) {
            return list.get(list.size() + LIST_INDEX_CORRECTION).getAmount()
                    + list.get(list.size() + LIST_INDEX_CORRECTION).getCategory();
        }
        return "";
    }
}
