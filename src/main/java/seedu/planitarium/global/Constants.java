package seedu.planitarium.global;

public class Constants {
    // For Person component
    public static final int PARENTS = 1;
    public static final int MY_GEN = 2;
    public static final int NUM_GROUPS = 3;
    public static final int SINGULAR = 1;
    public static final boolean FOR_USER = false;
    public static final boolean FOR_STORAGE = true;
    public static final String EMPTY_STRING = "";
    public static final String INDENTATION = "   ";
    public static final String FAMILY_INIT_MESSAGE = "New Family initialised";
    public static final String ADD_PERSON_CALL_MESSAGE = "Method addPerson() called";
    public static final String DELETE_PERSON_CALL_MESSAGE = "Method deletePerson() called";
    public static final String ADD_INCOME_CALL_MESSAGE = "Method addIncome() called";
    public static final String DELETE_INCOME_CALL_MESSAGE = "Method deleteIncome() called";
    public static final String EDIT_INCOME_CALL_MESSAGE = "Method editIncome() called";
    public static final String ADD_EXPEND_CALL_MESSAGE = "Method addExpend() called";
    public static final String DELETE_EXPEND_CALL_MESSAGE = "Method deleteExpend() called";
    public static final String EDIT_EXPEND_CALL_MESSAGE = "Method editExpend() called";
    public static final String OVERVIEW_CALL_MESSAGE = "Method overview() called";
    public static final String LIST_CALL_MESSAGE = "Method list() called";
    public static final String NUM_MEMBERS_CALL_MESSAGE = "Method getNumberOfMembers() called";
    public static final String NUM_INCOMES_CALL_MESSAGE = "Method getNumberOfIncomes() called";
    public static final String NUM_EXPENDS_CALL_MESSAGE = "Method getNumberOfExpenditures() called";
    public static final String FIND_CALL_MESSAGE = "Method find() called";
    public static final String PERSON_LIST_INIT_MESSAGE = "New PersonList initialised";
    public static final String GET_PERSON_LIST_CALL_MESSAGE = "Method getPersonList() called";
    public static final String GET_PERSON_CALL_MESSAGE = "Method getPerson() called";
    public static final String GET_REMAIN_CALL_MESSAGE = "Method getRemain() called";
    public static final String GET_TOTAL_INCOME_CALL_MESSAGE = "Method getTotalIncome() called";
    public static final String GET_TOTAL_EXPEND_CALL_MESSAGE = "Method getTotalExpend() called";
    public static final String PERSON_INIT_MESSAGE = "New Person initialised";
    public static final String GET_NAME_CALL_MESSAGE = "Method getName() called";
    public static final String GET_INCOME_LIST_CALL_MESSAGE = "Method getIncomeList() called";
    public static final String GET_EXPEND_LIST_CALL_MESSAGE = "Method getExpenditureList() called";
    public static final String LIST_EXPEND_CALL_MESSAGE = "Method listExpenditure() called";
    public static final String LIST_INCOME_CALL_MESSAGE = "Method listIncome() called";
    public static final String GET_DISPOSABLE_CALL_MESSAGE = "Method getDisposable() called";
    public static final String SAVE_NAME_CALL_MESSAGE = "Method saveName() called";

    // For Parser component
    public static final int INDEX_KEYWORD = 0;
    public static final int INDEX_LEFT_NOT_EXIST = 0;
    public static final int INDEX_LEFT_REMOVED = 1;
    public static final int INDEX_RIGHT_REMOVED = 0;
    public static final int LIMIT_TWO_TOKENS = 2;
    public static final int MIN_USER_INDEX = 1;
    public static final int MIN_EXPENDITURE_INDEX = 1;
    public static final int MIN_INCOME_INDEX = 1;
    public static final int MIN_CATEGORY_INDEX = 1;
    public static final int MIN_GROUP_INDEX = 1;
    public static final int LIMIT_TWO_DECIMAL = 3;
    public static final double MONEY_ZERO = 0.0;

    // For Help component
    public static final String HELP = "Show all commands: help";
    public static final String ADD_PERSON = "Add a person: add /n NAME /g GROUP_INDEX";
    public static final String DELETE_PERSON = "Delete a person: delete /u USER_INDEX /g GROUP_INDEX";
    public static final String ADD_INCOME = "Add an income addin /u USER_INDEX /g GROUP_INDEX "
            + "/i INCOME /d DESCRIPTION /c CATEGORY_INDEX /p <T|(any)>";
    public static final String DELETE_INCOME = "Delete an income: deletein "
            + "/u USER_INDEX /g GROUP_INDEX /r INCOME_INDEX";
    public static final String EDIT_INCOME = "Edit an income: editin /u USER_INDEX /g GROUP_INDEX "
            + "/r INCOME_INDEX /i INCOME /d DESCRIPTION /c CATEGORY_INDEX /p <T|(any)>";
    public static final String ADD_EXPEND = "Add an expenditure: addout /u USER_INDEX /g GROUP_INDEX "
            + "/e EXPENDITURE /d DESCRIPTION /c CATEGORY_INDEX /p <T|(any)>";
    public static final String DELETE_EXPEND = "Delete an expenditure: deleteout /u USER_INDEX "
            + "/g GROUP_INDEX /r EXPENDITURE_INDEX";
    public static final String EDIT_EXPEND = "Edit an expenditure: editout /u USER_INDEX /g GROUP_INDEX "
            + "/r EXPENDITURE_INDEX /e EXPENDITURE /d DESCRIPTION /c CATEGORY_INDEX /p <T|(any)>";
    public static final String LIST = "List records for person in group: list /g GROUP_INDEX";
    public static final String OVERVIEW = "Show financial summary for each group: overview";
    public static final String LISTCAT = "List categories: listcat";
    public static final String FIND = "Searching for details: find /d USER_STRING [/c CATEGORY_INDEX]";
    public static final String EXIT = "Exit program: bye";
    public static final String PARAMETER_NAME = "/n NAME refers to your name";
    public static final String PARAMETER_GID = "/g GROUP_INDEX refers to a group index found in 'overview'";
    public static final String PARAMETER_UID = "/u USER_INDEX refer a user index found in 'list'";
    public static final String PARAMETER_DESC = "/d DESCRIPTION refers to a description string input";
    public static final String PARAMETER_CID = "/c CATEGORY_INDEX refers to a category index found in 'listcat'";
    public static final String PARAMETER_INCOME = "/i INCOME refers to an income monetary value";
    public static final String PARAMETER_EXPENDITURE = "/e EXPENDITURE refers to an expenditure monetary value";
    public static final String PARAMETER_RECORDS = "/r X_INDEX refers to an index for income/expenditure found in"
            + "'list'";
    public static final String PARAMETER_RECURRING = "/p <T/(any)> refers to 'T' for recurring, or any input otherwise";

    // For Command assertion
    public static final String NAME_NOT_NULL = "Name should not be null";
    public static final String GROUP_NOT_NULL = "Group should not be null";
    public static final String DESCRIPTION_NOT_NULL = "Description should not be empty";
    public static final String INPUT_NOT_NULL = "Input should not be empty";
    public static final String KEYWORD_NOT_NULL = "Keywords should not be empty";
    public static final String FAMILY_NOT_NULL = "Family should not be null";
    public static final String USER_INDEX_NOT_VALID = "User index should be valid";

    // For Command logging
    public static final String LOG_ERROR_INFO = "Unknown error occurred in execution";

    public static final String ADDPERSONCMDTYPE = "AddPersonCMD";
    public static final String DELETEPERSONCMDTYPE = "DeletePersonCMD";
    public static final String ADDRECORDCMDTYPE = "AddRecordCMD";
    public static final String DELETERECORDCMDTYPE = "DeleteRecordCMD";
    public static final String EDITRECORDCMDTYPE = "EditRecordCMD";
    public static final String EXITCMDTYPE = "ExitCMD";
    public static final String HELPCMDTYPE = "HelpCMD";
    public static final String LISTCATCMDTYPE = "ListcatCMD";
    public static final String LISTCMDTYPE = "ListCMD";
    public static final String OVERVIEWCMDTYPE = "OverviewCMD";
    public static final String SEARCHCMDTYPE = "SearchCMD";
    
    // For general string or loop manipulation
    public static final int ZERO = 0;
}
