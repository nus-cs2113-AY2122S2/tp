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


### List Current Borrowings: `listcb`
List all items that are currently being borrowed. You can narrow down the list by entering an optional argument of the borrower's name. Results of borrowings ordered by earliest borrowing start date.

Format:   
`listcb`: List all items that are current being borrowed.  
`listcb p/BORROWER_NAME`: List all items that are currently being borrowed by borrower
* `BORROWER_NAME` must not contain punctuations.

Examples of usage:
```
> listcb
Name of Item: Trolley
Name of Borrower: Sally
Borrow Duration: 2021-03-19 to 2021-03-30

Name of Item: JBLFlip5
Name of Borrower: John Smith
Borrow Duration: 2021-03-21 to 2021-03-23

Name of Item: JBLFlip5
Name of Borrower: Sally
Borrow Duration: 2021-03-29 to 2021-04-01
```
If there are no items have been borrowed from the inventory, the `list -cb` command will return:
```
> listcb
There are no items in the inventory being borrowed.
```

```
> listcb p/Sally
Name of Item: Trolley
Name of Borrower: Sally
Borrow Duration: 2021-03-23 to 2021-03-30

Name of Item: JBLFlip5
Name of Borrower: Sally
Borrow Duration: 2021-04-24 to 2021-04-30
```
If the person does not exist in the borrowings, the `list -cb p/BORROWER_NAME` will return:
```
> listcb p/David
There are no items currently borrowed by David.
```


### List Future Borrowings: `list -fb`
List all items that will be borrowed in the future. You can narrow down the list by entering an optional argument of the borrower's name. Results of borrowings ordered by earliest borrowing start date.

Format:   
`list -fb`: List all future borrowings.  
`list -fb p/BORROWER_NAME`: List all future borrowings for Sally
* `BORROWER_NAME` must not contain punctuations.

Examples of usage (Assuming today's date is **18-03-2021**):
```
> list -fb
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
If there are no future borrowings, the `list -fb` command will return:
```
> list -fb
There are no future borrowings.
```

```
> list -fb p/Sally
Name of Item: Trolley
Name of Borrower: Sally
Borrow Duration: 19-03-2021 to 30-03-2021

Name of Item: JBLFlip5
Name of Borrower: Sally
Borrow Duration: 29-03-2021 to 01-04-2021
```
If the person does not exist in the borrowings, the `list -fb p/BORROWER_NAME` will return:
```
> list -fb p/David
There are no future borrowings for David.
```


### List Overdue Borrowings: `list -ob`
List all items should have been returned but have yet to be. You can narrow down the list by entering an optional argument of the borrower's name. Results of borrowings ordered by earliest borrowing start date.

Format:   
`list -ob`: List all overdue borrowings.  
`list -fb p/BORROWER_NAME`: List all overdue borrowings by Sally
* `BORROWER_NAME` must not contain punctuations.

Examples of usage (Assuming today's date is **31-03-2021**):
```
> list -ob
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
If there are no overdue borrowings, the `list -ob` command will return:
```
> list -ob
There are no overdue borrowings.
```

```
> list -ob p/Sally
Name of Item: Trolley
Name of Borrower: Sally
Borrow Duration: 19-03-2021 to 30-03-2021

Name of Item: JBLFlip5
Name of Borrower: Sally
Borrow Duration: 29-03-2021 to 01-04-2021
```
If the person does not exist in the borrowings, the `list -ob p/BORROWER_NAME` will return:
```
> list -ob p/David
There are no overdue borrowings for David.
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
Borrow Duration: 2021-03-21 to 2021-03-23
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
