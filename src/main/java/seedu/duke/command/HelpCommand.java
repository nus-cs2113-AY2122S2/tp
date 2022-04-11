package seedu.duke.command;

import seedu.duke.ListContainer;
import seedu.duke.Ui;
import seedu.duke.exceptions.HotelLiteManagerException;

import java.io.IOException;

public class HelpCommand extends Command {
    private static final String line = "------------------------------------------\n";
    private static final String HELP_MESSAGE = "I am here to help! \n"
            + "Given below are the formats commands related to customer satisfaction: \n"
            + "\t 1. add satisfaction CUSTOMER_NAME / SATISFACTION_RATING \n"
            + "\t 2. view satisfactions \n"
            + "\t 3. view average satisfaction \n"
            + line
            + "\t For commands related with housekeepers, use following formats: \n"
            + "\t 1. add housekeeper NAME / AGE \n"
            + "\t 2. availability NAME / DAY(S) \n"
            + "\t 3. view recorded housekeepers \n"
            + "\t 4. delete housekeeper NAME \n"
            + "\t 5. get available on DAY \n"
            + "\t 6. is a new week \n"
            + "\t 7. is a new year \n"
            + "\t 8. assign NAME / ROOM_NUMBER \n"
            + "\t 9. add performance HOUSEKEEPER_NAME / PERFORMANCE_RATING \n"
            + "\t 10. view performances \n"
            + line
            + "For commands related with rooms, use following formats: \n"
            + "\t 1. check in ROOM_NUMBER \n"
            + "\t 2. check out ROOM_NUMBER \n"
            + "\t 3. check room ROOM_NUMBER \n"
            + "\t 4. check all room \n"
            + "\t 5. check level LEVEL_NUMBER \n"
            + "\t 6. check category CATEGORY \n"
            + line
            + "For commands related with inventory, use following formats:  \n"
            + "\t 1. add item ITEM NAME / PAX \n"
            + "\t 2. update item pax ITEM NAME / PAX \n"
            + "\t 3. update item name OLD ITEM NAME / NEW ITEM NAME \n"
            + "\t 4. delete item NAME \n"
            + "\t 5. view all items \n"
            + "\t 6. view items with zero pax \n"
            + "\t 7. search item KEYWORD \n"
            + line
            + "For commands related with events happening in the hotel, use following formats: \n"
            + "\t 1. add event DESCRIPTION / DATE \n"
            + "\t 2. delete event INDEX \n"
            + "\t 3. view events";

    public HelpCommand(String commandStringWithoutCommand) throws HotelLiteManagerException {

    }


    /**
     * Print the list of commands available to user.
     *
     * @param ui The user interface for this execution method.
     * @return
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui)
            throws HotelLiteManagerException, IOException {
        ui.printHelp(HELP_MESSAGE);
    }
}
