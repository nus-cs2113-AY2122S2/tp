# WerkIt! User Guide

## Introduction

WerkIt! is an application for managing workout routines,
optimized for use via a Command Line Interface (CLI). This application is for you if you wish to
have an application to keep track of your workouts and have a system to store your workout routines.

You will be able to create, view, update and delete the workouts and plans you have created and store them in the application.
You will also be able to schedule your workout plans for a week (7 days). 

### Terminology 
Please take note of these few terms. It is explained as below:

| Term     | Description                                                                                                              |
|----------|--------------------------------------------------------------------------------------------------------------------------|
| Exercise | A single unit of exercise. <br/>Example: "push up", "pull up", "sit up"                                                  |
| Workout  | An exercise with a quantity/number of repetitions. <br/>Example: "5 push up", "5 pull up", "10 sit up"                   |
| Plan     | A set of workouts. <br/>Example: A plan named "Arms" will contains "5 push ups, 5 pull ups"                              |
| Schedule | Plan schedule for a day. The schedule is set for a week. <br/>Example: Arms plan will be added to Monday in the schedule |

---

## Table of Contents
* [Quick Start Guide](#quick-start-guide)
* [Features](#features)
  * [Exercise And Workout Features](#exercise-and-workout-features)
    * [Show All Exercises: `exercise /list`](#show-all-exercises-exercise-list)
    * [Create A Workout: `workout /new`](#create-a-workout-workout-new)
    * [Show All Workouts: `workout /listall`](#show-all-workouts-in-one-go)
    * [Show Specific Number Of Workouts: `workout /list`](#show-specific-number-of-Workouts-workout-list)
    * [Delete A Workout: `workout /delete`](#delete-a-workout-workout-delete)
    * [Update A Workout: `workout /update`](#update-a-workout-workout-update)
  * [Plan Features](#plan-features)
    * [Create A Plan: `plan /new`](#create-a-plan-plan-new)
    * [List A Plan: `plan /list`](#list-a-plan-plan-list)
    * [Show Details Of Plan]
  * [Schedule Features](#schedule-features)
    * [Update Schedule: `schedule /update`](#update-schedule-schedule-update)
    * [View Schedule: `schedule /list`](#view-schedule-schedule-list)
    * [Clear Schedule For A Day: `schedule /clear`](#clear-schedule-for-a-day-schedule-clear)
    * [Clear Schedule For The Week: `schedule /clearall`](#clear-schedule-for-the-week-schedule-clearall)
  * [Search Features](#search-features)
    * [Search For Exercise: `search /exercise`](#search-for-exercise-search-exercise)
    * [Search For Workout: `search /workout`](#search-for-workout-search-workout)
    * [Search For Plan: `search /plan`](#search-for-plan-search-plan)
    * [Search For All: `search /all`](#search-for-all-search-all)
  * [View Help: `help`](#view-help-help)
* [WerkIt!'s Local Storage Information](#werkits-local-storage-information)
* [Frequently Asked Questions (FAQ)](#frequently-asked-questions-faq)
* [Command Summary](#command-summary)

## Quick Start Guide

1. Ensure that you have [Java 11](https://www.oracle.com/java/technologies/downloads/) or above installed.
2. Download the latest version of WerkIt! from [here](https://github.com/AY2122S2-CS2113T-T09-2/tp/releases).
3. (Recommended) Create a new directory and move the WerkIt! JAR file to that directory.
4. (Recommended) Set your current working directory to the new directory.
5. Run the WerkIt! application with the command: `java -jar WerkIt.jar`.

---
## Features 
In this section, features of the application can be classified into 4 main categories. They are: <br>
* [Exercise and Workout Features](#exercise-and-workout-features)
* [Plan Features](#plan-features) 
* [Schedule Features](#schedule-features)
* [Search Features](#search-features)

Each feature is being run by a specific command format. When the command format is matched, 
the command will then be processed by the application and produce the intended outcome. The format of the commands
contains two types of formatting.

| Format         | Parameters                                                                                                                               |
|----------------|------------------------------------------------------------------------------------------------------------------------------------------|
| `<condition>`  | Contents enclosed between "<>" are the parameters needed for the command to be valid. <br /> Not all commands have conditions to be met. |
| `/commandType` | Content after "/" is to classify the command type. <br/> Such as classifying it to be a workout/ plan/ schedule/ search command          |



> :exclamation: **Heads up!** Your inputs cannot contain the pipe character `|`!

---
## Exercise And Workout Features

### Show all Exercises: `exercise /list`
Lists down all exercises that are available for selection.

Format: `exercise /list`

**Example**
```
> exercise /list
```
**Expected Outcome**
```
------------------------------------------------------------
There are 13 exercises available.
Here is the list of exercises: 
------------------------------------------------------------
1. push up
2. bicep curl
3. pull up
4. squat
5. lunge
6. hip thrust
7. sit up
8. crunch
9. russian twist
10. running
11. swimming
12. jumping jack
13. burpee
------------------------------------------------------------
End of exercise list.
------------------------------------------------------------
```

In the current exercise list, there are 13 exercises available.

---
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
### Show all Workouts in one go: `workout /listall`
Lists down all workouts that have been created and stored in the workout list at the time the command is executed.

Format: `workout /listall`

**Example**
```
> workout /listall
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
If there are 20 workouts in the list, all the 20 workouts will be displayed together. 

---

### Show specific number of Workouts: `workout /list`
Lists down a specific number of workouts that have been created and stored in the workout list at a time.
The maximum number of workouts that will be displayed at a time is 10 workouts. If there are more
workouts in the list, you will be prompted on whether you wish to display the next 10 (or fewer) workouts in the list.

Format: `workout /list`

**Example**
```
> workout /list
```
**Expected Outcome**
```
----------------------------------------------------------------------
Showing workouts 1-3 of 3:

1. push up (10 reps)
2. sit up (10 reps)
3. pull up (10 reps)

Showed all workouts in list
----------------------------------------------------------------------
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
## Plan Features
### Create a Plan: `plan /new`
Creates a new plan, which consists of a plan name and the workout(s) added.

Format: `plan /new <plan name> /workouts <workout number(s) separated by comma>`

| Parameters                               | Description                                                                                                                                                                                              |
|------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `<plan name>`                            | The name of the plan. Plan name must be unique.                                                                                                                                                          |
| `<workout number(s) separated by comma>` | The workout(s) that will be added in this new plan. <ul><li>Maximum number of workouts per plan is 10.</li><li>Workouts to be added can be repeated.</li><li>`workout /list` to view workouts.</li></ul> |

**Example**<br>
The workout list upon entering `workout /list`, contains the following workouts:
```
----------------------------------------------------------------------
Showing workouts 1-3 of 3:
1. push up (10 reps)
2. sit up (10 reps)
3. pull up (10 reps)
Showed all workouts in list
----------------------------------------------------------------------
```
To create a new plan, enter the following command:
```
> plan /new Grow My Muscles /workouts 1, 2, 3, 1
```
**Expected Outcome**
```
----------------------------------------------------------------------
Alright, the following plan has been created:

	Grow My Muscles

----------------------------------------------------------------------
```
A new plan containing the workouts you had specified earlier will be created and added to the application's list of plans.

---
### List A Plan: `plan /list`
Displays all available plan names in the application's plan list.

Format: `plan /list`

**Example**<br>
To list the plan names, enter the following command:
```
> plan /list
```
**Expected Outcome**
```
----------------------------------------------------------------------
Here are all your plan(s).
To view each plan in detail, enter
'plan /details <plan number in list>'.

1. Plan A
2. Plan B
3. Grow My Muscles

----------------------------------------------------------------------
```
In the current plans list above, there are 3 plans available.

---
## Schedule Features
### Update Schedule: `schedule /update`
Update the plan schedule for a particular day of the week.

Format: `schedule /update <day number> <plan number>`


| Parameters      | Description                                                                                                        |
|-----------------|--------------------------------------------------------------------------------------------------------------------|
| `<day number>`  | Day number is within the range of 1 to 7. Day number 1 represent Monday, day number 2 represent Tuesday and so on. |
| `<plan number>` | The plan number to be added for the day.                                                                           |

**Example**

Assume, the plan list contains the following plans:
```
----------------------------------------------------------------------
Here are all your plan(s).
To view each plan in detail, enter 'plan /details <plan number in list>'.

1.arms
2.stronger arms
----------------------------------------------------------------------
```
To update the plan schedule for Monday with plan 1, enter the following command:
```
> schedule /update 1 1
```
**Expected Outcome**
```
----------------------------------------------------------------------
Alright, the following plan schedule has been created:

	Monday -- arms

----------------------------------------------------------------------
```
---
### View Schedule: `schedule /list`
Display all the plans scheduled for the week.

Format: `schedule /list`

**Example**

To view all the plans scheduled for the week, enter the following command:
```
> schedule /list
```
**Expected Outcome**
```
----------------------------------------------------------------------

                         WORKOUT SCHEDULE
----------------------------------------------------------------------
     Day       |            Plan Name
----------------------------------------------------------------------
      Monday   |              arms                          
     Tuesday   |            rest day                      
   Wednesday   |            rest day                      
    Thursday   |            rest day                      
      Friday   |            rest day                      
    Saturday   |            rest day                      
      Sunday   |            rest day                      

----------------------------------------------------------------------
```
By default, if no plan is being scheduled for the day it is a rest day. From the expected outcome, 
only arms workout plan is scheduled for Monday.

---

### Clear Schedule For A Day: `schedule /clear`
Remove a plan for a particular day of the week.

Format: `schedule /clear <day number>`

| Parameters      | Description                                                                                                        |
|-----------------|--------------------------------------------------------------------------------------------------------------------|
| `<day number>`  | Day number is within the range of 1 to 7. Day number 1 represent Monday, day number 2 represent Tuesday and so on. |                                                                    |


**Example**

Assume, the schedule for the week contains the following plans:
```
----------------------------------------------------------------------

                         WORKOUT SCHEDULE
----------------------------------------------------------------------
     Day       |            Plan Name
----------------------------------------------------------------------
      Monday   |              arms                          
     Tuesday   |            rest day                      
   Wednesday   |            rest day                      
    Thursday   |            rest day                      
      Friday   |            rest day                      
    Saturday   |            rest day                      
      Sunday   |            rest day                      

----------------------------------------------------------------------
```
To clear the plans scheduled for Monday, enter the following command:
```
> schedule /clear 1
```
**Expected Outcome**
```
----------------------------------------------------------------------
Plan had been cleared for Monday.
----------------------------------------------------------------------
```
After a plan had been cleared for a particular day, the day will be reset to be a rest day.
To view the changes enter command ```schedule /list```. 
```
----------------------------------------------------------------------

                         WORKOUT SCHEDULE
----------------------------------------------------------------------
     Day       |            Plan Name
----------------------------------------------------------------------
      Monday   |            rest day                      
     Tuesday   |            rest day                      
   Wednesday   |            rest day                      
    Thursday   |            rest day                      
      Friday   |            rest day                      
    Saturday   |            rest day                      
      Sunday   |            rest day                      

----------------------------------------------------------------------
```
---
### Clear Schedule For The Week: `schedule /clearall`
Remove all plans scheduled for the week.

Format: `schedule /clearall`

**Example**

Assume, the schedule for the week contains the following plans:
```
----------------------------------------------------------------------

                         WORKOUT SCHEDULE
----------------------------------------------------------------------
     Day       |            Plan Name
----------------------------------------------------------------------
      Monday   |              arms                          
     Tuesday   |            rest day                      
   Wednesday   |         stronger arms                 
    Thursday   |            rest day                      
      Friday   |            rest day                      
    Saturday   |         stronger arms                 
      Sunday   |            rest day                      

----------------------------------------------------------------------
```
To clear all plans scheduled for the week, enter the following command:
```
> schedule /clearall
```
**Expected Outcome**
```
----------------------------------------------------------------------
Schedule had been cleared and reset.
There is no plan scheduled for any day.
To add plan for any day, enter:
schedule /update <day number [1-7]> <plan number>
----------------------------------------------------------------------
```
After a plan had been cleared for a particular day, the day will be reset to be a rest day.
To view the changes enter command ```schedule /list```.
```
----------------------------------------------------------------------

                         WORKOUT SCHEDULE
----------------------------------------------------------------------
     Day       |            Plan Name
----------------------------------------------------------------------
      Monday   |            rest day                      
     Tuesday   |            rest day                      
   Wednesday   |            rest day                      
    Thursday   |            rest day                      
      Friday   |            rest day                      
    Saturday   |            rest day                      
      Sunday   |            rest day                      

----------------------------------------------------------------------
```
---
## Search Features
### Search For Exercise: `search /exercise`
Find all exercises containing the specified keywords.

Format: `search /exercise <exercise keyword>`

| Parameters           | Description                                                         |
|----------------------|---------------------------------------------------------------------|
| `<exercise keyword>` | The keyword to be matched when searching through the exercise list. |


**Example**

Assume, the exercise list contains the following exercises:
```
----------------------------------------------------------------------
1. push up
2. bicep curl
3. pull up
4. squat
5. lunge
6. hip thrust
7. sit up
8. crunch
9. russian twist
10. running
11. swimming
12. jumping jack
13. burpee
----------------------------------------------------------------------
```
To search for exercises containing keywords "up", enter the following command:
```
> search /exercise up
```
**Expected Outcome**
```
----------------------------------------------------------------------
The exercise(s) containing keywords [up] is(are) listed below.
----------------------------------------------------------------------
1. push up
2. pull up
3. sit up
----------------------------------------------------------------------
```

---

### Search For Workout: `search /workout`
Find all workouts containing the specified keywords or number of reps.

Format: `search /workout <exercise keyword or number of reps>`


| Parameters                             | Description                                                                  |
|----------------------------------------|------------------------------------------------------------------------------|
| `<exercise keyword or number of reps>` | The keyword or number to be matched when searching through the workout list. |

**Example**

Assume, the workout list contains the following workouts:
```
----------------------------------------------------------------------
1. pull up (10 reps)
2. push up (1455 reps)
3. crunch (10 reps)
4. squat (25 reps)
----------------------------------------------------------------------
```
To search for workouts containing keywords "a", enter the following command:
```
> search /workout a
```
**Expected Outcome**
```
----------------------------------------------------------------------
The workout(s) containing keywords [a] is(are) listed below.
----------------------------------------------------------------------
1. squat (25 reps)
----------------------------------------------------------------------
```

To search for workouts with number of reps = 10, enter the following command:
```
> search /workout 10
```
**Expected Outcome**
```
----------------------------------------------------------------------
The workout(s) with reps = 10 is(are) listed below.
----------------------------------------------------------------------
1. pull up (10 reps)
2. crunch (10 reps)
----------------------------------------------------------------------

```
---

### Search For Plan: `search /plan`
Find all plans containing the specified keywords.

Format: `search /plan <plan keyword>`

| Parameters       | Description                                                     |
|------------------|-----------------------------------------------------------------|
| `<plan keyword>` | The keyword to be matched when searching through the plan list. |


**Example**

Assume, the plan list contains the following plans:
```
----------------------------------------------------------------------
Here are all your plan(s).
To view each plan in detail, enter
'plan /details <plan number in list>'.

1. grow my muscles
2. arms
3. legs
----------------------------------------------------------------------
```
To search for plans containing keywords "e", enter the following command:
```
> search /plan e
```
**Expected Outcome**
```
----------------------------------------------------------------------
The plan(s) containing keywords [e] is(are) listed below.
----------------------------------------------------------------------
1. grow my muscles
2. legs
----------------------------------------------------------------------
```

---

### Search For All: `search /all`
Find all exercises, workouts and plans containing the specified keywords.

Format: `search /all <keyword>`

| Parameters  | Description                                                         |
|-------------|---------------------------------------------------------------------|
| `<keyword>` | The keyword to be matched when searching through the all the lists. |


**Example**

Assume, the exercise list contains the following exercises:
```
----------------------------------------------------------------------
1. push up
2. bicep curl
3. pull up
4. squat
5. lunge
6. hip thrust
7. sit up
8. crunch
9. russian twist
10. running
11. swimming
12. jumping jack
13. burpee
----------------------------------------------------------------------
```
and the workout list contains the following workouts:
```
----------------------------------------------------------------------
1. pull up (10 reps)
2. push up (1455 reps)
3. crunch (10 reps)
4. squat (25 reps)
----------------------------------------------------------------------
```
and the plan list contains the following plans:
```
----------------------------------------------------------------------
1. grow my muscles
2. arms
3. legs
----------------------------------------------------------------------
```
To search for everything containing keywords "a", enter the following
command:
```
> search /all a
```
**Expected Outcome**
```
----------------------------------------------------------------------
The exercise(s) containing keywords [a] is(are) listed below.
----------------------------------------------------------------------
1. squat
2. russian twist
3. jumping jack
----------------------------------------------------------------------
The workout(s) containing keywords [a] is(are) listed below.
----------------------------------------------------------------------
1. squat (25 reps)
----------------------------------------------------------------------
The plan(s) containing keywords [a] is(are) listed below.
----------------------------------------------------------------------
1. arms
----------------------------------------------------------------------
```

To search for everything containing keywords 10, enter the following
command:
```
> search /all 10
```
**Expected Outcome**
```
----------------------------------------------------------------------
Sorry, no matching exercise found.
----------------------------------------------------------------------
The workout(s) with reps = 10 is(are) listed below.
----------------------------------------------------------------------
1. pull up (10 reps)
2. crunch (10 reps)
----------------------------------------------------------------------
Sorry, no matching plan found.
----------------------------------------------------------------------
```

---
## Help Feature
### View Help: `help`
Lists all the commands with examples.

Format: `help`

**Example**
```
> help
```
**Expected Outcome**
```
----------------------------------------------------------------------
	 To view all exercises available, please enter:
	 exercise /list
	 This will print all the exercises available.
----------------------------------------------------------------------
----------------------------------------------------------------------
	 To view all workouts, please enter:
	 workout /list
	 This will print all the existing workouts.
----------------------------------------------------------------------
	 To add a workout, please enter: 
	 workout /new <exercise name> /reps <no. of repetitions>
	 Example: 
	 workout /new push up /reps 10
	 This will add a workout with 10 reps of push up
----------------------------------------------------------------------
	 To delete a workout, please enter: 
	 workout /delete <index of workout>
	 Example: 
	 workout /delete 1
	 This will delete the workout with index 1 if exists.
----------------------------------------------------------------------
	 To update a workout, please enter: 
	 workout /update <index of workout> <quantity>
	 Example: 
	 workout /update 1 15
	 This will update the workout with index 1 to 15 reps if exists.
----------------------------------------------------------------------
----------------------------------------------------------------------
	 To view all plans, please enter:
	 plan /list
	 This will print all the existing plans.
----------------------------------------------------------------------
	 To view each plan in detail, please enter:
	 plan /details <plan index in list>
	 This will print all the workouts in the plan of given index.
----------------------------------------------------------------------
	 To add a plan, please enter: 
	 plan /new <plan name> /workouts <workout index(s) separated by ','>
	 Example: 
	 The workout list upon entering workout /list, contains the
 	 following workouts:
	 1. push up (10 reps)
	 2. sit up (10 reps)
	 3. pull up (10 reps)
	 To create a new plan, enter the following command:
	 plan /new Grow My Muscles /workouts 1, 2
	 A new plan named Grow My Muscles with workout index 1 and 2
	 will be created and added to the application's list of plans.
----------------------------------------------------------------------
	 To delete a plan, please enter: 
	 plan /delete <index of plan>
	 Example: 
	 plan /delete 1
	 This will delete the plan with index 1 if exists.
----------------------------------------------------------------------
----------------------------------------------------------------------
	 To view the schedule, please enter:
	 schedule /list
	 This will print the schedule.
----------------------------------------------------------------------
	 To update schedule, please enter: 
	 schedule /update <day index [1-7]> <plan index in the list>
	 Example: 
	 The plan list upon entering plan /list, contains the
 	 following plans:
	 1. arms
	 2. stronger arms
	 3. Grow My Muscles
	 To update the plan schedule for Monday with plan 1, enter the
 	 following command:
	 schedule /update 1 1
	 This will update the schedule of Monday with a plan named arms.
----------------------------------------------------------------------
	 To clear schedule, please enter: 
	 schedule /clear <day index [1-7]>
	 This will clear the schedule for the given day.
	 schedule /clearall
	 This will clear the schedule for everyday.
----------------------------------------------------------------------
----------------------------------------------------------------------
	 To search for exercise(s), please enter: 
	 search /exercise <exercise keyword>
	 Example: 
	 search /exercise up
	 This will show the exercise(s) containing keyword up if exist.
----------------------------------------------------------------------
	 To search for workout(s), please enter: 
	 search /workout <exercise keyword or number of reps>
	 Example: 
	 search /workout up
	 This will show the workout(s) containing exercise with keyword
	 up if exist.
	 search /workout 15
	 This will show the workout(s) with reps = 15 if exist.
----------------------------------------------------------------------
	 To search for plan(s), please enter: 
	 search /plan <plan keyword>
	 Example: 
	 search /plan grow
	 This will show the plan(s) containing keyword grow if exist.
----------------------------------------------------------------------
	 To search for everything related to the keyword, please enter: 
	 search /all <keyword>
	 Example: 
	 search /all a
	 This will show the exercise(s), workout(s) and plan(s) containing
	 keyword a if exist.
----------------------------------------------------------------------
----------------------------------------------------------------------
	 To exit werkIt, please enter: 
	 exit
	 This will exit werkIt.
----------------------------------------------------------------------
```

This will show a complete guide to use WerkIt.

---

## WerkIt!'s Local Storage Information
When you start WerkIt! for the first time, the following directory and files are created:

```
werkItResources/        // Primary resource directory for WerkIt!
    ├── exercises.txt   // Text file containing a list of exercises
    ├── workouts.txt    // Text file containing a list of user-created workouts
    ├── plans.txt       // Text file containing a list of user-created plans
    └── schedule.txt    // Text file containing a 7-day schedule of user-assigned plans for each day
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

Below is a summary of all the commands available in the WerkIt! application.

| Action                                       | Format and Example                                                                                                                                      |
|:---------------------------------------------|:--------------------------------------------------------------------------------------------------------------------------------------------------------|
| List Exercise                                | <br /> Format: `exercise /list` <br/>Example: `exercise /list` <br /> <br />                                                                            |
| Create Workout                               | <br /> Format: `workout /new <exercise name> /reps <number of repetitions>`<br/>Example: `workout /new biceps curl /reps 1`  <br /> <br />              |
| Delete Workout                               | <br /> Format: `workout /delete <workout number to delete>`<br/>Example: `workout /delete 2`  <br /> <br />                                             |
| Update Workout                               | <br /> Format: `workout /update <index of workout to be updated> <new number of repetitions>`<br/>Example: `workout /update 1 15`   <br /> <br />       |
| List Workouts (Display at most 10 at a time) | <br /> Format: `workout /list` <br/>Example: `workout /list` <br /> <br />                                                                              |
| List All Workouts                            | <br /> Format: `workout /listall` <br/>Example: `workout /listall` <br /> <br />                                                                        |
| Create Plan                                  | <br /> Format: `plan /new <plan name> /workouts <workout number(s) separated by comma>`<br/>Example: `plan /new Plan A /workouts 1, 2, 3` <br /> <br /> |
| List Plan                                    | <br /> Format: `plan /list` <br /> <br />                                                                                                               |
| List Details Of Plan                         | <br /> Format: [to be updated]  <br /> <br />                                                                                                           |
| Update Schedule                              | <br /> Format: `schedule /update <day number [1-7]> <plan number>`<br/>Example: `schedule /update 2 4` <br /> <br />                                    |
| List Schedule                                | <br /> Format: `schedule /list` <br /> <br />                                                                                                           |
| Clear Schedule For A Day                     | <br /> Format: `schedule /clear <day number [1-7]>`<br/>Example: `schedule /clear 3` <br /> <br />                                                      |
| Clear All Schedule                           | <br /> Format: `schedule /clearall` <br /> <br />                                                                                                       |
| Search For Exercise                          | <br /> Format: `search /exercise <exercise keyword>` <br/>Example: `search /exercise up` <br /> <br />                                                  |
| Search For Workout                           | <br /> Format: `search /workout <exercise keyword or number of reps>`  <br/>Example: `search /workout push` <br /> <br />                               |
| Search For Plan                              | <br /> Format: `search /plan <plan keyword>` <br/>Example: `search /plan arms` <br /> <br />                                                            |
| Search For ALL                               | <br /> Format: `search /all <keyword>` <br/>Example: `search /all 10` <br /> <br />                                                                     |
| View Help                                    | <br /> Format: `help`<br/>Example: `help`  <br /> <br />                                                                                                |
