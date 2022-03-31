# Developer Guide
## Contents
* [Acknowledgements](#acknowledgements)
* [Design](#design)
    * [Architecture](#architecture)
    * [Manager Component](#manager-component)
    * [Profile Component](#profile-component)
    * [TextUI Component](#textui-component)
    * [Storage Component](#storage-component)
    * [Parser Component](#parser-component)
    * [Command Component](#command-component)
* Implementation
    * [Add a session](#add-a-session)
    * [Remove a session](#remove-a-session)
    * [View a session](#view-a-session)
    * [List all sessions](#list-sessions)
    * [Settle a session](#settle-a-session)
    * [Add an activity](#add-an-activity)
    * [Remove an activity](#remove-an-activity)
    * [View an activity](#view-an-activity)
    * [List all activities](#list-activities)
    * [Add a group](#add-a-group)
    * [Remove a group](#remove-a-group)
    * [View a group](#view-a-group)
    * [List all groups](#list-groups)
* [Project Scope](#product-scope)
* [User Stories](#user-stories)
* [Non-Functional Requirements](#non-functional-requirements)
* [Glossary](#glossary)


## Acknowledgements
* Address Book (Level-3) - Provide samples of User Guide(UG) and Developer Guide(DG)

## Design
### Architecture
![Application Diagram Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/ArchitectureDiagram.drawio.png)

The *Architecture Diagram* shown above illustrates the high-level design of the SplitLah application.

**Overview of components**
* `SplitLah [Main]`
    * On app launch: Creates an instance of a `Manager` and runs the command loop.
* `Manager`
    * On creation: Initializes the Profile, TextUI and Storage components.
    * On run: Loads data from `Storage`, receives user input from UI and uses `Parser` to parse user input into application.
* `Profile`
    * Handles cached data within run time of application. Manages and stores the list of `session` 
      that were created by the user before storing it into `Storage`.
* `TextUI`
    * Handles UI operations of the application.
* `Storage`
    * Handles storage operations of the application.
* `Parser`
    * Handles input parsing and determines which command to run.
* `Command`
    * Defines how a command is executed.

**Interaction between components**
![Component Interaction Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/ComponentInteraction.drawio.png)
<br>
The *Component Interaction Diagram* shows the inner workings of how each component in SplitLah interacts.
The diagram depicts a scenario where a user attempts to create a session.



### SplitLah Component
![SplitLah Component Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/SplitLahComponent.drawio.png)
<br>
The `SplitLah` component is the application's main class. Its job is to initialize an instance of `Manager` when the
application starts. After initialization, it would then proceed to run a loop which would prompt the user for a 
command. When it receives a command from the user, it would invoke the `parser` and retrieve the command for SplitLah
to run. Upon using the `Exit` command, SplitLah would then exit from the command loop and end the application.


### Manager Component
![Manager Component Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/ManagerComponent.drawio.png)
<br>
The `Manager` class is initialized by the `SplitLah` class (the main class) when the application starts.
It stores the `Profile`, `TextUI` and `Storage` objects. The `Profile` class helps to manage all data accesses 
throughout the lifetime of the application. While the `Storage` helps to save what the `Profile` class has captured. 
The `TextUI` class serves as an interface to read user inputs and print application outputs.

### Profile Component
![Profile Component Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/Profile%20Component.drawio.png)
<br>
The `Profile` component is responsible for all data management and accesses within the lifetime of `SplitLah`.

The `Profile` component consists of the `Profile`, `Session`, `Activity`, `PersonList`, `Person`, `ActivityCost`
and `Name` classes.
* The `Profile` class serves as a container and holds a list of all `Session` and `Group` objects created by the user.<br>
  It also keeps track and issues new _unique identifiers_ for the creation of `Session`, `Activity` and `Group` objects.
* The `Session` class stores a list of `Activity` objects that are created within a `Session` object as well as a
  `PersonList` object that represent a collection of all participants in the session.<br>
  It also holds an _optional_ `Group` object (explained in further detail in the following point).
  * A `Session` object is identified by a _session unique identifier_.
* The `Group` class holds a single `PersonList` object and simply represents a collection of persons.<br>
  A `Group` object serves as a reusable shortcut to represent a group of participants if used 
  in the creation of a `Session` object.
  * A `Group` object is identified by a _group unique identifier_.
* The `Activity` class holds a single `PersonList` object that represents persons involved in the activity as well as
  an independent `Person` object that represents the person who paid for the activity.
  * An `Activity` object is identified by an _activity unique identifier_.
* The `Person` class represents a single individual identified by their name which is stored in a `Name` object.<br>
  Each `Person` object also stores a list of `ActivityCost` objects representing the costs that they bear
  or have paid for in each activity that they participate in.
* The `PersonList` class serves as a container for `Person` objects.

Each of the classes in the `Profile` component also provides utility methods to manage the objects that they hold.

### TextUI Component
![TextUI Component Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/TextUIComponent.drawio.png)
<br>
The `TextUI` class is initialized by the `Manager` class when the application starts.
It stores a `Scanner` and `PrintStream` object supplied upon initialization to read and write to the user interface.
It offers methods to print application output to and read user input from these objects for other classes to use.
As TextUI handles all input and output streams, these streams can be changed without affecting the rest of the program.

### Storage Component
![Storage Component Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/StorageComponent.drawio.png)
<br>

### Parser Component
![Parser Component Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/ParserComponent.drawio.png)
<br>
The `Parser` component consists of the `Parser` class, `ParserUtils` class, `ParserErrors` class,
as well as the `CommandParser` class and its subclasses.<br>
* The `Parser` class provides utility methods to parse commands and arguments from the user and
  return a `Command` object representing an instruction that the user has for SplitLah.<br>
  `Parser` class is the only class in the `Parser` component that other external classes actively interact with.<br>
* The `ParserUtils` class provide supporting methods for `Parser` class to properly run,
  and `ParserErrors` class provide methods to produce custom error messages for the `Parser` component.<br>
* The subclasses of `CommandParser` then serve to parse all arguments of a user input to create an object of a specific
  subclass of the `Command` class.

The general workflow of the `Parser` component is as follows:
1. When required to parse for a command, the running `SplitLah` object passes a `String` object containing
   the user input to `Parser` class.
2. `Parser` class instantiates a new `XYZCommandParser` object corresponding to the user input 
   and passes the user input to it.
   (`XYZCommand` is a placeholder for specific subclass of the `Command` class, e.g. `SessionCreateCommand`)
3. The `XYZCommandParser` object then uses parse methods from `Parser` class to extract all the
   arguments from the user input.
   * Each of these parse methods in `Parser` class then calls utility methods from `ParserUtils` class 
     to return a parsed value.
4. All relevant arguments that are parsed are then used to create a new `XYZCommand `object to be
   returned to the `Parser` class.
5. The created `XYZCommand` object is then returned to the `SplitLah` object to be run.

### Command Component
![Command Component Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/CommandComponent.drawio.png)
<br>
The `Command` component consists of the Command class and all the subclasses of the Command class
(also referred to as XYZCommand collectively).
* The subclasses include _data related commands_ such as `SessionJKLCommand`, `ActivityJKLCommand` and `GroupJKLCommand`,
  each representing commands that work with `Session`, `Activity` and `Group` classes respectively
  (JKL here refers to either `Create`, `Delete`, `Edit`, `List`, `View` or `Summary`).
* The subclasses also include _utility commands_ such as `HelpCommand`, `ExitCommand` and `InvalidCommand`
  (hereafter collectively referred to as `UtilityCommand`).

The general workflow of the `Command` component is as follows:
1. After a `XYZCommand` object is created by the [`Parser` Component](#parser-component),
   it is passed back to the `SplitLah` object to be run.
   * In the above process, all necessary information for the execution of the command is passed to and
     saved by the `XYZCommand` constructor.
2. Then, `XYZCommand#run` is executed by `SplitLah`. `XYZCommand#run` carries out the task
   that `XYZCommand` is designed to do.
   * In general, for _data related commands_, `SessionJKLCommand`, `ActivityJKLCommand` and `GroupJKLCommand`
     obtains the relevant `Session`, `Activity` and `Group` objects before operating upon them with relevant methods.
   * On the other hand, for _utility commands_, `UtilityCommand` works with and uses methods from the `TextUI` component
     to print messages and carry out their tasks.
   * The inner workings of each of the `XYZCommand` classes can be seen in greater detail under the [Implementation section](#implementation).
3. After `XYZCommand#run` completes, control is returned to `SplitLah` and
   the [lifecycle](#architecture) of an `XYZCommand` object ends.


## Implementation

### Parsing of Commands
**API reference:** [`Parser.java`](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/parser/Parser.java)

The sequence diagram below models the interactions between various entities within the Parser component and
the Command component when any user input is provided to SplitLah.
<br>
<br>
![Reference Frame Parser Sequence Diagram](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/RefParser.drawio.png)
<br>
<br>
1. When `SplitLah` reads a user input, `SplitLah` calls the `Parser#getCommand` method and passes the
   user input as the argument.
2. Given the user input, `Parser` class first decomposes the user input into two separate components, the _command type_
   and the _remaining arguments_.
   This is done using the two methods `Parser#getCommandType` and `Parser#getRemainingArguments` respectively.<br>
   Where the input is `session /create /n Class Outing /d 15-03-2022 /pl Alice Bob`, the _command type_ is parsed as
   `session /create` and the _remaining arguments_ is parsed as `/n Class Outing /d 15-03-2022 /pl Alice Bob`.
   1. If the _command type_ is of invalid syntax, the method `Parser#getCommandType` returns null.
      When null is returned, `Parser` class creates and returns an `InvalidCommand` object to `SplitLah`.
   2. Next, to check whether the _command type_ and _remaining arguments_ are valid, `Parser` class calls the method
      `Parser#checkIfCommandIsValid`. If either the _command type_ or the _remaining arguments_ are invalid, an error
      message is returned by the method, which is then used to return an `InvalidCommand` object to `SplitLah`.
3. Depending on the _command type_, `Parser` class instantiates an appropriate `XYZCommandParser` object. For example,
   for a _command type_ of `"session /create"`, a `SessionCreateCommandParser` object is instantiated.
   If `Parser` class does not recognise the _command type_, an `InvalidCommand` object is created and returned immediately.
4. With the corresponding `XYZCommandParser` object instantiated, `Parser` class calls the `getCommand` method
   of `XYZCommandParser`. This process is explained in further detail in the sequence diagrams below.

   ![Reference Frame Command Parser Sequence Diagram](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/RefCommandParser.drawio.png)

   ![Reference Frame ParseABC Sequence Diagram](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/RefParseABC.drawio.png)

   ![Reference Frame InvalidCommand Instantiation Sequence Diagram](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/RefInvalidCommand.drawio.png)

5. After `XYZCommandParser#getCommand` is called, `XYZCommandParser` prepares to create a `XYZCommand` object. 
   To begin with, it parses all the _remaining arguments_ using `ParseABC` methods from the `Parser` class.
   (`ParseABC` is a placeholder for specific methods in `Parser` class, 
   e.g. `Parser#parseName` and `Parser#parseSessionId`)
   * For example, `SessionCreateCommandParser` has to call `parsePersonList`, `parseGroupId`, `parseName` and
     `parseLocalDate` from `Parser` class in order to get the details to create a `Session` object.
   * If an exception is encountered, `XYZCommandParser` handles the exception accordingly, and if necessary,
     throws an exception back to `Parser` class, resulting in an `InvalidCommand` object being created and returned.
6. In detail, when `Parser#parseABC` is called, `Parser` class calls the method `getArgumentFromDelimiter` from
   `ParserUtils` class, which returns the respective object being parsed.
   * For example, when `SessionCreateCommandParser` calls `Parser#parsePersonList`,
     `ParserUtils#getArgumentFromDelimiter` is called. After returning a `String` object containing the arguments to
     `Parser` class, `Parser` class returns a `String[]` object to `SessionCreateCommandParser` after processing the
     arguments.
   * Any exception encountered by `ParserUtils` class is propagated back to `XYZCommandParser` to be handled.
7. After all necessary information is parsed, `XYZCommandParser` instantiates a new `XYZCommand` object and passes
   all parsed information to it through the constructor.
8. The newly instantiated `XYZCommand` object is then returned from `XYZCommandParser` to `Parser` class,
   and finally back to `SplitLah` to be run.

### Add a session
**API reference:** [`SessionCreateCommand.java`](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/command/SessionCreateCommand.java)

The sequence diagram below models the interactions between various entities in SplitLah
when the user invokes the `session /create` command.
<br>
<br>
![Create Session Sequence Diagram Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/SessionCreateCommand.drawio.png)
<br>
<br>
The general workflow of the `session /create` command is as follows:
1. The user input provided is passed to `SplitLah`.
2. `SplitLah` then parses the input by using methods in the `Parser` class to obtain a `SessionCreateCommand` object.
3. `SessionCreateCommand#run` method is then invoked to run the `session /create` command.
4. Once the command runs, `SessionCreateCommand#run` method checks if there is an existing session with the same session name.
5. If an existing session with the specified session name is found, a message indicating that another session with the same name exists is printed using `TextUi#printlnMessage`.
6. The `SessionCreateCommand` class creates a new `Session` object using the session name, session date, and person list.
7. The list of `Session` objects are managed by a `Profile` object, hence `Manager#getProfile` is called to obtain the `Profile` object,
   which is used to call the `Profile#addSession` method in order to store the new `Session` object.
8. After the session is added to the `Profile` object, `Manager#saveProfile` is called to save the changes to the local storage file.
9. The `SessionCreateCommand` class then prints a message indicating that a session has been successfully created with TextUi#printlnMessage`.

### Remove a session
**API reference:** [`SessionDeleteCommand.java`](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/command/SessionDeleteCommand.java)

The sequence diagram below models the interactions between various entities in SplitLah
when the user invokes the `session /delete` command.
<br>
<br>
![Delete Session Sequence Diagram Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/SessionDeleteCommand.drawio.png)
<br>
<br>
The general workflow of the `session /delete` command is as follows:
1. The user input provided is passed to `SplitLah`.
2. `SplitLah` then parses the input by using methods in the `Parser` class to obtain a `SessionDeleteCommand` object.
3. The `SessionDeleteCommand#run` method is then invoked to run the `session /delete` command.
4. The list of sessions are stored in a `Profile` object, hence `Manager#getProfile` is called
   before the list of sessions can be retrieved.
5. Once the `Profile` object is returned, `Profile#getSession` is called to retrieve the `Session` object with the specified 
session unique identifier from the list of sessions.
   * If a `Session` object with the specified session unique identifier cannot be found, it prints the error message and returns control to `SplitLah`.
   * Else the `Session` object with the specified session unique identifier is returned.
6. The `Profile#removeSession` method is then called to remove the `Session` object from the list of sessions stored in `Profile` object.
7. After the session is removed from the `Profile` object, `Manager#saveProfile` is called to save the changes to the local storage file.
8. The `SessionDeleteCommand` class then prints a message indicating that a session has been successfully created.

### View a session
**API reference:** [`SessionViewCommand.java`](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/command/SessionViewCommand.java)

The sequence diagram below models the interactions between various entities in SplitLah
when the user invokes the `session /view` command.
<br>
<br>
![View Session Sequence Diagram Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/SessionViewCommand.drawio.png)
<br>
<br>
The general workflow of the `session /view` command is as follows:
1. The user input provided is passed to `SplitLah`.
2. `SplitLah` then parses the input by using methods in the `Parser` class to obtain a `SessionViewCommand` object.
3. `SessionViewCommand#run` method is then invoked to run the `session /view` command.
4. The list of sessions are stored in a `Profile` object, hence `Manager#getProfile` is called.
5. The `SessionViewCommand` object then runs the `Profile#getSession` method to retrieve the session represented
   by the session unique identifier provided.
   1. If the session with the requested session unique identifier does not exist, an error message is printed out with 
      `TextUI#printlnMessage`.
   2. Else, a `String` object representing the details of the requested session is retrieved using the 
      `Session#toString` method. The `String` object is then printed out with `TextUI#printlnMessageWithDivider`.

### List sessions
**API reference:** [`SessionListCommand.java`](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/command/SessionListCommand.java)

The sequence diagram below models the interactions between various entities in SplitLah
when the user invokes the `session /list` command.
<br>
<br>
![List Sessions Sequence Diagram Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/SessionListCommand.drawio.png)
<br>
<br>
The general workflow of the `session /list` command is as follows:
1. The user input provided is passed to `SplitLah`.
2. `SplitLah` then parses the input by using methods in the `Parser` class to obtain a `SessionListCommand` object.
3. `SessionListCommand#run` method is then invoked to run the `session /list` command.
4. The list of sessions are stored in a `Profile` object, hence `Manager#getProfile` is called which returns a `Profile`
   object.
5. Once the profile is retrieved, `SessionListCommand` runs the `Profile#getSessionListSummaryString` method.
   1. If the session list in the profile is empty, the `Profile` class returns a `String` object containing an error 
      message.
   2. Otherwise, a `String` object representing a table summarising the list of sessions in the 
      profile is returned.
6. The `String` object retrieved is then printed out with `TextUI#printlnMessage`.

### Settle a session
**API reference:** [`SessionSummaryCommand.java`](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/command/SessionSummaryCommand.java)

The sequence diagram below models the interactions between various entities in SplitLah
when the user invokes the `session /summary` command
<br>
<br>
![Session Summary Sequence Diagram Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/SessionSummaryCommand.drawio.png)
<br>
<br>
The general workflow of the `session /summary` command is as follows:
1. The user input provided is passed to `SplitLah`.
2. `SplitLah` then parses the input by using methods in the `Parser` class to obtain a `SessionSummaryCommand` object.
3. A `SessionSummaryCommand#run` method is then invoked to run the `session /summary` command.
4. The `Profile` object that stores all sessions is obtained with the `Manager#getProfile` method.
5. The `TextUI` object that handles all reading and printing operations with the user interface
   is obtained with the `Manager#getUi` method.
6. From the `Profile` object obtained, the `Profile#getSession` method is invoked with the session unique identifier
   parsed from the user input to obtain the `Session` object that we want to settle all transactions for.
7. An `ArrayList<Person>` object containing all persons participating in the session is then obtained with
   the `Session#getPersonList` method.
8. With the list of participants, an `ArrayList<PersonCostPair>` object is obtained with the
   `SessionSummaryCommand#getPersonCostPairList` method.
   * This method first calculates all costs borne by each person in the list of participants, 
     then creates a `PersonCostPair` object that stores both the `Person` object and the cost borne by that person.
9. With both the `Session` object and the `ArrayList<PersonCostPair>` object, the method
   `SessionSummaryCommand#processAllTransactions` is called.
   * This method sorts all the `PersonCostPair` objects by their cost, then matches each debt to be paid with a debt
     to be collected between all persons. Each of such matches is referred to as a transaction.
     The matching process is repeated until no more transactions can be made,
     i.e. all debts are paid and all debts are collected.
   * A `String` object containing information regarding all transactions that have to be made is then returned.
   * If no transactions are required to be made, a message explaining that no transactions are required to be made
     is returned instead.
   * For the sake of brevity, the specifics of the method `SessionSumamryCommand#processAllTransactions` is omitted
     from the sequence diagram.
10. Finally, with the `TextUI` object, the method `printlnMessageWithDivider` is called to print the message
    obtained from the `SessionSummaryCommand#processAllTransactions` method.

### Add an activity
### Remove an activity
### View an activity

### List activities
**API reference:** [`ActivityListCommand.java`](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/command/ActivityListCommand.java)

The sequence diagram below models the interactions between various entities in SplitLah
when the user invokes the `activity /list` command.
<br>
<br>
![List Activity Sequence Diagram Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/ActivityListCommand.drawio.png)
<br>
<br>
The general workflow of the `activity /list` command is as follows:
1. The user input provided is passed to `SplitLah`.
2. `SplitLah` then parses the input by using methods in the `Parser` class to obtain a `ActivityListCommand` object.
3. `ActivityListCommand#run` method is then invoked to run the `activity /list` command.
4. The list of activities are stored in a `Profile` object, hence `Manager#getProfile` is called.
5. To retrieve the session containing the activity list from the `Profile` object, `Profile#getSession` is executed, returning a session containing all the activities to be listed.
6. Once the session is retrieved, `ActivityListCommand` class will run `Session#getActivityListSummaryString`.
   1. If the activity list in the session is empty, the Session class will return a `String` object containing an error message.
   2. If it's not empty, a `String` object representing a table summarising the list of activities in the session will be returned. 
7. Finally, the method `printlnMessageWithDivider` is called to print the message returned.

### Add a group
**API reference:** [`GroupCreateCommand.java`](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/command/GroupCreateCommand.java)

The sequence diagram below models the interactions between various entities in SplitLah
when the user invokes the `group /create` command.
<br>
<br>
![Create Group Sequence Diagram Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/GroupCreateCommand.drawio.png)
<br>
<br>
The general workflow of the `group /create` command is as follows:
1. The user input provided is passed to `SplitLah`.
2. `SplitLah` then parses the input by using methods in the `Parser` class to obtain a `GroupCreateCommand` object.
3. A `GroupCreateCommand#run` method is then invoked to run the `group /create` command.
4. Once the command starts to run, `GroupCreateCommand` class checks if there are duplicates in the name list.
5. If there are duplicates, a message indicating that name list contains duplicates is printed using `TextUi#printlnMessage`. 
6. If there are no duplicates, `GroupCreateCommand` class converts each of the names into a `Person` object.
7. `GroupCreateCommand` class then checks if there is an existing group with the same group name. 
8. If existing groups with the group name are found, a message indicating that another group with the same name is printed using `TextUi#printlnMessage`.
9. `GroupCreateCommand` class create a new `Group` object using the group name, name list, and group unique identifier. 
10. The list of `Group` objects are managed by a `Profile` object, hence `Manager#getProfile#addGroup` is called to store the new `Group` object in the `Profile` object.
11. The `GroupCreateCommand` class then prints a message indicating that a group has been successfully created.

### Remove a group
**API reference:** [`GroupDeleteCommand.java`](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/command/GroupDeleteCommand.java)

The sequence diagram below models the interactions between various entities in SplitLah
when the user invokes the `group /delete` command.
<br>
<br>
![Delete Groups Sequence Diagram Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/GroupDeleteCommand.drawio.png)
<br>
<br>

### View a group
**API reference:** [`GroupViewCommand.java`](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/command/GroupViewCommand.java)

The sequence diagram below models the interactions between various entities in SplitLah
when the user invokes the `group /view` command.
<br>
<br>
![View Groups Sequence Diagram Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/GroupViewCommand.drawio.png)
<br>
<br>
The general workflow of the `group /view` command is as follows:
1. The user input provided is passed to `SplitLah`.
2. `SplitLah` then parses the input by using methods in the `Parser` class to obtain a `GroupViewCommand` object.
3. `GroupViewCommand#run` method is then invoked to run the `group /view` command.
4. The list of groups are stored in a `Profile` object, hence `Manager#getProfile` is called before a group within 
   the list of groups can be retrieved.
5. The `GroupViewCommand` object runs the `Profile#getGroup` method to retrieve the group represented by the
   group unique identifier provided.
   1. If the group with requested group unique identifier does not exist, an error message is printed out with
      `TextUI#printlnMessage`.
   2. Else, the `String` object representing the details of the requested group is retrieved using the `Group#toString`
      method. The `String` object is then printed out with `TextUI#printlnMessageWithDivider`.

### List groups
**API reference:** [`GroupListCommand.java`](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/command/GroupListCommand.java)

The sequence diagram below models the interactions between various entities in SplitLah
when the user invokes the `group /list` command.
<br>
<br>
![List Groups Sequence Diagram Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/GroupListCommand.drawio.png)
<br>
<br>
The general workflow of the `group /list` command is as follows:
1. The user input provided is passed to `SplitLah`.
2. `SplitLah` then parses the input by using methods in the `Parser` class to obtain a `GroupListCommand` object.
3. `GroupListCommand#run` method is then invoked to run the `group /list` command.
4. The list of groups are stored in a `Profile` object, hence `Manager#getProfile` is called
   before the list of groups can be retrieved.
5. The `GroupListCommand` object runs the `Profile#getGroupListSummaryString` method to retrieve a `String` object
   representing the summaries of the groups stored.
   1. If there are no groups stored in the `Profile` object, a `String` object representing an empty list of groups is
      returned.
   2. Else, the `Profile` objects instantiates a new `TableFormatter` object and loops through the list of groups,
      calling `TableFormatter#addRow` for each group to create a table with the summary of each group. A `String` object
      representing the table is then returned.
6. The `String` object retrieved is printed out with `TextUI#printlnMessage`.

## Product scope
### Target user profile
* Budget conscious.
* Prefers to use desktop applications over other types of applications.
* Are able to type relatively fast.
* Comfortable using CLI applications.

### Value proposition
SplitLah will help the user keep track of expenditures made during each group outing that they go on and help the user 
calculate what transactions that they should make in order to ensure that everyone pays equally for the activities that 
they engage in during the outings.

## User Stories

| Version | As a ...              | I want to ...                        | So that I can ...                                                           |
|---------|-----------------------|--------------------------------------|-----------------------------------------------------------------------------|
| v1.0    | New user              | see usage instructions               | refer to them when I forget how to use the application                      |
| v1.0    | Budget conscious user | create sessions                      | record the transactions of the outing                                       |
| v1.0    | Budget conscious user | create activities                    | able to track the expenditure for each activity                             |
| v1.0    | Budget conscious user | view an existing session             | view the full details of a session previously created                       |
| v1.0    | Budget conscious user | view an existing activity            | view the cost breakdown of who paid and who was involved in the activity    |
| v1.0    | Budget conscious user | list all existing sessions           | view all sessions previously created                                        |
| v1.0    | Budget conscious user | list all activities in a session     | view all the activities that happened in the session                        |
| v1.0    | Budget conscious user | settle all transactions of a session | see a summary of who needs to pay what amount to who for the entire session |
| v1.0    | User                  | exit the application                 | stop tracking                                                               |

## Non-Functional Requirements
1. The application should be able to work in any operating systems with `Java 11` installed.
2. 

## Glossary

