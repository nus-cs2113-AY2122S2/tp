//@@author 1szheng

package seedu.planitarium.parser;

import org.junit.jupiter.api.Test;
import seedu.planitarium.exceptions.DuplicateDelimiterException;
import seedu.planitarium.exceptions.InvalidIndexException;
import seedu.planitarium.exceptions.InvalidMoneyException;
import seedu.planitarium.exceptions.MissingDelimiterException;
import seedu.planitarium.exceptions.EmptyStringException;
import seedu.planitarium.family.Person;
import seedu.planitarium.family.PersonList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {

    private static final String TYPE_USER_INDEX = "user";
    private static final String TYPE_GROUP_INDEX = "group";
    private static final String TYPE_EXPENDITURE_INDEX = "expenditure";
    private static final String TYPE_INCOME_INDEX = "income";
    private static final String TYPE_CATEGORY_INDEX = "category";

    // error messages
    private static final String ERROR_MSG = "Unknown error is detected from '%s', please check again.";
    private static final String ERROR_INDEX_MSG = "Invalid %s index `%s`";
    private static final String USER_INPUT_NOT_NULL = "User input should not be null";
    private static final String MONEY_INPUT_SHOULD_NOT_BE_NULL = "Money input should not be null";
    private static final String USER_INDEX_SHOULD_NOT_BE_NULL = "User index should not be null";
    private static final String CATEGORY_INDEX_SHOULD_NOT_BE_NULL = "Category index should not be null";
    private static final String EXPENDITURE_INDEX_SHOULD_NOT_BE_NULL = "Expenditure index should not be null";
    private static final String INCOME_INDEX_SHOULD_NOT_BE_NULL = "Income index should not be null";
    private static final String GROUP_INDEX_SHOULD_NOT_BE_NULL = "Group index should not be null";

    private static final String TOO_MANY_DELIMITER_N = "Too many delimiter `/n`";
    private static final String TOO_MANY_DELIMITER_U = "Too many delimiter `/u`";
    private static final String TOO_MANY_DELIMITER_D = "Too many delimiter `/d`";
    private static final String TOO_MANY_DELIMITER_I = "Too many delimiter `/i`";
    private static final String TOO_MANY_DELIMITER_E = "Too many delimiter `/e`";
    private static final String TOO_MANY_DELIMITER_R = "Too many delimiter `/r`";
    private static final String TOO_MANY_DELIMITER_P = "Too many delimiter `/p`";
    private static final String TOO_MANY_DELIMITER_C = "Too many delimiter `/c`";
    private static final String TOO_MANY_DELIMITER_G = "Too many delimiter `/g`";

    private static final String MISSING_DELIMITER_N = "Missing delimiter `/n`";
    private static final String MISSING_DELIMITER_U = "Missing delimiter `/u`";
    private static final String MISSING_DELIMITER_D = "Missing delimiter `/d`";
    private static final String MISSING_DELIMITER_I = "Missing delimiter `/i`";
    private static final String MISSING_DELIMITER_E = "Missing delimiter `/e`";
    private static final String MISSING_DELIMITER_R = "Missing delimiter `/r`";
    private static final String MISSING_DELIMITER_P = "Missing delimiter `/p`";
    private static final String MISSING_DELIMITER_C = "Missing delimiter `/c`";
    private static final String MISSING_DELIMITER_G = "Missing delimiter `/g`";

    private static final String INVALID_MONEY_NEGATIVE_VALUE = "Invalid money value `-10.50`";
    private static final String INVALID_MONEY_NOT_DOUBLE = "Invalid money value `hundred`";

    // misc values
    private static final String EMPTY_INPUT = "";
    private static final String ONE = "1";
    private static final String TWO = "2";
    private static final String ALICE = "Alice";
    private static final String TOO_LOW_INDEX = "0";
    private static final String TOO_HIGH_INDEX = "7";
    private static final int INT_ONE = 1;
    private static final String TEN_FIFTY_STRING = "10.50";
    private static final double TEN_FIFTY_DOUBLE = 10.50;
    private static final String TEN_FIFTY_NEGATIVE = "-10.50";
    private static final String HUNDRED = "hundred";
    private static final String FOOD = "Food";
    private static final String GIFT = "Gift";
    private static final String INCOME_HUNDRED = "100";

    // method test cases
    private static final String ADD_N_ALICE = "add /n Alice";
    private static final String ADD = "add";
    private static final String ADD_NO_DELIMITER = "add alice";
    private static final String ADD_MANY_DELIMITER = "add /n alice /n alice";

    private static final String ADDIN_U_1_D_GIFT_I_100 = "addin /u 1 /d Gift /i 100";
    private static final String ADDIN_U_NO_DELIMITER = "addin 1 /d Gift /i 100";
    private static final String ADDIN_U_MANY_DELIMITER = "addin /u 1 /u 2 /d Gift /i 100";
    private static final String ADDIN_D_NO_DELIMITER = "addin /u 1 Gift /i 100";
    private static final String ADDIN_D_MANY_DELIMITER = "addin /u 1 /d Gift /d Something /i 100";
    private static final String ADDIN_I_NO_DELIMITER = "addin /u 1 /d Gift 100";
    private static final String ADDIN_I_MANY_DELIMITER = "addin /u 1 /d Gift /i 100 /i";

    private static final String ADDOUT_U_1_D_FOOD_E_10_50 = "addout /u 1 /d Food /e 10.50";
    private static final String ADDIN_E_NO_DELIMITER = "addout /u 1 /d Food 10.50";
    private static final String ADDIN_E_MANY_DELIMITER = "addout /u 1 /d Food /e 10.50 /e";

    private static final String DELETEIN_U_1_R_2 = "deletein /u 1 /r 2";
    private static final String DELETEIN_R_NO_DELIMITER = "deletein /u 1 2";
    private static final String DELETEIN_R_MANY_DELIMITER = "deletein /u 1 /r 2 /r 1";

    private static final String ADDOUT_P_SMALL_TRUE = "addout /u 1 /e 10 /d Dinner /p t";
    private static final String ADDOUT_P_CAPITAL_TRUE = "addout /u 1 /e 10 /d Dinner /p T";
    private static final String ADDOUT_P_EXPLICIT_FALSE = "addout /u 1 /e 10 /d Dinner /p F";
    private static final String ADDOUT_P_DEFAULT_FALSE = "addout /u 1 /e 10 /d Dinner /p abc123";
    private static final String ADDOUT_P_NO_DELIMITER = "addout /u 1 /e 10 /d Dinner t";
    private static final String ADDOUT_P_MANY_DELIMITER = "addout /u 1 /e 10 /d Dinner /p t /p f";

    private static final String ADDOUT_U_1_E_10_D_DINNER_P_T_C_2 = "addout /u 1 /e 10 /d Dinner /p t /c 2";
    private static final String ADDOUT_C_NO_DELIMITER = "addout /u 1 /e 10 /d Dinner /p t";
    private static final String ADDOUT_C_MANY_DELIMITER = "addout /u 1 /e 10 /d Dinner /p t /c 2 /c 6";

    private static final String ADD_N_ALICE_G_2 = "add /n Alice /g 2";
    private static final String ADD_N_MANY_DELIMITER = "add /n Alice /g 2 /g 3";

    private String getIndexError(String type, String index) {
        String indexError = String.format(ERROR_INDEX_MSG, type, index);
        return String.format(ERROR_MSG, indexError);
    }

    @Test
    void parseCommandType_keywordExist_success() {
        String output1 = Parser.parseCommandType(EMPTY_INPUT);
        assertEquals(EMPTY_INPUT, output1);

        String output2 = Parser.parseCommandType(ADD_N_ALICE);
        assertEquals(ADD, output2);
    }

    @Test
    void parseCommandType_keywordIsNull_assertThrown() {
        try {
            Parser.parseCommandType(null);
            fail();
        } catch (AssertionError e) {
            assertEquals(USER_INPUT_NOT_NULL, e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void parseName_delimiterExist_success()
            throws DuplicateDelimiterException, MissingDelimiterException, EmptyStringException {
        String output = Parser.parseName(ADD_N_ALICE);
        assertEquals(ALICE, output);
    }

    @Test
    void parseName_nullInput_assertThrown() {
        try {
            Parser.parseName(null);
            fail();
        } catch (AssertionError e) {
            assertEquals(USER_INPUT_NOT_NULL, e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void parseName_delimiterIssues_exceptionThrown() {
        try {
            Parser.parseName(ADD_NO_DELIMITER);
            fail();
        } catch (MissingDelimiterException e) {
            assertEquals(String.format(ERROR_MSG, MISSING_DELIMITER_N), e.toString());
        } catch (Exception e) {
            fail();
        }
        try {
            Parser.parseName(ADD_MANY_DELIMITER);
            fail();
        } catch (DuplicateDelimiterException e) {
            assertEquals(String.format(ERROR_MSG, TOO_MANY_DELIMITER_N), e.toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void parseUserIndex_delimiterExist_success()
            throws DuplicateDelimiterException, MissingDelimiterException, EmptyStringException {
        String output = Parser.parseUserIndex(ADDIN_U_1_D_GIFT_I_100);
        assertEquals(ONE, output);
    }

    @Test
    void parseUserIndex_nullInput_assertThrown() {
        try {
            Parser.parseUserIndex(null);
            fail();
        } catch (AssertionError e) {
            assertEquals(USER_INPUT_NOT_NULL, e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void parseUserIndex_delimiterIssues_exceptionThrown() {
        try {
            Parser.parseUserIndex(ADDIN_U_NO_DELIMITER);
            fail();
        } catch (MissingDelimiterException e) {
            assertEquals(String.format(ERROR_MSG, MISSING_DELIMITER_U), e.toString());
        } catch (Exception e) {
            fail();
        }
        try {
            Parser.parseUserIndex(ADDIN_U_MANY_DELIMITER);
            fail();
        } catch (DuplicateDelimiterException e) {
            assertEquals(String.format(ERROR_MSG, TOO_MANY_DELIMITER_U), e.toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void parseDescription_delimiterExist_success()
            throws DuplicateDelimiterException, MissingDelimiterException, EmptyStringException {
        String output = Parser.parseDescription(ADDIN_U_1_D_GIFT_I_100);
        assertEquals(GIFT, output);
    }

    @Test
    void parseDescription_nullInput_assertThrown() {
        try {
            Parser.parseDescription(null);
            fail();
        } catch (AssertionError e) {
            assertEquals(USER_INPUT_NOT_NULL, e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void parseDescription_delimiterIssues_exceptionThrown() {
        try {
            Parser.parseDescription(ADDIN_D_NO_DELIMITER);
            fail();
        } catch (MissingDelimiterException e) {
            assertEquals(String.format(ERROR_MSG, MISSING_DELIMITER_D), e.toString());
        } catch (Exception e) {
            fail();
        }
        try {
            Parser.parseDescription(ADDIN_D_MANY_DELIMITER);
            fail();
        } catch (DuplicateDelimiterException e) {
            assertEquals(String.format(ERROR_MSG, TOO_MANY_DELIMITER_D), e.toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void parseIncome_delimiterExist_success()
            throws DuplicateDelimiterException, MissingDelimiterException, EmptyStringException {
        String output = Parser.parseIncome(ADDIN_U_1_D_GIFT_I_100);
        assertEquals(INCOME_HUNDRED, output);
    }

    @Test
    void parseIncome_nullInput_assertThrown() {
        try {
            Parser.parseIncome(null);
            fail();
        } catch (AssertionError e) {
            assertEquals(USER_INPUT_NOT_NULL, e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void parseIncome_delimiterIssues_exceptionThrown() {
        try {
            Parser.parseIncome(ADDIN_I_NO_DELIMITER);
            fail();
        } catch (MissingDelimiterException e) {
            assertEquals(String.format(ERROR_MSG, MISSING_DELIMITER_I), e.toString());
        } catch (Exception e) {
            fail();
        }
        try {
            Parser.parseIncome(ADDIN_I_MANY_DELIMITER);
            fail();
        } catch (DuplicateDelimiterException e) {
            assertEquals(String.format(ERROR_MSG, TOO_MANY_DELIMITER_I), e.toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void parseExpenditure_delimiterExist_success()
            throws DuplicateDelimiterException, MissingDelimiterException, EmptyStringException {
        String output = Parser.parseExpenditure(ADDOUT_U_1_D_FOOD_E_10_50);
        assertEquals(TEN_FIFTY_STRING, output);
    }

    @Test
    void parseExpenditure_nullInput_assertThrown() {
        try {
            Parser.parseExpenditure(null);
            fail();
        } catch (AssertionError e) {
            assertEquals(USER_INPUT_NOT_NULL, e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void parseExpenditure_delimiterIssues_exceptionThrown() {
        try {
            Parser.parseExpenditure(ADDIN_E_NO_DELIMITER);
            fail();
        } catch (MissingDelimiterException e) {
            assertEquals(String.format(ERROR_MSG, MISSING_DELIMITER_E), e.toString());
        } catch (Exception e) {
            fail();
        }
        try {
            Parser.parseExpenditure(ADDIN_E_MANY_DELIMITER);
            fail();
        } catch (DuplicateDelimiterException e) {
            assertEquals(String.format(ERROR_MSG, TOO_MANY_DELIMITER_E), e.toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void parseRecordIndex_delimiterExist_success()
            throws DuplicateDelimiterException, MissingDelimiterException, EmptyStringException {
        String output = Parser.parseRecordIndex(DELETEIN_U_1_R_2);
        assertEquals(TWO, output);
    }

    @Test
    void parseRecordIndex_nullInput_assertThrown() {
        try {
            Parser.parseRecordIndex(null);
            fail();
        } catch (AssertionError e) {
            assertEquals(USER_INPUT_NOT_NULL, e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void parseRecordIndex_delimiterIssues_exceptionThrown() {
        try {
            Parser.parseRecordIndex(DELETEIN_R_NO_DELIMITER);
            fail();
        } catch (MissingDelimiterException e) {
            assertEquals(String.format(ERROR_MSG, MISSING_DELIMITER_R), e.toString());
        } catch (Exception e) {
            fail();
        }
        try {
            Parser.parseRecordIndex(DELETEIN_R_MANY_DELIMITER);
            fail();
        } catch (DuplicateDelimiterException e) {
            assertEquals(String.format(ERROR_MSG, TOO_MANY_DELIMITER_R), e.toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void parseRecurringStatus_delimiterExist_success()
            throws DuplicateDelimiterException, MissingDelimiterException, EmptyStringException {
        assertTrue(Parser.parseRecurringStatus(ADDOUT_P_SMALL_TRUE));
        assertTrue(Parser.parseRecurringStatus(ADDOUT_P_CAPITAL_TRUE));
        assertFalse(Parser.parseRecurringStatus(ADDOUT_P_EXPLICIT_FALSE));
        assertFalse(Parser.parseRecurringStatus(ADDOUT_P_DEFAULT_FALSE));
    }

    @Test
    void parseRecurringStatus_nullInput_assertThrown() {
        try {
            Parser.parseRecurringStatus(null);
            fail();
        } catch (AssertionError e) {
            assertEquals(USER_INPUT_NOT_NULL, e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void parseRecurringStatus_delimiterIssues_exceptionThrown() {
        try {
            Parser.parseRecurringStatus(ADDOUT_P_NO_DELIMITER);
            fail();
        } catch (MissingDelimiterException e) {
            assertEquals(String.format(ERROR_MSG, MISSING_DELIMITER_P), e.toString());
        } catch (Exception e) {
            fail();
        }
        try {
            Parser.parseRecurringStatus(ADDOUT_P_MANY_DELIMITER);
            fail();
        } catch (DuplicateDelimiterException e) {
            assertEquals(String.format(ERROR_MSG, TOO_MANY_DELIMITER_P), e.toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void parseCategoryIndex_delimiterExist_success()
            throws DuplicateDelimiterException, MissingDelimiterException, EmptyStringException {
        String output = Parser.parseCategoryIndex(ADDOUT_U_1_E_10_D_DINNER_P_T_C_2);
        assertEquals(TWO, output);
    }

    @Test
    void parseCategoryIndex_nullInput_assertThrown() {
        try {
            Parser.parseCategoryIndex(null);
            fail();
        } catch (AssertionError e) {
            assertEquals(USER_INPUT_NOT_NULL, e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void parseCategoryIndex_delimiterIssues_exceptionThrown() {
        try {
            Parser.parseCategoryIndex(ADDOUT_C_NO_DELIMITER);
            fail();
        } catch (MissingDelimiterException e) {
            assertEquals(String.format(ERROR_MSG, MISSING_DELIMITER_C), e.toString());
        } catch (Exception e) {
            fail();
        }
        try {
            Parser.parseCategoryIndex(ADDOUT_C_MANY_DELIMITER);
            fail();
        } catch (DuplicateDelimiterException e) {
            assertEquals(String.format(ERROR_MSG, TOO_MANY_DELIMITER_C), e.toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void parseGroupIndex_delimiterExist_success()
            throws DuplicateDelimiterException, MissingDelimiterException, EmptyStringException {
        String output = Parser.parseGroupIndex(ADD_N_ALICE_G_2);
        assertEquals(TWO, output);
    }

    @Test
    void parseGroupIndex_nullInput_assertThrown() {
        try {
            Parser.parseGroupIndex(null);
            fail();
        } catch (AssertionError e) {
            assertEquals(USER_INPUT_NOT_NULL, e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void parseGroupIndex_delimiterIssues_exceptionThrown() {
        try {
            Parser.parseGroupIndex(ADD_N_ALICE);
            fail();
        } catch (MissingDelimiterException e) {
            assertEquals(String.format(ERROR_MSG, MISSING_DELIMITER_G), e.toString());
        } catch (Exception e) {
            fail();
        }
        try {
            Parser.parseGroupIndex(ADD_N_MANY_DELIMITER);
            fail();
        } catch (DuplicateDelimiterException e) {
            assertEquals(String.format(ERROR_MSG, TOO_MANY_DELIMITER_G), e.toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getValidMoney_positiveDouble_success() throws InvalidMoneyException {
        Double output = Parser.getValidMoney(TEN_FIFTY_STRING);
        assertEquals(TEN_FIFTY_DOUBLE, output);
    }

    @Test
    void getValidMoney_nullInput_assertThrown() {
        try {
            Parser.getValidMoney(null);
            fail();
        } catch (AssertionError e) {
            assertEquals(MONEY_INPUT_SHOULD_NOT_BE_NULL, e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getValidMoney_negativeDouble_exceptionThrown() {
        try {
            Parser.getValidMoney(TEN_FIFTY_NEGATIVE);
            fail();
        } catch (InvalidMoneyException e) {
            assertEquals(String.format(ERROR_MSG, INVALID_MONEY_NEGATIVE_VALUE),
                    e.toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getValidMoney_notNumber_exceptionThrown() {
        try {
            Parser.getValidMoney(HUNDRED);
            fail();
        } catch (InvalidMoneyException e) {
            assertEquals(String.format(ERROR_MSG, INVALID_MONEY_NOT_DOUBLE),
                    e.toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getValidUserIndex_validUserIndex_success() throws InvalidIndexException {
        PersonList personList = new PersonList();
        personList.addPerson(ALICE);

        int output = Parser.getValidUserIndex(ONE, personList.getNumberOfMembers());
        assertEquals(INT_ONE, output);
    }

    @Test
    void getValidUserIndex_nullInput_assertThrown() {
        try {
            PersonList personList = new PersonList();
            Parser.getValidUserIndex(null, personList.getNumberOfMembers());
            fail();
        } catch (AssertionError e) {
            assertEquals(USER_INDEX_SHOULD_NOT_BE_NULL, e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getValidUserIndex_notNumber_exceptionThrown() {
        try {
            PersonList personList = new PersonList();
            Parser.getValidUserIndex(ALICE, personList.getNumberOfMembers());
            fail();
        } catch (InvalidIndexException e) {
            assertEquals(getIndexError(TYPE_USER_INDEX, ALICE), e.toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getValidUserIndex_indexOutOfRange_exceptionThrown() {
        PersonList personList = new PersonList();
        personList.addPerson(ALICE);
        try {
            Parser.getValidUserIndex(TOO_LOW_INDEX, personList.getNumberOfMembers());
            fail();
        } catch (InvalidIndexException e) {
            assertEquals(getIndexError(TYPE_USER_INDEX, TOO_LOW_INDEX), e.toString());
        } catch (Exception e) {
            fail();
        }
        try {
            Parser.getValidUserIndex(TOO_HIGH_INDEX, personList.getNumberOfMembers());
            fail();
        } catch (InvalidIndexException e) {
            assertEquals(getIndexError(TYPE_USER_INDEX, TOO_HIGH_INDEX), e.toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getValidExpenditureIndex_validExpenditureIndex_success() throws InvalidIndexException {
        PersonList personList = new PersonList();
        personList.addPerson(ALICE);
        Person person = personList.getPerson(INT_ONE);
        person.addExpend(FOOD, TEN_FIFTY_DOUBLE, INT_ONE, false, false);

        int output = Parser.getValidExpenditureIndex(ONE, person.getNumberOfExpenditures());
        assertEquals(INT_ONE, output);
    }

    @Test
    void getValidExpenditureIndex_nullInput_assertThrown() {
        try {
            PersonList personList = new PersonList();
            personList.addPerson(ALICE);
            Person person = personList.getPerson(INT_ONE);
            Parser.getValidExpenditureIndex(null, person.getNumberOfExpenditures());
            fail();
        } catch (AssertionError e) {
            assertEquals(EXPENDITURE_INDEX_SHOULD_NOT_BE_NULL, e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getValidExpenditureIndex_notNumber_exceptionThrown() {
        try {
            PersonList personList = new PersonList();
            personList.addPerson(ALICE);
            Person person = personList.getPerson(INT_ONE);

            Parser.getValidExpenditureIndex(ALICE, person.getNumberOfExpenditures());
            fail();
        } catch (InvalidIndexException e) {
            assertEquals(getIndexError(TYPE_EXPENDITURE_INDEX, ALICE), e.toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getValidExpenditureIndex_indexOutOfRange_exceptionThrown() {
        PersonList personList = new PersonList();
        personList.addPerson(ALICE);
        Person person = personList.getPerson(INT_ONE);
        person.addExpend(FOOD, TEN_FIFTY_DOUBLE, INT_ONE, false, false);
        try {
            Parser.getValidExpenditureIndex(TOO_LOW_INDEX, person.getNumberOfExpenditures());

        } catch (InvalidIndexException e) {
            assertEquals(getIndexError(TYPE_EXPENDITURE_INDEX, TOO_LOW_INDEX), e.toString());
        } catch (Exception e) {
            fail();
        }
        try {
            Parser.getValidExpenditureIndex(TOO_HIGH_INDEX, person.getNumberOfExpenditures());

        } catch (InvalidIndexException e) {
            assertEquals(getIndexError(TYPE_EXPENDITURE_INDEX, TOO_HIGH_INDEX), e.toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getValidIncomeIndex_validIncomeIndex_success() throws InvalidIndexException {
        PersonList personList = new PersonList();
        personList.addPerson(ALICE);
        Person person = personList.getPerson(INT_ONE);
        person.addIncome(GIFT, TEN_FIFTY_DOUBLE, false, false);

        int output = Parser.getValidIncomeIndex(ONE, person.getNumberOfIncomes());
        assertEquals(INT_ONE, output);
    }

    @Test
    void getValidIncomeIndex_nullInput_assertThrown() {
        try {
            PersonList personList = new PersonList();
            personList.addPerson(ALICE);
            Person person = personList.getPerson(INT_ONE);
            Parser.getValidIncomeIndex(null, person.getNumberOfIncomes());
            fail();
        } catch (AssertionError e) {
            assertEquals(INCOME_INDEX_SHOULD_NOT_BE_NULL, e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getValidIncomeIndex_notNumber_exceptionThrown() {
        try {
            PersonList personList = new PersonList();
            personList.addPerson(ALICE);
            Person person = personList.getPerson(INT_ONE);

            Parser.getValidIncomeIndex(ALICE, person.getNumberOfIncomes());
            fail();
        } catch (InvalidIndexException e) {
            assertEquals(getIndexError(TYPE_INCOME_INDEX, ALICE), e.toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getValidIncomeIndex_indexOutOfRange_exceptionThrown() {
        PersonList personList = new PersonList();
        personList.addPerson(ALICE);
        Person person = personList.getPerson(INT_ONE);
        person.addIncome(GIFT, TEN_FIFTY_DOUBLE, false, false);
        try {
            Parser.getValidIncomeIndex(TOO_LOW_INDEX, person.getNumberOfIncomes());
            fail();
        } catch (InvalidIndexException e) {
            assertEquals(getIndexError(TYPE_INCOME_INDEX, TOO_LOW_INDEX), e.toString());
        } catch (Exception e) {
            fail();
        }
        try {
            Parser.getValidIncomeIndex(TOO_HIGH_INDEX, person.getNumberOfIncomes());
            fail();
        } catch (InvalidIndexException e) {
            assertEquals(getIndexError(TYPE_INCOME_INDEX, TOO_HIGH_INDEX), e.toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getValidCategoryIndex_validCategoryIndex_success() throws InvalidIndexException {
        int output = Parser.getValidCategoryIndex(ONE);
        assertEquals(INT_ONE, output);
    }

    @Test
    void getValidCategoryIndex_nullInput_assertThrown() {
        try {
            Parser.getValidCategoryIndex(null);
            fail();
        } catch (AssertionError e) {
            assertEquals(CATEGORY_INDEX_SHOULD_NOT_BE_NULL, e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getValidCategoryIndex_notNumber_exceptionThrown() {
        try {
            Parser.getValidCategoryIndex(ALICE);
            fail();
        } catch (InvalidIndexException e) {
            assertEquals(getIndexError(TYPE_CATEGORY_INDEX, ALICE), e.toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getValidCategoryIndex_indexOutOfRange_exceptionThrown() {
        try {
            Parser.getValidCategoryIndex(TOO_LOW_INDEX);
            fail();
        } catch (InvalidIndexException e) {
            assertEquals(getIndexError(TYPE_CATEGORY_INDEX, TOO_LOW_INDEX), e.toString());
        } catch (Exception e) {
            fail();
        }
        try {
            Parser.getValidCategoryIndex(TOO_HIGH_INDEX);
            fail();
        } catch (InvalidIndexException e) {
            assertEquals(getIndexError(TYPE_CATEGORY_INDEX, TOO_HIGH_INDEX), e.toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getValidGroupIndex_validGroupIndex_success() throws InvalidIndexException {
        int output = Parser.getValidGroupIndex(ONE);
        assertEquals(INT_ONE, output);
    }

    @Test
    void getValidGroupIndex_nullInput_assertThrown() {
        try {
            Parser.getValidGroupIndex(null);
            fail();
        } catch (AssertionError e) {
            assertEquals(GROUP_INDEX_SHOULD_NOT_BE_NULL, e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getValidGroupIndex_notNumber_exceptionThrown() {
        try {
            Parser.getValidGroupIndex(ALICE);
            fail();
        } catch (InvalidIndexException e) {
            assertEquals(getIndexError(TYPE_GROUP_INDEX, ALICE), e.toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getValidGroupIndex_indexOutOfRange_exceptionThrown() {
        try {
            Parser.getValidGroupIndex(TOO_LOW_INDEX);
            fail();
        } catch (InvalidIndexException e) {
            assertEquals(getIndexError(TYPE_GROUP_INDEX, TOO_LOW_INDEX), e.toString());
        } catch (Exception e) {
            fail();
        }
        try {
            Parser.getValidGroupIndex(TOO_HIGH_INDEX);
            fail();
        } catch (InvalidIndexException e) {
            assertEquals(getIndexError(TYPE_GROUP_INDEX, TOO_HIGH_INDEX), e.toString());
        } catch (Exception e) {
            fail();
        }
    }
}
