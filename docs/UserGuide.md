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
    * [Adding quantity of a Good:](#adding-quantity-of-a-unit-good) `add g/`
    * [Removing quantity of a Good:](#removing-quantity-of-a-unit-good) `remove g/`
    * [Listing available Goods:](#listing-available-goods) `list g/`
    * [Viewing a Good:](#viewing-a-good) `view g/`
    * [Finding a Good:](#finding-a-good) `find`
  * [Order Commands](#order-commands)
  * [Order Commands](#order-commands)

##Explanation of key terms or symbols
| Terms or Symbols used                              | Further Explanation                                                                                                                          |  
|----------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------|
| Unit Good                                          | A template containing details of a good.<br/>A Unit Good contains: <br/>-unique SKU <br/>-name <br/>-description of unit good <br/>-capacity|
| SKU                                                | Stands for Stock-Keeping Unit. It is the unique unit number for a specific warehouse item. It can contain characters and numbers (e.g WC01). |
| Capacity                                           | Defined as Small, Medium and Large.<br/> SMALL: <br/>Medium: <br/>Large:                                                                     |
| Good                                               | Is a unit good but contains the quantity of the specific Unit Good. A Good contains: <br/>-quantity of the Unit Good                         |
| Order                                              | An order is for                                                                                                                                |
| Orderline                                          | An orderline the specific good required by the order. It is a Good but contains the quantity required to fulfill the good. An orderline contains: <br/>-quantity of Good required to fulfill the order|
| `Words in MarkUp`                                  | Used to highlight keywords used for commands and commands themself.                                                                          |
| <img src="img.png" alt="img" style="width:50px;"/> | Used to denote features for warehouse managers.                                                                                              |
| `*Optional*`                                       | Fields in MarkUp bounded by asteriks (*) are optional to be filled in, but should be replaced with a space ' ' instead.                      |

This User Guide is meant for both warehouse workers and managers to learn how to use Simplst and the features to 
improve efficiency in managing warehouse inventory and orders. 


## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of ***Simplst*** from [here](https://github.com/AY2122S2-CS2113T-T09-4/tp/releases/).
3. Copy the file to the folder you want to use as the home folder for Simplst
4. Open the terminal in the folder which contains Simplst.jar and run java -jar Simplst.jar. The Startup should look like below:
```
____________________________________________________________
Hello from
Simplst
What would you like to do?
New login. Please type the total number of goods your warehouse can hold
____________________________________________________________
```
6. Type the commands in the terminal and press `Enter` to execute them. For example: typing help and pressing `Enter` will show you the features
    available in Simplst and how to type the command into the command line.

## Features

### ***Unit Good Commands***

### Adding a Unit Good `add ug/`
Add a new unit good to the warehouse, creating a Good with quantity 0 in the process.

Format: `add ug/ sku/[SKU] n/[NAME] d/*[DESCRIPTION]* cap/[CAPACITY]`

Note: 
* Refer to the explanation on Key Terms and Symbols for what an SKU is
* Optional description field. If there are no descriptions, key in `d/` followed by a space before keying in the next parameter.

Example of adding a unit good:<br/>
Adding a Unit Good of sku WC1, with name Wooden Chair, description as Chair made of oak from Europe, and a capacity of Medium.<br/>

`add ug/ sku/WC1 n/Wooden Chair d/Chair made of oak from Europe cap/Medium`

Expected Output
```
Another command?
```
Exceptions: Adding another Unit Good with the same SKU will override the previous good with the same SKU, and the newer
good added will be present instead.

### Removing a Unit Good `remove ug/`
Removing a unit good from the warehouse.

Format: `remove ug/ sku/[SKU]`

Note:
* Usually used when there occurs a mistake when a Unit Good is added, or if the warehouse does not store the Unit Good
anymore

Example of removing a unit good:<br/>
Removing a Unit Good of sku WC1.<br/>

`remove ug/ sku/WC1`

Expected Output
```
Another command?
```

### Listing Unit Goods `list ug/`
Listing all Unit Goods present in the warehouse.

Format: `list ug/`

Example of listing Unit Goods:<br/>
`list ug/`

Expected Output
```
List of unit goods (in no order):
WC1 - Wooden Chair (Chair made of oak from Europe)
WC2 - Wooden Table (Table made of oak from Italy)
Another command?
```
### ***Good Commands***

### Adding quantity of a Good `add g/`
Adding the quantity of a Unit Good which is set to default to be 0.

Format: `add g/ sku/[SKU] qty/[QUANTITY]`

Example of adding quantity to a specific Unit Good:<br/>
Adding 30 Wooden Chairs. <br/>

`add g/ sku/WC1 qty/30`

Expected Output
```
Another command?
```

### Removing quantity of a Unit Good `remove g/`
Removing the quantity of a Unit Good from its previous value.

Format: `remove g/ sku/[SKU] qty/[QUANTITY]`

Note:
* Used when certain units of the good has been shipped out and thus quantity has to be decreased.

Example of removing quantity of a specific Unit Good:<br/>
Removing 10 Wooden Chairs. <br/>

`remove g/ sku/WC1 qty/10`

Expected Output
```
Another command?
```

### Listing available Goods `list g/`
Listing all available goods in the warehouse.

Format: `list g/`

Example of listing all Goods:<br/>

`list g/`

Expected Output
```
//Need to pull new version to check output
Another command?
```

### Finding a Good `find`
Finding a Good in the warehouse through the collective name of the Unit Good.

Format: `find n/[NAME]`

Example of finding a Good:<br/>
Finding if Wooden Chair exists in the warehouse.
`list n/Wooden Chair`

Expected Output
```
//Need to pull new version to check output
Another command?
```

### ***Order Commands***



---------------------------------------------------------------------------------------------------
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

| Action                               | Command to be keyed into the terminal                         |
|--------------------------------------|---------------------------------------------------------------|
| Add Unit Good                        | `add ug/ sku/[SKU] n/[NAME] d/*[DESCRIPTION]* cap/[CAPACITY]` |
| Remove Unit Good                     | `remove ug/ sku/[SKU]`                                        |
| List Unit Good                       | `list ug/`                                                    |
| Add Good quantity                    | `add g/ sku/[SKU] qty/[QUANTITY]`                             |
| Remove Good quantity                 | `remove g/ sku/[SKU] qty/[QUANTITY]`                          |
| List Goods (with quantity displayed) | `list g/`                                                     |
| Find Good                            | `find n/[NAME]\`                                              |

