# Developer Guide

---
## Acknowledgements
In this project, we have referenced the following materials:
* [AB-3 Developer Guide](https://se-education.org/addressbook-level3/DeveloperGuide.html)
* [PlantUML Tutorial at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html)
* [Our individual projects](AboutUs.md)

---
## Design

> :information_source: **Note:** The `.puml` files used to create diagrams in this document 
> can be found in the diagrams folder. Refer to the above [PlantUML Tutorial](#Acknowledgements)
> to learn how to create and edit diagrams.

### Architecture
<image src="images/ArchitectureDiagram.png" width="300"/>

The ***Architecture Diagram*** given above shows the high-level design of PlanITarium.

Given below is a quick overview of the main components and how they interact.

**Main components of the Architecture**

[`Main`](https://github.com/AY2122S2-CS2113T-T10-2/tp/blob/master/src/main/java/seedu/planitarium/PlanITarium.java) 
is responsible for,
* At launch, initialize the components in an appropriate manner and calls Storage to read data if any. 
* Read user's commands from standard input for command execution.
* At shut down, invokes shutdown sequence and calls Storage to save its current data. 

[`UI`](#UI-Component) is responsible for the UI of PlanITarium.

[`Commands`](#Commands-Component) is responsible for the execution of commands.

[`Parser`](#Parser-Component) is responsible for parsing and validating user input.

[`Persons`](#Persons-Component) is responsible for holding the user data of PlanITarium in memory.

[`Money`](#Money-Component) is responsible for holding the monetary information in memory.

[`Storage`](#Storage-Component) is responsible for reading and writing data to the hard disk.

**How the components interact with each other**

{referring to AB-3, describe one encompassing command such as `add /n Alice`}

### UI Component
{For Huilin as she is most familiar}

### Commands Component
{For Huilin}

### Parser Component
{For Sizheng}

### Persons Component
{For Weijun}

### Money Component
{For Jiarong}

### Storage Component
{For Hans}

---
## Implementation

This section describes some noteworthy details on how certain features are implemented.

### Command Execution

Maybe for Huilin
{Suggest to show the process from `user input` to `input parsing` to `command factory`
then actual execution}

### Add Grouped User Feature

Maybe for Weijun
{Suggest to show the process to `add a new Person` to a logical group i.e. Children}

### Edit Values Feature 

Maybe for Jiarong
{Suggest to show the process from `parsing several delimiters` to `finding the
income/expenditure` to `editing the value`.}

### Listing Categorised Expenditures Feature

Maybe for Sizheng
{Suggest to show the process of iterating through lists to obtain the list of items in category}

### Data Archiving

Maybe for Hans
{Suggest to show the saving and loading process}

---
## Documentation

### Logging
{Describe the usage of logging for the product.}

### Testing
{Describe the testing methods for the product.}

---
## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

---
## User Stories

| Version | As a ... | I want to ...             | So that I can ...                                           |
|---------|----------|---------------------------|-------------------------------------------------------------|
| v1.0    | new user | see usage instructions    | refer to them when I forget how to use the application      |
| v2.0    | user     | find a to-do item by name | locate a to-do without having to go through the entire list |

---
## Non-Functional Requirements

{Give non-functional requirements}

---
## Glossary

* *glossary item* - Definition

---
## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
