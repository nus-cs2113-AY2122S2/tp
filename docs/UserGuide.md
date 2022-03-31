# Airline Reservation and Check-in System (ARCS) USER GUIDE

- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Features](#features)
    * [Create a new flight route: `addRoute`](#create-a-new-flight-route---addroute-)
    * [Find availability on existing flight route: `findRoute`](#find-availability-on-existing-flight-route---findroute-)
    * [List all flight routes: `listRoute`](#list-all-flight-routes---listroute-)
    * [Delete a flight route: `deleteRoute`](#delete-a-flight-route---deleteroute-)
    * [Add a customer: `addCustomer`](#add-a-customer---addcustomer-)
    * [Delete a customer: `deleteCustomer`](#delete-a-customer---deletecustomer-)
    * [List all customers: `listCustomer`](#list-all-customers---listcustomer-)
    * [Find a customer by IC: `findCustomer`](#find-a-customer-by-ic---findcustomer-)
    * [Create new flight booking: `book`](#create-new-flight-booking---book-)
    * [Remove flight booking: `deleteBooking`](#remove-flight-booking---deletebooking-)
    * [View all existing flight booking: `listBooking`](#view-all-existing-flight-booking---listbooking-)
    * [Add a new Menu Item:  `addMenuItem`](#add-a-new-menu-item----addmenuitem-)
    * [Delete menu item:  `deleteMenuItem`](#delete-menu-item----deletemenuitem-)
    * [Listing all menu items: `listMenuItems`](#listing-all-menu-items---listmenuitems-)
    * [Find menu items by name: `findMenuItemName`](#find-menu-items-by-name---findmenuitemname-)
    * [Find menu items by type: `findMenuItemType`](#find-menu-items-by-type---findmenuitemtype-)
    * [Exit the system: `bye`](#exit-the-system---bye-)
- [FAQ](#faq)
- [Command Summary](#command-summary)


## Introduction

ARCS aims to provide a dynamic and concise interface for staff to create, view and cancel flight routes and bookings, create in-flight meal reservations, a streamlined interface to check in customers, a payment interface to manage payments and the ability to generate a sales report for different periods for management purposes.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `ARCS` from [here](https://github.com/AY2122s2-CS2113-F12-3/tp/releases/tag/v1.0).
3. Copy the file to the folder you want to use as the home folder for your ARCS.
4. Open your command line interface and go to the folder directory.
5. Run the command java -jar CS2113ARCStp.jar.
6. Type the command in the command line and press Enter to execute it. <br>
   Some example commands you can try:

    * **`listRoute`** : Lists all existing flight routes.

    * **`addMenuItem`**`addMenuItem name/chocolate cake type/MENU_ITEM_TYPE price/MENU_ITEM_PRICE
      ` : Adds a Menu Item named `chocolate cake` to the in-flight menu.

    * **`deleteMenuItem`**`3` : Deletes the 3rd menu item shown in the current list.

    * **`findMenuItemName`** `findMenuItemName coke` : Returns all in-flight menu item that contains the word coke. 

    * **`bye`** : Exits the app.

7. Refer to the [Features](#features) below for details of each command.


## Features
**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `addMenuItem name/MENU_ITEM_NAME`, `MENU_ITEM_NAME` is a parameter which can be used as `add name/chocolate cake`.

* Items in square brackets are optional.<br>
  e.g `findRoute fd/FLIGHT_DATE d/DESTINATION s/SOURCE [ ft/FLIGHT_TIME ]` can be used as `findRoute fd/07112029 d/India s/singapore` or as `findRoute fd/02172022 d/singapore s/china ft/15:30`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `addMenuItem name/MENU_ITEM_NAME type/MENU_ITEM_TYPE`, `type/MENU_ITEM_TYPE addMenuItem name/MENU_ITEM_NAME` is also acceptable.

### Create a new flight route: `addRoute`
Create a new flight route.

Format: `addRoute  fid/FLIGHT_ID fd/FLIGHT_DATE ft/FLIGHT_TIME d/DESTINATION s/SOURCE c/CAPACITY`

* The `FLIGHT_ID` needs to be unique. If the 'FLIGHT_ID' is already registered, the system should generate an error message and require new input.
* The flight route can be searched out by `FLIGHT_DATE` `DESTINATION` `SOURCE` `[FLIGHT_TIME]`.

Example of usage:
* `addRoute fid/Bu3037 fd/02172022 ft/11:10 d/singapore s/china c/100`
* `addRoute fid/MU8401 fd/01232012 ft/13:10 d/singapore s/america c/300`

### Find availability on existing flight route: `findRoute`
List out all flight routes that are currently available.

Format: `findRoute fd/FLIGHT_DATE d/DESTINATION s/SOURCE [ft/FLIGHT_TIME]`

* List out the flight route with same `FLIGHT_DATE` `DESTINATION` `SOURCE` as required.
* `FLIGHT_TIME` is optional.
* Only full words will be matched.

Example of usage:
* `findRoute fd/02172022 d/singapore s/china ft/15:30` Flights at 02172022 15:30 from China to Singapore will be displayed.
* `findRoute fd/07112029 d/India s/singapore`Flights at 07112029 from Singapore to India will be displayed.


### List all flight routes: `listRoute`
Shows a list of all existing flight routes along with flight details.

Format: `listRoute`


### Delete a flight route: `deleteRoute`
Remove a route from the system.

Format: `deleteRoute INDEX`

* Deletes the route at the specified `INDEX`.
* The index refers to the index number shown in the latest displayed routes list.
* The index must be a positive integer 1, 2, 3,... starting from 1.

Example of usage:
* `deleteRoute 3` The third route in the route list will be deleted.


### Add a customer: `addCustomer`
Add a new customer into the system.

Format: `addCustomer ic/IC n/NAME p/PHONE_NUMBER e/EMAIL`
* A customer needs to be added in the system before any flight can be booked for this customer.
* Each customer is uniquely identified by the IC. If the customer IC number is already existing in the system, an error will be raised and the new customer cannot be added.
* The IC must have 9 characters with the first and last characters being letters.
* The email should be in proper email format, i.e. the email must contain "@".

Example of usage:
* `addCustomer ic/W9248013B n/Eddie p/38201843 e/eddie1238@123.com`
* `addCustomer ic/A09465022C n/Alice White p/97640183 e/alicewhite010@gmail.com`

### Delete a customer: `deleteCustomer`
Remove a customer from the system.

Format: `deleteCustomer INDEX`

* Deletes the route at the specified `INDEX`. 
* The index refers to the index number shown in the latest displayed customers list.
* The index must be a positive integer 1, 2, 3,... starting from 1.

Example of usage:
* `deleteCustomer 2`


### List all customers: `listCustomer`
Show the list of all existing customers along with customer information.

Format: `listCustomer`


### Find a customer by IC: `findCustomer`
Find the  customer with the specified ic.

Format: `findCustomer IC`

* List the customer information with the `IC`.
* If customer is found “No customer found.” will be displayed.

Example of usage:
* `findCustomer A9470034C`

### Create new flight booking: `book`
Add a flight booking to book a flight for a passenger.

Format: `book ic/IC fid/FLIGHT_ID`

* IC refers to the identity card number of the passenger, which uniquely identifies one passenger. 
* One customer cannot book more than one flight taking off at the same time.
* The flight that has no empty seats cannot be booked.

Example of usage:

* `book ic/W9848113C fid/SQ123`
* `book fid/KR457 ic/A9184730D`

### Remove flight booking: `deleteBooking`
Delete the specified flight booking from the flight booking lists.

Format: `deleteBooking INDEX`

* Deletes the booking at the specified `INDEX`.
* The index refers to the index number shown in the displayed bookings list.
* The index must be a positive integer 1, 2, 3,... starting from 1.

Example of usage:
* `listBooking` followed by `deleteBooking 2` deletes the 2nd flight booking in the flight booking list.

### View all existing flight booking: `listBooking`
Show the list of all flight bookings in the system.

Format: `listBooking`

Example of usage:

* `listBooking`


### Add a new Menu Item:  `addMenuItem`
Add a new in-flight menu item to the system.

Format: `addMenuItem name/MENU_ITEM_NAME type/MENU_ITEM_TYPE price/MENU_ITEM_PRICE`

* The `MENU_ITEM_NAME` If the menu item name has a space it must be separated using an underscore: _
* The `MENU_ITEM_TYPE` must be either APPETIZER/MAIN/SIDE/DESSERT/DRINKS (not case-sensitive) 
* The `MENU_ITEM_PRICE` must be a price > 0.
* The `MENU_ITEM_PRICE` must be an in SGD and in an acceptable value: 2.50, 2.05, 2.5, 2, 0.9, 0.10, 0.01


Example of usage: 


* `addMenuItem name/chicken_wing type/main price/2.50`

* `addMenuItem name/chocolate_cake type/dessert price/6`

### Delete menu item:  `deleteMenuItem`

Remove a menu item from the system

Format: `deleteMenuItem INDEX`

* The `INDEX` must be a positive integer 1, 2, 3,... starting from 1.
* The `INDEX` refers to the index number shown in the latest displayed routes list.

Example of usage:<br>
* `deleteMenuItem 4` <br>
* `deleteMenuItem 3`

### Listing all menu items: `listMenuItems`

List all menu items

Format: `listMenuItems`

Example of usage: <br>
* `listMenuItems`

###  Find menu items by name: `findMenuItemName`

List out all available menu item which contains the name given.

Format: `findMenuItemName NAME`

Example of usage: <br>
* `findMenuItemName chocolate cake` <br>
* `findMenuItemName coke` <br>

### Find menu items by type: `findMenuItemType`

List out all available menu items by type specified

Format: `findMenuItemType MENU_ITEM_TYPE`

* The `MENU_ITEM_TYPE` either APPETIZER/MAIN/SIDE/DESSERT/DRINKS (not case-sensitive)

Example of usage: <br>
* `findMenuItemType drinks` <br>
* `findMenuItemType dessert` <br>

### Exit the system: `bye`

Exit the system.

Format: `bye`

Example of usage: <br>
* `bye`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Copy the text file along with the JAR file to a new computer.

## Command Summary

Action | Format| Examples
--------|----------------------------------------------|-------------------------------------------------------
**Create flight route**| `addRoute  fid/FLIGHT_ID fd/FLIGHT_DATE ft/FLIGHT_TIME d/DESTINATION s/SOURCE c/CAPACITY` | `addRoute fid/Bu3037 fd/02172022 ft/11:10 d/singapore s/china c/100`
**Find flight route**| `findRoute fd/FLIGHT_DATE d/DESTINATION s/SOURCE [ft/FLIGHT_TIME]` | `findRoute fd/02172022 d/singapore s/china ft/15:30`
**List all flight routes**| `listRoute` | `listRoute`
**Delete flight route**| `deleteRoute INDEX` | `deleteRoute 3`
**Add customer**| `addCustomer` | `addCustomer ic/IC n/NAME p/PHONE_NUMBER e/EMAIL` | `addCustomer ic/W9248013B n/Eddie p/38201843 e/eddie1238@123.com`
**Delete a customer**| `deleteCustomer INDEX` | `deleteCustomer 2`
**List all customers**| `listCustomer` | `listCustomer`
**Find customer by IC**| `findCustomer IC` | `findCustomer A9470034C`
**Create flight booking**| `book ic/IC fid/FLIGHT_ID` | `book ic/W9848113C fid/SQ123`
**Remove flight booking**| `deleteBooking INDEX` | `deleteBooking 2`
**List all flight bookings**| `listBooking` | `listBooking`
**Add Menu Item**| `addMenuItem name/MENU_ITEM_NAME type/MENU_ITEM_TYPE price/MENU_ITEM_PRICE`| `addMenuItem name/coke type/drinks price/0.9`
**Delete menu item**| `deleteMenuItem INDEX` | `deleteMenuItem 4`
**List all menu items**| `listMenuItems`| `listMenuItems`
**Find menu items by name**| `findMenuItemName NAME` | `findMenuItemName coke`
**Find menu items by type**| `findMenuItemType MENU_ITEM_TYPE` | `findMenuItemType drinks`
**Exit the system**| `bye` | `bye` 
