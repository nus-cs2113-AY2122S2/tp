# Developer Guide
## Contents
* [Acknowledgements](#acknowledgements)
* [Design](#design)
    * [Architecture](#architecture)
    * [Manager Component](#manager-component)
    * [Profile Component](#profile-component)
    * [TextUI Component](#textui-component)
    * [Storage Component](#storage-component)
    * [Parser Component](#parser-component)
    * [Command Component](#command-component)
* Implementation
  * [To be completed]
* [Project Scope](#product-scope)
* [User Stories](#user-stories)
* [Non-Functional Requirements](#non-functional-requirements)
* [Glossary](#glossary)


## Acknowledgements
* Address Book (Level-3) - Provide samples of User Guide(UG) and Developer Guide(DG)

## Design
### Architecture
![Application Diagram Screenshot](https://raw.githubusercontent.com/froststein/tp/master/docs/images/developerguide/ArchitectureDiagram.drawio.png)

The *Architecture Diagram* shown above illustrates the high-level design of the SplitLah application.

**Overview of components**
  * `Main`
    * On app launch: Creates an instance of `Manager` and runs command loop.
  * `Manager`
    * On creation: Initializes the Profile, TextUI and Storage components.
    * On run: Loads data from `Storage`, receives user input from UI and uses `Parser` to parser user input into application.
  * `Profile`
    * Handles cached data within run time of application. Manages and stores `sessions`, `activities` and `groups` that were created by the user.
  * `TextUI`
    * Handles UI operations of the application.
  * `Storage`
    * Handles storage operations of the application.
  * `Parser`
    * Handles input parsing and determines which command to run.
  * `Command`
    * Defines how a command is executed.

### Manager Component
### Profile Component
### TextUI Component
### Storage Component
### Parser Component
### Command Component


## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

