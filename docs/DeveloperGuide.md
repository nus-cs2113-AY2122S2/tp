# Developer Guide

## Acknowledgements

- Some foundational source code was adapted from [addressbook-level2](https://github.com/se-edu/addressbook-level2).
- Google's [GSON library](https://github.com/google/gson) was used to facilitate serialisation and deserialisation of data stored in the data file.

## Design & Implementation
### Architecture
Given below is a quick overview of the main components of Mod Happy and how they interact with one another.
![Class Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/Components.puml)
#### Main components of the architecture

`Main`  is responsible for,
* Att app launch: sets up the components in the correct order and connects them, and calls storage to load data.
* Running time: connects UI and ModHappy Parser to get corresponding Command and execute; handles exceptions.
* At shut down: invokes ExitCommand and execute pre-ending methods.
  Commons represents a collection of classes used by multiple other components.

#### The rest of the App consists of five components.

* `UI`: The UI of the App.
* `Parser`: The interpretor that takes user input and returns corresponding commands.
* `Command`: Command executor that supports various orders from users.
* `Data`: Various data types for managing users' modules and tasks.
* `Storage`: Reads data from, and writes data to, the hard disk.

### Parser Component
![Class Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/Parser.puml)
How the parsing works:
1. All XYZParser(XYZ is a placeholder e.g.  AddParser) and ModHappyParser interited from Parser class, which defines parseString() method that can parse based on well defined command Regular expression(Regex) and command groups. XYZParser parses user input in multiple-layer manner to suport complex and extendable commands.
2. When called upon to parse a user command input, the ModHappyParser class, which serves as a general singleton parser of Mod Happy,  creates an XYZParser
3. XYZParser further parses the user input and will return corresponding Command objects or shunt the user input into its sub-parser and pass the output up .
### Data Component

The data component is responsible for the storage and manipulation of tasks and modules, as well as their associated attributes.

The following partial class diagram illustrates the general organisation of this component.

![Class Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/Data.puml)

The `ModuleList` class serves as the main data storage class for the program, and is always instantiated when the program is started. It holds:
* A `Module` object representing the General Tasks list. This `Module` is instantiated upon `ModuleList`'s creation and is meant to be the "default" module for all uncategorised or miscellaneous tasks.
* An `ArrayList` containing all user-created modules.

The `Module` class serves as a wrapper around a `TaskList`, providing additional attributes including the module code and module description. Within the context of Mod Happy, modules can be viewed as task categories with names, descriptions and other attributes. For this reason, the General Tasks list is implemented as a `Module` under the hood.

> 📔 <span style="color:#00bb00">**NOTE:**</span>
>
> An alternative method of implementing `ModuleList` is shown below, where the default General Tasks
list is simply represented as a `TaskList` instead of a full-fledged `Module`.
>
> ![Class Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/DataAlternative.puml)
>
> While this model is arguably closer to real life, the program logic would have to operate on different object types depending on whether a given `Task` belongs to a user-created Module or the default General Tasks list. This was deemed to increase coupling and introduce too much unnecessary clutter to the code, hence it was not used.

### Storage Component
**API** : Storage.java
![Class Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/Ch40gRv1-Mu/tp/branch-A-Storage-puml/docs/Storage.puml)
The Storage component,
* Storage interface is implemented by JsonStorage in Mod Happy, which will read and load data to and from json famat.
* ListStorage can save a ArrayList of any class that extends Object in json format, and read them back into corresponding objects.
* ModuleListStorage, TaskListStorage inherit from ListStorage, and they manage the write/load of ModuleList and TaskList to/from disk respectively
* ConfigurationStorage in inherits directly from JsonStorage, and it manage the write/load of Configuration
* There are navigability to Storage from Main and SaveComand, which handles the load and write data to/from disk respectively.
## Implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}


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
