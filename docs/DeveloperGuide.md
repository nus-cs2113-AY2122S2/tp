#Developer Guide

--------------------------------------------------------------------------------------------------------------------
#Contents
* [Acknowledgements](#acknowledgements)
* [Getting Started For Beginners](#getting-started-for-beginners)
* [Design](#design)
  * [Architecture](#architecture)
  * [UI Component](#ui-component)
  * [Manager Component](#manager-component)
  * [Helper Classes](#helper-classes)
    * [Command Component](#command)
    * [Parser Component](#parser)
    * [Storage Component](#storage)
  * [Common Classes](#common-classes)
* [Implementation](#implementation)
  * [Design Considerations](#design-considerations)
* [Product Scope](#product-scope)
  * [Target User](#target-user-profile)
  * [Value Proposition](#value-proposition)
  * [User Stories](#user-stories)
  * [Use Cases](#use-cases)
  * [Non-Functional Requirements](#non-functional-requirements)
* [Glossary](#glossary)
* [Appendix](#appendix)

--------------------------------------------------------------------------------------------------------------------
## **Acknowledgements**

* Design and Structure of Developer Guide referenced from the
[AB3-Developer Guide](http://se-education.org/addressbook-level3/DeveloperGuide.html).

--------------------------------------------------------------------------------------------------------------------
## **Getting Started for Beginners**

Refer to the [_User Guide_](UserGuide.md).

--------------------------------------------------------------------------------------------------------------------
## **Design**

### Architecture

The ***Architecture Diagram*** given above explains the high-level design ***HALPMI***.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has single method called `main` that initialises a new instance of a Manager class, and calls the `runLoop()`
method belonging to the Manager object.

[**`Assets`**](#asset-classes): Refers to a collection of classes that are the main assets of the application.

The rest of the App consists of three components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Manager`**](#manager-component): The Brain.
* [**`Helper`**](#helper-classes): Core Classes that help with operations.
  * [**`Command`**](#command): Calls all the commands.
  * [**`Parser`**](#parser): Breaks down user input into parameters accepted by the app, also checks if parameters are valid.
  * [**`Storage`**](#storage): Reads data from data files anf writes data to data files, also stores in app memory.

### UI component

### Manager component

### Helper Classes
#### `Command`

#### `Parser`

#### `Storage`

### Asset classes


--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

#### Design considerations:

---------------------------------------------------------------------------------------------------------------
## Product scope

###Target user profile:

* has a need to manage a significant number of contacts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

###Value proposition:
Manage contacts faster than a typical mouse/GUI driven app

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | new user                                   | see usage instructions         | refer to instructions when I forget how to use the App                 |
| `* * *`  | user                                       | add a new person               |                                                                        |
| `* * *`  | user                                       | delete a person                | remove entries that I no longer need                                   |
| `* * *`  | user                                       | find a person by name          | locate details of persons without having to go through the entire list |
| `* *`    | user                                       | hide private contact details   | minimize chance of someone else seeing them by accident                |
| `*`      | user with many persons in the address book | sort persons by name           | locate a person easily                                                 |

### Use cases


### Non-Functional Requirements

--------------------------------------------------------------------------------------------------------------------
## Glossary

--------------------------------------------------------------------------------------------------------------------

## **Appendix**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>
