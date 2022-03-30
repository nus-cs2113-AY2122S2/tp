# Developer Guide

## Content Page

* [Introduction](#introduction)
    * 
* [Quick start](#quick-start)
* [Command summary (Expenses)](#command-summary-expenses)
* [Command summary (Credit Card)](#command-summary-credit-card)
* [Features](#features)
    * [Expenses](#expenses)
        * [Display list of currently available commands for expenses: `help`](#display-a-list-of-commands-for-expenses)
        * [Add an expenditure to your program: `add`](#add-an-expenditure-to-your-program-add)
        * [Display current list of expenditures: `list` ](#display-current-list-of-expenditures-list)
        * [Modify an expenditure on your list: `update`](#modify-an-expenditure-on-your-list-update)
        * [Removing an expenditure on your list: `delete`](#removing-an-expenditure-on-your-list-delete)
        * [Calculations that MindMyMoney provide: `calculate`](#calculations-that-MindMyMoney-provide-calculate)
            * [Expenditure per month: `calculate /epm`](#expenditure-per-month-calculate-epm)
        * [Exiting MindMyMoney application: `bye`](#exiting-MindMyMoney-application-bye)
    * [Credit Card](#credit-card)
        * [Display list of currently available commands for credit card: `help`](#display-list-of-currently-available-commands-for-credit-card-help)
        * [Add a credit card to your program: `add`](#add-a-credit-card-to-your-program-add)
        * [Display current list of credit cards: `list` ](#display-current-list-of-expenditures-list)
        * [Modify a credit card on your list: `update`](#modify-a-credit-card-on-your-list-update)
        * [Removing a credit card on your list: `delete`](#removing-a-credit-card-on-your-list-delete)
    * [Saving the data](#saving-the-data)
* [FAQ](#faq)
* [Acknowledgements](#acknowledgements)

## Introduction

**MindMyMoney** (M<sup>3</sup>) is a desktop app for managing users' personal finances, optimized for use via a
Command Line Interface (CLI). With the application, users can track their expenses across multiple payment methods,
calculate monthly expenditure, and set financial goals. The application is targeted at students looking to start
managing their personal finances.

### Purpose
This document specified architecture and software design decisions for the application, MindMyMoney.
### Scope
This describes the software architecture and software design decisions for the implementation
of MindMyMoney. The intended audience of this document is the developers, designers, and
software testers of MindMyMoney.
### Acknowledgements
We would like to thank [AddressBook-3](https://se-education.org/addressbook-level3/) for assisting us in developing
MindMyMoney.
### Using the User Guide
Along the guide you may encounter several icons. These icons will provide several useful information.
> **💡 Note:**
>- This tells you that there is additional information that is useful when you are using the application.

> **⚠️Warning⚠️**
> - This tells you that there is some **important** information you should take note of to prevent issues from arising
    when you are using the application.  

Click on the hyperlinks in the [content page](#Content-Page) to quickly navigate the developer's guide.

## Design
### Technologies used
**MindMyMoney** is written fully in **Java 11** using  Object-Oriented Programming (OOP) paradigm to help structure and
organise the code. This enables the efficiency of future improvements and revisions.
Data stored in the application is saved into test files locally on the user's device.  

### Architecture Overview
![architecture diagram](images/architecture_diagram.png)  
Fig 1 - Architecture Diagram

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

### Component Overview 
The major code is broken down into components for better abstraction. 
The sections below give more details for each component.

### UI component
The source code can be found in [Ui.java](https://github.com/AY2122S2-CS2113T-T10-4/tp/blob/master/src/main/java/seedu/MindMyMoney/Ui.java)

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
The source code can be found in [Parser.java](https://github.com/AY2122S2-CS2113T-T10-4/tp/blob/master/src/main/java/seedu/MindMyMoney/Parser.java)

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
The source can be found in [command](https://github.com/AY2122S2-CS2113T-T10-4/tp/blob/master/src/main/java/seedu/MindMyMoney/command)

![command_diagram](images/command_diagram.png)
<br/> Fig 5 - Command Diagram

The Command component consists of `Command` abstract class, `AddCommand`, `ByeCommand`, `DeleteCommand`, `HelpCommand`
, `ListCommand`and `UpdateCommand` that extends the `Command` class.

The Command component:
- Provides all the Command classes which can be instantiated by `Parser.parseCommand()`. The Command objects can then be 
executed. Only 1 Command object can be created.
- Includes a `.executeCommand()` method in each Command classes which performs the relevant command and throws 
exceptions if an error is encountered. The error is then handled.

### Storage component 
The source can be found in [Storage.java](https://github.com/AY2122S2-CS2113T-T10-4/tp/blob/master/src/main/java/seedu/MindMyMoney/Storage.java)

![storage_diagram](images/storage_diagram.png)
<br/> Fig 6 - Storage Diagram

The Storage component consists of `Storage` class.

The Storage component:
- `MMM` class initialises a `Storage` object upon start up. The `Storage` class consists of 
`load()` and `save()` methods. 
- Concurrently, `MMM` will call the `load()` method and load any data that is stored on the hard disk.
- `MMM` calls the `save()` method and stores remaining data onto the hard disk when the program exits.


## Implementation
This section describes some noteworthy details on how certain features of MindMyMoney are implemented.

### Add Command Component
**API Reference**: `AddCommand.java`  

The Add Command component allows users to add in an expenditure, a new credit card or their income using a single command. 
This component provides speed and ease of use by only requiring a single line of input.

### Subcomponents
The Add Command component consists of 3 subcomponents. These features are differentiated by their flags.
- Add expenditure component.
- Add credit card component.
- Add income component.

The sequence diagram below shows the interactions of when an `AddCommand` is parsed.
![add_command_sequence_diagram](images/Add_Command_Sequence_Diagram.png)

Below is an example scenario showing how the AddCommand behaves at each step.
1. The `Parser` component parses user input and returns the new `AddCommand` object to the
   `MindMyMoney`.
2. `AddCommand` instantiates `addInput`, `expenditureList`, `creditCardList`, `incomeList`.
3. The application invokes `Addcommand.executeCommand()` to execute user instruction.
4. If user input contains credit card flag, the application executes `addCreditCard()`.
5. Else if user input contains income flag, the application executes `addIncome()`.
6. Else the application executes `addExpenditure()`.
7. The application then returns to the Parser component.

### Add Credit Card Command
MindMyMoney allow users to track their different payment methods, A user can add and track user expenditure. A user can add in a new expenditure
by specifying the payment method, the category, the description of the item, the cost of the item and the date of
purchase.


### Add Expenditure Command
A key functionality of MindMyMoney is the ability to add and track user expenditure. A user can add in a new expenditure
by specifying the payment method, the category, the description of the item, the cost of the item and the date of 
purchase.

#### Current Implementation
The sequence diagram below shows the interactions of different subcomponents of the system when adding an expenditure
to the list.  
![add_expenditure_command_sequence_diagram](images/Add_Expenditure_Command_Sequence_Diagram.png)  
  
The add expenditure command is facilitated by the `AddCommand`. By running the command `add` with its relevant flags, 
`Parser` will construct a new `AddCommand` which will be used to execute users input.

1. During the execution, `addExpenditure()` will parse through user input to obtain the `PAYMENT_METHOD`, `CATEGORY`,
   `DESCRIPTION`, `AMOUNT` and `TIME` fields.
2. Once all the fields are obtained, `addExpenditure()` will run tests for its respective fields and capitalise the 
   `PAYMENT_METHOD`, `CATEGORY` input.
3. The `addExpenditure()` object instantiates a new `Expenditure` object with the aforementioned 5 fields and adds them
   into the `ExpenditureList`.
4. The `addExpenditure()` object prints a list to show the user what it has saved.
5. The `addExpenditure()` returns to `AddCommand`.

#### Design considerations
Aspect: How to ask user for the 5 fields of input
* Alternative 1 (current choice): User is asked to put in all 5 fields at once, separated using flags.
    * Pros: Faster input, user can enter an expenditure using a single input.
    * Cons: User must be able to remember all the flags and its sequence.

* Alternative 2: User is asked iteratively to put in all 5 fields, prompted by a message after each input.
    * Pros: Beginner friendly, easily understandable, no need to remember flags.
    * Cons: Slower, implementation when user is familiar with the application.

### Calculate Command feature

To enable users to view their finances in a more meaningfully, MindMyMoney does calculations to present financial data
that is actionable for the users.

![calculate_command_sequence_diagram](images/gif_loading.gif)

### List Command feature
To enable users to view their current list of added expenditures. 

#### Design considerations
Aspect: How to make the list command easily tested using JUnit testing
* Alternative 1 (current choice): Abstract the conversion of Expenditure to String in a separate listToString() method
  * Pros: Easily tested using JUnit testing, by checking the String that the listToString() method returns
  * Cons: Added layer of abstraction that may be deemed redundant

* Alternative 2: Print directly in the ListCommand.executeCommand() method 
  * Pros: Easily implemented with lesser lines of code as the code is minimalist
  * Cons: JUnit testing would require I/O redirection prior to checking the output matches expectations

![list_command_sequence_diagram](images/List_Command_Sequence_Diagram.png)

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
