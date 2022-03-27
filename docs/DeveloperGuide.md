# Developer Guide

---

## Acknowledgements

In this project, we have referenced the following materials:

* [AB-3 Developer Guide](https://se-education.org/addressbook-level3/DeveloperGuide.html)
* [PlantUML Tutorial at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html)
* [Our individual projects](AboutUs.md)

---

## Design

### Architecture

The ***Architecture Diagram*** given below shows the high-level design of PlanITarium.

![ArchitectureDiagram](images/ArchitectureDiagram.png)

> :information_source: **Note:** The that `.puml` files used to create diagrams in this document
> can be found in the diagrams folder. Refer to the above [PlantUML Tutorial](#Acknowledgements)
> to learn how to create and edit these diagrams when necessary.

**Overview of components in the Architecture**

[`Main`](https://github.com/AY2122S2-CS2113T-T10-2/tp/blob/master/src/main/java/seedu/planitarium/PlanITarium.java)
is responsible for,

* At launch, initialize the components in an appropriate manner and calls Storage to read data if any.
* Read user's commands from standard input for command execution.
* At shut down, invokes shutdown sequence and calls Storage to save its current data.

[`UI`](#UI-Component) is responsible for the UI of PlanITarium.

[`Commands`](#Commands-Component) is responsible for handling and executing of commands.

[`Parser`](#Parser-Component) is responsible for parsing and validating user input.

[`Family`](#Persons-Component) is responsible for holding the user data of PlanITarium in memory.

[`Money`](#Money-Component) is responsible for holding the monetary information in memory.

[`Storage`](#Storage-Component) is responsible for reading and writing data to the hard disk.

**How the components interact with each other**

The slightly simplified *Sequence Diagram* below shows how the components interact for the scenrio where the user issues
the command `add /n Alice /g 2`.

![ArchitectureSequenceDiagram](images/ArchitectureSequenceDiagram.png)

Each of the main components shown in the diagram above is defined and implemented in a class with the same name as its
component. The section below provides a more in-depth details on how the components interact with one another.

Each component may have several other classes underneath it, belonging to the same logical grouping, to reduce coupling.
For example, the `MoneyList` component is defined as an abstract class that is extended by `IncomeList`
and `ExpenditureList`.

### UI Component

<!-- {For Huilin as she is most familiar} -->

### Commands Component

<!-- {For Huilin} -->

### Parser Component

**Class:** [`Parser.java`
](https://github.com/AY2122S2-CS2113T-T10-2/tp/blob/master/src/main/java/seedu/planitarium/parser/Parser.java)

![ParserClassDiagram](images/ParserClassDiagram.png)

The `Parser` component consists of the `Parser` class, `ParserUtility` class and several `Exception` classes.

The `Parser` class provides the `parseXYZ()` and `getValidXYZ()` methods where `XYZ` is a placeholder for the type of
term (e.g. `parseKeyword()` and `getValidUserIndex`). The methods prepended by **parse** assists in parsing the user
input into its respective terms and the method prepended by **getValid** assists in validating the parsed terms and
returning an appropriately typed object to the `Commands` component. The `Parser` class interacts with the
`ParserUtility` class which provides supporting methods for parsing and validating. Both classes throws exceptions as
required.

The following Sequence Diagram shows how the classes of the `Parser` component interacts for each user command.

![ParserOverviewSequenceDiagram](images/ParserSequenceDiagram0.png)

How the `Parser` component is used:

1. When the `Commands` component receives a user input, `parseKeyword()` is called upon to parse the type of command to
   be executed.
2. This will result in the keyword of the command to be returned as a string.
3. When necessary, the `parseXYZ()` methods will be called upon to parse more terms for the `Commands`
   component to obtain the details required for the command execution (e.g. `parseGroupIndex("add /n Alice /g 2")`
   to get group 2). The `ParserUtility` assists the parsing during this process by providing utility methods.
4. The `getValidXYZ()` methods will also be called upon thereafter to check and return valid typecasted objects to be
   used for the command execution (e.g. `getValidGroupIndex(indexString)` to check that the index provided corresponds
   to an existing group). The `ParserUtility` is also called here to assist with the validation process.

The Sequence Diagram below illustrates the interactions in the `Parser` component for a command execution.
Let `userInput` be the command string `deletein /u 1 /g 2 /r 1` and the minimum index `MIN_INDEX` be the constant that
is supported by PlanITarium to be `1`.

![ParserSequenceDiagramExecute](images/ParserSequenceDiagram1.png)

### Family Component

The **Class** of this component is specified in [`Family.java`](
https://github.com/AY2122S2-CS2113T-T10-2/tp/blob/master/src/main/java/seedu/planitarium/person/Family.java)
, [`PersonList.java`](
https://github.com/AY2122S2-CS2113T-T10-2/tp/blob/master/src/main/java/seedu/planitarium/person/PersonList.java)
and [`Person.java`](
https://github.com/AY2122S2-CS2113T-T10-2/tp/blob/master/src/main/java/seedu/planitarium/person/Person.java)
)

![FamilyComponent](images/FamilyComponent.png)

The list of persons consists of a `Family` that is made up of generational `PersonList`s. Each `PersonList` holds a  
list of `Person`s who belong to that generation.

The `Person` component,

* Stores the logical grouping of persons added i.e., all `Person` objects must belong to one of the `PersonList`s.
* Stores the total number of `Person`s in the `Family`, as well as each of the generational `PersonList`s.
* Depends on the `Money` component to help keep track of each `Person`'s income and expenditure as each `Person`   
  contains an `IncomeList` and `ExpenditureList`.

### MoneyList Component

<!-- {For Jiarong} -->

### Storage Component

**Class:**

![StorageDiagram](images/StorageDiagram.png)

The `Storage` component,

* can save each person's data and their income and expenditure data when the program exits into a local file, and reads
  them back into corresponding objects when the program runs again.
* depends on the classes in the `Person` component (because the `Storage` component's job is to save/retrieve objects to
  the `Person`)

---

## Implementation

This section describes some noteworthy details on how certain features are implemented.

### Command Execution

In PlanITarium, a command usually have following format:

```md
[command type][indicator][description]
```

For example,

```md
add \n Alice
```

is to add a person whose name is Alice. `add` here is the command type to add a person, and `\n` is the indicator of the
name of person.

There may be several indicators for every input, such as

```md
addin \i 200 \d salary \u 1
```

is to add a income for the person whose uid is 1, and the description of this income is salary.

When `PlanITarium` receives such input, it will pass the input to `CommandFactory`. The `CommandFactory` will call
`Parser` to parse the input into several components according to the indicator. The `Parser` will then return the type
of command to `CommandFactory`. According to the type, `CommandFactory` will return a command object to `PlanITarium`.
After receiving the command, `PlanITarium` will call `execute()` of command object to execute the command.

The following diagram is the sequence diagram of this entire process.

![CommandFactorySequence](images/CommandFactorySequence.png)

Following operations are implemented:

* `CommandFactory#getCommand(userInput, personList)` -- Return the command that is needed to be executed corresponding
  to the user input
* `Parser#getKeyword(userInput)` -- Return the keyword of the type of command
* `Command#execute()` -- Execute the command

Below is and example usage scenario and how Alice is added to the `PersonList`

Step 1. given that user input is

```md
add \n Alice
```

and this string will be passed to `CommandFactory` together with `personList` that contains all the people who had been
added previously by calling `getCommand()`.

Step 2. The `CommandFactory` will pass the input to `Parser` to parse the keyword by calling `Parser.parseKeywords`, and
the `Parser` should return `add` as keyword.

Step 3. The `CommandFactory` will then match the keyword to the type of command. In this case, `add` is corresponding
to `AddPersonCommand`. The `CommandFactory` will then create a new `AddPersonCommand` with `userInput` and `personList`,
and return this object to `PlanITarium`.

Step 4. `PlanITarium` will then execute this command by calling `execute()`. Alice will then be added to the
`personList`.

### \[Proposed] Logical grouping for different generation of person added

#### Proposed implementation

The proposed logical grouping of persons added is facilitated by `Family`. It holds 3 lists of `PersonList`, each one
for a different generation. Additionally, it implements the following operations:

* `Family#list()` -- Lists a high level overview of income and expenditure of each generation.
* `Family#remain()` -- Prints the total disposable income remaining for the family after everybody's income and   
  expenditures have been taken into account.
* `Family#addParent()` -- Adds a person into the `parents` list.
* `Family#addMyGen()` -- Adds a person into the `myGen` list.
* `Family#addChild()` -- Adds a person into the `children` list.

Given below is an example usage scenario and how a generation's high level finance overview is calculated.

Step 1. The user launches the application. A `Family` object will be initialised with its 3 generational   
`PersonList`s. They are `parents`, `myGen`, and `children`.

![PersonStep1](images/PersonStep1.png)

Step 2. The user wishes to add a person, say `John Doe`, to the `children` list. The user executes   
`add /n John Doe /g 3` command, adding a `Person` with `name` as `John Doe` to group 3, which is the `children`.

![PersonStep2](images/PersonStep2.png)

Step 3. The user executes `addin /g 3 /u 1 /i ...` to add a new income to index 1 of the `children` list, who is   
`John Doe`. This causes an income object to be added to the `IncomeList` of `John Doe`.

![PersonStep3](images/PersonStep3.png)

Step 4. The user now decides to have an overview of his family's finances by executing the `list` command. The `list`  
command will call `Family#list()`, which will go through each generation to sum up their incomes and expenditures and  
print that out.

![PersonStep4](images/PersonStep4.png)

#### Design considerations:

**Aspect: How to sort persons into logical groups:**

* **Alternative 1 \(current choice):** Have a `Family` object hold 3 `PersonList`s, one for each generation.
    * Pros: Only requires storage of one instance of each income and expenditure.
    * Cons: May have performance issues related to operations which work on every income and expenditure as it makes  
      them deeply nested.
* **Alternative 2:** Maintain the single `PersonList` with everyone inside, but give a tag to each `Person` to indicate
  which generation they belong to
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

Maybe for Jiarong {Suggest to show the process from `parsing several delimiters` to `finding the income/expenditure`
to `editing the value`.}

### [Proposed] Find feature

#### Proposed Implementation

The proposed find feature is facilitated by `Categories`, `Money(temp)`, `MoneyList(temp)`
and `Family`. The `Categories` is an enumeration of keys that is used as the expenditure categories. The `Money(temp)`
will have an additional integer attribute that acts as an index to a category. Additionally, the following operations
are implemented:

* `Categories#getLabel(index)` -- Returns the name of the category with this index.
* `Money(temp)#getCategory()` -- Returns the category index for this money object.
* `Money(temp)#getDescription()` -- Returns the description for this money object.
* `MoneyList(temp)#searchExpense(description, index)` -- Returns a list of expenditures having category index matching
  the index argument and description matching the expenditure's description.
* `MoneyList(temp)#searchIncome(description)` -- Returns a list of income having description matching the income's
  description.
* `Family#listExpenseSearch(description, index)` -- Lists all expenses matching the criteria.
* `Family#listIncomeSearch(description)` -- Lists all income with matching description.

Below is an example usage scenario and how the expenses of a category will be printed.

Step 1. Given that the application already has existing data and there is one person being tracked, Alice, who belongs
to the current generation. In this case `Family` would be initialised with one generation being tracked - myGen.

![FindIncomeExpenditure1](images/FindIncomeExpenditure1.png)

Step 2. The user executes `find /d Bills /c 1` command to search for income and expenditures with descriptions
containing "Bills". The `find` command will be parsed and calls
`Family#listExpenseSearch("Bills", 1)` and `Family#listSearch("Bills")` which would instantiate 2 temporary array list
for storing the results of the upcoming search.

![FindIncomeExpenditure2](images/FindIncomeExpenditure2.png)

Step 3. After the temporary array list for expenditure has been created, the existing generation will be iterated
for `Person` objects. The `expenditureList` for a person would be retrieved during that person's iteration
and `MoneyList(temp)#searchExpense("Bills", 1)` will be called as `expenditureList` extends `MoneyList(temp)`. This
method then iterates through the list and calls
`Money(temp)#getCategory()` and `Money(temp)#getDescription()` on each expenditure, collecting and returning the
expenditure if its category matches the given index and description matches the given description. The returned
expenditures are then appended to the temporary array list.

![FindIncomeExpenditure3](images/FindIncomeExpenditure3.png)

Step 4. After the temporary array list for income has been created, the existing generation will be iterated
for `Person` objects. The `incomeList` for a person would be retrieved during that person's iteration
and `MoneyList(temp)#searchIncome("Bills")` will be called as `incomeList` extends `MoneyList(temp)`. This method then
iterates through the list and calls
`Money(temp)#getDescription()` on each income, collecting and returning the income if its description matches the given
description. The returned incomes are then appended to the temporary array list.

![FindIncomeExpenditure4](images/FindIncomeExpenditure4.png)

Step 5. The iteration, collecting and appending to the temporary array list in step 3 and 4 is repeated until every
person has been iterated. Finally, `Categories#getLabel(1)` is called so that an appropriate message can be displayed to
the user, stating the name of the category, following by a series of print to display the expenditures in this category.

> :information_source: **Note:** If the `index` provided does not map to any existing categories,
> then it can be observed that there will never be any results returned. The `find` command will
> check the index provided using `Parser#checkValidCategory` before iterating `Family`. If the check
> fails, an error message will be displayed to the user instead of continuing with the execution.

The following sequence diagram shows how the `find` operation works after the `FindCommand` has been created
by [`CommandFactory`](#PlaceholderToCommandFactory):
![FindIncomeExpenditureSequence](images/FindIncomeExpenditureSequence.png)

#### Design considerations:

**Aspect: How to search through income and expenditures to be listed:**

* **Alternative 1 (current choice):** Maintain an individual array list for found income and expenditure
    * Pros: Easy and fast access of found results, no additional logic needed to separate income from expenditure
    * Cons: Additional memory management, needs to manage 2 array list.

* **Alternative 2 :** Maintain a single array list for both found income and expenditure
    * Pros: Easy to implement and less memory usage.
    * Cons: Additional logic check is needed to print the income and expenditure in a well formatted way

### [Proposed] Listing Categorised Expenditures Feature

#### Proposed Implementation

The proposed listing categorised expenditure feature is facilitated by `Categories`,
`Expenditure`, `ExpenditureList`  and `Family`. The `Categories` is an enumeration of keys that is used as the
expenditure categories. The `Expenditure` will have an additional integer attribute that acts as an index to a category.
Additionally, the following operations are implemented:

* `Categories#getLabel(index)` -- Returns the name of the category with this index.
* `Expenditure#getCategory()` -- Returns the category index for this expense.
* `ExpenditureList#getExpenseOfCategory(index)` -- Returns a list of expenditures having category index matching the
  index argument.
* `Family#listExpenseOfCategory(index)` -- Lists all expenses from the category index.

Below is an example usage scenario and how the expenses of a category will be printed.

Step 1. Given that the application already has existing data and there are two people being tracked, Alice and Bob, and
only Alice's expenses were added and categorised. Suppose that Alice is the main user and Bob is her father, then Alice
would belong to the current generation and Bob would belong to the parent generation. In this case `Family` would be
initialised with two generations being tracked - parents and myGen.

![ListCategorisedExpense0](images/ListCategorisedExpense0.png)

Step 2. The user executes `listcat /c 1` command to list all expenses in category `1`. The `listcat`
command will be parsed and calls `Family#listExpenseOfCategory(1)` which would instantiate a temporary array list for
storing the results of the upcoming search.

![ListCategorisedExpense1](images/ListCategorisedExpense1.png)

Step 3. After the temporary array list has been created, the generations being tracked will be iterated for `Person`
objects. The `expenditureList` for a person would be retrieved during that person's iteration
and `ExpenditureList#getExpenseOfCategory(1)` will be called as `expenditureList`
extends `ExpenditureList`. This method then iterates through the list and calls
`Expenditure#getCategory()` on each expenditure, collecting and returning the expenditure if its category matches the
given index. The returned expenditures are then appended to the temporary array list.

![ListCategorisedExpense2](images/ListCategorisedExpense2.png)

Step 4. The iteration, collecting and appending to the temporary array list in step 3 is repeated until every person has
been iterated. Finally, `Categories#getLabel(1)` is called so that an appropriate message can be displayed to the user,
stating the name of the category, following by a series of print to display the expenditures in this category.

> :information_source: **Note:** If the `index` provided does not map to any existing categories,
> then it can be observed that there will never be any results returned. The `listcat` command will
> check the index provided using `Parser#checkValidCategory` before iterating `Family`. If the check
> fails, an error message will be displayed to the user instead of continuing with the execution.

The following sequence diagram shows how the `listcat` operation works after a `ListCatCommand`
command object has been created by [`CommandFactory`](#Command-Execution):

![ListCategorisedExpenseSequence](images/ListCategorisedExpenseSequence.png)

#### Design considerations:

**Aspect: How to categorise expenses to be listed:**

* **Alternative 1 (current choice):** Adding a category attribute to expenses.
    * Pros: Easy to implement and less memory usage.
    * Cons: May have performance issues as it needs to iterate through every person's expenditure.

* **Altertive 2:** Maintain an array list for each category and store a copy of expenses.
    * Pros: Fast to print expenses in a category, no unnecessary look-ups.
    * Cons: Poor memory management, needs to store twice as many expenditures.

### Data Archiving

#### \[Proposed]Saving and loading feature

##### Proposed implementation

The proposed saving and loading mechanism is facilitated by `Family`. It loads records of `PersonList` for each logical
grouping inside family from a local file, process the data and adds the record to `Family` for the current session.
Saves the `PersonList` record of each `Family` grouping back into the local file upon exit of the program. It implements
the following operations:

* `Storage#checkFileExists()` -- Checks if `PlanITarium.txt` file exists in the local hard drive, creates one if it does
  not.
* `Storage#saveData()` -- Writes `PersonList` record of each `Storage` grouping into `PlanITarium.txt` file upon exit of
  the program.
* `Storage#loadData()` -- Opens and reads `PlanITarium.txt` for any existing `PersonList` records and updates the
  `PersonList` data in the current session for each logical group in `Family`.

Given below is an example scenario and how the save and load mechanism behaves at each step.

Step 1. The user launches the application. A `Storage` object will be initialised with an empty `PersonList` for each
`Family` grouping. The following sequence diagram shows how the `Storage` object is initialised

Step 2. The program will create a new `PersonList` for the session which calls `Storage#loadData()`, causing a check if
the local file `PlanITarium.txt` exists by calling `Storage#checkFileExists()` and creates one if it does not. The data
in the local file will be read, parsed and added to the empty `PersonList` in `Storage` by calling `PersonList` adding
operations. The data in the `PersonList` will then be returned to `PersonList` for each `Family` grouping in the current
session. The following sequence diagram shows how the loading operation works:

![StorageLoadSequence](images/StorageLoadSequence.png)

Step 3. The user then decides to exit the program by executing the command `bye`, `Storage#saveData` will be called. All
data in the `Family` object will be written to the local file `PlanITarium.txt` in the format of
`(group type) (user/operation) (Category) (Info)` which are to be read again when the program starts up. The following
Sequence diagram shows how the saving operation will work:

![StorageSaveSequence](images/StorageSaveSequence.png)

#### Design considerations:

**Aspect: When saving of data is executed:**

* **Alternative 1 (current choice):** Saving occurs just before the program exits.
    * Pros: Better performance as it only needs to iterate through every person's data once
    * Cons: Data will not be saved if the program crashes or exits unintentionally.

* **Alternative 2:** Saves the current data of each Person after every addition, deletion or update operation.
    * Pros: The local file will constantly be updated and lowers the risk of losing records
    * Cons: May have performance issue as it needs to iterate through every person's data to be saved after each
      operation.

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
