# Emily Sim - Project Portfolio Page

## Overview
This page showcases my contributions to the development of WerkIt!, a team project (tP) in the CS2113T
Software Engineering & Object-Oriented Programming module offered by the School of Computing, National University of
Singapore.

### About the Project
WerkIt! is a command line interface (CLI) application written in Java that allows users to create a weekly workout
schedule for them to refer to and follow. More details about the project can be found in the following locations:
* [GitHub Repository](../../)
* [WerkIt! User Guide](../UserGuide.md)
* [WerkIt! Developer Guide](../DeveloperGuide.md)


### Summary of Contributions
### Code Contributed
A detailed report of my code contributions to the tP can be found in the [tP Code Dashboard](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=emilysim00&breakdown=true)
hosted by the module coordinators of CS2113T.

![tP Code Dashboard](../images/ppp/emilysim00/tPCodeDashboard.png)

#### Summary of my feature's code contributions 
* Implemented various WerkIt's features. A summary of these features implemented by me is stated below. 

| WerkIt's Features                           | What it does                                                   | Main Method Implemented | Supporting Methods Implemented                   |
|---------------------------------------------|----------------------------------------------------------------|-------------------------|--------------------------------------------------|
| View all workouts                           | Allows user to view all the workouts created by him/her.       | `listAllWorkout()`      | -                                                | 
| Add/Update a plan for a day in the schedule | Allows user to schedule a workout plan on a particular day.    | `updateDay()`           | `isDayValid()` <br> `isPlanValid()`              |
| View schedule                               | Allows user to view all the plans scheduled for the week.      | `printSchedule()`       | `padWithSpaces()` <br> `convertDayNumberToDay()` |
| Remove plan scheduled on a particular day   | Allows users to remove the plan scheduled on a particular day. | `clearPlan()`           | `isDayValid()` <br> `clearPlan()`                |
| Reset the 7-days workout plan schedule      | Allows user to reset his/her workout plan schedule.            | `clearAllSchedule()`    | `clearPlan()`                                    |

* Wrote the majority of the code in `Day.java`, `ScheduleCommand.java` and `InvalidScheduleException.java`.
* Contributed to some code in exception-related files such as `InvalidCommandException`. 
* Contributed to some parts of the `UI` such as the `printNewScheduleCreatedMessage(Day newDay)` and 
`printClearedScheduleMessage()` methods. The former method will print a successful message to indicate 
that a plan has been successfully scheduled on the day stated. While the latter method will print a successful message to indicate
that a plan has been successfully removed on the day stated. 
* Contributed parts of the `Parser` API, mainly the `createScheduleCommand()` method.

#### Summary of my Logging and Assertions contributions
In addition, I have also written JavaDocs for all the methods stated above. Assertions are added into various
parts of the method to ensure the code works and logging was added to the methods to capture and keep track 
of the information of the methods. 

#### Summary of my Test Code contributions
Wrote JUnit test cases for WerkIt! The summary of the test cases I have written is stated in the table below. 

| Test Files               | Test Case Name                                                                                                                                                                                                                         |
|--------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| WorkoutListTest.java     | listWorkout_expectThreePrints() <br> void listWorkout_expectNoPrints()                                                                                                                                                                 |                    
| ScheduleCommandTest.java | setUserAction_createInvalidAction_expectInvalidCommandException() <br>scheduleCommand_validScheduleUpdateConstruction() <br> scheduleCommand_validScheduleClearConstruction() <br> scheduleCommand_validScheduleClearAllConstruction() |                    
| DayListTest.java         | updateSchedule_expectSuccess() <br> updateSchedule_expectInvalidUpdate() <br> clearSchedule_expectInvalidUpdate() <br> clearSchedule_expectSuccess() <br> clearAllSchedule_expectSuccess()                                             |                  

### Enhancements Implemented

### Contributions to the User Guide (UG)

### Contributions to the Developer Guide (DG)

### Contributions to Team-Based Tasks

### Review/Mentoring Contributions

### Contributions Beyond the Project Team

---

## Reflections on the Team Project (tP)
