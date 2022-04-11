# Mod Happy: Developer Guide

## Contents
1. [Introduction](#1-introduction)
2. [Product Scope](#2-product-scope)
3. [About this developer guide](#3-about-this-developer-guide)
   <br>3.1. [Purpose](#31-purpose)
   <br>3.2. [Explanation of Notation](#32-explanation-of-notation) 
4. [Acknowledgements](#4-acknowledgements)
5. [Design](#5-design)
   <br>5.1. [UI Component](#51-ui-component)
   <br>5.2. [Parser Component](#52-parser-component)
   <br>5.3. [Data Component](#53-data-component)
   <br>5.4. [Command Component](#54-command-component)
   <br>5.5. [Storage Component](#55-storage-component)
6. [Implementation](#6-implementation)
   <br>6.1. [Edit Feature](#61-edit-feature)
   <br>6.2. [Tag Feature](#62-tag-feature)
   <br>6.3. [Grade Feature](#63-grade-feature)
   <br>6.4. [GPA Feature](#64-gpa-feature)
   <br>6.5. [Storage Feature](#65-storage-feature)
7. [User Stories](#7-user-stories)
8. [Non-Functional Requirements](#8-non-functional-requirements)
9. [Glossary](#9-glossary)
10. [Instructions for manual testing](#10-instructions-for-manual-testing)


## 1. Introduction
Mod Happy is a command-line-based application that helps students manage their academics. Users are able to add modules and tasks, and calculate their Grade Point Average (GPA).

<br><br><br>

## 2. Product Scope

### 2.1. Target User Profile

- Undergraduate Students
- Comfortable using CLI applications
- Able to type relatively quickly

<br>

### 2.2. Value proposition
This application seeks to help the target users to keep track of and manage their module components and deadlines, as it can be confusing to juggle so many deliverables at once.

<br><br><br>

## 3. About this developer guide

### 3.1. Purpose
This developer guide aims to allow you to understand the design and implementation considerations for Mod Happy. With this guide, you will be able to add on or modify any existing implementations for your own purposes.

<br>

### 3.2. Explanation of notation
`Text` formatted as such represent classes and functions occurring in the code, as well as user input examples. 

> 📔 <span style="color:#3333ff">**NOTE:**</span>
>
> Callouts like this one contain additional important information about the component / implementation being discussed. Pay attention to them!

<br><br><br>

## 4. Acknowledgements

- Some foundational source code was adapted from [addressbook-level2](https://github.com/se-edu/addressbook-level2).
- Google's [GSON library](https://github.com/google/gson) was used to facilitate serialisation and deserialisation of data as part of the save/load feature.

<br><br><br>

## 5. Design

The following architecture diagram provides a high level overview of the main components of Mod Happy and how they interact with one another.

![Class Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/ClassDiagrams/Components.puml)

The `Main` class is responsible for handling program initialisation, termination, as well as the application's main execution logic.

Mod Happy's components are summarised below:

* `UI`: Manages the application's text UI.
* `Parser`: Interprets user input and returns corresponding `Command` objects.
* `Command`: Handles command execution logic.
* `Data`: Manages module and task data in program memory.
* `Storage`: Reads data from, and writes data to Mod Happy's data storage files.

<br>

### 5.1. UI Component

The `TextUi` class serves strictly as intermediary between the user and the program, and does not directly interact with any components other than the `Main` class. It fulfils the following roles:

- Listens for and grabs the user's input using a `Scanner`, and returns it to `Main` as a string for further processing.
- Displays any command results or status and error messages to the user.

<br>

### 5.2. Parser Component

![Class Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/ClassDiagrams/Parser.puml)

The `Parser` component serves to interpret user input and construct the relevant `Command` objects to be returned to `Main` for execution later on. This component comprises the following classes:

* `Parser`, which is an abstract class serving as the parent of all other classes in this component. This class implements basic parsing functionality through the use of regular expressions and the built-in `Pattern` class. This functionality is modified by its child classes to suit their own purposes.
* `ModHappyParser`, which identifies the command word present in the user input and invokes the relevant command-specific parser.
* A variety of command-specific parsers (e.g. `AddParser` for the `add` command), referred to in this guide as `XYZParser` for simplicity. These classes perform further parsing on any command-specific arguments, constructing and returning the corresponding `XYZCommand` object.

> 📔 <span style="color:#3333ff">**NOTE:**</span>
> 
> `NoArgumentParser` is an exception to the above; instead of being associated with a single command type, it is responsible for handling all commands which do not accept any arguments.

The following details how the `Parser` component works at runtime:

![Sequence Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/SequenceDiagrams/ParserSequenceDiagram.puml)

1. A single `ModHappyParser` instance is initialised by `Main` during at the start of the program.
2. Each time the user inputs a command, `ModHappyParser`'s `parseCommand()` method with the input as the parameter.
3. `ModHappyParser` identifies the relevant command-specific parser `XYZParser` and passes on the remaining unparsed arguments to its `parseCommand()` method.
4. `XYZParser` parses the command arguments subsequently, and finally construct an `XYZCommand` instance, which is subsequently returned to `Main`.

<br>

### 5.3. Data Component

The `Data` component is responsible for the storage and manipulation of tasks and modules as well as their associated attributes in program memory.

The following partial class diagram illustrates the structure of this component.

![Class Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/ClassDiagrams/Data.puml)

The `ModuleList` class serves as the main data storage class for the program, and is always instantiated when the program is started. It holds:
* A `Module` object representing the General Tasks list. This `Module` is instantiated upon `ModuleList`'s creation and is meant to be the "default" module for all uncategorised or miscellaneous tasks.
* An `ArrayList` containing all user-created modules.

The `Module` class serves as a wrapper around a `TaskList`, providing additional attributes including the module code and module description. Within the context of Mod Happy, modules can be viewed as task categories with names, descriptions and other attributes; for this reason, the General Tasks list is implemented as a `Module` under the hood.

> 📔 <span style="color:#3333ff">**NOTE:**</span>
>
> An alternative method of implementing `ModuleList` is shown below, where the default General Tasks
list is simply represented as a `TaskList` instead of a full-fledged `Module`.
>
> ![Class Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/ClassDiagrams/DataAlternative.puml)
>
> While this model is arguably closer to real life, the program logic would have to operate on different object types depending on whether a given `Task` belongs to a user-created Module, or the default General Tasks list. This was deemed to increase coupling and introduce too much unnecessary clutter to the code, hence it was not used.

<br>

### 5.4. Command Component

The `Command` component is in charge of actually executing the operations requested by the user.

The following partial class diagram illustrates the structure of this component:

![Class Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/ClassDiagrams/CommandClassDiagram.puml)

All commands inherit the abstract `Command` class and must contain an `execute()` method. The program logic that must be executed to fulfil the requested command is implemented in this method. Additionally, `execute()` returns any command output to be displayed to the user as feedback.

`Command` instances are generated by their corresponding `Parser` classes (e.g. `AddCommand` is constructed by `AddParser`) and executed by `Main`.

<br>

### 5.5. Storage Component

The `Storage` component is responsible for the saving and loading of program data to and from its data files. The following class diagram illustrates the structure of this component:

![Class Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/ClassDiagrams/Storage.puml)

* `Storage` is an interface supporting the reading and writing of data to a file.
* `Storage` is implemented by `JsonStorage`, an abstract class that reads and writes objects to a file in JSON format.
* `ListStorage` is an abstract class inheriting from `JsonStorage`, which is specifically used for the serialisation and deserialisation of `ArrayList` instances. `ModuleListStorage` and `TaskListStorage` both inherit from `ListStorage`.
  <br><br><br>

## 6. Implementation

This section describes some details on how some features are implemented.

<br>

### 6.1. Edit Feature

The edit feature allows the user to change a parameter of a task/module. The parameters of a module is its module description while the parameters of a task are its task name, task description and estimated working time. 

The following sequence diagram illustrates the process:

![Sequence Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/SequenceDiagrams/EditSeqDiagrams/Edit.puml)

![Sequence Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/SequenceDiagrams/EditSeqDiagrams/GetModule.puml)

![Sequence Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/SequenceDiagrams/EditSeqDiagrams/GetTask.puml)

Here is an example of editing the description of a task (First task of the module CS2113T):

1. User inputs `edit task 1 -m CS2113T -d "Changed"`.
2. `ModHappyParser` identifies the command word as `edit` and invokes `EditParser.getParser()`.
3. `EditParser.getParser()` will identify `task` and returns `EditTaskParser`
4. `ModHappyParser` passes `task 1 -m CS2113T -d "Changed"` to `EditTaskParser`.
5. `EditTaskParser` instantiates a `EditCommand` with `taskModule = "CS2113T"`, `taskIndex = 0`, `description = "Changed"`, `workingTime = null`, `taskname = null`. This is returned to `Main`.
6. `Main` calls the `execute()` method of the `EditCommand` instance.
7. `EditCommand` first gets the relevant `Module` and invokes `editTaskFromModule(targetModule)`.
8. `editTaskFromModule(targetModule)` retrieves the task `targetTask` specified by the index and invokes `targetTask.setTaskDescription(description)` to change the description.

<br>

### 6.2. Tag Feature

The tag feature allows the user to add user-created one-word tags to each task, so that tasks can be filtered for easily. Each task stores its tags in an `ArrayList<String>`.

The following sequence diagram illustrates the process:

![Sequence Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/SequenceDiagrams/TagSeqDiagrams/Tag.puml)

![Sequence Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/SequenceDiagrams/TagSeqDiagrams/GetModule.puml)

![Sequence Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/SequenceDiagrams/TagSeqDiagrams/CheckAndRunTagOperation.puml)

Here is an example on adding a tag to a general task:  

1. User inputs `tag add 2 testTag`.
2. `ModHappyParser` identifies the command word as `tag` and passes `add 2 "testTag"` to `TagParser`.
3. `TagParser` instantiates a `TagCommand` with `tagOperation = "add"`, `taskIndex = 2`, `tagDescription = "testTag"` and `taskModule = null`. This is returned to `Main`.
4. `Main` calls the `execute()` method of the `TagCommand` instance.
5. `TagCommand` first gets the relevant `Module`. Since `taskModule` is null, `getGeneralTasks()` is called and the General Tasks `Module` is retrieved.
6. Next, `TagCommand` checks the `tagOperation`. As its value is `add`, `addTag(targetModule)` is called.
7. Finally, command feedback is returned to `Main`, indicating that the operation was successful.

<br>

### 6.3. Grade Feature

The grade feature allows the user to input their predicted/actual grade, according to the official grades that NUS supports.

The following sequence diagram illustrates the process:

![Sequence Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/SequenceDiagrams/Grade.puml)

Here is an example on how to assign a grade to a module:

1. User inputs `grade CS2113T A+`
2. `ModHappyParser` identifies the command word as `grade` and passes `CS2113T A+` to `GradeParser`.
3. `GradeParser` instantiates a `GradeCommand` with `moduleCode = "CS2113T"`, `moduleGrade = "A+"`. This is returned to `Main`.
4. `Main` calls the `execute()` method of the `GradeCommand` instance.
5. `execute()` retrieves the `Module` instance of `CS2113T` if it exists and invokes `addGradeToModule(m)`.
6. `addGradeToModule(m)` then invokes `m.setModuleGrade(moduleGrade)` to assign the input grade to the specified module.

<br>

### 6.4. GPA Feature

The GPA feature computes the user's GPA to 2 decimal places, based on the inputted grades and modular credits of each module currently stored in the program.

The following sequence diagram illustrates the process:

![Sequence Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/SequenceDiagrams/GPA.puml)

Here is an example on how to calculate GPA:

1. User inputs `gpa`.
2. `ModHappyParser` identifies the command word as `gpa`. Since `gpa` takes no arguments, `gpa` is passed to `NoArgumentParser`.
3. `NoArgumentParser` returns an instance of `GpaCommand` to `Main`.
4. `Main` calls the `execute()` method of the `GpaCommand` instance.
5. `execute()` invokes `calculateGpa()`, which performs the actual GPA computation by iterating through the provided `moduleList`.
6. After calculating the GPA, a command feedback string is generated and returned as a string.

<br>

### 6.5. Storage Feature

This component makes use of the [Gson](https://sites.google.com/site/gson/gson-user-guide) library, which can be used to convert Java Objects to and from their JSON representation.

Several type-specific classes exist, each overseeing the storage of a different type of user data:

* `ConfigurationStorage` handles the saving and loading of user preferences. This data is stored in the `data/configuration.json` file.
* `TaskListStorage` handles the saving and loading of the General Tasks list as an `ArrayList<Task>` instance. This data is stored in the `data/tasks.json` file.
* `ModuleListStorage` handles the saving and loading of all user-created modules as well as the tasks associated with them as an `ArrayList<Module>` instance. This data is stored in the `data/modules.json` file.
* `ModHappyStorageManager` keeps a reference of storage and provides other components with easy and encapsulated access to write and load all kinds of data in Mod Happy. 

It is worth noting that `ModuleListStorage` and `TaskListStorage` are implemented separately despite having mostly similar code, as the `gson.fromJson` method takes in the class of the object to be constructed, and `.class` cannot be used with generic types.

After data is loaded from the data file, some verification checks are performed to ensure that the data is valid. The following table details some actions taken by Mod Happy in response to various types of data errors:

| Type of error                                                                                                                                                                                                               | Action taken                                                              |
|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| Malformed JSON<br>Multiple modules with the same module code<br>Module's `moduleCode` attribute is missing<br>Task's `taskName` attribute is missing<br>Invalid configuration name<br>Illegal or no value for configuration | Loading from the file is aborted.<br>A blank file is loaded in its place. |
| Module's `modularCredit` attribute is missing or contains illegal value                                                                                                                                                     | The missing value is initialised to `0`.                                  |
| Module's `moduleGrade` attribute is missing or contains illegal value                                                                                                                                                       | The `moduleGrade` is set to `NOT_ENTERED` (the default value when unset). |
| Task's `isTaskDone` attribute is missing or contains illegal value                                                                                                                                                          | The missing value is initialised to `false`.                              |
| Task's `taskDuration` attribute is missing or contains illegal value                                                                                                                                                        | The `taskDuration` is set to `null` (the default value when unset).       |

<br><br><br>

## 7. User Stories

| Version | As a ... | I want to ...                                 | So that ...                                                                 |
|---------|----------|-----------------------------------------------|-----------------------------------------------------------------------------|
| v1.0    | new user | see usage instructions                        | I can refer to them when I forget how to use the application                |
| v1.0    | user     | add a module                                  | I can track the progress of the module                                      |
| v1.0    | user     | remove a module                               | I can keep track in case I decide to drop the module                        |
| v1.0    | new user | save and load tasks                           | I can restore from backups                                                  |
| v1.0    | user     | delete a task                                 | I can remove the task when I don't need it anymore                          |
| v1.0    | user     | edit any existing task to suit my needs       | I can update the information of tasks easily                                |
| v1.0    | user     | add tasks                                     | I can track the tasks later                                                 | 
| v1.0    | user     | list all tasks                                | I can check all tasks                                                       |
| v1.0    | user     | add descriptions and notes to a task          | I can reference them in the future                                          |                   
| v1.0    | user     | edit descriptions and notes                   | I can change them                                                           |                                             
| v1.0    | user     | add expected working time for each task       | I can estimate the amount of time I should put in                           |
| v1.0    | user     | mark a task as completed or uncompleted       | I can manage the completeness of tasks easily                               |
| v1.0    | user     | reset the program                             | I can start from scratch in a new semester                                  |                     
| v1.0    | new user | see what commands I can use in the app        | I can use the app more easily                                               |                
| v1.0    | user     | list all modules                              | I can view all modules that I have added                                    |                                  
| v2.0    | user     | be prompted to confirm when deleting any task | I won’t delete the wrong task accidentally                                  |
| v2.0    | user     | show or hide completed tasks in the task list | I can check the uncompleted tasks only                                      |
| v2.0    | user     | create tags for tasks                         | I can categorise them more easily (e.g. tutorial, project, assignment, etc) |
| v2.0    | user     | mark tasks as important                       | I can know what tasks to prioritise                                         |
| v2.0    | user     | list tasks by tag                             | I can filter tasks I’m looking for                                          |
| v2.0    | user     | input my grades                               | I can estimate my final GPA                                                 |
| v2.0    | user     | estimate my GPA                               | I can gauge my performance                                                  |

<br><br><br>

## 8. Non-Functional Requirements

1. Should work on any mainstream OS as long as it has _Java 11_ installed.
2. Should be able to hold up to 1000 tasks and modules combined without a noticeable sluggishness in performance for typical usage.
3. Should be able to save up to 1000 tasks and modules without taking up noticeable disk space.

<br><br><br>

## 9. Glossary

* *CLI* - Command-line interface
* *Mainstream OS* - Windows, Linux, Unix, OS-X
* *GPA* - Grade Point Average 
* *Tag* - a single word to identify a group of tasks

<br><br><br>

## 10. Instructions for manual testing

Below are instructions to perform manual testing of the application. Please refer to the User Guide for more details on the usage of the various commands.

<br>

### Launch and exit
1. Download the JAR file and copy the file to an empty folder.
2. Launch a command terminal and start the application by typing `java -jar FILENAME`, where `FILENAME` is the name of the JAR file (e.g. `tp.jar`).
3. Exit the application by typing `exit`.

<br>

### Adding a module
* Test Case: `add mod CS2113T 4 -d "Software Engineering and OOP"` <br>
  Expected: A module named `CS2113T` with `4` mc is added, with a description of `Software Engineering and OOP`.
* Test Case: `add mod CS2113T 4` (Continuation from Test Case 1) <br>
  Expected: No new module is added. Error details in the message shows that a module of the same name already exists.
* Test Case: `add mod CS2101` <br>
  Expected: No new module is added. Error details in the message shows that there are missing modular credits.

<br>

### Adding a task
* Prerequisite: There are existing modules in the application.
* Assumption: You have a module `CS2113T` added.


* Test Case: `add task "start PE" -m CS2113T` <br>
  Expected: A task with name `start PE` is added under the module `CS2113T`.
* Test Case: `add task Invalid Name` <br>
  Expected: No task is added. Error details in the message shows that the task name is invalid.

<br>

### Deleting a module
* Prerequisite: There are existing modules in the application.
* Assumption: You have a module `CS2113T` added, but not `CS2101`.


* Test Case: `del mod CS2113T` <br>
  Expected: The module `CS2113T` is deleted.
* Test Case: `del mod CS2101` <br>
  Expected: No module is deleted. Error details in the message shows that there are no such module. 

> 📔 <span style="color:#3333ff">**NOTE:**</span>
> 
> If you have already added tasks to a module, deleting that module will present you with a confirmation request. Type `yes` to proceed with deletion.

<br>

### Deleting a task
* Prerequisite: There are existing tasks in the application.
* Assumption: You have the module `CS2113T` added with at least one task added. 


* Test Case: `del task 1 -m CS2113T` <br>
  Expected: The first task in `CS2113T` will be deleted. 
* Test Case: `del task -1` <br>
  Expected: No task is deleted. Error details in the message shows that the task number is invalid.

<br>

### Editing a module
* Prerequisite: There are existing modules in the application. 
* Assumption: You have the module `CS2113T` added.


* Test Case: `edit mod CS2113T -d "Changed"` <br>
  Expected: The description of `CS2113T` is set to `Changed`.
* Test Case: `edit mod CS2113T -t "2 hours"` <br>
  Expected: The module remains unchanged. Error details in the message shows that `-t` is an invalid flag.

<br>

### Editing a task
* Prerequisite: There are existing tasks in the application.
* Assumption: You have at least one task in your `General Tasks`.


* Test Case: `edit task 1 -d "Changed"` <br>
  Expected: The description of the first task in `General Tasks` is set to `Changed`.
* Test Case: `edit task 1 -d "Changed" -t "2 hours"` <br>
  Expected: The task remains unchanged. Error details in the message shows that there is an excess argument of `-t "2 hours"`.

<br>

### Setting grade for a module
* Prerequisite: There are existing modules in the application.
* Assumption: You have a module `CS2113T`.

* Test Case: `grade CS2113T A+` <br>
  Expected: The grade of `CS2113T` has been set to `A+`.
* Test Case: `grade CS2113T E` <br>
  Expected: No grade is set. Error details in the message shows that `E` is an invalid module grade.
* Test Case: `grade CS2113T -` <br>
  Expected: The grade of `CS2113T` has been removed.

<br>

### Calculating GPA
* Prerequisite: There are existing modules in the application.
* Assumption: You have a module `CS2113T` of `4` modular credits and grade `A+` and a module `CS2101` of `4` modular credits and grade `B`.


* Test Case: `gpa` <br>
  Expected: Your `gpa` is calculated as `4.25`.

<br>

### Showing Help
* Test Case: `help` <br>
  Expected: A message for format of the `help` command is shown.
* Test Case: `help add` <br>
  Expected: A message for the format of the `add` command is shown.

<br>

### Setting options
* Test Case: `option` <br>
  Expected: Available configuration settings is shown with its corresponding value.
* Test Case: `option INVALID_CONFIG` <br>
  Expected: An error message is shown detailing that there is no configuration called `INVALID_CONFIG`.
* Test Case: `option SHOW_COMPLETED_TASKS=invalid` <br>
  Expected: No configuration is changed. Error details in the message shows that the value `invalid` is not supported for configuration `SHOW_COMPLETED_TASKS`.

<br>

### Adding tags to a task
* Prerequisite: There are existing tasks in the application.
* Assumption: You have at least one task in `General Tasks`.


* Test Case: `tag add 1 IMPT` <br>
  Expected: The tag `IMPT` is added to your first task in `General Tasks`.
* Test Case: `tag add 1 .invalid` <br>
  Expected: No tag is added. Error details in the message shows that `.invalid` is an invalid tag name.

<br>

### Deleting tags from a task
* Prerequisite: There are tasks with tags in the application.
* Assumption: The first task in `General Tasks` is tagged as `IMPT` only.


* Test Case: `tag del 1 IMPT` <br>
  Expected: The tag `IMPT` is removed from the first task in `General Tasks`.
* Test Case: `tag del 1 OTHERS` <br>
  Expected: No tag is deleted. Error details in the message shows that no such tag exists.

<br>

### Listing all modules and tasks
* Prerequisite: There are tasks that were tagged.
* Assumption: There are tasks that were tagged as `IMPT`.

* Test Case: `list` <br>
  Expected: All of your modules and tasks are shown.
* Test Case: `list IMPT` <br>
  Expected: Only tasks tagged as `IMPT` are shown. All of your modules are still shown regardless.

<br>

### Marking a task as completed or uncompleted
* Prerequisite: There are existing tasks in the application.
* Assumption: There are at least one task in `General Tasks`.


* Test Case: `mark c 1` <br>
  Expected: The first task in `General Tasks` is marked as completed.
* Test Case: `mark u 1` <br>
  Expected: The first task in `General Tasks` is marked as uncompleted.
* Test Case: `mark t 1` <br>
  Expected: No tasks are marked. Error details in the message shows that `t` is an invalid flag.

<br>

### Saving the data in the application
* Prerequisite: There is some data (modules, tasks and options) in the application.


* Test Case: `save` <br>
   Expected: Your data will be saved. You can view them directly as JSON files in the `data` directory.

<br>

### Resetting the modules and tasks in the application
* Prerequisite: There are modules and tasks added in the application.


* Test Case: `reset` <br>
  Expected: All of your modules and tasks will be removed.