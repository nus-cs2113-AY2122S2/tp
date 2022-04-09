# User Guide

## Introduction

Restaurant-Information-Programme (R.I.P.) is a command line programme for restaurant owners to keep track of menus, staff information, and order management.
R.I.P. features full and concise input validation through simple commands, thereby making R.I.P. a simple yet robust application for restaurant management.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of the application from [here](https://github.com/AY2122S2-CS2113-T11-4/tp/releases).
3. Run the file using the correct command: `java -jar tp.jar`.
   1. It is recommended to create a new folder, and put the download file into this folder before running it.

## Features 

R.I.P. allows users to manage dishes, staff information, and orders in a restaurant.
Managers of restaurants may find this application to be very useful in keeping track of 
administrative information about their restaurant.

The application is simple to use and does not require verbose inputs. This ensures easy training and
uptake of the application.

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

## Order Management

### Introduction
`Order Management` feature is consist of several sub-features:
> `Create an order`, `Delete an order`, `Get total price of an order`, `Get total price of all orders in the list`, `Print receipt`.

### Usage
When using `Order Management`, the program pops up a select panel to ask for action:
```
(0) Exit Order Menu
(1) Create an order
(2) Delete an order
(3) Get total price of an order
(4) Get total price of all orders
(5) Print receipt
(6) Display order list
(7) Display dish menu
******************************
Enter choice: 
```
Enter corresponding index to use the feature.

## Accessing Order Menu
> Enter `2` in `Main Menu` to enter order management function.

## Create a new order (indexed as 1)
When using `Create an order`, the user creates a new order and adds dishes to it. The program asks for user inputs.
> Enter `1` in `Order Menu`

> Example: Add some dishes to: 2 Chicken Rice ($3.5 * 2), 1 Mala hotpot Set A($10)

```
Creating a new order...
Enter dishes you want to add to the order (enter negative number to exit): 
```

> Enter the index of the dish one by one. Enter a negative number to quit.

```
Enter choice: 1
Creating a new order...
Enter dishes you want to add to the order (enter negative number to exit): 0
You have 1 dish(es), some more: 
1
Added successfully!
You have 2 dish(es), some more: 
2
Added successfully!
You have 3 dish(es), some more: 
-1
```
### Notice
If the user input is invalid, the program issues an error.
```
Creating a new order...
Please enter a valid dish index and try again.
```
## Delete an order (indexed as 2)
When using `Delete an order`, the user deletes an existing order by index.
Order index starts from 1.

> Enter `2` in `Order Menu` 

> Example: Delete order with index 1.

```
Enter choice: 2
Deleting an order...
Enter the order you want to delete: 1
Deleted successfully!
```
### Notice
If the user input is invalid, the program issues an error.
```
Deleting an order...
Please enter a valid order index.
```

## Get total price of an order (indexed as 3)
When using `Get total price of an order`, the user searches an order by index and checks the total price of the
 order. Order index starts from 1.

> Enter `3` in `Order Menu` 

> Example: Get total price of an order with index 1.

```
Enter choice: 3
Getting total price of an order...
Enter the order you want to get price: 1
Total value of this order: 13.500000. 
```
### Notice
If the user input is invalid, the program issues an error.
```
Getting total price of an order...
Please enter a valid order index.
```

## Get total price of all orders (indexed as 4)
When using `Get total price of all orders`, the user gets the sum of prices of all created orders. 

> Enter `4` in `Order Menu` 

```
Enter choice: 4
Getting total price of all orders in the list...
Total value of all orders: 13.500000. 
```

## Print Receipt (indexed as 5)
When using `Print Receipt`, the user searches an order by index and print all dishes in the
order. Order index starts from 1.

> Enter `5` in `Order Menu`

> Example: Print receipt of an order with index 1.

```
Enter choice: 5
Printing receipt...
Enter the order you want to display: 1
This is your order. 
Chicken Rice ---- $3.5
Mala Hotpot Set A ---- $10.0
Total Price: 13.5
```
### Notice
If the user input is invalid, the program issues an error.
```
Printing receipt...
Invalid order index.
```

## Display order list (indexed as 6)
When using `Display order list`, the user checks the order list.

> Enter `6` in `Order Menu` 

```
Enter choice: 6
Printing all orders...
Order 1:
Chicken Rice ---- $3.5
Mala Hotpot Set A ---- $10.0
Total Price: 13.5
Order 2:
Chicken Rice ---- $3.5
Mala Hotpot Set A ---- $10.0
Total Price: 13.5
```

## Display dish menu (indexed as 7)
When using `Display dish menu`, the user checks the dish menu for reference. The function is the same as `List dish`.
> Enter `7` in `Order Menu` 



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
(5) Edit Staff
******************************
Enter choice: 
```
Enter corresponding index to use the feature.

### Print staff (indexed as 1)
When using `Print staff`, there is no need for input, the program will print the order and index.
> Add some staff: (100, John, Waiter, 2000), (101, Anna, Chef, 2500)
```
Printing staff...
1. 100     | John            | Waiter
2. 101     | Anna            | Chef
```
> Enter 1 to print staff

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
```
Adding new staff...
ID of staff: 
Name of staff:
Position of staff:
Salary of staff:
```
> Enter the ID of the new staff

> Enter the name of the new staff

> Enter the position of the new staff

> Enter the salary of the new staff

Staff with the ID, name, position and salary being inputted will be created.

#### Notice
`Name` and `Position` of the staff cannot be empty, and `ID` and `salary` of the staff cannot be zero or negative (range: -2,147,483,648 to 2,147,483,647). `ID` of the staff should also not already be in use by an existing staff.

### Delete staff (indexed as 4)
When using `Delete staff`, the program pops up an input panel.
```
Deleting staff...
ID of staff to delete: 
```
> Enter ID of staff

Staff with the ID being inputted will be deleted.

#### Notice
Staff with the `ID` being inputted should exist.

### Edit staff (indexed as 5)
When using `Edit staff`, the program pops up an input panel.
```
Editing staff...
ID of staff to edit: 
0. Exit
1. ID of staff
2. Name of staff
3. Position of staff
4. Salary of staff
Select field to edit: 
```
> Enter ID of staff

> Enter field of staff to edit

Enter corresponding index to edit the attribute.

If option 1 (ID) is chosen:
```
New ID of staff:
```
> Enter new ID of staff


If option 2 (name) is chosen:
```
New name of staff:
```
> Enter new name of staff


If option 3 (position) is chosen:
```
New position of staff:
```
> Enter new position of staff


If option 4 (salary) is chosen:
```
New salary of staff:
```
> Enter new salary of staff

#### Notice
Staff with the `ID` being inputted should exist. `Name` and `Position` of the staff cannot be empty, and `ID` and `salary` of the staff cannot be zero or negative (range: -2,147,483,648 to 2,147,483,647). New `ID` of the staff should also not already be in use by an existing staff.

## FAQ

**Q**: How do I transfer my data to another computer?
- **A**: You may simply copy the `data` folder to the other computer.

**Q**: Hmm, the data does not seem to be saved correctly!
- **A**: Note that this application is *transaction-based*. This means that any changes to the data
will not be saved unless the application is exited gracefully. Any other means of abruptly exiting the programme (such as Ctrl+C)
will not save the data (because they are not taken to be typical exit commands, but rather interrupts).

## Command Summary
| Action    | Command (with respect to Main Menu)                                                                                                                                                                                                                                                                         |
|-----------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| List Dish | `1` -> `1` |
| Add Dish  | `1` -> `2` -> `NAME` -> `PRICE` | 
| Delete Dish | `1` -> `3` -> `INDEX` |
| Change Dish Price | `1` -> `4` -> `INDEX` -> `PRICE` |
| Change Dish Name | `1` -> `5` -> `INDEX` -> `NAME` |
| Create Order | `2` -> `1` -> `DISH INDEX` |
| Delete Order | `2` -> `2` -> `INDEX` |
| Get Order Total Price | `2` -> `3` -> `INDEX` |
| Get All Order Total Price | `2` -> `4` |
| Print Receipt | `2` -> `5` -> `INDEX` |
| Display Order List | `2` -> `6` |
| Display Dish Menu | `2` -> `7` |
| Print Staff | `3` -> `1` |
| Find Staff | `3` -> `2` -> `ID` |
| Add Staff | `3` -> `3` -> `ID` -> `NAME` -> `POSITION` -> `SALARY` |
| Delete Staff | `3` -> `4` -> `ID` |
| Edit Staff ID | `3` -> `5` -> `1` -> `ID` |
| Edit Staff Name | `3` -> `5` -> `1` -> `NAME` |
| Edit Staff Position | `3` -> `5` -> `1` -> `POSITION` |
| Edit Staff Salary | `3` -> `5` -> `1` -> `SALARY` |
