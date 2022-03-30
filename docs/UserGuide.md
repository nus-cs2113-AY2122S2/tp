# User Guide

## Introduction

As online shopping becomes more convenient and transactions become easier, people tend to overshoot their budget
because they are not aware of their accumulated expenditure.
Spendvelope is designed to regularly remind the buyer of their preset spending limit for each month.


## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Spendvelope` from [here](link to be added).

## Features 
1. Add/delete record 
2. View record history list
3. Set spending limits and give warnings
4. View order summary(classified by summary)
5. Read/Store the data files
6. Search for expenses by keyword
7. Show help messages to users


### Adding a todo: `todo`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`


### Setting limit: `set`
Set a total spending limit for a month and display the warning
if the total expenditure exceeds the limit.

Format: `set LIMIT`

* The `LIMIT` can be any number greater than zero.
* The `LIMIT` is initialized as zero.

Example of usage: `set 500`

### Showing summary: `summary`
Show the summary table based on the record type.

Example of usage: `summary`

* In the summary table, records are classified into 5 types:
fashion, food, accessory, others, and subscription with detailed record information.
* The summary table also shows expenses on each category and the overall expenses.



## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary


* Add record `add i/ITEM_NAME p/PRICE d/DATE t/CATEGORY (if product) r/RENEWAL (if subscription)`
* Delete record `delete INDEX`
* Display record list `list`
* Find record by keyword `find<keyword>`
* Set limit `set LIMIT`
* Show help message `help`
* Show summary table based on types `summary`
* Exit the program `exit`