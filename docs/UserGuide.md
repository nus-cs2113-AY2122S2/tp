# User Guide

## Introduction

*Simplst* is a Warehouse Management System (WMS) for managing warehouse inventory and order management, optimzed for use 
via a Command Line Interface (CLI) for warehouse management workers and managers.

Simplst aims to improve efficiency and optimise standard warehouse tasks by having a CLI app which is simple to 
setup and quick to use. Simplst will mainly be used to add, remove, list and view goods and orders for the warehouse.

##Contents page
* [Explanation of Key Terms or Symbols](#explanation-of-key-terms-or-symbols)
* [Quick start](#quick-start)
* [Features](#features)
  * [Unit Good Commands](#unit-good-commands)
    * [Adding a Unit Good:](#adding-a-unit-good) `add ug/`
    * [Removing a Unit Good:](#removing-a-unit-good) `remove ug/`
    * [Listing Unit Goods:](#listing-unit-goods) `list ug/`
  * [Good Commands](#good-commands)
    * [Adding quantity of a Unit Good:](#adding-quantity-of-a-unit-good) `add g/`
    * [Removing quantity of a Unit Good:](#removing-quantity-of-a-unit-good) `remove g/`
    * [Listing available Goods:](#listing-available-goods) `list g/`
    * [Viewing a Good:](#viewing-a-good) `view g/`
    * [Finding a Good:](#finding-a-good) `find`
  * [Order Commands](#order-commands)

##Explanation of key terms or symbols
| Terms or Symbols used | Further Explanation                                                                                                               |  
|----------------------|-----------------------------------------------------------------------------------------------------------------------------------|
| Unit Good            | A Unit Good contains:  <br/>-unique SKU <br/>-name <br/>-description of unit good <br/>-capacity                                  |
| SKU                  | Stands for Stock-Keeping Unit. It is the unique unit number for a specific warehouse item. It can contain characters and numbers. |
| Capacity             | Defined as Small, Medium and Large.<br/> SMALL: <br/>Medium: <br/>Large:                                                          |
| Good                 | Contains all the details of a Unit Good. A Good contains: <br/>-quantity of the Unit Good                                         |
| Order                | To be confirmed                                                                                                                   |             
| `Words in MarkUp`    | Used to highlight keywords used for commands and commands themself.                                                               |
| Icon                 | Used to denote features for warehouse managers.                                                                                   |

This User Guide is meant for both warehouse workers and managers to learn how to use Simplst and the features to 
improve efficiency in managing warehouse inventory and orders. 


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

### Unit Good Commands

#### Adding a Unit Good `add ug/`

#### Removing a Unit Good `remove ug/`

#### Listing Unit Goods `list ug/`


### Good Commands

#### Adding quantity of a Unit Good `add g/`

#### Removing quantity of a Unit Good `remove g/`

#### Listing available Goods `list g/`

#### Finding a Good `find`


### Order Commands

### Adding inventory items: `add`
Add a new orderline or order into Simplst.

Format: 
* Adding order: `add o/ oid/ID r/RECEIVER a/SHIPPING_ADDRESS`
* Adding goods: `add g/ oid/ORDER_ID gid/GOOD_ID n/NAME q/QUANTITY d/DESCRIPTION`

Note:
* `GID` is the unique id representing the [Good](#what-is-a-orderline). 
* `OID` is the unique id representing the [Order](#what-is-an-order).
* The `QUANTITY` is a non-negative integer.

Example of usage: 

Example of adding an order:
Adding an order of id 101, receiver John Doe, and an address of NUS
`add o/ oid/101 r/John Doe a/NUS`

Example of adding a orderline:
Adding 15 tables relating to order id 101, with a orderline id of 1, and a description of "this is a lot of tables."
`add g/ oid/101 gid/1 n/tables q/15 d/this is a lot of tables.`

Expected Output
```
Order 101 is added
15 tables (This is a lot of tables.) Has been added
```

#### Exceptions
1. When trying to add a orderline, ensure that an order already exists, else Simplst will ask you to try adding the orderline again.

### Removing inventory items: `remove`
Remove an existing orderline or order in Simplst. 

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
* The `flag` is either `o/` or `g/` to view either an order or orderline

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
View the details of a specific orderline, based on its ID number.

Format: `view flag id/ID`

Note:
* The `flag` is either `o/` or `g/` to view either an order or orderline
* The `ID` is an integer uniquely representing the corresponding items.

Example of usage:

This is to view order with id 1
`view o/ id/101`

This is to view orderline with id 1
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
* Add orderline `add g/ oid/ORDER_ID gid/GOOD_ID n/NAME q/QUANTITY d/DESCRIPTION`
* Remove order: `remove o/ i/ID`
* Remove orderline: `remove g/ i/ID q/QUANTITY`
* List all orders: `list o/`
* List all goods: `list g/`
* View order: `view o/ id/ORDER_ID`
* View orderline: `view g/ id/GOOD_ID`
* Total quantity of goods: `total`
