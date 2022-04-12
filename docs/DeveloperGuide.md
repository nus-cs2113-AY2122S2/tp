# Developer Guide

## Table of contents

## Table of contents

1. [Acknowledgements](#acknowledgements)
2. [Setting up and getting started](#setting-up-and-getting-started)
   1. [Prerequisites](#prerequisites)
3. [Design](#design)
   1. [Architecture](#architecture)
   2. [Commons Component](#commons-component)
   3. [UI Component](#ui-component)
   4. [Data Component](#data-component)
   5. [Parser Components](#parser-components)
   6. [Command Component](#command-component)
   7. [Storage Component](#storage-component)
4. [Implementation](#implementation)
   1. [Add Command](#add-command)
   2. [Description Command](#description-command)
   3. [Help Command](#help-command)
   4. [Delete Command](#delete-command)
   5. [Return Command](#return-command)
   6. [Lost Command](#lost-command)
   7. [List Command](#list-command)
   8. [List Available Borrowings Command](#list-available-borrowings-command)
   9. [Cancel Future Borrowings Command](#cancel-future-borrowings-command)
   10. [Edit Command](#edit-command)
   11. [Search Command](#search-command)
   12. [Borrow Command](#borrow-command)
   13. [Storage](#storage)
5. [Product scope](#product-scope)
6. [User Stories](#user-stories)
7. [Non-Functional Requirements](#non-functional-requirements)
8. [Instructions for manual testing](#instructions-for-manual-testing)

## Acknowledgements

The following third party libraries and applications were utilised and referenced in the making of InvMgr.

1. GSON 
   1. [Library Link](https://github.com/google/gson) & [License](https://github.com/google/gson/blob/master/LICENSE)
   2. JSON serialisation and deserialisation
2. SE-EDU Address Book Level 3 
   1. [App link](https://github.com/se-edu/addressbook-level3) & [License](https://github.com/se-edu/addressbook-level3/blob/master/LICENSE)
   2. Parser Code
   3. Developer Guide

## Setting up and getting started

### Prerequisites

Before setting up the project locally, ensure you have installed the following:

1. Java 11
2. Intellij IDEA (Community/Pro) 

Follow the steps in the following guide precisely. Things will not work out if you deviate in some steps.

First, **fork** this repo, and **clone** the fork into your computer.

If you plan to use Intellij IDEA (highly recommended):
1. **Configure the JDK**: Follow the guide [_[se-edu/guides] IDEA: Configuring the JDK_](https://se-education.org/guides/tutorials/intellijJdk.html) to to ensure Intellij is configured to use **JDK 11**.
2. **Import the project as a Gradle project**: Follow the guide [_[se-edu/guides] IDEA: Importing a Gradle project_](https://se-education.org/guides/tutorials/intellijImportGradleProject.html) to import the project into IDEA. <br>
   Note: Importing a Gradle project is slightly different from importing a normal Java project.
3. **Verify the setup**:
   1. Run `seedu.duke.InvMgr` and try a few commands.
   2. Run the tests to ensure they all pass.
   
## Design

### Architecture

![ArchitectureDiagram](img/ArchitectureDiagram.png)

The Architecture Diagram given above explains the high-level design of the application

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

`InvMgr` is the main class and entry point for the application. It does the following:

1. **At app launch**: Initializes the components in the correct sequence, and connects them up with each other.
2. **When process is running**: Keeps the program running by continuously invoking the correct components in order
3. **At shut down**: Shuts down the components and invokes cleanup methods where necessary.

`Commons` represents a collection of classes used by multiple other components. 

The rest of the App consists of five other components.

1. `UI`: The UI of the App. 
2. `Data`: Holds the data of the application (`Item`) in memory.
3. `Parser`: Creates the appropriate `Command` object.
4. `Command`: Executes user instructions.
5. `Storage`: Reads data from, and writes data to, the storage medium.
 
#### Application Startup

![ApplicationStartupSequenceDiagram](img/ApplicationStartupSequenceDiagram.png)

The above diagram shows a sequence diagram of the program when it first starts up

On startup, `InvMgr` does a setup by creating the required `Ui`, `Storage`, and `ItemList` objects.

1. The `Ui` object is initialised.
2. `load()` from `Storage` is called. This returns an `ArrayList<Item>` containing the application data.
3. The `ArrayList<Item>` from before is used to create the `ItemList` object. `ItemList` is a wrapper around a `List<Item>`, and acts as the `Data` component of the application.

#### Application runtime and exit

![ApplicationRuntimeSequenceDiagram](img/ApplicationRuntimeSequenceDiagram.png)

The above diagram shows a sequence diagram of the program when it is running.

`InvMgr` will continuously loop, doing the following:

1. `InvMgr` will call upon `Ui` to get input from the user.
2. `InvMgr` passes the user input to `Parser.parse(command)`.
3. `Parser.parse(command)` returns a `Command` object.
4. `InvMgr` calls upon the `execute()` method of the returned `Command` object .
5. The loop stops when the user types `exit`.

### Commons Component

![stuff](todo)

The above diagram todo...

### UI Component

![UiClassDiagram](img/UiClassDiagram.png)

The above diagram shows the class diagram for the `Ui` component.

The `UI` component
- Displays salutations, prompts for user input, error messages and results of queries.
- Reads in user inputs
- Depends on the `Messages` and `InvMgrException` classes in the `Common` component. It displays messages stored in the `Messages` class and displays an error message whenever `InvMgrException` is invoked.

### Data Component

![ItemListClassDiagram](img/ItemListClassDiagram.png)

The above diagram shows the class diagram for an `ItemList`.

The `ItemList` component represents an inventory list:
- An `ItemList` contains 0 or more `Item`.
  - Example of `Item`: JBX Speakers.
- An `Item` contains 0 or more `BorrowRecord`.
- A `BorrowRecord` contains only 1 `BorrowStatus`.
  - `BorrowStatus`: Whether a borrow record is still current, or in the future or happened already in the past.

### Parser Components

![ParserClassDiagram](img/ParserClassDiagram.png)

The above diagram shows the class diagram for the `Parser` component.

How the parsing works:
- When called upon to parse a user command, the `InputParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `InputParser` returns back as a `Command` object.
- All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Command Component

![CommandClassDiagram1](img/CommandClassDiagram1.png)
![CommandClassDiagram2](img/CommandClassDiagram2.png)
![CommandClassDiagram2](img/CommandClassDiagram3.png)

The above diagram shows the class diagram for the `Command` component.

`Command` is an abstract class that sets certain commonalities that is implemented across all types of commands eg. `AddCommand`, `DescCommand`, `ListCommand`, etc. Each of these command classes have to override `Command`'s `execute()` method as each command has a different execution. For example, `AddCommand` will be focused on adding an item to an inventory list whereas `DescCommand` will be about retrieving information from the inventory list.

### Storage Component

![StorageClassDiagram](img/StorageClassDiagram.png)

The above diagram shows the class diagram for the `Storage` component.

1. `Storage` has `save()` and `load()` methods. These are called by `InvMgr` when needed.
2. `Storage` uses the 3rd party library GSON through the `Gson` object to convert the `Data` component into JSON and vice-versa.
3. `save(itemList)` which writes the contents of the `List` within the Data Component to the file at `dataPath`.
4. `load(itemList)` loads a JSON file into the `Data` component.

## Implementation

### Add Command

**Normal function**

![AddCommandSequenceDiagram](img/AddCommandSequenceDiagram.png)

The above diagram shows the sequence diagram of the addition of an item.

The user starts by typing an add command. The example used in the diagram above is the addition of an item with the name `Paper Cup`, quantity of `25` and description of `100ml paper cups`. The full command is `add n/Paper Cup q/25 d/100ml paper cups`.

1. The `run()` method within `InvMgr` calls the static method `parse()` in the `Parser` class, providing the entire string of input entered by the user.
2. Within `parse()`, the string is first split into 2 parts, the command word and the arguments. The command word is identified to be `add`, and executes the code within the case. The case calls the `parse()` method in `AddCommandParser`.
3. `AddCommandParser.parse()` uses the string argument and extracts the `NAME`, `QUANTITY`, and `DESCRIPTION` using to an `ArgumentMultimap` using `ArgumentTokenizer.tokenize()`.
4. The `QUANTITY` is then checked using `ParserUtils.parseQuantity()` to see if it is a positive non-zero integer.
5. If the `QUANTITY` is valid, an `Item` is created using the extracted arguments.
6. `parse()` uses the `Item` to generate a new `AddCommand` which is returned to the `run()` method.
7. The `run()` method calls on the `execute()` function in the `AddCommand`
8. `execute()` first creates a list of items using the `itemList`'s `getItemArrayList()` method.
9. The method then uses Java Streams to check if there are any items in the list that match the name of the item to be added.
10. If there is no item with matching name, the item will be added through `ItemList`'s `addItem()` method, and `AddCommand` will converse with `Ui` to show a message that the item has been added. In this case, the item to add will be printed as the name of the item, followed by " has been added!".
11. If there is in fact an item with matching name, `AddCommand` will not add the item to the item list, and will converse with `Ui` to show `DUPLICATE_ITEM_MESSAGE` from `common/Messages`, "There is already a similar item in the list! Use edit command to edit the item's quantity/description instead. Or change the name of the item to be more specific."

### Description Command

**Normal function**

![DescCommandSequenceDiagram](img/DescCommandSequenceDiagram.png)

The above diagram shows the sequence diagram for retrieving the description of an item.

The user starts by typing a desc command. The example used in the diagram above is to retrieve the description of an item with the index `1`, based on the list when the user types the `list` command.

1. The `run()` method within `InvMgr` calls the static method `parse()` in the `Parser` class, providing the entire string of input entered by the user.
2. Within `parse()`, the string is identified to start with the word `desc` and identifies the index given by the user ie. `1`.
3. `parse()` then generates a new `DescCommand` with the index as the argument. The new `DescCommand` is returned to the `run` method.
4. The `run()` method then calls on the `execute()` method in `DescCommand` which retrieves the specified `item` from `itemList`.
5. With the `item` object, we are able to retrieve the item's description and pass it as a String to be printed by `ui` using `showMessages` method.

**Error handling**

Exceptions are thrown/handled for the following:
1. When the user enters an index for an item that is not in the list (e.g. `100000` when item 100000 does not exist).

### Help Command

**Normal function**

![HelpCommandSequenceDiagram](img/HelpCommandSequenceDiagram.png)

The above diagram shows the sequence diagram for displaying the help menu. The help menu contains a list of all functions of Inventory Manager, as well as their function and syntax for calling them. 
<br> For a user who is unfamiliar with Inventory Manager, this help menu will enable the user to utilise Inventory Manager to its full capabilities.  
<br> The user starts by typing the `help` command. 

1. The `run()` method within `InvMgr` calls the static method `parse()` in the `Parser` class, providing the entire string of input entered by the user.
2. parse()` generates a new `HelpCommand` which is returned to the `run()` method of `InvMgr`.
3. The `run()` method calls on the `execute()` function in `HelpCommand`.
4. The `execute()` function calls `showMesages()`, taking in each Command class' help message as the argument and displaying them to the user.

### Delete Command

**Normal function**

![DeleteCommandSequenceDiagram](img/DeleteCommandSequenceDiagram.png)

The above diagram shows the sequence diagram of the addition of an item.

The user starts by typing a delete command. The example used in the diagram above is the deletion of an item with the index `1`, based on the list when the user types the `list` command.

1. The `run()` method within `InvMgr` calls the static method `parse()` in the `InputParser` class, providing the entire string of input entered by the user.
2. Within `parse()`, the string is first split into 2 parts, the command word and the arguments. The command word is identified to be `delete`, and executes the code within the case. The case calls the `parse()` method in `DeleteCommandParser`.
3. Within `DeleteCommandParser.parse()`, the argument is a number that is taken through a check to see if the number is a positive integer. This is done so by calling `ParserUtils.parseIndex()`.
4. If the argument is valid, `parse()` generates a new `DeleteCommand` using the index which is returned to the `run()` method.
5. The `run()` method calls on the `execute()` function in the `DeleteCommand` which will delete the item with that index from the `ItemList` using its `removeItem()` method.
6. If the argument is an invalid index beyond the range of the inventory list, an exception will be thrown.
7. `DeleteCommand` will converse with `Ui` to show a message that the item has been removed. In this case, the item to add will be printed as the name of the item, followed by " has been deleted.".

### Return Command

**Normal function**

![ReturnCommandSequenceDiagram](img/ReturnCommandSequenceDiagram.png)

The above diagram shows the sequence diagram of Return command, which allows users to return an item that is either overdue or currently on loan.

The user starts by typing a return command. The diagram above uses the example of a user who wishes to mark an item of index `1` as returned. The full return command is `return i/1`.

1. The `run()` method within `InvMgr` calls the static method `parse()` in the `Parser` class, which then calls the `parse()` method in `ReturnCommandParser`.
2. `ReturnCommandParser` parses the user input into `itemIndex`, and returns a `ReturnCommand` object that is returned to `InputParser` and then `InvMgr`.
3. The `run()` method calls on the `execute()` function in `ReturnCommand`.
4. The `execute()` function calls `checkItemListSize()`, which then calls `getSize()` to check if the item list is empty. If it is, an exception is thrown and return cannot be performed. 
5. Then, it calls `getItem()` to check if the item index is within range. If it is not, an exception is thrown and return cannot be performed. 
6. Then, it obtains the item's borrow records by calling `getBorrowRecords()` from `Item`.
7. Each borrow record is iterated over and its borrow status and return status are obtained using `getBorrowStatus()` and `getReturnStatus()` respectively.
8. A borrow record is considered overdue if its borrow status is past and its return status is false, while a borrow record is outstanding if its borrow status is present and its return status is false.
   If a borrow record is either overdue or outstanding, then it is marked as returned using `setReturnStatus`. The end date of the borrow record is changed to the day of return using `setEndDate()`. 
   `ReturnCommand` then converses with `Ui` to show the successful returned message.
9. If none of the borrow records are overdue or outstanding, then it is taken to be an invalid return request. In this scenario, `ReturnCommand` throws an exception.

**Error handling**

`ReturnCommandParser` throws/handles exceptions in the following situations:
1. When the user enters a command without any item index (missing `i/ `). This is done in `ReturnCommandParser`.
2. When the user enters an invalid item index (item index < 0). This is done in `ReturnCommandParser`.
3. When the user enters an invalid item index (item index is not an integer). This is done in `ReturnCommandParser`.

`ReturnCommand` throws/handles exceptions in the following situations: 
1. When the user enters an item index that is out of range (> total number of items in inventory).
2. When the user tries to return an item but the inventory is empty. 
3. When there are no items that are overdue or currently on loan and hence no items are due to be returned.

### Lost Command

**Normal function**

![LostCommandSequenceDiagram](img/LostCommandSequenceDiagram.png)

The above diagram shows the sequence diagram of Lost command, which allows users to mark an item as missing and update the inventory accordingly.

The user starts by typing a lost command. The diagram above uses the example of a user who wishes to mark `10` quantities of an item of index `1` as lost. The full return command is `lost i/1 q/10`.

1. The `run()` method within `InvMgr` calls the static method `parse()` in the `Parser` class, which then calls the `parse()` method in `LostCommandParser`.
2. `LostCommandParser` parses the user input into `itemIndex`, and returns a `LostCommand` object that is returned to `InputParser` and then `InvMgr`.
3. The `run()` method calls on the `execute()` function in `LostCommand`.
4. The `execute()` function calls `checkItemListSize()`, which then calls `getSize()` to check if the item list is empty. If it is, an exception is thrown and no items can be marked as lost.
5. Then, it calls `getItem()` to check if the item index is within range. If it is not, an exception is thrown and lost cannot be performed.
6. `removeItem(itemIndex: Integer)` is called to remove the item from the item list. Then, `showMessages(lostItem + "has been deleted")` is called to display a message that tells the user that the item has been deleted. 
7. `showMessages(Messages.REPORTED_LOST_AND_DELETED` is called to tell the user that the item has been reported lost and deleted from the inventory.

**Error handling**

`LostCommandParser` throws/handles exceptions in the following situations:
1. When the user enters a command without any item index (missing `i/ `). This is done in `ReturnCommandParser`.
2. When the user enters an invalid item index (item index < 0). This is done in `ReturnCommandParser`.
3. When the user enters an invalid item index (item index is not an integer). This is done in `ReturnCommandParser`.

`LostCommand` throws/handles exceptions in the following situations:
1. When the user enters an item index that is out of range (> total number of items in inventory).
2. When the user tries to report an item as lost but the inventory is empty.

### List Command

**Normal function**

![ListCommandSequenceDiagram](img/ListCommandSequenceDiagram.png)

The above diagram shows the sequence diagram of the listing of items in `itemList`.

The user starts by typing a list command.

1. `InvMgr` calls `parse("list")` method in `InputParser` class, which returns a ListCommand object.
2. `InvMgr` calls `execute(itemList, ui)` method in `ListCommand` object.
3. `ListCommand` loops through every `Item` in `itemList` and prints them line by line and numbers them.

### List Available Borrowings Command

**Normal function**

![ListAvailableBorrowingsSequenceDiagram](img/ListAvailableBorrowingsSequenceDiagram.png)

The above diagram shows the sequence diagram of listing the minimum number of items that can be borrowed between a start date and an end date

The user starts by typing a `listab s/STARTDATE e/ENDDATE` command. 

1. `InvMgr` calls `parse("listab s/XYZ e/ABC")` method in `InputParser` class, which then calls `parse("listab s/XYZ e/ABC")` method in `ListAvailableBorrowingsParser`.
2. `ListAvailableBorrowingsParser` parses the user input into `startDate` and `endDate`, and returns a `ListAvailableBorrowingsCommand` object.
3. `InvMgr` calls `execute(itemList, ui)` method in `ListAvailableBorrowingsCommand` object.
4. `ListAvailableBorrowingsCommand` loops through each item in `itemList`, and calls the `minQuantityAvailable` method to check the minimum quantity that can be borrowed throughout `startDate` to `endDate`.
5. If `minQuantity` is more than 0, the item is printed out with the quantity that can be borrowed.

### Cancel Future Borrowings Command

**Normal function**

![CancelFutureBorrowingsSequenceDiagram](img/CancelFutureBorrowingsSequenceDiagram.png)
![CancelFutureBorrowingsRef](img/CancelFutureBorrowingsRef.png)

The above diagram shows the sequence diagram of cancelling future reservations of items

The user starts by typing `cancel p/NAME i/INDEX` command. 

1. `InvMgr` calls `parse("cancel p/ABC i/1")` method in `InputParser` class, which then calls `parse("cancel p/ABC i/1")` method in `CancelFutureBorrowingsParser`.
2. `CancelFutureBorrowingsParser` parses the input into `name` and `index`, and returns a `CancelFutureBorrowingsCommand` object.
3. `InvMgr` calls `execute(itemList, ui)` method in `CancelFutureBorrowingsCommand` object.
4. `CancelFutureBorrowingsCommand` retrieves the `BorrowRecord` that is to be removed, and the `Item` that contains this `BorrowRecord`
5. `BorrowRecord` is then removed from the `Item`'s BorrowRecord list.


### Edit Command

**Normal function**

![EditCommand1SequenceDiagram](img/EditCommand1SequenceDiagram.png)
![EditCommand2SequenceDiagram](img/EditCommand2SequenceDiagram.png)
![EditCommand3SequenceDiagram](img/EditCommand3SequenceDiagram.png)

The above diagrams show the sequence diagram when editing an item.

The user starts by typing an `edit` command.

1. `InvMgr` calls `parse(command)`. `command` is the user input, i.e. `edit 1 n/Marker q/5 d/To draw on whiteboard r/+`.
2. `InvMgr` creates the appropriate `Command` object based on the user input, which is the `EditCommand`. The arguments are also parsed and the needed values are stored within `EditCommand`.
3. `InvMgr` calls `execute(itemList, ui)` of the `EditCommand` object.
4. Within the `execute()` method of `EditCommand`, `EditCommand` will try to extract the `Item` at the specified index of `ItemList` (`1` in this case).
5. Next, `EditCommand` duplicates the `Item`. It will be named as `placeholderItem`.
6. `placeholderItem` will have its attributes set accordingly based on the presence of various arguments.
   1. Since `name` is present (`n/Marker`), `EditCommand` will change the name of `placeholderItem` to `Marker`.
   2. Since `quantity` is present (`q/5`), `EditCommand` will add 5 to the quantity of `placeholderItem`.
   3. Since `description` is present (`q/To draw on whiteboard`), `EditCommand` will change the description of `placeholderItem` to `To draw on whiteboard`.
7. The `Item` at index 1 of `ItemList` will be replaced by the `placeholderItem`.
8. `EditCommand` will print out the changes in `Item`.

**Error handling**

Exceptions are thrown/handled for the following:

1. When the user enters a command without any name, description or quantity (missing all of `n/ q/ d/`). This is done in `EditCommandParser`.
2. When the user sets the relative flag (`r/`) without any associated quantity. This is done in `EditCommandParser`.
3. When the user enters an index for an item that is not in the list (e.g. `100000` when item 100000 does not exist). This is done in `EditCommand`.

### Search Command

**Normal function**

![SearchCommand1SequenceDiagram](img/SearchCommand1SequenceDiagram.png)
![SearchCommand2SequenceDiagram](img/SearchCommand2SequenceDiagram.png)

The above diagrams show the sequence diagram when searching for items.

The user starts by typing a `search` command.

1. `InvMgr` calls `parse(command)`. `command` is the user input, i.e. `search n/Marker d/draw`.
2. `InvMgr` creates the appropriate `Command` object based on the user input, which is the `SearchCommand`. The arguments are also parsed and the needed values are stored within `SearchCommand`.
3. `InvMgr` calls `execute(itemList, ui)` of the `SearchCommand` object.
4. For each `Item` in `ItemList`, `SearchCommand` will try to:
   1. Match, if given, the `name` to search in the name of `Item`.
   2. Match, if given, the `description` to search in the name of `Item`.
   3. If the `Item` matches the given arguments, the `Item` is prepared for printing through `String.format`, not shown for brevity.
   4. The `Item` is then printed.

**Error handling**

Exceptions are thrown/handled for the following:

1. When the user enters a command without any name or description (missing all of `n/ d/`). This is done in `SearchCommandParser`.

### Borrow Command

**Normal function**
![BorrowCommandSequenceDiagram](img/BorrowCommandSequenceDiagram.png)

The above diagrams show the sequence diagram for borrowing an item.

The user starts by typing a `borrow` command. eg. `borrow i/1 q/5 s/2022-03-21 e/2022-03-25 p/John Smith`
1. The `run()` method within `InvMgr` calls the static method `parse()` in the `Parser` class, providing the entire string of input entered by the user.
2. Within `parse()`, the string is identified to start with the word `borrow` and identifies the other arguments `index`, `quantity`, `startDate`, `endDate`, `borrowerName`.
3. `parse()` then generates a new `BorrowCommand` with the identified arguments. The new `BorrowCommand` is returned to the `run` method.
4. The `run()` method calls the `execute()` method in `BorrowCommand`.
5. A new `BorrowRecord` is created with the borrow details recorded eg. `quantity`, `startDate`, `endDate`, `borrowerName`.
6. To know which item this `BorrowRecord` belongs to, we use the item `index` given by the user to retrieve the `item` from `itemList`. 
7. The retrieved `item` object calls its own addBorrowRecord() method to attach the new borrow record to the list of records the item is holding.
8. Once successful, a message will be sent to the UI using `showMessages` method to inform the user.

**Error handling**

Exceptions are thrown/handled for the following:
1. When the user enters an index for an item that is not in the list (e.g. `100000` when item 100000 does not exist).
2. If there is insufficient quantity in the inventory to borrow to the user for the given dates.
3. If there are any missing arguments `i/`, `q/`, `s/`, `e/`, `p/`.
4. if borrow duration is longer than the maximum borrow duration of 7 days.
5. If `startDate` > `endDate`. Ie. Dates need to be in non-descending order.

### ListFutureBorrowings Command

**Normal function**

![ListFutureBorrowings1SequenceDiagram](img/ListFutureBorrowings1SequenceDiagram.png)
![ListFutureBorrowings2SequenceDiagram](img/ListFutureBorrowings2SequenceDiagram.png)

The above diagrams shows the sequence diagram of listing future borrowings.

The user starts by typing a `listfb` command. The example used in the diagrams above is listing of future borrowings for person `Jasper`

1. The `run()` method within `InvMgr` calls the static method `parse()` in the `Parser` class, providing the entire string of input entered by the user.
2. Within `parse()`, the string is first split into 2 parts, the command word and the arguments. The command word is identified to be `listfb`, and executes the code within the case. The case calls the `parse()` method in `ListFutureBorrowingsParser`.
3. `ListFutureBorrowingsParser.parse()` uses the string argument and extracts the `BORROWER_NAME` which is optional using an `ArgumentMultimap` through the method `ArgumentTokenizer.tokenize()`.
4. `parse()` uses the `BORROWER_NAME` to generate a new `ListFutureBorrowingsCommand` which is returned to the `run()` method.
5. The `run()` method calls on the `execute()` function in the `ListFutureBorrowingsCommand`
6. `execute()` first creates a list of items using the `itemList`'s `getItemArrayList()` method.
7. The method then uses Java Streams to check if there are any relevant `BorrowRecords`. These records are stored as strings in a list called `futureRecords`
8. Based on the size of the `futureRecords`, the `itemList` will converse with `Ui` to give the appropriate output.

### ListOverdueBorrowings Command

**Normal function**

![ListOverdueBorrowings1SequenceDiagram](img/ListOverdueBorrowings1SequenceDiagram.png)
![ListOverdueBorrowings2SequenceDiagram](img/ListOverdueBorrowings2SequenceDiagram.png)

The above diagrams shows the sequence diagram of listing overdue borrowings.

The user starts by typing a `listfb` command. The example used in the diagrams above is listing of overdue borrowings for person `Jasper`

1. The `run()` method within `InvMgr` calls the static method `parse()` in the `Parser` class, providing the entire string of input entered by the user.
2. Within `parse()`, the string is first split into 2 parts, the command word and the arguments. The command word is identified to be `listfb`, and executes the code within the case. The case calls the `parse()` method in `ListOverdueBorrowingsParser`.
3. `ListOverdueBorrowingsParser.parse()` uses the string argument and extracts the `BORROWER_NAME` which is optional using an `ArgumentMultimap` through the method `ArgumentTokenizer.tokenize()`.
4. `parse()` uses the `BORROWER_NAME` to generate a new `ListOverdueBorrowingsCommand` which is returned to the `run()` method.
5. The `run()` method calls on the `execute()` function in the `ListOverdueBorrowingsCommand`
6. `execute()` first creates a list of items using the `itemList`'s `getItemArrayList()` method.
7. The method then uses Java Streams to check if there are any relevant `BorrowRecords`. These records are stored as strings in a list called `overdueRecords`
8. Based on the size of the `overdueRecords`, the `itemList` will converse with `Ui` to give the appropriate output.

### Storage

#### Initialisation

**Normal function**

![StorageInitialisationSequenceDiagram](img/StorageInitialisationSequenceDiagram.png)

The above diagrams show the sequence diagram when `Storage` is initialisd.

1. `InvMgr` calls the `Storage(filePath)` constructor to create a `Storage` object. `filePath` is a `String` indicating where the data file to be loaded is found.
2. `Storage(filePath)` will check if the file at `filePath` exists. If it does, it returns a `Path` object pointing to the data file.
3. If not, the relevant files and subdirectories are created before returning the corresponding `Path` object.
4. The new `Storage` object will have its `dataPath` attribute set to the `Path` object earlier, and its `filePath` attribute set to the `filePath` passed into the constructor.

**Error handling**

Exceptions are thrown/handled for the following:

1. Input/output errors when creating the file needed at `dataPath`.
2. When `Storage` is created with a `filePath` that does not represent a valid file path.

#### Loading data

**Normal function**

![StorageLoadSequenceDiagram](img/StorageLoadSequenceDiagram.png)
![StorageFileReadSequenceDiagram](img/StorageFileReadSequenceDiagram.png)

The above diagrams show the sequence diagram when `load()` within Storage is called. 

Typically, this is only run once when the program first launches.

1. `InvMgr` calls the `load()` method of `storage`.
2. `storage` initialises `GsonManager()`. It is part of the library used to serialise and deserialise JSON into Java objects.
3. The `GsonManager` is configured to handle `BorrowRecord` object types. The project implements custom serialisation and deserialisation for `BorrowRecord`.
4. A `Gson()` instance is created based on the configurations in `GsonManager()`. It is referred to as `gson`.
5. `storage` will then load the contents of the file at `dataPath` into `wholeJsonData`.
   1. Implemention details for this step is on the second diagram.
6. `storage` then calls the `fromJson(wholeJsonData)` method of `gson`. This deserialises the JSON data into the respective Java objects.
7. An `ArrayList<Item>` may be returned by `fromJson()` method. If it is not (e.g. parsing error), a new empty list is created.
8. `storage` returns `ArrayList<Item>` to `InvMgr`. This will be used to create the `ItemList`.

**Error handling**

Exceptions are thrown/handled for the following:

1. Parsing error while deserialising the JSON data.
2. Input/output errors while reading the JSON file. 

#### Saving data

The following diagram shows the sequence diagram illustrating how the data file is saved. Typically, this is done after each `Command` is run.

![StorageSaveSequenceDiagram](img/StorageSaveSequenceDiagram.png)
![StorageFileWriteSequenceDiagram](img/StorageFileWriteSequenceDiagram.png)

1. `InvMgr` calls the `save(itemList)` method of `storage`.
2. `storage` initialises `GsonManager()`. It is part of the library used to serialise and deserialise JSON into Java objects.
3. The `GsonManager` is configured to handle `BorrowRecord` object types. The project implements custom serialisation and deserialisation for `BorrowRecord`.
4. A `Gson()` instance is created based on the configurations in `GsonManager()`. It is referred to as `gson`.
5. `storage` calls the `toJson(itemList)` method of `gson`. This returns a `String` named `serializedItems` which contains the JSON String representing `itemList`.
6. `storage` then writes `serializedItems` to the file at `dataFile`.
   1. Implemention details for this step is on the second diagram.

**Error handling**

Exceptions are thrown/handled for the following:

1. Input/output errors while writing to the JSON file.

## Product scope
### Target user profile

CCA Clubs that require assistance in inventory management, especially for fast typists who prefer CLI over GUI.

### Value proposition

1. Centralised management of resources that ensures accurate and timely allocation of equipment to students
2. Increases the ease and efficiency of resource management
3. More organised

## User Stories

| Version | As a ...                               | I want to ...                                   | So that I can ...                                                   |
|---------|----------------------------------------|-------------------------------------------------|---------------------------------------------------------------------|
| v1.0    | Manager                                | Add a new item to the inventory                 | Update my inventory                                                 |
| v1.0    | Manager                                | Remove an item from the inventory               | Update my inventory                                                 |
| v1.0    | Stocktaker                             | list out all my items                           | View all my items at a glance                                       |
| v1.0    | New user                               | List out all possible commands                  | I can familiarise myself with using the system                      |
| v1.0    | User who has not seen items physically | Get the description of a particular item        | I can visualise the item better to know if it is what i need        |
| v1.0    | As a frequent/first time user          | Write to a file containing the entire inventory | Save my inventory data to a file                                    |
| v1.0    | Stocktaker                             | Read from and load an inventory file data       | To work on and view the data                                        |
| v2.0    | User                                   | Search for an item in the inventory             | To check if the item exists in the inventory                        |
| v2.0    | User                                   | Edit an item in the inventory                   | Update/Make changes to the item attributes                          |
| v2.0    | User                                   | Borrow an item in the inventory                 | Add a borrow record in the inventory                                |
| v2.0    | User                                   | List all current borrowings                     | View which item is currently being borrowed                         |
| v2.0    | User                                   | List all future borrowings                      | View which item will be borrowed in the future and plan accordingly |
| v2.0    | User                                   | List all past borrowings                        | View which item has been borrowed in the past                       |
| v2.0    | User                                   | List all available borrowings                   | View which item is available for borrowing now                      |
| v2.1    | User                                   | Cancel future borrowings                        | Allow others to borrow the items instead                            |
| v2.1    | User                                   | Mark borrowed items as returned                 | Update what items are in the store                                  |
| v2.1    | User                                   | Report items as lost                            | Update if items have been permanently lost                          |

## Non-Functional Requirements

1. Should work across Windows, Linux and Mac OS X that has an installation of Java 11.
2. A user with above average typing speed should be able to accomplish tasks faster relative to using a mouse.

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

Run the program and add sample data with `add`. Type `help` for more information.

Try `edit`, `delete`, and `borrow` to play with the data. `list` and its variants to view data.
