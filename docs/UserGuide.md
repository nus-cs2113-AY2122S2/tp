# User Guide

## Introduction

MeetingJio is a program for **finding potential time slots for team meetings based on everyone’s availability** via a *Command Line Interface (CLI)*.
If you can type fast, MeetingJio can get your meeting management tasks done faster than manually eyeballing your timetable.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `MeetingJio` from [here](https://github.com/AY2122S2-CS2113-T11-3/tp/releases/tag/v1.0).
4. Run `java -jar MeetingJio.jar` at the terminal.
5. Type the command in the terminal and press Enter to execute it.
6. Refer to the *Features* below for the details of the commands.

## Features

### Viewing help: `help`
Shows a list of commands available.

**Format**: `help`

Example of usage:

`help`

Expected outcome:

```
Here is the list of commands available:
__________________________________________________________________________________________________________
1. To add a user: add_user [Name]
2. To add a lesson: add_lesson n/[Name] t/[Titile] d/[Day] st/[StartTime] et/[EndTime] m/[Mode]
3. To add a meeting: add_meeting t/[Title] d/[Day] st/[StartTime] et/[EndTime] m/[Mode]
4. To delete a lesson: delete n/[Name] i/[Index]
5. To list all lessons: list all
6. To list a user's lessons: list [Name]
7. To find free timeslots: free
8. To find free timeslots given a minimum duration: free [duration]
9. To clear all entries: clear all
10. To clear all entries: clear [Name]
11. To exit the application: exit
__________________________________________________________________________________________________________
```


### Adding a user: `add_user`
Adds a new user and his or her timetable to the master timetable.

**Format:** `add_user NAME`

* Duplicate user will not be added.

Example of usage:

`add_user John`

Expected outcome:
```
john's timetable is created and added to the master timetable
```


### Adding a lesson: `add_lesson`
Adds a new lesson to the list.

**Format:** `add_lesson n/NAME t/TITLE d/DAY_OF_WEEK st/START_TIME et/END_TIME m/MODE`

* The `START_TIME` and `END_TIME` have to be in 24-hour time format.
* The accepted options of `MODE` are _online_ and _physical_.
* Duplicate lesson will not be added.
* Lesson that conflicts with other events will not be added

Example of usage:

`add_lesson n/John t/CS2113 d/Friday st/1230 et/1330 m/online`

Expected outcome:
```
The following event has been added to john's timetable:
[L] TITLE: cs2113		DAY: friday		START: 1230		END: 1330		MODE: online
```


### Listing all lessons: `list`
Shows a list of lessons that has been added.

Format: `list`

Example of usage:

`list`

Expected outcome:
```
1.NAME: John Doe		TITLE: CS2113		DAY: friday		START: 1230		END: 1330		MODE: online
```


### Adding a new common meeting: `add_meeting`
Adds a new meeting that will be synced with everyone

**Format:** `add_meeting t/TITLE d/DAY_OF_WEEK st/START_TIME et/END_TIME m/MODE`

* The `START_TIME` and `END_TIME` have to be in 24-hour time format.
* The accepted options of `MODE` are _online_ and _physical_.
* All users added need to be free at the specified meeting day and time for the meeting to be successfully added and synced.

Example of usage:

`add_meeting t/meeting d/Wednesday st/1230 et/1330 m/online`

Expected outcome:
```
The following meeting has been added to everyone's timetable:
[M] TITLE: meeting		DAY: thursday		START: 1230		END: 1330		MODE: online
```


### Deleting an event: `delete`
Deletes an event from the user's specified timetable

**Format:** ` delete n/NAME`

* Deletes from the timetable of user
* Deletes the lesson at the specified `INDEX`.
* The `INDEX` refers to the index number shown in the displayed list.

Example of usage:

`delete n/John i/1`

Expected outcome:
```
The following event has been deleted from your timetable:
[L] TITLE: CS2113		DAY: friday		START: 1230		END: 1330		MODE: online
```


### Editing an event: `edit`
Edits an event from the user's specified timetable based on the user input 

**Format:** ` delete n/NAME i/INDEX t/TITLE d/DAY_OF_WEEK st/START_TIME et/END_TIME m/MODE`

* Required parameters: `NAME` and `INDEX`
* Optional parameters: `TITLE`, `DAY_OF_WEEK`, `START_TIME`, `END_TIME`, `MODE`
* Provide at least one optional parameter.
* The `INDEX` refers to the index number shown in the displayed user specified list.

Example of usage:

`edit n/John i/1 `

Expected outcome:
```

```


### Finding common timeslots: `free`
Shows a list of timeslots where everyone is free. 

Format: `free [DURATION]`
- If `DURATION` is specified, only timeslots which are longer than the duration will be shown.
- Else, all common timeslots will be shown.
- Common timeslots are kept from 0800 to 2359.

Example of usage:

`free 3`

Expected outcome:
```
Monday 1100 2359
Tuesday 0800 2359
Wednesday 0800 2359
Thursday 0800 2359
Friday 0800 1600
Friday 1800 2359
Saturday 0800 2359
Sunday 0800 2359
```
### Clearing all lessons from user: `clear`
Deletes all the lessons from specified user if any.

Format: `clear USER`

Example of usage:

`clear john`

Expected outcome:
```
john's timetable has been cleared
```

Alternatively you can clear all the events from everyone's timetable

### Clearing all lessons from all users: `clear all`

Format: `clear all`

Example of usage:

`clear all`

Expected outcome:
```
All records of everyone's timetable has been cleared
```

### Exiting the application: `exit`
Exits the application.

Format: `exit`

Example of usage:

`exit`

Expected outcome:
```
__________________________________________________________________________________________________________
See you again!
```


## Command summary


| Action       | Format                                                                     |
|--------------|----------------------------------------------------------------------------|
| Help         | `help`                                                                     |
| Add lesson   | `add_lesson n/NAME t/title d/DAY_OF_WEEK st/START_TIME et/END_TIME m/MODE` |
| Add Meeting  | `add_meeting t/title d/DAY_OF_WEEK st/START_TIME et/END_TIME m/MODE`       |
| Delete Event | `delete n/NAME i/INDEX`                                                    |
| Free         | `free`                                                               |
| List         | `list`                                                                     |
| Clear        | `clear`                                                                    |
| Exit         | `exit`                                                                     |


