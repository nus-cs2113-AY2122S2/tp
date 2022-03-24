# Sherpass User Guide

## Introduction

Sherpass is a desktop application for students to manage their academic schedules.
Optimised for use via a Command Line Interface (CLI),
users who can type fast will be able to plan out their tasks in a much quicker fashion compared to traditional GUI apps.

## Table of Content
- Quick Start
- Features
  - Add tasks: [`add`]() or [`addrecurring`]()
  - Delete tasks: [`delete`]() or [`deleterecurring`]()
  - Edit tasks: [`edit`]() or [`editrecurring`]()
  - List tasks: [`show`]()
  - Clear all tasks: [`clear`]()
  - Study session
    - Enter study session: [`study`]()
    - Start timer: [`start`]()
    - Pause timer: [`pause`]()
    - Resume timer: [`resume`]()
    - Stop timer: [`stop`]()
    - Exit study session: [`leave`]()
  - Exit program: [`exit`]()
  - Saving your tasks
- Command Summary
## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `Sherpass` 
from [here](https://github.com/AY2122S2-CS2113T-T09-1/tp/releases/tag/v1.0-Release).
3. Copy the jar file to the folder you want to use as the home folder for Sherpass.
4. Launch the application from a terminal using `java -jar Sherpass.jar`


## Features 

{Give detailed description of each feature}
### Managing your time
You can add your tasks into Sherpass and get a detailed outline of your schedule. Using this schedule, you won't
have to worry ever again about forgetting tasks and missing deadlines!

Sherpass also allows you to add recurring tasks (e.g classes) easily! Simply refer to the command details for
more information.

### Study sessions
//TBD


Notes on command input format:
- Words in UPPER_CASE are the parameters to be supplied by the user.
- Items in square brackets are optional
- Extraneous parameters for commands that do not take in parameters (such as list, stop, exit) will be ignored

## Command Guide

### Adding a recurring task: `addrecurring`
Adds a recurring task to your list of tasks.

Format: `add TASK_DESCRIPTION /do DATE /start START_TIME /end END_TIME /repeat FREQUENCY`

- `DATE` format is in `dd/MM/yyyy` - e.g. 3/10/2022 for 3 Oct 2022
- `TIME` format is in `HH:mm` - e.g. 23:00 for 11pm
- `FREQUENCY` can be either `DAILY`, `WEEKLY` or `MONTHLY`
- `START_TIME` must be after `END_TIME`

Example of usage: `addrecurring attend cs2113t lecture /do 25/2/2022 /start 16:00 /end 18:00 /repeat WEEKLY`

### Deleting a recurring task: `deleterecurring`
Deletes a recurring task from your list of tasks.

Format: `deleterecurring TASK_NUMBER`

- The specified task and it's future occurrence will be deleted.

### Editing a recurring task: `editrecurring`
Edit a recurring task in your list of tasks.

Format: `editrecurring TASK_NUMBER [TASK_DESCRIPTION] [/do DATE /start TIME /end TIME]

- To change the frequency of a recurring task, please delete and add the recurring task with the correct
frequency using the `deleterecurring `and `addrecurring` command respectively.
- At least one of the optional fields must be provided

Example of usage: 

Before edit command:


After command: `editrecurring 1 /do 25/3/2022 /start 18:00 /end 20:00`



## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Transfer a copy of your save file to your other device and place it in `JAR_FILE_LOCATION/data/Sherpass.json`

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
