# Project Portfolio Page of Musfirah Wani (musfirahe0556596)

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
A detailed report of my code contributions to the tP can be found in the [tP Code Dashboard](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=Musfirah&breakdown=true)
hosted by the module coordinators of CS2113T.

![tP Code Dashboard](../images/ppp/musfirahe0556596/tpCodeDashboard.PNG)

A summary of my code contributions are as follows:
- Implemented the `deleteWorkout()` method for workouts under `WorkoutList.java`.
  This includes logging, assertions and handling of exceptions in the method.
- Contributed to some parts of `WorkoutCommand.java`.
  The code for calling of the `WorkoutList#deleteWorkout()` method
  in the `WorkoutCommand` class was added.
- Contributed to some parts of the `UI` such as the
  `printDeleteWorkoutMessage()` and `printNewPlanCreatedMessage()` methods.
- Contributed to some code in exception-related files such as
  `InvalidCommandException` and mostly in `InvalidPlanException`.
- Contributed most to the creation of `Plan` related classes such as
  `Plan`, `PlanCommand` and `PlanList`. For `PlanList`, contributed to small methods
  and also the main plan features like the creation of plans (`createNewPlan()` and `addNewPlanToLists()`)
  and listing of all plans (`listAllPlan()`).
- Implemented the HashMap data structure in `PlanList` API,
  referenced from team member Alan's implementation.
- Wrote parts of the `Parser` API, mainly the `createPlanCommand()` method and parts
  of the `createWorkoutCommand()` method.
- Wrote JUnit test cases for WerkIt! APIs such as the
  `PlanListTest`, `PlanCommandTest`, `ParserTest`, `WorkoutCommandTest` and `WorkoutListTest`. Writing JUnit
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
  - For both cases above, the PR can be found [here](https://github.com/AY2122S2-CS2113T-T09-2/tp/pull/251).
- Wrote JavaDocs, assertions and logging for methods implemented.

### Contributions to the User Guide (UG)
- Wrote parts of the [introduction](../UserGuide.md#introduction) by specifying target user clearly.
- Wrote the guide on [deleting an existing workout](../UserGuide.md#delete-a-workout-workout-delete).
- Wrote the guide on [creating a new plan](../UserGuide.md#create-a-plan-plan-new).
- Wrote the guide on [listing of plans](../UserGuide.md#list-a-plan-plan-list).
- Wrote some parts of the [Quick Start Guide](../UserGuide.md#quick-start-guide) section.
- Wrote parts of the table of contents.
- Wrote parts of the [Command Summary](../UserGuide.md#command-summary).
- Contributed to a part of the [FAQ](../UserGuide.md#1-my-terminal-in-windows-is-displaying-weird-symbols-when-i-run-the-application),
by suggesting that we should include what weird symbols in the terminal looks like (FAQ written by team member Alan).

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
  - Wrote the `Logic` component of the architecture, including coming up with its diagrams ([link](../DeveloperGuide.md#logic-component)).
  - Wrote the `Workout-related features` under the Feature Overview of the Design section, including coming up with its diagrams ([link](../DeveloperGuide.md#workout-related-features)).
- Wrote part of the [glossary](../DeveloperGuide.md#glossary), by including what the term 'reps' means, 
to address a [PE-D] bug [here](https://github.com/AY2122S2-CS2113T-T09-2/tp/issues/211).
- Wrote parts of the [table of contents](../DeveloperGuide.md#table-of-contents).
- Wrote parts of the [Instructions for manual testing](../DeveloperGuide.md#instructions-for-manual-testing).
  - Wrote manual test cases for [launch and shutdown](../DeveloperGuide.md#launch-and-shutdown), [deleting a workout](../DeveloperGuide.md#deleting-an-existing-workout),
    [creating a plan](../DeveloperGuide.md#creating-a-new-plan) and [listing of plans](../DeveloperGuide.md#listing-all-plans).

### Contributions to Team-Based Tasks
- Maintained the team's Google Drive folder, which includes the team's project notebook
  and other documents for the co-requisite module CS2101.
- Helped to maintain the team's GitHub repository issue tracker by creating issues and also linking
  them to the PRs that addresses the issue.
- Helped to maintain PRs by assigning them to the members who did the PR and also
  assigning them to their respective milestone.
- Helped in release management of WerkIt! (for v1.0 and v2.0)
- Participated and helped to fix general bugs with the team (this includes but not limited to):
  - Added codes to handle empty workout descriptor errors in this [PR](https://github.com/AY2122S2-CS2113T-T09-2/tp/pull/44).
  - Improved code quality by renaming constants properly under `PlanCommand.java` in this [PR](https://github.com/AY2122S2-CS2113T-T09-2/tp/pull/293).
  - Improved code quality by refactoring the `PlanList#createAndAddPlan()` method in this [PR](https://github.com/AY2122S2-CS2113T-T09-2/tp/pull/310).
  - Fix bugs related to `workout /delete` command, due to PE-D findings. [PR link](https://github.com/AY2122S2-CS2113T-T09-2/tp/pull/222).
  - Fix bugs related to `plan /new` command, due to PE-D findings. [PR link](https://github.com/AY2122S2-CS2113T-T09-2/tp/pull/278).
- Update both UG and DG table of contents together with team members.

### Review/Mentoring Contributions
- Constantly reviewed and gave feedbacks to team members' pull requests.
  As of 10/04/2022, I have reviewed 77 out of 205 pull requests.
  Below is a screenshot of the number of PR reviews I have made, by
  using the appropriate GitHub filters `is:pr reviewed-by:@me`:

![PR Reviews](../images/ppp/musfirahe0556596/PRReviews.PNG)

In addition, review comments done by me can also be seen at the CS2113T's tp Comment Dashboard [here](https://nus-cs2113-ay2122s2.github.io/dashboards/contents/tp-comments.html).

### Contributions Beyond the Project Team
- Provided feedback and filed potential bug reports to another team's project (CS2113T-T10-1 SplitLah) as part 
  of the Practical Exam Dry-Run (PED). Below are the potential bugs found:
  - [Bug report 1: Session name not unique across all active session after editing](https://github.com/Musfirahe0556596/ped/issues/1)
  - [Bug report 2: Cost did not divide equally when entering a specific value](https://github.com/Musfirahe0556596/ped/issues/2)
  - [Bug report 3: Activity name not unique across all activities in a session](https://github.com/Musfirahe0556596/ped/issues/3)
  - [Bug report 4: Consideration on who pays what amount to payer directly](https://github.com/Musfirahe0556596/ped/issues/4)
  - [Bug report 5: Unable to edit a session if a group of friends is added at first](https://github.com/Musfirahe0556596/ped/issues/5)

---