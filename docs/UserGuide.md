# WerkIt! User Guide

## Introduction

WerkIt! is an application for managing workout routines,
optimized for use via a Command Line Interface (CLI).
---

* Table of Contents
  {:toc}

## Quick Start Guide

1. Ensure that you have [Java 11](https://www.oracle.com/java/technologies/downloads/) or above installed.
2. Download the latest version of WerkIt! from [here](https://github.com/AY2122S2-CS2113T-T09-2/tp/releases).
3. (Recommended) Create a new directory and move the WerkIt! JAR file to that directory.
4. (Recommended) Set your current working directory to the new directory.
5. Run the WerkIt! application with the command: `java -jar WerkIt.jar`.

---

## Features 

> **Heads up!** Your inputs cannot contain the pipe character `|`!

### Create a Workout: `workout /new`
Creates a new workout.

Format: `workout /new <exercise name> /reps <number of repetitions>`

| Parameters | Description                                                                                                                                                       |
| --- |-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `<exercise name>` | The name of the exercise. <ul><li>Exercise name must be from the pre-defined list of exercises.</li><li>Type `exercise /list` for the list of exercises</li></ul> |
| `<number of repetitions>` | The number of repetitions that should be carried out for `<exercise name>`.                                                                                       |

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

---
### Show all Workouts: `workout /list`
Lists down all workouts that have been created and stored in the workout list.

Format: `workout /list`

**Example**
```
> workout /list
```
**Expected Outcome**
```
------------------------------------------------------------
Showing workouts 1-3 of 3:
1. push up (10 reps)
2. sit up (10 reps)
3. pull up (10 reps)
Showed all workouts in list
------------------------------------------------------------
```

In the current workout list, there are a total of 3 workouts. All the workouts have been listed. 

---

### Delete a Workout: `workout /delete`
Removes an existing workout from the workout list.

Format: `workout /delete <workout number to delete>`

| Parameters                   | Description                                             |
|------------------------------|---------------------------------------------------------|
| `<workout number to delete>` | The workout number to be removed from the workout list. |

**Example**
<br/>Initially, the workout list contains the following workouts:
```
----------------------------------------------------------------------
Showing workouts 1-2 of 2:
1. russian twist (50 reps)
2. push up (20 reps)
Showed all workouts in list
----------------------------------------------------------------------
```
To remove workout number 2 from the list, the following command is entered:
```
> workout /delete 2
```
**Expected Outcome**
```
----------------------------------------------------------------------
Alright, the following workout has been removed:

	push up (20 reps)

----------------------------------------------------------------------
What's next?
----------------------------------------------------------------------
```
Workout number 2 (push up with 20 reps) is removed from the workout list. 
Running the `workout /list` command will show the workout list as follows:
```
----------------------------------------------------------------------
Showing workouts 1-1 of 1:
1. russian twist (50 reps)
Showed all workouts in list
----------------------------------------------------------------------
```
---

### Update a Workout: `workout /update`
Modifies an existing workout from the workout list.<br>
*Noted: Only the number of repetition can be changed.*

Format: `workout /update <index of workout to update> <new number of repetitions>`

| Parameters                     | Description                                               |
|--------------------------------|-----------------------------------------------------------|
| `<index of workout to update>` | The index of workout to be updated from the workout list. |
| `<new number of repetitions>`  | The number of repetitions to be changed to.               |

**Example**<br>
Initially, the workout list contains the following workouts:
```
----------------------------------------------------------------------
Showing workouts 1-3 of 3:
1. push up (10 reps)
2. sit up (10 reps)
3. pull up (10 reps)
Showed all workouts in list
----------------------------------------------------------------------
```
To update workout with index 1 to 15 reps, enter the following command:
```
> workout /update 1 15
```
**Expected Outcome**
```
----------------------------------------------------------------------
Alright, the following workout has been updated:

	push up (15 reps)

----------------------------------------------------------------------
```
Now the workout with index 1 (push up with 10 reps) is updated to push up with 15 reps.
Now running the `workout /list` command again will show the workout list as follows:
```
----------------------------------------------------------------------
Showing workouts 1-3 of 3:
1. push up (15 reps)
2. sit up (10 reps)
3. pull up (10 reps)
Showed all workouts in list
----------------------------------------------------------------------
```
---

## WerkIt!'s Local Storage Information
When you start WerkIt! for the first time, the following directory and files are created:

```
werkItResources/        // Primary resource directory for WerkIt!
    ├── exercises.txt   // Text file containing a list of exercises
    └── workouts.txt    // Text file containing a list of user-created workouts
```

Do note that the directory (and by extension, the files) will be created in your terminal's
**current working directory**. Thus, it is highly recommended for you to create a new directory
with the WerkIt! JAR file inside it, and run the application from the directory.

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

| Action         | Format and Example                                                                                                         |
|:---------------|:---------------------------------------------------------------------------------------------------------------------------|
| Create Workout | Format: `workout /new <exercise name> /reps <number of repetitions>`<br/>Example: `workout /new biceps curl /reps 1`       |
| Delete Workout | Format: `workout /delete <workout number to delete>`<br/>Example: `workout /delete 2`                                      |
| Update Workout | Format: `workout /update <index of workout to be updated> <new number of repetitions>`<br/>Example: `workout /update 1 15` |

