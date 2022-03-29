<p style="align:center"><img src="https://raw.githubusercontent.com/froststein/tp/master/docs/images/userguide/pngLogo.png"></p>
<br>

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

###Who SplitLah is for:
- People who go on group outings often and love to split bills.
- People who dislike manually calculating how much people owe across several activities.
- People who go on group outings where different people pay for different activities, making bill-splitting harder.
- People who want to manage outings with multiple social circles in one application.
- People who can type fast.

## Contents
* [How SplitLah works](#how-splitlah-works)
* [Quick Notes](#quick-notes)
* [Quick Start](#quick-start)
* [Features](#features)
  * Session Management
      * [Creating a session: `session /create`](#creating-a-session-session-create)
      * [Deleting a session: `session /delete`](#deleting-a-session-session-delete)
      * [Editing a session: `session /edit`](#editing-a-session-session-edit)
      * [Viewing a session: `session /view`](#viewing-a-session--session-view)
      * [Listing all sessions: `session /list`](#listing-all-sessions-session-list)
  * Activity Management
    * [Creating an activity: `activity /create`](#creating-an-activity-activity-create)
    * [Deleting an activity: `activity /delete`](#deleting-an-activity-activity-delete)
    * [Editing an activity: `activity /edit`](#editing-an-activity-activity-edit)
    * [Viewing an activity: `activity /view`](#viewing-an-activity-activity-view)
    * [Listing all activities in a session: `activity /list`](#listing-all-activities-in-a-session-activity-list)
  * Transaction Management
    * [Settling all transactions for a session: `session /summary`](#settling-all-transactions-for-a-session-session-summary)
  * Group Management
    * [Creating a group: `group /create`](#creating-a-group-group-create)
    * [Deleting a group: `group /delete`](#deleting-a-group-group-delete)
    * [Viewing a group: `group /view`](#viewing-a-group-group-view)
    * [Listing all groups: `group /list`](#listing-all-groups-group-list)
  * Miscellaneous
    * [Listing all available commands: `help`](#listing-all-available-commands-help)
    * [Exit](#exit)
* [FAQ](#faq)
* [Command Summary](#command-summary)

##How to use this user guide
- For each feature that SplitLah supports, we refer it as commands.
- We classify each command under 5 sections, [Session Management](#_session-management_), [Activity Management](#_activity-management_), 
[Transaction Management](#_transaction-management_), [Group Management](#_group-management_) and [Miscellaneous](#_miscellaneous_).
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

##How SplitLah works
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
> **⚠️```diff - Warning:```**
> - When using `/pl` and `/gid` delimiters together, if there is a duplicated name in `/pl` and 
> specified group with `/gid`. The duplicate name would be removed, storing only 1 instance of it.
>  - Example: Where the group specified by `/gid` consists of Alice and Bob and the arguments of `/pl` 
     includes Alice, only two names, Alice and Bob, would be saved.

Examples of usage:
1. Adds a new session named Class Outing with Alice and Bob involved on 15-03-2022.
   - `session /create /n Class Outing /d 15-03-2022 /pl Alice Bob` <br>
   ![Session create command Screenshot 1](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/SessionCreateCommand[1].png)
2. A [group](#creating-a-group-group-create) was previously created with group named *Friends* with Charlie and Mike. <br>
   Adds a new session named Class Gathering consisting of a group named *Friends* and Alice, on 16-04-2022.
   - `session /create /n Glass Gathering /d 16-04-2022 /gid 1 /pl Alice` <br>
   ![Session create command Screenshot 2](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/SessionCreateCommand[2].png)
<br>
<br>

### Deleting a session: `session /delete`

> Deletes an existing session.<br>
> A session represents a group outing spanning an arbitrary period of time containing one or more activities.

Format: `session /delete /sid [SESSION_ID]`

* `[SESSION_ID]` refers to the unique identifier of the session.
    * The unique identifier for a session can be retrieved with [`session /list`](#listing-all-sessions-session-list) command.

> **💡 Note:**
>- A session with a unique identifier of `[SESSION_ID]` has to exist before it can be removed.

> **⚠️Warning:**
> - This action is irreversible, once the command has been entered, the session would be immediately deleted.

Example of usage:
1. Remove an existing session with a unique identifier of 1.
   - `session /delete /sid 1` <br>
   ![Session delete command Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/SessionDeleteCommand.png)
<br>
<br>

### Editing a session: `session /edit`

> Edits an existing session. <br>
> A session represents a group outing spanning an arbitrary period of time containing one or more activities.

Format: `session /edit /sid [SESSION_ID] [</n [SESSION_NAME]>] [</d [SESSION_DATE]>] [</pl [NAME1 NAME2...]>]`

* `[SESSION_ID]` refers to the unique identifier of the session.
    * The unique identifier for a session can be retrieved with [`session /list`](#listing-all-sessions-session-list) command.

> **💡 Notes**
> - A session with a unique identifier of `[SESSION_ID]` has to exist before it can be edited.
> - Each name in `[NAME1 NAME2 ...]` for a particular session should be unique.
> - There are 3 editable fields, session name, session date and the people involved in the session.
> - When editing the people involved, existing participants must be included in the command.
>  - Example: If the session previously created had Alice and Bob with session ID of 1, 
>  and you wish to edit it to include charlie a valid edit command would be `session /edit /sid /pl Alice Bob Charlie`. 

> **⚠️Warning:**
> - This action is irreversible, once the command has been entered, the session would be edited.
    
Examples of usage:
- A session with a unique identifier of 1 was previously created named Class Outing with Alice and Bob involved on 15-03-2022.
1. Edit the session name to Class gathering and date to 16-03-2022.
    - `session /edit /sid 1 /n Class gathering /d 16-03-2022` <br>
      ![Session Edit command Screenshot 1](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/SessionEditCommand[1].png)
2. Edit the session to include charlie.
    - `session /edit /sid 1 /pl Alice Bob Charlie` <br>
      ![Session Edit command Screenshot 2](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/SessionEditCommand[2].png)


### Viewing a session : `session /view`

### Listing all sessions: `session /list`

> Displays all active sessions. Deleted sessions will not be listed.<br>
> A session represents a group outing spanning an arbitrary period of time containing one or more activities.

Format: `session /list`

Example of usage:

![Session list command Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/SessionListCommand.png)
<br>
<br>

<hr>

##_Activity Management_
### Creating an activity: `activity /create`

> Creates a new activity and assigns it to a session. <br>
> An activity represents a single group activity and stores its name, costs and the name of the payer.<br>
> A session represents a group outing spanning an arbitrary period of time containing one or more activities.

Format 1: `activity /create /sid [SESSION_ID] /n [ACTIVITY_NAME] /p [PERSON_PAID] /i [NAME1 NAME2 ...]
/co [TOTAL_COST] [</gst [GST_PERCENTAGE]>] [</sc [SERVICE_CHARGE]>]`

Format 2: `activity /create /sid [SESSION_ID] /n [ACTIVITY_NAME] /p [PERSON_PAID] /i [NAME1 NAME2 ...]
/cl [COST1 COST2 ...] [</gst [GST_PERCENTAGE]>] [</sc [SERVICE_CHARGE]>]`

* `[SESSION_ID]` refers to the unique identifier of the session.
    * The unique identifier for a session can be retrieved with [`session /list`](#listing-all-sessions-session-list) command.
* `[ACTIVITY_NAME]` refers to the name of the activity.
    * The activity name is **case-insensitive**.
* `[PERSON_PAID]` refers to the person who paid for the activity.
    * The person's name is **case-insensitive**.
* `[NAME1 NAME2 ...]` refers to a list of persons involved in the activity.
    * Each individual name is **case-insensitive**.
* `[TOTAL_COST]` refers to the total cost of the activity.
* `[COST1 COST2 ...]` refers to a list of costs respective to each person involved in the activity.
  * Example: `/i Alice Bob /cl 10 20` means that Alice's portion cost $10 while Bob's portion cost $20.

> **💡 Notes:**
>- A session with a unique identifier of `[SESSION_ID]` has to exist before an activity can be created and assigned to 
> it.
>- The `[ACTIVITY_NAME]` should be unique across all activities.
>- Each name in `[NAME1 NAME2 ...]` for the activity should be unique.
>- The names in `[PERSON_PAID]` and `[NAME1 NAME2 ...]` must also be associated with the session referenced by
   `[SESSION_ID]`.
>- The names in `[PERSON_PAID]` and `[NAME1 NAME2 ...]` must also only be a single word without whitespaces.
   > <br>Example: `Alice Tan` is not allowed.
>- The values that follow the delimiters `/co` and `/cl` can only have a maximum of 12 digits before
   and 2 digits after the decimal point, if any.


Examples of usage:
1. Adds a new activity to a session with a session unique identifier of 2 named Class Lunch. Alice paid a total of $10
   for both Bob and herself which will be split equally between them later on.
   - `activity /create /sid 2 /n Class Lunch /p Alice /i Alice Bob /co 10` <br>
   ![Activity create command [1] Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/ActivityCreateCommand[1].png)
2. Adds a new activity to a session with a session unique identifier of 2 named Class Lunch. Alice paid for both
   Bob and herself. Alice's meal cost $3.50 while Bob's meal cost $7.
   - `activity /create /sid 2 /n Class Lunch /p Alice /i Alice Bob /cl 3.5 7` <br>
   ![Activity create command [2] Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/ActivityCreateCommand[2].png)
<br>
<br>

### Deleting an activity: `activity /delete`

> Deletes an existing activity from a particular session.<br>
> An activity represents a single group activity and stores its name, costs and the name of the payer.<br>
> A session represents a group outing spanning an arbitrary period of time containing one or more activities.

Format: `activity /delete /sid [SESSION_ID] /aid [ACTIVITY_ID]`

* `[SESSION_ID]` refers to the unique identifier of the session.
    * The unique identifier for a session can be retrieved with [`session /list`](#listing-all-sessions-session-list) command.
* `[ACTIVITY_ID]` refers to the unique identifier of the activity.
    * The unique identifier for an activity can be retrieved with [`activity /list`](#listing-all-activities-in-a-session-activity-list) command.

> **💡 Notes:**
>- A session with a unique identifier of `[SESSION_ID]` has to exist before an activity in that session can be removed.
>- An activity with a unique identifier of `[ACTIVITY_ID]` has to exist before it can be removed.
>- A confirmation must be given before deletion takes place.

> **⚠️Warning:**
> - This action is irreversible, once the command has been entered, the activity would be immediately deleted.

Example of usage:
1. Remove an existing activity with a unique identifier of 1 from a session with a unique <br> identifier of 2.
   - `activity /delete /sid 2 /aid 1` <br>
   ![Activity delete command Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/ActivityDeleteCommand.png)
<br>
<br>

### Editing an activity: `activity /edit`

### Viewing an activity: `activity /view`

> Display details about an activity.<br>
> An activity represents a single group activity and stores its name, costs and the name of the payer.<br>

Format: `activity /view /sid [SESSION_ID] /aid [ACTIVITY_ID]`


* `[SESSION_ID]` refers to the unique identifier of the session.
    * The unique identifier for a session can be retrieved with [`session /list`](#listing-all-sessions-session-list) command.
* `[ACTIVITY_ID]` refers to the unique identifier of the activity. 
    * The unique identifier for an activity can be retrieved with [`activity /list`](#listing-all-activities-in-a-session-activity-list) command.

> **💡 Note:**
>- The session with a unique identifier of `[SESSION_ID]` and the activity with a unique identifier of `[ACTIVITY_ID]` have to exist before the activity can be viewed.


Example of usage:
1. Views an activity with a unique identifier of 2 in a session with a session unique identifier of 2.
    - `activity /view /sid 2 /aid 2` <br>
      ![Activity view command Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/ActivityViewCommand.png)
<br>     
<br>

### Listing all activities in a session: `activity /list`

> Displays all existing activities in a particular session.<br>
> An activity represents a single group activity and stores its name, costs and the name of the payer.<br>
> A session represents a group outing spanning an arbitrary period of time containing one or more activities.

Format: `activity /list /sid [SESSION_ID]`
* `[SESSION_ID]` refers to the unique identifier of the session.
    * The unique identifier for a session can be retrieved with [`session /list`](#listing-all-sessions-session-list) command.

> **💡 Note:**
>- A session with a unique identifier of `[SESSION_ID]` has to exist before its activities can be listed.


Example of usage:
1. Lists all activities in a session with a unique identifier of 2.
    - `activity /list /sid 2` <br>
      ![Activity list command Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/ActivityListCommand.png)
<br>     
<br>

<hr>

## _Transaction Management_
### Settling all transactions for a session: `session /summary`

> Displays a summary of a session that details how much each person must pay and to whom for all debts to be resolved.<br>
> A session represents a group outing spanning an arbitrary period of time containing one or more activities.

Format: `session /summary /sid [SESSION_ID]`

* `[SESSION_ID]` refers to the unique identifier of the session.
    * The unique identifier for a session can be retrieved with [`session /list`](#listing-all-sessions-session-list) command.

> **💡 Note:**
>- A session with a unique identifier of `[SESSION_ID]` has to exist before its summary can be generated.

Example of usage:
1. A [session](#creating-a-session-session-create) was previously created with session named Class Outing with Alice 
   and Bob involved on 15-03-2022.
2. An [activity](#creating-an-activity-activity-create) was created with activity named Class Lunch, where Alice paid for both
   Bob and herself with a total cost of $10.
3. Get a session summary for an active session with a session unique identifier of 2.
   - `session /summary /sid 1` <br>
   ![Session summary command Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/SessionSummaryCommand.png)
<br>
<br>


<hr>

## _Group Management_
### Creating a group: `group /create`

> Creates a new group. <br>
> A group represents one or more individuals. It is used as a shortcut in several commands for identifying a group of individual persons.<br>

Format : `group /create /n [GROUP_NAME] /pl [NAME1 NAME2 ...]`

* `[GROUP_NAME]` refers to the name of the group.
    * The group name is **case-insensitive**.
* `[NAME1 NAME2 ...]` refers to a list of persons involved in the activity.
    * Each individual name is **case-insensitive**.

> **💡 Notes:**
>- The `[GROUP_NAME]` should be unique across all groups.
>- Each name in `[NAME1 NAME2 ...]` for the group should be unique.


Example of usage:
1. Adds a new group named Uni Friends, with Alice and Bob involved.
    - `group /create /n Uni Friends /pl Alice Bob` <br>
      ![Group create command Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/GroupCreateCommand.png)
<br>
<br>

### Deleting a group: `group /delete`
> Deletes a existing group.<br>
> A group represents one or more individuals. It is used as a shortcut in several commands for identifying a group of individual persons.<br>

Format: `group /delete /gid [GROUP_ID]`

* `[GROUP_ID]` refers to the unique identifier of the group.
    * The unique identifier for a group can be retrieved with [`group /list`](#listing-all-groups-group-list) command.

> **💡 Note:**
>- A group with a unique identifier of `[GROUP_ID]` has to exist before it can be removed.

> **⚠️Warning:**
> - This action is irreversible, once the command has been entered, the group would be immediately deleted.

Example of usage:
1. Remove an existing group with a unique identifier of 1.
    - `group /delete /gid 1` <br>
      ![Group delete command Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/GroupDeleteCommand.png)
<br>
<br>

### Editing a group: `group /edit`

<br>
<br>

### Viewing a group: `group /view`

> Display details about a group.<br>
> A group represents one or more individuals.
> It is used as a shortcut in several commands for identifying a group of individual persons.

Format: `group /view /gid [GROUP_ID]`

* `[GROUP_ID]` refers to the unique identifier of the group.
    * The unique identifier for a group can be retrieved with [`group /list`](#listing-all-groups-group-list) command.

> **💡 Note:**
>- The group with a unique identifier of `[GROUP_ID]` has to exist before the group can be viewed.

Example of usage:
1. Views a group with a unique identifier of 1.
    - `group /view /gid 1` <br>
    ![Group view command Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/GroupViewCommand.png)
<br>     
<br>

### Listing all groups: `group /list`

> Displays all active groups. Deleted groups will not be listed.<br>
> A group represents one or more individuals.
> It is used as a shortcut in several commands for identifying a group of individual persons.

Format: `group /list`

Example of usage:

![Group list command Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/GroupListCommand.png)
<br>
<br>

<hr>

##_Miscellaneous_
### Listing all available commands: `help`
> Displays all available SplitLah commands and their syntax.

Format: `help`

Example of usage:

![Help command Screenshot](https://raw.githubusercontent.com/AY2122s2-cs2113t-t10-1/tp/master/docs/images/userguide/HelpCommand.png)
<br>
<br>

### Exit
> Exits the application.

Format: `exit`
 
## FAQ

**Q**: Is data saved to the disk upon exit?

**A**: SplitLah 1.0 does not currently support saving data to the disk.

## Command Summary

| Action                                   | Format                                                                                                                                                                                                             |
|------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Create a new session                     | Format: `session /create /n [SESSION_NAME] /d [DATE] /pl [NAME1 NAME2 …] [</gid [GROUP_ID]>]`<br><br> Example: `session /create /n Outing /d 15-03-2022 /pl Warren Ivan Roy`                                       |
| Delete an existing session               | Format: `session /delete /sid [SESSION_ID]`<br><br>Example: `session /delete /sid 1`                                                                                                                               |
| Edit an existing session                 | Format: ` `                                                                                                                                                                                                        |
| View an existing session                 | Format: `session /view /sid [SESSION_ID]`<br><br>Example: `session /view /sid 1`                                                                                                                                   |
| List all sessions                        | Format: `session /list`                                                                                                                                                                                            |
| Create activity and split costs evenly   | Format: `activity /create /sid [SESSION_ID] /n [ACTIVITY_NAME] /p [PAYER] /i [NAME1 NAME2 …] /co [TOTAL_COST]`<br><br>Example: `activity /create /sid 1 /n Lunch /p Warren /i Warren, Ivan, Roy /co 7.5`           |
| Create activity and split costs manually | Format: `activity /create /sid [SESSION_ID] /n [ACTIVITY_NAME] /p [PAYER] /i [NAME1 NAME2 …] /cl [COST1] [COST2]...`<br><br>Example: `activity /create /sid 1 /n Lunch /p Warren /i Warren, Ivan, Roy /cl 1 1 5.5` |
| Delete an existing activity              | Format: `activity /delete /sid [SESSION_ID] /aid [ACTIVITY_ID]`<br><br>Example: `activity /delete /sid 2 /aid 1`                                                                                                   |
| Edit an activity (split costs evenly)    | Format: ` `                                                                                                                                                                                                        |
| Edit an activity (split costs manually)  | Format: ` `                                                                                                                                                                                                        |
| View an existing activity                | Format: `activity /view /sid [SESSION_ID] /aid [ACTIVITY_ID]` <br><br>Example: `activity /view /sid 1 /aid 1`                                                                                                      |
| List all activities                      | Format: `activity /list /sid [SESSION_ID]` <br><br>Example: `activity /list /sid 1`                                                                                                                                |
| Show session summary                     | Format: `session /summary /sid [SESSION_ID]`<br><br>Example: `session /summary /sid 1`                                                                                                                             |
| Create a new group                       | Format: `group /create /n [GROUP_NAME] /pl [NAME1 NAME2 …]`<br><br>Example: `group /create /n SplitLah /pl Roy Ivan Warren Saurav Tianle`                                                                          |
| Delete an existing group                 | Format: `group /delete /gid [GROUP_ID]`<br><br>Example: `group /delete /gid 1`                                                                                                                                     |
| View an existing group                   | Format: `group /view /gid [GROUP_ID]`<br><br>Example: `group /view /gid 1`                                                                                                                                         |
| List all groups                          | Format: `group /list`                                                                                                                                                                                              |
| List all available commands              | Format: `help`                                                                                                                                                                                                     |
| Exit                                     | Format: `exit`                                                                                                                                                                                                     |
