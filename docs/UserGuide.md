# User Guide

## Introduction

SplitLah is an application for keeping track of expenses during group outing sessions, specifically for those who are 
budget conscious. It then proceeds to split the expenses according to the group members' individual costs for the 
activities they participated in, for a particular session, via a Command Line Interface(CLI).

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest .jar version of SplitLah from [here](https://github.com/AY2122S2-CS2113T-T10-1/tp/releases)
3. Copy the file to the folder you wish to use as a home folder for SplitLah.
4. Via command line, navigate to the home folder and run it with `java -jar SplitLah.jar`.
5. Type in a command and press Enter to execute it. A few commands you can try are:
    - `session /create /n Apple /d 11-03-2022 /pl Warren Ivan Roy` : Creates a Session named "Apple" occurring on 
   11/03/2022 (DD-MM-YYYY) with Warren, Ivan, and Roy as members.
    - `session /list` : Lists all Sessions.
    - `activity /create /sid 1 /n Lunch /p Warren /i Warren Roy Ivan /c 7.5` : Creates an Activity named "Lunch" and
   assigns it to the Session #1. The participats are Warren, Roy and Ivan, but only Warren has paid.
    - `activity /list /sid 1` : Lists Activities grouped under Session #1.
6. Refer to Features for a more in-depth explanation of all commands available.

## Features 

### Creating a session: `session /create`
> Creates a new session to be managed by SplitLah. <br> 
> Sessions are a way for the user to manage their gatherings that happen across the day.

Format: `session /create /n [SESSION_NAME] /d [SESSION_DATE] /pl [NAME1 NAME2 ...]`

* `[SESSION_NAME]` refers to the name of the session.
  * The session name is **case-insensitive**.
* `[SESSION_DATE]` refers to the date of the session.
  * The format of the date must be in `DD-MM-YYYY`.
* `[NAME1 NAME2 ...]` refers to a list of persons involved in the session.
  * Each individual name is **case-insensitive**.

> **Notes:**
>- The `[SESSION_NAME]` should be unique across sessions.
>- Each name in `[NAME1 NAME2 ...]` for the session should be unique.

Example of usage:
1. Adds a new session named Class Outing with Alice and Bob involved on 15-03-2022.
   - `session /create /n Class Outing /d 15-03-2022 /pl Alice Bob`
   
![Session create command Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/SessionCreateCommand.png)
<br>
<br>
### Deleting a session: `session /delete`
> Deletes an existing session that is managed by SplitLah. <br>
> Sessions are a way for the user to manage their gatherings that happen across the day.

Format: `session /delete /sid [SESSION_ID]`

* `[SESSION_ID]` refers to the unique identifier of the session.
    * The unique identifier for a session can be retrieved with `session /list` command.

Example of usage:
1. Remove an existing session that was created with session unique identifier of 1.
   - `session /delete /sid 1`

![Session delete command Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/SessionDeleteCommand.png)
<br>
<br>
### Listing all sessions: `session /list`
> List all sessions that were previously created.

Format: `session /list`

Example of usage:
![Session list command Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/SessionListCommand.png)
<br>
<br>

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

|Command|Description|
|---|---|
|`session`|Creates 
* Add todo `todo n/TODO_NAME d/DEADLINE`
