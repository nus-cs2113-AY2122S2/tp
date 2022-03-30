<p align="center"><img alt="logo" src="https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/pngLogo.png"></p>

# SplitLah
SplitLah is a desktop app with a Command Line Interface (CLI) for **splitting bills** amongst you and your friends after a group outing,
where each group outing can consist of several activities with **different people paying the bill** for each activity.
SplitLah does the work of **calculating how much each participant owes** and whom they must pay at the end of the activity.
You will no longer have to waste time doing these calculations by hand, leaving you with more time to spend with your
friends.

SplitLah also **consolidates all debts intelligently** so that they can be paid off with the fewest number of transactions 
possible, helping you to **reduce time spent on making pointless transactions**. 
You can **look up past activities** anytime you want. SplitLah remembers your information by saving your outings and
activities.
If you can type fast, SplitLah can manage your outings faster than apps using a Graphical User Interface (GUI).

### Who SplitLah is for:
- People who go on group outings often and love to split bills.
- People who dislike manually calculating how much people owe across several activities.
- People who go on group outings where different people pay for different activities, making bill-splitting harder.
- People who want to manage outings with multiple social circles in one application.
- People who can type fast.

<hr>

## Contents
* [How to use this user guide](#how-to-use-this-user-guide)
* [Quick Notes](#quick-notes)
* [Quick Start](#quick-start)
* [How SplitLah works](#how-splitlah-works)
* [Features](#features)
  * [Session Management](#session-management)
      * [Creating a session: `session /create`](#creating-a-session-session-create)
      * [Deleting a session: `session /delete`](#deleting-a-session-session-delete)
      * [Editing a session: `session /edit`](#editing-a-session-session-edit)
      * [Viewing a session: `session /view`](#viewing-a-session--session-view)
      * [Listing all sessions: `session /list`](#listing-all-sessions-session-list)
  * [Activity Management](#activity-management)
    * [Creating an activity: `activity /create`](#creating-an-activity-activity-create)
    * [Deleting an activity: `activity /delete`](#deleting-an-activity-activity-delete)
    * [Editing an activity: `activity /edit`](#editing-an-activity-activity-edit)
    * [Viewing an activity: `activity /view`](#viewing-an-activity-activity-view)
    * [Listing all activities in a session: `activity /list`](#listing-all-activities-in-a-session-activity-list)
  * [Transaction Management](#transaction-management)
    * [Settling all transactions for a session: `session /summary`](#settling-all-transactions-for-a-session-session-summary)
  * [Group Management](#group-management)
    * [Creating a group: `group /create`](#creating-a-group-group-create)
    * [Deleting a group: `group /delete`](#deleting-a-group-group-delete)
    * [Editing a group: `group /edit`](#editing-a-group-group-edit)
    * [Viewing a group: `group /view`](#viewing-a-group-group-view)
    * [Listing all groups: `group /list`](#listing-all-groups-group-list)
  * [Miscellaneous](#miscellaneous)
    * [Listing all available commands: `help`](#listing-all-available-commands-help)
    * [Exiting the application: `exit`](#exiting-the-application-exit)
* [FAQ](#faq)
* [Command Summary](#command-summary)

<hr>

## How to use this user guide
- For each feature that SplitLah supports, we refer it as commands.
- We classify each command under 5 sections, [Session Management](#session-management), [Activity Management](#activity-management), 
[Transaction Management](#transaction-management), [Group Management](#group-management) and [Miscellaneous](#miscellaneous).
- Each command is explained in greater detail in each command section.
- The format for each command and examples are encased within a `code block`, so that they can be copied directly into SplitLah if needed.
  - Example: `help`
- In each command: 
  - The blocks indicated with 💡 are important things to take note of when using the command.
  - The blocks indicated with ⚠️are warnings that you should pay attention to when using the command.

## Quick Notes
- Allowed characters for values:
    - Alphanumeric characters: `A-Z`, `a-z`, `0-9`
    - Decimals: `3.5`
    - Whitespace: `Birthday party`
- A forward slash `/` indicates a delimiter and is used to separate commands into parts.
  Each command's documentation specifies the required delimiters and their purpose.
    - Example: `/n`, `/sid`
- Parameters enclosed in `[ ]` must be supplied by the user.
    - Example: `[SESSION_ID]`
- Parameters with an ellipsis `...` indicate that the user can supply multiple values.
    - Example: `[COST1 COST2 ...]`
- Parameters enclosed within `[<` and `>]` indicates that the arguments are optional.
    - Example: `[</gst [GST_PERCENTAGE]>] [</sc [SERVICE_CHARGE]>]`

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest .jar version of SplitLah from [here](https://github.com/AY2122S2-CS2113T-T10-1/tp/releases)
3. Copy the file to the folder you wish to use as a home folder for SplitLah.
4. Start SplitLah by executing `java -jar SplitLah.jar` in the terminal.
5. Type in a command and press Enter to execute it.
6. Refer to Features for a more in-depth explanation of all commands available.

## How SplitLah works
- An activity represents a single group activity, paid for by one person. An activity stores a list of its participants,
the payer and how much each participant owes.
  - Example: `Lunch at a restaurant`
  - Consisting of the following participants:
    - `Alice`, `Bob`, `Charlie`
  - Paid for by:
    - `Alice`
- A session represents a period of time and stores one or more activities, as well as a list of participants. Each
activity could have a different payer.
  - Example: `Bob's birthday`
  - Consisting of the following participants:
    - `Alice`, `Bob`, `Charlie`
  - Consisting of the following activities:
    - `Breakfast at McDonald's`, `Lunch at a restaurant`, `Movie at a theatre`
    - Each paid for by a different person.
- At the end of a session, SplitLah calculates how much each person owes and who they need to pay. This information is
displayed in an easy-to-read summary.

<hr>

## Features 

## _Session Management_
> A session represents a group outing spanning an arbitrary period of time containing one or more activities.

<br>

### Creating a session: `session /create`
Creates a session so that you can manage your group outings using SplitLah. <br>

> Format: `session /create /n [SESSION_NAME] /d [SESSION_DATE] /pl [NAME1 NAME2 ...] [</gid [GROUD_ID]>]`
>* `[SESSION_NAME]` refers to the name of the session.
>  * The session name is **case-insensitive**.
>* `[SESSION_DATE]` refers to the date of the session.
>  * The format of the date must be in `DD-MM-YYYY`.
>* `[NAME1 NAME2 ...]` refers to a list of persons involved in the session.
>  * Each individual name is **case-insensitive**.
 
<br>

>  **💡 Notes:**
>- The `[SESSION_NAME]` should be unique across all active sessions.
>- Each name in `[NAME1 NAME2 ...]` for a particular session should be unique.
> 
> **⚠️Warnings:**
>- When using `/pl` and `/gid` delimiters together, if there is a duplicated name in `/pl` and 
   specified group with `/gid`. The duplicate name would be removed, storing only 1 instance of it.
>- Example: Where the group specified by `/gid` consists of Alice and Bob and the arguments of `/pl` 
   includes Alice, only two names, Alice and Bob, would be saved.

<br>

**Example 1** 
- Adds a new session named Class Outing with Alice and Bob involved on 15-03-2022.<br><br>
  `session /create /n Class Outing /d 15-03-2022 /pl Alice Bob` <br><br>
  ![Session create command Screenshot 1](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/SessionCreateCommand[1].png)

**Example 2** 
- A [group has been created](#creating-a-group-group-create) with group named *Friends* with Charlie and Mike. <br>
  Adds a new session named Class Gathering consisting of a group named *Friends* and Alice, on 16-04-2022.<br><br>
  `session /create /n Class Gathering /d 16-04-2022 /gid 1 /pl Alice` <br><br>
  ![Session create command Screenshot 2](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/SessionCreateCommand[2].png)
  <br>
  <br>

### Deleting a session: `session /delete`
Deletes an existing session so that you can remove sessions that you do not need.<br>

>Format: `session /delete /sid [SESSION_ID]`
>* `[SESSION_ID]` refers to the unique identifier of the session.
>    * The unique identifier for a session can be retrieved with [`session /list`](#listing-all-sessions-session-list) command.

<br>

> **💡 Note:**
>- A session with a unique identifier of `[SESSION_ID]` has to exist before it can be removed.
> 
> **⚠️Warning:**
> - This action is irreversible, once the command has been entered, the session would be immediately deleted.

<br>

**Example** 
- Removes an existing session with a unique identifier of 1.<br><br>
  `session /delete /sid 1` <br><br>
  ![Session delete command Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/SessionDeleteCommand.png)
  <br>
  <br>

### Editing a session: `session /edit`
Edits an existing session so that you can change details of a session.<br>

>Format: `session /edit /sid [SESSION_ID] [</n [SESSION_NAME]>] [</d [SESSION_DATE]>] [</pl [NAME1 NAME2...]>]`
>* `[SESSION_ID]` refers to the unique identifier of the session.
>    * The unique identifier for a session can be retrieved with [`session /list`](#listing-all-sessions-session-list) command.

<br>

> **💡 Notes**
>- A session with a unique identifier of `[SESSION_ID]` has to exist before it can be edited.
>- Each name in `[NAME1 NAME2 ...]` for a particular session should be unique.
>- There are 3 editable fields, session name, session date and the people involved in the session.
>  - At least 1 field has to be edited for the command to run.
>  - More than 1 field can be edited at a single run of the command.
>- When editing the people involved, existing participants must be included in the command.
>  - Example: If the session previously created had Alice and Bob with session ID of 1, 
>    and you wish to edit it to include charlie a valid edit command would be `session /edit /sid /pl Alice Bob Charlie`.
> 
> **⚠️Warning:**
> - This action is irreversible, once the command has been entered, the session would be edited.

<br>

Examples of usage:
- A [session has been created](#creating-a-session-session-create) with a unique identifier of 1,
  named Class Outing with Alice and Bob involved on 15-03-2022.

**Example 1** 
- Edits the session name to Class gathering and date to 16-03-2022. <br><br>
  `session /edit /sid 1 /n Class gathering /d 16-03-2022` <br><br>
  ![Session Edit command Screenshot 1](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/SessionEditCommand[1].png)

**Example 2** 
- Edits the session to include charlie. <br><br>
  `session /edit /sid 1 /pl Alice Bob Charlie` <br><br>
  ![Session Edit command Screenshot 2](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/SessionEditCommand[2].png)
  <br>
  <br>

### Viewing a session : `session /view`
Displays details about a session so that you can review the session.<br>

>Format: `session /view /sid [SESSION_ID]`
><br>
>* `[SESSION_ID]` refers to the unique identifier of the session.
>    * The unique identifier for a session can be retrieved with [`session /list`](#listing-all-sessions-session-list) command.

<br>

> **💡 Note:**
>- The session with a unique identifier of `[SESSION_ID]` has to exist before the activity can be viewed.

<br>

**Example**:
- Views a session with a unique identifier of 1.<br><br>
  `session /view /sid 1` <br><br>
  ![Session View command Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/SessionViewCommand.png)
  <br>     
  <br>

### Listing all sessions: `session /list`
Displays all existing sessions so that you can have an overview of previously created sessions. 
However, deleted sessions will not be listed.

> Format: `session /list`

<br>

**Example**
- Lists all existing sessions <br><br>
  `session /list`<br><br>
  ![Session list command Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/SessionListCommand.png)
  <br>
  <br>

<hr>

## _Activity Management_
> An activity represents a single group activity and stores its name, costs and the name of the payer.

<br>

### Creating an activity: `activity /create`
Creates an activity so that you can record the relevant details, and then assigns it to a session. <br>

There are two ways that you can create an activity:
1. Record the total cost to be split amongst everyone involved
2. Record each person's individual cost

> Format 1: `activity /create /sid [SESSION_ID] /n [ACTIVITY_NAME] /p [PERSON_PAID] /i [NAME1 NAME2 ...]
/co [TOTAL_COST] [</gst [GST_PERCENTAGE]>] [</sc [SERVICE_CHARGE]>]`
> 
> Format 2: `activity /create /sid [SESSION_ID] /n [ACTIVITY_NAME] /p [PERSON_PAID] /i [NAME1 NAME2 ...]
/cl [COST1 COST2 ...] [</gst [GST_PERCENTAGE]>] [</sc [SERVICE_CHARGE]>]`
>
>* `[SESSION_ID]` refers to the unique identifier of the session.
>    * The unique identifier for a session can be retrieved with [`session /list`](#listing-all-sessions-session-list) command.
>* `[ACTIVITY_NAME]` refers to the name of the activity.
>    * The activity name is **case-insensitive**.
>* `[PERSON_PAID]` refers to the person who paid for the activity.
>    * The person's name is **case-insensitive**.
>* `[NAME1 NAME2 ...]` refers to a list of persons involved in the activity.
>    * Each individual name is **case-insensitive**.
>* `[TOTAL_COST]` refers to the total cost of the activity.
>* `[COST1 COST2 ...]` refers to a list of costs respective to each person involved in the activity.
>    * Example: `/i Alice Bob /cl 10 20` means that Alice's portion cost $10 while Bob's portion cost $20.
>* `[GST_PERCENTAGE]` refers to the additional percentage gst that may be charged during your activity.
>* `[SERVICE_CHARGE]` refers to the additional percentage service charge that may be charged during your activity.

<br>

> **💡 Notes:**
>- A session with a unique identifier of `[SESSION_ID]` has to exist before an activity can be created and assigned to 
   it.
>- The `[ACTIVITY_NAME]` should be unique across all activities.
>- Each name in `[NAME1 NAME2 ...]` for the activity should be unique.
>- The names in `[PERSON_PAID]` and `[NAME1 NAME2 ...]` must also be associated with the session referenced by
   `[SESSION_ID]`.
>- The names in `[PERSON_PAID]` and `[NAME1 NAME2 ...]` must also only be a single word without whitespaces.
>   - Example: `Alice Tan` is not allowed.
>- The values in `[TOTAL_COST]` and `[COST1 COST2 ...]` can only have a maximum of 12 digits before
  and 2 digits after the decimal point, if any.
>- The values in `[GST_PERCENTAGE]` and `[SERVICE_CHARGE]` can only range from 0 to 100,
  with a maximum of 3 digits before and 2 after the decimal point, if any.

<br>

**Example 1**
- Adds a new activity to a session with a session unique identifier of 2 named Class Lunch. Alice paid a total of $10
for both Bob and herself which will be split equally between them later on.<br><br>
`activity /create /sid 2 /n Class Lunch /p Alice /i Alice Bob /co 10` <br><br>
![Activity create command [1] Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/ActivityCreateCommand[1].png)

**Example 2**
- Adds a new activity to a session with a session unique identifier of 2 named Class Lunch. Alice paid for both
  Bob and herself. Alice's meal cost $3.50 while Bob's meal cost $7.<br><br>
  `activity /create /sid 2 /n Class Lunch /p Alice /i Alice Bob /cl 3.5 7` <br><br>
  ![Activity create command [2] Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/ActivityCreateCommand[2].png)
  <br>
  <br>

### Deleting an activity: `activity /delete`
Deletes an existing activity from a particular session so that you can remove activities that you do not need.<br>

>Format: `activity /delete /sid [SESSION_ID] /aid [ACTIVITY_ID]`
>
>* `[SESSION_ID]` refers to the unique identifier of the session.
>    * The unique identifier for a session can be retrieved with [`session /list`](#listing-all-sessions-session-list) command.
>* `[ACTIVITY_ID]` refers to the unique identifier of the activity.
>    * The unique identifier for an activity can be retrieved with [`activity /list`](#listing-all-activities-in-a-session-activity-list) command.

<br>

> **💡 Notes:**
>- A session with a unique identifier of `[SESSION_ID]` has to exist before an activity in that session can be removed.
>- An activity with a unique identifier of `[ACTIVITY_ID]` has to exist before it can be removed.
>- A confirmation must be given before deletion takes place.
>
> **⚠️Warning:**
>- This action is irreversible, once the command has been entered, the activity would be immediately deleted.

<br>

**Example**:
- Removes an existing activity with a unique identifier of 1 from a session with a unique identifier of 2. <br><br>
  `activity /delete /sid 2 /aid 1` <br><br>
  ![Activity delete command Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/ActivityDeleteCommand.png)
  <br>
  <br>

### Editing an activity: `activity /edit`
Edits an existing activity so that you can change details of an activity.<br>

There are two ways that you can edit an activity:
1. Record the total cost to be split amongst everyone involved
2. Record each person's individual cost

> Format 1: `activity /edit /sid [SESSION_ID] /aid [ACTIVITY_ID] /n [ACTIVITY_NAME] /p [PAYER] /i
> [NAME1 NAME2...] /co [TOTAL_COST] [</gst [GST_PERCENTAGE]>] [</sc [SERVICE_CHARGE]>]`
> 
> Format 2: `activity /edit /sid [SESSION_ID] /aid [ACTIVITY_ID] /n [ACTIVITY_NAME] p [PAYER]
  /i [NAME1 NAME2...] /cl [COST1 COST2...] [</gst [GST_PERCENTAGE]>] [</sc [SERVICE_CHARGE]>]`
> 
>* `[SESSION_ID]` refers to the unique identifier of the session.
>    * The unique identifier for a session can be retrieved with [`session /list`](#listing-all-sessions-session-list) command.
>* `[ACTIVITY_ID]` refers to the unique identifier of the activity.
>    * The unique identifier for an activity can be retrieved with the [`activity /list`](#listing-all-activities-in-a-session-activity-list) command.
>* `[ACTIVITY_NAME]` refers to the name of the activity.
>    * The activity name is **case-insensitive**.
>* `[PERSON_PAID]` refers to the person who paid for the activity.
>    * The person's name is **case-insensitive**.
>* `[NAME1 NAME2 ...]` refers to a list of persons involved in the activity.
>    * Each individual name is **case-insensitive**.
>* `[TOTAL_COST]` refers to the total cost of the activity.
>* `[COST1 COST2 ...]` refers to a list of costs respective to each person involved in the activity.
>    * Example: `/i Alice Bob /cl 10 20` means that Alice's portion cost $10 while Bob's portion cost $20.
>* `[GST_PERCENTAGE]` refers to the additional percentage gst that may be charged during your activity.
>* `[SERVICE_CHARGE]` refers to the additional percentage service charge that may be charged during your activity.

<br>

> **💡 Notes**
>- A session with a unique identifier of `[SESSION_ID]` has to exist before an activity can be created and assigned to
     it.
>- An activity with a unique identifier of `[ACTIVITY_ID]` has to exist before it can be edited.
>- The `[ACTIVITY_NAME]` should be unique across all activities.
>- Each name in `[NAME1 NAME2 ...]` for the activity should be unique.
>- The names in `[PERSON_PAID]` and `[NAME1 NAME2 ...]` must also be associated with the session referenced by
   `[SESSION_ID]`.
>- The names in `[PERSON_PAID]` and `[NAME1 NAME2 ...]` must also only be a single word without whitespaces.
   >   - Example: `Alice Tan` is not allowed.
>- The values in `[TOTAL_COST]` and `[COST1 COST2 ...]` can only have a maximum of 12 digits before
   and 2 digits after the decimal point, if any.
>- The values in `[GST_PERCENTAGE]` and `[SERVICE_CHARGE]` can only range from 0 to 100,
   with a maximum of 3 digits before and 2 after the decimal point, if any.
>
> **⚠️Warnings:**
>- All fields must be supplied in the command, not just the ones you wish to edit.
>- This action is irreversible, once the command has been entered, the activity would be immediately edited.

<br>

**Example**
- An [activity has been created](#creating-an-activity-activity-create) with an `ACTIVITY_ID` of 1, part of 
  a session with a `SESSION_ID` of 1. It was named *Lunch* with Alice and Bob.<br>
  Edits the activity's name, payer, overall cost, GST percentage and service charge. List of participants remains
  unchanged.<br><br>
  `activity /edit /sid 1 /aid 1 /n Dinner /p Bob /i Alice Bob /co 30 /gst 7 /sc 10`<br><br>
  ![Activity edit command Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/ActivityEditCommand.png)
  <br>
  <br>

### Viewing an activity: `activity /view`
Displays details about an activity so that you can review the activity's details.<br>

> Format: `activity /view /sid [SESSION_ID] /aid [ACTIVITY_ID]`
>
>* `[SESSION_ID]` refers to the unique identifier of the session.
>    * The unique identifier for a session can be retrieved with [`session /list`](#listing-all-sessions-session-list) command.
>* `[ACTIVITY_ID]` refers to the unique identifier of the activity. 
>    * The unique identifier for an activity can be retrieved with [`activity /list`](#listing-all-activities-in-a-session-activity-list) command.

<br>

> **💡 Note:**
>- The session with a unique identifier of `[SESSION_ID]` and the activity with a unique identifier of `[ACTIVITY_ID]` have to exist before the activity can be viewed.

<br>

**Example**
- Views an activity with a unique identifier of 2 in a session with a session unique identifier of 2.<br><br>
  `activity /view /sid 2 /aid 2` <br><br>
  ![Activity view command Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/ActivityViewCommand.png)
  <br>     
  <br>

### Listing all activities in a session: `activity /list`
Displays all existing activities so that you can have an overview of previously created activities.
However, deleted activities will not be listed.

>Format: `activity /list /sid [SESSION_ID]`
>
>* `[SESSION_ID]` refers to the unique identifier of the session.
>    * The unique identifier for a session can be retrieved with [`session /list`](#listing-all-sessions-session-list) command.

<br>

> **💡 Note:**
>- A session with a unique identifier of `[SESSION_ID]` has to exist before its activities can be listed.

<br>

**Example**:
- Lists all activities in a session with a unique identifier of 2.<br><br>
  `activity /list /sid 2` <br><br>
  ![Activity list command Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/ActivityListCommand.png)
  <br>     
  <br>

<hr>

## _Transaction Management_

<br>

### Settling all transactions for a session: `session /summary`

Displays a transaction summary for a session and helps you calculate how much each person in the session
must pay and to whom they should pay for all debts to be resolved.<br>

> Format: `session /summary /sid [SESSION_ID]`
> * `[SESSION_ID]` refers to the unique identifier of the session.
>    * The unique identifier for a session can be retrieved with [`session /list`](#listing-all-sessions-session-list) command.

<br>

> **💡 Note:**
>- A session with a unique identifier of `[SESSION_ID]` has to exist before its summary can be generated.

<br>

**Example**
* Displays a session summary that summarises the [session that has been created](#creating-a-session-session-create)
  with session unique identifier of 1 to help Alice and Bob calculate what transactions they have to make to
  resolve all their debts to each other.<br><br>
  `session /summary /sid 1`<br><br>
  ![Session summary command Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/SessionSummaryCommand.png)
  <br>
  <br>


<hr>

## _Group Management_
> A group represents one or more individuals. It is used as a shortcut in several commands for identifying a group of individual persons.

<br>

### Creating a group: `group /create`
Creates a new group so that you do not to always enter the same persons' particulars every session.

> Format : `group /create /n [GROUP_NAME] /pl [NAME1 NAME2 ...]`
>
>* `[GROUP_NAME]` refers to the name of the group.
>    * The group name is **case-insensitive**.
>* `[NAME1 NAME2 ...]` refers to a list of persons involved in the activity.
>    * Each individual name is **case-insensitive**.

<br>

> **💡 Notes:**
>- The `[GROUP_NAME]` should be unique across all groups.
>- Each name in `[NAME1 NAME2 ...]` for the group should be unique.

<br>

**Example**:
- Adds a new group named Uni Friends, with Alice and Bob involved.<br><br>
  `group /create /n Uni Friends /pl Alice Bob` <br><br>
  ![Group create command Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/GroupCreateCommand.png)
  <br>
  <br>

### Deleting a group: `group /delete`
Deletes an existing group so that you can remove groups that you do not need.

> Format: `group /delete /gid [GROUP_ID]`
>
>* `[GROUP_ID]` refers to the unique identifier of the group.
>    * The unique identifier for a group can be retrieved with [`group /list`](#listing-all-groups-group-list) command.

<br>

> **💡 Note:**
>- A group with a unique identifier of `[GROUP_ID]` has to exist before it can be removed.
>
> **⚠️Warning:**
> - This action is irreversible, once the command has been entered, the group would be immediately deleted.

<br>

**Example**:
- Removes an existing group with a unique identifier of 1.<br><br>
  `group /delete /gid 1` <br><br>
  ![Group delete command Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/GroupDeleteCommand.png)
  <br>
  <br>

### Editing a group: `group /edit`
Edits an existing group so that you can change details of a group.<br>

>Format: `group /edit /gid [GROUP_ID] [</n [GORUP_NAME]>] [</pl [NAME1 NAME2...]>]`
>* `[GROUP_ID]` refers to the unique identifier of the group.
>    * The unique identifier for a gorup can be retrieved with [`group /list`](#listing-all-groups-group-list) command.

<br>

> **💡 Notes**
> - A group with a unique identifier of `[GROUP_ID]` has to exist before it can be edited.
> - Each name in `[NAME1 NAME2 ...]` for a particular group should be unique.
> - There are 2 editable fields, group name and the people involved in the session.
>
> **⚠️Warning:**
> - This action is irreversible, once the command has been entered, the group would be edited.

<br>

Examples of usage:
- A group with a unique identifier of 1 was previously created, and named Class Outing with Alice and Bob involved.

**Example 1**
- Edits the group name to Class gathering. <br><br>
  `group /edit /gid 1 /n Class gathering` <br><br>
  ![Group Edit command Screenshot 1](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/GroupEditCommand[1].png)

**Example 2**
- Edits the group to include charlie. <br><br>
  `group /edit /gid 1 /pl Alice Bob Charlie` <br><br>
  ![Group Edit command Screenshot 2](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/GroupEditCommand[2].png)
  <br>
  <br>

### Viewing a group: `group /view`
Displays details about a group so that you can review the group's details.<br>

>Format: `group /view /gid [GROUP_ID]`
>
>* `[GROUP_ID]` refers to the unique identifier of the group.
>    * The unique identifier for a group can be retrieved with [`group /list`](#listing-all-groups-group-list) command.

<br>

> **💡 Note:**
>- The group with a unique identifier of `[GROUP_ID]` has to exist before the group can be viewed.

<br>

**Example**:
- Views a group with a unique identifier of 1.<br><br>
  `group /view /gid 1` <br><br>
  ![Group view command Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/GroupViewCommand.png)
  <br>     
  <br>

### Listing all groups: `group /list`
Displays all existing groups so that you can have an overview of previously created groups.
However, deleted groups will not be listed.

>Format: `group /list`

<br>

**Example**:
- Lists all existing groups.<br><br>
  `group /list`<br><br>
  ![Group list command Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/GroupListCommand.png)
  <br>
  <br>

<hr>

## _Miscellaneous_

<br>

### Listing all available commands: `help`
Displays all available SplitLah commands and their syntax so that you can get assistance when using SplitLah.<br>
You can also refer to the [Command Summary](#command-summary) for a summary of all available commands in SplitLah.

> Format: `help`

<br>

### Exiting the application: `exit`
Quits the application.<br>

> Format: `exit`

<br>

<hr>
 
## FAQ

**Q**: Is data saved to the disk upon exit?

**A**: SplitLah 2.0 will create a save file in a folder named `data` that will be created in the same directory as
`SplitLah.jar`. All changes will be saved to the save file upon exit.

<br>

<hr>

## Command Summary

| Action                                  | Format                                                                                                                                                                                                                                  |
|-----------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Create a new session                    | Format: `session /create /n [SESSION_NAME] /d [DATE] /pl [NAME1 NAME2 …] [</gid [GROUP_ID]>]`<br><br> Example: `session /create /n Outing /d 15-03-2022 /pl Warren Ivan Roy`                                                            |
| Delete an existing session              | Format: `session /delete /sid [SESSION_ID]`<br><br>Example: `session /delete /sid 1`                                                                                                                                                    |
| Edit an existing session                | Format: `session /edit /sid [SESSION_ID] [</n [SESSION_NAME]>] [</d [SESSION_DATE]>] [</pl [NAME1 NAME2...]>]`<br><br> Example: `session /edit /sid 1 /n Class gathering /d 16-03-2022 /pl Alice Bob Charlie`                           |
| View an existing session                | Format: `session /view /sid [SESSION_ID]`<br><br>Example: `session /view /sid 1`                                                                                                                                                        |
| List all sessions                       | Format: `session /list`                                                                                                                                                                                                                 |
| Create activity (split costs evenly)    | Format: `activity /create /sid [SESSION_ID] /n [ACTIVITY_NAME] /p [PAYER] /i [NAME1 NAME2...] /co [TOTAL_COST]`<br><br>Example: `activity /create /sid 1 /n Lunch /p Warren /i Warren, Ivan, Roy /co 7.5`                               |
| Create activity (split costs manually)  | Format: `activity /create /sid [SESSION_ID] /n [ACTIVITY_NAME] /p [PAYER] /i [NAME1 NAME2...] /cl [COST1 COST2...]`<br><br>Example: `activity /create /sid 1 /n Lunch /p Warren /i Warren, Ivan, Roy /cl 1 1 5.5`                       |
| Delete an existing activity             | Format: `activity /delete /sid [SESSION_ID] /aid [ACTIVITY_ID]`<br><br>Example: `activity /delete /sid 2 /aid 1`                                                                                                                        |
| Edit an activity (split costs evenly)   | Format: `activity /edit /sid [SESSION_ID] /aid [ACTIVITY_ID] /n [ACTIVITY_NAME] /p [PAYER] /i [NAME1 NAME2...] /co [TOTAL_COST]`<br><br>Example: `activity /edit /sid 1 /aid 1 /n Lunch /p Warren /i Warren, Ivan, Roy /co 7.5`         |
| Edit an activity (split costs manually) | Format: `activity /edit /sid [SESSION_ID] /aid [ACTIVITY_ID] /n [ACTIVITY_NAME] /p [PAYER] /i [NAME1 NAME2...] /cl [COST1 COST2...]`<br><br>Example: `activity /edit /sid 1 /aid 1 /n Lunch /p Warren /i Warren, Ivan, Roy /cl 1 1 5.5` |
| View an existing activity               | Format: `activity /view /sid [SESSION_ID] /aid [ACTIVITY_ID]` <br><br>Example: `activity /view /sid 1 /aid 1`                                                                                                                           |
| List all activities                     | Format: `activity /list /sid [SESSION_ID]` <br><br>Example: `activity /list /sid 1`                                                                                                                                                     |
| Show session summary                    | Format: `session /summary /sid [SESSION_ID]`<br><br>Example: `session /summary /sid 1`                                                                                                                                                  |
| Create a new group                      | Format: `group /create /n [GROUP_NAME] /pl [NAME1 NAME2 …]`<br><br>Example: `group /create /n SplitLah /pl Roy Ivan Warren Saurav Tianle`                                                                                               |
| Delete an existing group                | Format: `group /delete /gid [GROUP_ID]`<br><br>Example: `group /delete /gid 1`                                                                                                                                                          |
| View an existing group                  | Format: `group /view /gid [GROUP_ID]`<br><br>Example: `group /view /gid 1`                                                                                                                                                              |
| List all groups                         | Format: `group /list`                                                                                                                                                                                                                   |
| List all available commands             | Format: `help`                                                                                                                                                                                                                          |
| Exit                                    | Format: `exit`                                                                                                                                                                                                                          |
