---
title: Developer Guide
---

<p align="center"><img alt="logo" src="https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/pngLogo.png"></p>

# Developer Guide
## Contents
* [Acknowledgements](#acknowledgements)
* [Introduction](#introduction)
    * [How to use this developer guide](#how-to-use-this-developer-guide)
* [Design](#design)
    * [Architecture](#architecture)
    * [Manager Component](#manager-component)
    * [Profile Component](#profile-component)
    * [TextUI Component](#textui-component)
    * [Storage Component](#storage-component)
    * [Parser Component](#parser-component)
    * [Command Component](#command-component)
* [Implementation](#implementation)
  * [Parsing of commands](#parsing-of-commands)
  * [Session Commands](#session-commands)
    * [Add a session](#add-a-session)
    * [Remove a session](#remove-a-session)
    * [Edit a session](#edit-a-session)
    * [View a session](#view-a-session)
    * [List all sessions](#list-sessions)
    * [Settle a session](#settle-a-session)
  * [Activity Commands](#activity-commands)
    * [Add an activity](#add-an-activity)
    * [Remove an activity](#remove-an-activity)
    * [Edit an activity](#edit-an-activity)
    * [View an activity](#view-an-activity)
    * [List all activities](#list-activities)
  * [Group Commands](#group-commands)
    * [Add a group](#add-a-group)
    * [Remove a group](#remove-a-group)
    * [Edit a group](#edit-a-group)
    * [View a group](#view-a-group)
    * [List all groups](#list-groups)
* [Appendix: Requirements](#appendix-requirements)
  * [Project Scope](#product-scope)
      * [Target user profile](#target-user-profile)
      * [Value proposition](#value-proposition)
  * [User stories](#user-stories)
  * [Non-functional requirements](#non-functional-requirements)
  * [Glossary](#glossary)
* [Appendix: Instructions for Manual Testing](#instructions-for-manual-testing)

## Acknowledgements
We would like to acknowledge [Address Book (Level-3)](https://github.com/se-edu/addressbook-level3) for providing much
insight as to how we could improve SplitLah and develop SplitLah while abiding by Object-Oriented Programming principles.
We also referenced their [User Guide(UG)](https://se-education.org/addressbook-level3/UserGuide.html)
and [Developer Guide(DG)](https://se-education.org/addressbook-level3/DeveloperGuide.html) to structure SplitLah's documentation.

## Introduction
This guide's purpose is to explain the internal workings of SplitLah, so that future engineers can understand the application's implementation in detail and continue working on the project with ease.

### How to use this developer guide
* SplitLah is divided into **six components**: [Manager Component](#manager-component),
[Profile Component](#profile-component), [TextUI Component](#textui-component),
[Storage Component](#storage-component), [Parser Component](#parser-component) and [Command Component](#command-component)
* Each component has its own section in this guide explaining them in detail.
* This [diagram](#interaction-between-components) shows the inner workings of how each component interacts with each other.
* SplitLah supports a total of **18 commands**. However, the `Help` and `Exit` commands are not explained in detail.
* Please refer to the [User Guide](https://ay2122s2-cs2113t-t10-1.github.io/tp/UserGuide.html) to find out how to use each command.
* Please refer to the [implementation section](#implementation) to find out how each command is designed and implemented in SplitLah.
* Each command's section in this guide contains an API reference link encased in `code block` that brings you to the main source code responsible for executing the command.

## Design
### Architecture
![Application Diagram Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/ArchitectureDiagram.drawio.png)

The *Architecture Diagram* shown above illustrates the high-level design of the SplitLah application.

**Overview of components**
* `SplitLah [Main]`
    * On app launch: Creates an instance of a `Manager` and runs the command loop.
* `Manager`
    * On creation: Initializes the `Profile`, `TextUI` and `Storage` components.
    * On run: Loads data from `Storage`, receives user input from `TextUI` and uses `Parser` to parse user input into application.
* `Profile`
    * Handles cached data within run time of application.
    * Manages and stores the list of `session` and `group` objects for the application
    * Stores the data cached within the application into `Storage` whenever a change is made.
* `TextUI`
    * Handles the reading of user input and the printing of the application's output to the user.
* `Storage`
    * Handles storage operations of the application.
* `Parser`
    * Handles input parsing and determines which command to run.
* `Command`
    * Defines how a command is executed.

### Interaction between components
![Component Interaction Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/ComponentInteraction.drawio.png)
<br>
The *Component Interaction Diagram* shows the inner workings of how each component in SplitLah interacts.
The diagram depicts a scenario where a user attempts to create a session.


### SplitLah Component
![SplitLah Component Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/SplitLahComponent.drawio.png)
<br>
The `SplitLah` component is the application's main class. Its job is to initialize an instance of `Manager` when the
application starts. After initialization, it proceeds to run a loop which prompts the user for an input.
When it receives an input from the user, it invokes the `Parser` and retrieves a command corresponding to the input for SplitLah
to run. Upon retrieving and running the `Exit` command, the application ends the loop and terminates.


### Manager Component
![Manager Component Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/ManagerComponent.drawio.png)
<br>
The `Manager` class is initialized by the `SplitLah` class (the main class) when the application starts.
It stores the `Profile`, `TextUI` and `Storage` objects. The `Profile` class helps to manage all data accesses 
throughout the lifetime of the application while the `Storage` class helps to save what the `Profile` class has captured. 
As for the `TextUI` class, it serves as an interface to read user inputs and to print application outputs.

### Profile Component
![Profile Component Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/Profile%20Component.drawio.png)
<br>
The `Profile` component is responsible for all data management and accesses within the lifetime of `SplitLah`.

It consists of the `Profile`, `Session`, `Activity`, `PersonList`, `Person`, `ActivityCost`
and `Name` classes.
* The `Profile` class serves as a container and holds a list of all `Session` and `Group` objects created by the user.<br>
  It also keeps track of and issues new _unique identifiers_ for the creation of `Session`, `Activity` and `Group` objects.
* The `Session` class stores a list of `Activity` objects that are created within a `Session` object as well as a
  `PersonList` object that represents a collection of all participants in the session.<br>
  It also holds an _optional_ `Group` object (explained in detail in the following point).
  * A `Session` object is identified by a _session unique identifier_.
* The `Group` class holds a single `PersonList` object and represents a collection of persons.<br>
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
The `Storage` class is initialised by the `Manager` class when the application starts. 
Upon initialization, the `Storage` class checks if a save file already exists. 
A save file records user data related to SplitLah even after the application is closed.
* If a save file is found, the `Storage` class loads data from it into the `Profile` object managed by the `Manager` object.
* Else a new save file is created and an empty `Profile` object is created instead.

When a command updates any attributes of the `Profile` object, the changes are updated to the save file. These commands are:
* `SessionCreateCommand`, `SessionDeleteCommand`, `SessionEditCommand`
* `ActivityCreateCommand`, `ActivityDeleteCommand`, `ActivityEditCommand`
* `GroupCreateCommand`, `GroupDeleteCommand`, `GroupEditCommand`

Once the commands finishes executing, `Manager#saveProfile` is called to update the save file.<br>

For example:
  1. The user enters `session /create /n Class outing /d 31-03-2022 /pl Alice bob`
  2. SplitLah recognizes this as a `SessionCreateCommand` and creates the new session. 
  3. As this updates the list of sessions stored in the `Profile` object,
    `Manager#saveProfile` is called to update the save file with the newly created session.

All classes associated with the `Profile` component implements the `Serializable` class. This allows the `Storage` class 
to write all the data stored in the `Profile` component into the save file without having to pre-process the data.

### Parser Component
![Parser Component Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/ParserComponent.drawio.png)
<br>
The `Parser` component consists of the `Parser` class, `ParserUtils` class, `ParserErrors` class,
as well as the `CommandParser` class and its subclasses.<br>
* The `Parser` class provides methods to parse commands from the user and
  return a `Command` object representing an instruction that the user has for SplitLah.<br>
* The `ParserUtils` class provide supporting methods to parse individual arguments from the user input,
  and `ParserErrors` class provide methods to produce custom error messages for the `Parser` component.<br>
* The subclasses of `CommandParser` serve to parse all arguments of a user input to create an object of a corresponding
  subclass of the `Command` class.

The general workflow of the `Parser` component is as follows:
1. When required to parse for a command, the running `SplitLah` object passes a `String` object containing
   the user input to `Parser` class.
2. `Parser` class instantiates a new `XYZCommandParser` object corresponding to the user input 
   and passes the user input to it.
   (`XYZCommand` is a placeholder for specific subclass of the `Command` class, e.g. `SessionCreateCommand`)
3. The `XYZCommandParser` object then uses parse methods from `ParserUtils` class to extract all the
   arguments from the user input.
   * Each of these parse methods in `ParserUtils` class calls other utility methods within the class 
     to return a parsed value.
4. All relevant arguments that are parsed are used to create a new `XYZCommand `object to return to the `Parser` class.
5. The created `XYZCommand` object is then returned to the `SplitLah` object to run.

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

<hr>

## Implementation

### Parsing of Commands
**API reference:** [`Parser.java`](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/parser/Parser.java),
[`ParserUtils.java`](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/parser/ParserUtils.java)

The sequence diagram below models the interactions between various entities within the Parser component and
the Command component when any user input is provided to SplitLah.
<br>
<br>
![Reference Frame Parser Sequence Diagram](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/RefParser.drawio.png)
<br>
<br>
1. When `SplitLah` reads a user input, `SplitLah` calls the `Parser#getCommand` method and passes the
   user input as the argument.
2. `Parser` class decomposes the user input into two separate components, the _command type_
   and the _remaining arguments_.
   With the two methods `Parser#getCommandType` and `Parser#getRemainingArguments`,
   `session /create /n Class Outing /d 15-03-2022 /pl Alice Bob` is parsed separately as
   `session /create` and `/n Class Outing /d 15-03-2022 /pl Alice Bob`.
   1. If the _command type_ is invalid, the method `Parser#getCommandType` returns null to `Parser` class.
      As a result, an `InvalidCommand` object is created and returned to `SplitLah`.
   2. Else, `Parser` class validates the _command type_ and the _remaining arguments_ with
      `Parser#checkIfCommandIsValid`. If either the _command type_ or the _remaining arguments_ are invalid, an error
      message is returned by the method and an `InvalidCommand` object is returned to `SplitLah`.
3. `Parser` class creates the `XYZCommandParser` object for a `XYZCommand`. For example,
   for a _command type_ of `"session /create"`, a `SessionCreateCommandParser` object is instantiated.
   If `Parser` class does not recognise the _command type_, an `InvalidCommand` object is created and returned immediately.

   ![Reference Frame Command Parser Sequence Diagram](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/RefCommandParser.drawio.png)

4. `Parser` class calls the `getCommand` method of `XYZCommandParser`.
5. `XYZCommandParser` parses all _remaining arguments_ using `parseABC` methods from the `ParserUtils` class.
   (`parseABC` is a placeholder for specific methods in `ParserUtils` class, 
   e.g. `ParserUtils#parseName` and `ParserUtils#parseSessionId`)
   * For example, `SessionCreateCommandParser` has to call the `parsePersonList`, `parseGroupId`, `parseName` and
     `parseLocalDate` methods from `ParserUtils` class.
   * If an exception is encountered, `XYZCommandParser` handles the exception accordingly, and if necessary,
     throws an exception back to `Parser` class, resulting in an `InvalidCommand` object being created and returned.
6. `ParseUtils#parseABC` then calls `ParserUtils#getArgumentFromDelimiter` to extract the relevant argument from
   the _remaining arguments_ and returns a corresponding parsed object.
   * For example, `ParserUtils#parseName` returns a `String` object representing a name.
7. After all necessary information is parsed, `XYZCommandParser` instantiates a new `XYZCommand` object with
   all the parsed information.
8. Following that, the `XYZCommand` object is returned from `XYZCommandParser` to `Parser` class,
   and finally back to `SplitLah` to run.

## Session Commands

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
3. `SessionCreateCommand#run` method is invoked to run the `session /create` command.
4. Once the command runs, `SessionCreateCommand#run` method checks if there is an existing session with the same session name.
5. If an existing session with the specified session name is found, a message indicating that another session with the same name exists is printed using `TextUI#printlnMessage`.
6. The `SessionCreateCommand` class creates a new `Session` object using the session name, session date, and person list.
7. The list of `Session` objects are managed by a `Profile` object, hence `Manager#getProfile` is called to obtain the `Profile` object,
   which is used to call the `Profile#addSession` method in order to store the new `Session` object.
8. After the session is added to the `Profile` object, `Manager#saveProfile` is called to save the changes to the local storage file.
9. The `SessionCreateCommand` class then prints a message indicating that a session has been successfully created with `TextUI#printlnMessage`.

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
3. The `SessionDeleteCommand#run` method is invoked to run the `session /delete` command.
4. The list of sessions are stored in a `Profile` object, hence `Manager#getProfile` is called
   before the list of sessions can be retrieved.
5. Once the `Profile` object is returned, `Profile#getSession` is called to retrieve the `Session` object with the specified 
session unique identifier from the list of sessions.
   * If a `Session` object with the specified session unique identifier cannot be found, it prints the error message and returns control to `SplitLah`.
   * Else, the `Session` object with the specified session unique identifier is returned.
6. To remove the `Session` object from the list of sessions stored in `Profile` object, the `Profile#removeSession` method is invoked.
7. After the session is removed from the `Profile` object, `Manager#saveProfile` is called to save the changes to the local storage file.
8. The `SessionDeleteCommand` class then prints a message indicating that a session has been successfully deleted.

### Edit a session
**API reference:** [`SessionEditCommand.java`](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/command/SessionEditCommand.java)

The sequence diagram below models the interactions between various entities in SplitLah
when the user invokes the `session /edit` command.
<br>
<br>
![Edit Session Sequence Diagram Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/SessionEditCommand.drawio.png)
<br>
<br>
The general workflow of the `session /edit` command is as follows:
1. The user input provided is passed to `SplitLah`.
2. `SplitLah` then parses the input by using methods in the `Parser` class to obtain a `SessionEditCommand` object.
3. The `SessionEditCommand#run` method is invoked to run the `session /edit` command.
4. The list of sessions are stored in a `Profile` object, hence `Manager#getProfile` is called
   before the list of sessions can be retrieved.
5. Once the `Profile` object is returned, `Profile#getSession` is called to retrieve the `Session` object with the specified
   session unique identifier from the list of sessions.
   * If a `Session` object with the specified session unique identifier cannot be found, it prints the error message and returns control to `SplitLah`.
   * Else, the `Session` object with the specified session unique identifier is returned.
6. The details of how a session is updated are displayed in the reference diagram below.<br>
   ![Reference Frame Update Session Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/RefUpdateSession.png)
7. `SessionEditCommand#run` checks if there is an update for a new list of persons, new session or new session date.
   * If there is an update on the list of persons, `SessionEditCommand#getNewPersonList` is called to return a new list of persons to be stored. 
     * The method checks if the newly provided list of persons contains duplicated names.
       * If duplicated names are detected, an exception is thrown, an error message is printed and control is returned to `SplitLah`.
       * Else, it calls `PersonList#isSuperSet` to check if the newly supplied list of persons contains all existing persons in the session.
         * If `PersonList#isSuperSet` returns `false`, an exception is thrown, an error message is printed and control is returned to `SplitLah`.
         * Else, a new list of persons ready to be stored in the session is returned.
   * If there is an update on the session name, `SessionEditCommand#getNewSessionName` is called to return the new session name.
     * The method checks if the provided session name already exists in the list of sessions.
       * If the provided session name exists within the list of sessions, an exception is thrown, an error message is printed and control is returned to `SplitLah`.
       * Else, the provided session name is returned to be used as the updated name for the session
   * If there is an update on the session date, `Session#setDateCreated` is called to set the new session date.
   * After which, the necessary setter methods are called to update the session name and the list of persons for the session that is being edited.
8. After the session is edited, `Manager#saveProfile` is called to save the changes to the local storage file.
9. The `SessionEditCommand` class then prints a message indicating that a session has been successfully edited.

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
3. `SessionViewCommand#run` method is invoked to run the `session /view` command.
4. The list of sessions are stored in a `Profile` object, hence `Manager#getProfile` is called.
5. The `SessionViewCommand` object then runs the `Profile#getSession` method to retrieve the session represented
   by the session unique identifier provided.
   1. If the session with the requested session unique identifier does not exist, an error message is printed out with 
      `TextUI#printlnMessage`.
   2. Else, a `String` object representing the details of the requested session is retrieved using the 
      `Session#toString` method and printed out using `TextUI#printlnMessageWithDivider`.

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
3. `SessionListCommand#run` method is invoked to run the `session /list` command.
4. The list of sessions are stored in a `Profile` object, hence `Manager#getProfile` is called which returns a `Profile`
   object.
5. Once the profile is retrieved, `SessionListCommand` runs the `Profile#getSessionListSummaryString` method.
   1. If the session list in the profile is empty, the `Profile` class returns a `String` object containing an error 
      message.
   2. Else, a `String` object representing a table summarising the list of sessions in the 
      profile is returned.
6. Following that, the `String` object retrieved is printed out with `TextUI#printlnMessage`.

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
3. `SessionSummaryCommand#run` method is invoked to run the `session /summary` command.
4. The `Profile` object that stores all sessions is obtained with the `Manager#getProfile` method.
5. The `TextUI` object that handles all reading and printing operations with the user interface
   is obtained with the `Manager#getUi` method.
6. From the `Profile` object obtained, the `Profile#getSession` method is invoked with the session unique identifier
   parsed from the user input to obtain the `Session` object that we want to settle all transactions for.
7. The `Session#getPersonList` method is called to retrieve an `ArrayList<Person>` object containing all persons 
  participating in the session.
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
   * If no transactions are required to be made, a message explaining that no transactions are required to be made
     is returned.
   * Else, a `String` object containing information regarding all transactions that have to be made is returned.
   * For the sake of brevity, the specifics of the method `SessionSumamryCommand#processAllTransactions` is omitted
     from the sequence diagram.
10. Finally, with the `TextUI` object, the method `TextUI#printlnMessageWithDivider` is called to print the message
    obtained from the `SessionSummaryCommand#processAllTransactions` method.

## Activity Commands

### Add an activity
**API reference:** [`ActivityCreateCommand.java`](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/command/ActivityCreateCommand.java)

The sequence diagram below models the interactions between various entities in SplitLah
when the user invokes the `activity /create` command.
<br>
<br>
![Create Activity Sequence Diagram Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/ActivityCreateCommand.drawio.png)
<br>
<br>
The general workflow of the `activity /create` command is as follows:
1. The user input provided is passed to `SplitLah`.
2. `SplitLah` then parses the input by using methods in the `Parser` class to obtain a `ActivityCreateCommand` object.
3. `ActivityCreateCommand#run` method is then invoked to run the `activity /create` command.
4. Once the command runs, `ActivityCreateCommand#run` method checks if there are duplicate names in the involved list. 
   - If there are duplicate names in the involved list, a message indicating that there are duplicates is printed using `TextUI#printlnMessage`
     and control is given back to `SplitLah`.
5. The `ActivityCreateCommand` object updates the cost and cost list by invoking the `ActivityCreateCommand#updateCostAndCostList` method.
6. The`ActivityCreateCommand#run` method invokes the `Manager#getProfile` method to retrieve the `Profile` object which stores the list of sessions.
7. Then, `Profile#getSession` method is called to retrieve the `Session` object which the activity that the user wishes to delete is stored in.
    - If the session does not exist, a message indicating that there is no such session is printed using `TextUI#printlnMessage` and control is given back to `SplitLah`.
    - Else, the `Session` object that the activity is stored in is returned.
    - This process is omitted in the sequence diagram for the sake of brevity.
8. Other getter methods are then called to obtain the necessary parameters used to instantiate an Activity object. These getter methods are omitted in the sequence diagram.
9. The `ActivityCreateCommand` object then adds the respective costs to each `Person` object involved in the activity using the `ActivityCreateCommand#addAllActivityCost` method.
10. Using the updated details as parameters, the `ActivityCreateCommand` object instantiates an `Activity` object to represent the new activity.
11. Then, the `Session#addActivity` method is called to add the new `Activity` object into the list of activities.
12. After the activity is added to the `Session` object, `Manager#saveProfile` is called to save the changes to the local storage file.
13. The `Manager` object then runs `Storage#saveProfileToFile` to save the updated profile to the local storage file.
14. The `ActivityCreateCommand` object then prints a message indicating that an activity has been successfully created with `TextUI#printlnMessage`.

### Remove an activity
**API reference:** [`ActivityDeleteCommand.java`](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/command/ActivityDeleteCommand.java)

The sequence diagram below models the interactions between various entities in SplitLah
when the user invokes the `activity /delete` command.
<br>
<br>
![Delete Activity Sequence Diagram Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/ActivityDeleteCommand.drawio.png)
<br>
<br>
The general workflow of the `activity /delete` command is as follows:
1. The user input provided is passed to `SplitLah`.
2. `SplitLah` then parses the input by using methods in the `Parser` class to obtain a `ActivityDeleteCommand` object.
3. `ActivityDeleteCommand#run` method is then invoked to run the `activity /delete` command.
4. Once the command runs, `ActivityDeleteCommand#run` method invokes the `Manager#getProfile` method to retrieve the `Profile` object which stores the list of sessions.
5. The `Profile#getSession` method is called to retrieve the `Session` object which the activity that the user wishes to delete is stored in.
    - If the session does not exist, a message indicating that there is no such session is printed using `TextUI#printlnMessage` and control is given back to `SplitLah`.
    - Else, the `Session` object that the activity is stored in is returned.
6. Once the `Session` object is retrieved, the `Session#removeActivity()` method is invoked to remove the `Activity` object from the list of activities stored.
   - If the activity does not exist, a message indicating that there is no such activity is printed using `TextUI#printlnMessage` and control is given back to `SplitLah`.
   - Else, the `Activity` object is removed from the list of activities.
8. After the activity is removed from the `Session` object, `Manager#saveProfile` is called to save the changes to the local storage file.
9. The `Manager` object then runs `Storage#saveProfileToFile` to save the updated profile to the local storage file.
10. The `ActivityDeleteCommand` object then prints a message indicating that an activity has been successfully deleted with `TextUI#printlnMessage`.

### Edit an activity
**API reference:** [`ActivityEditCommand.java`](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/command/ActivityEditCommand.java)

The sequence diagram below models the interactions between various entities in SplitLah
when the user invokes the `activity /edit` command.
<br>
<br>
![Edit Activity Sequence Diagram Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/ActivityEditCommand.drawio.png)
<br>
<br>
`activity /edit` functions by deleting an existing activity and recreating it with the same `activityId`, but with 
edited details.
<br>

The general workflow of the `activity /edit` command is as follows:
1. The user input provided is passed to `SplitLah`.
2. `SplitLah` then parses the input using methods in the `Parser` class to obtain an `ActivityEditCommand` object.
3. `ActivityEditCommand#run` method is then invoked to run the `activity /edit` command.
4. Once the command runs, `ActivityEditCommand#run` method invokes the `Manager#getProfile` method to retrieve the `Profile` object which stores the list of sessions.
5. The `Profile#getSession` method is called to retrieve the `Session` object which the activity that the user wishes to edit is stored in.
    - If the session does not exist, a message indicating that there is no such session is printed using `TextUi#printlnMessage` and control is given back to `SplitLah`.
    - Else, the `Session` object that the activity is stored in is returned.
6. `Session#getActivity` is called on the `activityId` provided by the user to fetch the `Activity` object representing
   the activity to be edited. 
    - If the activity does not exist, a message indicating that there is no such activity is printed using `TextUi#printlnMessage` and control is given back to `SplitLah`.
    - Else, the `Activity` object to be edited is returned and stored temporarily as `oldActivity`.
7. `ActivityEditCommand#retrieveDetailsFromOldActivity` is called on `oldActivity` to retrieve existing activity details
   for delimiters not supplied by the user. This allows `ActivityEditCommand` to reuse existing details for details the user
   does not wish to edit.
8. `ActivityEditCommand#updateCostAndCostList` and `ActivityEditCommand#validateCostListAndInvolvedList` are called to 
   create a list of costs and validate them. These costs are necessary to recreate the activity after deleting it.
9. `ActivityEditCommand#addAllActivityCost` is called to add the respective costs to each `Person` object involved in the activity.
     - A dummy activity unique identifier that cannot be used by any other activity is when adding these costs.
10. `Session#removeActivity()` method is invoked to remove `oldActivity` from the list of activities stored.
11. A new `Activity` object is created using the new activity details and stored as `newActivity`.
12. `Session#addActivity` is called to add `newActivity` to the session.
13. `ActivityEditCommand#updateDummyActivityIdsInActivityCosts` is called on the session to update all dummy activity unique identifiers added by `ActivityEditCommand#addAllActivityCost` to
    the `activityId` of the old activity.
14. `Manager#saveProfile` is called to save the changes to the local storage file.
15. The `ActivityCreateCommand` object then prints a message indicating that an activity has been successfully edited with `TextUi#printlnMessage`.

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
6. Once the session is retrieved, `ActivityListCommand` class runs `Session#getActivityListSummaryString`.
   1. If the activity list in the session is empty, the Session class returns a `String` object containing an error message.
   2. If it's not empty, a `String` object representing a table summarising the list of activities in the session is returned. 
7. Finally, the method `TextUI#printlnMessageWithDivider` is called to print the message returned.

## Group Commands

### Add a group
**API reference:** [`GroupCreateCommand.java`](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/command/GroupCreateCommand.java)

The sequence diagram for `GroupCreateCommand` is omitted as it bears many similarities with [`SessionCreateCommand`](#add-a-session).<br>
The interactions of `GroupCreateCommand` with `Profile` and `Storage` classes are identical but the key differences lie in the arguments being parsed:
* `GroupCreateCommand` parses only the **name** and **list of persons**. It then creates a new `Group` object and adds it to the list of groups managed by the `Profile` class.

Please refer to the [sequence diagram](#add-a-session) of `SessionCreateCommand` for reference.

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
5. If there are duplicates, a message indicating that name list contains duplicates is printed using `TextUI#printlnMessage`. 
6. If there are no duplicates, `GroupCreateCommand` class converts each of the names into a `Person` object.
7. `GroupCreateCommand` class then checks if there is an existing group with the same group name. 
8. If existing groups with the group name are found, a message indicating that another group with the same name is printed using `TextUI#printlnMessage`.
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

### Edit a group

### View a group
**API reference:** [`GroupViewCommand.java`](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/command/GroupViewCommand.java)
The sequence diagram for `GroupViewCommand` is omitted as it bears many similarities with [`SessionViewCommand`](#view-a-session).<br>
The interactions of `GroupViewCommand` with the `Profile` class is identical but the key differences are as follows:
* `GroupViewCommand` parses the **group unique identifier** instead of the **session unique identifier**.
* It then displays the details of the `Group` object from the list of groups managed by the `Profile` class corresponding to the **group unique identifier**.

Please refer to the [sequence diagram](#view-a-session) of `SessionViewCommand` for reference.

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
The sequence diagram for `GroupListCommand` is omitted as it bears many similarities with [`SessionListCommand`](#list-sessions).<br>
The interactions of `GroupListCommand` with the `Profile` class is identical but the key differences lies in the objects that are used in the `Profile` class:
* `GroupListCommand` calls the method `Profile#getGroupListSummaryString` instead of `Profile#getSessionListSummaryString` which utilises the list of groups instead of the list of sessions.

Please refer to the [sequence diagram](#list-sessions) of `SessionListCommand` for reference.

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

<hr>

## Appendix: Requirements
## Product scope
### Target user profile
* Budget conscious.
* Prefers to use desktop applications over other types of applications.
* Are able to type relatively fast.
* Comfortable using CLI applications.

### Value proposition
SplitLah helps the user keep track of expenditures made during each group outing that they go on and help the user 
calculate what transactions that they should make in order to ensure that everyone pays equally for the activities that 
they engage in during the outings.

## User Stories

| Version | As a ...                   | I want to ...                        | So that I can ...                                                                  |
|---------|----------------------------|--------------------------------------|------------------------------------------------------------------------------------|
| v1.0    | New user                   | see usage instructions               | refer to them when I forget how to use the application                             |
| v1.0    | Budget conscious user      | create sessions                      | record the transactions of the outing                                              |
| v1.0    | Returning User             | delete sessions                      | remove unnecessary sessions                                                        |
| v1.0    | Budget conscious user      | create activities                    | able to track the expenditure for each activity                                    |
| v1.0    | Returning User             | delete activities                    | remove unnecessary activities                                                      |
| v1.0    | Returning user             | view an existing activity            | view the cost breakdown of who paid and who was involved in the activity           |
| v1.0    | Budget conscious user      | list all existing sessions           | view all sessions previously created                                               |
| v1.0    | Budget conscious user      | list all activities in a session     | view all the activities that happened in the session                               |
| v1.0    | Budget conscious user      | settle all transactions of a session | see a summary of who needs to pay what amount to who for the entire session        |
| v2.0    | Careless User              | edit sessions                        | amend a mistake previously made when creating a session                            |
| v2.0    | Returning user             | view an existing session             | view the full details of a session previously created                              |
| v2.0    | Careless User              | edit activities                      | amend a mistake previously made when creating an activity                          |
| v2.0    | User with a lot of friends | create groups                        | I can create sessions with groups instead of listing the person names out manually |
| v2.0    | Returning User             | delete groups                        | remove unnecessary groups                                                          |
| v2.0    | Careless User              | edit groups                          | amend a mistake previously made when creating a groups                             |
| v2.0    | Returning User             | view groups                          | view the full details of a group previously created                                |
| v2.0    | User with a lot of friends | list groups                          | view all groups previously created                                                 |

## Non-Functional Requirements
1. The application should be able to work on any operating systems with `Java 11` installed.
2. The application should be responsive.
3. The application should be usable by a novice who may not be well versed with a Command Line Interface (CLI).
4. Ths application should be usable by a novice who has never used an application to split bills.

## Glossary
| Terms    | Definition                                                                                                                                                                                                                                                                                                  |
|----------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Manager  | A Manager manages and stores 3 different objects, namely the `Profile`, `TextUI` and `Storage` objects.                                                                                                                                                                                                     |
| Profile  | A Profile is responsible for all data management and accesses within the lifetime of the application. It serves as a container and holds a list of all `Session` and `Group` objects and keeps track of the unique identifiers to be issued upon the creation of `Session`, `Activity` and `Group` objects. |
| TextUI   | A TextUI is an user interface that the user sees on the CLI.                                                                                                                                                                                                                                                |
| Storage  | A Storage is in charge of saving and reading to and from the save file respectively.                                                                                                                                                                                                                        |
| Parser   | A Parser is responsible for making sense of the user inputs and processing them as commands for the application to run.                                                                                                                                                                                     |
| Command  | A Command is an object that performs a task that corresponds to the user input.                                                                                                                                                                                                                             |
| Session  | A session represents a group outing that involves a list of participants and spans an arbitrary period of time containing one or more activities.                                                                                                                                                           |
| Activity | An activity represents a single group activity and stores its name, costs and the name of the payer.                                                                                                                                                                                                        |
| Group    | A group represents one or more individuals. The sole purpose of a group is to quickly identify a group of individuals without having to manually enter their details one by one when creating a session.                                                                                                    |
| API      | An Application Programming Interface (API) specifies the interface through which other programs can interact with a software component.                                                                                                                                                                     |

<hr>

## Appendix: Instructions for Manual Testing
This section includes instructions to test SplitLah manually.

> **💡 Note:**
> 
> These instructions only provide a starting point for testers to work on; testers are free to do more _exploratory_ testing.

### Overview
* [Launch and Shutdown](#launch-and-shutdown)
* [Session Testing](#session-testing)
* [Activity Testing](#activity-testing)
* [Group Testing](#group-testing)
* [Storage Testing](#storage-testing)

### Launch and Shutdown
<hr>

#### Initial Launch
1. Ensure that Java 11 or above is installed.
2. Download the latest .jar version of SplitLah from [here](https://github.com/AY2122S2-CS2113T-T10-1/tp/releases)
3. Copy the file to the folder you wish to use as a home folder for SplitLah.
4. Open a terminal and set the working directory to the home folder.
5. Start SplitLah by executing `java -jar splitlah.jar` in the terminal.
6. When SplitLah has successfully launched a welcome message should appear.
   For the first launch, SplitLah should mention that no save files were found and loaded.

#### Shutting Down
1. When SplitLah is awaiting user input, enter `exit` to terminate the application.
2. A farewell message should be printed as the SplitLah terminates.

<hr>

### Session Testing
<hr>

#### Creating a Session
> For details on the usage of `session /create` command, please refer to our [User Guide](https://ay2122s2-cs2113t-t10-1.github.io/tp/UserGuide.html#creating-a-session-session-create).

**Test Cases:**

Test Scenario 1: No sessions are currently stored in the application. <br>
1. Test Command: `session /create /n Test1 /pl Person1 Person2 /d 10-04-2022` <br>
   Expected: A success message should be printed along with the details of the session as provided in the command.
2. Test Command: `session /create /n Test2 /pl Person1 Person2 /d today`<br>
   Expected: A success message is printed along with the details of the session as provided in the command.

Test Scenario 2: There is a session named Test1 currently stored in the application. <br>
1. Test Command: `session /create /n Test1 /pl Person1 Person2 /d 10-04-2022` <br>
   Expected: An error message is printed indicating a session with the same name exists within the application.


<hr>

#### Deleting a Session
> For details on the usage of `session /delete` command, please refer to our [User Guide](https://ay2122s2-cs2113t-t10-1.github.io/tp/UserGuide.html#deleting-a-session-session-delete).
<hr>

#### Editing a Session
> For details on the usage of `session /edit` command, please refer to our [User Guide](https://ay2122s2-cs2113t-t10-1.github.io/tp/UserGuide.html#editing-a-session-session-edit).
<hr>

#### Settling a Session
> For details on the usage of `session /summary` command, please refer to our [User Guide](https://ay2122s2-cs2113t-t10-1.github.io/tp/UserGuide.html#settling-all-transactions-for-a-session-session-summary).

**Test Cases:**

Test Scenario 1: No sessions are currently stored in the application. 
* Test Command: `session /summary /sid 1` <br>
   Expected: An error message should be printed, indicating that no session are stored in SplitLah.

Test Scenario 2: Only a single session with a session unique identifier of `1` exists in the application after creating
a single session with the [`session /create`](https://ay2122s2-cs2113t-t10-1.github.io/tp/UserGuide.html#creating-a-session-session-create) command.
1. Test Command: `session /summary`<br>
   Expected: An error message should be printed, indicating that the `/sid` delimiter is missing from the input.
2. Test Command: `session /summary /sid`<br>
   Expected: An error message should be printed, indicating that an argument following the `/sid` delimiter is missing from the input.
3. Test Command: `session /summary /sid apple`<br>
   Expected: An error message should be printed, indicating that an integer argument should be provided following the `/sid` delimiter.
4. Test Command: `session /summary /sid 2`<br>
   Expected: An error message should be printed, indicating that a session with the specified session unique identifier was not found.
5. Test Command: `session /summary /sid 1`<br>
   Expected: A summary indicating the transactions that have to be made to settle all debts of the session with a 
   session unique identifier of `1` should be printed.
<hr>

#### Viewing a Session
> For details on the usage of `session /view` command, please refer to our [User Guide](https://ay2122s2-cs2113t-t10-1.github.io/tp/UserGuide.html#viewing-a-session--session-view)

**Test Cases:**

Test Scenario 1: No sessions are currently stored in the application.
* Test Command: `session /view /sid 1` <br>
  Expected: An error message should be printed, indicating that no session are stored in SplitLah.

Test Scenario 2: Only a single session with a session unique identifier of `1` exists in the application after creating
a single session with the [`session /create`](https://ay2122s2-cs2113t-t10-1.github.io/tp/UserGuide.html#creating-a-session-session-create) command.
1. Test Command: `session /view`<br>
   Expected: An error message should be printed, indicating that the `/sid` delimiter is missing from the input.
2. Test Command: `session /view /sid`<br>
   Expected: An error message should be printed, indicating that an argument following the `/sid` delimiter is missing from the input.
3. Test Command: `session /view /sid apple`<br>
   Expected: An error message should be printed, indicating that an integer argument should be provided following the `/sid` delimiter.
4. Test Command: `session /view /sid 2`<br>
   Expected: An error message should be printed, indicating that a session with the specified session unique identifier was not found.
5. Test Command: `session /view /sid 1`<br>
   Expected: The full details of the session with a session unique identifier of `1` should be printed.
<hr>

#### Listing all Sessions
> For details on the usage of `session /list` command, please refer to our [User Guide](https://ay2122s2-cs2113t-t10-1.github.io/tp/UserGuide.html#listing-all-sessions-session-list)

**Test Cases:**

Test Scenario 1: No sessions are currently stored in the application.
* Test Command: `session /list` <br>
  Expected: An error message should be printed, indicating that no session are stored in SplitLah.

Test Scenario 2: At least 1 session exists in the application.
* Test Command: `session /list`<br>
  Expected: A table summarising the details of all existing sessions should be printed.

<hr>

### Activity Testing
<hr>

#### Creating an Activity
> For details on the usage of `activity /create` command, please refer to our [User Guide](https://ay2122s2-cs2113t-t10-1.github.io/tp/UserGuide.html#creating-an-activity-activity-create).
<hr>

#### Deleting an Activity
> For details on the usage of `activity /delete` command, please refer to our [User Guide](https://ay2122s2-cs2113t-t10-1.github.io/tp/UserGuide.html#deleting-an-activity-activity-delete).
<hr>

#### Editing an Activity
> For details on the usage of `activity /edit` command, please refer to our [User Guide](https://ay2122s2-cs2113t-t10-1.github.io/tp/UserGuide.html#editing-an-activity-activity-edit).
<hr>

#### Viewing an Activity
> For details on the usage of `activity /view` command, please refer to our [User Guide](https://ay2122s2-cs2113t-t10-1.github.io/tp/UserGuide.html#viewing-an-activity-activity-view).
<hr>

#### Listing all Activities
> For details on the usage of `activity /list` command, please refer to our [User Guide](https://ay2122s2-cs2113t-t10-1.github.io/tp/UserGuide.html#listing-all-activities-in-a-session-activity-list).
<hr>

### Group Testing
<hr>

#### Creating a Group
> For details on the usage of `group /create` command, please refer to our [User Guide](https://ay2122s2-cs2113t-t10-1.github.io/tp/UserGuide.html#creating-a-group-group-create).
<hr>

#### Deleting a Group
> For details on the usage of `group /delete` command, please refer to our [User Guide](https://ay2122s2-cs2113t-t10-1.github.io/tp/UserGuide.html#deleting-a-group-group-delete).
<hr>

#### Editing a Group
> For details on the usage of `group /edit` command, please refer to our [User Guide](https://ay2122s2-cs2113t-t10-1.github.io/tp/UserGuide.html#editing-a-group-group-edit).
<hr>

#### Viewing a Group
> For details on the usage of `group /view` command, please refer to our [User Guide](https://ay2122s2-cs2113t-t10-1.github.io/tp/UserGuide.html#viewing-a-group-group-view).
<hr>

#### Listing all Groups
> For details on the usage of `group /list` command, please refer to our [User Guide](https://ay2122s2-cs2113t-t10-1.github.io/tp/UserGuide.html#listing-all-groups-group-list).
<hr>

### Storage Testing
<hr>
