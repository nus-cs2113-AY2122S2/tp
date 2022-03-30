# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

## Main Menu

In `Main Menu`, when the select panel is shown:
```
Welcome to Restaurant Information Programme!
(0) Exit Application
(1) Enter Dish Menu
(2) Enter Order Menu
(3) Enter Staff Menu
******************************
Enter choice: 
```
> Enter 1 to use dish management function (Dish menu)
> Enter 2 to use order management function (Order menu)
> Enter 3 to use staff management function (Staff menu)

## Dish Management

### Introduction
`Dish Management` feature consists of several sub-features:
> `List Dish`, `Add Dish`, `Delete Dish`, `Dish info modification`, and `Storage`.

### Usage
When using `Dish Management`, the program pops up a select panel to ask for action:
```
(0) Exit Menu
(1) List Dish
(2) Add Dish
(3) Delete Dish
(4) Change the price of a dish
(5) Change the name of a dish
******************************
Enter choice: 
```
Enter corresponding index to use the feature.

## Enter Dish Menu
In `Main Menu`, when the select panel is shown:
```
Welcome to Restaurant Information Programme!
(0) Exit Application
(1) Enter Dish Menu
(2) Enter Order Menu
(3) Enter Staff Menu
******************************
Enter choice: 
```
> Enter 1 to use order management function (Dish menu)

## List dish (indexed as 1)
When using `List dish`, there is no need for input, the program will print the dish name and the index.
> Add some dish: Chicken Rice($3.5), Mala hotpot Set A($10), Soup (Along with Chicken Rice)($0), Chilli Crab($30)
> Enter 1 to list dishes
```
Listing dishes...
1. Chicken Rice ---- $3.5
2. Mala Hotpot Set A ---- $10.0
3. Soup (Along With Chicken Rice) ---- free
4. Chilli Crab ---- $30.0
```

#### Notice
If there is no dish in the menu. The output will be
```
Listing dishes...
You haven't got a dish in menu!
```

### Add dish (indexed as 2)
When using `Add dish`, the program pops up an input panel.
```
Adding new dish...
The name of dish: 
```
> Enter the name of the new dish
```
Enter choice: 2
Adding new dish...
The name of dish: some dish
The price of dish: 
```
> Enter the price of the new dish

#### Notice
`Name` of the dish cannot be empty, and `price` of the dish cannot be negative (`price` being 0 means this dish is free to serve).

### Order Management

## Introduction
`Order Management` feature is consist of several sub-features:
> `Create an order`, `Delete an order`, `Get total value of current order`, `Get total value of all orders in the list`, `Print receipt`.

## Usage
When using `Order Management`, the program pops up a select panel to ask for action:
```aidl
(0) Exit
(1) Create an order
(2) Delete an order
(3) Get value of an order
(4) Get total value of all orders
(5) Print receipt
******************************
Enter choice: 
```
Enter corresponding index to use the feature.

## Accessing Order Menu
> Enter `2` in `Main Menu` to enter order management function.

## Creating a new order
When using `Creating an order`, the user creates a new order and adds dishes to it. The program asks for user inputs.
> Enter `1` in `Order Menu`

> Example: Add some dishes to: 2 Chicken Rice ($3.5 * 2), 1 Mala hotpot Set A($10)

```aidl
Creating a new order...
Enter dishes you want to add to the order (enter negative number to exit): 
```

> Enter the index of the dish one by one. Enter a negative number to quit.

```aidl
Enter choice: 1
Creating a new order...
Enter dishes you want to add to the order (enter negative number to exit): 0
You have 1 dish(es), some more: 
0
Added successfully!
You have 2 dish(es), some more: 
1
Added successfully!
You have 3 dish(es), some more: 
-1
```
### Notice
If the user input is invalid, the program issues an error.
```aidl
Creating a new order...
Please enter a valid dish index and try again.
```
## Deleting an order
When using `Deleting an order`, the user deletes an existing order by index.

> Enter `2` in `Order Menu` 

> Example: Delete order with index 0.

```aidl
Enter the order you want to delete: 0
Deleted successfully!
```

## Checking total value of an order
When using `Checking total value of an order`, the user searches an order by index and checks the total value of the
 order. The order value is the sum of the prices of all dishes of that order.

> Enter `3` in `Order Menu` 

> Example: Delete order with index 0.

```aidl
Enter choice: 3
Enter the order you want to get price: 0
Total value of this order: 17.000000. 
```

## Checking total value of all orders
When using `Checking total value of all orders`, the user gets the sum of values of all created orders. 

> Enter `4` in `Order Menu` 

```aidl
Enter choice: 4
Total value of all orders: 17.000000. 
```
## Staff Management

### Introduction
`Staff Management` feature consists of several sub-features:
> `Print Staff`, `Find Staff`, `Add Staff`, and `Delete Staff`.

### Usage
When using `Staff Management`, the program pops up a select panel to ask for action:
```
(0) Exit Staff Menu
(1) Print Staff
(2) Find Staff
(3) Add Staff
(4) Delete Staff
******************************
Enter choice: 
```
Enter corresponding index to use the feature.

### Print staff (indexed as 1)
When using `Print staff`, there is no need for input, the program will print the order and index.
> Add some staff: (100, John, Waiter, 2000), (101, Anna, Chef, 2500)
> Enter 1 to print staff
```
Printing staff...
1. 100     | John            | Waiter
2. 101     | Anna            | Chef
```

Details of all staff will be printed.

#### Notice
If there is no dish in the menu. The output will be
```
Printing staff...
There is no staff.
```

### Find staff (indexed as 2)
When using `Find staff`, the program pops up an input panel.
```
Finding staff...
ID of staff: 
```
> Enter ID of staff

Details of staff with the ID being inputted will be printed.

#### Notice
Staff with the `ID` being inputted should exist.

### Add staff (indexed as 3)
When using `Add staff`, the program pops up an input panel.
>
```
Adding new staff...
ID of staff: 
```
> Enter the ID of the new staff
```
Adding new staff...
ID of staff: 
Name of staff:
```
> Enter the name of the new staff
```
Adding new staff...
ID of staff: 
Name of staff:
Position of staff
```
> Enter the position of the new staff
```
Adding new staff...
ID of staff: 
Name of staff:
Salary of staff:
```
> Enter the salary of the new staff

Staff with the ID, name, position and salary being inputted will be created.

#### Notice
`Name` and `Position` of the staff cannot be empty, and `ID` and `salary` of the staff cannot be zero or negative.

### Delete staff (indexed as 4)
When using `Delete staff`, the program pops up an input panel.
```
Deleting staff...
ID of staff: 
```
> Enter ID of staff

Staff with the ID being inputted will be deleted.

#### Notice
Staff with the `ID` being inputted should exist.

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
