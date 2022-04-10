package seedu.simplst.parsers;

import seedu.simplst.Warehouse;
import util.exceptions.InvalidFileException;
import util.exceptions.InvalidObjectType;
import util.exceptions.NullException;
import util.exceptions.WrongCommandException;

import java.util.HashMap;

public abstract class CommandParser {
    HashMap<String, String> matches;
    String userInput;
    Warehouse warehouse;

    public CommandParser(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    protected abstract void initExtractParams();

    protected abstract void extractParams() throws
            WrongCommandException, NullException, InvalidFileException, InvalidObjectType;
    //    protected abstract void validate_params();   // They need implement their own validate commands

    public void parse(String userInput) throws
            WrongCommandException, NullException, InvalidFileException, InvalidObjectType {
        this.userInput = userInput;
        this.initExtractParams();
        try {
            this.extractParams();
        } catch (InvalidObjectType e1) {
            return;
        }
    }
}
