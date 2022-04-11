# User Guide

## Table of contents
1. [Introduction](#introduction)
2. [Quick Start](#quick-start)
3. [Caveats](#caveats)
4. [Features](#features)
    1. [Add an Item](#add-an-item-add)
    2. [Search for Items](#search-for-items-search)
    3. [List All Items](#list-all-items-list)
    4. [Borrow an Item](#borrow-an-item-borrow)
    5. [List Current Borrowings](#list-current-borrowings-listcb)
    6. [List Future Borrowings](#list-future-borrowings-listfb)
    7. [List Overdue Borrowings](#list-overdue-borrowings-listob)
    8. [List Available Borrowings](#list-available-borrowings-listab)
    9. [Get Description of Item](#get-description-of-item-desc)
    10. [Delete an Item](#delete-an-item-delete)
    11. [Edit an Item](#edit-an-item-edit)
    12. [Cancel a future borrowing](#cancel-a-future-borrowing-cancel)
    13. [Exit](#exit-exit)
5. [FAQ](#faq)
6. [Command Summary][(#command-summary)]

## Introduction

ClubInvMgr is a desktop CLI app for inventory management for CCA clubs, especially for fast typists who can accomplish tasks quickly by typing out commands.

## Warning

Do not run the program between transition of days (11.59pm to 12.01am). Save your program and exit before, and relaunch after.

## Quick Start

1. Ensure that you have Java 11 or above installed.
1. Download the latest version of `InvMgr` from [here](todo).TODO

## Features

### Add an Item: `add`

Adds an item.

**Format:**

`add n/NAME q/QUANTITY d/DESCRIPTION`

**Arguments**


| Argument Flag | Argument Name |                  Accepted Values                   |          Meaning           |
|:-------------:|:-------------:|:--------------------------------------------------:|:--------------------------:|
|      n/       |     NAME      |                       String                       |    Name of Item to add     |
|      q/       |   QUANTITY    |                  Positive Integer                  |   Amount of Item to add    |
|      d/       |  DESCRIPTION  |                       String                       | Description of Item to add |

**Caveats**

* All 3 arguments must be present.

**Examples of usage:**

```
> add n/Chalkboard q/1 d/Draw using chalk
```

### Search for Items: `search`

Search for items based on at least one of the following:
1. Name
2. Description

Returns a list of items that contains the search terms. (case-insensitive)

**Format:**

`search [n/NAME] d/DESCRIPTION`

`search n/NAME [d/DESCRIPTION]`

**Arguments:**

| Argument Flag | Argument Name |                  Accepted Values                   |            Meaning            |
|:-------------:|:-------------:|:--------------------------------------------------:|:-----------------------------:|
|      n/       |     NAME      |                       String                       |    Name of Item to search     |
|      d/       |  DESCRIPTION  |                       String                       | Description of Item to search |

**Caveats:**

* Both `NAME` and `DESCRIPTION` should not be empty (At least one present).
* Using `n/` and/or `d/` without any values will result in ignored arguments  (`search n/NAME d/` will only search name, description will not be considered).
* If only one of `n/NAME` or `d/DESCRIPTION` is entered, only the entered argument will be considered when matching.

**Examples of usage:**

```
> search d/ Draw
Here are the items matching your search terms: 
1. Chalkboard | 1 | Draw using cha...
2. Whiteboard | 1 | Draw using mar...
3. Markers | 1 | To draw

> search d/ Draw
Here are the items matching your search terms: 
1. Chalkboard | 1 | Draw using cha...
2. Whiteboard | 1 | Draw using mar...

> search d/chalk n/board
Here are the items matching your search terms: 
1. Chalkboard | 1 | Draw using cha...
```

### List All Items: `list`

List all items in the inventory.

**Format:**

`list`

**Arguments:**

None

**Caveats:**

Arguments after `list` will be ignored. i.e. `list foo` will behave the same way `list` does.

**Examples of usage:**

```
> list
Name | Quantity	|
VGA Cable | 1 
HDMI Cable | 2
```

### Borrow an Item: `borrow`

Borrow the item that you want for the duration between the start date and end date.

**Format:** 

`borrow i/ITEM_INDEX q/QUANTITY s/START_DATE e/END_DATE p/BORROWER_NAME`

**Arguments:**

| Argument Flag | Argument Name | Accepted Values  |             Meaning             |
|:-------------:|:-------------:|:----------------:|:-------------------------------:|
|      i/       |  ITEM_INDEX   | Positive Integer | The index of the item to borrow |
|      q/       |   QUANTITY    | Positive Integer |  The amount of item to borrow   |
|      s/       |  START_DATE   | Date(yyyy-MM-dd) |     The start date of loan      |
|      e/       |   END_DATE    | Date(yyyy-MM-dd) |      The end date of loan       |
|      p/       | BORROWER_NAME |      String      |        Name of borrower         |

**Caveats:**

* `ITEM_INDEX` should be within one of the index for ItemList.
* `QUANTITY` should be >= 0.
* The `START_DATE` and `END_DATE` must be in YYYY-MM-DD format.
* Each item can only be borrowed for a maximum of 7 days.
* `END_DATE` must be either the same as `START_DATE` or a later date.
* Borrower will borrow at `START_DATE` 00:01 HRS and return at `END_DATE` 2359 HRS.

**Examples of usage:**
```
> borrow i/1 q/5 s/2022-03-21 e/2022-03-25 p/John Smith
You have successfully borrowed the following item:
Name of Item: JBLFlip5
Name of Borrower: John Smith
Borrow Duration: 2022-03-21 to 2022-03-25
Borrow Quantity: 5
```

### List Current Borrowings: `listcb`

List all items that are currently being borrowed. You can narrow down the list by entering an optional argument of the borrower's name.

**Format:**

`listcb`: List all items that are currently being borrowed.  

`listcb p/BORROWER_NAME`: List all items that are currently being borrowed by BORROWER_NAME.

**Arguments:**

| Argument Flag | Argument Name | Accepted Values |            Meaning             |
|:-------------:|:-------------:|:---------------:|:------------------------------:|
|      p/       | BORROWER_NAME |     String      | Name of borrower to search for |

**Caveats:**  
None  

**Examples of usage: (Assuming today's date is ***2021-03-18***)**

```
> listcb
Name of Item: Trolley
Name of Borrower: Sally
Borrow Duration: 2021-03-17 to 2021-03-20
Borrow Quantity: 5

Name of Item: JBLFlip5
Name of Borrower: Sally
Borrow Duration: 2021-03-16 to 2021-03-20
Borrow Quantity: 1

> listcb p/Sally
Name of Item: Trolley
Name of Borrower: Sally
Borrow Duration: 2021-03-17 to 2021-03-20
Borrow Quantity: 1

Name of Item: JBLFlip5
Name of Borrower: Sally
Borrow Duration: 2021-03-17 to 2021-03-19
Borrow Quantity: 1
```

### List Future Borrowings: `listfb`

List all items that will be borrowed in the future. You can narrow down the list by entering an optional argument of the borrower's name.

**Format:**

`listfb`: List all future borrowings.  

`listfb p/BORROWER_NAME`: List all future borrowings for BORROWER_NAME

**Arguments:**

| Argument Flag | Argument Name | Accepted Values |            Meaning             |
|:-------------:|:-------------:|:---------------:|:------------------------------:|
|      p/       | BORROWER_NAME |     String      | Name of borrower to search for |

**Caveats:**  
None

**Examples of usage (Assuming today's date is ***2021-03-18***):**
```
> listfb
Name of Item: Trolley
Name of Borrower: Sally
Borrow Duration: 2021-03-19 to 2021-03-21
Borrow Quantity: 5

Name of Item: JBLFlip5
Name of Borrower: John Smith
Borrow Duration: 2021-03-21 to 2021-03-23
Borrow Quantity: 1

Name of Item: JBLFlip5
Name of Borrower: Sally
Borrow Duration: 2021-03-29 to 2021-04-01
Borrow Quantity: 1
```

If there are no future borrowings, the `listfb` command will return:

```
> listfb
There are no future borrowings.
```

```
> listfb p/Sally
Name of Item: Trolley
Name of Borrower: Sally
Borrow Duration: 2021-03-19 to 2021-03-21
Borrow Quantity: 5

Name of Item: JBLFlip5
Name of Borrower: Sally
Borrow Duration: 2021-03-29 to 2021-04-01
Borrow Quantity: 1
```

If the person does not exist in the borrowings, the `listfb p/BORROWER_NAME` will return:
```
> listfb p/David
There are no future borrowings for David.
```

### List Overdue Borrowings: `listob`

List all items should have been returned but have yet to be. You can narrow down the list by entering an optional argument of the borrower's name.

**Format:**

`listob`: List all overdue borrowings. 

`listob p/BORROWER_NAME`: List all overdue borrowings by BORROWER_NAME

**Arguments:**

| Argument Flag | Argument Name | Accepted Values |            Meaning             |
|:-------------:|:-------------:|:---------------:|:------------------------------:|
|      p/       | BORROWER_NAME |     String      | Name of borrower to search for |

**Caveats:**  
None

**Examples of usage (Assuming today's date is ***2021-03-31***):**

```
> listob
Name of Item: Trolley
Name of Borrower: Sally
Borrow Duration: 2021-03-25 to 2021-03-30
Borrow Quantity: 5

Name of Item: JBLFlip5
Name of Borrower: John Smith
Borrow Duration: 2021-03-21 to 2021-03-23
Borrow Quantity: 1

Name of Item: JBLFlip5
Name of Borrower: Sally
Borrow Duration: 2021-03-24 to 2021-03-26
Borrow Quantity: 1
```

If there are no overdue borrowings, the `listob` command will return:

```
> listob
There are no overdue borrowings.
```

```
> listob p/Sally
Name of Item: Trolley
Name of Borrower: Sally
Borrow Duration: 2021-03-25 to 2021-03-30
Borrow Quantity: 5

Name of Item: JBLFlip5
Name of Borrower: Sally
Borrow Duration: 2021-03-24 to 2021-03-26
Borrow Quantity: 1
```

If the person does not exist in the borrowings, the `listob p/BORROWER_NAME` will return:

```
> listob p/David
There are no overdue borrowings for David.
```

### List Available Borrowings: `listab`

List all items that is available all the time between a start date and an end date. 

**Format:**

`listab s/START_DATE e/END_DATE`: List all available borrowings between `START_DATE` and `END_DATE`.

**Arguments:**

| Argument Flag | Argument Name | Accepted Values  |                       Meaning                       |
|:-------------:|:-------------:|:----------------:|:---------------------------------------------------:|
|      s/       |  START_DATE   | DATE(yyyy-MM-dd) | Starting date to search for all Item's availability |
|      e/       |   END_DATE    | DATE(yyyy-MM-dd) |   End date to search for all Item's availability    |

**Caveats:**

* `START_DATE` and `END_DATE` must be in `YYYY-MM-DD` format.

**Examples of usage:**

```
> listab s/2022-06-06 e/2022-06-06
Here are the items available for borrowing:
1.Chalkboard | 1
```

If there are no items available for borrowings, the `listab` command will return:

```
> listab s/2022-03-31 e/2022-04-01
Here are the items available for borrowing:
Sorry. There are no items available for borrowings.
```

### Get Description of Item: `desc`

Retrieve the details of a particular item of your interest from the current inventory by entering the index (1-based indexing).

**Format:** 

`desc INDEX`

**Arguments:**

| Argument Flag | Argument Name | Accepted Values  |          Meaning          |
|:-------------:|:-------------:|:----------------:|:-------------------------:|
|       -       |     INDEX     | Positive Integer | Index of Item in the list |

**Caveats:**

None.

**Examples of usage:**

```
> desc 1
Name of Item: JBLFlip5
Description: Waterproof up to 3m, fully charged batteries can last for 5 hours, bluetooth enabled.
```

### Delete an Item: `delete`

Delete an item by entering the index (1-based indexing).

**Format:** 

`delete INDEX`

**Arguments:**

| Argument Flag | Argument Name | Accepted Values  |               Meaning               |
|:-------------:|:-------------:|:----------------:|:-----------------------------------:|
|       -       |     INDEX     | Positive Integer | Index of Item to delete in the list |

**Caveats:**

None.

**Examples of usage:**

```
> list
Here are the items in your list:
1.Paper | 5
2.Markers | 3
3.Whiteboard | 1
4.Chalkboard | 1

Enter command: 
> delete 1
Paper | 5 has been deleted.
```

### Edit an Item: `edit`
Edit an item by entering the index (1-based indexing). Then, indicate the fields to be changed.

**Format:**

`edit INDEX n/NAME [q/QUANTITY [r/ +|-]] [d/DESCRIPTION]`

`edit INDEX [n/NAME] q/QUANTITY [r/ +|-] [d/DESCRIPTION]`

`edit INDEX [n/NAME] [q/QUANTITY [r/ +|-]] d/DESCRIPTION`

The arguments:
1. `NAME` - changes the name of an item
2. `QUANTITY` - changes the quantity of an item. Can be combined with `r/`, see 4.
3. `DESCRIPTION` - changes the description of an item.
4. `r/ +|-` (used only with quantity argument) - the Item's quantity will be added or subtracted from the specified quantity. Takes only two values.

**Arguments:**

| Argument Flag | Argument Name | Accepted Values  |                                           Meaning                                           |
|:-------------:|:-------------:|:----------------:|:-------------------------------------------------------------------------------------------:|
|       -       |     INDEX     |      String      |                              Index of item to edit in the list                              |
|      n/       |     NAME      |      String      |                                      New name for item                                      |
|      q/       |   QUANTITY    | Positive Integer |                                    New quantity for item                                    |
|      r/       |   RELATIVE    |    `+` or `-`    | Will change the quantity relative to the item's current quantity. (`+` adds, `-` subtracts) |
|      d/       |  DESCRIPTION  |      STRING      |                                  New description for item                                   |

**Caveats:**

* `NAME`, `DESCRIPTION`, and `QUANTITY` should not be empty (At least one present).
* Using `n/`, `d/`, `q/` and `r/` without any values will result in ignored arguments (`edit 1 n/NAME d/` will only edit name, description will not be considered).
* Only entered arguments will be considered when editing the values, e.g. specifying `NAME` only will change only the name of the item of interest.

**Examples of usage:**

```
> list
Here are the items in your list:
1.Markers | 3
2.Whiteboard | 1
3.Chalkboard | 1

> edit 1 n/Pencils
Item at index 1 has been modified.
Before: Markers | 1 | To draw
After: Pencils | 1 | To draw

> edit 1 n/Markers q/5 r/ +
Item at index 1 has been modified.
Before: Pencils | 1 | To draw
After: Markers | 6 | To draw

> edit 1 q/5 r/- d/To draw on whiteboard
Item at index 1 has been modified.
Before: Markers | 6 | To draw
After: Markers | 1 | To draw on whi...
```

### Cancel a future borrowing: `cancel`

Cancels future borrowing made by a specific person. It is only possible to cancel a borrowing with a person name and the index of his future borrowings.

**Format:**

`cancel p/BORROWER_NAME i/INDEX`

**Arguments:**

| Argument Flag | Argument Name | Accepted Values  |                                                            Meaning                                                             |
|:-------------:|:-------------:|:----------------:|:------------------------------------------------------------------------------------------------------------------------------:|
|      p/       | BORROWER_NAME |      String      |                                                 Name of borrower to search for                                                 |
|      i/       |     INDEX     | Positive Integer | Index of the future borrowing made by `BORROWER_NAME`. Index are based off the result of the command `listfb p/BORROWER_NAME`. |

**Caveats:**

None?

**Examples of usage:**

```
> listfb p/Tom
Here is a list of future borrowings for Tom: 
1) Name of Item: Chalkboard
Name of Borrower: Tom
Borrow Duration: 2022-05-05 to 2022-05-06
Borrow Quantity: 1

2) Name of Item: pencil
Name of Borrower: Tom
Borrow Duration: 2022-05-06 to 2022-05-06
Borrow Quantity: 4

> cancel p/Tom i/1
Future borrowing of Chalkboard | 1 from 2022-05-05 to 2022-05-06 by Tom has been removed

> listfb p/Tom
Here is a list of future borrowings for Tom: 
1) Name of Item: pencil
Name of Borrower: Tom
Borrow Duration: 2022-05-06 to 2022-05-06
```

### Exit: `exit`

Exits the program.

**Format:** 

`exit`

**Arguments:**

None?

**Caveats:**

None?

**Examples of usage:**
```
> exit
bye
```

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Simply transfer `data/inventoryData.json` to the other computer. Make sure the jar file is located appropriately, i.e. the structure should follow:
```
<current dir>
|  InvMgr.jar
|  data
|  |   inventoryData.json
```

## Command Summary

| Action                  | Format                                                   | Examples                                            |
|-------------------------|----------------------------------------------------------|-----------------------------------------------------|
| List                    | `list`                                                   ||
| List current borrowings | `listcb`                                                 ||
| Borrow                  | `borrow i/INDEX s/START_DATE e/END_DATE p/BORROWER_NAME` | `borrow i/1 s/2021-03-21 e/2021-03-25 p/John Smith` |
| Description             | `desc INDEX`                                             | `desc 1`                                            |
