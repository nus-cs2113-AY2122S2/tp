package seedu.simplst.parsers;

import seedu.simplst.Warehouse;
import util.exceptions.*;

import java.util.HashMap;

public abstract class CommandParser {
    HashMap<String, String> matches;
    String userInput;
    Warehouse warehouse;

    public CommandParser(Warehouse warehouse) {
        this.warehouse = warehouse;
    }


    protected abstract void init_extract_params() throws WrongCommandException, MissingFlagException, EmptyFieldException;

    protected abstract void extract_params() throws
            WrongCommandException, NullException, InvalidFileException, InvalidObjectType, MissingFlagException, EmptyFieldException;
    //    protected abstract void validate_params();   // They need implement their own validate commands

    public void parse(String userInput) throws
            WrongCommandException, NullException, InvalidFileException, InvalidObjectType, MissingFlagException, EmptyFieldException {
        this.userInput = userInput;
        this.init_extract_params();
        try {
            this.extract_params();
        } catch (InvalidObjectType e1) {
            return;
        }
    }
}
