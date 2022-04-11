# User Guide

## Introduction

MeetingJio is a program for **finding potential time slots for team meetings based on** everyone’s **availability** via a *Command Line Interface (CLI)*.
If you can type fast, MeetingJio can get your meeting management tasks done faster than manually eyeballing your timetable.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `MeetingJio` from [here](https://github.com/AY2122S2-CS2113-T11-3/tp/releases/tag/v2.0).
3. Run `java -jar MeetingJio.jar` at the terminal.
4. Type the command in the terminal and press Enter to execute it.
5. Refer to the *Features* below for the details of the commands.

## Features

### Overview
1. Add lessons to your own timetable and others timetable
2. See slots for possible meetings based on duration
3. Book meetings based on availability of **all users**
4. View everyone's timetable and availability
5. Save your timetable details to storage and retrieve accordingly

### Viewing help: `help`
Shows a list of commands available which are case-sensitive.

**Format**: `help`

Example of usage:

`help`

Expected outcome:

```
Here is the list of commands available:
__________________________________________________________________________________________________________
1. To add a user: add_user [Name]
2. To add a lesson: add_lesson n/[Name] t/[Title] d/[Day] st/[StartTime] et/[EndTime] m/[Mode]
3. To add a meeting: add_meeting t/[Title] d/[Day] st/[StartTime] et/[EndTime] m/[Mode]
4. To delete an event: delete n/[Name] i/[Index]
5. To edit a lesson: edit n/[Name] i[Index] t/[Title] d/[Day] st/[StartTime] et/[EndTime] m/[Mode]
6. To list all events: list all
7. To list a user's events: list [Name]
8. To list all lessons: list_lesson all
9. To list a user's lessons: list_lesson [Name]
10. To list all meetings: list_meeting all
11. To list a user's meetings: list_meeting [Name]
12. To find free timeslots: free
13. To find free timeslots given a minimum duration: free [Duration]
14. To clear a user's timetable: clear [Name]
15. To clear all entries: clear all
16. To exit the application: exit
_________________________________________________________________________________________________________
```


### Adding a user: `add_user`
Creates a new user and adds his or her timetable to the master timetable.

**Format:** `add_user NAME`

| Parameters | Description      | Accepted inputs                                                                                                            |  
|:----------:|------------------|----------------------------------------------------------------------------------------------------------------------------|
|    NAME    | Name of the user | English name without any numbers or special characters except `-` and `'`. <br/> Additionally the name cannot be all `all` |

* Duplicate user will not be added.

Example of usage:
```
add_user john
add_user peter
```


Expected outcome:
```
john's timetable is created and added to the master timetable
peter's timetable is created and added to the master timetable
```


### Adding a lesson: `add_lesson`
Adds a new lesson to the user's timetable.

**Format:** `add_lesson n/NAME t/TITLE d/DAY_OF_WEEK st/START_TIME et/END_TIME m/MODE`

|  Parameters   | Description              | Accepted inputs                  |  
|:-------------:|--------------------------|----------------------------------|
|     `NAME`    | Name of the user         | Existing user                    | 
|    `TITLE`    | Title of the lesson      | Does not contain `/` character   | 
| `DAY_OF_WEEK` | Day of week              | Ranges from `monday` to `sunday` | 
| `START_TIME`  | Start time of the lesson | 24-hour format                   | 
|  `END_TIME`   | End time of the lesson   | 24-hour format                   | 
|    `MODE`     | Mode of the lesson       | `online` or `physical`           | 

* Lessons cannot span across multiple days/past midnight.
* Duplicate lesson will not be added.
* Lesson that conflicts with other events will not be added.

**Example of usage:**

```
add_lesson n/john t/cs2113 d/friday st/1230 et/1330 m/online
add_lesson n/peter t/CS2113 d/Monday st/1200 et/1300 m/online
```

**Expected outcome:**
```
The following event has been added to john's timetable:
[L] TITLE: cs2113		DAY: friday		START: 1230		END: 1330		MODE: online

The following event has been added to peter's timetable:
[L] TITLE: cs2113		DAY: monday		START: 1200		END: 1300		MODE: online
```


### Adding a new common meeting: `add_meeting`
Adds a new meeting that will be synced with everyone

**Format:** `add_meeting t/TITLE d/DAY_OF_WEEK st/START_TIME et/END_TIME m/MODE`

|  Parameters   | Description               | Accepted inputs                  |  
|:-------------:|---------------------------|----------------------------------|
|    `TITLE`    | Title of the meeting      | Do not contain `/` character     | 
| `DAY_OF_WEEK` | Day of week               | Ranges from `monday` to `sunday` | 
| `START_TIME`  | Start time of the meeting | 24-hour format                   | 
|  `END_TIME`   | End time of the meeting   | 24-hour format                   | 
|    `MODE`     | Mode of the meeting       | `online` or `physical`           | 

* All users added need to be free at the specified meeting day and time for the meeting to be successfully added and synced.
* If a new user is added, pre-existing meetings will be automatically added to the new user's timetable.
* Meetings cannot span across multiple days/past midnight.

**Example of usage:**

`add_meeting t/meeting d/Wednesday st/1230 et/1330 m/online`

**Expected outcome:**
```
The following meeting has been added to everyone's timetable: 
[M] TITLE: meeting		DAY: wednesday		START: 1230		END: 1330		MODE: online
```

### Listing all events: `list`
Shows a list of events that has been added. 

**Format:** `list NAME` or `list all`

|  Parameters   | Description              | Accepted inputs                  |  
|:-------------:|--------------------------|----------------------------------|
|     `NAME`    | Name of the user         | Existing user                    | 

* If user is specified, only that user's events will be listed out.
* If all is specified, every user's events will be listed out.

**Example of usage:**

`list all`

Expected outcome:
```
john
1.[M] TITLE: meeting		DAY: wednesday		START: 1230		END: 1330		MODE: online
2.[L] TITLE: cs2113		DAY: friday		START: 1230		END: 1330		MODE: online
peter
1.[L] TITLE: cs2113		DAY: monday		START: 1200		END: 1300		MODE: online
2.[M] TITLE: meeting		DAY: wednesday		START: 1230		END: 1330		MODE: online
```

### Listing all lessons: `list_lesson`
Shows a list of lessons that has been added.

**Format:** `list_lesson NAME` or `list_lesson all`

|  Parameters   | Description              | Accepted inputs                  |  
|:-------------:|--------------------------|----------------------------------|
|     `NAME`    | Name of the user         | Existing user                    | 

* If user is specified, only that user's lessons will be listed out with index based on that user's timetable.
* If all is specified, every user's lessons will be listed out in the order of events in the timetable.

**Example of usage:**

`list_lesson all`

**Expected outcome:**
```
john
2.[L] TITLE: cs2113		DAY: friday		START: 1230		END: 1330		MODE: online
peter
1.[L] TITLE: cs2113		DAY: monday		START: 1200		END: 1300		MODE: online
```

### Listing all meetings: `list_meeting`
Shows a list of meetings that has been added.

**Format:** `list_meeting NAME` or `list_meeting all`

|  Parameters   | Description              | Accepted inputs                  |  
|:-------------:|--------------------------|----------------------------------|
|     `NAME`    | Name of the user         | Existing user                    | 

* If user is specified, only that user's meetings will be listed out.
* If all is specified, every user's meetings will be listed out.

**Example of usage:**

`list_meeting all`

**Expected outcome:**
```
john
1.[M] TITLE: meeting		DAY: wednesday		START: 1230		END: 1330		MODE: online
peter
2.[M] TITLE: meeting		DAY: wednesday		START: 1230		END: 1330		MODE: online
```


### Finding common timeslots: `free`
Shows a list of timeslots where everyone is free.

Format: `free DURATION`

| Parameters | Description                                                 | Accepted inputs                     |  
|:----------:|-------------------------------------------------------------|-------------------------------------|
| `DURATION` | Minimum duration of free slots that is interpreted in hours | A positive integer not more than 24 | 

- If `DURATION` is specified, only timeslots which are longer than or equal the duration will be shown.
- Else, all common timeslots will be shown.
- Common timeslots are kept from 0000 to 2359 for each day, so `free 24` essentially identifies common free days.

Example of usage:

`free 3`

Expected outcome:
```
Monday 0000 1200
Monday 1300 2359
Tuesday 0000 2359
Wednesday 0000 1230
Wednesday 1330 2359
Thursday 0000 2359
Friday 0000 1230
Friday 1330 2359
Saturday 0000 2359
Sunday 0000 2359
```


### Editing a lesson: `edit`
Edits a lesson from the user's specified timetable based on the user input

**Format:** ` edit n/NAME i/INDEX t/TITLE d/DAY_OF_WEEK st/START_TIME et/END_TIME m/MODE`

|  Parameters   | Description                                    | Accepted inputs                          |   
|:-------------:|------------------------------------------------|------------------------------------------|
|    `NAME`     | Name of the user                               | Existing user                            | 
|    `INDEX`    | Index number of the specified user's timetable | Valid number shown in the displayed list | 
|    `TITLE`    | Title of the lesson                            | Does not contain `/` character           | 
| `DAY_OF_WEEK` | Day of week                                    | Ranges from `monday` to `sunday`         | 
| `START_TIME`  | Start time of the lesson                       | 24-hour format                           | 
|  `END_TIME`   | End time of the lesson                         | 24-hour format                           | 
|    `MODE`     | Mode of the lesson                             | `online` or `physical`                   | 

* **Required parameters:** `NAME` and `INDEX`
    * To edit the lesson from the user's specified timetable at the specified index.
* **Optional parameters:** `TITLE`, `DAY_OF_WEEK`, `START_TIME`, `END_TIME`, `MODE`
    * To update the lesson's parameters with the values in user input.
    * Provide at least one optional parameter.
* Note that meetings are non-editable.

**Example of usage:**

'''edit n/peter i/1 t/cs2113 d/monday m/physical'''

**Expected outcome:**
```
The lesson has been updated to the following:
[L] TITLE: cs2113		DAY: monday		START: 1200		END: 1300		MODE: physical
```


### Deleting an event: `delete`
Deletes an event from the user's specified timetable

**Format:** ` delete n/NAME i/INDEX`

| Parameters | Description                                    | Accepted inputs                          |  
|:----------:|------------------------------------------------|------------------------------------------|
|   `NAME`   | Name of the user                               | Existing user                            | 
|  `INDEX`   | Index number of the specified user's timetable | Valid number shown in the displayed list | 

* Deletes the event from the user's specified timetable at the specified index.
* If a meeting event is deleted, the meeting will be deleted from all users.

**Example of usage:**

```
delete n/john i/2
delete n/peter i/2
```

**Expected outcome:**
```
The following event has been deleted from your timetable:
[L] TITLE: cs2113		DAY: friday		START: 1230		END: 1330		MODE: online
The following meeting event has been deleted from everyone's timetable:
[M] TITLE: meeting		DAY: wednesday		START: 1230		END: 1330		MODE: online
```
A list all will show that no meetings exist
```
john
There are no events in your timetable yet!
peter
1.[L] TITLE: cs2113		DAY: monday		START: 1200		END: 1300		MODE: physical

```

### Clearing all events from user: `clear`
Deletes all the events from specified user if any and remove the user's timetable from the master timetable. 
Note that in this, case if a meeting event for this user exists, it will only be deleted for this user. 

**Format:** 
```
clear NAME
```

|  Parameters   | Description              | Accepted inputs                  |  
|:-------------:|--------------------------|----------------------------------|
|    `NAME`     | Name of the user         | Existing user                    | 

**Example of usage:**

`clear john`

**Expected outcome:**
```
john's timetable has been cleared
```


### Clearing all events from all users: `clear all`
Deletes all the events from everyone's timetable if any and remove all timetables from the master timetable.

Format: `clear all`

Example of usage:

`clear all`

Expected outcome:
```
All records of everyone's timetable has been cleared
```


### Exiting the application: `exit`
Exits the application and saves the current timetable of users into the text file `MeetingJio.txt`.

**Format:** `exit`

**Example of usage:**

`exit`

**Expected outcome:**
```
__________________________________________________________________________________________________________
Data saved to local successfully
__________________________________________________________________________________________________________
See you again!
```

### MeetingJio.txt
This shows an example of a populated text file that has been successfully saved.

**Format:**
```
Name
1.[L] TITLE: [Title] DAY: [Day] START: [StartTime] END: [EndTime] MODE: [Mode]
2.[M] TITLE: [Title] DAY: [Day] START: [StartTime] END: [EndTime] MODE: [Mode]
Name
1.[L] TITLE: [Title] DAY: [Day] START: [StartTime] END: [EndTime] MODE: [Mode]
2.[M] TITLE: [Title] DAY: [Day] START: [StartTime] END: [EndTime] MODE: [Mode]
3.[L] TITLE: [Title] DAY: [Day] START: [StartTime] END: [EndTime] MODE: [Mode]
```
**Example with actual data:**
```
ibra
1.[M] TITLE: meeting		DAY: thursday		START: 1230		END: 1330		MODE: online
2.[L] TITLE: cs2113		DAY: friday		START: 1230		END: 1330		MODE: online
3.[L] TITLE: cs2113		DAY: friday		START: 1430		END: 1530		MODE: online
ym
1.[M] TITLE: meeting		DAY: thursday		START: 1230		END: 1330		MODE: online
2.[L] TITLE: cs2113		DAY: friday		START: 1230		END: 1330		MODE: online
3.[L] TITLE: cs2113		DAY: friday		START: 1430		END: 1530		MODE: online
```

**Limitation:**
- When adding a new meeting, please add it under the **first user added**. 
- For the case above, you only need to add the new meeting for `ibra` and it will populate for `ym` as well.
- **Take note**, adding new meeting under names except 
for the first name **will be ignored**.
- This applies for editing the details of an existing meeting, please edit it under the **first user added**. 

## Current Limitation of app
- Meeting cannot span across multiple days or go past midnight. If that is the case, please create 2 separate meetings.
e.g. if I want to have a meeting from 11pm - 3am, create one for today 2300 - 2359 and another from 0000 - 0300 
for the next day. We will fix this in the next version.


## Command summary

| Action                         | Format                                                                       |
|--------------------------------|------------------------------------------------------------------------------|
| Help                           | `help`                                                                       |
| Add user                       | `add_user NAME`                                                              |
| Add lesson                     | `add_lesson n/NAME t/title d/DAY_OF_WEEK st/START_TIME et/END_TIME m/MODE`   |
| Add meeting                    | `add_meeting t/TITLE d/DAY_OF_WEEK st/START_TIME et/END_TIME m/MODE`         |
| List all events                | `list all`                                                                   |
| List user's events             | `list NAME`                                                                  |
| List all lessons               | `list_lesson all`                                                            |
| List user's lessons            | `list_lesson NAME`                                                           |
| List all meetings              | `list_meeting all`                                                           |
| List user's meetings           | `list_meeting NAME`                                                          |
| Free                           | `free DURATION`                                                              |
| Edit lesson                    | `edit n/NAME i/INDEX t/TITLE d/DAY_OF_WEEK st/START_TIME et/END_TIME m/MODE` |                                                        |
| Delete event                   | `delete n/NAME i/INDEX`                                                      |
| Clear events from user         | `clear NAME`                                                                 |
| Clear all events from everyone | `clear all`                                                                  |
| Exit                           | `exit`                                                                       |


