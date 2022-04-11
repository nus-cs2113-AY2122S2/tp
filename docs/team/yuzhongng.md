# Ng Yu Zhong - Project Portfolio Page

## Overview

Sherpass is a command interface desktop application to help students manage their academic workloads
by preparing them a timetable to plan their activities, as well as encouraging them to
complete the activities with the use of the study session.

- [Sherpass Github](https://github.com/AY2122S2-CS2113T-T09-1/tp)
- [Sherpass User Guide](https://ay2122s2-cs2113t-t09-1.github.io/tp/UserGuide.html)
- [Sherpass Developer Guide](https://ay2122s2-cs2113t-t09-1.github.io/tp/DeveloperGuide.html)

### Summary of Contributions

- [Code contributed](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=yuzhongng&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18)
      
    
#### New features:
  - `Timetable` scheduling
    - User would be able to see his timetable for the day, tomorrow, current week, next week,
      current Month, and any specific Month. 

#### Enhancements implemented: 
- Refactor Parser Class
  - Pull Request [#73](https://github.com/AY2122S2-CS2113T-T09-1/tp/pull/73)
  - Most of the features implemented will go through the Parser Class such as `Task`, `Timer`,
    `Storage`, `Timetable` and `Main`.  
  - Split the main parser class to several subclass such as `StorageParser`, `TaskParser`, `TimerParser` 
    and `TimetableParser`.
    Each subclass will handle its own parser command.
  
    
    For eg, All the task related command will be passed to TaskParser. Parser Class will be responsible for decoding 
    User Input. The decoded input will be then pass to its relevant Parser Class such as TaskParser for further 
    computation.
  
- Refactor Timetable Class
  - Pull Request [#77](https://github.com/AY2122S2-CS2113T-T09-1/tp/pull/77)
  - Original `Timetable` class is responsible for a lot of things such as, ensuring proper printing of timetable schedule, 
    computation of Timetable Class. 
  - Split the Timetable Class into 3 different subclass, such as `TimetableLogic`, `TimetablePrinting` and `Timetable`. 
  - `TimetableLogic` will be responsible for all the computation of Timetable.
  - `TimetablePrinting` will be responsible for the proper formatting of Timetable schedule.
  - `Timetable` will be responsible for the type of method available for the user.

- J-UNIT test
  - Timetable Class
    - Pull Request [#61](https://github.com/AY2122S2-CS2113T-T09-1/tp/pull/61)
    - Wrote test cases for timetable class. 
  - TimetableParser Class
    - Pull Request [#153](https://github.com/AY2122S2-CS2113T-T09-1/tp/pull/153)
    - Wrote test cases for timetableParser Class.

#### Contribution to Documentation
  - User Guide
    - Add User Guide for `delete recurring` task
    - Add User Guide for `Timetable`
      - All the display method for Timetable such as show,
        today, tomorrow, any date, current week, next week, current Month, any Month.
    - Update User Guide for `edit command` and `add command` to reflect the latest command syntax.

  - Documentation Guide
    - Documentation related to showing of monthly Timetable schedule.  
  
#### Contribution to team-based tasks
- Refactor Parser Class
- Refactor Timetable Class.
- Modify the help Command
  - Pull Request [#153](https://github.com/AY2122S2-CS2113T-T09-1/tp/pull/153)
  - Update the help command for the add and edit features to reflect the latest implementation.

#### Review/mentoring contributions
- Leave comments and improvement PR [#37](https://github.com/AY2122S2-CS2113T-T09-1/tp/pull/37)

#### Contributions beyond the project team
Review another team application during PE dry run.
[link](https://github.com/yuzhongng/ped/issues)




