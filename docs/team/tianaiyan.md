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
[tP Code Dashboard](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=emilysim00&breakdown=true)
hosted by the module coordinators of CS2113T.

![tP Code Dashboard](../images/ppp/tianaiyan/tPCodeDashboard.png)

Given below are my contributions to the project.
* Implements `updateWorkout()` method in `WorkoutList` class, which allow users to modify the
  repetition value of a workout.
* Contributes to most of the part for updating the workouts saved in plan when a workout is updated.
* Implements `deletePlanContainsDeletedWorkout` method in `PlanList` class to fix the bug which 
  deleting a workout but the deleted workout is still in the plan.
* Contributes to some functions to check validity of inputs.
* Implements `listPlanDetails()` method and `deletePlan()` method in `PlanList` class.<br>
  `listPlanDetails()` allows users to see the workouts in a plan. <br>
  `deletePlan()` allows users to delete a plan from the plan list.
* Contributes to some part of the `UI` class. For instance, `printUpdateWorkoutMessage()` method,
  `printDeletePlanMessage()` method, which tells user that the command is operated 
  correctly, and `help` method related to the functions I implemented.
* Implements the `rewriteAllPlansToFile()` method in `FileManager` class, which will rewrites all
  the plans to local file `plans.txt`.
* Contributes to some parts of the `workoutListTest` class and `planListTest` class.
* Write some part of the User Guide.<br>
  Mainly. the content related to 'Update Workout' feature, 'List Workouts In A Plan' feature, 
  and 'Delete Plan' feature.
* Write some part of the Development Guide.<br>
  Mainly. the content related to 'Update Workout' feature, 'List Workouts In A Plan' feature,
  and 'Delete Plan' feature. <br>
  Some other work includes the drawing sequence diagram of Component Interaction Diagram and
  updating the sequence diagram for `delete` function for workouts after implements the 
  `deletePlanContainsDeletedWorkout()` method.
