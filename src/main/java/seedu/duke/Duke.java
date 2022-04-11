package seedu.duke;

import java.io.IOException;
import java.util.logging.LogManager;

import seedu.duke.command.Command;
import seedu.duke.command.customercommands.AddSatisfactionCommand;
import seedu.duke.command.housekeepercommands.AddAvailabilityCommand;
import seedu.duke.command.housekeepercommands.AddHousekeeperCommand;
import seedu.duke.command.housekeepercommands.AgeIncreaseCommand;
import seedu.duke.command.housekeepercommands.ResetAvailabilityCommand;
import seedu.duke.command.housekeepercommands.DeleteHousekeeperCommand;
import seedu.duke.command.housekeepercommands.AddHousekeeperPerformanceCommand;
import seedu.duke.command.itemcommands.AddItemCommand;
import seedu.duke.command.itemcommands.DeleteItemCommand;
import seedu.duke.command.itemcommands.UpdateItemNameCommand;
import seedu.duke.command.itemcommands.UpdateItemPaxCommand;
import seedu.duke.command.roomcommand.CheckAllRoomCommand;
import seedu.duke.command.roomcommand.CheckInCommand;
import seedu.duke.command.roomcommand.CheckOutCommand;
import seedu.duke.command.roomcommand.CheckRoomByCatCommand;
import seedu.duke.command.roomcommand.CheckRoomByLevelCommand;
import seedu.duke.command.roomcommand.CheckRoomCommand;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.roomlists.RoomList;
import seedu.duke.storage.RoomFileManager;

public class Duke {
    /**
     * Updates the list saved within the respective files based on the type of command the user has inputted.
     *
     * @param command       The command object that was constructed by the command parser based on the command the user
     *                      inputted.
     * @param listContainer The object containing the lists to update depending on the command inputted by the user.
     * @throws IOException if we are unable to write to the respective file found within the ListFolder directory.
     */

    private void writeListsToFile(Command command, ListContainer listContainer)
            throws IOException, HotelLiteManagerException {
        if (command instanceof AddItemCommand || command instanceof UpdateItemPaxCommand
                || command instanceof UpdateItemNameCommand || command instanceof UpdateItemNameCommand
                || command instanceof DeleteItemCommand) {
            writeItemListsToFile(command, listContainer);
        } else if (command instanceof AddAvailabilityCommand || command instanceof AddHousekeeperCommand || command
                instanceof AgeIncreaseCommand || command instanceof ResetAvailabilityCommand || command
                instanceof DeleteHousekeeperCommand || command instanceof AddHousekeeperPerformanceCommand) {
            writeHousekeeperListsToFile(command, listContainer);
        } else if (command instanceof CheckAllRoomCommand || command instanceof CheckInCommand || command
                instanceof CheckOutCommand || command instanceof CheckRoomByCatCommand || command
                instanceof CheckRoomByLevelCommand || command instanceof CheckRoomCommand) {
            writeRoomListToFile(listContainer);
        } else if (command instanceof AddSatisfactionCommand) {
            writeSatisfactionListsToFile(command, listContainer);
        }
    }

    /**
     * Updates the list saved within the respective files based on the type of housekeeper related
     * commands given by the user.
     *
     * @param command       The command object that was constructed by the command parser based on the command the user
     *                      inputted.
     * @param listContainer The object containing the lists to update depending on the command inputted by the user.
     * @throws IOException if we are unable to write to the respective file found within the ListFolder directory.
     */
    private void writeHousekeeperListsToFile(Command command, ListContainer listContainer) throws IOException {
        if (command instanceof AddHousekeeperCommand) {
            AddHousekeeperCommand addHousekeeperCommand = (AddHousekeeperCommand) command;
            addHousekeeperCommand.writeHousekeeperToFile(listContainer);
        } else if (command instanceof AddAvailabilityCommand) {
            AddAvailabilityCommand addAvailabilityCommand = (AddAvailabilityCommand) command;
            addAvailabilityCommand.writeAvailabilityToFile(listContainer);
        } else if (command instanceof AgeIncreaseCommand) {
            AgeIncreaseCommand ageIncreaseCommand = (AgeIncreaseCommand) command;
            ageIncreaseCommand.writeAgeIncreaseToFile(listContainer);
        } else if (command instanceof DeleteHousekeeperCommand) {
            DeleteHousekeeperCommand deleteHousekeeperCommand = (DeleteHousekeeperCommand) command;
            deleteHousekeeperCommand.writeHousekeeperToFile(listContainer);
        } else if (command instanceof ResetAvailabilityCommand) {
            ResetAvailabilityCommand resetAvailabilityCommand = (ResetAvailabilityCommand) command;
            resetAvailabilityCommand.writeHousekeeperToFile(listContainer);
        } else if (command instanceof AddHousekeeperPerformanceCommand) {
            AddHousekeeperPerformanceCommand addHousekeeperPerformanceCommand
                    = (AddHousekeeperPerformanceCommand) command;
            addHousekeeperPerformanceCommand.writeHousekeeperPerformanceListToFile(listContainer);
        }
    }


    private void writeRoomListToFile(ListContainer listContainer) throws IOException, HotelLiteManagerException {
        RoomList roomList = listContainer.getRoomList();
        RoomFileManager fileManager = new RoomFileManager();
        fileManager.save(roomList.getRoomList());
    }


    /**
     * Updates the item list saved within the file ListFolder/ItemList.txt with the current item list.
     *
     * @param command       The command object that was constructed by the command parser based on the command the user
     *                      inputted.
     * @param listContainer The object containing the data structure necessary to update the ItemList stored within
     *                      the ListFolder/ItemList.txt file.
     *                      In this case, we require access to the ItemList object which is within listContainer.
     * @throws IOException if we are unable to write to the file ListFolder/ItemList.txt
     */
    private void writeItemListsToFile(Command command, ListContainer listContainer) throws IOException {
        if (command instanceof AddItemCommand) {
            AddItemCommand addItemCommand = (AddItemCommand) command;
            addItemCommand.writeItemListToFile(listContainer);
        } else if (command instanceof UpdateItemPaxCommand) {
            UpdateItemPaxCommand updateItemPaxCommand = (UpdateItemPaxCommand) command;
            updateItemPaxCommand.writeItemListToFile(listContainer);
        } else if (command instanceof UpdateItemNameCommand) {
            UpdateItemNameCommand updateItemNameCommand = (UpdateItemNameCommand) command;
            updateItemNameCommand.writeItemListToFile(listContainer);
        } else if (command instanceof DeleteItemCommand) {
            DeleteItemCommand deleteItemNameCommand = (DeleteItemCommand) command;
            deleteItemNameCommand.writeItemListToFile(listContainer);
        }
    }

    private void writeSatisfactionListsToFile(Command command, ListContainer listContainer) throws IOException {
        if (command instanceof AddSatisfactionCommand) {
            AddSatisfactionCommand addSatisfactionCommand = (AddSatisfactionCommand) command;
            addSatisfactionCommand.writeSatisfactionListToFile(listContainer);
        }
    }

    /**
     * The primary method that the duke program executes.
     */
    private void run() {
        Ui ui = new Ui();
        ui.printGreeting();
        CommandParser commandParser = new CommandParser();
        ListContainer listContainer = null;
        try {
            listContainer = new ListContainer();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HotelLiteManagerException e) {
            ui.printErrorMessage(e);
        }

        boolean shouldExitProgram = false;
        String userInput;
        while (!shouldExitProgram) {
            Command command = null;
            try {
                userInput = ui.readUserInput();
                command = commandParser.parse(userInput);
                command.execute(listContainer, ui);
                writeListsToFile(command, listContainer);
                shouldExitProgram = command.isExit();
            } catch (HotelLiteManagerException e) {
                ui.printErrorMessage(e);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        LogManager.getLogManager().reset();
        new Duke().run();
    }
}
