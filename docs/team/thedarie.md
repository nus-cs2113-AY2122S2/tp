# Daryl - Project Portfolio Page

## Overview
Sherpass is a command line interface desktop application to help students manage their academic workloads
by preparing them a timetable to plan their activities, as well as encouraging them to
complete the activities with the use of the study session.

- [Sherpass Github](https://github.com/AY2122S2-CS2113T-T09-1/tp)
- [Sherpass User Guide](https://ay2122s2-cs2113t-t09-1.github.io/tp/UserGuide.html)
- [Sherpass Developer Guide](https://ay2122s2-cs2113t-t09-1.github.io/tp/DeveloperGuide.html)

## Summary of Contributions

### Code Contributed

[Link to code contribution](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=thedarie&breakdown=true)

Summary of code contributions:
- Used a JSON library to facilitate the `Storage` component of the application.
    - Implemented the translation of `Task` to a JSON string and vice versa.
    - Implemented the writing of tasks currently in the application to a JSON file on disk.
    - Implemented the loading of save files into the application.
    - Implemented handling of invalid save data.
- Wrote some functions in `TaskParser` that processes user input to prepare execution of given command.
- Implemented recurring tasks feature that allows user to have a task repeat at regular intervals.
    - Handled and implemented the logic of how recurring tasks should be added, deleted and edited.
    - Implemented the `HashSet` data structure in `TaskList` to keep track of the task identifiers.
    - Implemented the `Frequency` enumeration to identify the interval of which tasks should be repeated.
- Added JUnit tests for Storage, TaskParser and StorageParser class.
- Added JavaDoc comments for most methods that I wrote.
- Refactored code related to `Storage` and `Task` components on multiple occasions to achieve better code quality.

### Contributions to User Guide:
- Wrote the quick start section [(Link)](https://ay2122s2-cs2113t-t09-1.github.io/tp/UserGuide.html#editing-your-tasks-edit)
- Wrote the command guide for [adding](https://ay2122s2-cs2113t-t09-1.github.io/tp/UserGuide.html#adding-your-tasks-add),
  [editing](https://ay2122s2-cs2113t-t09-1.github.io/tp/UserGuide.html#editing-your-tasks-edit) and [deleting](https://github.com/AY2122S2-CS2113T-T09-1/tp/blob/master/docs/UserGuide.md#deleting-your-tasks-delete) tasks
- Wrote the section on Saving your tasks [(Link)](https://ay2122s2-cs2113t-t09-1.github.io/tp/UserGuide.html#saving-your-tasks)
- Added some screenshots and example commands for the command guide section.
- Wrote the FAQ section [(Link)](https://ay2122s2-cs2113t-t09-1.github.io/tp/UserGuide.html#faq)

### Contributions to Developer Guide:
- Wrote the section on Task Implementation [(Link)](https://ay2122s2-cs2113t-t09-1.github.io/tp/DeveloperGuide.html#task-implementation)
- Gave an overview of Storage in the Architecture section [(Link)](https://ay2122s2-cs2113t-t09-1.github.io/tp/DeveloperGuide.html#storage)
- Explained the storage component in the Design & Implementation section [(Link)](https://ay2122s2-cs2113t-t09-1.github.io/tp/DeveloperGuide.html#storage-implementation)
- Added class diagram for `Storage` and `Task` to aid explanation.
- Added sequence diagrams to explain how save files are loaded and how tasks are edited.
- Added some example scenarios of task management in Manual Testing section [(Link)](https://ay2122s2-cs2113t-t09-1.github.io/tp/DeveloperGuide.html#instructions-for-manual-testing)

### Contributions to team-based tasks:
- Set up the initial format for user guide
- Formatted the tables in user guide to explain parameters in command guide
- Labelled bugs that were reported after PE-D and closed duplicate bugs.

### Review/mentoring contributions:
- [Pull Request #31](https://github.com/AY2122S2-CS2113T-T09-1/tp/pull/31#pullrequestreview-906699693)
- [Pull Request #40](https://github.com/AY2122S2-CS2113T-T09-1/tp/pull/40#pullrequestreview-911017528)
- [Pull Request #36](https://github.com/AY2122S2-CS2113T-T09-1/tp/pull/36#discussion_r825325236)
- [Pull Request #53](https://github.com/AY2122S2-CS2113T-T09-1/tp/pull/53#discussion_r830723256)

### Contributions beyond the project team:
- Reviewed another team's project during PE-D [(Link)](https://github.com/thedarie/ped/issues)