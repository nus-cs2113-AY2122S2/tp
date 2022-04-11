# User Guide
## Introduction

Hotel Lite is a convenient tool for hotel managers to keep track of multiple management tasks. 
This application offers functionalities for keeping track of hotel inventory,
managing housekeeper staff records, recording customer satisfaction ratings, 
assigning housekeepers to rooms, 
adding, deleting, viewing events happening in the hotel,
and maintaining the state of checked-in/checked-out rooms. 

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `Hotel Lite` from [here](http://link.to/duke).
3. To get a list of commands available type `help` after running the application.
4. **WARNING: Please do not touch the save files (in the `ListFolder` folder) that save the
user's application usage history.** This will interfere with the function of the program. 
5. __WARNING: This application does not allow duplicate command strings.__ For example, `help help` will not be accepted and will throw an exception.

## Features
1. [Customer Satisfaction Related Commands](#customer-satisfaction-related-commands)
    * Add Customer Satisfaction 
    * View All Customer Satisfactions
    * Calculate Average Satisfaction
2. [Housekeeper Related Commands](#housekeeper-related-commands)
   * Add Housekeeper Profile
   * Add/Update Availability of Housekeeper
   * View Recorded Housekeeper List
   * Delete Housekeeper
   * Obtain Housekeepers on Days of Interest
   * Reset Housekeeper Availability
   * Increase Age of All Housekeepers
   * Adding Housekeeper Performance Rating
   * View All Housekeeper Performances
   * Assign a housekeeper to a room
3. [Event Related Commands](#event-related-commands)
   * Add an event
   * Delete an event
   * View previously added events
4. [Room Related Commands](#room-related-commands)
   * Check in a room
   * Check out a room
   * Check room information
   * Check all room information
   * Check room information by level
   * Check room information by category
5. [Item Related Commands](#item-related-commands)
   * Add A New Item To The Item List
   * Update The Pax Of An Item In The Item List
   * Update The Name Of An Item In The Item List
   * Delete An Item In The Item List
   * View All Items
   * View All Items Within The Item List With A Pax Of Zero
   * Search For Items Within The Item List


## Customer Satisfaction Related Commands

### Adding a customer satisfaction: `add satifaction`
Adds a new customer satisfaction (name of customer and their satisfaction rating from 1-5) to the list of 
customer satisfactions.

Format: `add satisfaction CUSTOMER_NAME / SATISFACTION_RATING`

* The `CUSTOMER_NAME` can be in a natural language format. It must not be 
the name of a customer whose satisfaction rating was already recorded. 
It must not contain any non-alphabetical characters (aside from spaces). 
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

### Calculate average satisfaction: `view average satisfaction`
Calculates the mean customer satisfaction. 

Format: `view average satisfaction`

Example of usage:

`add satisfaction Will Smith / 3`  <br />
`add satisfaction Bob / 5`  <br />
`add satisfaction Chris Rock / 4`  <br />
`view average satisfaction`

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

Average customer satisfaction: 4.0
```


## Housekeeper Related Commands

### Adding Housekeeper Profile : `add housekeeper`

Adds a new housekeeper profile into the list of housekeepers. The add profile command includes the name and age of the
new housekeeper to be added. As housekeeper is being added for the first time, availability shown will be empty ("N/A").


Format: `add housekeeper NAME / AGE`

* The `NAME` cannot have digits and symbols.
* The `NAME` given must not exist in the housekeeper list.
* The `AGE` must be between 21 and 60.

Example of usage:

`add housekeeper susan / 46`

`add housekeeper jane / 33`

Expected output:

If housekeeper has not been recorded:
```
=============== Noted ! ================
[SUSAN]: Age: 46, Availability: N/A
========================================
```

If housekeeper has been recorded:
```
Error! This person has already been recorded.
```

### Add/Update Availability of Housekeeper : `availability`
Declare housekeeper's availability in the week using numbers from 1 to 7 where each number represents a day in a week.
User can update availability by calling the command again.

Numbers from 1 to 7 are map to a day in a week by: <br/>
`1`: Monday <br/>
`2`: Tuesday <br/>
`3`: Wednesday<br/>
`4`: Thursday <br/>
`5`: Friday <br/>
`6`: Saturday <br/>
`7`: Sunday <br/>

Format: `availability NAME / DAY(S)`
* `NAME` given must be in the records of housekeeper list to be eligible to add availability.
* `DAY(S)` given must be between 1 and 7. Multiple days can be represented using commas.
* `DAY(S)` given can be duplicated as long as it is an integer between 1 and 7. The duplicated days will be recorded only
once in the list

Example of usage:

`availability sally / 1`

`availability jane / 1,3,5,7,3`

`availability susan / 1,3,5,`

Expected output:
```
================ Noted! ===================
Added sally availability into records
===========================================
```


### View Recorded Housekeeper List : `view recorded housekeepers`

View all housekeeper in the list with their name, age and availability. Availability will not be shown if user
have not entered it yet.

Format: `view recorded housekeepers`

Example of usage:
```
add housekeeper susan / 46
add housekeeper jane / 33
add housekeeper sally / 33
availability sally / 1,7,
view recorded housekeepers
```

Expected output:
```
=============== Noted ! ================
[SUSAN]: Age: 46, Availability: N/A
========================================

=============== Noted ! ================
[JANE]: Age: 33, Availability: N/A
========================================

=============== Noted ! ================
[SALLY]: Age: 33, Availability: N/A
========================================

================ Noted! ===================
Added sally availability into records
===========================================

=============== Housekeeper List ================
1. [SUSAN]: Age: 46, Availability: N/A
2. [JANE]: Age: 33, Availability: N/A
3. [SALLY]: Age: 33, Availability: Monday Sunday 
=============== End of the list =================
```

### Delete Housekeeper : `delete housekeeper `
When a housekeeper resigns, the deleted housekeeper command removes the resigning housekeeper from the list of 
housekeepers. This command will also display the number of housekeepers remaining in the housekeeper list.


Format: `delete housekeeper NAME`

* `NAME` given must be in the records of housekeeper list to be eligible for deletion.

Example of usage:
```
delete housekeeper sally
```

Expected output:

If housekeeper to be deleted exists in the list for deletion:
```
================ Noted! ===================
Deleted sally from the list of profile
Take note! Total pax of housekeeper:  2
===========================================
```

If housekeeper to be deleted does not exist in the list for deletion:
```
Error! User does not exist.
```

### Obtain Housekeepers on Days of Interest : `get available on `
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
```
add housekeeper sally / 33
availability sally / 1,7,
get available on 1
```
Expected output:

If there exist any housekeeper on Monday:
```
=========== Monday List ===========
1. sally
========= End of the list =========
```

If there is no housekeeper available on Monday:
```
=========== Monday List ===========
TAKE NOTE! NO ONE IS AVAILABLE!!
========= End of the list =========
```

### Reset Housekeeper Availability : `is a new week`
When a new week begins, all housekeeper availabilities can be reset by calling this command. For verification,
command will also print out the list with all availabilities being reset.

Format: `is a new week`

Expected output:

Initial Housekeeper List Recorded:
```
=============== Housekeeper List ================
1. [SUSAN]: Age: 46, Availability: Monday Wednesday Thursday Friday 
2. [JANE]: Age: 33, Availability: Monday Wednesday Friday Saturday 
=============== End of the list =================
```

After calling new week command:
```
Housekeeper's availability has been reset!
=============== Housekeeper List ================
1. [SUSAN]: Age: 46, Availability: N/A
2. [JANE]: Age: 33, Availability: N/A
=============== End of the list =================
```

### Increase Age of All Housekeepers : `is a new year`
This command can be used to increase the age of all housekeepers by one when a new year begins.
Housekeepers who exceed the age restriction of 60 after increment will be removed from the list of 
housekeepers, as the age limit for working as a housekeeper at the hotel is 21 to 60 years old. When the 
housekeepers are removed, a list of those who are over the age restriction will be displayed.

Format: `is a new year`

Expected output:

If list of housekeeper has housekeeper which exceed age limit:

Initial Recorded Housekeeper List:
```
=============== Housekeeper List ================
1. [SUSAN]: Age: 46, Availability: N/A
2. [JANE]: Age: 33, Availability: N/A
3. [LUCY]: Age: 60, Availability: N/A
4. [JON]: Age: 60, Availability: N/A
5. [CALLY]: Age: 59, Availability: N/A
=============== End of the list =================
```

After calling new year command:
```
Everyone age has increased by one
**Over age limit housekeeper will be removed from list**
======== Age Limit Exceed List ========
1. [LUCY]: Age: 60, Availability: N/A
2. [JON]: Age: 60, Availability: N/A
=========== End of the list ===========
```
Recorded Housekeeper List after command:
```
=============== Housekeeper List ================
1. [SUSAN]: Age: 47, Availability: N/A
2. [JANE]: Age: 34, Availability: N/A
3. [CALLY]: Age: 60, Availability: N/A
=============== End of the list =================
```

However, If none of the housekeeper exceed age limit:
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
================ Noted! ===================
 I have assigned susan to room number 301.
===========================================
```

### Adding a housekeeper's performance: `add performance`
Adds a new housekeeper performance (name of housekeeper and their performance rating from 1-5) to the list of
housekeeper performances.

Format: `add performance HOUSEKEEPER_NAME / PERFORMANCE_RATING`

* The `HOUSEKEEPER_NAME` can be in a natural language format. It must not be the name of a
  housekeeper who already has a recorded performance rating, and it must be the name of a
  housekeeper who has a corresponding record in the list of housekeepers. It must not contain any 
  non-alphabetical characters (aside from spaces).
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


## Event Related Commands

### Add an event: `add event`
Adds an event inside the list on a given date. 

Format: `add event DESCRIPTION / DATE`

The date has to strictly be in the format `yyyy-mm-dd` or the command will not go through.

Sample input:

```
add event study / 2022-12-13
```

Expected output:

```
=================== Noted! ======================
I have added the following event in your list:
	Event study (at: Dec 13 2022)
=================================================
```

### View the list of events: `view events`

Lists all the events present in the system in order. No additional argument is required. The list of events inside the system will not change; the existing events will merely be displayed.

This command helps us know which index each event is added in. The index from this list can be referenced when the `delete` function (described below) is called.

Format: `view events`

Expected output:

```
=================== Noted! ======================
Here are all the events in your list:
	1. Event study (at: Dec 12 2022)
=================================================
```

### Delete an event: `delete event `

Deletes an event at a particular position in the list. The event will disappear from the system forever. It will not only get deleted from the list, it will vanish from the computer memory as well. 

Format: `delete event INDEX`

Make sure that you put the correct index for the event to be removed. Indexes can be seen when the list of events is printed.

Sample input: `delete event 1`

Expected output:

```
=================== Noted! ======================
I have deleted the following event from your list:
	Event study (at: Dec 12 2022)
=================================================
```


## Room Related Commands
This app is targeted for one hotel, thus the room has been fixed and the information of room cannot be modified.

### Check in a room: `check in`
Checks in a room according to the room number. The status of room will change to 
Occupied

Format: `check in ROOM_NUMBMR`

* The `ROOM_NUMBER` must be inside the room list.

Example of usage:

`check in 203`

Expected output:

```
=======================================================================
Type           Room Id        Level          Status         Housekeeper    
=======================================================================
Triple         203            2              Occupied       NA    
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
=======================================================================
Type           Room Id        Level          Status         Housekeeper    
=======================================================================
Triple         203            2              Vacant         NA      
```


### Check room information: `check room`
Checks the information of the room

Format: `check room ROOM_NUMBMR`

* The `ROOM_NUMBER` must be inside the room list.

Example of usage:

`check room 203`

Expected output:

```
=======================================================================
Type           Room Id        Level          Status         Housekeeper    
=======================================================================
Triple         203            2              Vacant         NA    
```

### Check room information by level: `check level`
Checks information of all room at target level (1-4)

Format: `check level LEVEL_NUMBER`

* The `LEVEL_NUMBER` must has at least one room.

Example of usage:

`check level 2`

Expected output:

```
=======================================================================
Type           Room Id        Level          Status         Housekeeper    
=======================================================================
Double         201            2              Vacant         NA                            
Triple         202            2              Vacant         NA                            
Triple         203            2              Vacant         NA                            
Queen          204            2              Vacant         NA   
```

### Check room information by category: `check category`
Checks information of all room at target category (single, double, triple, queen, king, twin)

Format: `check category CATEGORY`

* The `LEVEL_NUMBER` must has at least one room.

Example of usage:

`check category Queen`

Expected output:

```
=======================================================================
Type           Room Id        Level          Status         Housekeeper    
=======================================================================
Queen          204            2              Vacant         NA                            
Queen          301            3              Vacant         NA    
```

### Check all room information: `check all room`
Checks information of all room at target category

Format: `check all room`


Example of usage:

`check all room`

Expected output:

```
=======================================================================
Type           Room Id        Level          Status         Housekeeper    
=======================================================================
Single         101            1              Vacant         NA                            
Single         102            1              Vacant         NA                            
Double         103            1              Vacant         NA                            
Double         201            2              Vacant         NA                            
Triple         202            2              Vacant         NA                            
Triple         203            2              Vacant         NA                            
Queen          204            2              Vacant         NA                            
Queen          301            3              Vacant         NA                            
King           302            3              Vacant         NA                            
King           303            3              Vacant         NA                            
Twin           401            4              Vacant         NA                            
Twin           402            4              Vacant         NA                            

```
## Item Related Commands

### Add A New Item To The Item List: `add item`
Adds a new item (name of item and its pax) to the item list which represents all the items found within the inventory.

Format: `add item ITEM NAME / PAX`
* `add item` is not case-sensitive.
* `Item Name` must only contain alphabetical characters, digits, whitespaces or apostrophe  
* `Item Name` given must not be currently present within the item list.
* `Pax` given must be from 1 to 1000000.

Example of usage:
```
add item Toilet Roll / 15
```

Expected output:
```
================ Noted! ===================
The item and its pax has been added to the item list.
There are currently 1 items within the item list.
===========================================
```

### Update The Pax Of An Item In The Item List: `update item pax`
Updates the pax of an item that is currently found within the item list to reflect an actual item within the inventory increasing or decreasing.

Format: `update item pax ITEM NAME / PAX`
* `update item pax` is not case-sensitive.
* `ITEM NAME` given must be found within the item list to be eligible to be updated.
* `ITEM NAME` is not case-sensitive.
* `ITEM NAME` must only contain alphabetical characters, digits, whitespaces or apostrophes.
* `Pax` given must have a range from 0 to 1000000.

Example of usage:
```
update item pax Toilet Roll / 300
```

Expected output:
```
================ Noted! ===================
The pax of TOILET ROLL has been updated to 300.
===========================================
```

### Update The Name Of An Item In The Item List: `update item name`
Updates the name of an item that is currently found within the item list.

Format: `update item name OLD ITEM NAME / NEW ITEM NAME`

* `update item name` is not case-sensitive.
* `OLD ITEM NAME` given must be found within the item list as it is the name of the item currently within the item list which we want to update. 
* `OLD ITEM NAME` cannot be the same as `NEW ITEM NAME`.
* `OLD ITEM NAME` must only contain alphabetical characters, digits, whitespaces or apostrophes.
* `NEW ITEM NAME` is the new name for the updated item.
* `NEW ITEM NAME` is not case-sensitive.
* `NEW ITEM NAME` must only contain alphabetical characters, digits, whitespaces or apostrophes.

Example of usage:
```
update item name Toilet Roll / Premium Toilet Roll
```

Expected output:
```
================ Noted! ===================
The name of TOILET ROLL has been updated to PREMIUM TOILET ROLL.
===========================================
```

### Delete An Item In The Item List: `delete item`
Removes an item that is currently found within the item list to reflect that we are no longer storing that product within the inventory.

Format: `delete item NAME`
* `delete item` is not case-sensitive.
* `NAME` given must be found within the item list to be eligible to be deleted.
* `NAME` is not case-sensitive.
* `NAME` must only contain alphabetical characters, digits, whitespaces or apostrophes.

Example of usage:
```
delete item Premium Toilet Roll
```

Expected output:
```
================ Noted! ===================
PREMIUM TOILET ROLL has been removed from the Item List.
There are currently 0 items within the Item List.
===========================================
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
================ Noted! ===================
The item and its pax has been added to the item list.
There are currently 1 items within the item list.
===========================================
================ Noted! ===================
The item and its pax has been added to the item list.
There are currently 2 items within the item list.
===========================================
================ Noted! ===================
=============== Item List =================
The item and its pax has been added to the item list.
There are currently 3 items within the item list.
===========================================
=============== Item List =================
1. Item Name: TOILET ROLL Item Pax: 15
2. Item Name: TISSUE PAPER Item Pax: 20
3. Item Name: TABLES Item Pax: 30
============ End of the list ==============
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
================ Noted! ===================
The pax of TOILET ROLL has been updated to 0.
===========================================
================ Noted! ===================
The pax of TISSUE PAPER has been updated to 0.
===========================================
=============== Item List =================
1. Item Name: TOILET ROLL Item Pax: 0
2. Item Name: TISSUE PAPER Item Pax: 0
============ End of the list ==============
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
=============== Item List =================
1. Item Name: TOILET ROLL Item Pax: 0
============ End of the list ==============
```


## Command Summary

| General Command | Format, Example |
| --- | --- |
| bye | ```bye``` |


| Satisfaction Command      | Format, Example                                                                                   |
|---------------------------|---------------------------------------------------------------------------------------------------|
| add satisfaction          | ```add satisfaction CUSTOMER_NAME / SATISFACTION_RATING```<br/>eg. ```add satisfaction bob / 5``` |
| view satisfactions        | ```view satisfactions```                                                                          |
| view average satisfaction | ```view average satisfaction```                                                                   |


| Housekeeper Command | Format, Example                                                                                     |
| --- |-----------------------------------------------------------------------------------------------------|
| add housekeeper | ```add housekeeper NAME / AGE```<br/>eg. ```add housekeeper susan / 46```                           |
| availability | ```availability NAME / DAY(S)```<br/>eg.```availability jane / 1,3,5,7```                           |
| view recorded housekeepers | ```view recorded housekeepers```                                                                    |
| delete | ```delete housekeeper NAME``` <br/>eg. ```delete housekeeper sally```                               |
| get available on | ```get available on DAY ```<br/>eg.```get available on 1```                                         |
| is a new week | ```is a new week ```                                                                                |
| is a new year | ```is a new year ```                                                                                |
| assign housekeeper | ```assign NAME / ROOM_NUMBER```<br/>eg.```assign susan / 301```                                     |
| add housekeeper performance | ``` add performance HOUSEKEEPER_NAME / PERFORMANCE_RATING``` <br/>eg. ```add performance bob / 5``` |
| view all housekeeper performances | ``` view performances```                                                                            |


| Room Command | Format, Example |
| --- | --- |
| check in a room | ```check in ROOM_NUMBER```<br/>eg. ```check in 301```|
| check out a room | ```check out ROOM_NUMBER```<br/>eg. ```check out 301```|
| check room information | ```check room ROOM_NUMBER```<br/>eg. ```check room 301```|
| check all room information | ```check all room``` <br/>eg. ```check all room```|
| check room information by level | ```check level LEVEL_NUMBER ```<br/>eg.```check level 4``` |
| check room information by category | ```check category CATEGORY ```<br/>eg.```check category single``` |

| Item Command                       | Format, Example |
|------------------------------------| --- |
| add Item                           | ```add item ITEM NAME / PAX```<br/>eg. ```add item Toilet Roll / 15```|
| update item pax                   | ```update item pax ITEM NAME / PAX```<br/>eg. ```update item pax Toilet Roll / 300```|
| update item name             | ```update item name OLD ITEM NAME / NEW ITEM NAME```<br/>eg. ```update item name Toilet Roll / Premium Toilet Roll```|
| delete item         | ```delete item NAME``` <br/>eg. ```delete item Premium Toilet Roll```|
| view all items    | ```view all items ``` |
| view items with zero pax | ```view items with zero pax ```|
| search item | ```search item KEYWORD ```<br/>eg.```search item Toilet``` |

| Event Command | Format, Example |
| --- | --- |
| add an event | ```add event DESCRIPTION / DATE```<br/>eg. ```add event Submit TP / 2022-04-11```|
| delete an event | ```delete event INDEX```<br/>eg. ```delete event 1```|
| view all events | ```view events```|
