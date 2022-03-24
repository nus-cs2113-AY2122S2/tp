# User Guide

## Introduction

Mod Happy is a command line application designed to help you manage your academic matters in a student-friendly manner. It can track your outstanding tasks, categorise them according to modules and other user-created tags, and even help to perform GPA or CA% computations for you.

## Quick Start

1. Ensure that you have _Java 11_ or above installed. The link to _Java 11_ installer is [here](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html).
2. Download the latest version of `Mod Happy` from [here](https://github.com/AY2122S2-CS2113T-T10-3/tp/releases).
3. Copy the JAR file into an empty folder.
4. Open a terminal on your laptop, and go to the working directory where the file is saved.
5. Run the command  `java -jar tp.jar` to start the program.

## About this user guide

### Explanation of notation

- User-supplied input parameters are indicated by fully capitalised field names. For instance, in `del mod MODULE_CODE`, you would replace `MODULE_CODE` with the module code of the module you wish to delete (e.g. `del mod CS2113T`).
- When multiple parameters are indicated within round brackets and separated with `|`, exactly one parameter must be chosen. For example, `mark (c | u)` means either one of `mark c` or `mark u`.
- Parameters indicated within square brackets, such as `[-m "MODULE_DESCRIPTION"]`, are optional. The part of the command enclosed within these brackets may be omitted if you wish.
> 📔 <span style="color:#00bb00">**NOTE:**</span>
> 
> Pay special attention to whether input parameters are surrounded by double quotes. Missing or unnecessary double quotes will likely result in Mod Happy not understanding your command.

> ⚠ <span style="color:#ffa500">**IMPORTANT:**</span>
> 
> All parameters, including optional ones, must appear in the same order they are given in the command format provided for each command. Mod Happy may not understand your command if you specify these parameters in a different order.

## Features

### Accessing help: `help`

Displays help for the indicated command. If no command word is supplied, a generic help message is shown.

Format: `help [COMMAND_WORD]`

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

### Deleting a task/module: `del`

Deletes an object as indicated by the command argument.

- **Delete module: `del mod`**

  Deletes the specified module from the list of modules tracked by Mod Happy.<br><br>
  Format: `del mod MODULE_CODE`<br><br>
  Example: `del mod CS2113T`<br><br>
- **Delete task: `del task`**

  Deletes the task with the specified number from the list of tasks associated with the provided module code. If no module code is provided, the task is removed from the General Tasks list.<br><br>
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

  The task to be edited is specified using its task number and associated module code; if no module code is specified, the task is drawn from the General Tasks list. The task name, description, and estimated working time are editable, but the task cannot be associated with a different module.<br><br>
  Format: `edit task TASK_NUMBER [-m MODULE_CODE] (-n "TASK_NAME" | -d "TASK_DESCRIPTION" | -t "ESTIMATED_WORKING_TIME")`
  <br><br>
  Example: `edit task 1 -m CS2113T -n "CS2113T Tutorial 2"` <br><br>
  > 📔 <span style="color:#00bb00">**NOTE:**</span>
  >
  > Only one parameter can be edited per command. The following input is not allowed:
  >
  > `edit task 2 -m CS2113T -n "CS2113T Tutorial 1" -d "Draw class diagram"`

### Marking a task: `mark`

Marks the specified task as completed or uncompleted. The task to be marked is specified using its task number and associated module code; if no module code is specified, the task is drawn from the General Tasks list.

The `c` flag indicates that the task will be marked as completed, while the `u` flag marks the task as uncompleted.

Format: `mark (c | u) TASK_INDEX [-m MODULE_CODE]`<br><br>
Example (mark general task as completed): `mark c 1`<br>
Example (mark module task as uncompleted): `mark u 1 -m CS2113T`

### Managing custom tags: `tag`

Adds or deletes a tag from the specified task. This task is specified using its task number and associated module code; if no module code is specified, the task is drawn from the General Tasks list.

> ⚠ <span style="color:#ffa500">**IMPORTANT:**</span>
>
> The tag name must be a single word; it cannot contain whitespace.

Format: `tag (add | del) TASK_INDEX [-m MODULE_CODE] "TAG_NAME"`

Example: `tag add 1 -m CS2113T "project"`

### Listing all tasks/modules: `list`

Displays a list of all tasks, grouped by module code. General tasks are displayed separately.

If a tag name is provided, only tasks with the associated tag will be shown.

Format: `list ["TAG_NAME"]`

### Adding/Changing a grade to a module: `grade`

Adds/Changes a grade to a module specified by its module code.

Format: `grade MODULE_CODE MODULE_GRADE`

> 📔 <span style="color:#00bb00">**NOTE:**</span>
>
> Only the following grades are supported (case-insensitive):<br>
> A+, A, A-, B+, B, B-, C+, C, D+, D, F, S, U, CS, CU

Example: `grade CS2113T A+`

### Resetting the program: `reset`

Removes all tasks and modules.

Format: `reset`

### Saving the list: `save`

Saves all tasks and modules to the data file.

Format: `save`

> ⚠ <span style="color:#ffa500">**IMPORTANT:**</span>
>
> Mod Happy does **not** auto-save your changes! Do remember to save your work at regular intervals, or before exiting the program.

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: Your task and module data are stored in the `data` folder, located in the same folder as Mod Happy's JAR file. To transfer data to another computer, simply copy this folder to the new machine. Make sure to place the folder in the same location as the Mod Happy application itself!

## Command Summary

| Command | Format                                                                                                                                                                                   |
|:-------:|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|  help   | `help [COMMAND_WORD]`                                                                                                                                                                    |
|   add   | `add mod MODULE_CODE MODULAR_CREDITS [-d "MODULE_DESCRIPTION"]`<br>`add task "TASK_NAME" [-m MODULE_CODE] [-d "TASK_DESCRIPTION"] [-t “ESTIMATED_WORKING_TIME”]`                         |
|   del   | `del mod MODULE_CODE`<br>`del task TASK_NUMBER [-m MODULE_CODE]`                                                                                                                         |
|  edit   | <code>edit task TASK_NUMBER [-m MODULE_CODE] (-n "TASK_NAME" &#124; -d "TASK_DESCRIPTION" &#124; -t "ESTIMATED_WORKING_TIME")</code> <br> `edit mod MODULE_CODE -d "MODULE_DESCRIPTION"` |
|  mark   | <code>mark (c &#124; u) TASK_NUMBER [-m MODULE_CODE]</code>                                                                                                                              |
|   tag   | <code>tag (add &#124; del) [-m MODULE_CODE] "TAG_NAME"</code>                                                                                                                            |
|  list   | `list ["TAG_NAME"]`                                                                                                                                                                      |
|  grade  | `grade MODULE_CODE MODULE_GRADE`                                                                                                                                                         |
|  reset  | `reset`                                                                                                                                                                                  |
|  save   | `save`                                                                                                                                                                                   |
