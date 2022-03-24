package constants;

/** Constant values used in Parser class. */
public class ParserConstants {
    /** Delimiter denoting item name argument in user command. */
    public static final String PARAMETER_DELIMITER_ITEM_NAME = "i/";

    /** Delimiter denoting price argument in user command. */
    public static final String PARAMETER_DELIMITER_PRICE = "p/";

    /** Delimiter denoting date argument in user command. */
    public static final String PARAMETER_DELIMITER_DATE = "d/";

    /** Delimiter denoting product type argument in user command. */
    public static final String PARAMETER_DELIMITER_PRODUCT_TYPE = "t/";

    /** Delimiter denoting renewal argument in user command. */
    public static final String PARAMETER_DELIMITER_RENEWAL = "r/";

    /** Regex pattern of item name argument given in user command. */
    public static final String PARAMETER_PATTERN_ITEM_NAME = "i/.*?(?=( [ipdtr]/)|$)";

    /** Regex pattern of price argument given in user command. */
    public static final String PARAMETER_PATTERN_PRICE = "p/.*?(?=( [ipdtr]/)|$)";

    /** Regex pattern of date argument given in user command. */
    public static final String PARAMETER_PATTERN_DATE = "d/.*?(?=( [ipdtr]/)|$)";

    /** Regex pattern of product type argument given in user command. */
    public static final String PARAMETER_PATTERN_PRODUCT_TYPE = "t/.*?(?=( [ipdtr]/)|$)";

    /** Regex pattern of renewal argument given in user command. */
    public static final String PARAMETER_PATTERN_RENEWAL = "r/.*?(?=( [ipdtr]/)|$)";

    /** Regex pattern of prices given in user command. */
    public static final String STRING_PATTERN_PRICE = "[^\\d.]";
}
