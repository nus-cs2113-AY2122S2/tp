# User Guide

## Introduction

PlanITarium is a CLI application that helps you and your family to track your finances. It allows you to add individuals
and track their income and expenditure with a monthly view. There are many options for customization; you may group
individuals and track each group, and categorise your expenses for better financial planning. PlanITarium aims to be
your favourite personal expenditure tracker.

**Who this product is meant for:**

* Young adults who are looking for a fast and easy financial tracker.
* Individuals that needs a monthly overview on their financial status.
* Those who wish to track the financial status of their family members.
* Experienced typists who can operate the application on a terminal.

## Quick Start

1. Ensure that you are running Java 11 on your device.
2. Down the latest `PlanITarium.jar` from [releases](https://github.com/AY2122S2-CS2113T-T10-2/tp/releases).
3. Move the file into a folder that you want to use as the _home folder_.
4. Open a terminal in the _home folder_ and run `java -jar PlanITarium.jar`.
5. Type your commands into the terminal and press [Enter] to execute it.
6. Refer to [Features](#features) below for details on each command.

## Quick Notes

* Words in `UPPER_CASE` are the parameters to be supplied by you.
    * Example: `add /n NAME /g GROUP_INDEX` where `NAME` and `GROUP_INDEX` are the parameters.
* A forward slash `/`, appended by a letter, denotes a delimiter that is used to indicate a parameter used for the
  command. Each delimiter is used to indicate one parameter. The documentation for the commands will specify the
  required delimiters and the purpose of its parameter.
    * Example: `/n`, `/u`, `/i`
* The delimiter-parameter pair can be provided in any preferred order.
    * Example: `add /n Alice /g 2`, `add /g 2 /n Alice`
* Parameters enclosed in angle brackets and separated by a vertical bar `|` indicate that they have fixed values.
    * Example: `/p <T|F>` where the values accepted are `T` or `F`
* If you wish to use a forward slash `/` in the description field, input it enclosed in whitespaces.
    * Example: `Bought on / off switch` as a description

## Features

### Adding a person: `add`

> Adds a person to the list to track finance

Format: `add /n NAME /g GROUP_INDEX`

Example of usage:

`add /n Alice /g 1`

<br/>

### Removing a person: `delete`

> Deletes a person from the list to track finance

Format: `delete /u USER_INDEX /g GROUP_INDEX`

Example of usage:

`delete /u 1 /g 1`

<br/>

### Adding an income: `addin`

> Adds an income description and its value to a specific individual

Format: `addin /u USER_INDEX /g GROUP_INDEX /d DESCRIPTION /i INCOME`

* `USER_INDEX` refers to an index that is mapped to that individual.
    * The index can be obtained from the [list](#listing-all-records-by-person-list) command.
* `GROUP_INDEX` refers to an index that is mapped to the group that individual belongs to.
    * The index can be obtained from the [list](#listing-all-records-by-group-list) command.
* `DESCRIPTION` refers to the name or description of the income.
    * Duplicate descriptions are allowed and each record will be differentiated by an index.
* `INCOME` refers to the monetary value of the income.
    * No currency symbols should be included and values are in at most 2 decimal places.

Example of usage:

* Adding a Salary of $2,000 to an individual with the user index of 1, and in group 2.
    * Suppose that the user index 1 belong to Alice, and the group 2 to be myGen.
    * `addin /u 1 /g 2 /i 2000 /d Salary`

  ![addin-command-screenshot]() <!-- this is a placeholder -->

<br/>

### Deleting an income: `deletein`

> Deletes an income description and its value from a specific individual

Format: `deletein /u USER_INDEX /g GROUP_INDEX /r INCOME_INDEX`

* `USER_INDEX` refers to an index that is mapped to that individual.
    * The index can be obtained from the [list](#listing-all-records-by-person-list) command.
* `GROUP_INDEX` refers to an index that is mapped to the group that individual belongs to.
    * The index can be obtained from the [list](#listing-all-records-by-group-list) command.
* `INCOME_INDEX` refers to an index that is mapped to a specific income of an individual.
    * The index can be obtained from the [list](#listing-all-records-by-person-list) command.

Example of usage:

* Deleting the previous entry, Salary of $2,000, from Alice.
    * Suppose that the previous income entry was given an index of 1.
    * `deletein /u 1 /g 1 /r 1`

  ![deletein-command-screenshot]() <!-- this is a placeholder -->

<br/>

### Editing an income: `editin`

> Edit an income under a stated user

Format: `editin /u USER_INDEX /g GROUP_INDEX /r INCOME_INDEX /i INCOME /d DESCRIPTION /c CATEGORY_INDEX /p (t/f)`

Example of usage:

`editin /u 1 /g 1 /r 1 /i 200 /d salary /c 1 /p t`

<br/>

### Adding an expenditure: `addout`

> Adds an expenditure under a stated user

Format: `addout /u USER_INDEX /g GROUP_INDEX /e EXPENDITURE /d DESCRIPTION /c CATEGORY_INDEX /p (t/f)`

Example of usgae:

`addout /u 1 /g 1 /e 20 /d candy /c 1 /p t`

<br/>

### Deleting an expenditure: `deleteout`

> Deletes an expenditure under a stated user

Format: `deleteout /u USER_INDEX /g GROUP_INDEX /r EXPENDITURE_INDEX`

Example of usage:

`deleteout /u 1 /g 1 /r 2`

<br/>

### Editing an expenditure: `editout`

> Edit an expenditure under a stated user

Format:    `editout /u USER_INDEX /g GROUP_INDEX /r EXPENDITURE_INDEX /i EXPENDITURE /d DESCRIPTION /c CATEGORY_INDEX /p (t/f)`

Example of usage:

`editout /u 1 /g 1 /r 1 /i 30 /d sugar /c 1 /p t`

<br/>

### Showing Remaining: `remain`

> Shows the difference between total income and total expenditure.

Format: `remain`

### Listing all records by person: `list`

Shows a list of all income, expenditures input, by person.

Format:    `list`

<br/>

### Listing all records by group: `list`

> Shows a list of income, expenditures, and remain, by group

Format: `list /g GROUP_INDEX`

Example of usage:

`list /g 1`

<br/>

### Searching: `find`

> Search for a string throughout all income and expenditure objects

Format:    `find /d USER_STRING /c CATEGORY_INDEX`

Example of usgae:

`find /d Candy /c 1`

<br/>

### Listing categories: `listcat`

> Shows a list of categories

Format:    `listcat`

<br/>

### Exit: `bye`

> Exits the program.

Format:    bye

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: You can keep the file where your data stores, and transfer the file to another computer.

## Command Summary

| Command                | Format |
|------------------------|--------|
| Add a person           | test   |
| Remove a person        |        |
| Add an income          |        |
| Delete an income       |        |
| Edit an income         |        |
| Add an expenditure     |        |
| Delete an expenditure  |        |
| Edit an expenditure    |        |
| Show remain            |        |
| List records by person |        |
| List records by group  |        |
| List categories        |        |
| Exit                   |        |
| Show all commands      |        |



* Add a person `add /n NAME /g GROUP_INDEX`
* Remove a person `delete /u USER_INDEX /g GROUP_INDEX`
* Add an income `addin /u USER_INDEX /g GROUP_INDEX /i INCOME /d DESCRIPTION /c CATEGORY_INDEX /p (t/f)`
* Delete an income `deletein /u USER_INDEX /g GROUP_INDEX /r INCOME_INDEX`
* Edit an
  income `editin /u USER_INDEX /g GROUP_INDEX /r INCOME_INDEX /i INCOME /d DESCRIPTION /c CATEGORY_INDEX /p (t/f)`
* Add an expenditure `addout /u USER_INDEX /g GROUP_INDEX /e EXPENDITURE /d DESCRIPTION /c CATEGORY_INDEX /p (t/f)`
* Delete an expenditure `deleteout /u USER_INDEX /g GROUP_INDEX /r EXPENDITURE_INDEX`
* Edit an
  expenditure `editout /u USER_INDEX /g GROUP_INDEX /r EXPENDITURE_INDEX /i EXPENDITURE /d DESCRIPTION /c CATEGORY_INDEX /p (t/f)`
* Show remain `remain`
* List records by person `list`
* List records by group `list /g GROUP_INDEX`
* List categories `listcat`
* Exit `bye`