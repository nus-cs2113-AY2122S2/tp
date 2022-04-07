# Project Portfolio Page of Musfirah Wani (musfirahe0556596)

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
A detailed report of my code contributions to the tP can be found in the [tP Code Dashboard](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=Musfirah&breakdown=true)
hosted by the module coordinators of CS2113T.

![tP Code Dashboard](../images/ppp/musfirahe0556596/tPCodeDashboard.png)

A summary of my code contributions are as follows:
- Implemented the `deleteWorkout()` method for workouts.
  This includes logging, assertions and handling of exceptions in the method.
- Contributed to some parts of `WorkoutCommand.java`.
  The code for calling of the `WorkoutList#deleteWorkout()` method
  in the `WorkoutCommand` class was also added in the `WorkoutList` class.
- Contributed to some parts of the `UI` such as the
  `printDeleteWorkoutMessage()` and `printNewPlanCreatedMessage()` methods.
- Contributed to some code in exception-related files such as 
`InvalidCommandException` and mostly in `InvalidPlanException`.
- Contributed most to the creation of `Plan` related classes such as
`Plan`, `PlanCommand` and `PlanList`. For `PlanList`, contributed to small methods
and also the main plan features like the creation of plans (`createAndAddPlan()`)
and listing of all plans (`listAllPlan()`).
- Implemented the HashMap data structure in `PlanList` API, 
referenced from team member Alan's implementation.
- Wrote parts of the `Parser` API, mainly the `createPlanCommand()` method and parts
of the `createWorkoutCommand()` method.
- Wrote JUnit test cases for WerkIt! APIs such as the
`PlanListTest`, `PlanCommandTest`, `ParserTest`, `WorkoutCommandTest` and `WorkoutListTest`.
This is done to improve the code coverage of the team. Additionally, writing JUnit
test cases for some test cases were difficult as it may require the application
to modify files. Examples of particularly time-consuming test cases are provided below:
  - Under `PlanCommandTest.java`: Writing test case to ensure plan is created successfully.
  It wasn't an easy task because one needs to modify the `plans.txt` file and also create
  plans with a unique name (thus, the `java.util.Random` class is used to help generate plan names with unique
  names). The code can be found [here](https://github.com/AY2122S2-CS2113T-T09-2/tp/blob/master/src/test/java/commands/PlanCommandTest.java),
  particularly the `execute_validCreatePlan_expectSuccess()` test case.
  - Under `WorkoutCommandTest.java`: Writing test case to ensure workouts are created and deleted
  properly. Again, it wasn't an easy task because one needs to modify
  the `workouts.txt` file. In addition, the test case for `delete` workout
  requires one to keep track of the `plans.txt` file too due to the fact that plans containing the deleted workout gets
  deleted too. The code can be found [here](https://github.com/AY2122S2-CS2113T-T09-2/tp/blob/master/src/test/java/commands/WorkoutCommandTest.java),
  particularly the `execute_validCreateWorkout_expectSuccess()` and the `execute_validDeleteWorkout_expectSuccess()` test cases. 
  - For both cases above, the PR can be found [here](https://github.com/AY2122S2-CS2113T-T09-2/tp).
- Wrote JavaDocs, assertions and logging for methods implemented.

### Enhancements Implemented
[to be updated]

### Contributions to the User Guide (UG)
- Wrote the guide on [deleting an existing workout](../UserGuide.md#delete-a-workout-workout-delete).
- Wrote the guide on [creating a new plan](../UserGuide.md#create-a-plan-plan-new).
- Wrote the guide on [listing of plans](../UserGuide.md#list-a-plan-plan-list).
- Modified some parts of the [Quick Start Guide](../UserGuide.md#quick-start-guide) section.
- Wrote parts of the table of contents.

### Contributions to the Developer Guide (DG)
- Wrote the following contributions to the ['Implementation'](../DeveloperGuide.md#implementation) section:
  - How the application deletes an existing workout, including its design considerations
  and sequence diagrams ([link](../DeveloperGuide.md#delete-existing-workout)).
  - How the application creates a new plan, including its design considerations and
  sequence diagrams ([link](../DeveloperGuide.md#create-a-new-plan)).
  - How the application lists all plans, including its sequence diagrams 
  ([link](../DeveloperGuide.md#list-plans)).
- Wrote the following contributions to the ['Design'](../DeveloperGuide.md#design) section:
  - Came up with the [Architecture Diagram](../DeveloperGuide.md#architecture-overview) of the application and described its overview.
  - Wrote the `Logic` component of the architecture, including coming up with its diagrams [link](../DeveloperGuide.md#logic-component).
  - Wrote the `Workout-related features` under the Feature Overview of the Design section, including coming up with its diagrams ([link](../DeveloperGuide.md#workout-related-features)).
- Wrote part of the [glossary](../DeveloperGuide.md#glossary)
- Wrote the [table of contents](../DeveloperGuide.md#table-of-contents).
- Wrote parts of the [Instructions for manual testing](../DeveloperGuide.md#instructions-for-manual-testing).
  - Wrote manual test cases for [deleting a workout](../DeveloperGuide.md#deleting-an-existing-workout),
  [creating a plan](../DeveloperGuide.md#creating-a-new-plan) and [listing of plans](../DeveloperGuide.md#listing-all-plans).

### Contributions to Team-Based Tasks
- Maintained the team's Google Drive folder, which includes the team's project notebook
  and other documents for the co-requisite module CS2101.
- Helped to maintain the team's GitHub repository issue tracker by creating issues and also linking
them to the PRs that addresses the issue.
- Helped to maintain PRs by assigning them to the members who did the PR and also
assigning them to their respective milestone (if team members forget to do these).
- Helped in release management of WerkIt! (for v1.0 and v2.0)
- Participated and helped to fix general bugs with the team. 
An example is the following [PR](https://github.com/AY2122S2-CS2113T-T09-2/tp/pull/44), whereby
codes to handle empty workout descriptor errors are added.

### Review/Mentoring Contributions
- Constantly reviewed and gave feedbacks to team members' pull requests.
As of 06/04/2022, I have reviewed 68 out of 152 pull requests.
Below is a screenshot of the number of PR reviews I had made, by 
using the correct Github filters `is:pr reviewed-by:@me`:

![PR Reviews](../images/ppp/musfirahe0556596/PRReviews.png)

In addition, review comments can also be seen at the CS2113T's tp Comment Dashboard [here](https://nus-cs2113-ay2122s2.github.io/dashboards/contents/tp-comments.html):
Below is a screenshot of the tp Comment Dashboard, as of 06/04/2022:

![tp Comment Dashboard](../images/ppp/musfirahe0556596/tpCommentDashboard.png)


### Contributions Beyond the Project Team
- Provided feedback and filed potential bug reports to another team's project (CS2113T-T10-1 SplitLah) as part
of the Practical Exam Dry-Run (PED). In addition, provided detailed explanation of the bug found instead of just
providing merely a screenshot of it with no explanations. Below
are the potential bugs found:
  - [Bug report 1: Session name not unique across all active session after editing](https://github.com/Musfirahe0556596/ped/issues/1)
  - [Bug report 2: Cost did not divide equally when entering a specific value](https://github.com/Musfirahe0556596/ped/issues/2)
  - [Bug report 3: Activity name not unique across all activities in a session](https://github.com/Musfirahe0556596/ped/issues/3)
  - [Bug report 4: Consideration on who pays what amount to payer directly](https://github.com/Musfirahe0556596/ped/issues/4)
  - [Bug report 5: Unable to edit a session if a group of friends is added at first](https://github.com/Musfirahe0556596/ped/issues/5)

---

## Reflections on the Team Project (tP)
[to be updated]
