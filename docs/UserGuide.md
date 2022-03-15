# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features

Note:</br>
Compulsory Flags start with "/". </br>
Optional Flags start with "-". </br>
Compulsory Parameters are fully capitalised: e.g. MODULE_CODE. </br>
Optional Parameters are in square brackets: e.g. [-m "MODULE_DESCRIPTION"]
All parameters except MODULE_CODE are surrounded by double quotation marks e.g. "PARAMETER".

### Accessing Help: `help`

### Adding a task/module: `add`

### Deleting a task/module: `del`

Deletes an object as indicated by the command argument.

- Delete a module </br>
  Format: `del /m MODULE_CODE`</br></br>
  Example to delete a module: `del /m CS2113T`</br></br>
- Delete a task </br>
  Format: `del /t TASK_NUMBER [-m MODULE_CODE]`</br></br>
  Example to delete a general task: `del /t sleep later`</br>
  Example to delete a module task: `del /t review pr -m CS2113T`</br>

### Editing a task/module: `edit`

### Marking a task: `mark`

### Listing all tasks/modules: `list`

### Clearing the list: `reset`

Removes all tasks and modules. </br>
Format: `reset`

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: {your answer here}

## Command Summary
| Command | Format                                                                                                                                                             |
|:-------:|--------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|  help   | `help`                                                                                                                                                             |
|   add   | `add /m MODULE_CODE [-d "MODULE_DESCRIPTION"]`</br>`add /t "TASK_NAME" [-d "TASK_DESCRIPTION"] [-t “ESTIMATED_WORKING_TIME”] [-m MODULE_CODE]`                     |
|   del   | `del /m MODULE_CODE del /t TASK_NUMBER [-m MODULE_CODE]`                                                                                                           |
|  edit   | `edit /t TASK_INDEX (-n "TASK_NAME" or -d "TASK_DESCRIPTION" or -t "ESTIMATED_WORKING_TIME") [-m MODULE_CODE]` </br> `edit /m MODULE_CODE -d "MODULE_DESCRIPTION"` |
|  mark   | `mark /c TASK_NUMBER [-m MODULE_CODE]`</br>`mark /u TASK_NUMBER [-m MODULE_CODE]`                                                                                  |
|  list   | `list`                                                                                                                                                             |
|  reset  | `reset`                                                                                                                                                            |
