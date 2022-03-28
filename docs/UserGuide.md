# User Guide

## Introduction

EquipmentManager is a Command Line Interface application to help with keeping track of equipment details (e.g. current user, quantity, cost, warranty duration, purchase date) for the AV club. It provides a clean and fast way to manage the inventory as compared to “traditional” methods such as an excel spreadsheet.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `EquipmentManager` from [here](https://github.com/AY2122S2-CS2113-F12-2/tp/releases).

## Features 

- [Adding an equipment: `add`](#adding-an-equipment-add)
- [Checking an equipment: `check`](#checking-an-equipment-check)
- [Listing equipment: `list`](#listing-equipment-list)
- [Updating an equipment: `update`](#updating-an-equipment-update)
- [Deleting an equipment: `delete`](#deleting-an-equipment-delete)
- [Saving application state: `save`](#saving-application-state-save)
- [Getting help: `help`](#getting-help-help)
- [Exiting the application: `bye`](#exiting-the-application-bye)

### Adding an equipment: `add`
Adds new Equipment to the list of Equipment.

Format: `add n/ITEM_NAME sn/SERIAL_NUMBER t/TYPE c/COST pf/PURCHASED_FROM pd/PURCHASED_DATE`

* The `TYPE` must be one of the following types: MICROPHONE, SPEAKER, STAND, CABLE.
* The `COST` cannot contain any symbols.  

Example of usage and output: 

`add n/SpeakerB s/S1404115ASF t/Speaker c/1000 pf/Loud_Technologies pd/2022-02-23`
output: `Equipment successfully added: SpeakerB, serial number S1404115ASF`

### Checking an equipment: `check`
Check the details of the equipment that has the name specified.

Format: `check n/ITEM_NAME`

* Only the `ITEM_NAME` can be used to check for an equipment.

Example of usage and output:

`check n/SpeakerB`
output: 
`Here are the equipment matching to 'SpeakerB':`

`1. serialNumber=S1404115ASF,itemName=SpeakerB,type=SPEAKER,cost=1000.0,purchasedFrom=Loud_Technologies,purchasedDate=2022-02-23`

### Listing equipment: `list`
Print a list of all equipment in the inventory.

Format: `list`

Example of usage and output:

`list`
output:
`TOTAL QUANTITY OF EQUIPMENT: 2`

`1. serialNumber=S89347971ASF,itemName=MixerC,type=MICROPHONE,cost=2000.0,purchasedFrom=Loud_Technologies,purchasedDate=2022-01-21`
`2. serialNumber=S1404115ASF,itemName=SpeakerB,type=SPEAKER,cost=1000.0,purchasedFrom=Loud_Technologies,purchasedDate=2022-02-23`

### Listing equipment by type

### Updating an equipment: `update`

### Deleting an equipment: `delete`

### Saving application state: `save`

### Getting help: `help`

### Exiting the application: `bye`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Copy the data folder from the old computer into the other computer, in the same directory as the JAR file.

## Command Summary

'Cheat Sheet' of commands here

* Add equipment `todo n/TODO_NAME d/DEADLINE`
* Listing equipment: 
* Listing equipment by type: ADD ON HERE
* Updating an equipment: ADD ON HERE
* Deleting an equipment: ADD ON HERE
* Saving application state: ADD ON HERE
* Getting help: ADD ON HERE
* Exiting the application: ADD ON HERE
