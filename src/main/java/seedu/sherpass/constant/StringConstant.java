package seedu.sherpass.constant;

public class StringConstant {
    public static final String DO_ON_KEYWORD = "/do_on";
    public static final String BY_KEYWORD = "/by";
    public static final String SINGLE_SPACE = " ";
    public static final String CLOSED_APOSTROPHE = "'";
    public static final String REGEX_TO_SPLIT_STRING_USING_BY_OR_DO_ON_DATES =
            "/by \\d{1,3}/\\d{1,3}/\\d{4}|/do_on \\d{1,3}/\\d{1,3}/\\d{4}";
}
