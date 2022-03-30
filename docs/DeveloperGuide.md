# Developer Guide

Mod Happy is a command-line-based application that helps students manage their academics.

## Acknowledgements

- Some foundational source code was adapted from [addressbook-level2](https://github.com/se-edu/addressbook-level2).
- Google's [GSON library](https://github.com/google/gson) was used to facilitate serialisation and deserialisation of data stored in the data file.

## Design

The following architecture diagram provides a high level overview of the main components of Mod Happy and how they interact with one another.

![Class Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/ClassDiagrams/Components.puml)

The `Main` class is responsible for handling program initialisation, termination, as well as the application's main execution logic.

Mod Happy's components are summarised below:

* `UI`: Manages the application's text UI.
* `Parser`: Interprets user input and returns corresponding `Command` objects.
* `Command`: Handles command execution logic.
* `Data`: Manages module and task data in program memory.
* `Storage`: Reads data from, and writes data to Mod Happy's data storage files.

### UI Component

The `TextUi` class serves strictly as intermediary between the user and the program, and does not directly interact with any components other than the `Main` class. It fulfils the following roles:

- Listens for and grabs the user's input using a `Scanner`, and returns it to `Main` as a string for further processing.
- Displays any command results or status and error messages to the user.

### Parser Component

![Class Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/ClassDiagrams/Parser.puml)

The `Parser` component serves to interpret user input and construct the relevant `Command` objects to be returned to `Main` for execution later on. This component comprises the following classes:

* `Parser`, which is an abstract class serving as the parent of all other classes in this component. This class implements basic parsing functionality through the use of regular expressions and the built-in `Pattern` class. This functionality is modified by its child classes to suit their own purposes.
* `ModHappyParser`, which identifies the command word present in the user input and invokes the relevant command-specific parser.
* A variety of command-specific parsers (e.g. `AddParser` for the `add` command), referred to in this guide as `XYZParser` for simplicity. These classes perform further parsing on any command-specific arguments, constructing and returning the corresponding `XYZCommand` object.

> 📔 <span style="color:#00bb00">**NOTE:**</span>
> 
> `NoArgumentParser` is an exception to the above; instead of being associated with a single command type, it is responsible for handling all commands which do not accept any arguments.

The following details how the `Parser` component works at runtime:

1. A single `ModHappyParser` instance is initialised by `Main` during at the start of the program.
2. Each time the user inputs a command, `ModHappyParser`'s `parseCommand()` method with the input as the parameter.
3. `ModHappyParser` identifies the relevant command-specific parser `XYZParser` and passes on the remaining unparsed arguments to its `parseCommand()` method.
4. `XYZParser` parses the remaining command arguments and uses them to construct an `XYZCommand` instance, which is subsequently returned to `Main`.

### Data Component

The `Data` component is responsible for the storage and manipulation of tasks and modules as well as their associated attributes in program memory.

The following partial class diagram illustrates the structure of this component.

![Class Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/ClassDiagrams/Data.puml)

The `ModuleList` class serves as the main data storage class for the program, and is always instantiated when the program is started. It holds:
* A `Module` object representing the General Tasks list. This `Module` is instantiated upon `ModuleList`'s creation and is meant to be the "default" module for all uncategorised or miscellaneous tasks.
* An `ArrayList` containing all user-created modules.

The `Module` class serves as a wrapper around a `TaskList`, providing additional attributes including the module code and module description. Within the context of Mod Happy, modules can be viewed as task categories with names, descriptions and other attributes; for this reason, the General Tasks list is implemented as a `Module` under the hood.

> 📔 <span style="color:#00bb00">**NOTE:**</span>
>
> An alternative method of implementing `ModuleList` is shown below, where the default General Tasks
list is simply represented as a `TaskList` instead of a full-fledged `Module`.
>
> ![Class Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/ClassDiagrams/DataAlternative.puml)
>
> While this model is arguably closer to real life, the program logic would have to operate on different object types depending on whether a given `Task` belongs to a user-created Module or the default General Tasks list. This was deemed to increase coupling and introduce too much unnecessary clutter to the code, hence it was not used.


### Command Component

The `Command` component is in charge of actually executing the operations requested by the user.

The following partial class diagram illustrates the structure of this component:

![Class Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/ClassDiagrams/CommandClassDiagram.puml)

All commands inherit the abstract `Command` class and must contain an `execute()` method. The program logic that must be executed to fulfil the requested command is implemented in this method. Additionally, `execute()` returns any command output to be displayed to the user as feedback.

`Command` instances are generated by their corresponding `Parser` classes (e.g. `AddCommand` is constructed by `AddParser`) and executed by `Main`.

### Storage Component

The `Storage` component is responsible for the saving and loading of program data from and to its data files. The following class diagram illustrates the structure of this component:

![Class Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/ClassDiagrams/Storage.puml)

Several type-specific classes exist, each overseeing the storage of a different type of user data: 

* `ConfigurationStorage` handles the saving and loading of user preferences. This data is stored in the `data/configuration.json` file.
* `TaskListStorage` handles the saving and loading of the General Tasks list as an `ArrayList<Task>` instance. This data is stored in the `data/tasks.json` file.
* `ModuleListStorage` handles the saving and loading of all user-created modules as well as the tasks associated with them as an `ArrayList<Module>` instance. This data is stored in the `data/modules.json` file.

All write operations rely on the general purpose `writeData()` method of the abstract class `JsonStorage`. However, read operations are implemented in each type-specific class; the `readData()` methods of these classes reconstruct the original object from the serialised data and return them.

## Implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

This section describes some details on how some features are implemented.

### Tag Feature

The tag feature allows the user to add user-created one-word tags to each task, so that tasks can be filtered for easily. Each task stores its tags in an `ArrayList<String>`.

The following sequence diagram illustrates the process:

![Sequence Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/SequenceDiagrams/TagSeqDiagram/Tag.puml)

![Sequence Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/SequenceDiagrams/TagSeqDiagram/GetModule.puml)

![Sequence Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/SequenceDiagrams/TagSeqDiagram/CheckAndRunTagOperation.puml)

Here is an example on adding a tag to a general task:  

1. User inputs `tag add 2 "testTag"`. 
2. `ModHappyParser` identifies the command word as `tag` and passes `add 2 "testTag"` to `TagParser`.
3. `TagParser` instantiates a `TagCommand` with `tagOperation = "add"`, `taskIndex = 2`, `tagDescription = "testTag"` and `taskModule = null`. This is returned to `Main`.
4. `Main` calls the `execute()` method of the `TagCommand` instance.
5. `TagCommand` first gets the relevant `Module`. Since `taskModule` is null, `getGeneralTasks()` is called and the General Tasks `Module` is retrieved.
6. Next, `TagCommand` checks the `tagOperation`. As its value is `add`, `addTag(targetModule)` is called.
7. Finally, command feedback is returned to `Main`, indicating that the operation was successful.

### GPA Feature

The GPA feature computes the user's GPA to 2 decimal places, based on the inputted grades and modular credits of each module currently stored in the program.

Here is an example on how to calculate GPA:

1. User inputs `gpa`.
2. `ModHappyParser` identifies the command word as `gpa`. Since `gpa` takes no arguments, `gpa` is passed to `NoArgumentParser`.
3. `NoArgumentParser` returns an instance of `GpaCommand` to `Main`.
4. `Main` calls the `execute()` method of the `GpaCommand` instance.
5. `execute()` invokes `calculateGpa()`, which performs the actual GPA computation by iterating through the provided `moduleList`.
6. After calculating the GPA, a command feedback string is generated and returned as a string.

Below is the sequence diagram of how the GPA feature works:

![Sequence Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/SequenceDiagrams/GPASeqDiagram/GPA.puml)

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

Below are instructions to perform manual testing of the application. Please refer to the User Guide for more details on the usage of the various commands.

### Launch and exit

