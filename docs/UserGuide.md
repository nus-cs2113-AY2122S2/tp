# User Guide

## Introduction

*Simplst* is a laptop app for managing warehouse inventory and processes, optimzed for use view a Command Line Interface
(CLI) for warehouse management workers.

* [Quick start] [#Quick Start]
* [Features] [#Features]
  * [Adding inventory items:](#adding-inventory-items-add) `add` 
  * [Removing inventory items:](#removing-inventory-items-remove) `remove`
  * [Getting a list of total inventory:](#getting-a-list-of-total-inventory-list) `list` 
  * [Getting a view of inventory:](#getting-a-description-of-inventory-view) `view`
  * [Getting the total number of inventory in the warehouse:](#getting-the-total-number-of-inventory-in-the-warehouse-total) `total` 

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

Expected Output
```
100 chairs (Empty Description) Has been added
15 tables (Empty Description) Has been added
```

### Removing inventory items: `remove`
Adds a new item to the list of todo items.

Format: `remove i/ID q/QUANTITY`

* The `ID` is an integer uniquely represent the items.
* The `QUANTITY` is a non-negative integer.

Example of usage:

`remove id/101 q/2`

Expected output:

```
2 chairs have been removed.
```

### Getting a list of total inventory: `list`
To get a list of names of every inventory item.

Format: `list flag/`

* The `flag` is either `o` or `g` to view either an order or good

Example of usage:

This is to list all current orders
`list o/`

This is to list all current goods
`list g/`

Expected output: 
```
List of inventory items:
    1. Chairs
    2. Wooden Tables
```

### Getting a description of inventory: `view`
View the details of a specific good, based on its ID number.

Format: `view flag/ id/ID`

* The `flag` is either `o` or `g` to view either an order or good
* The `ID` is an integer uniquely representing the corresponding items.

Example of usage:

This is to view order with id 1
`view o/ id/1`

This is to view good with id 1
`view g/ id/1`

Expected output:
```
Viewing order with id 1
```
```
Viewing item with id 1
Item name: chairs
Item description: Empty Description
Item quantity: 86
```

### Getting the total number of inventory in the warehouse: `total`
Prints out the total number of goods in the entire warehouse.

Format: `total`

Example of usage:

`total`

Expected output:
```
There are 86 goods in total.
```

## Command Summary

* Add good `add id/ID n/GOOD_NAME q/QUANTITY`
* Remove good: `remove i/ID q/QUANTITY`
* List all orders: `list o/`
* List all goods: `list g/`
* View order: `view o/ id/ORDER_ID`
* View good: `view g/ id/GOOD_ID`
* Total quantity of goods: `total`
