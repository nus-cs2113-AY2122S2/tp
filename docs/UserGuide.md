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
Optional parameters are in square brackets. E.g. [-m "MODULE_DESCRIPTION"]
All parameters except MODULE_CODE are surrounded by double quotation marks. E.g. "PARAMETER"

### Accessing Help: `help`

### Adding a task/module: `add`

Adds an object as indicated by the command argument. <br>
A module can have its description while a task can have its description and/or its estimated working time.

- Add a module <br>
  Format: `add /m MODULE_CODE [-d "MODULE_DESCRIPTION"]`<br><br>
  Example to add a module: `add /m CS2113T -d "Software Engineering"`<br><br>
  Note: The module code should not have any spaces or special characters.<br><br>
- Add a task <br>
  Format: `add /t "TASK_NAME" [-d "TASK_DESCRIPTION"] [-t “ESTIMATED_WORKING_TIME”] [-m MODULE_CODE]`<br><br>
  Example to add a general task without any parameters: `add /t "Review PR"`<br>
  Example to add a module task with parameters: `add /t "iP Level-0" -d "Greet user and exit" -t "1 hour" -m CS2113T`
<br><br>
  Note: Adding tasks with parameters must be in the order (-d, -t, -m), omitting any flags you are not adding.<br>

### Deleting a task/module: `del`

Deletes an object as indicated by the command argument.

- Delete a module <br><br>
  Format: `del /m MODULE_CODE`<br><br>
  Example to delete a module: `del /m CS2113T`<br><br>
- Delete a task <br><br>
  Format: `del /t TASK_NUMBER [-m MODULE_CODE]`<br><br>
  Example to delete a general task: `del /t 1`<br>
  Example to delete a module task: `del /t 1 -m CS2113T`<br>

### Editing a task/module: `edit`

Edits an object's parameter as indicated by the command arguments.<br>
For a module, you are able to change its description only.<br>
For a task, you are able to change its name, description, or estimated working time.

- Edit a module description <br>
  Format: `edit /m MODULE_CODE -d "MODULE_DESCRIPTION"` <br><br>
  Example to edit a module description: `edit /m CS2113T -d "Software Engineering & OOP"`<br><br>
- Edit a task parameter <br>
  Format: `edit /t TASK_INDEX (-n "TASK_NAME" or -d "TASK_DESCRIPTION" or -t "ESTIMATED_WORKING_TIME") [-m MODULE_CODE]`
  <br><br>
  Example to edit a task parameter: `edit /t 1 -n "CS2113T Tutorial 2" -m CS2113T` <br><br>
  Note: You can only edit one task parameter per command. <br>
  Not allowed: `edit /t 2 -n "CS2113T Tutorial 1" -d "Draw class diagram" -m CS2113T`<br>

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

### Listing all tasks/modules: `list`

Displays a list of all tasks, some of which are grouped by module code while the rest fall under "general tasks" list.

Format: `list`

Example: `list`

### Clearing the list: `reset`

Removes all tasks and modules. <br>
Format: `reset`

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: {your answer here}

## Command Summary
<br>

| Command | Format                                                                                                                                                             |
|:-------:|--------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|  help   | `help`                                                                                                                                                             |
|   add   | `add /m MODULE_CODE [-d "MODULE_DESCRIPTION"]`</br>`add /t "TASK_NAME" [-d "TASK_DESCRIPTION"] [-t “ESTIMATED_WORKING_TIME”] [-m MODULE_CODE]`                     |
|   del   | `del /m MODULE_CODE del /t TASK_NUMBER [-m MODULE_CODE]`                                                                                                           |
|  edit   | `edit /t TASK_INDEX (-n "TASK_NAME" or -d "TASK_DESCRIPTION" or -t "ESTIMATED_WORKING_TIME") [-m MODULE_CODE]` </br> `edit /m MODULE_CODE -d "MODULE_DESCRIPTION"` |
|  mark   | `mark /c TASK_NUMBER [-m MODULE_CODE]`</br>`mark /u TASK_NUMBER [-m MODULE_CODE]`                                                                                  |
|  list   | `list`                                                                                                                                                             |
|  reset  | `reset`                                                                                                                                                            |

