package seedu.duke.commands;

import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Optional;

/** Performs case insensitive search of an Item's name, or description, or both. */
public class SearchCommand extends Command {
    public static final String COMMAND_WORD = "search";
    public static final String COMMAND_NAME = "Search Item";
    public static final String USAGE_MESSAGE = "Search for the name and/or description of an item.";
    public static final String COMMAND_FORMAT = COMMAND_WORD + "/n name [/d description] | [/n name] /d description]";
    public static final String HELP_MESSAGE = COMMAND_NAME + ":\n" + "[Function] " + USAGE_MESSAGE + ":\n"
            + "[Command Format] " + COMMAND_FORMAT + "\n";
    public static final String SEARCH_RESULT_PREAMBLE = "Here are the items matching your search terms: ";
    public static final String SEARCH_RESULT_ENTRY_FORMAT = "%d. %s";

    private final Optional<String> name;
    private final Optional<String> description;

    /**
     * Constructs a SearchCommand.
     * One of {@code name} or {@code description} must not be null.
     * @param name a name to search for
     * @param description a description to search for
     * @throws NullPointerException if the constructor is called when both name and description are null.
     */
    public SearchCommand(Optional<String> name, Optional<String> description) {
        this.name = name;
        this.description = description;
        if (!name.isPresent() && !description.isPresent()) {
            throw new NullPointerException();
        }
        results = new ArrayList<>();
    }

    @Override
    public void execute(ItemList itemList, Ui ui) {
        // O(n) search for items matching name and description
        ui.showMessages(SEARCH_RESULT_PREAMBLE);
        for (int i = 0; i < itemList.getSize(); i++) {
            Item searchItem = itemList.getItem(i);
            if (this.name.isPresent()
                    && !caseInsensitiveContains(searchItem.getName(), this.name.get())) {
                continue;
            }
            if (this.description.isPresent()
                    && !caseInsensitiveContains(searchItem.getDescription(), this.description.get())) {
                continue;
            }
            results.add(searchItem);

            String printMsg = String.format(SEARCH_RESULT_ENTRY_FORMAT, i + 1, searchItem.toDetailedString());
            ui.showMessages(printMsg);
        }
    }

    @Override
    public boolean equals(Object other) {
        SearchCommand toCompare;
        if (other == this) {
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

    private boolean caseInsensitiveContains(String str1, String str2) {
        String lowerStr1 = str1.toLowerCase();
        String lowerStr2 = str2.toLowerCase();
        return lowerStr1.contains(lowerStr2);
    }

}