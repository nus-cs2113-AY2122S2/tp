# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design

### Architecture
![architecture diagram](images/architecture_diagram.png)
<br/> Fig 1 - Architecture Diagram

The Architecture Diagram above shows the high-level design of the application. The **main components**
consist of:
- `MMM`: Initialises the components in the correct sequence and connects them with each other. Also holds the user's
expenditures in memory.
- `Ui`: The User Interface of the application and deals with interaction with the user.
- `Parser`: Deals with making sense of user commands.
- `Commands`: The collection of all executable commands.
- `Storage`: Reads data from, and writes data to the hard disk.

By abstracting out closely related code into classes, it allows`MMM`to deal at a higher level, without worrying
about the lower level details. Higher cohesion is also achieved and coupling is minimized as each component is
only coupled to the main class,`MMM`.

The Sequence Diagram below shows an example of how the components interact with each other for the scenario 
where the user issues the command`add shoes 100`.

![sequence_diagram](images/sequence_diagram.png)
<br/> Fig 2 - Sequence Diagram

The sections below give more details for each component.

<br/>

### UI component
The source code can be found in [Ui.java](https://github.com/AY2122S2-CS2113T-T10-4/tp/blob/master/src/main/java/seedu/mindmymoney/Ui.java)

![ui_diagram](images/ui_diagram.png)
<br/> Fig 3 - Ui Diagram

The UI component consists of a`Ui`and`PrintStrings`class.

The UI component:
- Prints the welcome banner and message on startup, as well as a financial tip from the`PrintStrings`class.
- Prints`>`before the user's input, to help the user differentiate between their input and an output from the
application.
- Reads input from the user.

<br/>

### Parser component
The source code can be found in [Parser.java](https://github.com/AY2122S2-CS2113T-T10-4/tp/blob/master/src/main/java/seedu/mindmymoney/Parser.java)

![parser_diagram](images/parser_diagram.png)
<br/> Fig 4 - Parser Diagram

The Parser component consists of a`Parser`, `Functions`, `ExpenditureList` and `Expenditure`class

The Parser component:
- Receives user's input and splits it into the Command Type and Description using the`Functions`class.
- Uses the`itemList`, which is an`ExpenditureList`object, to instantiate a`Command`object based on the Command Type.
- Returns the`Command`object that can then be executed.

We pass in the`itemList`to the`Command`object instead of using a global variable to ease testing. This way, we can 
add, delete and update`Expenditure`entries in a new`itemList`during testing without affecting the actual`itemList`.

### Command component 
The source can be found in [command](https://github.com/AY2122S2-CS2113T-T10-4/tp/blob/master/src/main/java/seedu/mindmymoney/command)

![command_diagram](images/command_diagram.png)
<br/> Fig 5 - Command Diagram

The Command component consists of `Command` abstract class, `AddCommand`, `ByeCommand`, `DeleteCommand`, `HelpCommand`
, `ListCommand`and `UpdateCommand` class.

The Command component:
- `Command` class is an abstract class and `AddCommand`, `ByeCommand`, `DeleteCommand`, `HelpCommand`, `ListCommand`
and `UpdateCommand`extends the `Command` class.
- Parser.parseCommand() creates the different kinds of command that can be executed. Only 1 command object can be 
created at any given time.
- In each command class, there is a .executeCommand() method that executes what it needs to do and throws exceptions 
when an exception is caught.

### Storage component 
The source can be found in [Storage.java](https://github.com/AY2122S2-CS2113T-T10-4/tp/blob/master/src/main/java/seedu/mindmymoney/Storage.java)

![storage_diagram](images/storage_diagram.png)
<br/> Fig 6 - Storage Diagram

The Storage component consists of `Storage` class.

The Storage component:
- When the program starts up, the `MMM` class will initialise a `Storage` object. The `Storage` class consists of load() 
and save() functions. 
- At the same time, `MMM` will call the load function and load any data that is stored on local storage.
- When the program exits, `MMM` will call the save function and store remaining data onto local storage.


## Implementation

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
