# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `AlloNUS` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

### Main Menu Features

#### ...
...

Format: `add ...`

* ...
* ...

Example of usage:

`...`

`...`

### Expense Tracker Features
####List out all expenses: `list`
Lists out all currently tracked expenses in a list using the keyword `list`. Each entry shows
its current index in the list, followed by the date, amount, category and any remarks for each expense 
made.

Format: `list`

**Example of usage:**
`list`

**Expected outcome:**
```
Here are the expenses you have made so far:
 1. 15/3/2022|9.50|Movie|Spiderman: No Way Home
 2. 14/3/2022|4.30|Food|Chicken rice for lunch
```
#### Adding an expense: `add`
Adds a new expense to the list of expenses. The keyword `add` is used followed by the date, 
amount, category and remarks of a given expense, using the delimiters of `d/` , `a/`,  `c/` and `r/`
respectively.

Format: `add d/DATE a/AMOUNT c/CATEGORY r/REMARKS`


**Example of usage:**

`add d/15/3/2022 a/9.50 c/Movie r/Spiderman: No Way Home`

**Expected Outcome:**
```
Added 15/3/2022|9.50|Movie|Spiderman: No Way Home
```
####Delete an expense: `rm`
Deletes a specific expense record that currently exists in the list using its index. Users may choose
to `list` out the expenses first before deleting to verify its index. After deleting an expense
record, it will be shown before it is removed from the list.

Format: 
`rm INDEX`

**Example of usage:**

`rm 2`

**Expected outcome:**
```
Deleted entry: 14/3/2022|4.30|Food|Chicken rice for lunch
```

### Study Manager Features

#### Adding a module: `add`
Adds a new module to the list of modules.

Format: `add ...`

* ...
* ...

Example of usage: 

`...`

`...`

### Contacts Tracker Features

#### Adding a contact: `add`
Adds a new contact to the list of contacts.

Format: `add ...`

* ...
* ...

Example of usage:

`...`

`...`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
