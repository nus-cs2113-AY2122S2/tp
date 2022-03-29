# User Guide

## Introduction

*Simplst* is a Warehouse Management System (WMS) for managing warehouse inventory and processes, optimzed for use view a Command Line Interface
(CLI) for warehouse management workers and warehouse management managers.

Simplst aims to improve efficiency and optimise standard warehouse tasks by having a CLI app which is simple to setup and quick to use.
Simplst will mainly be used to add, remove, list and view orders and goods for the warehouse.

### What is a Good?
In the context for Simplst, a Good contains:
* unique id as a number
* name
* quantity for the goods as a number
* description of good

### What is an Order?
In this context for Simplst, an order is a collection of goods that is for one reciepient and one address:
* unique id as a number
* reciever name
* address
* list of goods

An order should be first added to Simplst, then add the goods related to that order after.

This User Guide is meant for both workers and managers to learn how to use Simplst and the features to improve efficiency 

* [Quick start](#quick-start)
* [Features](#features)
  * [Adding inventory items:](#adding-inventory-items-add) `add` 
  * [Removing inventory items:](#removing-inventory-items-remove) `remove`
  * [Getting a list of total inventory:](#getting-a-list-of-total-inventory-list) `list` 
  * [Getting a view of inventory:](#getting-a-description-of-inventory-view) `view`
  * [Getting the total number of inventory in the warehouse:](#getting-the-total-number-of-inventory-in-the-warehouse-total) `total` 

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `Simplst` from [here](https://github.com/AY2122S2-CS2113T-T09-4/tp/releases/).
3. Copy the file to the folder you want to use as the home folder for Duke
4. Open the terminal in the folder which contains Duke.jar and run java -jar Duke.jar. The Startup should look like below:
```
____________________________________________________________
Hello from
Simplst
What would you like to do?
New login. Please type the total number of goods your warehouse can hold
____________________________________________________________
```
6. Type the commands in the terminal and press Enter to execute them. For example: typing help and pressing Enter will show you the features
    available in Simplst and how to type the command into the command line.

## Features

### Adding inventory items: `add`
Add a new good or order into Simplst.

Format: 
* Adding order: `add o/ oid/ID r/RECEIVER a/SHIPPING_ADDRESS`
* Adding goods: `add g/ oid/ORDER_ID gid/GOOD_ID n/NAME q/QUANTITY d/DESCRIPTION`

Note:
* `GID` is the unique id representing the [Good](#what-is-a-good). 
* `OID` is the unique id representing the [Order](#what-is-an-order).
* The `QUANTITY` is a non-negative integer.

Example of usage: 

Example of adding an order:
Adding an order of id 101, receiver John Doe, and an address of NUS
`add o/ oid/101 r/John Doe a/NUS`

Example of adding a good:
Adding 15 tables relating to order id 101, with a good id of 1, and a description of "this is a lot of tables."
`add g/ oid/101 gid/1 n/tables q/15 d/this is a lot of tables.`

Expected Output
```
Order 101 is added
15 tables (This is a lot of tables.) Has been added
```

#### Exceptions
1. When trying to add a good, ensure that an order already exists, else Simplst will ask you to try adding the good again.

### Removing inventory items: `remove`
Remove an existing good or order in Simplst. 

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
