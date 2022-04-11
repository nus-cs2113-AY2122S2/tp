# User Guide

## Introduction

Travel Agency Reservation Booking System (TARBS) is a desktop app for managing reservations for travel packages and tourist attractions, optimised for use via a Command Line Interface (CLI). If you can type fast, TARBS can help to improve efficiency of adding and editing reservations, amongst many other features.


### Why do we need TARBS?
Travel agencies often have to manage multiple customers and their respective bookings or plans.

## Quick Start

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `TARBS` from [here](https://github.com/AY2122S2-CS2113-F10-3/tp/releases).

## Features 

### Quick Help: `help`
Print detailed instructions on the available commands that users may input.

Usage: `help`

### Show all packages: `packages`

View a list of all available packages

Usage: `packages`

### Viewing a package: `info`
View a specified Travel Package from the list of Packages based on the package ID.

Format: `info {package_id}`

Example of usage:

`info 2`

### Show packages sorted by starting date, price or vacancies : `all` 
View list of packages sorted by ascending date or price and descending vacancies. 

Format: `all` 

Example of usage:
`all` 

Sort packages by:
1. Date (Earliest to Latest) 
2. Number of vacancies left
3. Price
4. Return to main menu.

`Enter 1-4` 




### Adding a package: `add`
Adds a new Travel Package to the list of Packages.

Format: `add {package_name},{package_id},{startDate},{endDate},{hotel},{price},{country},{vacancies}`

* The `startDate` and `endDate` must be in the format DD/MM/YYYY.
* The `price` can only contain numbers or decimal points.
* `package_id` should be unique. 


Example of usage: 

`add Skiing Trip,1,23/02/2022,24/02/2022,hotelName,90.99,Singapore,20`


### Deleting a package: `delete`
Delete a Travel Package from the list of Packages based on the package ID.

Format: `delete {package_id}`

Example of usage: 

`delete 2`

### Placing a reservation: `reserve`
Add a reservation to a Travel Package.

Format: `reserve {package_id},{contact_name},{contact_number},{number_pax}`

Example of usage: 

`reserve 3,John,91234567,3`

### Removing a reservation: `remove`
Remove a reservation from a Travel Package using packageID and contact number. 

Format: `remove {package_id},{contact_number}`

Example of usage: 

`remove 1,8888888`

### View reservations: `reservations`
View reservations booked in a Travel Package.

Format: `reservations {package_id}`

Example of usage: 

`reservations 2`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: When starting the application, data.txt will be created if there isn't one already. Simply place the data.txt with the executable JAR file on the new computer and you are good to go. 



## Command Summary


| Command | Syntax |
| --- | :---  |
| help | help |
| packages | packages |
| info | info {package_number} |
| all | all | 
| add | add {package_name},{package_id},{startDate},{endDate},{hotel},{price},{country},{max_vacancies} |
| delete | delete {package_id} |
| reserve  | reserve {package_id},{contact_name},{contact_number},{number_pax} |
| remove | remove {package_id},{conact_number} |
| reservations | reservations {package_id} |



