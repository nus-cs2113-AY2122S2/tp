# Isaac Lai - Project Portfolio Page

## Overview

Sherpass is a command line interface desktop application to help students manage their academic workloads
by preparing them a timetable to plan their activities, as well as encouraging them to
complete the activities with the use of the study session.

- [Sherpass Github](https://github.com/AY2122S2-CS2113T-T09-1/tp)
- [Sherpass User Guide](https://ay2122s2-cs2113t-t09-1.github.io/tp/UserGuide.html)
- [Sherpass Developer Guide](https://ay2122s2-cs2113t-t09-1.github.io/tp/DeveloperGuide.html)

## Summary of Contributions

### Code contributed

[RepoSense Link](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=laiisaac&breakdown=true)

### Features and enhancements implemented

- Implemented the `Timer` feature in the Study Session

    - **Functionality**: As the name suggests, users can start a timer of their choice (by choosing a default timer mode
  or using a custom duration timer). The 2 available modes are `Countdown`, which is a timer counting down, and
  `Stopwatch`, which records elapsed time. The timer lasts for a set duration, printing the time remaining/elapsed at 
  regular intervals. The timer can be paused, resumed and stopped at any time by the user.
  
    - **Justification**: The timer feature is the main part of the Study Session as it helps users to block out time to 
  be able to focus on their tasks. We gained inspiration from 
  [Pomodoro study sessions](https://examstudyexpert.com/pomodoro-study-method/).
  
    - **Highlights**: Used threads and using their sleep function to keep track of time.
  Although I have worked with threads before in a different programming language, there was still a learning curve when 
  trying to use the various thread functions, since Java's implementation of threads is slightly different and has 
  different terminologies.

- Implemented `TimerLogic` class in the Study Session

  - **Functionality**: `TimerLogic` handles the logic regarding `Timer` class. It handles creation of the `Countdown`
  or `Stopwatch` object when user wants to start a timer and calling of the various methods (pause, resume, stop) on the
  timer.
  
  - **Justification**: As part of separation of concerns, `TimerLogic` object manages the spawned timer object.

  - **Highlights**: The class had to be implemented correctly to avoid any unintended behaviour of threads. The class
  had to be refined over time due to new or changing requirements - e.g. allowing certain commands to be called whilst
  in the study session. Bugs from missed edge cases also led to us refining the logic of the class.

### Contributions to documentation

- **User Guide**:

  - Added documentation for Timer feature under study session (Pull requests 
  [#71](https://github.com/AY2122S2-CS2113T-T09-1/tp/pull/71),
  [#84](https://github.com/AY2122S2-CS2113T-T09-1/tp/pull/84))

- **Developer Guide**:

  - Added documentation for Timer implementation (Pull requests 
  [#63](https://github.com/AY2122S2-CS2113T-T09-1/tp/pull/63),
  [#85](https://github.com/AY2122S2-CS2113T-T09-1/tp/pull/85))

### Contribution to team tasks

- Added documentation in Developer Guide for various sections (Pull request
  [#92](https://github.com/AY2122S2-CS2113T-T09-1/tp/pull/92))

### Review/mentoring contributions

- PRs reviewed (with comments): [#37](https://github.com/AY2122S2-CS2113T-T09-1/tp/pull/37),
[#44](https://github.com/AY2122S2-CS2113T-T09-1/tp/pull/44)

### Contributions beyond the project team

Reviewed a peer team during the Practical Exam dry run - [Link](https://github.com/laiisaac/ped/issues)
