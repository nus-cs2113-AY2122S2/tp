# WerkIt! Developer Guide

## Table of Contents
* [Acknowledgements](#acknowledgements)
* [Design](#design)
* [Implementation](#implementation)
* [Product Scope](#product-scope)
* [User Stories](#user-stories)
* [Non-Functional Requirements](#non-functional-requirements)
* [Glossary](#glossary)
* [Instructions for Manual Testing](#instructions-for-manual-testing)

## Acknowledgements
The following websites and codebases were referenced and adapted for our project:

* AddressBook-Level2 project ([Website](https://se-education.org/addressbook-level2/) | 
[GitHub](https://github.com/se-edu/addressbook-level2))
* Team Member Alan Low's individual project (iP) codebase ([GitHub](https://github.com/alanlowzies/ip))

## Setting Up your Development Environment
### Requirements
- [ ] Java JDK version 11
- [ ] An IDE of your choice, though IntelliJ IDEA is recommended as this project is developed
with this IDE.

> Note: IDE-related references in this developer guide IDE will be tailored for IntelliJ IDEA. 

### Setting Up
1. Fork the [WerkIt! GitHub repository](https://github.com/AY2122S2-CS2113T-T09-2/tp).
2. Clone your fork to your machine.
3. Set up your local repo in your IDE.
    - Ensure that the project in your IDE is configured to run on Java JDK version 11. 
    - A guide on setting your project to use JDK 11 in your IntelliJ IDEA IDE can be found 
[here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).
4. Run `Main.java`. If you have set up your environment correctly, you should see the following
output in your terminal: 
```
======================================================================
 __        __        _    ___ _   _ 
 \ \      / /__ _ __| | _|_ _| |_| |
  \ \ /\ / / _ \ '__| |/ /| || __| |
   \ V  V /  __/ |  |   < | || |_|_|
    \_/\_/ \___|_|  |_|\_\___|\__(_)
                                    
Welcome to WerkIt!, your personal exercise planner.
----------------------------------------------------------------------
Checking for required directory and files...
- The required data directory was not found. It will be created.
- The WerkIt! resource directory has been created in
  your terminal's current working directory.

- The exercise file was not found. It will be created.
- The exercise file 'exercises.txt' has been created in
  the WerkIt! resource directory.

- The workout file was not found. It will be created.
- The workout file 'workouts.txt' has been created in
  the WerkIt! resource directory.

- The plan file was not found. It will be created.
- The plan file 'plans.txt' has been created in
  the WerkIt! resource directory.

- The schedule file was not found. It will be created.
- The schedule file 'schedule.txt' has been
  created in the WerkIt! resource directory.

Loading saved file data...
- Exercises file	OK!
----------------------------------------------------------------------
Now then, what can I do for you today?
(Need help? Type 'help' for a guide!)
----------------------------------------------------------------------
>
```
5. Type `exit` to exit the program.

You are now ready to begin developing!

## Design 
### Overview

---

## Implementation
### Overview
* [Create New Workout](#create-new-workout)
* [Search](#search)
  * [Search for Exercise](#search-for-exercise)
  * [Search for Plan](#search-for-plan)

### Create New Workout
In _WerkIt!_, a workout is defined as an exercise paired with a number that represents the number
of repetitions. For example, 20 repetitions of the Russian twist is considered a workout. 

### Search
![SearchUML](https://github.com/a1021492980/tp/blob/branch-DG1/docs/uml/diagram/SearchClassUML.png?raw=true)
<br>

When WerkIt is running, the `WerkIt` class will keep prompting the user to enter command through the
`WerkIt#startContinuousUserPrompt()` method. After the user has entered command, The `UI#getUserInput()` method in `UI`
class will catch the user input, and it will be sent to `Parser#parseUserInput(String userInput)` method to analyse the
user's command. If the user's command type is search, i.e. `search <userAction> <keywords>`, the
`Parser#parseUserInput(String userInput)` method will parse the 'search' base word and proceed to create search related
command using `Parser#createSearchCommand(String userInput)` method. This method will further evaluate the
`<userAction>` and call the constructor of `SearchCommand` class by passing relevant parameters related to search to the
constructor. If the `<userAction>` is null or incorrect, an InvalidCommandException will be thrown. If the `<keywords>`
is not specified, an InvalidCommandException will be thrown.

#### Search for exercise
Format: `search /exercise <keywords>`

The `Parser#createSearchCommand(String userInput)` method will further evaluate the user input
`/exercise` and call the constructor of `SearchCommand` class by passing relevant parameters related to search exercise
to the constructor. The created `SearchCommand` object is returned by the `Parser#createSearchCommand(String userInput)`
method to `Parser#parseUserInput(String userInput)` method, and finally returned by
`Parser#parseUserInput(String userInput)` method to `WerkIt#startContinuousUserPrompt()` method. The search command will
be executed in `WerkIt#startContinuousUserPrompt()`. And based on the `<keywords>` specified by the user, the output
will either be a list of matching exercises or 'Sorry, no matching exercise found' if the user has entered the command
correctly.

#### Search for plan
Format: `search /plan <keywords>`

The `Parser#createSearchCommand(String userInput)` method will further evaluate the user input
`/plan` and call the constructor of `SearchCommand` class by passing relevant parameters to the constructor.
The created `SearchCommand` object is returned by the `Parser#createSearchCommand(String userInput)`
method to `Parser#parseUserInput(String userInput)` method, and finally returned by
`Parser#parseUserInput(String userInput)` method to `WerkIt#startContinuousUserPrompt()` method. The search command will
be executed in `WerkIt#startContinuousUserPrompt()`. And based on the `<keywords>` specified by the user, the output
will either be a list of matching names of plan or 'Sorry, no matching plan found' if the user has entered the command
correctly.

---

## Product Scope
### Target User Profile

{Describe the target user profile}

### Value Proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
| v1.0 | user | create a workout | keep track of how many repetitions I would like to do with an exercise |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}