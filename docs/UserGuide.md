# User Guide

## Contents

* TOC
  {:toc}

## Introduction

SplitLah is a CLI program that lets users split bills proportionally amongst themselves after a group outing. SplitLah does
the work of calculating how much each participant must pay across multiple activities, and keeps track of who has paid
and who has not.

Value proposition:

- Allows users to keep track of several activities and their participants.
- Splits costs evenly or unevenly depending on what is required.
- Lumps all payments due together for easy payment.
- Can be operated quickly by an experienced typist.

Target users:

- People who go on group outings often and split bills.
- People who dislike calculating how much people owe across several activities.
- People who go on group outings where different people pay for different activities, making bill-splitting harder.


## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest .jar version of SplitLah from [here](https://github.com/AY2122S2-CS2113T-T10-1/tp/releases)
3. Copy the file to the folder you wish to use as a home folder for SplitLah.
4. Start SplitLah by executing `java -jar SplitLah.jar` in the terminal.
5. Type in a command and press Enter to execute it.
6. Refer to Features for a more in-depth explanation of all commands available.

<hr>

## Features 


### Creating a session: `session /create`
> Creates a new session.<br>
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
1. Remove an existing session that was created with a session unique identifier of 1.
   - `session /delete /sid 1`
> **Notes:**
>- A session with a unique identifier of `[SESSION_ID]` has to exist before it can be removed.

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
### Creating an activity: `activity /create`
> Creates a new activity within a particular session to be managed by SplitLah. <br>
> Activities are a way for the user to keep track of the activities and their respective costs throughout a session. <br>
> Other information include the person who paid for the activity and the people involved in the activity.

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
>- The session with a unique identifier of `[SESSION_ID]` has to exist before the activity can be created.
>- The `[ACTIVITY_NAME]` should be unique across all activities.
>- Each name in `[NAME1 NAME2 ...]` for the activity should be unique.
>- The names in `[PERSON_PAID]` and `[NAME1 NAME2 ...]` have to be names that are already captured in the session with
   a unique identifier of `[SESSION_ID]`.

Examples of usage:
1. Adds a new activity in a session with a session unique identifier of 1, named Class Lunch, where Alice paid for both
   Bob and herself with a total cost of $10.
    1. `activity /create /sid 1 /n Class Lunch /p Alice /i Alice Bob /c 10`

<br>[INSERT SCREEN SHOT]
<br>
<br>
2. Adds a new activity in a session with a session unique identifier of 1, named Class Lunch, where Alice paid for both
   Bob and herself. Alice's meal costs $3.50 while Bob's meal costs $7.
    1. `activity /create /sid 1 /n Class Lunch /p Alice /i Alice Bob /cl 3.5 7`

<br>[INSERT SCREEN SHOT]
<br>
<br>
### Settle all transactions for a session: `session /summary`
> Prints out a summary of the session to tell you, for that session, who needs to pay how much to another person
> in order for everyone to have no remaining debts and no more debt to collect from others.<br>
> This helps you to quickly calculate all payments that have to be done at the end of the day for a group outing.

Format: `session /summary /sid [SESSION_ID]`

* `[SESSION_ID]` refers to the unique identifier of the session.
    * The unique identifier for a session can be retrieved with `session /list` command.

Example of usage:
1. Get a session summary for an existing session with a session unique identifier of 1.
    1. `session /summary /sid 1`
> **Notes:**
>- A session with a unique identifier of `[SESSION_ID]` has to exist before its summary can be generated.

<br>[INSERT SCREEN SHOT]
<br>
<br>
## FAQ

**Q**: Is data saved to the disk upon exit?

**A**: SplitLah 1.0 does not currently support saving data to the disk.

## Command Summary

| Action                                   | Format                                                                                                                                                                                                           |
|------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Add session                              | Format: `session /create /n [SESSION_NAME] /d [DATE] /pl [PARTICIPANTS]`<br><br> Example: `session /create /n Outing /d 15-03-2022 /pl Warren, Ivan, Roy`                                                        |
| Delete session                           | Format: `session /delete /sid [SESSION_ID]`<br><br>Example: `session /delete /sid 1`                                                                                                                             |
| Create activity and split costs evenly   | Format: `activity /create /sid [SESSION_ID] /n [ACTIVITY_NAME] /p [PAYER] /i [PARTICIPANTS] /c [TOTAL_COST]`<br><br>Example: `activity /create /sid 1 /n Lunch /p Warren /i Warren, Ivan, Roy /c 7.5`            |
| Create activity and split costs manually | Format: `activity /create /sid [SESSION_ID] /n [ACTIVITY_NAME] /p [PAYER] /i [PARTICIPANTS] /cl [COST1] [COST2]...`<br><br>Example: `activity /create /sid 1 /n Lunch /p Warren /i Warren, Ivan, Roy /c 1 1 5.5` |
| List sessions                            | Format: `session /list`                                                                                                                                                                                          |
| Show session summary                     | Format: `session /summary /sid [SESSION_ID]`<br><br>Example: `session /summary /sid 1`                                                                                                                           |
| Exit                                     | Format: `exit`                                                                                                                                                                                                   |