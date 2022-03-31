# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

### Dish Management

## Introduction
`Dish Management` feature is consist of several sub-features:
> `List Dish`, `Add Dish`, `Delete Dish`, `Dish info modification`, and `Storage`.

## Usage
When using `Dish Management`, the program pops up a select panel to ask for action:
```aidl
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
When using `List dish`, there is no need for input, the program will print the order and index.
> Add some dish: Chicken Rice($3.5), Mala hotpot Set A($10), Soup (Along with Chicken Rice)($0), Chilli Crab($30)
> Enter 1 to list dishes
```aidl
Listing dishes...
1. Chicken Rice ---- $3.5
2. Mala Hotpot Set A ---- $10.0
3. Soup (Along With Chicken Rice) ---- free
4. Chilli Crab ---- $30.0
```

### Notice
If there is no dish in the menu. The output will be
```aidl
Listing dishes...
You haven't got a dish in menu!
```

## Add dish (indexed as 2)
When using `Add dish`, the program pops up an input panel.
```aidl
Adding new dish...
The name of dish: 
```
> Enter the name of the new dish
```aidl
Enter choice: 2
Adding new dish...
The name of dish: some dish
The price of dish: 
```
> Enter the price of the new dish

### Order Management

## Introduction
`Order Management` feature is consist of several sub-features:
> `Display Menu`, `Create an order`, `Delete an order`, `Get total value of current order`, `Get total value of all orders in the list`, and `Print receipt`.

## Usage
When using `Order Management`, the program pops up a select panel to ask for action:
```aidl
(0) Exit
(1) Display Menu
(2) Create an order
(3) Delete an order
(4) Get total value of current order
(5) Get total value of all orders in the list
(6) Print receipt
******************************
Enter choice: 
```
Enter corresponding index to use the feature.

## Enter Order Menu
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
> Enter 2 to use order management function (Order menu)

## Display Menu (indexed as 1)
When using `Display Menu`, there is no need for input, the program will print the order and index.
> Add some dish: Chicken Rice($3.5), Mala hotpot Set A($10), Soup (Along with Chicken Rice)($0), Chilli Crab($30)
> Enter 1 to Display menu
```aidl
Displaying menu...
1. Chicken Rice ---- $3.5
2. Mala Hotpot Set A ---- $10.0
3. Soup (Along With Chicken Rice) ---- free
4. Chilli Crab ---- $30.0
```

### Notice
If there is no dish in the menu. The output will be
```aidl
Displaying menu...
You haven't got a dish in menu!
```

## Create an order (indexed as 2)
When using `Create an order`, the program pops up an input panel.
```aidl
Creating an order...
Enter dishes you want to order (enter negative number to exit):  
```
> Enter the index of the dish(es) you want to order
```aidl
Enter choice: 2
Adding new dish...
The name of dish: some dish
The price of dish: 
```
> Enter the price of the new dish





### Notice
`Name` of the dish cannot be empty, and `price` of the dish cannot be negative (`price` being 0 means this dish is free to serve).



## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
