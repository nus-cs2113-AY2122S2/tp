# User Guide

## Introduction

ClubInvMgr is a desktop CLI app for inventory management for CCA clubs, especially for fast typists who can accomplish tasks quickly by typing out commands.

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

### Understand an item: `desc`
Retrieve the details of a particular item of your interest from the current inventory by entering the index (1-based indexing).

Format: `desc INDEX`

Examples of usage:  
```
> desc 1
Name of Item: JBLFlip5
Description: Waterproof up to 3m, fully charged batteries can last for 5 hours, bluetooth enabled.
```

### Borrow an item: `borrow`
Borrow the item that you want for the duration between the start datte and end date.

Format: `borrow INDEX s/START_DATE e/END_DATE`  


Examples of usage:
```
> borrow 23 s/21-03-2021 e/23-03-2021 
Name of Item: JBLFlip5
Description: Waterproof up to 3m, fully charged batteries can last for 5 hours, bluetooth enabled.
```

### View present borrowings of a person: `view`
View the borrowings of a particular person

Format: `borrow INDEX s/START_DATE e/END_DATE`

Examples of usage:
```
> borrow 23 s/21-03-2021 e/23-03-2021 
Name of Item: JBLFlip5
Duration: From 21-03-2021 to 23-03-2021
Description: Waterproof up to 3m, fully charged batteries can last for 5 hours, bluetooth enabled.
```


### Adding a todo: `todo`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage:  
`todo n/Write the rest of the User Guide d/next week`  
`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
