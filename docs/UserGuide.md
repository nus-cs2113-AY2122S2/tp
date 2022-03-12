# User Guide

## Introduction

SplitLah is an application for keeping track of expenses during group outing sessions, specifically for those who are 
budget conscious. It then proceeds to split the expenses according to the group members' individual costs for the 
activities they participated in, for a particular session, via a Command Line Interface(CLI).

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

### Creating a session: `session /create`
>Creates a new session to be managed by SplitLah. <br> 
>Sessions are a way for the user to manage their gatherings that happen across the day.

Format: `session /create /n [SESSION_NAME] /d [SESSION_DATE] /pl [NAME1 NAME2 ...]`

* `[SESSION_NAME]` refers to the name of the session.
  * The session name is **case-insensitive**.
* `[SESSION_DATE]` refers to the date of the session.
  * The format of the date can be in `DD-MM-YYYY` or `YYYY-MM-DD`.
* `[NAME1 NAME2 ...]` refers to a list of persons involved in the session.
  * Each individual name is **case-insensitive**.

> **Notes:**
>- The `[SESSION_NAME]` should be unique across sessions.
>- Each name in `[NAME1 NAME2 ...]` for the session should be unique.

Example of usage:
1. Adds a new session named Class Outing with Alice and Bob involved on 2022-03-15.
   1. `session /create /n Class Outing /d 2022-03-15 /pl Alice Bob`
   
<br>[INSERT SCREEN SHOT]
<br>
<br>
### Deleting a session: `session /delete`
>Deletes an existing session that is managed by SplitLah. <br>
>Sessions are a way for the user to manage their gatherings that happen across the day.

Format: `session /delete /sid [SESSION_ID]`

* `[SESSION_ID]` refers to the unique identifier of the session.
    * The unique identifier for a session can be retrieved with `session /list` command.

Example of usage:
1. Remove an existing session that was created with session unique identifier of 1.
   1. `session /delete /sid 1`

<br>[INSERT SCREEN SHOT]
<br>
<br>
### Creating an activity: `activity /create`
>Creates a new activity within a particular session to be managed by SplitLah. <br>
>Activities are a way for the user to keep track of the activities and their respective costs throughout a session. <br>
>Other information include the person who paid for the activity and the people involved in the activity.

Format 1: `activity /create /sid [SESSION_ID] /n [ACTIVITY_NAME] /p [PERSON_PAID] /i [NAME1 NAME2 ...]
/c [TOTAL_COST] [OPTIONAL_ARGUMENTS]`

Format 2: `activity /create /sid [SESSION_ID] /n [ACTIVITY_NAME] /p [PERSON_PAID] /i [NAME1 NAME2 ...]
/cl [COST1 COST2 ...] [OPTIONAL_ARGUMENTS]`

* `[SESSION_ID]` refers to the unique identifier of the session.
    * The unique identifier for a session can be retrieved with `session /list` command.
* `[ACTIVITY_NAME]` refers to the name of the activity.
    * The activity name is **case-insensitive**.
* `[PERSON_PAID]` refers to the person who paid for the activity.
    * The person's name is **case-insensitive**.
* `[NAME1 NAME2 ...]` refers to a list of persons involved in the activity.
    * Each individual name is **case-insensitive**.
* `[TOTAL_COST]` refers to the total cost of the activity.
* `[COST1 COST2 ...]` refers to a list of costs respective to each person involved in the activity.

> **Notes:**
>- The `[ACTIVITY_NAME]` should be unique across all activities.
>- Each name in `[NAME1 NAME2 ...]` for the activity should be unique.

Examples of usage:
1. Adds a new activity in a session with session identifier 1, named Class Lunch, where Alice paid for both
   Bob and herself with a total cost of $10.
    1. `activity /create /sid 1 /n Class Lunch /p Alice /i Alice Bob /c 10`
2. Adds a new activity in a session with session identifier 1, named Class Lunch, where Alice paid for both
   Bob and herself. Alice's meal costs $3.50 while Bob's meal costs $7.
    1. `activity /create /sid 1 /n Class Lunch /p Alice /i Alice Bob /cl 3.5 7`

<br>[INSERT SCREEN SHOT]
<br>
<br>
## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
