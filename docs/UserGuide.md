# User Guide

## Introduction

AlloNUS (“All On Us”) is an all-in-one platform
for tracking your classes, expenses, and personal contacts
optimized for use via a Command Line Interface (CLI).
If you can type fast, AlloNUS can get your schedule, expenses,
and contact management tasks done faster than traditional GUI apps.

##Table of Contents
* [Quick Start](#quick-start)
* [Features](#features)
* [Main Menu Features](#main-menu-features)
  * [Navigating from menu to section of interest](#navigating-from-menu-to-section-of-interest-goto)
  * [Going back to menu](#going-back-to-menu-menu)
  * [Main Menu help](#getting-guidance-on-the-usage-of-the-application-help)
  * [Exiting the app](#exiting-the-application-exit)
* [Expense Tracker Features](#expense-tracker-features)
  * [Adding an expense](#adding-an-expense-add)
  * [List out expenses](#list-out-all-expenses-list)
  * [Delete an expense](#delete-an-expense-rm)
  * [Edit an expense](#editing-an-expense-edit)
  * [Finding an expense](#finding-an-expense-find)
* [Study Manager Features](#study-manager-features)
  * [Adding a module](#adding-a-module-add)
  * [List out modules](#listing-modules-list)
  * [Deleting a module](#deleting-a-module-rm)
  * [Editing a module](#editing-a-module-edit)
  * [Finding a module](#finding-a-module-find)
  * [Reading from .ics file](#reading-from-ics-file-read-ics)
* [Contacts Tracker Features](#contacts-tracker-features)
  * [Adding a contact](#adding-a-contact-add)
  * [List out contacts](#list-all-contacts-list)
  * [Deleting a contact](#deleting-a-contact-rm)
  * [Editing a contact](#editing-a-contact-edit)
  * [Finding a contact](#finding-a-contact-find)
* [Load and Save](#load-and-save)
* [FAQ](#faq)
* [Command Summary](#command-summary)

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `AlloNUS` from [here](https://github.com/AY2122S2-CS2113-F10-4/tp/releases).

## Features 

{Give detailed description of each feature}

### Main Menu Features
#### Navigating from menu to section of interest: `goto`
Allows access to subsections of the application, namely, to the expense tracker, contacts manager and task manager.
This command can be used from other sections as well to directly go to another section. 

Format: `goto SECTION`

Note: `SECTION` here refers to the names of the sections of the application and there are only 3 valid choices:
* `Expense_Tracker`
* `Study_Manager`
* `Contacts_Manager`

**Examples of usage:**
* `goto Expense_Tracker`
* `goto Study_Manager`
* `goto Contacts_Manager`

**Expected outcome:**
For expense tracker,
```
---------------------------------------------------
Welcome to Expense Tracker
---------------------------------------------------
```

For study manager,
```
Welcome to Modules Tracker, where you can track all your classes.
```

For contacts manager,
```
---------------------------------------------------
Welcome to Contacts Manager
---------------------------------------------------
```

#### Going back to menu: `menu`
Allows navigation back to the menu section of the application.

Format: `menu`

**Example of usage:**
user types in the `menu` command from another section which results in:
```
---------------------------------------------------
Welcome back to the main menu
---------------------------------------------------
---------------------------------------------------
MENU:
---------------------------------------------------
```

#### Getting guidance on the usage of the application: `help`
Displays guidance for menu features.

Format: `help`

**Example of usage:**
User enters `help` when in main menu which results in:
```
---------------------------------------------------
Going from menu to section of interest:

Allows access to subsections of the application, namely, to the expense tracker, contacts manager and task manager.

Format: goto SECTION

Examples:

- goto Expense_Tracker
- goto Study_Manager
- goto Contacts_Manager


Going back to menu:

Allows navigation back to the menu section of the application. 

Format: menu

Example:

- menu


Getting guidance on the usage of the application:

Displays user guide.

Format: help

Example:

- help


Exiting the application:

Terminates the application.

Format: exit

Example:

- exit
---------------------------------------------------
---------------------------------------------------
MENU:
---------------------------------------------------
```

#### Exiting the application: `exit`
Terminates the application. Can only be called from the menu. 

Format: `exit`

**Example of usage:**
User enters `exit` which results in:
```
---------------------------------------------------
Goodbye! Hope to see you again...
---------------------------------------------------
```


### Expense Tracker Features

#### Adding an expense: `add`
Adds a new expense to the list of expenses. The keyword `add` is used followed by the date,
amount, category and remarks of a given expense, using the delimiters of `d/` , `a/`,  `c/` and `r/`
respectively.
- The DATE field must be in the format of YYYY-MM-DD. All other formats would not be accepted.
- The AMOUNT field must be a valid number (integer/float) and must be non-negative.
- '/' must not be the last character of a given field, even with trailing spaces.
- The usage of '/' as a free text must be enclosed with white spaces, even as the first character.
  - Example: `r/Buffet / Alacarte meal`

Format: `add d/DATE a/AMOUNT c/CATEGORY r/REMARKS`

| Parameter         | Description                            |
|-------------------|----------------------------------------|
| ```<DATE> ```     | Date of expense(YYYY-MM-DD)            |
| ```<AMOUNT> ```   | Amount spent on the expense in $       |
| ```<CATEGORY> ``` | Free-text on type of expense           |
| ```<REMARKS> ```  | Any additional information (non-empty) |

**Example of usage:**

`add d/2022-03-15 a/9.50 c/Movie r/Spiderman: No Way Home`

**Expected Outcome:**
```
---------------------------------------------------
Added 2022-03-15 | $9.50 | Movie | Spiderman: No Way Home
---------------------------------------------------
```
#### List out all expenses: `list`
Lists out all currently tracked expenses in a list using the keyword `list`. Each entry shows
its current index in the list, followed by the date, amount, category and the remarks for each expense 
made.

Format: `list`

**Example of usage:**
`list`

**Expected outcome:**
```
---------------------------------------------------
Here are the expenses you have made so far:
 1. 2022-03-15 | $9.50 | Movie | Spiderman: No Way Home
 2. 2022-03-16 | $4.30 | Food | Chicken rice for lunch
 
 ---------------------------------------------------
```

#### Delete an expense: `rm`
Deletes a specific expense record that currently exists in the list using its index. Users may choose
to `list` out the expenses first before deleting to verify its index. After deleting an expense
record, it will be shown before it is removed from the list.
- ```<INDEX>``` refers to the actual number shown on the left of each expense record when `list` is used.

Format: 
`rm INDEX`

| Parameter         | Description                               |
|-------------------|-------------------------------------------|
| ```<INDEX> ```    | Index of the expense record to be deleted |

**Example of usage:**

`list`
```
---------------------------------------------------
Here are the expenses you have made so far:
 1. 2022-03-15 | $9.50 | Movie | Spiderman: No Way Home
 2. 2022-03-14 | $4.30 | Food | Chicken rice for lunch
 
 ---------------------------------------------------
```
`rm 2`

**Expected outcome:**
```
---------------------------------------------------
Deleted entry: 2022-03-14 | $4.30 | Food | Chicken rice for lunch
---------------------------------------------------
```
#### Editing an expense: `edit`
Edits an existing expense record in the list of expenses. Users may choose to `list` out the expenses first 
before editing to verify its index. After the record is extracted, users need to choose
which field to edit in that record. After editing an expense
record, the newly edited record will be shown to the user.
- New values entered by the user follow the same restrictions as `add` function above.

Format: 
`edit INDEX`

`[FIELD] [NEW VALUE]`

| Parameter         | Description                                                                                           |
|-------------------|-------------------------------------------------------------------------------------------------------|
| ```<FIELD> ```    | The field of a record to be edited.<br/>Valid fields: `<DATE>`, `<AMOUNT>`, `<CATEGORY>`, `<REMARKS>` |
| ```<NEW VALUE>``` | The new value to be inserted at the specified field. <br>It must comply with the constraints specified by `add` section above.|

**Example of usage:**

`edit 1`

`category movie`

**Expected Outcome:**
```
---------------------------------------------------
Here is the expense record you have chosen to edit:
2022-03-22 | $18.00 | Category | This is a remark
---------------------------------------------------
---------------------------------------------------
Which field would you like to edit? Enter [field] [newValue] or enter 'DONE' when you have finished editing:
---------------------------------------------------
< category Movie
---------------------------------------------------
New category value set!
---------------------------------------------------
< done
---------------------------------------------------
Editing complete!
---------------------------------------------------
---------------------------------------------------
Here is the newly edited expense record:
2022-03-22 | $20.00 | Movie | This is a remark
---------------------------------------------------
```

#### Finding an expense: `find`
Looks for a specific expense record by using a user-specified keyword.
- Only the `<CATEGORY>`, `<DATE>` and `<REMARKS>` fields will be considered when looking for an expense record.
- The keyword is case-insensitive.
- Partial matches on the keyword are also returned.

Format:
`find KEYWORD`

| Parameter        | Description                          |
|------------------|--------------------------------------|
| ```<KEYWORD> ``` | The word to look for in each expense |

**Example of usage:**

`find fast`

**Expected outcome:**
```
---------------------------------------------------
Here are the matching expense records:
2022-03-22 | $9.50 | Movie | Fast and Furious
---------------------------------------------------
```

### Study Manager Features

#### Adding a module: `add`
Adds a new module to the list of modules.

Format: `add m/<MODULE_CODE> c/<CATEGORY> d/<DAY> t/<TIME>`

| Parameter            | Description                          | Constraints |
|----------------------|--------------------------------------| --------------------------------------|
| ```<MODULE_CODE> ``` | Code for the module                  | Accepted inputs are alphanumeric parameters |
| ```<CATEGORY> ```    | Category of the module| Accepts shorthand notations `lec`,`tut`,`lab` and `exam` |
| ```<DAY> ```         | Class day of the week for the module<br><br>OR<br><br>Date of a one-off event like exam or a non recurring class| Accepted inputs are valid days of the week <br> E.g. `thursday` or `Thursday`<br><br>OR<br><br>Alternatively a valid date of type DD-MM-YYYY can be specified<br>E.g. `12-12-2022`   |
| ```<TIME> ```        | Class timeslot for the module |  Accepted inputs are timeslots with a start and end time of the form `HH:MMam/pm - HH:MMam/pm`  <br>E.g. `2:00pm - 4:00pm` |

Example of usage:

    add m/CS2113 c/lec d/Friday t/4:00pm-6:00pm
    add m/CG2271 c/tut d/Thursday t/3:00pm-4:00pm
    add m/CS2113 c/exam d/10-04-2021 t/10:00am-12:00pm

Expected outcome:

    Okay, I have added a new module to the schedule
    [Module] CS2113 Lecture: Friday, 4:00pm-6:00pm

A new module has been added to the schedule with the specified module code, category, day and time.

#### Listing modules: `list`
Lists existing modules in the schedule.

Format: `list`

Example of usage: 

    list

Expected outcome:

    Here are the modules in your schedule:
    1: [Module] CS2113 Lecture: Friday, 4:00pm-6:00pm
    2: [Module] CS3244 Tutorial: Monday, 1:00pm-2:00pm

#### Deleting a module: `rm`
Deletes the specified module from the schedule.

Format: `rm <index>`

| Parameter      | Description                       |
|----------------|-----------------------------------|
| ```<index> ``` | Index of the module to be deleted |


Example of usage:

    rm 1

Expected outcome:

    Noted I have removed this module from your schedule:
    [Module] CS2113 Lecture: Friday, 4:00pm-6:00pm

#### Editing a module: `edit`
Edits an existing module in the schedule. The user can specify a module parameter to edit individually. 
Once all edits are finished the user enters done to exit the edit mode.

Format: `edit <index>`

`m/<MODULE_CODE>`

`c/<CATEGORY>`

`d/<DAY>`

`t/<TIME>`

`done`

| Parameter      | Description                       |
|----------------|-----------------------------------|
| ```<index> ``` | Index of the module to be edited |
| ```<MODULE_CODE> ``` | New code for the module                  |
| ```<CATEGORY> ```    | New category of the module               |
| ```<DAY> ```         | New class day of the week for the module |
| ```<TIME> ```        | New class timing for the module          |

Example of usage:

    edit 1
    m/CS3244
    done

Expected outcome:

    edit 1
    Here is the module that you have chosen to edit:
    [Module] CS2113 Lecture: Friday, 4:00pm-6:00pm
    Choose the part that you would like to edit:
    m/CS3244
    Here are the changes so far. You can edit more module parameters or you can enter 'done' to stop editing!
    [Module] CS3244 Lecture: Friday, 4:00pm-6:00pm
    done
    Your Module was successfully edited! Here are the changes:
    [Module] CS3244 Lecture: Friday, 4:00pm-6:00pm
    Exiting the edit mode

The existing module has been edited to change the module code from CS2113 to CS3244.

#### Finding a module: `find`

Format: find `<query>`

| Parameter      | Description                       |
|----------------|-----------------------------------|
| ```<query> ``` | Search query to find module |

Example of usage:

    find CS
    find Wednesday
    find 2pm


Expected outcome:

    list
    Here are the modules in your schedule:
    1: [Module] CS2113 Lecture: Thursday, 2:00pm-4:00pm
    2: [Module] EE4204 Lecture: Wednesday, 2:00pm-4:00pm
    find CS
    Here are the matching modules in your list:
    1: [Module] CS2113 Lecture: Thursday, 2:00pm-4:00pm
    find Wednesday
    Here are the matching modules in your list:
    1: [Module] EE4204 Lecture: Wednesday, 2:00pm-4:00pm
    find 2pm
    Here are the matching modules in your list:
    1: [Module] CS2113 Lecture: Thursday, 2:00pm-4:00pm
    2: [Module] EE4204 Lecture: Wednesday, 2:00pm-4:00pm

#### Reading from .ics file: `read ics`
Creates a list of your modules by reading from .ics calendar file that can be downloaded from nusmods.com.

This is an efficient alternative to add modules function where you can only add one module at a time.

Prerequisites:
1. Download the .ics file of your academic schedule from nusmods.com.
2. Place the unedited .ics file in the same folder as your AllOnUs.jar file.

Format: `read ics`

Example of usage:

    read ics
    nusmods_calendar.ics


Expected outcome:

    read ics
    Please enter the name of your .ics file from nusmods:
    nusmods_calendar.ics
    
    I have found these modules from your ics file:

    1: [Module] EG2401A Lecture: Wednesday, 6:00 pm-8:00 pm
    2: [Module] EG2401A Tutorial: Friday, 9:00 am-10:00 am
    3: [Module] CS2113 Lecture: Friday, 4:00 pm-6:00 pm
    4: [Module] CS2113 Tutorial: Friday, 10:00 am-11:00 am
    5: [Module] CS2113 Exam: 05-05-2022, 1:00 pm-3:00 pm
    6: [Module] CG2271 Tutorial: Wednesday, 4:00 pm-5:00 pm
    7: [Module] CG2271 Lecture: Wednesday, 10:00 am-12:00 pm
    8: [Module] CG2271 Laboratory: Friday, 2:00 pm-4:00 pm
    9: [Module] CG2271 Exam: 28-04-2022, 9:00 am-11:00 am
    10: [Module] CS3244 Lecture: Thursday, 12:00 pm-2:00 pm
    11: [Module] CS3244 Lecture: Monday, 2:00 pm-3:00 pm
    12: [Module] CS3244 Tutorial: Monday, 5:00 pm-6:00 pm
    13: [Module] CS3244 Exam: 23-04-2022, 9:00 am-11:00 am
    14: [Module] EE4204 Lecture: Monday, 10:00 am-12:00 pm
    15: [Module] EE4204 Tutorial: Wednesday, 1:00 pm-2:00 pm
    16: [Module] EE4204 Exam: 29-04-2022, 9:00 am-11:00 am
    
    I have added these to your existing schedule!
    Exiting read ics mode

The .ics file from nusmods.com has been parsed to get the relevant module details and has automatically added them to your module list.
You can now perform all other StudyManager functions on this list as per normal.

### Contacts Tracker Features

#### Adding a contact: `add`
Adds a new contact to the list of contacts.

Format: `add n/NAME f/FACULTY e/EMAIL d/DESCRIPTION`

* Use the delimiters `n/`, `f/`, `e/`, `d/` to indicate the contact's name, faculty, email, and description respectively
* You may indicate the fields in any order

Example of usage:

`add n/Jane Doe f/SoC e/e0334987@u.nus.edu d/group mate for CS4215`

Expected outcome:

```
Got it. I've added this contact:
  Name: Jane Doe, Faculty: SoC, Email: e0334987@u.nus.edu, Description: group mate for CS4215
Now you have N contacts in the list.
```
Where `N` depends on the length of the current contacts list.

#### List all contacts: `list`
Lists all contact information.

Format: `list`

Example of usage:

`list`

Expected outcome:

```
Here are the contacts in your list:
 1. Name: Jane Doe, Faculty: SoC, Email: e0334987@u.nus.edu, Description: group mate for CS4215
 2. Name: Lucy, Faculty: SoC, Email: email@u.nus.edu, Description: group mate from cs2113

```

#### Deleting a contact: `rm`
Delete a contact from the current list of contacts.

Format: `rm INDEX`

* You can use the `list` command to see what index a contact corresponds to
* `INDEX` must be a valid index number within the list, e.g.
  * `rm 0` uses an invalid index, as the contacts list starts from 1
  * `rm 7` uses an invalid index _if_ the contacts list has less than 7 entries
  * `rm not a number` uses an invalid index, as `not a number` isn't a number

Example of usage:

`rm 2`

Expected outcome:

```
Noted. I've removed this contact:
  Name: Lucy, Faculty: SoC, Email: email@u.nus.edu, Description: group mate from cs2113
Now you have N contacts in the list.
```
Where `N` depends on the length of the current contacts list.

#### Editing a contact: `edit`
Edits an existing contact from the current list of contacts. 

Format: `edit INDEX [n/NAME] [f/FACULTY] [e/EMAIL] [d/DESCRIPTION]`

* You can use the `list` command to see what index a contact corresponds to
* `INDEX` must be a valid index number within the list
* You can edit multiple fields at once, though it is optional
  * However, you must edit at least one field at a time
  * This means that the Contacts Manager will prompt you for a field 
if you only enter `edit INDEX` without any fields supplied

Example of usage:

`edit 1 f/FASS n/Jane Not Doe`

Expected outcome:

```
Okay, I've updated the information of this contact:
  Name: Jane Not Doe, Faculty: FASS, Email: e0334987@u.nus.edu, Description: group mate for CS4215
```

#### Finding a contact: `find`
Finds an existing contact from the current list of contacts. 
Only the Name field will be considered when looking for a contact record.

Format: `find KEYWORD`

* The keyword is case-sensitive
* Partial matches on the keyword are also returned
* You can only search for one keyword at a time

Example of usage:

`find Doe`

Expected outcome:

```
Here are the matching contacts in your list:
 1. Name: Jane Not Doe, Faculty: FASS, Email: e0334987@u.nus.edu, Description: group mate for CS4215
```

### Load and Save

Loading and saving is done automatically and the user does not need to worry about manually saving or loading
data. However, do not tamper with the load and save files. 

Furthermore, exit the application properly using the `exit` command from menu for guaranteed correctness of 
the program and this load and save feature. 

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: After you exit the program, your application data will be automatically stored in a text file locally called 
"allonusData.txt". 
You can transfer your data to another computer by copying this text file and placing
it in the same directory as the application in the new computer. 
When the application runs, it will automatically load the saved data in the text file. 
Please ensure the version of both applications are the same.


## Command Summary

| Action                  | Format, Examples                                                                                                            |
|-------------------------|-----------------------------------------------------------------------------------------------------------------------------|
| Add (Expense)           | `add d/DATE a/AMOUNT c/CATEGORY r/REMARK` <br> E.g. `add d/2022-04-03 a/500 c/Food r/At Supper Stretch`                     |
| Add (Module)            | `add m/MODULE_CODE c/CATEGORY d/DAY t/TIME` <br> E.g. `add m/CS2113 c/lec d/Friday t/4pm-6pm`                               |
| Add (Contact)           | `add n/NAME f/FACULTY e/EMAIL d/DESCRIPTION` <br> E.g., `add n/Jane Doe f/SoC e/e0334987@u.nus.edu d/group mate for CS4215` |  
| Edit (Expense)          | `edit INDEX` <br>  `[FIELD] [NEW VALUE]  `<br>E.g. <br>`edit 1` <br> `category Food`                                        |
| Edit (Module)           | `edit <index>` <br> E.g. <br> `edit 1` <br> `m/CS2113`                                                                      |
| Edit (Contact)          | `edit INDEX [n/NAME] [f/FACULTY] [e/EMAIL] [d/DESCRIPTION]`                                                                 |
| Read from .ics (Module) | `read ics`                                                                                                                  |
| Find                    | `find KEYWORD`                                                                                                              |
| Remove                  | `rm INDEX`                                                                                                                  |
| List                    | `list`                                                                                                                      |
| Return to Menu          | `menu`                                                                                                                      |
| Help                    | `help`                                                                                                                      |
| Navigate                | `goto m/SECTION`                                                                                                            |
| Exit                    | `exit`                                                                                                                      |
