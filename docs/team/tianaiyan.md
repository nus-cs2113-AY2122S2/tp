# Tianai - Project Portfolio Page

## Overview
This page showcases my contributions to the development of WerkIt!, a team project (tP) in the CS2113T
Software Engineering & Object-Oriented Programming module offered by the School of Computing, National University of
Singapore.

### About the Project
WerkIt! is a command line interface (CLI) application written in Java that allows users to create a weekly workout
schedule for them to refer to and follow. More details about the project can be found in the following locations:
* [GitHub Repository](https://github.com/AY2122S2-CS2113T-T09-2/tp)
* [WerkIt! User Guide](../UserGuide.md)
* [WerkIt! Developer Guide](../DeveloperGuide.md)

### Summary of Contributions
### Code Contributed
A detailed report of my code contributions to the tP can be found in the 
[tP Code Dashboard](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=TianaiYan&breakdown=true)
hosted by the module coordinators of CS2113T.

* Implemented `updateWorkout()` method in `WorkoutList` class, which allow users to modify the
  repetition value of a workout.
* Contributes to most of the part for updating the workouts saved in plan when a workout is updated.
  Writes `updatePlanContainsUpdatedWorkout()` method in `PlanList` and methods used in this method.
* Implemented `deletePlanContainsDeletedWorkout()` method in `PlanList` class to fix the bug which 
  deleting a workout but the deleted workout is still in the plan.
* Wrote some methods to clear the plan in the schedule when that plan is deleted, but it was not use at last.
* Contributes to some functions to check validity of inputs.
* Implemented `listPlanDetails()` method and `deletePlan()` method in `PlanList` class.<br>
  `listPlanDetails()` allows users to see the workouts in a plan. <br>
  `deletePlan()` allows users to delete a plan from the plan list.
* Contributes to some part of the `UI` class. For instance, `printUpdateWorkoutMessage()` method,
  `printDeletePlanMessage()` method, which tells user that the command is operated 
  correctly, and `help` method related to the functions I implemented.
* Implemented the `rewriteAllPlansToFile()` method in `FileManager` class, which allows to rewrite all
  the plans to local file `plans.txt`.
* Contributes to some parts of the `workoutListTest` class and `planListTest` class.

### Contributions to the UG
* Wrote the guide for feature `workout /update`.
* Wrote the guide for feature `plan /details` and `plan /delete`.

### Contributions to the DG
* Wrote the part for `workout /update` feature.
* Wrote the part for `plan /details` and `plan /delete` features.
* Added manual testing cases for `workout /update`, `plan /details`, and `plan /delete` features.
* Wrote the interaction between components.
* Wrote `plan-related features` under `Design` section.
* Updated sequence diagram `workout /delete` after wrote the methods to delete the plan
  which includes deleted workouts.

### Contributions to Team-Based Tasks
* Maintained the issue tracker: Create issues to be done by me and link them to the corresponding issues when creating PRs.
* Reviewed PRs, includes [Review 1](https://github.com/AY2122S2-CS2113T-T09-2/tp/pull/97), 
  [Review 2](https://github.com/AY2122S2-CS2113T-T09-2/tp/pull/125), 
  [Review 3](https://github.com/AY2122S2-CS2113T-T09-2/tp/pull/253).


### Contributions beyond the project team:
* Reported 6 bugs ([Bug 1](https://github.com/AY2122S2-CS2113T-T10-2/tp/issues/185), 
  [Bug 2](https://github.com/AY2122S2-CS2113T-T10-2/tp/issues/188), 
  [Bug 3](https://github.com/AY2122S2-CS2113T-T10-2/tp/issues/193), 
  [Bug 4](https://github.com/AY2122S2-CS2113T-T10-2/tp/issues/203), 
  [Bug 5](https://github.com/AY2122S2-CS2113T-T10-2/tp/issues/207), 
  [Bug 6](https://github.com/AY2122S2-CS2113T-T10-2/tp/issues/212)) for another team's project (CS2113T-T10-2 PlanITarium).