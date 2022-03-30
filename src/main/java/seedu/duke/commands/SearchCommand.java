package seedu.duke.commands;

import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchCommand extends Command {
    public static final String COMMAND_WORD = "search";
    public static final String COMMAND_NAME = "Search Item";
    public static final String USAGE_MESSAGE = "Search for the name and/or description of an item.";
    public static final String COMMAND_FORMAT = COMMAND_WORD + "/n name [/d description] | [/n name] /d description]";
    public static final String HELP_MESSAGE = COMMAND_NAME + ":\n" + "[Function] " + USAGE_MESSAGE + ":\n"
            + "[Command Format] " + COMMAND_FORMAT + "\n";

    private final Optional<String> name;
    private final Optional<String> description;

    public SearchCommand(Optional<String> name, Optional<String> description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public void execute(ItemList itemList, Ui ui) {
        // O(n) search for items matching name and description
        List<Item> results = new ArrayList<>();
        for (int i = 0; i < itemList.getSize(); i++) {
            Item searchItem = itemList.getItem(i);
            // Note that the .get() on the right is safe due to short circuit eval
            if (this.name.isPresent() && !searchItem.getName().contains(name.get())) {
                continue;
            }
            if (this.description.isPresent() && !searchItem.getDescription().contains(description.get())) {
                continue;
            }
            results.add(searchItem);
        }

        ui.showMessages("Here are the items matching your search terms: ");
        for (int i = 0; i < results.size(); i++) {
            ui.showMessages(String.valueOf(i + 1) + "." + results.get(i));
        }
    }

    @Override
    public boolean equals(Object other) {
        SearchCommand toCompare;
        if (other==this) {
            // return if same object
            return true;
        }
        if (other instanceof SearchCommand) {
            // cast only if other is instance of SearchCommand
            toCompare = (SearchCommand) other;
            return (this.name.equals(toCompare.name)) && (this.description.equals(toCompare.description));
        } else {
            // null, or object not SearchCommand
            return false;
        }
    }
}