# User Guide

## Introduction

ClubInvMgr is a desktop CLI app for inventory management for CCA clubs, especially for fast typists who can accomplish tasks quickly by typing out commands.

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

### List All Items: `list`
List all items in the inventory.

Format: `list`

Examples of usage:
```
> list
Name | Quantity	|
VGA Cable | 1 
HDMI Cable | 2
```


### List Current Borrowings: `list_current_borrowings`
List all items that are currently being borrowed. You can narrow down the list by entering an optional argument of the borrower's name. Results of borrowings ordered by earliest borrowing start date.

Format:   
`list_current_borrowings`: List all items that are current being borrowed.  
`list_current_borrowings p/BORROWER_NAME`: List all items that are currently being borrowed by borrower
* `BORROWER_NAME` must not contain punctuations.

Examples of usage:
```
> list -cb
Name of Item: Trolley
Name of Borrower: Sally
Borrow Duration: 19-03-2021 to 30-03-2021

Name of Item: JBLFlip5
Name of Borrower: John Smith
Borrow Duration: 21-03-2021 to 23-03-2021

Name of Item: JBLFlip5
Name of Borrower: Sally
Borrow Duration: 29-03-2021 to 01-04-2021
```
If there are no items have been borrowed from the inventory. The `list -cb` command will return:
```
> list -cb
There are no items in the inventory being borrowed.
```

```
> list -cb p/Sally
Name of Item: Trolley
Name of Borrower: Sally
Borrow Duration: 19-03-2021 to 30-03-2021

Name of Item: JBLFlip5
Name of Borrower: Sally
Borrow Duration: 29-03-2021 to 01-04-2021
```
If the person does not exist in the borrowings, the `list -cb p/BORROWER_NAME` will return:
```
> list -cb p/David
There are no items currently borrowed by David.
```


### Borrow an Item: `borrow`
Borrow the item that you want for the duration between the start date and end date.

Format: `borrow i/ITEM_INDEX s/START_DATE e/END_DATE p/BORROWER_NAME`
* `ITEM_INDEX` should be within one of the index for ItemList.
* The `START_DATE` and `END_DATE` must be in DD-MM-YYYY format.
* The `BORROWER_NAME` cannot contain punctuations.

Examples of usage:
```
> borrow i/23 s/21-03-2021 e/23-03-2021 p/John Smith
You have successfully borrowed the following item:
Name of Item: JBLFlip5
Name of Borrower: John Smith
Borrow Duration: 21-03-2021 to 23-03-2021
```

```
> borrow i/12 s/21-03-2021 e/23-03-2021 p/John Smith
Sorry. The item is not avaiable for borrowing during this duration.
```

```
> borrow i/28
Sorry. This item does not exist in the current inventory.
```

### Get Description of Item: `desc`
Retrieve the details of a particular item of your interest from the current inventory by entering the index (1-based indexing).

Format: `desc INDEX`

Examples of usage:
```
> desc 1
Name of Item: JBLFlip5
Description: Waterproof up to 3m, fully charged batteries can last for 5 hours, bluetooth enabled.
```

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

| Action                  | Format                                                 | Examples                                           |
|-------------------------|--------------------------------------------------------|----------------------------------------------------|
| List                    | `list`                                                 ||
| List current borrowings | `list -cb`                                             ||
| Borrow                  | `borrow INDEX s/START_DATE e/END_DATE p/BORROWER_NAME` | `borrow 23 s/21-03-2021 e/23-03-2021 p/John Smith` |
| Description             | `desc INDEX`                                           | `desc 1`                                           |
