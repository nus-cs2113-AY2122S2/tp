# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation


1. The following sequence diagram shows how the Bye Command works.
![](ByeCommand.png)

## Product scope
### Target user profile

For airlines counter staff who are fast typist to easily manage flight bookings.

### Value proposition

Given that the pandemic is ending, more and more people are resuming air travel. <br>
Therefore, airlines counter staff need a fast an efficient way to manage the many flight bookings.

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|Counter Staff|Add in flight menu item|Add menu items available for in-flight meals|
|v1.0|Counter Staff|Remove in flight menu item|Remove menu items that can be reserved for in-flight meals|
|v1.0|Counter Staff|Add in flight meal reservation|Manage in-flight meal reservation request from customers|
|v1.0|Counter Staff|Remove in flight meal reservation|Manage in-flight meal reservation request from customers|
|v1.0|Counter Staff|List all in flight menu items|To answer customer queries on the menu items available|
|v1.0|Counter Staff|Add Flight route|Add new flight route that the airline releases|
|v1.0|Counter Staff|Remove Flight route|Remove the flight routes the airline will no longer offer|
|v1.0|Counter Staff|List all flight route|Have an overview of available flight routes|
|v1.0|Counter Staff|Find flight route|To answer customer's query on whether a flight route exists|
## Non-Functional Requirements

1. The program should not crash under any circumstances.
2. The program can at maximum display "I do not understand command."
3. The program should have a user guide.
4. The program should be able to load in data from txt file.
5. The program should be able to save data to a txt file.
6. The program will be a 100% CLI interface.

## Glossary

* *Menu Item* - Refers to a food menu item

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}


## Plan to add for following development:
#### 1. stuff class: define stuff class
      stuff id(key), stuff name, authentication code
#### 2. stuff method:
    add and delete stuff
#### 3. develop stuff ui interface
#### 4. add stuff authentication before edit flight
    
    How the feature is implemented (or is going to be implemented).
    Why it is implemented that way.
    Alternatives considered.