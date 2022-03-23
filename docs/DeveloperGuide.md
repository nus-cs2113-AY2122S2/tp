# Developer Guide

## Acknowledgements

- Some foundational source code was adapted from [addressbook-level2](https://github.com/se-edu/addressbook-level2).
- Google's [GSON library](https://github.com/google/gson) was used to facilitate serialisation and deserialisation of data stored in the data file.

## Design & Implementation
### Architecture
Given below is a quick overview of the main components of Mod Happy and how they interact with one another.
![Class Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/Components.puml)

### Parser Component
![Class Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T10-3/tp/master/docs/Parser.puml)

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

## Implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Tag Feature

The tag command accepts a string from the user and adds it into `ArrayList<String> tags` of a `Task`.  

Here is an example on adding a tag to a general task:  
1) User inputs `tag add 2 "testTag"`. 
2) `TagParser` will initialise `TagCommand` with add as `tagOperation` 2 as `taskIndex` and testTag as `tagDescription`, while `taskModule` is null.
3) `TagCommand` then gets the relevant `Module`. If `taskModule` is null, `getGeneralTasks()` is called. Else, `getModule(taskModule)` is called instead.
4) Next, `TagCommand` checks the `tagOperation`. If add, `addTag(targetModule)` is called. Else if del, `removeTag(targetModule)` is called. Else, it throws `ParseException`.

Below is the sequence diagram of how the tag feature works:

![Sequence Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/ngys117/tp/branch-PR-DeveloperGuide/docs/TagSeqDiagram/Tag.puml)
![Sequence Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/ngys117/tp/branch-PR-DeveloperGuide/docs/TagSeqDiagram/GetModule.puml)
![Sequence Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/ngys117/tp/branch-PR-DeveloperGuide/docs/TagSeqDiagram/CheckAndRunTagOperation.puml)

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
