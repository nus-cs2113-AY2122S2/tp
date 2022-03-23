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
Adds new goods or order.

Format: 
* Adding order: `add o/ id/ID r/Receiver a/SHIPPING_ADDRESS`
* Adding goods: `add g/ oid/ORDER_ID gid/GOOD_ID n/NAME q/QUANTITY d/DESCRIPTION`

Note:
* `ID`, `GID`and `OID` are an integer uniquely represent the items.
* The `QUANTITY` is a non-negative integer.

Example of usage: 

`add o/ id/101 r/John Doe a/here`

`add g/ oid/101 gid/102 n/tables q/15 d/this is a lot of tables.`

Expected Output
```
Order 101 is added
15 tables (This is a lot of tables.) Has been added
```

### Removing inventory items: `remove`
Remove goods or order. 

Format: 
* Removing order: `remove o/ id/ID`
* Removing goods: `remove g/ id/ID q/QUANTITY`

Note:
* The `ID` is an integer uniquely represent the items.
* The `QUANTITY` is a non-negative integer.

Example of usage:

`remove g/ id/102 q/2`

`remove o/ id/101`

Expected output:

```
2 tables have been removed.
Order 101 has been removed.
```

### Getting a list of total inventory: `list`
To get a list of names of every inventory item.

Format: `list flag`

Note:
* The `flag` is either `o/` or `g/` to view either an order or good

Example of usage:

This is to list all current orders
`list o/`

This is to list all current goods
`list g/`

Expected output: 
```
List of orders:
    1. 101 - John Doe (here)
List of goods:
    1. 102 - tables
```

### Getting a description of inventory: `view`
View the details of a specific good, based on its ID number.

Format: `view flag id/ID`

Note:
* The `flag` is either `o/` or `g/` to view either an order or good
* The `ID` is an integer uniquely representing the corresponding items.

Example of usage:

This is to view order with id 1
`view o/ id/101`

This is to view good with id 1
`view g/ id/102`

Expected output:
```
Viewing order with id 101
Receiver: John Doe
Shipping address: here
Items in the order:
    1. 102 - tables
```
```
Viewing item with id 102
Item name: tables
Item description: This is a lot of tables
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

* Add order `add o/ id/ID r/RECEIVER a/SHIPPING_ADDRESS`
* Add good `add g/ oid/ORDER_ID gid/GOOD_ID n/NAME q/QUANTITY d/DESCRIPTION`
* Remove order: `remove o/ i/ID`
* Remove good: `remove g/ i/ID q/QUANTITY`
* List all orders: `list o/`
* List all goods: `list g/`
* View order: `view o/ id/ORDER_ID`
* View good: `view g/ id/GOOD_ID`
* Total quantity of goods: `total`
