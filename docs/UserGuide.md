# Mod Happy: User Guide

## Contents
1. [Introduction](#1-introduction)
2. [Quick start](#2-quick-start)
   <br>2.1. [Recommended operating systems](#21-recommended-operating-systems)
3. [About this user guide](#3-about-this-user-guide)
   <br>3.1. [Explanation of notation](#31-explanation-of-notation)
   <br>3.2. [Specifying tasks](#32-specifying-tasks)
   <br>3.3. [Specifying durations](#33-specifying-durations)
4. [Features](#4-features)
   <br>4.1. [Accessing help](#41-accessing-help-help)
   <br>4.2. [Accessing options](#42-accessing-options-option)
   <br>4.3. [Adding a task/module](#43-adding-a-taskmodule-add)
   <br>4.4. [Deleting a task/module](#44-deleting-a-taskmodule-del)
   <br>4.5. [Editing a task/module](#45-editing-a-taskmodule-edit)
   <br>4.6. [Marking a task](#46-marking-a-task-mark)
   <br>4.7. [Managing custom tags](#47-managing-custom-tags-tag)
   <br>4.8. [Listing all tasks](#48-listing-all-tasks-list)
   <br>4.9. [Setting a module's grade](#49-setting-a-modules-grade-grade)
   <br>4.10. [Viewing GPA](#410-viewing-gpa-gpa)
   <br>4.11. [Resetting the program](#411-resetting-the-program-reset)
   <br>4.12. [Saving your data](#412-saving-your-data-save)
5. [FAQ](#5-faq)
6. [Command summary](#6-command-summary)

---

## 1. Introduction

Mod Happy is a command line application geared towards NUS students. Designed to be your personal assistant for all things academic, Mod Happy can **track** your outstanding tasks, **tag** them for easy organisation and **categorise** them according to modules, and even help you **calculate** your projected GPA.

<br><br><br>

## 2. Quick start

1. Ensure that you have _Java 11_ or above installed. The link to the _Java 11_ installer can be found [here](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html).
2. Download the latest version of `Mod Happy` [here](https://github.com/AY2122S2-CS2113T-T10-3/tp/releases).
3. Copy the JAR file into an empty folder.
4. Open a terminal on your laptop, and navigate to the directory containing the JAR file.
5. Run the command `java -jar tp.jar` to start the program.

<br>

### 2.1. Recommended Operating Systems

Mod Happy has been tested on and is confirmed to work with the following major operating systems:

| Operating System  | Version                        | 
|:------------------|:-------------------------------|
| Microsoft Windows | Windows 7 and above            | 
| Apple macOS       | MacOS 10.15 Catalina and above | 
| Ubuntu Linux      | Ubuntu 18.04 and above         | 
| Kali Linux        | Debian 10.10 and above         | 
| CentOS Linux      | CentOS 7 and above             |

> 📔 <span style="color:#3333ff">**NOTE:**</span>
> 
> Although Mod Happy may still work on operating systems not listed in the above table, we cannot guarantee that it will be bug-free or that the performance will be optimal.

<br><br><br>

## 3. About this user guide

This user guide contains detailed information about the various features and commands available within Mod Happy. For each command, you can find its format, a short description of what it does, as well as some example usage scenarios to help illustrate its effects. 

The following section details the various terminologies and notation used throughout the guide.

<br>

### 3.1. Explanation of notation

- Fully capitalised field names, like `MODULE_CODE`,  indicate input parameters which you supply. For instance, in `del mod MODULE_CODE`, you would replace `MODULE_CODE` with the module code of the module you wish to delete (e.g. `del mod CS2113T`).
- When multiple parameters are enclosed within round brackets `()` and separated with `|`, you must choose exactly one of the options presented. For example, `mark (c | u)` means that you must pick either `mark c` or `mark u`.
- Parts of the command indicated within square brackets `[]` are optional, and you may choose to omit the enclosed section if you wish. For example, if the command format is `list [TAG_NAME]`, `list` and `list example_tag` are both valid inputs.

> 📔 <span style="color:#3333ff">**NOTE:**</span>
> 
> Pay special attention to whether input parameters are surrounded by double quotes in the command format. Missing or unnecessary double quotes will likely result in Mod Happy not understanding your command.
> 
> Example 1: `command EXAMPLE` does not require double quotes around `EXAMPLE`. `command hello` is an example of a valid command.
> 
> Example 2: `command "EXAMPLE_2"` requires double quotes around `EXAMPLE_2`. `command "hello"` is an example of a valid command.

> ⚠ <span style="color:#ffa500">**IMPORTANT:**</span>
> 
> All parameters, including optional ones, must appear in the same order shown in the command format provided. Mod Happy may fail to interpret your command if you specify these parameters in a different order.

<br>

### 3.2. Specifying tasks

To understand how to specify tasks, it helps for you to have a brief understanding of how Mod Happy organises them. When a task is created, it is associated with a module (or the General Tasks list, if no module is provided) and stored within its list of tasks. In other words, there is no master list of tasks; tasks belonging to two different modules are stored in two entirely separate lists.

As a result, providing the task's number is not specific enough for Mod Happy to figure out which task you are referring to. Instead, you have to additionally specify the module code associated with the task. For example:
- Task number `3`, module code `CS2113T`: refers to task number 3 stored under the module CS2113T.
- Task number `2`, no module code specified: refers to task number 2 stored under the General Tasks list.

<br>

### 3.3. Specifying durations

Some Mod Happy commands require you to provide a duration. You can specify these in the following format:

#### Format: `DURATION_AMOUNT DURATION_UNIT`

- `DURATION_AMOUNT`: Any positive number less than or equal to one billion (1000000000), including decimals.
- `DURATION_UNIT`: The time unit that `DURATION_AMOUNT` is specified in. Mod Happy supports the following units:
  - Hours: `h`, `H`, `hour`, `Hour`, `hours`, `Hours`
  - Minutes: `m`, `M`, `min`, `Min`,`mins`, `Mins`, `minute`, `Minute`, `minutes`, `Minutes`
  
> ⚠ <span style="color:#ffa500">**IMPORTANT:**</span>
>
> You can only choose to specify the duration in hours or minutes — not both. If you need to specify "2 hours and 45 minutes", for example, try `2.75 hours` instead.

<br><br><br>

## 4. Features

### 4.1. Accessing help: `help`

- **Generic help**

  Shows you a generic help message.

  #### Format: `help`

  ##### Example:

  ```
  > help
  
  ____________________________________________________________
  Displays help and format for selected command.
  Format to display help for specific command: help COMMAND
  Available commands: exit, add, del, edit, grade, gpa, help, list, mark, option, reset, save, tag

  Compulsory parameters are fully capitalised: e.g. MODULE_CODE.
  Optional parameters are in square brackets: e.g. [-d MODULE_DESCRIPTION]
  ____________________________________________________________
  ```
  
  <br>

- **Help for specific command word**

  Shows you the help text for the specified command word. 
 
  #### Format: `help [COMMAND_WORD]`

  - `COMMAND_WORD`: The command you wish to view the help message for.
  
  ##### Example:

  ```
  > help add
  
  ____________________________________________________________
  Adds a module or task as indicated by the command input.
  Format to add module: add mod MODULE_CODE MODULAR_CREDITS [-d "MODULE_DESCRIPTION"]
  Format to add task:   add task "TASK_NAME" [-m MODULE_CODE] [-d "TASK_DESCRIPTION"] [-t "ESTIMATED_WORKING_TIME"]
  ____________________________________________________________
  ```

<br>

### 4.2. Accessing options: `option`

Allows you to view and change various user preferences which can affect other aspects of Mod Happy's operation. This command has three different formats, each of which serve a different purpose.

- **Viewing available configuration options**
  
  Lists the names of all available configuration options, as well as what you have them currently set to.

  #### Format: `option`

  ##### Example:

  ```
  > option
  
  ____________________________________________________________
  Available config settings:
  SHOW_COMPLETED_TASKS: false
  ____________________________________________________________
  ```
  
  <br>

- **Viewing details of a specific configuration option**

  Shows you a short description of the supplied configuration option as well as its corresponding valid values.

  #### Format: `option CONFIG_NAME`

  - `CONFIG_NAME`: The name of the configuration option you wish to view the details of.<br><br>
  
  ##### Example:

  ```
  > option SHOW_COMPLETED_TASKS
  
  ____________________________________________________________
  SHOW_COMPLETED_TASKS
  false: Hide completed tasks
  true: Show completed tasks
  ____________________________________________________________
  ```
  
  <br>

- **Editing a specific configuration option**

  Allows you to edit the value of a configuration option of your choice.

  #### Format: `option CONFIG_NAME=NEW_VALUE`

  - `CONFIG_NAME`: The name of the configuration option you wish to modify.
  - `NEW_VALUE`: The new value of the configuration option. This value must be a value accepted by the target configuration option.
  
  ##### Example:

  ```
  > option SHOW_COMPLETED_TASKS=true
  
  ____________________________________________________________
  Preferences updated: SHOW_COMPLETED_TASKS=true
  ____________________________________________________________
  ```
  
  <br>

  > 📔 <span style="color:#3333ff">**NOTE:**</span>
  >
  > Due to the design of Mod Happy, CONFIG_NAME and NEW_VALUE should be connected only by "=". Whitespaces between keywords will not be accepted by the application.
  >
  > Common illegal input:
  > ```
  > option SHOW_COMPLETED_TASKS = true
  >  ```

<br>
The following configuration options currently exist:

| Config name          | Description                                                                                                                            | Accepted values                                                                |
|----------------------|----------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------|
| SHOW_COMPLETED_TASKS | Determines whether tasks marked as completed are shown when [listing tasks](#48-listing-all-tasks-list).<br>**Default value: `false`** | `true`: **All** tasks are shown.<br>`false`: Only uncompleted tasks are shown. |  

<br>

### 4.3. Adding a task/module: `add`

- **Add module: `add mod`**

  Adds a module to your module list. You must indicate the number of modular credits and may optionally specify a short description for the module.

  #### Format: `add mod MODULE_CODE MODULAR_CREDITS [-d "MODULE_DESCRIPTION"]`

  - `MODULE_CODE`: The module code of the module. Must be a single word containing only alphanumeric characters and underscore `_`.
  - `MODULAR_CREDITS`: The number of modular credits the module has. Must be an integer from 0 to 100, inclusive.
  - `MODULE_DESCRIPTION`: A short description of the module. Can contain any characters except double quotes `"`.
  
  ##### Example 1:

  ```
  > add mod CS2101 4
  
  ____________________________________________________________
  Hey! I have added this module!
  CS2101 (4MC, Grade: -)
  ____________________________________________________________
  ```

  ##### Example 2:

  ```
  > add mod CS2113T 4 -d "Software Engineering"
  ____________________________________________________________
  Hey! I have added this module!
  CS2113T (Software Engineering) (4MC, Grade: -)
  ____________________________________________________________
  ```
  
  <br>

  > 📔 <span style="color:#3333ff">**NOTE:**</span>
  > 
  > Module codes are case-sensitive. Mod Happy treats `CS2113T` and `cs2113t` as two different modules!

  <br>

- **Add task: `add task`**
  
  Adds a task to the list of tasks tracked under the specified module code. If you do not specify any module code, the task is added to your General Tasks list, which is not associated with any module.<br><br>

  You may optionally specify a short description for the task, as well as an estimate for the expected time spent working on it.

  #### Format: `add task "TASK_NAME" [-m MODULE_CODE] [-d "TASK_DESCRIPTION"] [-t “ESTIMATED_WORKING_TIME”]`

  - `TASK_NAME`: The name of the task. Can contain any characters except double quotes `"`.
  - `MODULE_CODE`: The module code of the module to be associated with this task. Must be a single word containing only alphanumeric characters and underscore `_`. Furthermore, a module with this module code must currently exist.
  - `TASK_DESCRIPTION`: A short description of the task. Can contain any characters except double quotes `"`.
  - `ESTIMATED_WORKING_TIME`: The expected duration spent working on the task. The duration must be specified in [this format](#33-specifying-durations).
  
  ##### Example 1:

  ```
  > add task "Review PR"
  ____________________________________________________________
  Hey! I have added this task under General tasks!
  ( ) Review PR []
  ____________________________________________________________
  ```
  
  ##### Example 2:

  ```
  > add task "iP Level-0" -m CS2113T -d "Greet user and exit" -t "1 hour"
  ____________________________________________________________
  Hey! I have added this task under CS2113T (Software Engineering) (4MC, Grade: -)!
  ( ) iP Level-0 (Greet user and exit) (Estimated working time: 1 hours) []
  ____________________________________________________________
  ```

<br>

### 4.4. Deleting a task/module: `del`

- **Delete module: `del mod`**

  Deletes the specified module from your module list. You will be prompted for confirmation if the module has tasks assigned to it.

  #### Format: `del mod MODULE_CODE`

  - `MODULE_CODE`: The module code of the module to be deleted. Must be a single word containing only alphanumeric characters and underscore `_`. Furthermore, a module with this module code must currently exist.

  ##### Example:

  ```
  > del mod CS2113T

  ____________________________________________________________
  CS2113T (Software Engineering) (4MC, Grade: -) contains task(s).
  Are you sure you want to delete this? (yes/no)
  ____________________________________________________________
  
  > no

  ____________________________________________________________
  Deletion has been cancelled.
  ____________________________________________________________
  ```

  <br>

- **Delete task: `del task`**

  Deletes the [specified task](#32-specifying-tasks) from its parent module, or the General Tasks list if you do not specify a module code.

  #### Format: `del task TASK_NUMBER [-m MODULE_CODE]`

  - `TASK_NUMBER`: The number of the task to be deleted. Must be a positive integer.
  - `MODULE_CODE`: The module code of the module associated with this task. Must be a single word containing only alphanumeric characters and underscore `_`. Furthermore, a module with this module code must currently exist.

  ##### Example 1:

  ```
  > del task 1

  ____________________________________________________________
  ( ) Review PR [] has been deleted.
  ____________________________________________________________
  ```
  
  ##### Example 2:

  ```
  > del task 1 -m CS2113T

  ____________________________________________________________
  ( ) iP Level-0 (Greet user and exit) (Estimated working time: 1 hours) [] has been deleted.
  ____________________________________________________________
  ```

<br>

### 4.5. Editing a task/module: `edit`

- **Edit module: `edit mod`**

  Edits an attribute of a module you have previously created. Only the module description is editable after creation.

  #### Format: `edit mod MODULE_CODE -d "MODULE_DESCRIPTION"`

  - `MODULE_CODE`: The module code of the module to be edited. Must be a single word containing only alphanumeric characters and underscore `_`. Furthermore, a module with this module code must currently exist.
  - `MODULE_DESCRIPTION`: The new module description for the module. Can contain any characters except double quotes `"`.

  ##### Example:

  ```
  > edit mod CS2113T -d "Software Engineering & OOP"

  ____________________________________________________________
  The description of CS2113T has been changed.
  ____________________________________________________________
  ```
  <br>

- **Edit task: `edit task`**

  Edits an attribute of the [specified task](#32-specifying-tasks). You can edit the task name, description, and estimated working time, but the task cannot be associated with a different module.

  #### Format: `edit task TASK_NUMBER [-m MODULE_CODE] (-n "TASK_NAME" | -d "TASK_DESCRIPTION" | -t "ESTIMATED_WORKING_TIME")`

  - `TASK_NUMBER`: The number of the task to be edited. Must be a positive integer.
  - `MODULE_CODE`: The module code of the module associated with this task. Must be a single word containing only alphanumeric characters and underscore `_`. Furthermore, a module with this module code must currently exist.
  - `TASK_NAME`: The name of the task. Can contain any characters except double quotes `"`.
  - `TASK_DESCRIPTION`: The new description for the task. Can contain any characters except double quotes `"`.
  - `ESTIMATED_WORKING_TIME`: The new expected duration. The duration must be specified in [this format](#33-specifying-durations).

  ##### Example:

  ```
  > edit task 1 -m CS2113T -n "CS2113T Tutorial 2"

  ____________________________________________________________
  The task name of Prepare for tutorial from CS2113T has been changed.
  ____________________________________________________________
  ```
  <br>

  > 📔 <span style="color:#3333ff">**NOTE:**</span>
  >
  > Only one parameter can be edited per command. You cannot do the following:
  >
  > `edit task 2 -m CS2113T -n "CS2113T Tutorial 1" -d "Draw class diagram"`

<br>

### 4.6. Marking a task: `mark`

Allows you to mark the [specified task](#32-specifying-tasks) as completed or uncompleted.

The `c` flag indicates that the task will be marked as completed, while the `u` flag marks the task as uncompleted.

#### Format: `mark (c | u) TASK_NUMBER [-m MODULE_CODE]`

- `TASK_NUMBER`: The number of the task to be marked. Must be a positive integer.
- `MODULE_CODE`: The module code of the module associated with this task. Must be a single word containing only alphanumeric characters and underscore `_`. Furthermore, a module with this module code must currently exist.

##### Example 1:

```
> mark c 1

____________________________________________________________
Nice! I have marked this task as completed!
(X) Reply to emails []
____________________________________________________________
```

##### Example 2:

```
> mark u 1 -m CS2113T
____________________________________________________________
Ok! I have marked this task for you as uncompleted!
( ) CS2113T Tutorial 2 []
____________________________________________________________
```

<br>

### 4.7. Managing custom tags: `tag`

Allows you to add or delete a tag from the [specified task](#32-specifying-tasks).

#### Format: `tag (add | del) TASK_NUMBER [-m MODULE_CODE] TAG_NAME`

- `TASK_NUMBER`: The number of the task to be deleted. Must be a positive integer.
- `MODULE_CODE`: The module code of the module associated with this task. Must be a single word containing only alphanumeric characters and underscore `_`. Furthermore, a module with this module code must currently exist.
- `TAG_NAME`: The name of the tag to be added or deleted. Only alphanumeric characters and underscore `_` are allowed.

> ⚠ <span style="color:#ffa500">**IMPORTANT:**</span>
>
> The tag name cannot contain whitespace; it must be a single word.

##### Example:

```
> tag add 1 -m CS2113T project

____________________________________________________________
Tag "project" added:
( ) CS2113T Tutorial 2 [project].
____________________________________________________________
```

<br>

### 4.8. Listing all tasks: `list`

Shows you your tasks, grouped by module code. General tasks are displayed separately.

If a [tag name](#47-managing-custom-tags-tag) is provided, only tasks with the associated tag will be shown.

> 📔 <span style="color:#3333ff">**NOTE:**</span>
> 
> If the [`SHOW_COMPLETED_TASKS` option](#42-accessing-options-option) is set to `false`, you will only be shown your outstanding tasks. The number of tasks that were hidden will be indicated at the bottom of each group.

#### Format: `list [TAG_NAME]`

- `TAG_NAME`: The name of the tag to be filtered for. Must be a single word containing only alphanumeric characters and underscore `_`.

##### Example 1:

```
> list

____________________________________________________________
Ok! Here are the task(s) in your list:
CS2113T (Software Engineering & OOP) (4MC, Grade: -)
    1. ( ) CS2113T Tutorial 2 [project]

CS2101 (4MC, Grade: -)
    1. ( ) Write user guide peer review []

General tasks
    1. (X) Reply emails []
____________________________________________________________
```

##### Example 2:

```
> list project

____________________________________________________________
Ok! Here are the task(s) in your list:
CS2113T (Software Engineering & OOP) (4MC, Grade: -)
  1. ( ) CS2113T Tutorial 2 [project]
  
CS2101 (4MC, Grade: -)
  (empty)
  
General tasks
  (empty)

____________________________________________________________
```

<br>

### 4.9. Setting a module's grade: `grade`

Assigns a grade to a module of your choice.

#### Format: `grade MODULE_CODE MODULE_GRADE`

- `MODULE_CODE`: The module code of the module to be assigned the grade. Must be a single word containing only alphanumeric characters and underscore `_`. Furthermore, a module with this module code must currently exist.
- `MODULE_GRADE`: The grade to be assigned to the module.

> 📔 <span style="color:#3333ff">**NOTE:**</span>
>
> Only the following grades are supported (case-insensitive):
> 
> A+, A, A-, B+, B, B-, C+, C, D+, D, F, S, U, CS, CU
  
##### Example:

```
> grade CS2113T A+

____________________________________________________________
Your grade for CS2113T has been added.
____________________________________________________________
```

<br>

### 4.10. Viewing GPA: `gpa`

Computes your GPA based the [inputted grades](#49-setting-a-modules-grade-grade) of all currently stored modules, and displays it. Modules for which you have not inputted any grade are not factored into the calculation.

#### Format: `gpa`

##### Example:

```
> gpa

____________________________________________________________
Your GPA is 5.00! :)
____________________________________________________________
```

<br>

### 4.11. Resetting the program: `reset`

Removes all your tasks and modules.

#### Format: `reset`

##### Example:

```
> reset

____________________________________________________________
All modules and tasks have been removed.
____________________________________________________________
```

<br>

### 4.12. Saving your data: `save`

Saves all your tasks and modules to the data file.

#### Format: `save`

##### Example:

```
> save

____________________________________________________________
General tasks written to file.
Module data written to file.
Config options written to file.
____________________________________________________________
```

> ⚠ <span style="color:#ffa500">**IMPORTANT:**</span>
>
> Mod Happy does **not** auto-save your changes! Do remember to save your work at regular intervals, or before exiting the program.

<br><br><br>

## 5. FAQ

**Q**: How do I transfer my data to another computer?

**A**: Your task and module data are stored in the `data` folder, located in the same folder as Mod Happy's JAR file. To transfer data to another computer, simply copy this folder to the new machine. Make sure to place the folder in the same location as the Mod Happy application itself!

## 6. Command summary

|                  Command                   | Format                                                                                                                                                                                                                                                                                                                                                        |
|:------------------------------------------:|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|      [help](#41-accessing-help-help)       | `help [COMMAND_WORD]`<br><br>Examples:<br>`help`<br>`help add`                                                                                                                                                                                                                                                                                                |
|   [option](#42-accessing-options-option)   | `option`<br>`option CONFIG_NAME`<br>`option CONFIG_NAME=NEW_VALUE`<br><br>Examples:<br>`option`<br>`option SHOW_COMPLETED_TASKS`<br>`option SHOW_COMPLETED_TASKS=true`                                                                                                                                                                                        |
|     [add](#43-adding-a-taskmodule-add)     | `add mod MODULE_CODE MODULAR_CREDITS [-d "MODULE_DESCRIPTION"]`<br>`add task "TASK_NAME" [-m MODULE_CODE] [-d "TASK_DESCRIPTION"] [-t “ESTIMATED_WORKING_TIME”]`<br><br>Examples:<br>`add mod CS2101 4`<br>`add mod CS2113T 4 -d "Software Engineering"`<br>`add task "Review PR"`<br>`add task "iP Level-0" -m CS2113T -d "Greet user and exit" -t "1 hour"` |
|    [del](#44-deleting-a-taskmodule-del)    | `del mod MODULE_CODE`<br>`del task TASK_NUMBER [-m MODULE_CODE]`<br><br>Examples:<br>`del task 1`<br>`del task 1 -m CS2113T`                                                                                                                                                                                                                                  |
|   [edit](#45-editing-a-taskmodule-edit)    | `edit mod MODULE_CODE -d "MODULE_DESCRIPTION"`<br><code>edit task TASK_NUMBER [-m MODULE_CODE] (-n "TASK_NAME" &#124; -d "TASK_DESCRIPTION" &#124; -t "ESTIMATED_WORKING_TIME")</code><br><br>Examples:<br>`edit mod CS2113T -d "Software Engineering & OOP"`<br>`edit task 1 -m CS2113T -n "CS2113T Tutorial 2"`                                             |
|      [mark](#46-marking-a-task-mark)       | <code>mark (c &#124; u) TASK_NUMBER [-m MODULE_CODE]</code><br><br>Examples:<br>`mark c 1`<br>`mark u 1 -m CS2113T`                                                                                                                                                                                                                                           |
|    [tag](#47-managing-custom-tags-tag)     | <code>tag (add &#124; del) [-m MODULE_CODE] TAG_NAME</code><br><br>Examples:<br>`tag add 1 -m CS2113T project`                                                                                                                                                                                                                                                |
|     [list](#48-listing-all-tasks-list)     | `list [TAG_NAME]`<br><br>Examples:<br>`list`<br>`list project`                                                                                                                                                                                                                                                                                                |
| [grade](#49-setting-a-modules-grade-grade) | `grade MODULE_CODE MODULE_GRADE`<br><br>Examples:<br>`grade CS2113T A+`                                                                                                                                                                                                                                                                                       |
|        [gpa](#410-viewing-gpa-gpa)         | `gpa`                                                                                                                                                                                                                                                                                                                                                         |
| [reset](#411-resetting-the-program-reset)  | `reset`                                                                                                                                                                                                                                                                                                                                                       |
|     [save](#412-saving-your-data-save)     | `save`                                                                                                                                                                                                                                                                                                                                                        |