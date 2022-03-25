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

![ManagerUML](https://raw.githubusercontent.com/AY2122s2-cs2113t-t09-3/tp/master/docs/Diagrams/ManagerUML.png)
<br>
How the Manager class works:
* When `Duke` class instantiates a `Manager` object and calls `runLoop` method, the program will execute a while loop
* The while loop only halts when `isTerminated` boolean becomes true. The programme exits
* In the while loop, there is a switch statement. It calls a `Command/UI` method based on a commandWord
* The commandWord determines which method is called

### Helper Classes
#### `Command`

![CommandUML](https://raw.githubusercontent.com/AY2122s2-cs2113t-t09-3/tp/master/docs/Diagrams/CommandUML.png)
How the Command class works:
* The `Manager` class calls specific methods in `Command` class
* Each method in the `Command` class would call on an `asset` class method which will manipulate the attributes inside it

#### `Parser`
The parser parses the description of the command. It first calls the validator class to validate the parameters and then returns a command.



#### `Storage`

### Asset classes


--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Find appointments with selected criteria
This feature is a method within `AppointmentList`. `AppointmentList` contains an arraylist
of `Appointment` as a private element. `AppointmentList` methods invoked interacts with this
list, possibly making changes in the process.

Currently, `AppointmentList` has the following methods:
* `AppointmentList#add` -- Appends a new `Appointment` to the list.
* `AppointmentList#showAll` -- Text display of all appointments in the list.
* `AppointmentList#find` -- Find selected appointments using the criteria given.

The methods are exposed in the `Manager#runLoop` method where user input is parsed.

Below is an example describing the behaviour of the `find` feature.

#### Design considerations:

---------------------------------------------------------------------------------------------------------------
## Product scope

###Target user profile:

* lightweight appli
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

###Value proposition:
Manage contacts faster than a typical mouse/GUI driven app
Streamlines the updating of data and records

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Version | Priority | As a ... | I want to ... | So that I can ... |
|---------| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
|         | `* * *`  | new user                                   | see usage instructions         | refer to instructions when I forget how to use the App                 |
|         | `* * *`  | new user                                   | import or export data          | share with others when needed and make backup copies                                                                       |
|         | `* *`    | user                                       | find appointments based on selected criteria | filter out the appointments that I want to know about                                   |
|         | `* * *`  | user                                       | find a person by name          | locate details of persons without having to go through the entire list |
|         | `* *`    | user                                       | hide private contact details   | minimize chance of someone else seeing them by accident                |
|         | `*`      | user with many persons in the address book | sort persons by name           | locate a person easily                                                 |

### Use cases


### Non-Functional Requirements
Device Environment:
* Must have Java 11 or higher installed in OS
* 32-bit or 64-bit environment
* Command Line Interface supported

  Performance of app:
* Function offline, without the need for internet access
* Quick to launch and use
* No noticeable lag or delay in performance when running
* Intuitive and seamless for new users.
* Ability to export the data into a txt file to load on another OS

  Reliability of app:
* Data files should be updated constantly and accurately, with no data loss
* Data records should be retrievable and readable
* Text inputs should produce similar results if utilised multiple times.
* Program should run without any forced-close error due to bugs
--------------------------------------------------------------------------------------------------------------------
## Glossary
* *FUllNAME* - Standard form for fullname of patients and doctors is a String value with no spaces
* *NRIC* - Standard form for nric of patients and doctors is a String value with no spaces
* *AGE* - Standard form for age is an int value more than 0 
* *GENDER* - Standard form for gender of patients and doctors is a char value of "M" or "F"
* *ADDRESS* - Standard form for address is a String value with no spaces
* *DOB* - Standard form for date-of-birth is a String value with no spaces
* *SPECIALIZATION* - Standard form for specialization of doctors is String value with no spaces
* *MEDICINEID* - Standard form for medicine id is a String value with no spaces
* *MEDICINENAME* - Standard form for medicine name is a String value with no spaces
* *DOSAGE* - Standard form for dosage of medicine is an int value, standard unit milligrams
* *EXPIRY* - Standard form for expiry of medicine is a String value with no spaces
* *SIDEEFFECTS* - Standard form for side effects of medicine is a String value with no spaces
* *QUANTITY* - Standard form for quantity of medicine is an int value 


--------------------------------------------------------------------------------------------------------------------

## **Appendix**

For manual testing, developers can follow the instructions listed out in the [UserGuide](UserGuide.md)
* Detailed input and output examples are displayed for cross checking.
  Alternatively, they can also follow the following steps to test out the respective commands,
  upon downloading the jar file, while adhering to the specified restrictions in Glossary:

1. `add doctor /info [nric],[name],[age],[gender],[address],[DOB],[Specialisation]`
  * Adds a new doctor with the specified parameters into the system
2. `add patient /info [nric],[name],[age],[gender],[address],[DOB]`
  * Adds a new patient with the specified parameters into the system
3. `add medicine /info [name],[dosage],[expiry date],[side effects],[quantity]`
  * Adds a new medicine with the specified parameters into the system
4. `view doctor` or `view doctor /info [nric]`
  * View records of all doctors or a specific doctor with the given nric
5. `view patient` or `view patient /info [nric]`
  * View records of all patients or a specific patient with the given nric.
6. `view medicine` or `view medicine /info [name]`
  * View records of all medicines or a specific medicine with the given id.
7. `delete doctor /info [nric]`
  * deletes the record of the doctor with the specified nric
8. `delete patient /info [nric]`
  * deletes the record of the patient with the specified nric
9. `delete medicine /info [Batch ID]`
  * deletes the record of the medicine with the specified id
