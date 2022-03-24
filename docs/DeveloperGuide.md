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
The diagram depicts a scenario when a user attempts to create a session.



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
The `Profile` class holds the list of sessions and groups that have been created by the user. 
It also tracks the unique identifier for `Session`, `Activity` and `Group` classes. The `Profile`
class would return a unique identifier every time a new `Session`, `Activity` or `Group` is created. 

### TextUI Component

### Storage Component

### Parser Component
![Parser Component Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/ParserComponent.drawio.png)
<br>
The `Parser` component consists of the `Parser` class, `ParserUtils` class as well as the `ParserErrors` class.<br>
The `Parser` class provides utility methods to parse commands and arguments from the user and
return a `Command` object representing an instruction that the user has for SplitLah.
`Parser` class is the only class in the `Parser` component that other external classes interact with.<br>
On the other hand, the `ParserUtils` class provide supporting methods for `Parser` class to properly run,
and `ParserErrors` class provide methods to produce custom error messages for the `Parser` component.

<!-- Insert Sequence Diagram -->

The general workflow of the `Parser` component is as follows:
1. When required to parse for a command, an input String object is passed to the `Parser#getCommand()` method.
2. The `Parser#getCommandType()` method is then called to decide what command is to be carried out given the input from the user.
3. Then, `Parser#getRemainingArgument()` method is run to extract the arguments from the user input.
4. The arguments are then passed to the specified `XYZCommand#prepare()` method if there are any arguments. 
Otherwise, the constructor is called. Either methods will result in the creation of a new `XYZCommand` object. 
(`XYZCommand` is a placeholder for specific subclass of the `Command` class, e.g. `SessionCreateCommand`)
5. The created `XYZCommand` object is then returned by `Parser#getCommand()` method.

### Command Component

## Implementation

### Add a session
**API reference:** [`SessionCreateCommand.java`](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/command/SessionCreateCommand.java)

The sequence diagram below models the interactions between various entities in the system
when the user invokes the `session /create` command.
<br>
<br>
![Create Session Sequence Diagram Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/SessionCreateCommand.drawio.png)
<br>
<br>
The general workflow of the `session /create` command is as follows:

### Remove a session
### View a session
### List sessions
**API reference:** [`SessionListCommand.java`](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/command/SessionListCommand.java)

The sequence diagram below models the interactions between various entities in the system
when the user invokes the `session /list` command.
<br>
<br>
![List Sessions Sequence Diagram Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/SessionListCommand.drawio.png)
<br>
<br>
The general workflow of the `session /list` command is as follows:
1. The user input provided is passed to `Splitlah`.
2. `Splitlah` then parses the input by using methods in the `Parser` class to obtain a `SessionListCommand` object.
3. `SessionListCommand#run` method is then invoked to run the `session /list` command.
4. The list of sessions are stored in a `Profile` object, hence `Manager#getProfile` is called.
5. To retrieve the sessions from profile, `Profile#getSessionList` is executed,
   where a list of `Session` objects are returned.
6. Once the list is retrieved, `SessionListCommand` class checks if the list is empty.
  1. If the list is empty, a message indicating that the list is empty is printed
     using `TextUi#printlnMessage`.
  2. If the list is not empty, `SessionListCommand` will loop from the first to the second last session,
     calling `TextUi#printlnMessage()` to print out a brief overview of each session.
     Then, the last group is printed with a divider below it, using the method `TextUi#printlnMessageWithDivider()`.

### Settle a session
### Add an activity
### Remove an activity
### View an activity
### List activities
### Add a group
### Remove a group
### View a group
### List groups
**API reference:** [`GroupListCommand.java`](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/command/GroupListCommand.java)

The sequence diagram below models the interactions between various entities in the system
when the user invokes the `group /list` command.
<br>
<br>
![List Groups Sequence Diagram Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/GroupListCommand.drawio.png)
<br>
<br>
The general workflow of the `group /list` command is as follows:
1. The user input provided is passed to `Splitlah`.
2. `Splitlah` then parses the input by using methods in the `Parser` class to obtain a `GroupListCommand` object.
3. `GroupListCommand#run()` method is then invoked to run the `group /list` command.
4. The list of groups are stored in a `Profile` object, hence `Manager#getProfile()` is called
before the list of groups can be retrieved.
5. To retrieve the groups from the profile retrieved, `Profile#getGroupList()` method is executed,
where a list of `Group` objects are returned.
6. Once the list is retrieved, `GroupListCommand` class checks if the list is empty.
   1. If the list is empty, a message indicating that the list is empty is printed
   using the method `TextUi#printlnMessage()`.
   2. If the list is not empty, `GroupListCommand` will loop from the first to the second last group, 
   calling `TextUi#printlnMessage()` to print out the summary of each group.
   Then, the last group is printed with a divider below it, using the method `TextUi#printlnMessageWithDivider()`.

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

