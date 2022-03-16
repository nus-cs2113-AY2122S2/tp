# User Guide

## Introduction

MeetingJio is a program for **finding potential time slots for team meetings based on everyone’s availability** via a *Command Line Interface (CLI)*.
If you can type fast, MeetingJio can get your meeting management tasks done faster than manually eyeballing your timetable.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `MeetingJio` from [here](http://link.to/duke).
3. Run `java -jar tp.jar` at the _home folder_ at terminal.
4. Type the command in the terminal and press Enter to execute it.
5. Refer to the *Features* below for the details of the commands.

## Features

### Viewing help: `help`
Shows a list of commands available.

Format: `help`

### Adding a lesson: `add`
Adds a new lesson to the list.

Format: `add n/NAME l/LESSON d/DAY_OF_WEEK st/START_TIME et/END_TIME m/MODE`

* The `START_TIME` and `END_TIME` have to be in 24-hour time format.
* The accepted options of `MODE` are _online_ and _physical_.
* Duplicate lesson will not be added.

Example of usage:

`add n/John Doe l/CS2113 d/Friday st/1230 et/1330 m/online`

### Deleting a lesson: `delete`
Deletes a lesson from the list.

Format: ` delete INDEX`

* Deletes the lesson at the specified `INDEX`.
* The `INDEX` refers to the index number shown in the displayed lesson list.

Example of usage:

`delete 2`

### Listing all lessons: `list`
Shows a list of lessons that has been added.

Format: `list`

### Clearing all lessons: `clear`
Deletes all the lessons from the list.

Format: `clear`

### Exiting the application: `exit`
Exits the application.

Format: `exit`


## Command summary

| Action | Format                                                               |
|--------|----------------------------------------------------------------------|
| Help   | `help`                                                               |
| Add    | `add n/NAME l/LESSON d/DAY_OF_WEEK st/START_TIME et/END_TIME m/MODE` |
| Delete | `delete INDEX`                                                       |
| List   | `list`                                                               |
| Clear  | `clear`                                                              |
| Exit   | `exit`                                                               |

