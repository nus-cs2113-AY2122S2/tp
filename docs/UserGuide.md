# Airline Reservation and Check-in System (ARCS) USER GUIDE


## Introduction

ARCS aims to provide a dynamic and concise interface for staff to create, edit and cancel bookings, create in-flight meal reservations, a streamlined interface to check in customers, a payment interface to manage payments and the ability to generate a sales report for different periods for management purposes.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `ARCS` from [here](https://github.com/AY2122s2-CS2113-F12-3/tp/releases/tag/v1.0).
3. Copy the file to the folder you want to use as the home folder for your ARCS.
4. Open your command line interface and go to the folder directory.
5. Run the command java -jar CS2113ARCStp.jar
6. Type the command in the command line and press Enter to execute it. <br>
   Some example commands you can try:

    * **`listRoute`** : Lists all existing flight routes

    * **`addMenuItem`**`addMenuItem name/chocolate cake type/MENU_ITEM_TYPE price/MENU_ITEM_PRICE
      ` : Adds a Menu Item named `chocolate cake` to the in-flight menu.

    * **`deleteMenuItem`**`3` : Deletes the 3rd menu item shown in the current list.

    * **`findMenuItemName`** `findMenuItemName coke` : Returns all in-flight menu item that contains the word coke 

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

### Add a new Menu Item:  `addMenuItem`
Add a new in-flight menu item to the system.

Format: `addMenuItem name/MENU_ITEM_NAME type/MENU_ITEM_TYPE price/MENU_ITEM_PRICE`

* The `MENU_ITEM_NAME` If the menu item name has a space it must be separated using an underscore: _
* The `MENU_ITEM_TYPE` must be either APPETIZER/MAIN/SIDE/DESSERT/DRINKS (not case-sensitive) 
* The `MENU_ITEM_PRICE` must be a price > 0.
* The `MENU_ITEM_PRICE` must be an in SGD and in an acceptable value: 2.50, 2.05, 2.5, 2, 0.9, 0.10, 0.01


Example of usage: 


`addMenuItem name/chicken_wing type/main price/2.50`

`addMenuItem name/chocolate_cake type/dessert price/6`

### Delete menu item:  `deleteMenuItem`

Remove a menu item from the system

Format: `deleteMenuItem INDEX`

* The `INDEX` must be a positive integer 1, 2, 3,... starting from 1.
* The `INDEX` refers to the index number shown in the latest displayed routes list.

Example of usage:<br>
`deleteMenuItem 4` <br>
`deleteMenuItem 3`

### Listing all menu items: `listMenuItems`

List all menu items

Format: `listMenuItems`

Example of usage: <br>
`listMenuItems`

###  Find menu items by name: `findMenuItemName`

List out all available menu item which contains the name given.

Format: `findMenuItemName NAME`

Example of usage: <br>
`findMenuItemName chocolate cake` <br>
`findMenuItemName coke` <br>

### Find menu items by type: `findMenuItemType`

List out all available menu items by type specified

Format: `findMenuItemType MENU_ITEM_TYPE`

* The `MENU_ITEM_TYPE` either APPETIZER/MAIN/SIDE/DESSERT/DRINKS (not case-sensitive)

Example of usage: <br>
`findMenuItemType drinks` <br>
`findMenuItemType dessert` <br>

### Exit the system: `bye`

Exit the system.

Format: `bye`

Example of usage: <br>
`bye`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Copy the text file along with the JAR file to a new computer.

## Command Summary

Action | Format| Examples
--------|----------------------------------------------|-------------------------------------------------------
**Add Menu Item**| `addMenuItem name/MENU_ITEM_NAME type/MENU_ITEM_TYPE price/MENU_ITEM_PRICE`| `addMenuItem name/coke type/drinks price/0.9`
**Delete menu item**| `deleteMenuItem INDEX` | `deleteMenuItem 4`
**Listing all menu items**| `listMenuItems`| `listMenuItems`
**Find menu items by nam**| `findMenuItemName NAME` | `findMenuItemName coke`
**Find menu items by type**| `findMenuItemType MENU_ITEM_TYPE` | `findMenuItemType drinks`
**Exit the system**| bye | bye 
