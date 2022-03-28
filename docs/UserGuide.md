# User Guide

## Introduction

MeetingJio is a program for **finding potential time slots for team meetings based on everyone’s availability** via a *Command Line Interface (CLI)*.
If you can type fast, MeetingJio can get your meeting management tasks done faster than manually eyeballing your timetable.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `MeetingJio` from [here](https://github.com/AY2122S2-CS2113-T11-3/tp/releases/tag/v1.0).
3. Run `java -jar MeetingJio.jar` at the terminal.
4. Type the command in the terminal and press Enter to execute it.
5. Refer to the *Features* below for the details of the commands.

## Features

### Add Lessons to your own timetable and others timetable

### See slots for possible meetings based on duration

### Book meetings based on availability of all users

### View everyone's timetable and availability

### Save your timetable details to storage and retrieve accordingly

### Viewing help: `help`
Shows a list of commands available.

**Format**: `help`

Example of usage:

`help`

Expected outcome:

```
Here is the list of commands available:
__________________________________________________________________________________________________________
1. To add a lesson: add n/[Name] l/[Lesson] d/[day] st/[StartTime] et/[EndTime] m/[Mode]
2. To delete a lesson: delete [Index]
3. To list all lessons: list
4. To clear all entries: clear
5. To exit the application: exit
__________________________________________________________________________________________________________
```

### Adding a lesson: `add`
Adds a new lesson to the list.

**Format:** `add n/NAME l/LESSON d/DAY_OF_WEEK st/START_TIME et/END_TIME m/MODE`

* The `START_TIME` and `END_TIME` have to be in 24-hour time format.
* The accepted options of `MODE` are _online_ and _physical_.
* Duplicate lesson will not be added.

Example of usage:

`add n/John Doe l/CS2113 d/Friday st/1230 et/1330 m/online`

Expected outcome:
```
The following event has been added to your timetable:
NAME: John Doe		TITLE: CS2113		DAY: friday		START: 1230		END: 1330		MODE: online
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
[M] TITLE: Wednesday		DAY: wednesday		START: 1230		END: 1330		MODE: online
```



### Deleting a lesson: `delete`
Deletes an event from the user's specified timetable

**Format:** ` delete n/NAME i/INDEX`

* Deletes from the timetable of user
* Deletes the lesson at the specified `INDEX`.
* The `INDEX` refers to the index number shown in the displayed lesson list.

Example of usage:

`delete n/John Joe i/1`

Expected outcome:
```
The following event has been deleted from your timetable:
NAME: John Doe		TITLE: CS2102		DAY: monday		START: 1230		END: 1330		MODE: online
```

### Clearing all lessons: `clear`
Deletes all the lessons from specified user if any.

Format: `clear USER`

Example of usage:

`clear john`

Expected outcome:
```
john's timetable has been cleared
```

Alternatively you can clear all the events from everyone's timetable

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


