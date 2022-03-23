---
layout: page
title: Developer Guide
---
* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------
## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

Our project could not have been possible without the prior work of the following:

- [SE-EDU project team](https://se-education.org/docs/team.html) for their work on [AddressBook Level 3](https://github.com/se-edu/addressbook-level3), for which we referenced ideas as well as code snippets.
- Google's [GSON Java library](https://github.com/google/gson) for which we used to load and save our data files.
- Members of the [AY2122S2-CS2113-F10-2 team](https://github.com/nus-cs2113-AY2122S2) for pointing out the possibility of and requesting permission to make use of the above GSON library.  

--------------------------------------------------------------------------------------------------------------------
## Design & Implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Architecture

Our design draws significant inspiration from the implementation of AddressBook Level 3 (henceforth AddressBook). 
As the program was initially conceptualised to be a text-based command-line interface, heavy consideration was given
to the design and user interactions when it came to the text input to be parsed by the user. 
As such, we referenced AddressBook to segment the program into **`Parser`**, **`Command`** and **`Equipment`** classes.

**Main components of the architecture**

- **`Duke`** is the Main class and entry-point for the program. 
- **`Parser`** serves as the first filter to split raw text input and pass in arguments to the **`Command`** for further processing.
- **`Command`**, together with its various subclasses serve as specific implementations to pass the arguments taken in to the various methods of the **`EquipmentManager`** class.
- **`EquipmentManager`** keeps track of actual **`Equipment`** instances created by our program.
- **`Storage`** performs File I/O functions.

### Parser

**`Parser`** is the first filter for text inputs read to the user. 
As alluded to prior, one major consideration was to build it in a manner that can parse text input as effectively as possible. In considering text input, we divided the parsing into the following segments:

#### `commandWord` `argumentTag/argumentValue` `argumentTag/argumentValue` `...`

To dispatch `argumentTag/argumentValue` strings to the correct **`Command`** class, the following logic is employed throughout **`Parser`** by **`parseCommand`**.

**1. Parse the correct Command Word**

**`splitCommandTerm`** splits the input string upon the first space. 
The first substring is used to decide which **`Command`** to dispatch while the second is used for its arguments.
In the case where a second substring is not required, as in the case of **`help`** and **`list`**, a null String is used.

**2. Split Arguments**

For simpler command words such as **`delete`** and **`check`**, only one pair of **`argumentType`** and **`argumentValue`** were required. 
As such, these will be passed in directly to their **`Command`** classes without a **`argumentType`** pair.

More complex command words such as **`add`** and **`update`** necessitated multiple arguments.
To implement this while ensuring that multi-word strings are acceptable input, **`extractArguments`** was implemented. 
Without specifically explaining the main regular expression ([details here](https://regex101.com/r/gwjHWD/3)), the approach sought to match **`argumentTag/argumentValue`** pairs with a positive lookahead. 
The final argument pair will then be extracted using a separate regex. 
Together, this ensured that all argument pairs can be effectively parsed and dispatched to each **`Command`** class.

Throughout the `Parser` implementation, exceptions were used to return `IncorrectCommand` classes that can be used to pass error messages to the user. These will be discussed in the following segments.

--------------------------------------------------------------------------------------------------------------------
## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

--------------------------------------------------------------------------------------------------------------------
## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

--------------------------------------------------------------------------------------------------------------------
## Non-Functional Requirements

{Give non-functional requirements}

--------------------------------------------------------------------------------------------------------------------
## Glossary

* *glossary item* - Definition

--------------------------------------------------------------------------------------------------------------------
## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
