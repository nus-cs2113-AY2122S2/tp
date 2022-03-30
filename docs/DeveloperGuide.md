# Developer Guide

## Contents
1. [Introduction](#introduction)
2. [Product Scope](#product-scope)
3. [About this developer guide](#about-this-developer-guide)
   <br>2.1. [Purpose](#purpose)
   <br>2.2. [Explanation of Notation](#explanation-of-notation) 
4. [Acknowledgements](#acknowledgements)
5. [Design](#design)
   <br>4.1. [UI Component](#ui-component)
   <br>4.2. [Parser Component](#parser-component)
   <br>4.3. [Data Component](#data-component)
   <br>4.4. [Command Component](#command-component)
   <br>4.5. [Storage Component](#storage-component)
6. [Implementation](#implementation)
   <br>5.1. [Tag Feature](#tag-feature)
   <br>5.2. [GPA Feature](#gpa-feature) 


## Introduction
Mod Happy is a command-line-based application that helps students manage their academics. Users are able to add modules and tasks, and calculate their Grade Point Average (GPA).

## Product Scope

### Target User Profile

- Undergraduate Students
- Comfortable using CLI applications
- Able to type relatively quickly

### Value proposition
This application seeks to help the target users to keep track of and manage their module components and deadlines, as it can be confusing to juggle so many deliverables at once.

## About this developer guide
### Purpose
This developer guide aims to allow you to understand the design and implementation considerations for Mod Happy. With this guide, you will be able to add on or modify any existing implementation for your own usage.

### Explanation of notation
`Text` formatted as such represent the classes and functions implemented in the application. You can refer to the API provided to view the implementation directly.

> 📔 <span style="color:#00bb00">**NOTE:**</span>
>
> Text enclosed in this "Note" block should be taken note of as it can contain important information about the Component/Implementation.

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

### Edit Feature

The edit feature allows the user to change a parameter of a task/module. The parameters of a module is its module description while the parameters of a task are its task name, task description and estimated working time. 

Here is an example of editing the description of a task (First task of the module CS2113T):

1. User inputs `edit task 1 -m CS2113T -d "Changed"`.
2. `ModHappyParser` identifies the command word as `edit` and passes `task 1 -m CS2113T -d "Changed"` to `EditParser`.
3. `EditParser` instantiates a `EditCommand` with `taskModule = "CS2113T"`, `taskIndex = 0`, `description = "Changed"`, `workingTime = null`, `taskname = null`. This is returned to `Main`.
4. `Main` calls the `execute()` method of the `EditCommand` instance.
5. `EditCommand` first gets the relevant `Module` and invokes `editTaskFromModule(targetModule)`.
6. `editTaskFromModule(targetModule)` retrieves the task `targetTask` specified by the index and invokes `targetTask.setTaskDescription(description)` to change the description.

Below is the sequence diagram of how the Grade feature works:
*<br>UPDATE AFTER MERGE*
![Sequence Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/SequenceDiagrams/Edit.puml)

### Tag Feature

The tag feature allows the user to add user-created one-word tags to each task, so that tasks can be filtered for easily. Each task stores its tags in an `ArrayList<String>`.

The following sequence diagram illustrates the process:

![Sequence Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/SequenceDiagrams/TagSeqDiagram/Tag.puml)

![Sequence Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/SequenceDiagrams/TagSeqDiagram/GetModule.puml)

![Sequence Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/SequenceDiagrams/TagSeqDiagram/CheckAndRunTagOperation.puml)

Here is an example on adding a tag to a general task:  

1. User inputs `tag add 2 testTag`.
2. `ModHappyParser` identifies the command word as `tag` and passes `add 2 "testTag"` to `TagParser`.
3. `TagParser` instantiates a `TagCommand` with `tagOperation = "add"`, `taskIndex = 2`, `tagDescription = "testTag"` and `taskModule = null`. This is returned to `Main`.
4. `Main` calls the `execute()` method of the `TagCommand` instance.
5. `TagCommand` first gets the relevant `Module`. Since `taskModule` is null, `getGeneralTasks()` is called and the General Tasks `Module` is retrieved.
6. Next, `TagCommand` checks the `tagOperation`. As its value is `add`, `addTag(targetModule)` is called.
7. Finally, command feedback is returned to `Main`, indicating that the operation was successful.

### Grade Feature

The Grade feature allows the user to input their predicted/actual grade, according to the official grades that NUS supports.

Here is an example on how to assign a grade to a module:

1. User inputs `grade CS2113T A+`
2. `ModHappyParser` identifies the command word as `grade` and passes `CS2113T A+` to `GradeParser`.
3. `GradeParser` instantiates a `GradeCommand` with `moduleCode = "CS2113T"`, `moduleGrade = "A+"`. This is returned to `Main`.
4. `Main` calls the `execute()` method of the `GradeCommand` instance.
5. `execute()` retrieves the `Module` instance of `CS2113T` if it exists and invokes `addGradeToModule(m)`.
6. `addGradeToModule(m)` then invokes `m.setModuleGrade(moduleGrade)` to assign the input grade to the specified module.

Below is the sequence diagram of how the Grade feature works:
*<br>UPDATE AFTER MERGE*
![Sequence Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/SequenceDiagrams/Grade.puml) 


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


## User Stories

| Version | As a ... | I want to ...                                                | So that ...                                                                 |
|---------|----------|--------------------------------------------------------------|-----------------------------------------------------------------------------|
| v1.0    | new user | see usage instructions                                       | I can refer to them when I forget how to use the application                |
| v1.0    | user     | add a module                                                 | I can track the progress of the module                                      |
| v1.0    | user     | remove a module in case I decide to drop the module          ||
| v1.0    | new user | save and load tasks                                          | I can restore from backups                                                  |
| v1.0    | user     | delete a task                                                | I can remove the task when I don't need it anymore                          |
| v1.0    | user     | edit any existing task to suit my needs                      | I can update the information of tasks easily                                |
| v1.0    | user     | add tasks                                                    | I can track the tasks later                                                 | 
| v1.0    | user     | list all tasks                                               | I can check all tasks                                                       |
| v1.0    | user     | add descriptions and notes to a task                         | I can reference them in the future                                          |                   
| v1.0    | user     | edit descriptions and notes                                  | I can change them                                                           |                                             
| v1.0    | user     | add expected working time for each task                      | I can estimate the amount of time I should put in                           ||
| v1.0    | user     | mark a task as completed or uncompleted                      | I can manage the completeness of tasks easily                               |
| v1.0    | user     | reset the program                                            | I can start from scratch in a new semester                                  |                     
| v1.0    | new user | see what commands                                            | I can use in the app	I can use the app more easily                          |                
| v1.0    | user     | list all modules                                             | I can view all modules that I have added                                    |                                  
| v2.0    | user     | be prompted to confirm when deleting any task                | I won’t delete the wrong task accidentally                                  |
| v2.0    | user     | show or hide completed tasks in the task list                | I can check the uncompleted tasks only                                      |
| v2.0    | user     | create tags for tasks                                        | I can categorise them more easily (e.g. tutorial, project, assignment, etc) |
| v2.0    | user     | mark tasks as important                                      | I can know what tasks to prioritise                                         |
| v2.0    | user     | list tasks by tag                                            | I can filter tasks I’m looking for                                          |
| v2.0    | user     | input my grades                                              | I can estimate my final grade                                               |
| v2.0    | user     | sort modules by % grade                                      | I can know how well I am doing                                              |
| v2.0    | user     | set a task as graded or ungraded                             | I can manage the grades of assessment easily                                |
| v2.0    | user     | set the module weightage for each graded task in each module | I can check the progress of each module later                               |
| v2.0    | user     | input my estimated/predicted grades for a task               | I can gauge my performance in the module thus far                           |

## Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java 11 installed.
2. Should be able to hold up to 1000 tasks and modules combined without a noticeable sluggishness in performance for typical usage.
3. Should be able to save up to 1000 tasks and modules without taking up noticeable disk space.


## Glossary

* *Mainstream OS* - Windows, Linux, Unix, OS-X

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

Below are instructions to perform manual testing of the application. Please refer to the User Guide for more details on the usage of the various commands.

### Launch and exit

