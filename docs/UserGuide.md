# User Guide

## Introduction

*Simplst* is a laptop app for managing warehouse inventory and processes, optimzed for use view a Command Line Interface
(CLI) for warehouse management workers.

* [Quick start] [#Quick Start]
* [Features] [#Features]
  * [Adding inventory items:] `add` [#Adding inventory items:]
  * [Removing inventory items:] `remove`[#Removing inventory items:]
  * [Getting a list of total inventory:] `list` [#Getting a list of total inventory:]
  * [Getting a description of inventory:] `description` [#Getting a description of inventory:]
  * [Getting the total number of inventory in the warehouse:] `total` [#Getting the total number of inventory 
  in the warehouse:]

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Simplst` from [here](http://link.to/duke).

## Features

### Adding inventory items: `add`
Adds a new item to the list of todo items.

Format: `add id/ID n/GOOD_NAME q/QUANTITY`

* The `ID` is an integer uniquely represent the items.
* The `QUANTITY` is a non-negative integer.

Example of usage: 

`add id/101 n/chairs q/100`

`add id/102 n/tables q/15`

### Removing inventory items: `remove`
Adds a new item to the list of todo items.

Format: `remove i/ID q/QUANTITY`

* The `ID` is an integer uniquely represent the items.
* The `QUANTITY` is a non-negative integer.

Example of usage:

`remove id/101 q/2`

Expected output:

`2 chairs have been removed.`

### Getting a list of total inventory: `list`
To get a list of names of every inventory item.

Format: `list`

Example of usage:

`list`

Expected output: 
```
List of inventory items:
    1. Chairs
    2. Wooden Tables
```

### Getting a description of inventory: `view`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.

Example of usage:

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

### Getting the total number of inventory in the warehouse: `total`
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
