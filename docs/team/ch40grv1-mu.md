# Mu Changrui - Project Portfolio Page

## Overview: Mod Happy

Mod Happy is a command-line application written in Java which helps students manage their academics by keeping track of their tasks and facilitating GPA calculations.

The sections below are my contributions to this project.

## Summary of Contributions
You can view my contributed code [here](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=Ch40gRv1-Mu&breakdown=true).

- **Foundational code:**
    - Wrote `Parser`, which is the parent class of other parsers. I applied java regex to implement the parserString method, that is utilized by all other parsers. [#69](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/69)
    - Wrote the skeleton logic structure of `ModHappyParser` and `Main` with referring to [AB3](https://github.com/se-edu/addressbook-level3). [#69](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/69)
    - Wrote major classes of storage (`Storage`,`JsonStorage`,`ListStorage`,`ModuleListStorage`,`TaskListStorage`, `ConfigurationStorage`). [#91](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/91)
    - Wrote `Configuration`, which standardizes the user options and manages the states of user's customized options in the app. [#102](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/102)
    - Wrote `Command`, which is the parent class of other Command, this abstract class defines the logic of Command and is utilized in the current program. [#69](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/69)
    - Wrote `CommandResult`, which stores the result of commands and is passed to UI for displaying to users. [#69](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/69)
    - Wrote `ModHappyException`, which is the parent class of other exceptions in the app(Only contributed to the skeleton). [#69](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/69)


- **New features:**
    - Added the ability to save, which will save the state of the program(modules, tasks, user options) and significant enhance the usability of the app, because it enables users to access the data across multiple usage sessions. [#91](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/91)

    - Partially implemented the loading of serialised user data from storage. [#91](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/91). I handled the serialisation and deserialization functions, while instantiation of the relevant data objects in the program based on the data loaded were written by a teammate.
    - Added the ability to check and set customized options (e.g.always hide completed tasks). This feature enhances the usability of the app and is an extendable skeleton for user model in MVC. [#102](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/102)
    - Wrote `ExitCommand` that will execute pre-ending operations and end the process.  [#69](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/69)

- **Enhancements:**
    - Significantly refactoring the estimated time feature of tasks. I made `Duration`, which accepted flexible format of string representing time duration from users, store the task duration as a `java.time.Duration` and display the time in unified format. [#124](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/124). The updated implementation of this feature significant enhance the linguistic and logical meaning of "time" in the app and make it possible for the future features (e.g.sorting by time).
    - Contributed the majority of test cases for `TaskDuration`,`Parser`,`OptionParser` and class of storages. [#69](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/69)


- **Documentation:**
    - User guide:
        - Added supported system explaining the operating systems that the app are well tested on. [#173](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/173)
        - Added expected output to all sample input and keep the output of the user guide updated when the app is updated. [#173](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/173)
        - Added sample input in the command summary. [#173](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/173)

    - Developer guide:
        - Added section explaining the format and usage of estimated time. [#118](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/118)
        - Added explanation of the overview of the app and created the relevant class diagrams within that section. [#99](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/99),  [#109](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/109)
        - Added explanations of the parsers and created the relevant class diagrams within that section.  [#109](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/109), [#118](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/118)
        - Added explanations of the component and implementation of storage, and created the relevant class diagrams within that section. [#109](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/109), [#99](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/99)

- **Team tasks:**
    - Performed code cleanup and fixed bugs.
    - Confirmed meeting time and created Zoom for each meeting.
    - Checked the releases `v1.0`, `v2.0` and `v2.1` on multiple systems(macOS, Kali Linux, Ubuntu, CentOS) for each release.
    - Keep tracking potential bugs and created multiple bug issues after discussing with teammates. [#170](https://github.com/AY2122S2-CS2113T-T10-3/tp/issues/170), [#135](https://github.com/AY2122S2-CS2113T-T10-3/tp/issues/135), [#134](https://github.com/AY2122S2-CS2113T-T10-3/tp/issues/134), [#172](https://github.com/AY2122S2-CS2113T-T10-3/tp/issues/172) , [#136](https://github.com/AY2122S2-CS2113T-T10-3/tp/issues/136) , [#119](https://github.com/AY2122S2-CS2113T-T10-3/tp/issues/119), [#71](https://github.com/AY2122S2-CS2113T-T10-3/tp/issues/71)
    - Static analysis on code and improved code quality. [#188](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/188)


- **Community:**
    - PRs reviewed (most with significant comments): [#176](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/176) , [#73](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/73), [#75](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/75), [#80](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/80), [#86](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/86), [#87](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/87), [#88](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/88), [#89](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/89), [#90](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/90), [#94](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/94), [#105](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/105), [#106](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/106), [#108](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/108), [#111](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/111), [#171](https://github.com/AY2122S2-CS2113T-T10-3/tp/pull/171)

