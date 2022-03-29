# PlanITarium User Guide

This document contains the User Guide to the **PlanITarium** application. It serves to describe the features of the
application and provide examples of its utilisation. Each feature, or also known as **commands**, are described in
detail in the [Features](#features) section below.

**PlanITarium** is a Command-Line Interface application that helps you and your family to track your finances. It allows
you to:

* add your family members and **track** their income and expenditure,
* get a monthly **view** on your financial status,
* **group** your loved ones for better management,
* **categorise** your expenses to assist with financial planning.

**Who this product is meant for:**

* Young adults who are looking for a **fast and easy** financial tracker.
* Individuals that needs a **monthly overview** on their financial status.
* Those who wish to track the **financial status** of their family members.
* **Experienced typists** who can operate the application on a terminal.

## Table of Contents

* [Quick Start](#quick-start)
* [Quick Notes]()
* [Features]()
    * [View all commands: `help`](#viewing-all-commands-help)
    * [Add a person: `add`](#add-a-person-add)
    * [Delete a person: `delete`](#delete-a-person-delete)
    * [Add an income: `addin`](#add-an-income-addin)
    * [Delete an income: `deletein`](#delete-an-income-deletein)
    * [Edit an income: `editin`](#edit-an-income-editin)
    * [Add an expenditure: `addout`](#add-an-expenditure-addout)
    * [Delete an expenditure: `deleteout`](#delete-an-expenditure-deleteout)
    * [Edit an expenditure: `editout`](#edit-an-expenditure-editout)
    * [Show financial summary: `overview`](#show-financial-summary-overview)
    * [Show all records by group: `list`](#show-all-records-by-group-list)
    * [Show expenditure categories: `listcat`](#show-expenditure-categories-listcat)
    * [Searching for details: `find`](#searching-for-details-find)
    * [Exit the program: `bye`](#exit-bye)
* [Frequently Asked Questions]()
* [Command Summary]()

<br/>

## Quick Start

1. Ensure that you are running **Java 11** on your device.
2. Down the latest `PlanITarium.jar` from [releases](https://github.com/AY2122S2-CS2113T-T10-2/tp/releases).
3. Move the file into a folder that you want to use as the _home folder_.
4. Open a terminal in the _home folder_ and run `java -jar PlanITarium.jar`.
5. Type your commands into the terminal and press [Enter] to execute it.
6. Refer to [Features](#features) below for details on each command.

<br/>

## Quick Notes

The following table describes the **formats** used in this User Guide.

| Format              | Description                                                                                                                                              |
|:--------------------|:---------------------------------------------------------------------------------------------------------------------------------------------------------|
| `/x`                | A forward slash that is appended by a letter denotes a delimiter that should be accompanied by a block term e.g. `/n NAME`.                              |
| `BLOCK_TERM`        | The terms in upper casing are placeholders parameters for details to be supplied by you e.g. `add /n Alice` instead of `add /n NAME`.                    |
| `/x BLOCK_TERM`     | The delimiter-term pairs not enclosed in any brackets are compulsory inputs to be supplied by you.                                                       |
| `[/x BLOCK_TERM]`   | The delimiter-term pairs enclosed in square brackets are optional inputs that you may choose not to provide.                                             |
| <code>/p <T&#124;F> | The parameters enclosed in angle brackets indicates that they have fixed values. You may choose any of the valuse that are separated by a vertical pipe. |

Some additional points to take note of:

* You may provide delimiter-parameter pair in any preferred order.
    * Example: `add /n Alice /g 2`, `add /g 2 /n Alice`
* If you wish to use a forward slash `/` in any parameters, enclose in whitespaces.
    * Example: `Bought on / off switch` as a description
* You need not include any currency symbols and monetary values are in at most 2 decimal places.
* You may provide duplicate entries such as when you have two family members with the same name. PlanITarium will track
  each of them as separate entries via indexing.

<br/>

## Features

This section describes each command in detail.

**How to use this section:**

* The command formats and examples are provided in a `code block` so that you can **easily copy** them into PlanITarium.
* Each command is described on an **incremental basis from the previous command**, you may refer to a previous command
  for more information e.g. a `Delete` command using information from the `Add` command before it.
* Sub-points with :information_source: indicates details that you should take note of.
* Refer to the following table for more details on the parameters that you need to provide.

| Pair                              | Description                                                                                                                                                                                             |
|:----------------------------------|:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `/n NAME`                         | The name of someone you would like to track.                                                                                                                                                            |
| `/g GROUP_INDEX`                  | An index that helps you to categorise the individuals being tracked.<br/> You can find the group index from the [overview command]().                                                                   |
| `/u USER_INDEX`                   | An index that is tagged to someone you are tracking.<br/>You can find the user index from the [overview command]().                                                                                     |
| `/d DESCRIPTION`                  | The description (or name) of the income and expenditure you wish to track.                                                                                                                              |
| `/c CATEGORY_INDEX`               | An index that refers to a category label such as 'Food and Drinks`.<br/>You can find the category index from the [list categories command]().                                                           |
| `/i INCOME`                       | The monetary value of the income you wish to track.                                                                                                                                                     |
| `/e EXPENDITURE`                  | The monetary value of the expenditure that you have made.                                                                                                                                               |
| `/r (INCOME / EXPENDITURE)_INDEX` | An index that refers to an income or expenditure that you have recorded previously.<br/>You can find this index from the [listing records command]().                                                   |
| <code>/p <T&#124;(any)>           | `T` indicates that an income or expense that you are tracking in the application is recurrent on a monthly-basis while `(any)` refers to any other inputs which will indicate that it is non-recurrent. |

<br/>

### Viewing all commands: `help`

> Shows you a list of available commands that is described in this section.

Format: `help`

<br/>

### Add a person: `add`

> Add yourself or a family member to start tracking.

Format: `add /n NAME /g GROUP_INDEX`

* **NAME** refers to your name.
* **GROUP_INDEX** refers to the group that you want to be assigned to.

> :information_source: Notes:
> * Each group will track persons assigned to it separately from one another.
> * The tracked persons will be indexed starting from 1 and incremented for every entry.
> * Each entry will be treated as a unique individual regardless of naming or grouping.

Example of usage:

* Add Alice to the Parent's category to be tracked.

  `add /n Alice /g 1`

* _Result_: Entry for Alice is added to Parent's tracking list with a user index of **1**.

  ![add-command-screenshot]() <!-- this is a placeholder -->

<br/>

### Delete a person: `delete`

> Deletes a person from the list to track finance

Format: `delete /g GROUP_INDEX /u USER_INDEX`

* **USER_INDEX** refers to the index of that is tagged to the to-be-deleted person.
* **GROUP_INDEX** refers to the index of the group that belongs to this person.

Example of usage:

`delete /u 1 /g 1`

<br/>

### Add an income: `addin`

> Add an entry to your list of tracked incomes.

Format: `addin /g GROUP_INDEX /u USER_INDEX /d DESCRIPTION /i INCOME /p <T/(any)>`

* **GROUP_INDEX** refers to the index of the group that you belong to.
* **USER_INDEX** refers to the index of that is tagged to you.
* **DESCRIPTION** refers to the name or description of your income.
* **INCOME** refers to the monetary value of your income.
* **<T/(any)>** refers to whether your income is recurrent on a monthly-basis.

> :information_source: Notes:
> * Each person will have their incomes recorded separately from one another.
> * The income entries will be indexed starting from 1 and **incremented** for every entry.

Example of usage:

* Add a monthly-recurrent Salary of $2,000 to Alice's income.

  `addin /g 1 /u 1 /d Salary /i 2000 /p T`

* _Result_: Entry for Salary is added to Alice's income list with an income index of **1**.

  ![addin-command-screenshot]() <!-- this is a placeholder -->

<br/>

### Delete an income: `deletein`

> Delete an entry from your list of tracked incomes.

Format: `deletein /g GROUP_INDEX /u USER_INDEX /r INCOME_INDEX`

* **GROUP_INDEX** refers to the index of the group that you belong to.
* **USER_INDEX** refers to the index of that is tagged to you.
* **INCOME_INDEX** refers to the index of the income you would like to delete.

> :information_source: Notes:
> * Entries in an income list which have an income index higher than the deleted one will be **decremented** after the 
    deletion is completed.

Example of usage:

* Delete the entry, recurrent Salary of $2,000, from Alice's income.

  `deletein /g 1 /u 1 /r 1`

* _Result_: Income entry is deleted from Alice's income list.

  ![deletein-command-screenshot]() <!-- this is a placeholder -->

<br/>

### Edit an income: `editin`

> Edit an income under a stated user

Format: `editin /g GROUP_INDEX /u USER_INDEX /r INCOME_INDEX /i INCOME /d DESCRIPTION /c CATEGORY_INDEX /p <T|F>`

Example of usage:

`editin /u 1 /g 1 /r 1 /i 200 /d salary /c 1 /p t`

<br/>

### Add an expenditure: `addout`

> Adds an expenditure description and its value to a specific individual

Format: `addout /g GROUP_INDEX /u USER_INDEX /e EXPENDITURE /d DESCRIPTION /c CATEGORY_INDEX /p <T|F>`

* **USER_INDEX** refers to an index that is mapped to that individual.
* **GROUP_INDEX** refers to an index that is mapped to the group that individual belongs to.
* **DESCRIPTION** refers to the name or description of the expenditure.
* **EXPENDITURE** refers to the monetary value of the expenditure.
* **CATEGORY_INDEX** refers to the category of the expenditure.
* **<T|F>** refers to the setting of expenditure to be recurring or not recurring.

Example of usgae:

* Add a spending of $20 on a piece of candy to an individual with the user index of 1, in group 1 and type of
  expenditure in category 1.
    * Suppose that the user index 1 belong to Alice, group 3 to be children, category 1 to be Food and Drinks, and this
      expenditure is recurring.
        * `addout /u 1 /g 3 /e 20 /d candy /c 1 /p t`

  ![addout-command-screenshot]() <!-- this is a placeholder -->

<br/>

### Delete an expenditure: `deleteout`

> Deletes an expenditure description and its value from a specific individual

Format: `deleteout /g GROUP_INDEX /u USER_INDEX /r EXPENDITURE_INDEX`

* **USER_INDEX** refers to an index that is mapped to that individual. .
* **GROUP_INDEX** refers to an index that is mapped to the group that individual belongs to.
* **EXPENDITURE_INDEX** refers to an index that is mapped to a specific expenditure of an individual.

Example of usage:

* Delete the previous entry, spending of $20 on Candy, from Alice in group 3.
    * Suppose that the previous expenditure entry was given an index of 2
    * `deleteout /u 1 /g 3 /r 2`

  ![deleteout-command-screenshot]() <!-- this is a placeholder -->

<br/>

### Edit an expenditure: `editout`

> Edits an expenditure description, its value, its category index and its recurring status of a specific individual.

Format: `editout /g GROUP_INDEX /u USER_INDEX /r EXPENDITURE_INDEX /i EXPENDITURE /d DESCRIPTION /c CATEGORY_INDEX /p <T|F>`

* **USER_INDEX** refers to an index that is mapped to that individual.
* **GROUP_INDEX** refers to an index that is mapped to the group that individual belongs to.
* **DESCRIPTION** refers to the name or description of the expenditure.
* **EXPENDITURE** refers to the monetary value of the expenditure.
* **CATEGORY_INDEX** refers to the category of the expenditure.
* **<T|F>** refers to the setting of expenditure to be recurring or not recurring.

Example of usage:

* Edits an expenditure entry to have a description of sugar with value of $30, to be in category 1 and set to be
  recurring.
    * Suppose that the entry was given a user index of 1, in group 3 with an expenditure index of 1.
    * `editout /u 1 /g 3 /r 1 /i 30 /d sugar /c 1 /p t`

  ![editout-command-screenshot]() <!-- this is a placeholder -->

<br/>

### Show financial summary: `overview`

> Shows a list of the total incomes, expenditures and remaining of each group

Format: `overview`

Example of usage:

![list-command-screenshot]() <!-- this is a placeholder -->

<br/>

### Show all records by group: `list`

> Shows a list of incomes and expenditures, in a group

Format: `list /g GROUP_INDEX`

* **GROUP_INDEX** refers to an index that is mapped to the group that individual belongs to.

Example of usage:

`list /g 1`

![detail_list-command-screenshot]() <!-- this is a placeholder -->

<br/>

### Show expenditure categories: `listcat`

> Shows a list of categories

Format: `listcat`

<br/>

### Searching for details: `find`

> Search for a string throughout all income and expenditure objects

Format: `find /d USER_STRING /c CATEGORY_INDEX`

* **USER_STRING** refers to the keyword which you want to look for.
    * Keywords are case-sensitive and inclusive. So a search for `brush` will successfully look for `toothbrush`.

<!-- can put in notes ^ -->

Example of usage:

`find /d Candy /c 1`

![find-command-screenshot]() <!-- this is a placeholder -->

<br/>

### Exit: `bye`

> Exits the program.

Format: `bye`

<br/>

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: You can keep the file where your data stores, and transfer the file to another computer.

<br/>

## Command Summary

To keep things simple, we will omit the parameter description for you. To view each command in detail, refer to
the [features](#features) section.

| Command                                                             | Format                                             |
|---------------------------------------------------------------------|----------------------------------------------------|
| [View all commands](#viewing-all-commands-help)                     | add /n __ /g __                                    |
| [Add a person](#add-a-person-add)                                   | delete /u __ /g __                                 |
| [Delete a person](#delete-a-person-delete)                          | addin /u__ /g __ /i __ /d __ /c __ /p __           |
| [Add an income](#add-an-income-addin)                               | deletein /u __ /g __ /r __                         |
| [Delete an income](#delete-an-income-deletein)                      | editin /u __ /g __ /r __ /i __ /d __ /c __  /p __  |
| [Edit an income](#edit-an-income-editin)                            | addout /u__ /g __ /e __ /d __ /c __ /p __          |
| [Add an expenditure](#add-an-expenditure-addout)                    | deleteout /u __ /g __ /r __                        |
| [Delete an expenditure](#delete-an-expenditure-deleteout)           | editout /u __ /g __ /r __ /e __ /d __ /c __  /p __ |
| [Edit an expenditure](#edit-an-expenditure-editout)                 | overview                                           |
| [Show financial summary](#show-financial-summary-overview)          | list /g __                                         |
| [Show all records by group](#show-all-records-by-group-list)        | find /d __ /c __                                   | 
| [Show expenditure categories](#show-expenditure-categories-listcat) | listcat                                            |
| [Searching for details](#searching-for-details-find)                | bye                                                |
| [Exit the program](#exit-bye)                                       | help                                               |
