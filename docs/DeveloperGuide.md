# Developer Guide

- [Acknowledgements](#acknowledgements)
- [Design](#design)
- [Implementation](#implementation)
- [Product scope](#product-scope)
  - [Target user profile](#target-user-profile)
  - [Value proposition](#value-proposition)
- [User stories](#user-stories)
- [Non-functional requirements](#non-functional-requirements)
- [Glossary](#glossary)
- [Instructions for manual testing](#instructions-for-manual-testing)

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design


Fig 1 - Architecture Diagram

The Architecture Diagram given above explains the high-level design of the App.

**Main components of the architecture:**
`Main Spendvelope`: Initializes and makes use of the services in each component.
`TextUi`: Interacts with the user.
`Parser`: Makes sense of user commands.
`Commands`: The collection of executable commands on the App.
`Storage`: Reads data from, and writes data to the hard disk.
`LimitManager`: Allows the user to set an expenditure limit. Holds this limit in memory.
`RecordManager`: Allows user to add, delete, view records. Holds this list in memory.
`ExpenseManager`: Tracks the total expenditure from all records.

## Implementation
add feature
Implementation
When command ‘add’ is called, a Record class is created and added to the Record List by RecordManager. Depending on the input, the program will either make a Product or a Subscription object that extends Record with its own properties. ExpenseManager will add the price to total expense by using addToExpense(price).
delete feature
Implementation
When command ‘delete’ is called, the corresponding Record will be deleted by the RecordManager from the Record List. ExpenseManager will subtract the price from total expense by using subtractFromExpense(Record.getPrice()).
set Spending Limit feature
Implementation
When command ‘set’ is called, the LimitManager class will be called to set the limit for the user. This limit will later trigger a warning if the user has exceeded it when they “add” another record to the program.
list feature
Implementation
When command ‘list’ is called, the listCommand will call getAllRecords() from RecordManager. The method will return all the arraylist of records that have been inputted by the user before.
help feature
Implementation
When command ‘help’ is called, the helpCommand will print all the instruction examples in order for the user to know what they can input to the program.
[Proposed] find feature
Proposed Implementation
The proposed find mechanism is facilitated by FindCommand, the FindCommand, extending the command, will call getFilteredRecords(String query) from RecordManager. The method will return the ArrayList of all records that have been filtered by the inputted query that has been inputted by the user.
[Proposed] Total Expense feature
Proposed Implementation
The proposed find mechanism is facilitated by FindCommand, the FindCommand, extending the command, will call getFilteredRecords(String query) from RecordManager. The method will return the ArrayList of all records that have been filtered by the inputted query that has been inputted by the user.

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
