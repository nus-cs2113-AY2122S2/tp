# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

## Customer Satisfaction Related Commands

### Adding a customer satisfaction: `add satifaction`
Adds a new customer satisfaction (name of customer and their satisfaction rating from 1-5) to the list of 
customer satisfactions.

Format: `add satisfaction CUSTOMER_NAME / SATISFACTION_RATING`

* The `CUSTOMER_NAME` can be in a natural language format. It must not be 
the name of a customer whose satisfaction rating was already recorded. 
* The `SATISFACTION_RATING` must be an integer from 1 to 5, inclusive. 

Example of usage:

`add satisfaction Will Smith / 3`

Expected output: 

```
========== Noted ! ==========
The Satisfaction instance WILL SMITH: 3 has been added to the list of Satisfactions.
There are currently 1 recorded customer satisfactions.
=============================
```




### View all customer satisfactions: `view satisfactions`
Lists out all customer satisfactions recorded so far in a user-readable format. 

Format: `view satisfactions`

Example of usage:

`add satisfaction Will Smith / 3`  <br />
`add satisfaction Bob / 5`  <br />
`add satisfaction Chris Rock / 4`  <br />
`view satisfactions`

Expected output:
```
========== Noted ! ==========
The Satisfaction instance WILL SMITH: 3 has been added to the list of Satisfactions.
There are currently 1 recorded customer satisfactions.
=============================

========== Noted ! ==========
The Satisfaction instance BOB: 5 has been added to the list of Satisfactions.
There are currently 2 recorded customer satisfactions.
=============================

========== Noted ! ==========
The Satisfaction instance CHRIS ROCK: 4 has been added to the list of Satisfactions.
There are currently 3 recorded customer satisfactions.
=============================

======== Customer Satisfaction List ========
1. [ WILL SMITH ]: 3
2. [ BOB ]: 5
3. [ CHRIS ROCK ]: 4
============= End of the list =============
```

### Adding a housekeeper's performance: `add performance`
Adds a new housekeeper performance (name of housekeeper and their performance rating from 1-5) to the list of
housekeeper performances. 


Format: `add performance HOUSEKEEPER_NAME / PERFORMANCE_RATING`

* The `HOUSEKEEPER_NAME` can be in a natural language format. It must not be the name of a 
housekeeper who already has a recorded performance rating, and it must be the name of a 
housekeeper who has a corresponding record in the list of housekeepers. 
* The `PERFORMANCE_RATING` must be an integer from 1-5, inclusive. 

Example of usage:

`add performance Fred Jones / 4`

Expected output:

```
========== Noted ! ==========
The HousekeeperPerformance instance FRED JONES: 4 has been added to the list of housekeeper performances.
There are currently 1 recorded housekeeper performances.
=============================
```

### View all housekeeper performances: `view performances`
Lists out all housekeeper performances recorded so far in a user-readable format. The performance
ratings are sorted in descending order (from highest to lowest). 

Format: `view performances`

Example of usage:

`add performance Fred Jones / 4`  <br />
`add performance Joe Johnson / 2`  <br />
`add performance John Smith / 3`  <br />
`view performances` 

Expected output:

```
========== Noted ! ==========
The HousekeeperPerformance instance FRED JONES: 4 has been added to the list of housekeeper performances.
There are currently 1 recorded housekeeper performances.
=============================

========== Noted ! ==========
The HousekeeperPerformance instance JOE JOHNSON: 2 has been added to the list of housekeeper performances.
There are currently 2 recorded housekeeper performances.
=============================

========== Noted ! ==========
The HousekeeperPerformance instance JOHN SMITH: 3 has been added to the list of housekeeper performances.
There are currently 3 recorded housekeeper performances.
=============================

======== Housekeeper Performance List ========
1. [ FRED JONES ]: 4
2. [ JOHN SMITH ]: 3
3. [ JOE JOHNSON ]: 2
============= End of the list =============
```


## Housekeeper Related Commands

### Adding Housekeeper Profile : `add housekeeper`

Adds a new housekeeper profile into the list of housekeepers. The add profile command includes the name and age of the
new housekeeper to be added.

Format: `add housekeeper NAME / AGE`

* The `NAME` can be in any format. For example, Susan1@.
* The `AGE` must be between 21 and 60.

Example of usage:

`add housekeeper susan / 46`

`add housekeeper jane / 33`

Expected output:
```
========== Noted ! ==========
[SUSAN]: Age: 46, Availability: <Enter Availability>
=============================
```

### View housekeeper list : `view recorded housekeeper`

View all housekeeper in the list with their name, age and availability. Availability will not be shown if user
have not entered it yet.

Format: `view recorded housekeeper`

Expected output:
```
======== Housekeeper List ========
1. [SUSAN]: Age: 46, Availability: <Enter Availability>
2. [JANE]: Age: 33, Availability: <Enter Availability>
3. [SALLY]: Age: 60, Availability: ( Monday Tuesday )
======== End of the list ========
```

### Add Availability of Housekeeper : `availability`
Declare housekeeper's availability in the week using numbers from 1 to 7 where each number represents a day in a week.

Numbers from 1 to 7 are map to a day in a week by: <br/>
`1`: Monday <br/>
`2`: Tuesday <br/>
`3`: Wednesday<br/>
`4`: Thursday <br/>
`5`: Friday <br/>
`6`: Saturday <br/>
`7`: Sunday <br/>

Format: `availability NAME / DAYS`
* `NAME` given must be in the records of housekeeper list to be eligible to add availability.
* `DAYS` given must be between 1 and 7. Multiple days can be represented using commas.

Example of usage:

`availability sally / 1`

`availability jane / 1,3,5,7`

Expected output:

`Added sally availability into records`

### Delete Housekeeper: `delete housekeeper `
When a housekeeper resigns, calling delete housekeeper command will remove housekeeper that resign from 
the list of housekeepers. This command will also show number of housekeeper left in the list of housekeeper.


Format: `delete housekeeper NAME`

* `NAME` given must be in the records of housekeeper list to be eligible for deletion.

Example of usage:
```
delete housekeeper sally
```

Expected output:
```
Deleted sally from the list of profile
Take note! Total pax of housekeeper:  2
```

### Get Housekeepers Available on Interested Days : `get available on `
Derive a list of housekeeper available from any day in a week. If command is used on days with no housekeeper 
available, list printed will indicate that no housekeeper is available on that day.

Format:
`get available on DAY`

* `DAY` have to be between 1 and 7.
* Numbers from 1 to 7 are map to a `DAY` in a week by: <br/>
  `1`: Monday <br/>
  `2`: Tuesday <br/>
  `3`: Wednesday<br/>
  `4`: Thursday <br/>
  `5`: Friday <br/>
  `6`: Saturday <br/>
  `7`: Sunday <br/>
  
* Only one day can be enquired at a time.

Example of usage:

`get available on 1`

`get available on 7`

Expected output:

If there exist any housekeeper on Monday:
```
======== Monday List ========
1. sally
======== End of the list ========
```

If there is no housekeeper available on Monday:
```
======== Monday List ========
TAKE NOTE! NO ONE IS AVAILABLE!!
======== End of the list ========
```

### Reset Housekeeper Availability: `is a new week`
When a new week begins, all housekeeper availabilities can be reset by calling this command. For verification,
command will also print out the list with all availabilities being reset.

Format: `is a new week`

Expected output:
```
Housekeeper's availability has been reset!
======== Housekeeper List ========
1. [SUSAN]: Age: 46, Availability: <Enter Availability>
2. [JANE]: Age: 33, Availability: <Enter Availability>
======== End of the list ========mand Sum
```

### Increase Age of All Housekeepers: `is a new year`
When a new year begins, all housekeeper age can be increase by one through this command.
As the age limit of working as a housekeeper in the hotel is 21 to 60 years old, housekeeper
that exceed age limit of 60 after increment will be removed from the list of housekeeper. A list of
housekeeper exceeding age limit will be shown when removing.

Format: `is a new year`

Expected output:

If list of housekeeper has housekeeper which exceed age limit:
```
Everyone age has increased by one
**Over age limit housekeeper will be removed from list**
======== Age Limit Exceed List ========
1. [LUCY]: Age: 60, Availability: <Enter Availability>
2. [JON]: Age: 60, Availability: <Enter Availability>
======== End of the list ========
```
If none of the housekeeper exceed age limit:
```
Everyone age has increased by one
**Over age limit housekeeper will be removed from list**
======== Age Limit Exceed List ========
Everyone is within age limit
======== End of the list ========
```

### Assign housekeeper to a room `assign housekeeper `
A housekeeper can be assigned to be responsible for a room. The room may or may not have a houskeeper assigned to it.

Format: `assign housekeeper NAME / ROOMID`

The `NAME` should be of a housekeeper already added in the system. The `ROOMID` should represent a room number of a room that is present in the system.

Sample input: 

```
assign susan / 301
```

Expected output:

```
======================================================================
I have assigned susan to room number 301.
======================================================================
```

## Event Related Commands

### Add an event: `add event`
Adds an event inside the list on a given date. 

Format: `add event DESCRIPTION / DATE`

The date has to strictly be in the format `yyyy-mm-dd` or the command wound not run.

Sample input:

```
add event cry / 2022-03-30
```

Expected output:

```
======================================================================
I have added the following event in your list:
Event cry (at: Mar 30 2022)
======================================================================
```

### View the list of events: `list events`

Lists all the events present in the system in order. No additional argument is required. The list of events inside the system will not change; the existing events will merely be displayed.

This command helps us know which index each event is added in. The index from this list can be referenced when the `delete` function (described below) is called.

Format: `list events`

Expected output:

```
======================================================================
Here are all the events in your list:
	1. Event something (at: Feb 2 2022)
	2. Event cry (at: Mar 30 2022)
======================================================================
```

### Delete an event: `delete event `

Deletes an event at a particular position in the list. The event will disappear from the system forever. It will not only get deleted from the list, it will vanish from the computer memory as well. 

Format: `delete event INDEX`

Make sure that you put the correct index for the event to be removed. Indexes can be seen when the list of events is printed.

Sample input: `delete event 2`

Expected output:

```
======================================================================
I have deleted the following event from your list:
	Event cry (at: Mar 30 2022)
======================================================================
```

The list after deleting this event:
```
======================================================================
Here are all the events in your list:
	1. Event something (at: Feb 2 2022)
======================================================================
```


## Room Related Commands

### Check in a room: `check in`
Checks in a room according to the room number. The status of room will change to 
Occupied

Format: `check in ROOM_NUMBMR`

* The `ROOM_NUMBER` must be inside the room list.

Example of usage:

`check in 203`

Expected output:

```
======================================================================
Type		Room Id		level		Status			Housekeeper Name
======================================================================
Triple		203			 2			Occupied			NA
```


### Check out a room: `check out`
Checks out a room according to the room number. The status of room will change to
Vaccant

Format: `check out ROOM_NUMBMR`

* The `ROOM_NUMBER` must be inside the room list.

Example of usage:

`check out 203`

Expected output:

```
======================================================================
Type		Room Id		level		Status			Housekeeper Name
======================================================================
Triple		203			 2			Vaccant			NA
```


### Check room information: `check room`
Checks the information of the room

Format: `check room ROOM_NUMBMR`

* The `ROOM_NUMBER` must be inside the room list.

Example of usage:

`check room 203`

Expected output:

```
======================================================================
Type		Room Id		level		Status			Housekeeper Name
======================================================================
Triple		203			 2			Vaccant			NA
```

### Check room information by level: `check level`
Checks information of all room at target level

Format: `check level LEVEL_NUMBER`

* The `LEVEL_NUMBER` must has at least one room.

Example of usage:

`check level 2`

Expected output:

```
Type		Room Id		level		Status			House Keeper Name
Double		201			 2			Vacant  			jane
Triple		202			 2			Vacant  			jane
Triple		203			 2			Occupied			NA
Queen		204			 2			Vacant  			NA
```

### Check room information by category: `check category`
Checks information of all room at target category

Format: `check category CATEGORY`

* The `LEVEL_NUMBER` must has at least one room.

Example of usage:

`check category Queen`

Expected output:

```
======================================================================
Type		Room Id		level		Status			Housekeeper Name
======================================================================
Queen		204			 2			Vacant  			NA
Queen		301			 3			Occupied			jane
```

### Check all room information: `check all room`
Checks information of all room at target category

Format: `check all room`


Example of usage:

`check all room`

Expected output:

```
======================================================================
Type		Room Id		level		Status			Housekeeper Name
======================================================================
Single		101			 1			Occupied			NA
Single		102			 1			Vacant  			NA
Double		103			 1			Vacant  			NA
Double		201			 2			Vacant  			jane
Triple		202			 2			Vacant  			jane
Triple		203			 2			Occupied			NA
Queen		204			 2			Vacant  			NA
Queen		301			 3			Occupied			jane
King		302			 3			Occupied			NA
King		303			 3			Vacant  			NA
Twin		401			 4			Vacant  			susan
Twin		402			 4			Occupied			NA
```
## Item Related Commands
### Add A New Item To The Item List: `add item`
Adds a new item (name of item and its pax) to the item list which represents all the items found within the inventory.

Format: `add item ITEM NAME / PAX`
* `add item` is not case-sensitive.
* `Item Name` can be any string of characters including numbers and symbols. 
* `Item Name` given must not be currently present within the item list.
* `Pax` given has a limit of 1000000.

Example of usage:
```
add item Toilet Roll / 15
```

Expected output:
```
The item and its pax has been added to the item list.
Number of items within the Item List: 1
```

### Update The Pax Of An Item In The Item List: `update item pax`
Updates the pax of an item that is currently found within the item list to reflect an actual item within the inventory increasing or decreasing.

Format: `update item pax ITEM NAME / PAX`
* `update item pax` is not case-sensitive.
* `ITEM NAME` given must be found within the item list to be eligible to be updated.
* `ITEM NAME` is not case-sensitive.
* `Pax` given has a limit of 1000000.

Example of usage:
```
update item pax Toilet Roll / 300
```

Expected output:
```
The pax of TOILET ROLL has been updated to 300.
```

### Update The Name Of An Item In The Item List: `update item name`
Updates the name of an item that is currently found within the item list.

Format: `update item name OLD ITEM NAME / NEW ITEM NAME`

* `update item name` is not case-sensitive.
* `OLD ITEM NAME` given must be found within the item list as it is the name of the item currently within the item list which we want to update. 
* `NEW ITEM NAME` is the new name for the updated item.
* `NEW ITEM NAME` is not case-sensitive.
* `OLD ITEM NAME` cannot be the same as `NEW ITEM NAME`.

Example of usage:
```
update item name Toilet Roll / Premium Toilet Roll
```

Expected output:
```
The name of TOILET ROLL has been updated to PREMIUM TOILET ROLL.`
```

### Delete An Item In The Item List: `delete item`
Removes an item that is currently found within the item list to reflect that we are no longer storing that product within the inventory.

Format: `delete item NAME`
* `delete item` is not case-sensitive.
* `NAME` given must be found within the item list to be eligible to be deleted.
* `NAME` is not case-sensitive.

Example of usage:
```
delete item Premium Toilet Roll
```

Expected output:
```
PREMIUM TOILET ROLL has been removed from the Item List.
Number of items within the Item List: 0
```

### View All Items: `view all items`
Lists out all the items along with their respective pax within the list in a user-readable format.

Format: `view all items`
* `view all items` is not case-sensitive.

Example of usage:
```
add item Toilet Roll / 15
add item Tissue Paper / 20
add item Tables / 30
view all items
```

Expected output:
```
The item and its pax has been added to the item list.
There are currently 1 items within the item list.
The item and its pax has been added to the item list.
There are currently 2 items within the item list.
The item and its pax has been added to the item list.
There are currently 3 items within the item list.
=========== Item List ===========
1. Item Name: TOILET ROLL Item Pax: 15
2. Item Name: TISSUE PAPER Item Pax: 20
3. Item Name: TABLES Item Pax: 30
======== End of the list ========
```

### View All Items Within The Item List With A Pax Of Zero: `view items with zero pax`
Lists out all the items which has a pax of zero within the item list in a user-readable format.This allows the user to easily know which items have run out in the inventory and need to be replenished.

Format: `view items with zero pax`

* `view items with zero pax` is not case-sensitive.

Example of usage:
```
update item pax Toilet Roll / 0
update item pax Tissue Paper / 0
view items with zero pax
```

Expected output:
```
The pax of TOILET ROLL has been updated to 0.
The pax of TISSUE PAPER has been updated to 0.
=========== Item List ===========
1. Item Name: TOILET ROLL Item Pax: 0
2. Item Name: TISSUE PAPER Item Pax: 0
======== End of the list ========
```

### Search For Items Within The Item List: `search item`
Displays all items (item name and pax) within the item list that matches the input keyword.

Format: `search item KEYWORD`

Example of usage:
```
search item Toilet
```
* `search item` is not case-sensitive.
* `KEYWORD` is not case-sensitive.

Expected output:
```
=========== Item List ===========
1. Item Name: TOILET ROLL Item Pax: 0
======== End of the list ========
```

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Commary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
