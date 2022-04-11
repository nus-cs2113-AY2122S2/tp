package seedu.duke.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands.
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_QUANTITY = new Prefix("q/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_START_DATE = new Prefix("s/");
    public static final Prefix PREFIX_END_DATE = new Prefix("e/");
    public static final Prefix PREFIX_BORROWER_NAME = new Prefix("p/");
    public static final Prefix PREFIX_ITEM_INDEX = new Prefix("i/");
}
