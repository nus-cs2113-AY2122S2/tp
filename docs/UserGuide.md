# WerkIt! User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

> Heads up! Your inputs cannot contain the pipe character `|`!

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

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
