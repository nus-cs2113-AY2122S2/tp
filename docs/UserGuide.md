# User Guide

## Introduction

{Travel Agency Reservation Booking System (TARBS) is a desktop app for managing reservations for travel packages and tourist attractions, optimised for use via a Command Line Interface (CLI). If you can type fast, TARBS can help to improve efficiency of adding and editing reservations, amongst many other features.}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `TARBS` from [here](https://github.com/AY2122S2-CS2113-F10-3/tp/releases).

## Features 

{Give detailed description of each feature}

### Adding a package: `add`
Adds a new Travel Package to the list of Packages.

Format: `add {package_name},{package_id},{startDate},{endDate},{hotel},{price},{country},{vacancies}`

* The `startDate` and `endDate` must be in the format DD/MM/YYYY.
* The `price` can only contain numbers or decimal points.  

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
Remove a reservation from a Travel Package.

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

**A**: {your answer here}

## Command Summary

| Command | Syntax |
| --- | :---  |
| packages | packages |
| info | info {num} |
| add | add {package_name} {country} {duration} {price} {vacancies} |
| delete | delete {num} |
| reserve  | reserve {package_number} {contact_name} {contact_number} {number_pax} |
| remove | remove {reservation_id} |
| reservations | reservations {package_number} |



