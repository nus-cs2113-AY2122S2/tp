# Choo Yi Kai - Project Portfolio Page

## Overview: Mod Happy

Mod Happy is a command-line application written in Java which helps students manage their academics by keeping track of their tasks and facilitating GPA calculations.

The section below lists my contributions to this project.

## Summary of Contributions
You can view my contributed code [here](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=chooyikai&breakdown=true).

- **Foundational code:** Rewrote basic classes used by the program for data storage (`Task`, `TaskList`, `Module`, `ModuleList`) to be more OOP-compliant. This implementation is currently in use, although it has since been modified to accommodate features that were added later. ([#75](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/75))


- **New features:**
  - Added the ability to add modules, which formed part of the minimum functionality for the application. ([#86](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/86))
  - Partially implemented the loading of serialised user data from the saved data file. ([#94](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/94))
    - This feature renders the app significantly more usable it contributes to the persistence of user data across multiple usage sessions.
    - I handled the instantiation of the relevant data objects in the program based on the data loaded, while the actual serialisation and deserialisation functions were written by a teammate.
  - Added the ability to toggle the visibility of completed tasks from the `list` command output. ([#111](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/111))
    - This feature helps to reduce visual clutter when there are many completed tasks already present in the application.


- **Enhancements:**
  - Significantly rewrote `add`, `exit`, `list` and `mark` commands to comply with the new data classes, after they were changed to be more OOP-compliant. ([#75](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/75))
    - The updated implementation of these commands set the precedent for all future command additions, and this was generally followed by the rest of the team when adding new features.
  - Contributed a significant number of test cases for the parsing of user commands, and refactored some existing ones. ([#86](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/86))
  - Added input validation to the deserialisation functions used for data loading. ([#175](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/175), [#194](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/194))
    - This makes the program inspect and reject / tweak invalid user data loaded from the data file instead of blindly trusting anything inside (which could violate some program assumptions if the file was corrupted or modified manually).


- **Documentation:**
  - User guide:
    - Added explanations for using the user guide, the notation used within, as well as how to specify tasks. ([#105](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/105), [#111](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/111), [#116](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/116))
    - Added documentation for the `option` and `gpa` commands. ([#111](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/111))
    - Added accepted inputs for each parameter in each command format. ([#116](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/116))
    - Generally rewrote command explanations to sound less clinical and more friendly.
  - Developer guide:
    - Added explanation of the Data component and created the relevant class diagrams within that section. ([#111](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/111))
    - Edited explanations of the Parser and Command components. ([#111](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/111))
    - Edited class diagram for the Storage component to correctly represent binding relationships. ([#111](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/111), [#116](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/116))
    - Added some elaboration for the Storage implementation. ([#194](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/194))


- **Team tasks:**
  - Performed code cleanup and fixed bugs throughout the codebase, as well as resolved formatting, language and consistency issues in the user and developer guides.
  - Managed releases `v1.0` and `v2.0` on GitHub.
  - Cleaned up issues which were not closed after being addressed, and removed user stories which were no longer in scope due to time constraints or shifting project vision. 


- **Community:**
  - PRs reviewed (with significant comments): [#69](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/69), [#76](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/76), [#84](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/84), [#91](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/91), [#95](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/95), [#101](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/101), [#108](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/108), [#109](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/109) 