# User Guide

## Introduction

{Give a product intro}

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `PlanITarium` from github page.
3. Run `java -jar PlanITarium.jar` command in the terminal of the folder where the
`PlanITarium` is.

## Features 

### Adding a person: `add`
Adds a person to the list to track finance

Format: `add /n NAME /g GROUP_INDEX` 

Example of usage: 

`add /n Alice /g 1`

### Removing a person: `delete`
Deletes a person from the list to track finance

Format: `delete /u USER_INDEX /g GROUP_INDEX`

Example of usage:

`delete /u 1 /g 1`

### Adding an income: `addin`
Adds an income under a stated user

Format: `addin /u USER_INDEX /g GROUP_INDEX /i INCOME
/d DESCRIPTION /c CATEGORY_INDEX /p (t/f)`

Example of usage:

`addin /u 1 /g 1 /i 200 /d salary /c 1 /p t`

### Deleting an income: `deletein`
Deletes an income from a stated user

Format: `deletein /u USER_INDEX /g GROUP_INDEX /r INCOME_INDEX`

Example of usage:

`deletein /u 1 /g 1 /r 1`

### Editing an income: `editin`
Edit an income under a stated user

Format: `editin /u USER_INDEX /g GROUP_INDEX /r INCOME_INDEX
/i INCOME /d DESCRIPTION /c CATEGORY_INDEX /p (t/f)`

Example of usage:

`editin /u 1 /g 1 /r 1 /i 200 /d salary /c 1 /p t`

### Adding an expenditure: `addout`
Adds an expenditure under a stated user

Format: `addout  /u USER_INDEX /g GROUP_INDEX /e EXPENDITURE
/d DESCRIPTION /c CATEGORY_INDEX /p (t/f)`

Example of usgae:

`addout  /u 1 /g 1 /e 20 /d candy /c 1 /p t`

### Deleting an expenditure: `deleteout`
Deletes an expenditure under a stated user

Format: `deleteout /u USER_INDEX /g GROUP_INDEX /r EXPENDITURE_INDEX`

Example of usage:

`deleteout /u 1 /g 1 /r 2`

### Editing an expenditure: `editout`
Edit an expenditure under a stated user

Format:	`editout /u USER_INDEX /g GROUP_INDEX /r EXPENDITURE_INDEX
/i EXPENDITURE /d DESCRIPTION /c CATEGORY_INDEX /p (t/f)`

Example of usage:

`editout /u 1 /g 1 /r 1
/i 30 /d sugar /c 1 /p t`

### Showing Remaining: `remain`
Shows the difference between total income and total expenditure.

Format: `remain`

### Listing all records by person: `list`
Shows a list of all income, expenditures input, by person.

Format:	`list`

### Listing all records by group: `list`
Shows a list of income, expenditures, and remain, by group

Format: `list /g GROUP_INDEX`

Example of usage: 

`list /g 1`

### Searching: `find`
Search for a string throughout all income and expenditure objects

Format:	`find /d USER_STRING /c CATEGORY_INDEX`

Example of usgae:

`find /d Candy /c 1`

### Listing categories: `listcat`
Shows a list of categories

Format:	`listcat`

### Exit: `bye`
Exits the program.

Format: 	bye

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: You can keep the file where your data stores, and transfer the file to another computer.

## Command Summary

* Add a person `add /n NAME	/g GROUP_INDEX`
* Remove a person `delete /u USER_INDEX /g GROUP_INDEX`
* Add an income `addin /u USER_INDEX /g GROUP_INDEX /i INCOME /d DESCRIPTION /c CATEGORY_INDEX /p (t/f)`
* Delete an income `deletein /u USER_INDEX /g GROUP_INDEX /r INCOME_INDEX`
* Edit an income `editin /u USER_INDEX /g GROUP_INDEX /r INCOME_INDEX /i INCOME /d DESCRIPTION /c 
CATEGORY_INDEX /p (t/f)`
* Add an expenditure `addout  /u USER_INDEX /g GROUP_INDEX /e EXPENDITURE
  /d DESCRIPTION /c CATEGORY_INDEX /p (t/f)`
* Delete an expenditure `deleteout /u USER_INDEX /g GROUP_INDEX /r EXPENDITURE_INDEX`
* Edit an expenditure `editout /u USER_INDEX /g GROUP_INDEX /r EXPENDITURE_INDEX /i EXPENDITURE /d DESCRIPTION /c 
CATEGORY_INDEX /p (t/f)`
* Show remain `remain`
* List records by person `list`
* List records by group `list /g GROUP_INDEX`
* List categories `listcat`
* Exit `bye`