# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}
### Manager component

![ManagerUML](https://raw.githubusercontent.com/AY2122s2-cs2113t-t09-3/tp/master/docs/Diagrams/ManagerUML.png)
<br>
How the Manager class works:
* When `Duke` class instantiates a `Manager` object and calls `runLoop` method, the program will execute a while loop
* The while loop only halts when `isTerminated` boolean becomes true. The programme exits
* In the while loop, there is a switch statement. It calls a `Command/UI` method based on a commandWord


### Command component

How the Manager class works:


## Product scope
### Target user profile

* Our target user are hospital/clinic administrators who oversees the institution's information
* Our target user can type reasonably fast
* Our target user has some proficiency in CLI applications

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

* *CLI* - Command-line interface

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
