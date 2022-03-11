# WerkIt! User Guide

## Introduction

{Give a product intro}

---

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of WerkIt! from [here (WIP)](http://link.to/duke).
3. (Recommended) Create a new directory and move the WerkIt! JAR file to the directory.
4. (Recommended) Set your current working directory to the new directory.
5. Run the WerkIt! application with the command: `java -jar WerkIt.jar`.

---

## Features 

> **Heads up!** Your inputs cannot contain the pipe character `|`!

### Create a Workout: `workout /new`
Creates a new workout.

Format: `workout /new <exercise name> /reps <number of repetitions>`

| Parameters | Description                                                                                                                                                      |
| --- |------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `<exercise name>` | The name of the exercise. <ul><li>Exercise name must be from the pre-defined list of exercises</li><li>Type `exercise /list` for the list of exercises</li></ul> |
| `<number of repetitions>` | The number of repetitions that should be carried out for `<exercise name>`.                                                                                      |

**Example**
```
> workout /new russian twist /reps 50
```
**Expected Outcome**
```
----------------------------------------------------------------------
Alright, the following workout has been created:

	russian twist (50 reps)

----------------------------------------------------------------------
```
A new workout of carrying out Russian twists 50 times will be created and added to the application's list of workouts.

### Adding a todo: `todo`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

---

## WerkIt!'s Local Storage Information
When you start WerkIt! for the first time, the following directory and folders are created:

```
werkItResources/        // Primary resource directory for WerkIt!
    ├── exercises.txt   // Text file containing a list of exercises
    └── workouts.txt    // Text file containing a list of user-created workouts
```

Do note that the directory (and by extension, the files) will be created in your terminal's
**current working directory**. Thus, it is highly recommended for you to create a new directory
with the WerkIt! JAR file inside it, and running the application from the directory.

When you launch WerkIt! in subsequent sessions, please ensure that you run it from the same directory
that you did when starting WerkIt! for the first time.

---

## Frequently Asked Questions (FAQ)

**Q**: How do I transfer my data to another computer? 

**A**: Locate your `werkItResources` directory and WerkIt! JAR file, copy and paste it in your other 
computer's desired location. Thereafter, you can run WerkIt! as per normal. 
- (Please see 'WerkIt's Local Storage Information' for recommended location).

<br/>

**Q**: I cannot see the workouts that I have created in previous sessions.

**A**: Please ensure that you launch WerkIt! from the same directory that you did when starting the application for the 
first time. Your saved data should be in that directory.

<br/>

---

## Command Summary

{Give a 'cheat sheet' of commands here}

| Action         | Format and Example                                                                                                   |
|:---------------|:---------------------------------------------------------------------------------------------------------------------|
| Create Workout | Format: `workout /new <exercise name> /reps <number of repetitions>`<br/>Example: `workout /new biceps curl /reps 1` |


