# User Guide

## Introduction

CSProj Planner is a desktop app to help Computer Science students manage their projects. However, while being optimized for CS students, the app will still be useful to any student looking to easily keep track of all their upcoming projects. Users are able to add and delete projects at will, as well as view the tasks needed to be done for their submissions.

## Contents

- [Quick Start](#quick-start)
- [Features](#features)
   - [add a project](#add-a-project-addproject)
   - [delete a project](#delete-a-project-deleteproject)
   - [print all projects](#print-all-projects-listprojects)
   - [add todo to a project](#add-todo-to-a-project-todo)
   - [mark todo as done](#mark-todo-as-done-mark)
   - [set the deadline to a project](#set-the-deadline-to-a-project-projdeadline)
   - [change the GitHub repo of a project](#change-the-GitHub-repo-of-a-project)
   - [open the GitHub repo of a project](#open-the-GitHub-repo-of-a-project)
   - [set the deadline to a todo](#set-the-deadline-to-a-todo-tododeadline)
   - [add language to a project](#add-language-to-a-project-addlanguage)
   - [list languages of a project](#list-languages-of-a-project-listlanguages)
   - [view](#view-details-of-a-project-view)
   - [exit](#exit-exit)
   
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Quick Start

- Ensure you have Java  `11` or above installed in your computer.
- Ensure you have downloaded our latest version.
- Copy the file to your preferred folder as the home folder for your CSProj Planner.
- Double-click the file to start the app.
- Type your command in the command box and press Enter to execute it. Some example commands you can try:
   - `addproject cs2113`: Add a new project named cs2113 to the planner.
   - `listproject`: list out all projects in the list.  
   - `exit`: Exits the project.
- The data will be automatically saved after the program exited safely.


## Features 

### Add a project: ```addproject```
Add a project to your current list of projects

Format: `addproject [project_name]`
*The `project_name` can be of any length, number of words, and any types of characters.  
*The `project_name` cannot be pure whitespace.


#### Example of usage
```
addproject project1
```

#### Example of output
```
project1 added.
```

---
### Delete a project: `deleteproject`
Delete a project from your current list of projects

Format: `deleteproject [project_name]`
*The `project_name` must be a match for a current project already in your list of projects

#### Example of usage
```
deleteproject project1
```

#### Example of output
```
project1 deleted.
```

---
### Print all projects: `listprojects`
Prints all projects in the list with their names and project deadlines

Format: `listprojects` or `listproject`

* The `listprojects`(`listproject`) cannot contain a space, i.e., `list projects`(`list project`) will be considered an unknown command.  

---
### Add todo to a project: `todo`
Adds a todo task with description to a project

Format: `todo [project_index] [description]`
* The `project_index` must be a positive integer.

#### Example of usage
```
todo 1 buy textbooks
```

#### Example of output
```
Todo:  buy textbooks have been added to project pro1
```

---
### Mark todo as done: `mark`

Format: `mark [project_index] [todo_index]`
* The `project_index` and `todo_index` must be positive integers.

#### Example of usage
```
mark 1 1
```

#### Example of output
```
Todo has been marked as done successfully: 
[X] buy textbooks
```
---
### Set the deadline to a project: `projdeadline`
Adds a deadline date to a project

Format: `projdeadline [project_index] [deadline]`
* The `project_index` must be a positive integer.
* deadline must be entered in the following format "yyyy-mm-dd"
* Alternatively, entering a day of the week also works (i.e. Thursday)

#### Example of usage  
```
projdeadline 1 2022-04-03
```

#### Example of output  
```
Deadline added to project1: 2022-04-03
```
---
### Change the GitHub repo of a project
Changes the GitHub repository link for your project

Format: `changegit [project_index] [github_URL]`
* The `project_index` must be a positive integer.
* Every project comes with the default github repo "http://github.com"
* When entering your github_URL, it must begin with either http:// or https://

#### Example of usage
```
changegit project1 http://github.com/project1
```

---
### Open the GitHub repo of a project
Opens the GitHub repository in your default browser

Format: `opengit [project_name]`

#### Example of usage
```
opengit project1
```

---
### Set the deadline to a todo: `tododeadline`
Adds a deadline date to a todo

Format: `tododeadline [project_index] [todo_index] [deadline]`
* The `project_index` and `todo_index` must be positive integers.
* deadline must be entered in the following format "yyyy-mm-dd"
* Alternatively, entering a day of the week also works (i.e. Thursday)

#### Example of usage
```
tododeadline 1 1 2022-05-22
```

#### Example of output
```
Deadline added to Complete Diagrams: 2022-05-22
```

---
### View details of a project: `view`
View details of a project: Name, deadline, todos

Format: `view [project_name]`

#### Example of usage
```
view cs2113
```

#### Example of output
```
Project Name: cs2113
Deadline: 2022-03-17
	[1]. [X] complete addproject command
	[2]. [ ] complete deleteproject command
```
---
### Add language to a project: `addlanguage`  
Adds language to a project  

Format: `addlanguage [project_index] [language]`  
* The `project_index` must be positive integers.

#### Example of usage
```
addlanguage 1 java
```

#### Example of output 
```
java language added.
```

---
### List languages of a project: `listlanguages`
Lists languages of a project  
Format: `listlanguages [project_title]`

#### Example of usage
```
listlanguages cs2113
```  

#### Example of output
```
Programming languages for cs2113:
	[1]. java
```
---
### Help: `help`
List out all available commands

Format: `help`

---
### Exit: `exit`
Exit the program

Format: `exit`

---
## FAQ

**Q**: How do I transfer my data to another computer?

**A**: Copy the data file named `projectList.txt` through `your working directory/src/data/`, then send it to another computer.

If you want to use this data on another computer, please follow any of the below options:
- Option 1: You can firstly download your data and save the file as `projectList.txt`. Then create 2 new folders `src/data` under the directory where you prefer to work. You will be able to see your data when you start the app.
- Option 2: You can first start the app on another computer. You will see the new folder generated under your working directory after the program exits. Replace the `projectList` file with the file you want to transfer under the path `./src/data/`.

Please note that the file name should not be changed.

## Command Summary

| Action              | Format                                   |
|---------------------|------------------------------------------|
| add project         | `addproject [project_name]`              |
| delete project      | `deleteproject [project_name]`           |
| view all project(s) | `listproject`, `listprojects`            |
| add todo            | `todo [project_index] [description]`     |
| mark todo as done   | `mark [project_index] [todo_index]`      |
| add project deadline| `projdeadline [project_index] [deadline]`|
| change GitHub       | `changegit [project_index] [github_URL]` |
| open GitHub         | `opengit [project_name]`                 |
| add todo deadline   | `tododeadline [project_index] [todo_index] [deadline]`|
| view a project      | `view [project_name]`                    |
| addlanguage	      | `addlanguage [project_index] [language]` |
| listlanguages	      | `listlanguages`                          |
| Exit                | `exit`                                   |
