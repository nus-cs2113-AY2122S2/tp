package constants;

public class ParserConstants {
    public static String PARAMETER_DELIMITER_ITEM_NAME = "i/";
    public static String PARAMETER_DELIMITER_PRICE = "p/";
    public static String PARAMETER_DELIMITER_DATE = "d/";
    public static String PARAMETER_DELIMITER_PRODUCT_TYPE = "t/";
    public static String PARAMETER_DELIMITER_RENEWAL = "r/";

    public static String PARAMETER_PATTERN_ITEM_NAME = "i/.*?(?=( [ipdtr]/)|$)";
    public static String PARAMETER_PATTERN_PRICE = "p/.*?(?=( [ipdtr]/)|$)";
    public static String PARAMETER_PATTERN_DATE = "d/.*?(?=( [ipdtr]/)|$)";
    public static String PARAMETER_PATTERN_PRODUCT_TYPE = "t/.*?(?=( [ipdtr]/)|$)";
    public static String PARAMETER_PATTERN_RENEWAL = "r/.*?(?=( [ipdtr]/)|$)";

    public static String STRING_PATTERN_PRICE = "[^\\d.]";
}
