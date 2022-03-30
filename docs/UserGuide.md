# User Guide

## Contents

1. [Introduction](#introduction)
2. [Quick start](#quick-start)
3. [About this user guide](#about-this-user-guide)
   1. [Explanation of notation](#explanation-of-notation)
   2. [Specifying tasks](#specifying-tasks)
4. [Features](#features)
   1. [Accessing help](#accessing-help-help)
   2. [Accessing options](#accessing-options-option)
   3. [Adding a task/module](#adding-a-taskmodule-add)
   4. [Deleting a task/module](#deleting-a-taskmodule-del)
   5. [Editing a task/module](#editing-a-taskmodule-edit)
   6. [Marking a task](#marking-a-task-mark)
   7. [Managing custom tags](#managing-custom-tags-tag)
   8. [Setting a module's grade](#setting-a-modules-grade-grade)
   9. [Viewing GPA](#viewing-gpa-gpa)
   10. [Resetting the program](#resetting-the-program-reset)
   11. [Saving your data](#saving-your-data-save)
5. [FAQ](#faq)
6. [Command summary](#command-summary)
   

## Introduction

Mod Happy is a command line application designed to help you manage your academic matters in a student-friendly manner. It can track your outstanding tasks, categorise them according to modules and other user-created tags, and even help to perform GPA computations for you.

## Quick start

1. Ensure that you have _Java 11_ or above installed. The link to the _Java 11_ installer can be found [here](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html).
2. Download the latest version of `Mod Happy` [here](https://github.com/AY2122S2-CS2113T-T10-3/tp/releases).
3. Copy the JAR file into an empty folder.
4. Open a terminal on your laptop, and navigate to the directory containing the JAR file.
5. Run the command  `java -jar tp.jar` to start the program.

## About this user guide

### Explanation of notation

- User-supplied input parameters are indicated by fully capitalised field names. For instance, in `del mod MODULE_CODE`, you would replace `MODULE_CODE` with the module code of the module you wish to delete (e.g. `del mod CS2113T`).
- When multiple parameters are indicated within round brackets and separated with `|`, exactly one parameter must be chosen. For example, `mark (c | u)` means either one of `mark c` or `mark u`.
- Parameters indicated within square brackets, such as `[-d "MODULE_DESCRIPTION"]`, are optional. The part of the command enclosed within these brackets may be omitted if you wish.

> 📔 <span style="color:#00bb00">**NOTE:**</span>
> 
> Pay special attention to whether input parameters are surrounded by double quotes. Missing or unnecessary double quotes will likely result in Mod Happy not understanding your command.

> ⚠ <span style="color:#ffa500">**IMPORTANT:**</span>
> 
> All parameters, including optional ones, must appear in the same order they are given in the command format provided for each command. Mod Happy may not understand your command if you specify these parameters in a different order.

### Specifying tasks

Many commands require you to specify a task for it to act on. This is done by providing a task number, as well as optionally a module code. For example:
- Task number `3`, module code `CS2113T`: refers to task number 3 stored under the module CS2113T.
- Task number `2`, no module code specified: refers to task number 2 stored under the General Tasks list.

## Features

### Accessing help: `help`

Displays help for the indicated command. If no command word is supplied, a generic help message is shown.

Format: `help [COMMAND_WORD]`

### Accessing options: `option`

Allows you to view and change user preferences. This command has three different formats, each of which serve a different purpose.

- **Viewing available configuration options**
  
  Lists the names and current statuses of all available configuration options.<br><br>
  Format: `option`<br><br>
- **Viewing details of a specific configuration option**

  Displays a short description of the supplied configuration option as well as its corresponding accepted values.<br><br>
  Format: `option CONFIG_NAME`<br><br>
  Example: `option SHOW_COMPLETED_TASKS`<br><br>
- **Editing a specific configuration option**

  Sets the value of the specified configuration option to the one you specify (if it is valid).<br><br>
  Format: `option CONFIG_NAME=NEW_VALUE`<br><br>
  Example: `option SHOW_COMPLETED_TASKS=false`
  > 📔 <span style="color:#00bb00">**NOTE:**</span>
  >
  > The whitespace around the `=` is optional. In other words, `option SHOW_COMPLETED_TASKS=false` is also a valid command input.

The following configuration options currently exist:

| Config name          | Description                                                                                                 | Accepted values                                                                |
|----------------------|-------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------|
| SHOW_COMPLETED_TASKS | Determines whether tasks marked as completed are shown by the `list` command.<br>**Default value: `false`** | `true`: **All** tasks are shown.<br>`false`: Only uncompleted tasks are shown. |  


### Adding a task/module: `add`

Adds an object as indicated by the command argument.

- **Add module: `add mod`**

  Adds a module to the list of modules tracked by Mod Happy. You have to indicate the number of modular credits and optionally specify a short description for the module.<br>
  > ⚠ <span style="color:#ffa500">**IMPORTANT:**</span>
  >
  > The module code must be a single word, and can only consist of alphanumeric characters as well as the underscore `_`.

  Format: `add mod MODULE_CODE MODULAR_CREDITS [-d "MODULE_DESCRIPTION"]`<br><br>
  Example: `add mod CS2113T 4 -d "Software Engineering"`<br><br>
  
  > 📔 <span style="color:#00bb00">**NOTE:**</span>
  > 
  > Module codes are case-sensitive. Mod Happy treats `CS2113T` and `cs2113t` as two different modules!
- **Add task: `add task`**
  
  Adds a task to the list of tasks tracked under the specified module code. If no module code is supplied, the task is added to the General Tasks list, which is not associated with any module.<br><br>

  You may optionally specify a short description for the task, as well as the estimated for the time to be spent working on it.<br><br>

  Format: `add task "TASK_NAME" [-m MODULE_CODE] [-d "TASK_DESCRIPTION"] [-t “ESTIMATED_WORKING_TIME”]`<br><br>
  Example (general task without any parameters): `add task "Review PR"`<br>
  Example (module task with parameters): `add task "iP Level-0" -m CS2113T -d "Greet user and exit" -t "1 hour" `
  > 📔 <span style="color:#00bb00">**NOTE:**</span>
  >
  > ESTIMATED_WORKING_TIME is in task duration format, please check [task duration](#task-duration) to see the supporting format.

### Deleting a task/module: `del`

Deletes an object as indicated by the command argument.

- **Delete module: `del mod`**

  Deletes the specified module from the list of modules tracked by Mod Happy.<br><br>
  Format: `del mod MODULE_CODE`<br><br>
  Example: `del mod CS2113T`<br><br>
- **Delete task: `del task`**

  Deletes the [specified task](#specifying-tasks) from its associated task list.<br><br>
  Format: `del task TASK_NUMBER [-m MODULE_CODE]`<br><br>
  Example (general task): `del task 1`<br>
  Example (module task): `del task 1 -m CS2113T`<br><br>

### Editing a task/module: `edit`

Edits an object's parameter as indicated by the command arguments.<br>

- **Edit module: `edit mod`**

  The module to be edited is specified with its module code. Only the module description is editable after the module is created.<br><br>
  Format: `edit mod MODULE_CODE -d "MODULE_DESCRIPTION"` <br><br>
  Example: `edit mod CS2113T -d "Software Engineering & OOP"`<br><br>
- **Edit task: `edit task`**

  Edits the [specified task](#specifying-tasks). The task name, description, and estimated working time are editable, but the task cannot be associated with a different module.<br><br>
  Format: `edit task TASK_NUMBER [-m MODULE_CODE] (-n "TASK_NAME" | -d "TASK_DESCRIPTION" | -t "ESTIMATED_WORKING_TIME")`
  <br><br>
  Example: `edit task 1 -m CS2113T -n "CS2113T Tutorial 2"` <br><br>
  > 📔 <span style="color:#00bb00">**NOTE:**</span>
  >
  > Only one parameter can be edited per command. The following input is not allowed:
  >
  > `edit task 2 -m CS2113T -n "CS2113T Tutorial 1" -d "Draw class diagram"`

### Marking a task: `mark`

Marks the [specified task](#specifying-tasks) as completed or uncompleted.

The `c` flag indicates that the task will be marked as completed, while the `u` flag marks the task as uncompleted.

Format: `mark (c | u) TASK_INDEX [-m MODULE_CODE]`<br><br>
Example (mark general task as completed): `mark c 1`<br>
Example (mark module task as uncompleted): `mark u 1 -m CS2113T`

### Managing custom tags: `tag`

Adds or deletes a tag from the [specified task](#specifying-tasks).

> ⚠ <span style="color:#ffa500">**IMPORTANT:**</span>
>
> The tag name must be a single word; it cannot contain whitespace.

Format: `tag (add | del) TASK_INDEX [-m MODULE_CODE] TAG_NAME`

Example: `tag add 1 -m CS2113T project`

### Listing all tasks/modules: `list`

Displays a list of tasks, grouped by module code. General tasks are displayed separately.

If a tag name is provided, only tasks with the associated tag will be shown.

> 📔 <span style="color:#00bb00">**NOTE:**</span>
> 
> If the `SHOW_COMPLETED_TASKS` option is set to `false`, any tasks marked as completed will be omitted from the displayed list. The number of hidden tasks is given at the bottom of each group.

Format: `list [TAG_NAME]`

### Setting a module's grade: `grade`

Assigns a grade to a module, specified by its module code.

Format: `grade MODULE_CODE MODULE_GRADE`

> 📔 <span style="color:#00bb00">**NOTE:**</span>
>
> Only the following grades are supported (case-insensitive):<br>
> A+, A, A-, B+, B, B-, C+, C, D+, D, F, S, U, CS, CU

Example: `grade CS2113T A+`

### Viewing GPA: `gpa`

Computes and displays the GPA based the inputted grades of all currently stored modules. Modules without any assigned grade are omitted from the calculation.

Format: `gpa`

### Resetting the program: `reset`

Removes all tasks and modules.

Format: `reset`

### Saving your data: `save`

Saves all tasks and modules to the data file.

Format: `save`

> ⚠ <span style="color:#ffa500">**IMPORTANT:**</span>
>
> Mod Happy does **not** auto-save your changes! Do remember to save your work at regular intervals, or before exiting the program.

### Task Duration
The format of task duration:
`DURATION_NUMBER DURATION_UNIT`
- **Duration number:**
Mod Happy supports any positive decimal and integer no bigger than 1000,000,000. 
- **Duration unit: Mod Happy understands the following forms of duration units:**

| `hour` | `minute`                                                                                                                                                                            |
|:-------:|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|  h  |       m                 |
|  H   | M         |
|   hour   | minute                                                                                                                      |
|  Hour   | Hour |
|  hours   |         minutes            |
|   Hours |   Minutes                        |
|   | min                                                                                                                                                                 |
| - | Min                                                                                                                              |

> ⚠ <span style="color:#ffa500">**IMPORTANT:**</span>
>
> User can only choose either hour or minute as the input of duration unit. For decimal hour, Mod Happy will convert the decimal part as minute offset. For decimal minute, Mod Happy will do rounding for the decimal part. 

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: Your task and module data are stored in the `data` folder, located in the same folder as Mod Happy's JAR file. To transfer data to another computer, simply copy this folder to the new machine. Make sure to place the folder in the same location as the Mod Happy application itself!

## Command summary

| Command | Format                                                                                                                                                                               |
|:-------:|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|  help   | `help [COMMAND_WORD]`                                                                                                                                                                |
|   add   | `add mod MODULE_CODE MODULAR_CREDITS [-d "MODULE_DESCRIPTION"]`<br>`add task "TASK_NAME" [-m MODULE_CODE] [-d "TASK_DESCRIPTION"] [-t “ESTIMATED_WORKING_TIME”]`                     |
|   del   | `del mod MODULE_CODE`<br>`del task TASK_NUMBER [-m MODULE_CODE]`                                                                                                                     |
|  edit   | <code>edit task TASK_NUMBER [-m MODULE_CODE] (-n "TASK_NAME" &#124; -d "TASK_DESCRIPTION" &#124; -t "ESTIMATED_WORKING_TIME")</code> <br> `edit mod MODULE_CODE -d "MODULE_DESCRIPTION"` |
|  mark   | <code>mark (c &#124; u) TASK_NUMBER [-m MODULE_CODE]</code>                                                                                                                          |
|   tag   | <code>tag (add &#124; del) [-m MODULE_CODE] TAG_NAME</code>                                                                                                                          |
|  list   | `list [TAG_NAME]`                                                                                                                                                                    |
|  grade  | `grade MODULE_CODE MODULE_GRADE`                                                                                                                                                     |
|   gpa   | `gpa`                                                                                                                                                                                |
|  reset  | `reset`    
|  option | `option CONFIG_NAME=NEW_VALUE`
|  save   | `save`                                                                                                                                                                               |
