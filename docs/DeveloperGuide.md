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
* `Main`
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

### Manager Component
![Manager Component Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/developerguide/ManagerComponent.drawio.png)
<br>
The `Manager` class is initialized by the `Main` class when the application starts.
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
The `Parser` class serves as a provides utility methods to parse commands and arguments from the user and
return a `Command` object representing an instruction that the user has for SplitLah.
It is the class in the `Parser` component that other external classes interact with.<br>
On the other hand, the `ParserUtils` class provide supporting methods for `Parser` class to properly run,
and `ParserErrors` class provide methods to produce custom error messages for the `Parser` package.

<!-- Insert Sequence Diagram -->

The general workflow of the `Parser` component is as follows:
1. When required to parse for a command, an input String object is passed to the `Parser#getCommand()` method.
2. The `Parser#getCommandType()` method is then called to decipher what command is to be carried out.
3. Then, `Parser#getRemainingArgument()` method is run to extract the arguments of the input command.
4. The arguments are passed to the specified `XYZCommand#prepare()` method if there are any arguments. 
Otherwise, the constructor is called. Both methods will create a new `XYZCommand` object. 
(`XYZCommand` is a placeholder for specific subclass of the `Command` class, e.g. `SessionCreateCommand`)
5. This `Command` object is then returned by `Parser#getCommand()` method.

### Command Component

## Implementation

### Add a session
### Remove a session
### View a session
### List sessions
### Settle a session
### Add an activity
### Remove an activity
### View an activity
### List activities
### Add a group
### Remove a group
### View a group
### List groups


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

