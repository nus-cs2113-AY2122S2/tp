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

The **API** of this component is specified in `Family.java`, `PersonList.java` and `Person.java`.

<image src="images/PersonsComponent.png" width="300"></image>

The list of persons consists of a `Family` that is made up of generational `PersonList`s. Each `PersonList` holds a  
list of `Person`s who belong to that generation.

The `Person` component,
* Stores the logical grouping of persons added i.e., all `Person` objects must belong to one of the `PersonList`s.
* Stores the total number of `Person`s in the `Family`, as well as each of the generational `PersonList`s.
* Depends on the `Money` component to help keep track of each `Person`'s income and expenditure as each `Person`   
  contains an `IncomeList` and `ExpenditureList`.

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

### \[Proposed] Logical grouping for different generation of person added

#### Proposed implementation

The proposed logical grouping of persons added is facilitated by `Family`. It holds 3 lists of `PersonList`, each one
for a different generation.
Additionally, it implements the following operations:
* `Family#list()` -- Lists a high level overview of income and expenditure of each generation.
* `Family#remain()` -- Prints the total disposable income remaining for the family after everybody's income and   
  expenditures have been taken into account.
* `Family#addParent()` -- Adds a person into the `parents` list.
* `Family#addMyGen()` -- Adds a person into the `myGen` list.
* `Family#addChild()` -- Adds a person into the `children` list.

Given below is an example usage scenario and how a generation's high level finance overview is calculated.

Step 1. The user launches the application. A `Family` object will be initialised with its 3 generational   
`PersonList`s. They are `parents`, `myGen`, and `children`.
-- Insert UML object diagram here

Step 2. The user wishes to add a person, say `John Doe`, to the `children` list. The user executes   
`add /n John Doe /g 3` command, adding a `Person` with `name` as `John Doe` to group 3, which is the `children`.
-- Insert UML object diagram here

Step 3. The user executes `addin /g 3 /u 1 /i ...` to add a new income to index 1 of the `children` list, who is   
`John Doe`. This causes an income object to be added to the `IncomeList` of `John Doe`.
-- Insert UML object diagram here

Step 4. The user now decides to have an overview of his family's finances by executing the `list` command. The `list`  
command will call `Family#list()`, which will go through each generation to sum up their incomes and expenditures and  
print that out.
-- Insert UML sequence diagram here

#### Design considerations:
**Aspect: How to sort persons into logical groups:**
* **Alternative 1 \(current choice):** Have a `Family` object hold 3 `PersonList`s, one for each generation.
  * Pros: Only requires storage of one instance of each income and expenditure.
  * Cons: May have performance issues related to operations which work on every income and expenditure as it makes  
    them deeply nested.
* **Alternative 2:** Maintain the single `PersonList` with everyone inside, but give a tag to each `Person` to  
  indicate which generation they belong to
  * Pros: Very low maintenance and changes required to existing code.
  * Cons: Lack of abstraction, and that total income and expenditure for each generation would need to be   
    stored until the entire list is iterated through before being able to print.

**Aspect: How many levels of information to show**
* **Alternative 1 \(current choice):** Each class shows information suiting their level i.e., `Family#list()` shows  
  an overview of each `PersonList`'s total income and expenditure.
  * Pros: Users will be able to choose how much information they want to see.
  * Cons: The user would be unable to view all the information with a single command.
* **Alternative 2:** Only have a single `Family#list()` which gives detailed information of each `Person`'s income   
  and expenditures.
  * Pros: Only 1 command is required to show all information.
  * Cons: If the user only wants a high level overview, the user could be hit with information overload.

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
