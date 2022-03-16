package seedu.duke.parser;

import seedu.duke.data.Item;
import seedu.duke.commands.Command;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.HiCommand;
import seedu.duke.commands.AddCommand;
import seedu.duke.commands.DeleteCommand;

public class Parser {
    public static Command parse(String userCommand) {
        if (userCommand.startsWith("exit")) {
            return new ExitCommand();

        } else if (userCommand.startsWith("add")) {
            Item item = createItem(userCommand);
            return new AddCommand(item);

        } else if (userCommand.startsWith("delete")) {
            String deleteIndex = userCommand.split(" ")[1];
            return new DeleteCommand(Integer.parseInt(deleteIndex));

        }/* else if (userCommand.startsWith("list")) {
            return new ListCommand();

        } else if (userCommand.startsWith("desc")) {
            int descIndex = userCommand.split(" ")[1];
            return new DescCommand(descIndex);
            
        } else if (userCommand.startsWith("help")) {
            return new HelpCommand();

        }*/ else {
            return new HiCommand();
        }
    }

    private static String getName(String[] itemString) {
        String nameString = itemString[1];
        int nameStringLength = nameString.length();
        return nameString.substring(0, nameStringLength - 2);
    }

    private static int getQuantity(String[] itemString) {
        String quantityString = itemString[2];
        int quantityStringLength = quantityString.length();
        return Integer.parseInt(quantityString.substring(0, quantityStringLength - 2));
    }

    private static String getDesc(String[] itemString) {
        String descString = itemString[3];
        int descStringLength = descString.length();
        return descString.substring(0, descStringLength - 2);
    }

    private static Item createItem(String userCommand) {
        String[] itemString = userCommand.split("/");
        String name = getName(itemString);
        int quantity = getQuantity(itemString);
        String desc = getDesc(itemString);
        return new Item(name, quantity, desc);
    }
}
