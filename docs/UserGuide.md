# User Guide

## Introduction

MeetingJio is a program for **finding potential time slots for team meetings based on everyone’s availability** via a *Command Line Interface (CLI)*.
If you can type fast, MeetingJio can get your meeting management tasks done faster than manually eyeballing your timetable.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `MeetingJio` from [here](http://link.to/duke).
3. Run `java -jar tp.jar` at the _home folder_ at terminal.
4. Type the command in the terminal and press Enter to execute it.
5. Refer to the *Features* below for the details of the commands.

## Features

### Viewing help: `help`
Shows a list of commands available.

Format: `help`

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

Format: `add n/NAME l/LESSON d/DAY_OF_WEEK st/START_TIME et/END_TIME m/MODE`

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

### Deleting a lesson: `delete`
Deletes a lesson from the list.

Format: ` delete INDEX`

* Deletes the lesson at the specified `INDEX`.
* The `INDEX` refers to the index number shown in the displayed lesson list.

Example of usage:

`delete 2`

Expected outcome:
```
The following event has been deleted from your timetable:
NAME: John Doe		TITLE: CS2102		DAY: monday		START: 1230		END: 1330		MODE: online
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
### Clearing all lessons: `clear`
Deletes all the lessons from the list.

Format: `clear`

Example of usage:

`clear`

Expected outcome:
```
Your whole timetable has been cleared
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

| Action | Format                                                               |
|--------|----------------------------------------------------------------------|
| Help   | `help`                                                               |
| Add    | `add n/NAME l/LESSON d/DAY_OF_WEEK st/START_TIME et/END_TIME m/MODE` |
| Delete | `delete INDEX`                                                       |
| List   | `list`                                                               |
| Clear  | `clear`                                                              |
| Exit   | `exit`                                                               |

