# User Guide

## Overview

EquipmentManager is a Command Line Interface application to help with keeping track of equipment details (e.g. current user, quantity, cost, warranty duration, purchase date) for an AV club. It provides a clean and fast way to manage the inventory as compared to “traditional” methods such as an Excel spreadsheet.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `EquipmentManager` from [here](https://github.com/AY2122S2-CS2113-F12-2/tp/releases).
3. Copy the file to the folder you want to use as the _home folder_ for your `EquipmentManager` application.
4. Open the command line and go to the folder mentioned above.
5. Run java -jar tp.jar
6. Type `help` to see the list of available commands.

## Features 

- [Introduction](#introduction)
- [Adding an equipment: `add`](#adding-an-equipment-add)
- [Checking an equipment: `check`](#checking-an-equipment-check)
- [Listing equipment: `list`](#listing-equipment-list)
- [Updating an equipment: `update`](#updating-an-equipment-update)
- [Deleting an equipment: `delete`](#deleting-an-equipment-delete)
- [Saving application state: `save`](#saving-application-state-save)
- [Getting help: `help`](#getting-help-help)
- [Exiting the application: `bye`](#exiting-the-application-bye)


### Introduction

* Words in `UPPER_CASE` are the parameters to be supplied by the user. Spaces are acceptable. However, the parameter must be enclosed in backtick.<br>
  e.g. in ``check n/`ITEM_NAME` ``, `ITEM_NAME` is a parameter which can be used as ``check n/`SM-57` ``.

* Items in square brackets are optional. <br>
  e.g. ``s/`SERIAL_NUMBER` [n/`ITEM_NAME`] [t/`TYPE`] ...`` can be used as ``s/`SM57-1` n/`SM57` t/`MICROPHONE`...`` or as ``s/`SM57-1` t/`MICROPHONE`...``.

* After the command word (e.g. `add`, `update`), parameters can be in any order.<br>
  e.g. if the command specifies ``... s/`SERIAL_NUMBER` n/`ITEM_NAME`...``, ``n/`ITEM_NAME` s/`SERIAL_NUMBER` `` is also acceptable.

* Only one attribute/value can be saved per parameter. Where multiple inputs are supplied, the last parameter will be taken instead of the first one. <br>
  e.g. ``... n/`ITEM_NAME_1` n/`ITEM_NAME_2` `` will be interpreted as ``... n/`ITEM_NAME_2` ``, omitting ``n/`ITEM_NAME_1` `` entirely.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `bye` and `save`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* The maximum cost supported is up to $10 million, any specified cost equal or greater than that will be displayed incorrectly.

### Adding an equipment: `add`
Adds new Equipment to the list of Equipment.

Format: ``add n/`ITEM_NAME` s/`SERIAL_NUMBER` t/`TYPE c/COST` pf/`PURCHASED_FROM` pd/`PURCHASED_DATE` ``

* The `TYPE` must be one of the following types: MICROPHONE, SPEAKER, STAND, CABLE.
* The `COST` cannot contain any symbols.  

Example of usage and output: 

``add n/`SpeakerB` s/`S1404115ASF` t/`Speaker` c/`1000` pf/`Loud_Technologies` pd/`2022-02-23` ``

Output:

```
Equipment successfully added: SpeakerB, serial number S1404115ASF
```

### Checking an equipment: `check`
Check the details of the equipments that matches the specified parameter.

Format: ``check parameter/`PARAMETER_VALUE` ``

e.g. ``check n/`ITEM_NAME` `` or ``check s/`SERIAL_NUMBER` `` or ``check t/`TYPE` ``

* Only one parameter can be used to check for a piece of equipment.

Example of usage and output:

``check n/`SpeakerB` ``

Output: 

```
Here are the equipment matching to 'SpeakerB':
1. serialNumber=S1404115ASF,itemName=SpeakerB,type=SPEAKER,cost=1000.0,purchasedFrom=Loud_Technologies,purchasedDate=2022-02-23
```

### Listing equipment: `list`
Print a list of all equipment in the inventory. If a parameter is supplied, only the equipment matching to the parameter will be printed.

Format: ``list [t/`TYPE`]``

* `TYPE` can only take on values of `SPEAKER`, `MICROPHONE`, `STAND`, `CABLE`.

Example of usage and output:

`list`

Output:

```
TOTAL QUANTITY OF EQUIPMENT: 2
1. serialNumber=S89347971ASF,itemName=MicrophoneC,type=MICROPHONE,cost=2000.0,purchasedFrom=Loud_Technologies,purchasedDate=2022-01-21
2. serialNumber=S1404115ASF,itemName=SpeakerB,type=SPEAKER,cost=1000.0,purchasedFrom=Loud_Technologies,purchasedDate=2022-02-23
```

Example of usage and output:

``list t/`MICROPHONE` ``

Output:

```
TOTAL QUANTITY OF EQUIPMENT: 1
1. serialNumber=S89347971ASF,itemName=MicrophoneC,type=MICROPHONE,cost=2000.0,purchasedFrom=Loud_Technologies,purchasedDate=2022-01-21
```

### Updating an equipment: `update`

Equipment can be updated with new information. Every parameter except serial number can be updated. 

Format: ``update s/`SERIAL_NUMBER` [n/`ITEM_NAME`] [t/`TYPE`] [c/`COST`] [pf/`PURCHASED_FROM`] [pd/`PURCHASED_DATE`]``

* The `TYPE` must be one of the following types: MICROPHONE, SPEAKER, STAND, CABLE.
* The `COST` cannot contain any symbols.

Example of usage and output:

``update s/`S14115ASF` c/`1200` pf/`AVLFX` ``

Output: 

```
Equipment successfully updated for serial number S14115ASF,
Updated details are:
New cost: 1200.0
New purchased from: AVLFX
```

### Deleting an equipment: `delete`

Removes an Equipment entry from the list of Equipment entirely. This is irreversible after the file is saved.

Format: ``delete s/`SERIAL_NUMBER` ``

Example of usage and output:

``delete s/`S14115ASF` ``

Output:

```
Equipment successfully deleted: SpeakerB, serial number S14115ASF
```

### Saving application state: `save`

Saves the current state of the Equipment list to the equipments.json file.

Format: `save`

Example of usage and output:

`save`

Output:

```
Successfully saved.
```

### Getting help: `help`

When `help` is called, the application will print a list of all available commands along with their usages and examples.

Format: `help`

Example of usage and output:

`help`

Output:

```
add: Adds a Equipment to the equipmentInventory.
Parameters: n/`ITEM NAME` s/`SERIAL NUMBER` t/`TYPE` c/`COST` pf/`PURCHASED FROM` pd/`PURCHASED DATE`
Example: add n/`SpeakerB` s/`S1404115ASF` t/`Speaker` c/`1000` pf/`Loud_Technologies` pd/`2022-02-23`

delete: Deletes the equipment with the specified serial number.
Parameters: s/`SERIAL_NUMBER`
Example: delete s/`SM57-1`

update: Updates the equipment with the specified serial number.
Parameters in [square brackets] are optional.
Parameters: s/`SERIAL NUMBER` [n/`ITEM NAME`] [t/`TYPE`] [c/`COST`] [pf/`PURCHASED FROM`] [pd/`PURCHASED DATE`]
Example: update s/`SM57-1` n/`SpeakerC` c/`2510` pd/`2022-08-21`

list: Prints a list of all equipment in the inventory.
Parameters: NIL
Example: list

list: Prints a list of all equipment in the inventory of the specified type.
Parameters: `TYPE`
Example: list t/`MICROPHONE`

check: Check the details of the equipments that matches the specified parameter.
Parameters: parameter/`PARAMETER_VALUE`
Example: check n/`MixerC` or check s/`SM57-1` or check t/`MICROPHONE`

save: Saves current state of application.
Parameters: NIL
Example: save

bye: Exits the application.
Parameters: NIL
Example: bye

help: Shows details of available commands to users.
Parameters: NIL
Example: help
```

### Exiting the application: `bye`

Exits the application and saves the current state of the Equipment list to the equipments.json file.

Format: `bye`

Example of usage and output:

`bye`

Output:

```
Bye, See you again!
```

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Copy the data folder from the old computer into the other computer, in the same directory as the JAR file.

## Command Summary

'Cheat Sheet' of commands here

* Add equipment ``add n/`ITEM_NAME` s/`SERIAL_NUMBER` t/`TYPE` c/`COST` pf/`PURCHASED_FROM` pd/`PURCHASED_DATE` ``
* Check equipment ``check n/`ITEM_NAME` ``
* Listing equipment: `list`
* Listing equipment by type: ``list t/`TYPE` ``
* Updating an equipment: ``update s/`SERIAL_NUMBER` [n/`ITEM_NAME`] [t/`TYPE`] [c/`COST`] [pf/`PURCHASED_FROM`] [pd/`PURCHASED_DATE`]``
* Deleting an equipment: ``delete s/`SERIAL_NUMBER` ``
* Saving application state: `save`
* Getting help: `help`
* Exiting the application: `bye`
