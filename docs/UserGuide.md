# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have _Java 11_ or above installed. The link to _Java 11_ installer is [here](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html).
2. Download the latest version of `Mod Happy` from [here](http://link.to/duke).
3. Copy the jar file into an empty folder.
4. Open a terminal on your laptop, and go to the working directory where the file is saved.
5. Run the command  `java -jar tp.jar` to start the program.
6. You can now enter different commands.

## Features

Note:<br>
Compulsory flags start with "/". <br>
Optional flags start with "-". <br>
Compulsory parameters are fully capitalised. E.g. MODULE_CODE <br>
Optional parameters are in square brackets. E.g. [-m "MODULE_DESCRIPTION"] <br>
All parameters except MODULE_CODE are surrounded by double quotation marks. E.g. "PARAMETER"

### Accessing Help: `help`

### Adding a task/module: `add`

Adds an object as indicated by the command argument.

- **Add module: `add /m`**
  
  Adds a module to the list of modules tracked by Mod Happy. You have to indicate the number of modular credits and optionally specify a short description for the module.<br>
  > ⚠ <span style="color:#ffa500">**IMPORTANT:**</span>
  >
  > The module code must be a single word, and can only consist of alphanumeric characters as well as the underscore `_`.

  Format: `add /m MODULE_CODE MODULAR_CREDITS [-d "MODULE_DESCRIPTION"]`<br><br>
  Example: `add /m CS2113T 4 -d "Software Engineering"`<br><br>
  
  > 📔 <span style="color:#00bb00">**NOTE:**</span>
  > 
  > Module codes are case-sensitive. Mod Happy treats `CS2113T` and `cs2113t` as two different modules!
- **Add task: `add /t`**
  
  Adds a task to the list of tasks tracked under the specified module code. If no module code is supplied, the task is added to the General Tasks list, which is not associated with any module.<br><br>

  You may optionally specify a short description for the task, as well as the estimated for the time to be spent working on it.<br><br>

  Format: `add /t "TASK_NAME" [-d "TASK_DESCRIPTION"] [-t “ESTIMATED_WORKING_TIME”] [-m MODULE_CODE]`<br><br>
  Example to add a general task without any parameters: `add /t "Review PR"`<br>
  Example to add a module task with parameters: `add /t "iP Level-0" -d "Greet user and exit" -t "1 hour" -m CS2113T`
<br><br>
  Note: Adding tasks with parameters must be in the order (-d, -t, -m), omitting any flags you are not adding.<br><br>

### Deleting a task/module: `del`

Deletes an object as indicated by the command argument.

- Delete a module <br><br>
  Format: `del /m MODULE_CODE`<br><br>
  Example to delete a module: `del /m CS2113T`<br><br>
- Delete a task <br><br>
  Format: `del /t TASK_NUMBER [-m MODULE_CODE]`<br><br>
  Example to delete a general task: `del /t 1`<br>
  Example to delete a module task: `del /t 1 -m CS2113T`<br><br>

### Editing a task/module: `edit`

Edits an object's parameter as indicated by the command arguments.<br>
For a module, you are able to change its description only.<br>
For a task, you are able to change its name, description, or estimated working time.

- Edit a module description <br><br>
  Format: `edit /m MODULE_CODE -d "MODULE_DESCRIPTION"` <br><br>
  Example to edit a module description: `edit /m CS2113T -d "Software Engineering & OOP"`<br><br>
- Edit a task parameter <br><br>
  Format: `edit /t TASK_INDEX (-n "TASK_NAME" or -d "TASK_DESCRIPTION" or -t "ESTIMATED_WORKING_TIME") [-m MODULE_CODE]`
  <br><br>
  Example to edit a task parameter: `edit /t 1 -n "CS2113T Tutorial 2" -m CS2113T` <br><br>
  Note: You can only edit one task parameter per command. <br>
  Not allowed: `edit /t 2 -n "CS2113T Tutorial 1" -d "Draw class diagram" -m CS2113T`<br><br>

### Marking a task: `mark`

Mark a task as completed or uncompleted with the given task number from the specified module. If no module code is given, the task to be marked will be drawn from the “general tasks” list.

- Mark a task as completed <br><br>
  Format: `mark /c TASK_INDEX [-m MODULE_CODE]` <br><br>
  Example to mark a general task as completed: `mark /c 1`<br>
  Example to mark a module task as completed: `mark /c 1 -m CS2113T`<br><br>
- Mark a task as uncompleted <br><br>
  Format: `mark /u TASK_INDEX [-m MODULE_CODE]` <br><br>
  Example to mark a general task as uncompleted: `mark /u 1`<br>
  Example to mark a module task as uncompleted: `mark /u 1 -m CS2113T`<br><br>

### Clearing the list: `reset`

If a tag name is provided, only tasks with the associated tag will be shown.

Format: `list ["TAG_NAME"]`

### Adding/Changing a grade to a module: `grade`

Adds/Changes a grade to a module specified by its module code.

Format: `grade /m MODULE_CODE MODULE_GRADE`

> 📔 <span style="color:#00bb00">**NOTE:**</span>
>
> Only the following grades are supported (case-insensitive):<br>
> A+, A, A-, B+, B, B-, C+, C, D+, D, F, S, U, CS, CU

Example: `grade /m CS2113T A+`

### Resetting the program: `reset`

Removes all tasks and modules.

Format: `reset`

### Saving the list: `save`

Saves all tasks and modules.

Format: `save`

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: {your answer here}

## Command Summary
<br>

| Command | Format                                                                                                                                                                               |
|:-------:|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|  help   | `help [COMMAND_WORD]`                                                                                                                                                                |
|   add   | `add /m MODULE_CODE [-d "MODULE_DESCRIPTION"]`<br>`add /t "TASK_NAME" [-d "TASK_DESCRIPTION"] [-t “ESTIMATED_WORKING_TIME”] [-m MODULE_CODE]`                                        |
|   del   | `del /m MODULE_CODE`<br>`del /t TASK_NUMBER [-m MODULE_CODE]`                                                                                                                        |
|  edit   | <code>edit /t TASK_INDEX (-n "TASK_NAME" &#124; -d "TASK_DESCRIPTION" &#124; -t "ESTIMATED_WORKING_TIME") [-m MODULE_CODE]</code> <br> `edit /m MODULE_CODE -d "MODULE_DESCRIPTION"` |
|  mark   | <code>mark (/c &#124; /u) TASK_NUMBER [-m MODULE_CODE]</code>                                                                                                                        |
|   tag   | <code>tag (add &#124; del) [-m MODULE_CODE] "TAG_NAME"</code>                                                                                                                        |
|  list   | `list ["TAG_NAME"]`                                                                                                                                                                  |
|  grade  | `grade /m MODULE_CODE MODULE_GRADE`                                                                                                                                                  |
|  reset  | `reset`                                                                                                                                                                              |
|  save   | `save`                                                                                                                                                                               |

